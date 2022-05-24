import Product from "../entity/Product";
import ProductService from "./product.service";

describe("ProductService Unit Tests", () => { 
    
    describe("Should change prices of all products", () => {
        const PERCENT = 10;
        const listOfProducts = [
            new Product("1", "Product 1", 10),
            new Product("2", "Product 2", 30),
            new Product("3", "Product 3", 12),
        ];
        ProductService.increasePrice(listOfProducts, PERCENT);
        test.each([
            [listOfProducts[0], 11],
            [listOfProducts[1], 33],
            [listOfProducts[2], 13.2],
        ])
            ("Should increase price of product %p",
                (product, expected) => {
                    expect(product.getPriceOfProduct())
                        .toBe(expected);
                }
            );
    });
});