package org.fullcycle.admin.catalogo.domain.category;

import org.fullcycle.admin.catalogo.domain.validation.Error;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import org.fullcycle.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

  private static final int NAME_MIN_LENGTH = 3;
  public static final int NAME_MAX_LENGTH = 255;
  private final Category category;

  public CategoryValidator(final Category category, final ValidationHandler aHandler) {
    super(aHandler);
    this.category = category;
  }

  @Override
  public void validate() {
    checkNameConstraints();
  }

  private void checkNameConstraints() {
    final var name = this.category.getName();
    if (name == null) {
      this.validationHandler().append(
          new Error("'name' should be not null")
      );
      return;
    }

    if (name.isBlank()) {
      this.validationHandler().append(
          new Error("'name' should be not blank")
      );
    }

    final int length = name.trim().length();
    if (length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH) {
      this.validationHandler().append(
          new Error("'name' must be between 3 and 255 characters")
      );
    }
  }
}
