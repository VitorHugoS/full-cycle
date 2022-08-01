package org.fullcycle.admin.catalogo.domain.category;

import org.fullcycle.admin.catalogo.domain.validation.Error;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import org.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

  private final Category category;

  public CategoryValidator(final Category category, final ValidationHandler aHandler) {
    super(aHandler);
    this.category = category;
  }

  @Override
  public void validate() {
    if (this.category.getName() == null) {
      this.validationHandler().append(
          new Error("'name' should be not null")
      );
    }
  }
}
