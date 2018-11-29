var speakeasy = require("speakeasy");

module.exports = function(req, res, next) {
    const userToken = req.body.token;
    const secret = req.body.customerAccount.secret;

    var verified = speakeasy.totp.verify({
        secret: secret,
        encoding: "base32",
        token: userToken
    });
    if (verified) {
        next();
    } else {
        res.json({
            success: false,
            message: "TOTP Code Invalid!"
        });
    }
};
