package org.fullcycle.admin.catalogo.application.category.retrieve.get;

import java.time.Instant;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;

public record CategoryOutput(
    CategoryId categoryId,
    String name,
    String description,
    boolean isActive,
    Instant createdAt,
    Instant updatedAt,
    Instant DeletedAt
) {

  public static CategoryOutput from(final Category aCategory) {
    return new CategoryOutput(
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
