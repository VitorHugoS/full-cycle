import { Model, Table, PrimaryKey, Column, ForeignKey, BelongsTo, HasMany } from "sequelize-typescript";
import CustomerModel from "./customer.model";
import OrderItemModel from "./item.model";

@Table({
    tableName: "order",
    timestamps: false,
})
export default class OrderModel extends Model {

    @PrimaryKey
    @Column
    declare id: string;

    @ForeignKey(() => CustomerModel)
    @Column({
        allowNull: false,
    })
    declare customer_id: number;

    @BelongsTo(() => CustomerModel)
    declare customer: CustomerModel;

    @HasMany(() => OrderItemModel)
    declare items: OrderItemModel[];

    @Column({
        allowNull: false,
    })
    declare total: number;
}