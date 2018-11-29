const speakeasy = require("speakeasy"),
    Customer = require("./customer"),
    Merchant = require("./merchant"),
    Transaction = require("./transaction");

Customer.sync({ force: true }).then(() => {
    Customer.bulkCreate([
        {
            name: "Abram Situmorang",
            cardNumber: "4111111111111111",
            balance: 100000000000000000,
            secret: speakeasy.generateSecret({ length: 19 }).base32
        },
        {
            name: "Ahmad Izzan",
            cardNumber: "4111111111111112",
            balance: 200000,
            secret: speakeasy.generateSecret({ length: 19 }).base32
        },
        {
            name: "Manasye Bukit",
            cardNumber: "4111111111111113",
            balance: 300000,
            secret: speakeasy.generateSecret({ length: 19 }).base32
        },
        {
            name: "Joko Widodo",
            cardNumber: "4111111111111114",
            balance: 500000000,
            secret: speakeasy.generateSecret({ length: 19 }).base32
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
