package org.fullcycle.admin.catalogo.application.category.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

  @InjectMocks
  private DefaultCreateCategoryUseCase usecase;

  @Mock
  private CategoryGateway categoryGateway;

  @Test
  @DisplayName("Given A Valid Command When Calls CreateCategory#execute should return categoryId")
  public void givenAValidCommand_WhenCallsCreateCategoryExecute_ShouldReturnCategoryId() {

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

    Mockito.when(categoryGateway.create(any()))
        .thenAnswer(returnsFirstArg());

    final var actualOutput = usecase.execute(aCommand).get();

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

  @Test
  @DisplayName("Given A Invalid Name When Calls CreateCategory#execute should return domain exception")
  public void givenAInvalidName_WhenCallsCreateCategoryExecute_ShouldReturnDomainException() {

    final String expectedName = null;
    final var expectedDescription = "A categoria mais asssistida";
    final var expectedIsActive = true;
    final var expectedErrorMessage = "'name' should be not null";
    final var expectedErrorCount = 1;

    final var aCommand =
        CreateCategoryCommand
            .with(
                expectedName,
                expectedDescription,
                expectedIsActive
            );

    final var notification =
        usecase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    Mockito.verify(categoryGateway, Mockito.times(0)).create(any());
  }

  @Test
  @DisplayName("Given A Valid Command When Gateway Thrwos Exception should return a execption")
  public void givenAValidCommand_WhenGatewayThrowsException_ShouldReturnAException() {

    final var expectedName = "filmes";
    final var expectedDescription = "A categoria mais asssistida";
    final var expectedIsActive = false;
    final var expectedErrorMessage = "Gateway error";
    final var expectedErrorCount = 1;

    final var aCommand =
        CreateCategoryCommand
            .with(
                expectedName,
                expectedDescription,
                expectedIsActive
            );

    final var notification =
        usecase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
    Mockito.verify(categoryGateway, Mockito.times(1)).create(any());
  }
}
