package org.fullcycle.admin.catalogo.domain.category;

import java.util.Optional;
import org.fullcycle.admin.catalogo.domain.pagination.Pagination;

public interface CategoryGateway {

  Category create(Category aCategory);

  void deleteById(CategoryId anId);

  Optional<Category> findById(CategoryId anId);

  Category update(Category aCategory);

  Pagination<Category> findAll(CategorySearchQuery aQuery);

}
