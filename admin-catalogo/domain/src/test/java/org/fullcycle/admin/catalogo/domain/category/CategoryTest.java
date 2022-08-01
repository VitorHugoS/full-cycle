package org.fullcycle.admin.catalogo.domain.category;

import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Should unit test Category")
public class CategoryTest {

  @Test
  @DisplayName("Given a valid params when call new Category then instantiate a category")
  public void givenAvalidaParams_whenCallNewCategory_thenInstantiateACategory() {
    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var actualCategory = Category.newCategory(
        expectedName,
        expectedDescription,
        expectedIsActive
    );

    Assertions.assertNotNull(actualCategory);
    Assertions.assertNotNull(actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertNotNull(actualCategory.getUpdatedAt());
    Assertions.assertNull(actualCategory.getDeletedAt());
  }

  @Test
  @DisplayName("Given an invalid null name when call new Category then should return error")
  public void givenAnInValidNullName_whenCallNewCategory_thenShouldReturnError() {
    final String expectedName = null;
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var expectedErrorMessage = "'name' should be not null";
    final var expectedErrorCount = 1;

    final var actualCategory = Category.newCategory(
        expectedName,
        expectedDescription,
        expectedIsActive
    );
    final var actualException = Assertions.assertThrows(
        DomainException.class,
        () -> actualCategory.validate(new ThrowsValidationHandler())
    );

    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
}
