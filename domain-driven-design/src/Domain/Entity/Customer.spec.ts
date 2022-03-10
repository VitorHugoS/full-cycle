import Customer from "./Customer";
import Address from "./VO/Address";
import CustomerName from "./VO/CustomerName";

const customerNameMock = new CustomerName('John', 'Doe');

describe("Customer Entity Unit Tests", () => { 
    
    it("should throw error when id is empty", () => { 
        expect(() => {
            new Customer("", customerNameMock);
         }).toThrowError("Id is required");        
    });

    it("should changeName", () => {
        const customer = new Customer("1", customerNameMock);
        const customerNewName = new CustomerName('Vitor', 'Hugo');
        customer.changeName(customerNewName);
        expect(customer.getCustomerName()).toBe(customerNewName);        
    });

    it("should getCustomerName", () => {
        const customer = new Customer("1", customerNameMock);
        expect(customer.getCustomerName()).toBe(customerNameMock);        
    });

    describe("should test method activate", () => { 
        const customer = new Customer("1", customerNameMock);
        it("should throw error when address is empty", () => { 
            expect(() => { 
                customer.activate();
            }).toThrowError("Address is mandatory to activate a customer");
        });

        it("should activate customer when has address", () => { 
            const customerAddress = new Address("Rua", 1, "12345678", "Cidade");
            customer.setAddress(customerAddress);
            customer.activate();
            expect(customer.getStatusCustomer()).toBe(true);
        });

        it("should deactivate customer", () => { 
            customer.deactivate();
            expect(customer.getStatusCustomer()).toBe(false);
        });
    });

});