import Customer from "../Entity/Customer";
import Order from "../Entity/Order";
import OrderItem from "../Entity/OrderItem";
import Product from "../Entity/Product";
import CustomerName from "../Entity/VO/CustomerName";
import OrderService from "./order.service";

describe("OrderService Unit Tests", () => {

    it("should place an order", () => { 
        const listOfProducts = [
            new Product("1", "Product 1", 10),
            new Product("2", "Product 2", 10),
            new Product("3", "Product 3", 10),
        ];

        const listOfOrderItem = [
            new OrderItem(1, listOfProducts[0]),
            new OrderItem(2, listOfProducts[1]),
            new OrderItem(3, listOfProducts[2]),
        ];
        const customer = new Customer("1", new CustomerName("John", "Doe"));
        const order = OrderService.placeOrder(customer, listOfOrderItem);

        expect(order.total()).toBe(30);
        expect(customer.getRewardPoints()).toBe(15);
    });
    
    it("Should get total of all orders", () => {
        const listOfProducts = [
            new Product("1", "Product 1", 10),
            new Product("2", "Product 2", 10),
            new Product("3", "Product 3", 10),
        ];

        const listOfOrderItem = [
            new OrderItem(1, listOfProducts[0]),
            new OrderItem(2, listOfProducts[1]),
            new OrderItem(3, listOfProducts[2]),
        ];

        const listOfProductsSecondOrder = [
            new Product("1", "Product 1", 50),
            new Product("2", "Product 2", 45),
            new Product("3", "Product 3", 10),
        ];

        const listOfOrderItemSecondOrder = [
            new OrderItem(1, listOfProductsSecondOrder[0]),
            new OrderItem(2, listOfProductsSecondOrder[1]),
            new OrderItem(3, listOfProductsSecondOrder[2]),
        ];

        const listOfOrder = [
            new Order("1", "1", listOfOrderItem),
            new Order("2", "2", listOfOrderItemSecondOrder),
        ];

        expect(OrderService.getTotalOfOrders(listOfOrder)).toBe(135);
     });
});