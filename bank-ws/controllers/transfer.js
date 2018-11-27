var express = require("express"),
    router = express.Router(),
    db = require("../models/db"),
    Customer = require("../models/customer"),
    Merchant = require("../models/merchant"),
    Transaction = require("../models/transaction"),
    auth = require("../middlewares/auth");

router.post("/", auth, function(req, res) {
    db.transaction(function(t) {
        Customer.getByCardNumber(req.body.userCardNumber).then(
            customer => {
                console.log("Customer id = " + customer.id);
                console.log("Merchant Account id = " + req.body.merchantAccountId)
            },
            { transaction: t }
        )
    })
    .then(
        result => {
            // Transaction succeeds
            res.json({
                huyu: "bankai"
            })
        }
    )
    .catch(
        error => {
            // Transaction failed
            res.json({
                huyu: "bankaasfasdfasdfai"
            })
        }
    );
});

module.exports = router;
