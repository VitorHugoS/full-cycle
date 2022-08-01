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

  @Test
  @DisplayName("Given an invalid empty name when call new Category then should return error")
  public void givenAnInValidEmptyName_whenCallNewCategory_thenShouldReturnError() {
    final var expectedName = "  ";
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

  @Test
  @DisplayName("Given an invalid name length less than 3 when call new Category then should return error")
  public void givenAnInValidNameLengthLessThan3_whenCallNewCategory_thenShouldReturnError() {
    final var expectedName = "ab ";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
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

  @Test
  @DisplayName("Given an invalid name greather than 255 when call new Category then should return error")
  public void givenAnInValidNameLengthGreatherThan255_whenCallNewCategory_thenShouldReturnError() {
    final var expectedName = "ab".repeat(500);
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
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

  @Test
  @DisplayName("Given a Valid True isActive when call new Category Deactivate then return category inactive")
  public void givenAValidTrueIsActive_whenCallNewCategoryDeactivate_thenShouldReturnCategoryInactive() {
    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;

    final var aCategory = Category.newCategory(
        expectedName,
        expectedDescription,
        true
    );
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

    final var updatedAt = aCategory.getUpdatedAt();
    final var createdAt = aCategory.getCreatedAt();

    Assertions.assertTrue(aCategory.isActive());
    Assertions.assertNull(aCategory.getDeletedAt());

    final var actualCategory = aCategory.deactivate();

    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertTrue(
        actualCategory.getUpdatedAt().isAfter(updatedAt)
    );
    Assertions.assertEquals(actualCategory.getCreatedAt(), createdAt);
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }

  @Test
  @DisplayName("Given a Valid False isActive when call new Category Activate then return category activate")
  public void givenAValidFalseIsActive_whenCallNewCategoryActive_thenShouldReturnCategoryActive() {
    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var aCategory = Category.newCategory(
        expectedName,
        expectedDescription,
        false
    );
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

    final var updatedAt = aCategory.getUpdatedAt();
    final var createdAt = aCategory.getCreatedAt();

    Assertions.assertFalse(aCategory.isActive());
    Assertions.assertNotNull(aCategory.getDeletedAt());

    final var actualCategory = aCategory.activate();

    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(actualCategory.getCreatedAt(), createdAt);
    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertTrue(
        actualCategory.getUpdatedAt().isAfter(updatedAt)
    );
    Assertions.assertNull(actualCategory.getDeletedAt());
  }

  @Test
  @DisplayName("Given a Valid Category when call update then return category updated")
  public void givenAValidCategory_whenCallUpdate_thenShouldReturnCategoryUpdated() {
    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var aCategory = Category.newCategory(
        "filme",
        "categoria",
        false
    );
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

    final var updatedAt = aCategory.getUpdatedAt();
    final var createdAt = aCategory.getCreatedAt();

    final var actualCategory = aCategory
        .update(
            expectedName,
            expectedDescription,
            expectedIsActive
        );

    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(actualCategory.getCreatedAt(), createdAt);
    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertTrue(
        actualCategory.getUpdatedAt().isAfter(updatedAt)
    );
    Assertions.assertNull(actualCategory.getDeletedAt());
  }

  @Test
  @DisplayName("Given a Valid Category when call update to inactive then return category updated")
  public void givenAValidCategory_whenCallUpdateToInactive_thenShouldReturnCategoryUpdated() {
    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = false;

    final var aCategory = Category.newCategory(
        "filme",
        "categoria",
        true
    );
    Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertTrue(aCategory.isActive());
    Assertions.assertNull(aCategory.getDeletedAt());

    final var updatedAt = aCategory.getUpdatedAt();
    final var createdAt = aCategory.getCreatedAt();

    final var actualCategory = aCategory
        .update(
            expectedName,
            expectedDescription,
            expectedIsActive
        );

    Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

    Assertions.assertEquals(actualCategory.getCreatedAt(), createdAt);
    Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
    Assertions.assertEquals(expectedName, actualCategory.getName());
    Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertNotNull(actualCategory.getCreatedAt());
    Assertions.assertTrue(
        actualCategory.getUpdatedAt().isAfter(updatedAt)
    );
    Assertions.assertNotNull(actualCategory.getDeletedAt());
  }
}
