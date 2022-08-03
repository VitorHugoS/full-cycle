package org.fullcycle.admin.catalogo.application.category.update;

import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;

public record UpdateCategoryOutput
    (
        CategoryId id
    ) {

  public static UpdateCategoryOutput
  from(
      final Category category
  ) {
    return new UpdateCategoryOutput
        (
            category.getId()
        );
  }
}
