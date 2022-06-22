import Product from "../../../domain/product/entity/product";
import FindProductUseCase from "./find.product.useCase";

const product = new Product("123", "Product 1", 1);

const mockRepository = () => { 
  return {
    find: jest.fn().mockReturnValue(Promise.resolve(product)),
    create: jest.fn().mockReturnValue(Promise.resolve()),
    update: jest.fn(),
    findAll: jest.fn(),
  };
};

describe("Test find product unit usercase", () => { 
  it("should find a customer", async () => {
    const productRepository = mockRepository();
    const useCase = new FindProductUseCase(productRepository);
    
    await productRepository.create(product);

    const input = {
      id: "123",
    };
    const output = {
      id: "123",
      name: "Product 1",
      price: 1,
    };
    const result = await useCase.execute(input);

    expect(result).toEqual(output);
  });
  it("should not find a product", async () => {
    const productRepository = mockRepository();
    productRepository.find.mockImplementation(() => {
      throw new Error("Product not found");
      
    });
    const useCase = new FindProductUseCase(productRepository);
    const input = {
      id: "1234",
    };
    expect(() => useCase.execute(input)).rejects.toThrow("Product not found");
  });
});