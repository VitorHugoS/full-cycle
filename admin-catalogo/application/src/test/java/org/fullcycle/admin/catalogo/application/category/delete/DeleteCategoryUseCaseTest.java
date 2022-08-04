package org.fullcycle.admin.catalogo.application.category.delete;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

  @InjectMocks
  private DefaultDeleteCategoryUseCase useCase;

  @Mock
  private CategoryGateway categoryGateway;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(categoryGateway);
  }

  @Test
  public void givenAValidId_whenCallsDeleteGategory_shouldBeOk() {
    final var aCategory = Category.newCategory(
        "filme",
        "descricao",
        true
    );
    final var expectedId = aCategory.getId();

    Mockito
        .doNothing()
        .when(categoryGateway)
        .deleteById(eq(expectedId));

    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, times(1)).deleteById(eq(expectedId));

  }

  @Test
  public void givenAnInvalidId_whenCallsDeleteGategory_shouldBeOk() {

    final var expectedId = CategoryId.from("123");

    Mockito
        .doNothing()
        .when(categoryGateway)
        .deleteById(eq(expectedId));

    Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, times(1)).deleteById(eq(expectedId));
  }

  @Test
  public void givenAValidId_whenGatewayThrowsExceptions_shouldReturnException() {

    final var expectedId = CategoryId.from("123");

    Mockito
        .doThrow(new IllegalStateException("gateway error"))
        .when(categoryGateway)
        .deleteById(eq(expectedId));

    Assertions.assertThrows(IllegalStateException.class,
        () -> useCase.execute(expectedId.getValue()));

    Mockito.verify(categoryGateway, times(1)).deleteById(eq(expectedId));
  }
}
