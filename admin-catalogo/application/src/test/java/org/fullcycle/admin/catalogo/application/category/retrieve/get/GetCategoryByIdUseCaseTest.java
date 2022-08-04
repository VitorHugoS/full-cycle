package org.fullcycle.admin.catalogo.application.category.retrieve.get;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import java.util.Optional;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {

  @InjectMocks
  private DefaultGetCategoryByIdUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;


  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }

  @Test
  public void givenAValidId_whenCallsGetCategory_shouldReturnACategory() {
    final var aCategory = Category.newCategory(
        "filmes",
        "teste",
        true
    );

    final var anExpectedId = aCategory.getId();

    Mockito.when(categoryGateway.findById(eq(anExpectedId)))
        .thenReturn(Optional.of(aCategory.clone()));

    final var actualCategory = useCase.execute(anExpectedId.getValue());

    Assertions.assertEquals(CategoryOutput.from(aCategory), actualCategory);
  }

  @Test
  public void givenAninvalidId_whenCallsGetCategory_shouldReturnNotFound() {
    final var expectedErrorMessage = "Category with ID 123 was not found";
    final var expectedId = CategoryId.from("123");

    Mockito.when(categoryGateway.findById(eq(expectedId)))
        .thenReturn(Optional.empty());

    final var actualExceptions =
        Assertions.assertThrows(
            DomainException.class,
            () -> useCase.execute(expectedId.getValue())
        );

    Assertions.assertEquals(expectedErrorMessage, actualExceptions.getMessage());
  }

  @Test
  public void givenAValidId_whenCallsGetCategory_shouldReturnException() {
    final var expectedId = CategoryId.from("123");

    Mockito.when(categoryGateway.findById(eq(expectedId)))
        .thenThrow(new IllegalStateException("gateway error"));

    Assertions.assertThrows(IllegalStateException.class,
        () -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, times(1)).findById(eq(expectedId));

  }
}
