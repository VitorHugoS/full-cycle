import Customer from "./domain/entity/Customer";
import CustomerName from "./domain/entity/VO/CustomerName";
import Address from "./domain/entity/VO/Address";
import OrderItem from "./domain/entity/OrderItem";
import Order from "./domain/entity/Order";

const customerName = new CustomerName('John', 'Doe');
const address = new Address('Rua 12', 45, '38300104', 'Ituiutaba');
const customer = new Customer('1', customerName);
customer.setAddress(address);
customer.activate();

const item1 = new OrderItem('1', "item 1", 10);
const item2 = new OrderItem('2', "item 2", 35);

const order = new Order("1", "1", [item1, item2]);

console.log(order);