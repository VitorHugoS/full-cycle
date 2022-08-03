package org.fullcycle.admin.catalogo.application.category.update;

import io.vavr.control.Either;
import org.fullcycle.admin.catalogo.application.UseCase;
import org.fullcycle.admin.catalogo.domain.validation.handler.Notification;

public class UpdateCategoryUseCase
    extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {

  @Override
  public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand anIn) {
    return null;
  }
}
