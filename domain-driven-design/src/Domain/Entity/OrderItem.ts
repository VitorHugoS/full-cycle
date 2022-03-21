import Product from "./Product";

export default class OrderItem {
    
    private _id: string;
    private _product: Product;

    constructor(id: string, product: Product) {
        this._id = id;
        this._product = product;
        this.validate();
    }

    getPriceOfProduct() { 
        return this._product.getPriceOfProduct();
    }

    get product(): Product {
        return this._product;
    }

    get id(): string {
        return this._id;
    }
    
    validate(): void {
        if (this._id.length === 0) {
            throw new Error('Id is required');
        }
        if (!this._product) {
            throw new Error('Product is required');
        }
    }
}