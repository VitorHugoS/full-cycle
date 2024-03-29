package org.fullcycle.admin.catalogo.domain.category;

import java.time.Instant;
import java.util.Objects;
import org.fullcycle.admin.catalogo.domain.AggregateRoot;
import org.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class Category extends AggregateRoot<CategoryId> implements Cloneable {

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
    this.createdAt = Objects.requireNonNull(aCreatedAt, "'createdAt' should not be null");
    this.updatedAt = Objects.requireNonNull(anUpdatedAt, "'updatedAt' should not be null");
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

  public static Category with(
      final CategoryId categoryId,
      final String name,
      final String description,
      final boolean isActive,
      final Instant createdAt,
      final Instant updatedAt,
      final Instant deletedAt) {
    return new Category(
        categoryId,
        name,
        description,
        isActive,
        createdAt,
        updatedAt,
        deletedAt
    );
  }

  public static Category with(final Category category) {
    return Category.with(
        category.getId(),
        category.getName(),
        category.getDescription(),
        category.isActive(),
        category.getCreatedAt(),
        category.getUpdatedAt(),
        category.getUpdatedAt()
    );
  }

  @Override
  public void validate(final ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
  }

  public Category deactivate() {
    if (getDeletedAt() == null) {
      this.deletedAt = Instant.now();
    }
    this.active = false;
    this.updatedAt = Instant.now();
    return this;
  }

  public Category activate() {
    this.deletedAt = null;
    this.active = true;
    this.updatedAt = Instant.now();
    return this;
  }

  public Category update(
      final String aName,
      final String aDescription,
      final boolean isActive) {
    if (isActive) {
      activate();
    } else {
      deactivate();
    }
    this.name = aName;
    this.description = aDescription;
    this.updatedAt = Instant.now();
    return this;
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

  @Override
  public Category clone() {
    try {
      Category clone = (Category) super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}