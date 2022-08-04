package org.fullcycle.admin.catalogo.application.category.retrieve.get;

import java.util.Objects;
import java.util.function.Supplier;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.fullcycle.admin.catalogo.domain.validation.Error;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public CategoryOutput execute(final String anId) {
    final var categoryId = CategoryId.from(anId);
    return
        this.categoryGateway
            .findById(categoryId)
            .map(CategoryOutput::from)
            .orElseThrow(notFound(categoryId));
  }

  private Supplier<DomainException> notFound(final CategoryId anId) {
    return () -> DomainException.with(
        new Error("Category with ID %s was not found".formatted(anId.getValue())));
  }


}
