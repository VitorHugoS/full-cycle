import CustomerFactory from "../../../domain/customer/factory/customer.factory";
import Address from "../../../domain/customer/value-object/address";
import UpdateCustomerUseCase from "./update.customer.useCase";

const customer = CustomerFactory.createWithAddress(
   "Customer 1",
   new Address("Street 1", 1, "Zip", "City"),
);

const input = {
   id: customer.id,
   name: "Customer 2",
   address: {
      street: "Street 2",
      number: 2,
      zip: "Zip 2",
      city: "City 2",
   },
};


const MockRepository = () => {
   return {
      find: jest.fn().mockReturnValue(Promise.resolve(customer)),
      create: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn(),
   };
};

describe("Test update customer unit usercase", () => {
   it("should update a customer", async () => {
      const customerRepository = MockRepository();
      const useCase = new UpdateCustomerUseCase(customerRepository);

      const output = await useCase.execute(input);
      expect(output).toEqual(input);
   });
});