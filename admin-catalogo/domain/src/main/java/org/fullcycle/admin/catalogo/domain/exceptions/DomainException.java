package org.fullcycle.admin.catalogo.domain.exceptions;

import java.util.List;
import org.fullcycle.admin.catalogo.domain.validation.Error;

public class DomainException extends RuntimeException {

  private final List<Error> errors;

  private DomainException(final List<Error> anErrors) {
    super("", null, true, false);
    this.errors = anErrors;
  }

  public List<Error> getErrors() {
    return errors;
  }

  public static DomainException with(final List<Error> anErrors) {
    return new DomainException(anErrors);
  }


}
