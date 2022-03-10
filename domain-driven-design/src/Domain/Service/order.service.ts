import Order from "../Entity/Order";

export default class OrderService {
    static getTotalOfOrders(orders: Order[]): number {
        return orders.reduce((acc, order) => acc + order.total(), 0);
    }
}