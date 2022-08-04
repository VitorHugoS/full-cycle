package org.fullcycle.admin.catalogo.application.category.delete;

import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public void execute(final String anId) {
    this.categoryGateway.deleteById(CategoryId.from(anId));
  }
}
