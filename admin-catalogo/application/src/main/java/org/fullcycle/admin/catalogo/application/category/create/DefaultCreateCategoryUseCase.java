package org.fullcycle.admin.catalogo.application.category.create;

import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Either.Left;
import io.vavr.control.Either.Right;
import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import org.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public Either<Notification, CreateGategoryOutput> execute(final CreateCategoryCommand aCommand) {
    final var aName = aCommand.name();
    final var aDescription = aCommand.description();
    final var isActive = aCommand.isActive();

    final var notification = Notification.create();

    final var aCategory = Category.newCategory(aName, aDescription, isActive);

    aCategory.validate(notification);
    return notification.hasErrors() ? API.Left(notification) : create(aCategory);
  }

  private Either<Notification, CreateGategoryOutput> create(final Category aCategory) {
    return
        API.Try(() -> this.categoryGateway.create(aCategory))
            .toEither()
            .bimap(Notification::create, CreateGategoryOutput::from);
  }
}
