export default class CustomerName { 
    _firstName: string = "";
    _lastName: string = "";

    constructor(firstName: string, lastName: string) {
        this._firstName = firstName;
        this._lastName = lastName;
        this.validate();
    }

    validate() {
        if (this._firstName.length == 0) {
            throw new Error('First name is required');
        }
        if (this._lastName.length == 0) {
            throw new Error('Last name is required');
        }
    }

    toString(): string {
        return `${this._firstName} ${this._lastName}`;
    }

    getFullName(): string {
        return this.toString();
    }

    getShortName(): string {
        return this._firstName;
    }
}