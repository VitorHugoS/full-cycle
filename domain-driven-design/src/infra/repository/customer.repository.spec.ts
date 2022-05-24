import { Sequelize } from 'sequelize-typescript';
import Customer from '../../domain/entity/Customer';
import CustomerName from '../../domain/entity/VO/CustomerName';
import Address from '../../domain/entity/VO/Address';
import CustomerModel from '../db/sequelize/model/customer.model';
import CustomerRepository from './customer.repository';

describe("Customer Repository Unit Test", () => {
    let sequelize: Sequelize;
    const customerName = new CustomerName('John', 'Doe');
    const customerAddress = new Address('Street', 123, 'Zip', 'City');
    beforeEach(async () => {
        sequelize = new Sequelize({
            dialect: "sqlite",
            logging: false,
            sync: { force: true },
            storage: ":memory:",
        });

        sequelize.addModels([CustomerModel]);
        await sequelize.sync();
    });

    afterEach(async () => { 
        await sequelize.close();
    });

    it("should create a customer", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const customerModel = await CustomerModel.findOne({ where: { id: "1" } });

        expect(customerModel.toJSON()).toStrictEqual({
            id: "1",
            firstName: "John",
            lastName: "Doe",
            street: 'Street',
            number: 123,
            zip: 'Zip',
            city: 'City',
            activate: false,
            rewardPoints: 0,
        });

    });

    it("should update a customer", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        customer.changeName(new CustomerName('Vitor', 'Hugo'));
        await customerRepository.update(customer);

        const customerModel = await CustomerModel.findOne({ where: { id: "1" } });

        expect(customerModel.toJSON()).toStrictEqual({
            id: "1",
            firstName: "Vitor",
            lastName: "Hugo",
            street: 'Street',
            number: 123,
            zip: 'Zip',
            city: 'City',
            activate: false,
            rewardPoints: 0,
        });

    });

    it("should find a customer", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const customerResult = await customerRepository.find("1");

        expect(customer).toStrictEqual(customerResult);

    });

    it("should throw an error when customer is not found", async () => {
        const customerRepository = new CustomerRepository();
        expect(async () => {
            await customerRepository.find("1");
        }).rejects.toThrow(new Error("Customer not found"));
    });

});