var express = require("express"),
    router = express.Router(),
    QRCode = require("qrcode"),
    customerMiddleware = require("../middlewares/customerMiddleware"),
    merchantMiddleware = require("../middlewares/merchantMiddleware");

router.post("/", [customerMiddleware, merchantMiddleware], function(req, res) {
    try {
        var otpauth_url =
            "otpauth://totp/SecretKey?secret=" + req.body.customerAccount.secret;
        QRCode.toDataURL(otpauth_url, function(err, image_data) {
            res.json({
                success: true,
                message: "Here's the QRCode",
                qrCode: image_data
            });
        });
    } catch(e) {
        res.json({
            success: false,
            message: "Fail to generate QRCode"
        })
    }
});

module.exports = router;
