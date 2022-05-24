import Order from "./Order";
import OrderItem from "./OrderItem";
import Product from "./Product";

const productMock = [
    new Product("Product 1", "32", 150),
    new Product("Product 2", "32", 150),
];

const totalProductMock = productMock.reduce((acc, product) => acc + product.getPriceOfProduct(), 0);
const orderItemMock = [
    new OrderItem("1", productMock[0]),
    new OrderItem("2", productMock[1]),
];

describe("Order Entity Unit Tests", () => { 
    
    it("should throw error when id is empty", () => { 
        expect(() => {
            new Order("", "32", []);
         }).toThrowError("Id is required");        
    });

    it("should throw error when customerId is empty", () => { 
        expect(() => {
            new Order("1", "", []);
         }).toThrowError("CustomerId is required");        
    });

    it("should throw error when items is empty", () => { 
        expect(() => {
            new Order("1", "32", []);
         }).toThrowError("Items is required");        
    });

    it("should calculateTotal", () => { 
        const order = new Order("1", "32", orderItemMock); 
        expect(order.total()).toBe(totalProductMock);        
    });
});