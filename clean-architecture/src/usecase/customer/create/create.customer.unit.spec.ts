import CreateCustomerUseCase from "./create.customer.useCase";

const input = {
   name: "Customer 1",
   address: {
      street: "Street 1",
      number: 1,
      zip: "Zip",
      city: "City",
   },
};

const mockRepository = () => {
   return {
      find: jest.fn(),
      create: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn(),
   };
};


describe("Test create customer unit usercase", () => {
   it("should create a customer", async () => {
      const customerRepository = mockRepository();
      const useCase = new CreateCustomerUseCase(customerRepository);

      const output = await useCase.execute(input);
      expect(output).toEqual({
         id: expect.any(String),
         name: input.name,
         address: {
            street: input.address.street,
            number: input.address.number,
            zip: input.address.zip,
            city: input.address.city,
         },
      });
   });

   it("should thrown an error when name is missing", async () => {
      const customerRepository = mockRepository();
      const useCase = new CreateCustomerUseCase(customerRepository);
      
      input.name = '';

      await expect(useCase.execute(input)).rejects.toThrowError("Name is required");
   });

   it("should thrown an error when street is missing", async () => {
      const customerRepository = mockRepository();
      const useCase = new CreateCustomerUseCase(customerRepository);
      
      input.address.street = '';

      await expect(useCase.execute(input)).rejects.toThrowError("Street is required");
   });
});