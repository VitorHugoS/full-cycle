export default class Product {
    
    private _id: string;
    private _name: string;
    private _price: number;

    constructor(id: string, name: string, price: number) {
        this._id = id;
        this._name = name;
        this._price = price;
        this.validate();
    }

    get id(): string {
        return this._id;
    }

    get price(): number {
        return this._price;
    }

    get name(): string {
        return this._name;
    }

    getPriceOfProduct(): number {
        return this._price;
    }

    changeName(newName: string): void {
        this._name = newName;
    }

    changePrice(newPrice: number): number {
        return this._price = newPrice;
    }

    validate(): void {
        if (this._id.length == 0) {
            throw new Error('Id is required');
        }
        if (this._name.length == 0) {
            throw new Error('Name is required');
        }
        if (this._price == 0) {
            throw new Error('Price needs to be greater than 0');
        }
    }
}