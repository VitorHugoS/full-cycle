package org.fullcycle.admin.catalogo.application.category.create;

import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;

public record CreateGategoryOutput(
    CategoryId id
) {

  public static CreateGategoryOutput from(final Category aCategory) {
    return new CreateGategoryOutput(aCategory.getId());
  }
}
