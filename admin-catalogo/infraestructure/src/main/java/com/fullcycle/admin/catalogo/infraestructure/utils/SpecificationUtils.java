package com.fullcycle.admin.catalogo.infraestructure.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {

  public SpecificationUtils() {
  }

  public static <T> Specification<T> like(final String prop, final String term) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.like(
        criteriaBuilder.upper(root.get(prop)), likePattern(term.toUpperCase()));
  }

  private static String likePattern(final String term) {
    return "%" + term + "%";
  }
}
