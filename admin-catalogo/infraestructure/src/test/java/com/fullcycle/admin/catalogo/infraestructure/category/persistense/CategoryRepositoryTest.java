package com.fullcycle.admin.catalogo.infraestructure.category.persistense;

import com.fullcycle.admin.catalogo.infraestructure.category.MySqlGatewayTest;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@MySqlGatewayTest
public class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  public void givenAnInvalidNullName_whenCallsSave_shouldReturnError() {
    final var expectedPropertyName = "name";
    final var expectedMessage = "not-null property references a null or transient value : com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryJpaEntity.name";

    final var aCategory = Category.newCategory("filmes", "categoria mais", true);
    final var anEntity = CategoryJpaEntity.from(aCategory);
    anEntity.setName(null);

    final var actualException =
        Assertions.assertThrows(DataIntegrityViolationException.class,
            () -> categoryRepository.save(anEntity));

    final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class,
        actualException.getCause());

    Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
    Assertions.assertEquals(expectedMessage, actualCause.getMessage());
  }

  @Test
  public void givenAnInvalidNullCreatedAt_whenCallsSave_shouldReturnError() {
    final var expectedPropertyName = "createdAt";
    final var expectedMessage = "not-null property references a null or transient value : com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryJpaEntity.createdAt";

    final var aCategory = Category.newCategory("filmes", "categoria mais", true);
    final var anEntity = CategoryJpaEntity.from(aCategory);
    anEntity.setCreatedAt(null);

    final var actualException =
        Assertions.assertThrows(DataIntegrityViolationException.class,
            () -> categoryRepository.save(anEntity));

    final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class,
        actualException.getCause());

    Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
    Assertions.assertEquals(expectedMessage, actualCause.getMessage());
  }

  @Test
  public void givenAnInvalidNullUpdatedAt_whenCallsSave_shouldReturnError() {
    final var expectedPropertyName = "updatedAt";
    final var expectedMessage = "not-null property references a null or transient value : com.fullcycle.admin.catalogo.infraestructure.category.persistense.CategoryJpaEntity.updatedAt";

    final var aCategory = Category.newCategory("filmes", "categoria mais", true);
    final var anEntity = CategoryJpaEntity.from(aCategory);
    anEntity.setUpdatedAt(null);

    final var actualException =
        Assertions.assertThrows(DataIntegrityViolationException.class,
            () -> categoryRepository.save(anEntity));

    final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class,
        actualException.getCause());

    Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
    Assertions.assertEquals(expectedMessage, actualCause.getMessage());
  }
}
