package org.fullcycle.admin.catalogo.application.category.retrieve.list;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import org.fullcycle.admin.catalogo.domain.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ListCategoryUseCaseTest {

  @InjectMocks
  private DefaultCategoriesListUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }

  @Test
  public void givenAValidQuery_whellCallsListUseCase_thenShouldReturnCategories() {
    final var categories = List.of(
        Category.newCategory("filmes", "desc", true),
        Category.newCategory("series", "desc", true)
    );

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage,
        categories.size(), categories);
    final var query =
        new CategorySearchQuery(
            expectedPage,
            expectedPerPage,
            expectedTerms,
            expectedSort,
            expectedDirection
        );

    final var expectedItemsCount = 2;
    final var expectedResult = expectedPagination
        .map(
            CategoryListOutput::from
        );

    when(
        categoryGateway
            .findAll(eq(query))
    ).thenReturn(expectedPagination);

    final var expectedOutput = useCase.execute(query);

    Assertions.assertEquals(expectedItemsCount, expectedOutput.items().size());
  }

  @Test
  public void givenAValidQuery_whellHasNoResults_thenShouldReturnEmptyCategories() {
    final var categories = List.<Category>of();

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";

    final var expectedPagination = new Pagination<>(expectedPage, expectedPerPage,
        categories.size(), categories);
    final var query =
        new CategorySearchQuery(
            expectedPage,
            expectedPerPage,
            expectedTerms,
            expectedSort,
            expectedDirection
        );

    final var expectedItemsCount = 0;
    final var expectedResult = expectedPagination
        .map(
            CategoryListOutput::from
        );

    when(
        categoryGateway
            .findAll(eq(query))
    ).thenReturn(expectedPagination);

    final var expectedOutput = useCase.execute(query);

    Assertions.assertEquals(expectedItemsCount, expectedOutput.items().size());
  }

  @Test
  public void givenAValidQuery_whellCategoryGatewayThrowsException_thenShouldReturnException() {

    final var expectedPage = 0;
    final var expectedPerPage = 10;
    final var expectedTerms = "";
    final var expectedSort = "createdAt";
    final var expectedDirection = "asc";
    final var query =
        new CategorySearchQuery(
            expectedPage,
            expectedPerPage,
            expectedTerms,
            expectedSort,
            expectedDirection
        );

    when(
        categoryGateway
            .findAll(eq(query))
    ).thenThrow(new IllegalStateException("gateway error"));

    final var expectedOutput =
        Assertions.assertThrows(
            IllegalStateException.class, () -> useCase.execute(query)
        );

    Assertions.assertEquals("gateway error", expectedOutput.getMessage());
  }
}
