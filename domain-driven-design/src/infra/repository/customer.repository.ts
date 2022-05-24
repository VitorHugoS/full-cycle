import Customer from "../../domain/entity/Customer";
import Address from "../../domain/entity/VO/Address";
import CustomerName from "../../domain/entity/VO/CustomerName";
import CustomerRepositoryInterface from "../../domain/repository/customer-repository.interface";
import CustomerModel from "../db/sequelize/model/customer.model";

export default class CustomerRepository implements CustomerRepositoryInterface {
    async create(entity: Customer): Promise<void> {
        await CustomerModel.create({
            id: entity.getId(),
            firstName: entity.getCustomerName()._firstName,
            lastName: entity.getCustomerName()._lastName,
            street: entity.getAddress()._street,
            number: entity.getAddress()._number,
            zip: entity.getAddress()._zip,
            city: entity.getAddress()._city,
            activate: entity.getStatusCustomer(),
            rewardPoints: entity.getRewardPoints(),
        });
    }

    async update(entity: Customer): Promise<void> {
        await CustomerModel.update(
            {
                id: entity.getId(),
                firstName: entity.getCustomerName()._firstName,
                lastName: entity.getCustomerName()._lastName,
                street: entity.getAddress()._street,
                number: entity.getAddress()._number,
                zip: entity.getAddress()._zip,
                city: entity.getAddress()._city,
                activate: entity.getStatusCustomer(),
                rewardPoints: entity.getRewardPoints(),
            },
            {
                where: {
                    id: entity.getId(),
                },
            }
        );
    }

    async find(id: string): Promise<Customer> {
        let customer;
        try {
            customer = await CustomerModel.findOne(
                {
                    where: {
                        id: id,
                    },
                    rejectOnEmpty: true,
                },
            );
        } catch (error) {
            throw new Error("Customer not found");
        }
        const customerAddress = new Address(customer.street, customer.number, customer.zip, customer.city);
        const customerName = new CustomerName(customer.firstName, customer.lastName);
        const customerData = new Customer(customer.id, customerName);
        customerData.setAddress(customerAddress);
        customerData.addRewardPoints(customer.rewardPoints);
        return customerData;
    }
    
    async findAll(): Promise<Customer[]> {
        const customers = await CustomerModel.findAll();
        return customers.map((customer) => {
            const customerAddress = new Address(customer.street, customer.number, customer.zip, customer.city);
            const customerName = new CustomerName(customer.firstName, customer.lastName);
            const customerData = new Customer(customer.id, customerName);
            customerData.setAddress(customerAddress);
            customerData.addRewardPoints(customer.rewardPoints);
            return customerData;
        });
    }

 }