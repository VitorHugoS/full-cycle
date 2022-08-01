package org.fullcycle.admin.catalogo.application.category.create;

import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public CreateGategoryOutput execute(final CreateCategoryCommand aCommand) {
    final var aName = aCommand.name();
    final var aDescription = aCommand.description();
    final var isActive = aCommand.isActive();

    final var aCategory = Category.newCategory(
        aName,
        aDescription,
        isActive
    );
    aCategory.validate(new ThrowsValidationHandler());

    this.categoryGateway.create(aCategory);
    return CreateGategoryOutput.from(aCategory);
  }
}
