package com.fullcycle.admin.catalogo.infraestructure.category;

import static com.fullcycle.admin.catalogo.infraestructure.utils.SpecificationUtils.like;

import com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryJpaEntity;
import com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryRepository;
import com.fullcycle.admin.catalogo.infraestructure.utils.SpecificationUtils;
import java.util.Optional;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import org.fullcycle.admin.catalogo.domain.pagination.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategoryMySqlGateway implements CategoryGateway {

  private final CategoryRepository categoryRepository;

  public CategoryMySqlGateway(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category create(final Category aCategory) {
    return save(aCategory);
  }

  @Override
  public void deleteById(final CategoryId anId) {
    final String anIdValue = anId.getValue();
    if (this.categoryRepository.existsById(anIdValue)) {
      this.categoryRepository.deleteById(anIdValue);
    }
  }

  @Override
  public Optional<Category> findById(final CategoryId anId) {
    return this.categoryRepository
        .findById(anId.getValue())
        .map(CategoryJpaEntity::toAggregate);
  }

  @Override
  public Category update(final Category aCategory) {
    return save(aCategory);
  }

  @Override
  public Pagination<Category> findAll(final CategorySearchQuery aQuery) {
    final var page = PageRequest.of(
        aQuery.page(),
        aQuery.perPage(),
        Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
    );

    final var specifications = Optional.ofNullable(aQuery.terms())
        .filter(str -> !str.isBlank())
        .map(
            str -> SpecificationUtils.
                <CategoryJpaEntity>like("name", str)
                .or(like("description", str))
        ).orElse(null);

    final var pageResult = this.categoryRepository.findAll(Specification.where(specifications),
        page);
    return new Pagination<>(
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        pageResult.map(CategoryJpaEntity::toAggregate).toList()
    );
  }

  private Category save(final Category aCategory) {
    return this.categoryRepository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
  }
}
