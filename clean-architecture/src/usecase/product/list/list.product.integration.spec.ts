import { Sequelize } from "sequelize-typescript";
import ProductModel from "../../../infrastructure/product/repository/sequelize/product.model";
import ProductRepository from "../../../infrastructure/product/repository/sequelize/product.repository";
import CreateProductUseCase from "../create/create.product.useCase";
import ListProductUseCase from "./list.product.usecase";

describe("Test find a list product usercase", () => { 
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
   
  it("should find a list products", async () => {
    const productRepository = new ProductRepository();
    const useCase = new CreateProductUseCase(productRepository);
    const useCaseList = new ListProductUseCase(productRepository);
    const input = {
      type: 'a',
      name: 'Product 1',
      price: 1,
    };

    const input2 = {
      type: 'b',
      name: 'Product 2',
      price: 10,
    };
    const productList = await Promise.all([useCase.execute(input), useCase.execute(input2)]);
    const output = await useCaseList.execute({});
    expect(output.products.length).toBe(2);
      for (var position = 0; position < output.products.length; position++) {
         expect(output.products[position].name).toBe(productList[position].name);
         expect(output.products[position].price).toBe(productList[position].price);
      }
  });
});