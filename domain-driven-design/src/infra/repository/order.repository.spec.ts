import { Sequelize } from 'sequelize-typescript';
import Customer from '../../domain/entity/Customer';
import CustomerName from '../../domain/entity/VO/CustomerName';
import Address from '../../domain/entity/VO/Address';
import CustomerModel from '../db/sequelize/model/customer.model';
import CustomerRepository from './customer.repository';
import OrderModel from '../db/sequelize/model/order.model';
import OrderItemModel from '../db/sequelize/model/item.model';
import ProductModel from '../db/sequelize/model/product.model';
import ProductRepository from './product.repository';
import Product from '../../domain/entity/Product';
import OrderItem from '../../domain/entity/OrderItem';
import Order from '../../domain/entity/Order';
import OrderRepository from './order.repository';

describe("Order Repository Unit Test", () => {
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

        sequelize.addModels([CustomerModel, OrderModel, OrderItemModel, ProductModel]);
        await sequelize.sync();
    });

    afterEach(async () => { 
        await sequelize.close();
    });

    it("should create a order", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const productRepository = new ProductRepository();
        const product = new Product("1", "produto 1", 10);
        productRepository.create(product);

        const orderItem = new OrderItem("1", product);
        const order = new Order("1", customer.getId(), [orderItem]);

        const orderRepository = new OrderRepository();
        await orderRepository.create(order);
        const orderModel = await OrderModel.findOne({
            where: {
                id: "1",
            },
            include: ["items"],
        });
        
        expect(orderModel.toJSON()).toStrictEqual({
            id: "1",
            customer_id: 1,
            total: order.total(),
            items: [
                {
                    id: orderItem.id,
                    name: orderItem.product.name,
                    price: orderItem.product.price.toString(),
                    order_id: 1,
                    product_id: 1,
                },
            ]
        });

    });

    it("should update a order", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const productRepository = new ProductRepository();
        const product = new Product("1", "produto 1", 10);
        productRepository.create(product);

        const orderItem = new OrderItem("1", product);
        const order = new Order("1", customer.getId(), [orderItem]);

        const orderRepository = new OrderRepository();
        await orderRepository.create(order);

        product.changeName("produto 2");
        product.changePrice(200);
        await orderRepository.update(order);
        const orderModel = await OrderModel.findOne({
            where: {
                id: "1",
            },
            include: ["items"],
        });
        expect(orderModel.toJSON()).toStrictEqual({
            id: "1",
            customer_id: 1,
            total: order.total(),
            items: [
                {
                    id: orderItem.id,
                    name: product.name,
                    price: product.price.toString(),
                    order_id: 1,
                    product_id: 1,
                },
            ]
        });

    });

    it("should find a order", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const productRepository = new ProductRepository();
        const product = new Product("1", "produto 1", 10);
        productRepository.create(product);

        const orderItem = new OrderItem("1", product);
        const order = new Order("1", customer.getId(), [orderItem]);

        const orderRepository = new OrderRepository();
        await orderRepository.create(order);
        const orderModel = await orderRepository.find("1");
        expect(order).toStrictEqual(orderModel);

    });

    it("should find all order", async () => {
        const customerRepository = new CustomerRepository();
        const customer = new Customer("1", customerName);
        customer.setAddress(customerAddress);
        await customerRepository.create(customer);

        const productRepository = new ProductRepository();
        const product = new Product("1", "produto 1", 10);
        productRepository.create(product);

        const orderItem = new OrderItem("1", product);
        const order = new Order("1", customer.getId(), [orderItem]);

        const orderRepository = new OrderRepository();
        await orderRepository.create(order);

        
        const customer2 = new Customer("2", customerName);
        customer2.setAddress(customerAddress);
        await customerRepository.create(customer2);

        const product2 = new Product("13", "produto 12", 150);
        productRepository.create(product2);

        const orderItem2 = new OrderItem("2", product2);
        const order2 = new Order("2", customer2.getId(), [orderItem2]);

        await orderRepository.create(order2);
        const orderModel = await orderRepository.findAll();
        expect([order, order2]).toStrictEqual(orderModel);

    });
});