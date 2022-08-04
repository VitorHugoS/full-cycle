package org.fullcycle.admin.catalogo.application.category.retrieve.list;

import org.fullcycle.admin.catalogo.application.UseCase;
import org.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import org.fullcycle.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
    extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {

}
