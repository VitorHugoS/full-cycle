package com.fullcycle.admin.catalogo.infraestructure.category;

import com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryJpaEntity;
import com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryRepository;
import java.util.List;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySqlGatewayTest
public class CategoryMySqlGatewayTest {

  @Autowired
  private CategoryMySqlGateway categoryMySqlGateway;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  public void givenAValidCategory_whenCallsCreate_shouldReturnANewCategory() {
    final var expectedName = "filmes";
    final var expectedDescription = "categoria mais assistida";
    final var isActive = true;

    final var aCategory =
        Category.newCategory(
            expectedName,
            expectedDescription,
            isActive
        );

    Assertions.assertEquals(0, categoryRepository.count());

    final var actualCategory = categoryMySqlGateway.create(aCategory);

    Assertions.assertEquals(1, categoryRepository.count());

    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(isActive, actualCategory.isActive());
    Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
    Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
    Assertions.assertNull(actualCategory.getDeletedAt());

    final var anEntity = categoryRepository
        .findById(aCategory.getId().getValue()).get();

    Assertions.assertEquals(anEntity.getId(), actualCategory.getId().getValue());
    Assertions.assertEquals(anEntity.getName(), actualCategory.getName());
    Assertions.assertEquals(anEntity.getDescription(), actualCategory.getDescription());
    Assertions.assertEquals(anEntity.isActive(), actualCategory.isActive());
    Assertions.assertEquals(anEntity.getCreatedAt(), actualCategory.getCreatedAt());
    Assertions.assertEquals(anEntity.getUpdatedAt(), actualCategory.getUpdatedAt());
    Assertions.assertNull(anEntity.getDeletedAt());
  }

  @Test
  public void givenAValidCategory_whenCallsUpdate_shouldReturnCategoryUpdated() {
    final var expectedName = "filmes";
    final var expectedDescription = "categoria mais assistida";
    final var isActive = true;

    final var aCategory =
        Category.newCategory(
            "film",
            null,
            isActive
        );

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));
    final var aUpdatedCategory = aCategory.clone()
        .update(expectedName, expectedDescription, isActive);

    final var actualCategory = categoryMySqlGateway.update(aUpdatedCategory);

    Assertions.assertEquals(1, categoryRepository.count());

    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(isActive, actualCategory.isActive());
    Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
    Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
    Assertions.assertNull(actualCategory.getDeletedAt());

    final var anEntity = categoryRepository
        .findById(aCategory.getId().getValue()).get();

    Assertions.assertEquals(anEntity.getId(), actualCategory.getId().getValue());
    Assertions.assertEquals(anEntity.getName(), actualCategory.getName());
    Assertions.assertEquals(anEntity.getDescription(), actualCategory.getDescription());
    Assertions.assertEquals(anEntity.isActive(), actualCategory.isActive());
    Assertions.assertEquals(anEntity.getCreatedAt(), actualCategory.getCreatedAt());
    Assertions.assertEquals(anEntity.getUpdatedAt(), actualCategory.getUpdatedAt());
    Assertions.assertNull(anEntity.getDeletedAt());
  }

  @Test
  public void givenAPrePersistedCategoryAndValidCategoryId_whenTryToDeletedIt_shouldDelteCategory() {
    final var aCategory = Category.newCategory("filmes", "teste", true);

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

    Assertions.assertEquals(1, categoryRepository.count());

    categoryMySqlGateway.deleteById(aCategory.getId());

    Assertions.assertEquals(0, categoryRepository.count());
  }

  @Test
  public void givenAnInvalidCategoryId_whenTryToDeletedIt_shouldDelteCategory() {
    Assertions.assertEquals(0, categoryRepository.count());

    categoryMySqlGateway.deleteById(CategoryId.from("invalid"));

    Assertions.assertEquals(0, categoryRepository.count());
  }

  @Test
  public void givenAValidCategoryId_whenCallsFindById_shouldReturnCategory() {
    final var expectedName = "filmes";
    final var expectedDescription = "categoria mais assistida";
    final var isActive = true;

    final var aCategory =
        Category.newCategory(
            expectedName,
            expectedDescription,
            isActive
        );

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

    Assertions.assertEquals(1, categoryRepository.count());

    final var anEntity = categoryRepository
        .findById(aCategory.getId().getValue()).get();

    Assertions.assertEquals(1, categoryRepository.count());

    Assertions.assertEquals(anEntity.getId(), aCategory.getId().getValue());
    Assertions.assertEquals(anEntity.getName(), aCategory.getName());
    Assertions.assertEquals(anEntity.getDescription(), aCategory.getDescription());
    Assertions.assertEquals(anEntity.isActive(), aCategory.isActive());
    Assertions.assertEquals(anEntity.getCreatedAt(), aCategory.getCreatedAt());
    Assertions.assertEquals(anEntity.getUpdatedAt(), aCategory.getUpdatedAt());
    Assertions.assertNull(anEntity.getDeletedAt());
  }

  @Test
  public void givenAValidCategoryIdNotStored_whenCallsFindById_shouldReturnCategory() {
    Assertions.assertEquals(0, categoryRepository.count());

    final var anEntity = categoryRepository
        .findById("id");

    Assertions.assertTrue(anEntity.isEmpty());
  }

  @Test
  public void givenPrePersistedCategories_whenCallsFindAll_shouldReturnPaginated() {
    final var expectedPage = 0;
    final var expectedPerPage = 1;
    final var expectedTotal = 3;

    final var categoryA = Category.newCategory("Filme 1", null, true);
    final var categoryB = Category.newCategory("Filme 2", null, true);
    final var categoryC = Category.newCategory("Filme 3", null, true);

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAll(List.of(
        CategoryJpaEntity.from(categoryA),
        CategoryJpaEntity.from(categoryB),
        CategoryJpaEntity.from(categoryC)
    ));

    Assertions.assertEquals(3, categoryRepository.count());

    final var query = new CategorySearchQuery(0, 1, "", "name", "asc");
    final var actualResult = categoryMySqlGateway.findAll(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryA.getId(), actualResult.items().get(0).getId());
  }

  @Test
  public void givenEmptyCategoriesTable_whenCallsFindAll_shouldReturnEmptyPage() {
    final var expectedPage = 0;
    final var expectedPerPage = 1;
    final var expectedTotal = 0;

    Assertions.assertEquals(0, categoryRepository.count());

    final var query = new CategorySearchQuery(0, 1, "", "name", "asc");
    final var actualResult = categoryMySqlGateway.findAll(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());
  }

  @Test
  public void givenFollowPagination_whenCallsFindAllWithPage1_shouldReturnPaginated() {
    var expectedPage = 0;
    final var expectedPerPage = 1;
    final var expectedTotal = 3;

    final var categoryA = Category.newCategory("Filme 1", null, true);
    final var categoryB = Category.newCategory("Filme 2", null, true);
    final var categoryC = Category.newCategory("Filme 3", null, true);

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAll(List.of(
        CategoryJpaEntity.from(categoryA),
        CategoryJpaEntity.from(categoryB),
        CategoryJpaEntity.from(categoryC)
    ));

    Assertions.assertEquals(3, categoryRepository.count());

    var query = new CategorySearchQuery(0, 1, "", "name", "asc");
    var actualResult = categoryMySqlGateway.findAll(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryA.getId(), actualResult.items().get(0).getId());

    query = new CategorySearchQuery(1, 1, "", "name", "asc");
    actualResult = categoryMySqlGateway.findAll(query);

    expectedPage = 1;
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryB.getId(), actualResult.items().get(0).getId());

    query = new CategorySearchQuery(2, 1, "", "name", "asc");
    actualResult = categoryMySqlGateway.findAll(query);

    expectedPage = 2;
    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryC.getId(), actualResult.items().get(0).getId());


  }

  @Test
  public void givenFollowPaginationAndDocAsTerms_whenCallsFindAllWithPage1_shouldReturnPaginated() {
    var expectedPage = 0;
    final var expectedPerPage = 1;
    final var expectedTotal = 1;

    final var categoryA = Category.newCategory("Filmes", null, true);
    final var categoryB = Category.newCategory("Series", null, true);
    final var categoryC = Category.newCategory("Documentarios", null, true);

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAll(List.of(
        CategoryJpaEntity.from(categoryA),
        CategoryJpaEntity.from(categoryB),
        CategoryJpaEntity.from(categoryC)
    ));

    Assertions.assertEquals(3, categoryRepository.count());

    var query = new CategorySearchQuery(0, 1, "doc", "name", "asc");
    var actualResult = categoryMySqlGateway.findAll(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryC.getId(), actualResult.items().get(0).getId());

  }

  @Test
  public void givenFollowPaginationAndDescriptionAsTerms_whenCallsFindAllWithPage1_shouldReturnPaginated() {
    var expectedPage = 0;
    final var expectedPerPage = 1;
    final var expectedTotal = 1;

    final var categoryA = Category.newCategory("Filmes", "categoria menos assistida", true);
    final var categoryB = Category.newCategory("Series", "a categoria mais assistida", true);
    final var categoryC = Category.newCategory("Documentarios", "uma categoria assistida", true);

    Assertions.assertEquals(0, categoryRepository.count());

    categoryRepository.saveAll(List.of(
        CategoryJpaEntity.from(categoryA),
        CategoryJpaEntity.from(categoryB),
        CategoryJpaEntity.from(categoryC)
    ));

    Assertions.assertEquals(3, categoryRepository.count());

    var query = new CategorySearchQuery(0, 1, "mais assistida", "description", "asc");
    var actualResult = categoryMySqlGateway.findAll(query);

    Assertions.assertEquals(expectedPage, actualResult.currentPage());
    Assertions.assertEquals(expectedPerPage, actualResult.perPage());
    Assertions.assertEquals(expectedTotal, actualResult.total());

    Assertions.assertEquals(categoryB.getId(), actualResult.items().get(0).getId());

  }

}
