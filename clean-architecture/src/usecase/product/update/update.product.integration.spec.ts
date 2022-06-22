import { Sequelize } from "sequelize-typescript";
import ProductModel from "../../../infrastructure/product/repository/sequelize/product.model";
import ProductRepository from "../../../infrastructure/product/repository/sequelize/product.repository";
import CreateProductUseCase from "../create/create.product.useCase";
import UpdateProductUseCase from "./update.product.useCase";

describe("Test update product usercase", () => { 
   let sequelize: Sequelize;

  beforeEach(async () => {
    sequelize = new Sequelize({
      dialect: "sqlite",
      storage: ":memory:",
      logging: false,
      sync: { force: true },
    });

    await sequelize.addModels([ProductModel]);
    await sequelize.sync();
  });

  afterEach(async () => {
    await sequelize.close();
  });
   
  it("should update a product", async () => {
    const productRepository = new ProductRepository();
    const useCase = new CreateProductUseCase(productRepository);
    const useCaseUpdate = new UpdateProductUseCase(productRepository);
    const input = {
      type: 'a',
      name: 'Product 1',
      price: 1,
    };
    const product = await useCase.execute(input);
    const input2 = {
      id: product.id,
      name: 'Product 2',
      price: 10,
    };
    const output = await useCaseUpdate.execute(input2);
    expect(output).toEqual({
      id: input2.id,
      name: input2.name,
      price: input2.price,
    });
  });
});