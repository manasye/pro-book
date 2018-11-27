const Sequelize = require("sequelize");
// const db = require('./db')
const Customer = require("./customer");
const Merchant = require("./merchant");
const Transaction = require("./transaction");

Customer.sync({ force: true }).then(() => {
    Customer.bulkCreate([
        {
            name: "Abram Situmorang",
            cardNumber: "4111111111111111",
            balance: 100000
        },
        { name: "Ahmad Izzan", cardNumber: "4111111111111112", balance: 200000 },
        {
            name: "Manasye Bukit",
            cardNumber: "4111111111111113",
            balance: 300000
        },
        {
            name: "Joko Widodo",
            cardNumber: "4111111111111114",
            balance: 500000000
        }
    ]);
});

Merchant.sync({ force: true }).then(() => {
    Merchant.create({
        ownerAccount: 4,
        merchantName: "Pro-Book",
        secret: "JokowiMaruf2019"
    });
});

Transaction.sync({ force: true }).then(() => {
    Transaction.create({
        senderAccount: 1,
        receiverAccount: 4,
        amount: 1,
        transactionTime: new Date()
    });
});
