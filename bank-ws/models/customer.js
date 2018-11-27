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
        type: Sequelize.FLOAT(14, 2),
        allowNull: false,
        defaultValue: 0
    }
});

Customer.getByCardNumber = function(cardNumber) {
    return Customer.findOne({
        where: {
            cardNumber: cardNumber
        }
    });
}

// Customer.prototype.charge = function(amount) {
//     return Customer.add
// }

module.exports = Customer;
