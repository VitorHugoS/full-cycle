package org.fullcycle.admin.catalogo.domain.category;

import java.time.Instant;
import org.fullcycle.admin.catalogo.domain.AggregateRoot;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class Category extends AggregateRoot<CategoryId> {

  private String name;
  private String description;
  private boolean active;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  private Category(
      final CategoryId anId,
      final String aName,
      final String aDescription,
      final boolean isActive,
      final Instant aCreatedAt,
      final Instant anUpdatedAt,
      final Instant aDeletedAt) {
    super(anId);
    this.name = aName;
    this.description = aDescription;
    this.active = isActive;
    this.createdAt = aCreatedAt;
    this.updatedAt = anUpdatedAt;
    this.deletedAt = aDeletedAt;
  }

  public static Category newCategory(
      final String aName,
      final String aDescription,
      final boolean isActive
  ) {
    final var id = CategoryId.unique();
    final var now = Instant.now();
    final var deletedAt = isActive ? null : Instant.now();
    return new Category(
        id,
        aName,
        aDescription,
        isActive,
        now,
        now,
        deletedAt
    );
  }

  @Override
  public void validate(final ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
  }

  public CategoryId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }
}