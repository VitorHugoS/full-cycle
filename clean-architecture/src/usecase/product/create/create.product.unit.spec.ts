import CreateProductUseCase from "./create.product.useCase";

const input = {
   type: 'a',
   name: "Product 1",
   price: 1,
};

const mockRepository = () => {
   return {
      find: jest.fn(),
      create: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn(),
   };
};


describe("Test create product unit usercase", () => {
   it("should create a product", async () => {
      const productRepository = mockRepository();
      const useCase = new CreateProductUseCase(productRepository);

      const output = await useCase.execute(input);
      expect(output).toEqual({
         id: expect.any(String),
         name: input.name,
         price: input.price,
      });
   });

   it("should thrown an error when name is missing", async () => {
      const productRepository = mockRepository();
      const useCase = new CreateProductUseCase(productRepository);
      
      input.name = '';

      await expect(useCase.execute(input)).rejects.toThrowError("Name is required");
   });

   it("should thrown an error when price is less then 0", async () => {
      const productRepository = mockRepository();
      const useCase = new CreateProductUseCase(productRepository);
      input.name = 'Product 1';
      input.price = -1;

      await expect(useCase.execute(input)).rejects.toThrowError("Price must be greater than zero");
   });
});