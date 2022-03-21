import Order from "../../Domain/Entity/Order";
import OrderItem from "../../Domain/Entity/OrderItem";
import Product from "../../Domain/Entity/Product";
import OrderRepositoryInterface from "../../Domain/Repository/order-repository.interface";
import OrderItemModel from "../db/sequelize/model/item.model";
import OrderModel from "../db/sequelize/model/order.model";

export default class OrderRepository implements OrderRepositoryInterface {
    async create(entity: Order): Promise<void> {
        await OrderModel.create({
            id: entity.id,
            customer_id: entity.customerId,
            total: entity.total(),
            items: entity.items.map((item) => ({ 
                id: item.id,
                product_id: item.product.id,
                name: item.product.name,
                price: item.product.price,
            })),
        },
        {
            include: [{ model: OrderItemModel }],
        }
        );
    }

    async update(entity: Order): Promise<void> {
        await OrderModel.findOne({
            where: {
                id: entity.id,
            },
            include: ["items"],
        }).then((order) => {
            order.id = entity.id;
            order.customer_id = Number(entity.customerId);
            order.total = entity.total();
            order.items.forEach((item) => {
                const foundOrderItem = entity.items.filter((orderItem) => orderItem.id === item.id);
                if (foundOrderItem !== undefined) {
                    item.product_id = Number(foundOrderItem[0].product.id);
                    item.name = foundOrderItem[0].product.name;
                    item.price = String(foundOrderItem[0].product.price);
                    item.save();
                }
            });
            order.save();
        });
    }

    async find(id: string): Promise<Order> {
        let order;
        try {
            order = await OrderModel.findOne(
                {
                    where: {
                        id: id,
                    },
                    include: ["items"],
                    rejectOnEmpty: true,
                },
            );
        } catch (error) {
            throw new Error("Order not found");
        }
        const orderItems = order.items.map((item) => {
            const product = new Product(String(item.product_id), item.name, Number(item.price));
            return new OrderItem(item.id, product);
        });
        const orderData = new Order(order.id, String(order.customer_id), orderItems);
        return orderData;
    }
    
    async findAll(): Promise<Array<Order>> {
        const orders = await OrderModel.findAll({ include: ["items"] });
        return orders.map((order) => {
            const orderItems: OrderItem[] = [];
            order.items.forEach((item) => {
                const product = new Product(String(item.product_id), item.name, Number(item.price));
                orderItems.push(new OrderItem(item.id, product));
            });
            const orderData = new Order(order.id, String(order.customer_id), orderItems);
            return orderData;
        });
    }
 }