import EventHandlerInterface from "../../@shared/event-handler.interface";
import ProductCreatedEvent from "../product-created.event";

export default class LogProductIsCreated implements EventHandlerInterface<ProductCreatedEvent> {
    handler(event: ProductCreatedEvent): void {
        console.log(`Product is created ${event.eventData.name}`);
    }
}