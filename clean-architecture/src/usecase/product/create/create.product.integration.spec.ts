import { Sequelize } from "sequelize-typescript";
import ProductModel from "../../../infrastructure/product/repository/sequelize/product.model";
import ProductRepository from "../../../infrastructure/product/repository/sequelize/product.repository";
import CreateProductUseCase from "./create.product.useCase";

describe("Test create product usercase", () => { 
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
   
  it("should create a product", async () => {
    const productRepository = new ProductRepository();
    const useCase = new CreateProductUseCase(productRepository);
    const input = {
      type: 'a',
      name: 'Product 1',
      price: 1,
    };
    const result = await useCase.execute(input);
    expect(result).toEqual({
         id: expect.any(String),
         name: input.name,
         price: input.price,
      });
  });
});