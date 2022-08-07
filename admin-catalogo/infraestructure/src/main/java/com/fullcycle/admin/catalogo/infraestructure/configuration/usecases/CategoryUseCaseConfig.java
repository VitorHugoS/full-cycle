package com.fullcycle.admin.catalogo.infraestructure.configuration.usecases;

import com.fullcycle.admin.catalogo.infraestructure.category.CategoryMySqlGateway;
import net.bytebuddy.pool.TypePool.Default;
import org.fullcycle.admin.catalogo.application.category.create.CreateCategoryUseCase;
import org.fullcycle.admin.catalogo.application.category.create.DefaultCreateCategoryUseCase;
import org.fullcycle.admin.catalogo.application.category.delete.DefaultDeleteCategoryUseCase;
import org.fullcycle.admin.catalogo.application.category.delete.DeleteCategoryUseCase;
import org.fullcycle.admin.catalogo.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import org.fullcycle.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import org.fullcycle.admin.catalogo.application.category.retrieve.list.DefaultCategoriesListUseCase;
import org.fullcycle.admin.catalogo.application.category.retrieve.list.ListCategoriesUseCase;
import org.fullcycle.admin.catalogo.application.category.update.DefaultUpdateCategoryUseCase;
import org.fullcycle.admin.catalogo.application.category.update.UpdateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

  private final CategoryMySqlGateway categoryMySqlGateway;

  public CategoryUseCaseConfig(CategoryMySqlGateway categoryMySqlGateway) {
    this.categoryMySqlGateway = categoryMySqlGateway;
  }

  @Bean
  public CreateCategoryUseCase createCategoryUseCase() {
    return new DefaultCreateCategoryUseCase(categoryMySqlGateway);
  }

  @Bean
  public UpdateCategoryUseCase updateCategoryUseCase() {
    return new DefaultUpdateCategoryUseCase(categoryMySqlGateway);
  }

  @Bean
  public GetCategoryByIdUseCase getCategoryByIdUseCase() {
    return new DefaultGetCategoryByIdUseCase(categoryMySqlGateway);
  }

  @Bean
  public ListCategoriesUseCase listCategoriesUseCase() {
    return new DefaultCategoriesListUseCase(categoryMySqlGateway);
  }

  @Bean
  public DeleteCategoryUseCase deleteCategoryUseCase() {
    return new DefaultDeleteCategoryUseCase(categoryMySqlGateway);
  }
}
