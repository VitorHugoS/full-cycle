import Product from "../entity/Product";

export default class ProductService {
    
    static increasePrice(listOfProducts: Array<Product>, percent: number): void {
        listOfProducts.forEach(product => {
            product.changePrice(
                (product.getPriceOfProduct() * percent) / 100
                + product.getPriceOfProduct()
            );
        });
    }

}
