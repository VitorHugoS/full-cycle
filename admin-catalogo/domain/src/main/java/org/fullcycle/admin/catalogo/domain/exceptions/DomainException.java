package org.fullcycle.admin.catalogo.domain.exceptions;

import java.util.List;
import org.fullcycle.admin.catalogo.domain.validation.Error;

public class DomainException extends NoStackTraceException {

  private final List<Error> errors;

  private DomainException(final String aMessage, final List<Error> anErrors) {
    super(aMessage);
    this.errors = anErrors;
  }

  public List<Error> getErrors() {
    return errors;
  }

  public static DomainException with(final List<Error> anErrors) {
    return new DomainException("", anErrors);
  }

  public static DomainException with(Error anErrors) {
    return new DomainException(anErrors.message(), List.of(anErrors));
  }


}
