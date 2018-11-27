Merchant = require("../models/merchant");

module.exports = function(req, res, next) {
    Merchant.getBySecret(req.body.merchantSecret)
        .then(merchant => {
            req.body.merchantAccountId = merchant.ownerAccount;
            next();
        })
        .catch(error => {
            console.log("[ERROR]: " + error.message);
            res.json({
                success: true,
                message: "Merchant Secret invalid"
            });
        });
};
