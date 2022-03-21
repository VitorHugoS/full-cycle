import Order from "../Entity/Order";
import RepositoryInterface from "./repository-interface";

export default interface CustomerRepositoryInterface extends RepositoryInterface<Order> {
    
}