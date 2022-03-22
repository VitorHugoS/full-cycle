import EventDispatcher from "../event/@shared/event-dispatcher";
import Address from "./VO/Address";
import CustomerName from "./VO/CustomerName";
import SendLogWhenCustomerIsCreated from "../event/customer/handler/send-log-when-customer-is-created.handler";
import SendLogWhenCustomerIsCreated2 from "../event/customer/handler/send-log-when-customer-is-created-2.handler";
import SendLogWhenAddressFromCustomerIsChanged from "../event/customer/handler/send-log-when-address-from-customer-is-changed.handler";
import CustomerCreatedEvent from "../event/customer/customer-created.event";
import CustomerChangedAddressEvent from "../event/customer/customer-changed-address.event";

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

        const eventDispatcher = new EventDispatcher();
        const eventHandler = new SendLogWhenCustomerIsCreated();
        const eventHandler2 = new SendLogWhenCustomerIsCreated2();
        eventDispatcher.register("CustomerCreatedEvent", eventHandler);
        eventDispatcher.register("CustomerCreatedEvent", eventHandler2);

        const customerCreatedEvent = new CustomerCreatedEvent({});
        eventDispatcher.notify(customerCreatedEvent);
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
        const eventDispatcher = new EventDispatcher();
        const eventHandler = new SendLogWhenAddressFromCustomerIsChanged();
        eventDispatcher.register("CustomerChangedAddressEvent", eventHandler);
        const customerChangedAddress = new CustomerChangedAddressEvent({
            id: this._id,
            name: this._name._firstName,
            address: this._address,
        });
        eventDispatcher.notify(customerChangedAddress);
    }

    addRewardPoints(points: number): void { 
        this._rewardPoints += points;
    };

    getRewardPoints(): number { 
        return this._rewardPoints;
    }
}
