import Address from "../../../domain/customer/value-object/address";
import CustomerRepository from "../../../infrastructure/customer/repository/sequelize/customer.repository";
import { InputUpdateCustomerDto, OutputUpdateCustomerDto } from "./update.customer.dto";

export default class UpdateCustomerUseCase {
   constructor(private customerRepository: CustomerRepository) { }
   
   async execute(input: InputUpdateCustomerDto): Promise<OutputUpdateCustomerDto> {
      const customer = await this.customerRepository.find(input.id);
      customer.changeName(input.name);
      customer.changeAddress(new Address(input.address.street, input.address.number, input.address.zip, input.address.city));
      await this.customerRepository.update(customer);
      return {
         id: customer.id,
         name: customer.name,
         address: customer.Address,
      };
   }
}