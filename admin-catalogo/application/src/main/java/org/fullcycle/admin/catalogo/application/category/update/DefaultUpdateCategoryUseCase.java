package org.fullcycle.admin.catalogo.application.category.update;

import static io.vavr.API.Left;

import io.vavr.API;
import io.vavr.control.Either;
import java.util.function.Supplier;
import org.fullcycle.admin.catalogo.domain.category.Category;
import org.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.fullcycle.admin.catalogo.domain.validation.Error;
import org.fullcycle.admin.catalogo.domain.validation.handler.Notification;

public class DefaultUpdateCategoryUseCase
    extends UpdateCategoryUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = categoryGateway;
  }

  @Override
  public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {
    final var anId = CategoryId.from(aCommand.id());
    final var aName = aCommand.name();
    final var aDescription = aCommand.description();
    final var isActive = aCommand.isActive();

    final var aCategory = this.categoryGateway
        .findById(anId)
        .orElseThrow(notFound(anId));

    final var notification = Notification.create();

    aCategory
        .update(aName, aDescription, isActive)
        .validate(notification);

    return notification.hasErrors() ? Left(notification) : update(aCategory);
  }

  private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
    return API.Try(
            () -> this.categoryGateway.update(aCategory)
        ).toEither()
        .bimap(Notification::create, UpdateCategoryOutput::from);
  }

  private Supplier<DomainException> notFound(final CategoryId anId) {
    return () -> DomainException.with(
        new Error("Category with ID %s was not found".formatted(anId.getValue())));
  }
}
