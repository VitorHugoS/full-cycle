package org.fullcycle.admin.catalogo.domain.validation.handler;

import java.util.List;
import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.fullcycle.admin.catalogo.domain.validation.Error;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class ThrowsValidationHandler implements ValidationHandler {

  @Override
  public ValidationHandler append(final Error anError) {
    throw DomainException.with(anError);
  }

  @Override
  public ValidationHandler append(ValidationHandler anHandler) {
    throw DomainException.with(anHandler.getErrors());
  }

  @Override
  public ValidationHandler validate(Validation aValidation) {
    try {
      aValidation.validate();
    } catch (final Exception ex) {
      throw DomainException.with(List.of(new Error(ex.getMessage())));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return null;
  }
}
