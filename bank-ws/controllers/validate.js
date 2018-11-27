var express = require("express"),
    router = express.Router(),
    Customer = require("../models/customer");

router.post("/", function(req, res) {
    Customer.getByCardNumber(req.body.cardNumber)
        .then(customer => {
            // console.log(customer.dataValues);
            res.json({
                success: true,
                message: "Card Number valid",
                valid: true,
                customer: {
                    id: customer.id,
                    name: customer.name,
                    cardNumber: customer.cardNumber,
                    balace: customer.balance
                }
            });
        })
        .catch(error => {
            console.log("[ERROR]: " + error.message);
            res.json({
                success: true,
                message: "Card Number invalid",
                valid: false
            });
        });
});

router.get("/", function(req, res) {
    Customer.getByCardNumber(req.query.cardNumber)
        .then(account => {
            // console.log(account.dataValues);
            res.json({
                success: true,
                message: "Card Number valid",
                valid: true,
                account: {
                    id: account.id,
                    name: account.name,
                    cardNumber: account.cardNumber,
                    balace: account.balance
                }
            });
        })
        .catch(error => {
            console.log("[ERROR]: " + error.message);
            res.json({
                success: true,
                message: "Card Number invalid",
                valid: false
            });
        });
});

module.exports = router;
