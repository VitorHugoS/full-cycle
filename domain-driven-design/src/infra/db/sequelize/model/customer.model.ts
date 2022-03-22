import { Model, Table, PrimaryKey, Column } from "sequelize-typescript";

@Table({
    tableName: "customer",
    timestamps: false,
})
export default class CustomerModel extends Model {

    @PrimaryKey
    @Column
    declare id: string;

    @Column({
        allowNull: false,
    })
    declare firstName: string;

    @Column({
        allowNull: false,
    })
    declare lastName: string;

    @Column({
        allowNull: false,
    })
    declare street: string;

    @Column({
        allowNull: false,
    })
    declare number: number;

    @Column({
        allowNull: false,
    })
    declare zip: string;

    @Column({
        allowNull: false,
    })
    declare city: string;

    @Column({
        allowNull: false,
    })
    declare activate: boolean;

    @Column({
        allowNull: false,
    })
    declare rewardPoints: number;
}