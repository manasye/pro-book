Merchant = require('../models/merchant');
Customer = require('../models/customer');

module.exports = function(req, res, next) {
    Merchant.getBySecret(req.body.merchantSecret)
        .then(merchant => {
            return Customer.findOne({
                where: {
                    id: merchant.ownerAccount
                }
            });
        })
        .then(merchantOwnerAccount => {
            req.body.merchantOwnerAccount = merchantOwnerAccount;
            next();
        })
        .catch(error => {
            console.log('[ERROR]: ' + error.message);
            res.json({
                success: true,
                message: 'Merchant Secret invalid'
            });
        });
};
