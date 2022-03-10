import Customer from "./Domain/Entity/Customer";
import CustomerName from "./Domain/Entity/VO/CustomerName";
import Address from "./Domain/Entity/VO/Address";
import OrderItem from "./Domain/Entity/OrderItem";
import Order from "./Domain/Entity/Order";

const customerName = new CustomerName('John', 'Doe');
const address = new Address('Rua 12', 45, '38300104', 'Ituiutaba');
const customer = new Customer('1', customerName);
customer.setAddress(address);
customer.active();

const item1 = new OrderItem('1', "item 1", 10);
const item2 = new OrderItem('2', "item 2", 35);

const order = new Order("1", "1", [item1, item2]);

console.log(order);