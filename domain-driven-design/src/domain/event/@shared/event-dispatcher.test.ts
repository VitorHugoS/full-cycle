import SendEmailWhenProductIsCreatedHandler from "../product/handler/send-email-when-product-is-created.handler";
import LogProductIsCreated from "../product/handler/log-product-is-created.handler";

import ProductCreatedEvent from "../product/product-created.event";
import EventDispatcher from "./event-dispatcher";
import SendLogWhenCustomerIsCreated from "../customer/handler/send-log-when-customer-is-created.handler";
import SendLogWhenCustomerIsCreated2 from "../customer/handler/send-log-when-customer-is-created-2.handler";
import SendLogWhenAddressFromCustomerIsChanged from "../customer/handler/send-log-when-address-from-customer-is-changed.handler";
import CustomerCreatedEvent from "../customer/customer-created.event";
import CustomerChangedAddressEvent from "../customer/customer-changed-address.event";

describe("Domain events tests", () => {
    describe("Product event tests", () => { 
        it("should register an event handler", () => { 
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendEmailWhenProductIsCreatedHandler();

            eventDispatcher.register("ProductCreatedEvent", eventHandler);

            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]).
                toMatchObject(eventHandler);
        });


        it("should unregister an event handler", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendEmailWhenProductIsCreatedHandler();

            eventDispatcher.register("ProductCreatedEvent", eventHandler);

            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]).
                toMatchObject(eventHandler);

            eventDispatcher.unregister("ProductCreatedEvent", eventHandler);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).
                toBe(0);

        });

        it("should unregister all events handlers", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendEmailWhenProductIsCreatedHandler();

            eventDispatcher.register("ProductCreatedEvent", eventHandler);

            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]).
                toMatchObject(eventHandler);

            eventDispatcher.unregisterAll();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeUndefined();

        });

        it("should notify all event handlers", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendEmailWhenProductIsCreatedHandler();
            const eventHandler2 = new LogProductIsCreated();

            const spyEventHandler = jest.spyOn(eventHandler, "handler");
            const spyEventHandler2 = jest.spyOn(eventHandler2, "handler");

            eventDispatcher.register("ProductCreatedEvent", eventHandler);
            eventDispatcher.register("ProductCreatedEvent", eventHandler2);

            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"].length).
                toBe(2);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"][0]).
                toMatchObject(eventHandler);
            expect(eventDispatcher.getEventHandlers["ProductCreatedEvent"][1]).
                toMatchObject(eventHandler2);

            const productCreatedEvent = new ProductCreatedEvent({
                name: 'Product 1',
                description: 'Product 1 description',
                price: 10,
            });

            eventDispatcher.notify(productCreatedEvent);
            expect(spyEventHandler).toHaveBeenCalled();
            expect(spyEventHandler2).toHaveBeenCalled();
        });
    });

    describe("Customer ChangedAddress event tests", () => { 
        const eventDispatcher = new EventDispatcher();
        const eventHandler = new SendLogWhenAddressFromCustomerIsChanged();
        eventDispatcher.register("CustomerChangedAddressEvent", eventHandler);

        it("should register an event handler", () => { 
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"][0]).
                toMatchObject(eventHandler);
        });


        it("should unregister an event handler", () => {
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"][0]).
                toMatchObject(eventHandler);

            eventDispatcher.unregister("CustomerChangedAddressEvent", eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"].length).
                toBe(0);

        });

        it("should unregister all events handlers", () => {
            eventDispatcher.register("CustomerChangedAddressEvent", eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"][0]).
                toMatchObject(eventHandler);

            eventDispatcher.unregisterAll();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeUndefined();

        });

        it("should notify all event handlers", () => {
            eventDispatcher.register("CustomerChangedAddressEvent", eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["CustomerChangedAddressEvent"][0]).
                toMatchObject(eventHandler);
            const spyEventHandler = jest.spyOn(eventHandler, "handler");

            const customerCreatedEvent = new CustomerChangedAddressEvent({});

            eventDispatcher.notify(customerCreatedEvent);
            expect(spyEventHandler).toHaveBeenCalled();
        });
    });

    describe("Customer Created event tests", () => { 
        it("should register an event handler", () => { 
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendLogWhenCustomerIsCreated();
            const eventHandler2 = new SendLogWhenCustomerIsCreated2();

            eventDispatcher.register("CustomerCreatedEvent", eventHandler);
            eventDispatcher.register("CustomerCreatedEvent", eventHandler2);

            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).
                toBe(2);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]).
                toMatchObject(eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]).
                toMatchObject(eventHandler2);
        });


        it("should unregister an event handler", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendLogWhenCustomerIsCreated();
            const eventHandler2 = new SendLogWhenCustomerIsCreated2();

            eventDispatcher.register("CustomerCreatedEvent", eventHandler);
            eventDispatcher.register("CustomerCreatedEvent", eventHandler2);

            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).
                toBe(2);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]).
                toMatchObject(eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]).
                toMatchObject(eventHandler2);

            eventDispatcher.unregister("CustomerCreatedEvent", eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).
                toBe(1);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]).
                toMatchObject(eventHandler2);

        });

        it("should unregister all events handlers", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendLogWhenCustomerIsCreated();
            const eventHandler2 = new SendLogWhenCustomerIsCreated2();

            eventDispatcher.register("CustomerCreatedEvent", eventHandler);
            eventDispatcher.register("CustomerCreatedEvent", eventHandler2);

            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).
                toBe(2);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]).
                toMatchObject(eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]).
                toMatchObject(eventHandler2);

            eventDispatcher.unregisterAll();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeUndefined();

        });

        it("should notify all event handlers", () => {
            const eventDispatcher = new EventDispatcher();
            const eventHandler = new SendLogWhenCustomerIsCreated();
            const eventHandler2 = new SendLogWhenCustomerIsCreated2();

            const spyEventHandler = jest.spyOn(eventHandler, "handler");
            const spyEventHandler2 = jest.spyOn(eventHandler2, "handler");

            eventDispatcher.register("CustomerCreatedEvent", eventHandler);
            eventDispatcher.register("CustomerCreatedEvent", eventHandler2);

            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"]).
                toBeDefined();
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"].length).
                toBe(2);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][0]).
                toMatchObject(eventHandler);
            expect(eventDispatcher.getEventHandlers["CustomerCreatedEvent"][1]).
                toMatchObject(eventHandler2);

            const customerCreatedEvent = new CustomerCreatedEvent({});

            eventDispatcher.notify(customerCreatedEvent);
            expect(spyEventHandler).toHaveBeenCalled();
            expect(spyEventHandler2).toHaveBeenCalled();
        });
    });
    
});