import Customer from "../Entity/Customer";
import Order from "../Entity/Order";
import OrderItem from "../Entity/OrderItem";
import { v4 as uuid } from "uuid";

export default class OrderService {

    static placeOrder(customer: Customer, items: OrderItem[]): Order {
        if (items.length === 0) {
            throw new Error('Order must have at least one item');
        }

        const order = new Order(uuid(), customer.getId(), items);
        customer.addRewardPoints(order.total() / 2);
        return order;
    }

    static getTotalOfOrders(orders: Order[]): number {
        return orders.reduce((acc, order) => acc + order.total(), 0);
    }
}