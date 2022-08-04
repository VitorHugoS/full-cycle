package org.fullcycle.admin.catalogo.application.category.retrieve.list;

import java.time.Instant;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;

public record CategoryListOutput(
    CategoryId id,
    String name,
    String description,
    boolean isActive,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {

  public static CategoryListOutput from(
      final Category aCategory
  ) {
    return new CategoryListOutput(
        aCategory.getId(),
        aCategory.getName(),
        aCategory.getDescription(),
        aCategory.isActive(),
        aCategory.getCreatedAt(),
        aCategory.getUpdatedAt(),
        aCategory.getDeletedAt()
    );
  }
}
