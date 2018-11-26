const Sequelize = require("sequelize");
const db = require("./db");
const Customers = require("./customer");

const Merchant = db.define("merchant", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    ownerAccount: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: Customers,
            key: "id"
        }
    },
    merchantName: {
        type: Sequelize.STRING,
        allowNull: false
    },
    secret: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true
    }
});

module.exports = Merchant;
