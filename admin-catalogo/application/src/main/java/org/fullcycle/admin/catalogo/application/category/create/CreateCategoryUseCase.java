package org.fullcycle.admin.catalogo.application.category.create;

import io.vavr.control.Either;
import org.fullcycle.admin.catalogo.application.UseCase;
import org.fullcycle.admin.catalogo.domain.validation.handler.Notification;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateGategoryOutput>> {

}
