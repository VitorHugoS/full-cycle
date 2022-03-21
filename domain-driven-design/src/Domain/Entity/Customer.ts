import Address from "./VO/Address";
import CustomerName from "./VO/CustomerName";

export default class Customer {
    private _id: string;
    private _name: CustomerName;
    private _address!: Address;
    private _activate: boolean = false;
    private _rewardPoints: number = 0;

    constructor(id: string, customerName: CustomerName) {
        this._id = id;
        this._name = customerName;
        this.validate();
    }
    validate(): void {
        if (this._id.length == 0) {
            throw new Error('Id is required');
        }
    }

    getId(): string {
        return this._id;
    }

    getCustomerName(): CustomerName { 
        return this._name;
    }

    getAddress(): Address { 
        return this._address;
    }

    getStatusCustomer(): boolean { 
        return this._activate;
    }
    
    changeName(customerName: CustomerName): void { 
        this._name = customerName;
    }

    activate(): void {
        if (this._address === undefined) {
            throw new Error('Address is mandatory to activate a customer');
        }
        this._activate = true;
    }

    deactivate(): void {
        this._activate = false;
    }

    setAddress(address: Address): void {
        this._address = address;
    }

    addRewardPoints(points: number): void { 
        this._rewardPoints += points;
    };

    getRewardPoints(): number { 
        return this._rewardPoints;
    }
}
