import Customer from "../../../domain/customer/entity/customer";
import Address from "../../../domain/customer/value-object/address";
import FindCustomerUseCase from "./find.customer.useCase";

const customer = new Customer("123", "Customer 1");
const address = new Address("Street 1", 1, "Zip", "City");
customer.changeAddress(address);


const mockRepository = () => { 
  return {
    find: jest.fn().mockReturnValue(Promise.resolve(customer)),
    create: jest.fn().mockReturnValue(Promise.resolve()),
    update: jest.fn(),
    findAll: jest.fn(),
  };
};

describe("Test find customer unit usercase", () => { 
  it("should find a customer", async () => {
    const customerRepository = mockRepository();
    const useCase = new FindCustomerUseCase(customerRepository);
    
    await customerRepository.create(customer);

    const input = {
      id: "123",
    };
    const output = {
      id: "123",
      name: "Customer 1",
      address: {
        street: "Street 1",
        number: 1,
        city: "City",
        zip: "Zip",
      },
    };
    const result = await useCase.execute(input);

    expect(result).toEqual(output);
  });
  it("should not find a costumer", async () => {
    const customerRepository = mockRepository();
    customerRepository.find.mockImplementation(() => {
      throw new Error("Customer not found");
      
    });
    const useCase = new FindCustomerUseCase(customerRepository);
    const input = {
      id: "1234",
    };
    expect(() => useCase.execute(input)).rejects.toThrow("Customer not found");
  });
});