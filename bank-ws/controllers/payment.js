var express = require("express"),
    router = express.Router(),
    db = require("../models/db"),
    Customer = require("../models/customer"),
    Merchant = require("../models/merchant"),
    Transaction = require("../models/transaction"),
    customerMiddleware = require("../middlewares/customerMiddleware"),
    merchantMiddleware = require("../middlewares/merchantMiddleware");

router.post("/", [customerMiddleware, merchantMiddleware], function(req, res) {
    return db
        .transaction(function(t) {
            customerAccount = req.body.customerAccount;
            merchantOwnerAccount = req.body.merchantOwnerAccount;

            return customerAccount
                .charge(req.body.amount, { transaction: t })
                .then(_ => {
                    return merchantOwnerAccount.deposit(req.body.amount, {
                        transaction: t
                    });
                })
                .then(_ => {
                    return Transaction.createTransaction(
                        customerAccount.id,
                        merchantOwnerAccount.id,
                        req.body.amount,
                        { transaction: t }
                    );
                });
        })
        .then(result => {
            // Transaction succeeds
            return res.json({
                success: true,
                message: "Payment success"
            });
        })
        .catch(error => {
            // Transaction failed
            console.log("[ERROR]: " + error.message);
            if (error.message != "Insufficient funds")
                error.message = "Internal error";
            return res.json({
                success: false,
                message: error.message
            });
        });
});

module.exports = router;
