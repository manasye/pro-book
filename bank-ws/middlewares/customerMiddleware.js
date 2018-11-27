Customer = require("../models/customer");

module.exports = function(req, res, next) {
    Customer.getByCardNumber(req.body.cardNumber)
        .then(customer => {
            req.body.customerAccount = customer;
            next();
        })
        .catch(error => {
            console.log("[ERROR]: " + error.message);
            res.json({
                success: true,
                message: "Account Number invalid"
            });
        });
};
