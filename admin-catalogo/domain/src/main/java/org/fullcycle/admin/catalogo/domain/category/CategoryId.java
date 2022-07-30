package org.fullcycle.admin.catalogo.domain.category;

import java.util.Objects;
import java.util.UUID;
import org.fullcycle.admin.catalogo.domain.Identifier;

public class CategoryId extends Identifier {

  private final String value;

  private CategoryId(String value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  public static CategoryId unique() {
    return
        CategoryId.from(UUID.randomUUID());
  }

  public static CategoryId from(final String anId) {
    return new CategoryId(anId);
  }

  public static CategoryId from(final UUID anId) {
    return new CategoryId(anId.toString().toLowerCase());
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final CategoryId that = (CategoryId) o;
    return getValue().equals(that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
