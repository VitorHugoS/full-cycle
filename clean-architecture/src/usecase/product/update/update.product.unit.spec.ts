import Product from "../../../domain/product/entity/product";
import ProductFactory from "../../../domain/product/factory/product.factory";
import UpdateProductUseCase from "./update.product.useCase";

const productFactory = {
   type: 'a',
   name: 'produto 1',
   price: 100
};
const product = ProductFactory.create('a', productFactory.name, productFactory.price);

const input = {
   id: product.id,
   name: product.name,
   price: product.price,
};

const changeProduct = {
   id: product.id,
   name: 'produto 2',
   price: 1,
}

const MockRepository = () => {
   return {
      find: jest.fn().mockReturnValue(Promise.resolve(new Product(input.id, input.name, input.price))),
      create: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn(),
   };
};

describe("Test update product unit usercase", () => {
   it("should update a product", async () => {
      const productRepository = MockRepository();
      const useCase = new UpdateProductUseCase(productRepository);

      const output = await useCase.execute(changeProduct);
      expect(output).toEqual(changeProduct);
   });
});