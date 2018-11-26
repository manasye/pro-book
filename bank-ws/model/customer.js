const Sequelize = require("sequelize");
const db = require("./db");

const Customer = db.define("customer", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: Sequelize.STRING,
        allowNull: false
    },
    cardNumber: {
        type: Sequelize.STRING,
        allowNull: false
    },
    amount: {
        type: Sequelize.FLOAT(14, 2),
        allowNull: false,
        defaultValue: 0
    }
});

module.exports = Customer;
