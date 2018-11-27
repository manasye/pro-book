const Sequelize = require("sequelize");
const db = require("./db");
const Customers = require("./customer");

const Transaction = db.define("transaction", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    senderAccount: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: Customers,
            key: "id"
        }
    },
    receiverAccount: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: Customers,
            key: "id"
        }
    },
    amount: {
        type: Sequelize.INTEGER,
        allowNull: false
    },
    transactionTime: {
        type: Sequelize.DATE,
        allowNull: false
    }
});

module.exports = Transaction;
