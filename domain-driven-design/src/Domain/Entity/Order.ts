import OrderItem from "./OrderItem";

export default class Order {
    private _id: string;
    private _customerId: string;
    private _items: Array<OrderItem>;
    private _total: number;
    
    constructor(id: string, customerId: string, items: Array<OrderItem>) {
        this._id = id;
        this._customerId = customerId;
        this._items = items;
        this._total = this.total();
        this.validate();
    }

    validate(): void {
        if (this._id.length == 0) {
            throw new Error('Id is required');
        }
        if (this._customerId.length == 0) {
            throw new Error('CustomerId is required');
        }
        if (this._items.length == 0) {
            throw new Error('Items is required');
        }
    }

    total(): number { 
        return this._items.reduce((acc, product) => acc + product.getPriceOfProduct(), 0);
    }
}