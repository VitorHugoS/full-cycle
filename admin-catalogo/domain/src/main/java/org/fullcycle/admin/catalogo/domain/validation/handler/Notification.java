package org.fullcycle.admin.catalogo.domain.validation.handler;

import java.util.ArrayList;
import java.util.List;
import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.fullcycle.admin.catalogo.domain.validation.Error;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class Notification implements ValidationHandler {

  private final List<Error> errors;

  private Notification(final List<Error> errors) {
    this.errors = errors;
  }

  public static Notification create() {
    return new Notification(new ArrayList<>());
  }

  public static Notification create(final Error anError) {
    return
        new Notification(new ArrayList<>())
            .append(anError);
  }

  public static Notification create(final Throwable t) {
    return
        new Notification(new ArrayList<>())
            .append(new Error(t.getMessage()));
  }

  @Override
  public Notification append(final Error anError) {
    this.errors.add(anError);
    return this;
  }

  @Override
  public Notification append(final ValidationHandler anHandler) {
    this.errors.addAll(anHandler.getErrors());
    return this;
  }

  @Override
  public Notification validate(Validation aValidation) {
    try {
      aValidation.validate();
    } catch (final DomainException domain) {
      this.errors.addAll(domain.getErrors());
    } catch (final Throwable t) {
      this.errors.add(new Error(t.getMessage()));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return this.errors;
  }
}
