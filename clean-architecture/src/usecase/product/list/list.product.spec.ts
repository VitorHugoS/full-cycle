import ProductFactory from "../../../domain/product/factory/product.factory";
import ListProductUseCase from "./list.product.usecase";

const product1 = ProductFactory.create('a', 'Produto 1', 1);

const product2 = ProductFactory.create('a', 'Produto 2', 10);

const productList = [product1, product2];

const MockRepository = () => { 
   return {
      create: jest.fn(),
      find: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn().mockReturnValue(Promise.resolve(productList)),
   };
};

describe("Unite test for list product use case", () => { 
   it("should list a product", async () => {
      const repository = MockRepository();
      const useCase = new ListProductUseCase(repository);

      const output = await useCase.execute({});
      
      expect(output.products.length).toBe(2);
      for (var position = 0; position < output.products.length; position++) {
         expect(output.products[position].id).toBe(productList[position].id);
         expect(output.products[position].name).toBe(productList[position].name);
         expect(output.products[position].price).toBe(productList[position].price);
      }
   });
});

