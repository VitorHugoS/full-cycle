import { Model, Table, PrimaryKey, Column, ForeignKey, BelongsTo } from "sequelize-typescript";
import OrderModel from "./order.model";
import ProductModel from "./product.model";

@Table({
    tableName: "order_item",
    timestamps: false,
})
export default class OrderItemModel extends Model {

    @PrimaryKey
    @Column
    declare id: string;

    @ForeignKey(() => ProductModel)
    @Column({
        allowNull: false,
    })
    declare product_id: number;

    @BelongsTo(() => ProductModel)
    declare product: ProductModel;

    @ForeignKey(() => OrderModel)
    @Column({
        allowNull: false,
    })
    declare order_id: number;

    @BelongsTo(() => OrderModel)
    declare order: OrderModel;

    @Column({
        allowNull: false,
    })
    declare name: string;

    @Column({
        allowNull: false,
    })
    declare price: string;

}