import EventHandlerInterface from "../../@shared/event-handler.interface";
import CustomerChangedAddressEvent from "../customer-changed-address.event";

export default class SendLogWhenAddressFromCustomerIsChanged implements EventHandlerInterface<CustomerChangedAddressEvent> {
    handler(event: CustomerChangedAddressEvent): void {
        const addressJSON = event.eventData.address;
        console.log(`Endereço do cliente: ${event.eventData.id}, ${event.eventData.name} 
            alterado para: ${addressJSON}`);
    }
}