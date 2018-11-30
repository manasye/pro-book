Customer = require("../models/customer");

module.exports = function(req, res, next) {
    console.log(req.body);
    Customer.getByCardNumber(req.body.cardNumber)
        .then(customer => {
            if (customer == null) {
                req.body.customerAccount = customer;
                next();
            } else {
                throw new Error("Account number invalid");
            }
        })
        .catch(error => {
            console.log("[ERROR]: " + error.message);
            res.json({
                success: false,
                message: "Account Number invalid"
            });
        });
};
