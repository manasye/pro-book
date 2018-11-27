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
        type: Sequelize.BIGINT,
        allowNull: false
    },
    transactionTime: {
        type: Sequelize.DATE,
        allowNull: false
    }
});

Transaction.createTransaction = function(
    senderAccount,
    receiverAccount,
    amount,
    opts
) {
    return Transaction.upsert({
        senderAccount: senderAccount,
        receiverAccount: receiverAccount,
        amount: amount,
        transactionTime: new Date()
    }, opts);
};

module.exports = Transaction;
