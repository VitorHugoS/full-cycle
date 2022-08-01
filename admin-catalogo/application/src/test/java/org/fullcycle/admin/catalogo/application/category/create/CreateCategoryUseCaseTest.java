package org.fullcycle.admin.catalogo.application.category.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

  @Test
  @DisplayName("Given A Valid Command When Calls CreateCategory#execute should return categoryId")
  public void givenAValidCommandWhenCallsCreateCategoryExecuteShouldReturnCategoryId() {

    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais asssistida";
    final var expectedIsActive = true;

    final var aCommand =
        CreateCategoryCommand
            .with(
                expectedName,
                expectedDescription,
                expectedIsActive
            );

    final CategoryGateway categoryGateway = Mockito.mock(CategoryGateway.class);
    Mockito.when(categoryGateway.create(Mockito.any())).thenAnswer(returnsFirstArg());

    final var useCase = new DefaultCreateCategoryUseCase(
        categoryGateway
    );

    final var actualOutput = useCase.execute(aCommand);

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(categoryGateway, Mockito.times(1))
        .create(Mockito.argThat(aCategory ->
            Objects.equals(expectedName, aCategory.getName())
                && Objects.equals(expectedDescription, aCategory.getDescription())
                && Objects.equals(expectedIsActive, aCategory.isActive())
                && Objects.nonNull(aCategory.getId())
                && Objects.nonNull(aCategory.getCreatedAt())
                && Objects.nonNull(aCategory.getDescription())
                && Objects.isNull(aCategory.getDeletedAt())));
  }
}
