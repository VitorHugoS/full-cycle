package org.fullcycle.admin.catalogo.application.category.retrieve.list;

import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import org.fullcycle.admin.catalogo.domain.pagination.Pagination;

public class DefaultCategoriesListUseCase extends ListCategoriesUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultCategoriesListUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = categoryGateway;
  }

  @Override
  public Pagination<CategoryListOutput> execute(CategorySearchQuery aQuery) {
    return categoryGateway.findAll(aQuery)
        .map(CategoryListOutput::from);
  }
}
