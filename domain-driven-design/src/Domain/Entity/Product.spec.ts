import Product from "./Product";

describe("Product Entity Unit Tests", () => { 
    
    it("should throw error when id is empty", () => { 
        expect(() => {
            new Product("", "32", 150);
         }).toThrowError("Id is required");        
    });

    it("should throw error when name is empty", () => { 
        expect(() => {
            new Product("1", "", 45);
         }).toThrowError("Name is required");        
    });

    it("should throw error when price is 0", () => { 
        expect(() => {
            new Product("1", "32", 0);
         }).toThrowError("Price needs to be greater than 0");        
    });
});