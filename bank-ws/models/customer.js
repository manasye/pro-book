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
    balance: {
        type: Sequelize.BIGINT,
        allowNull: false,
        defaultValue: 0
    },
    secret: {
        type: Sequelize.STRING,
        allowNull: false
    }
});

Customer.getByCardNumber = function(cardNumber) {
    return Customer.findOne({
        where: {
            cardNumber: cardNumber
        }
    });
};

Customer.prototype.charge = function(amount, opts) {
    if (this.balance >= amount) {
        return Customer.update(
            {
                balance: parseInt(this.balance) - parseInt(amount)
            },
            Object.assign(opts, { where: { id: this.id } })
        );
    } else {
        throw new Error("Insufficient funds");
    }
};

Customer.prototype.deposit = function(amount, opts) {
    return Customer.update(
        {
            balance: parseInt(this.balance) + parseInt(amount)
        },
        Object.assign(opts, { where: { id: this.id } })
    );
};

module.exports = Customer;
