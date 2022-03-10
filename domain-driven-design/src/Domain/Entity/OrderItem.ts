import Product from "./Product";

export default class OrderItem {
    
    private _id: number;
    private _product: Product;

    constructor(id: number, product: Product) {
        this._id = id;
        this._product = product;
        this.validate();
    }

    getPriceOfProduct() { 
        return this._product.getPriceOfProduct();
    }
    
    validate(): void {
        if (this._id == 0) {
            throw new Error('Id is required');
        }
        if (!this._product) {
            throw new Error('Product is required');
        }
    }
}