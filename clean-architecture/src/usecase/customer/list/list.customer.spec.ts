import CustomerFactory from "../../../domain/customer/factory/customer.factory";
import Address from "../../../domain/customer/value-object/address";
import ListCustomerUseCase from "./list.customer.usecase";

const customer1 = CustomerFactory.createWithAddress(
   "Jhon Doe",
   new Address("Street 1", 1, "12345", "City")
);

const customer2 = CustomerFactory.createWithAddress(
   "Jhon Doe",
   new Address("Street 1", 1, "12345", "City")
);

const customerList = [customer1, customer2];

const MockRepository = () => { 
   return {
      create: jest.fn(),
      find: jest.fn(),
      update: jest.fn(),
      findAll: jest.fn().mockReturnValue(Promise.resolve([customer1, customer2])),
   };
};

describe("Unite test for list customer use case", () => { 
   it("should list a customer", async () => {
      const repository = MockRepository();
      const useCase = new ListCustomerUseCase(repository);

      const output = await useCase.execute({});
      
      expect(output.customers.length).toBe(2);
      for (var position = 0; position < output.customers.length; position++) {
         expect(output.customers[position].id).toBe(customerList[position].id);
         expect(output.customers[position].name).toBe(customerList[position].name);
         expect(output.customers[position].address.city).toBe(customerList[position].Address.city);
         expect(output.customers[position].address.number).toBe(customerList[position].Address.number);
         expect(output.customers[position].address.street).toBe(customerList[position].Address.street);
         expect(output.customers[position].address.zip).toBe(customerList[position].Address.zip);
      }
   });
});

