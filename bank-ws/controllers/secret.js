var express = require("express"),
    router = express.Router(),
    QRCode = require("qrcode"),
    customerMiddleware = require("../middlewares/customerMiddleware"),
    merchantMiddleware = require("../middlewares/merchantMiddleware");

router.post("/", [customerMiddleware, merchantMiddleware], function(req, res) {
    var otpauth_url =
        "otpauth://totp/SecretKey?secret=" + req.body.customerAccount.secret;
    QRCode.toDataURL(otpauth_url, function(err, image_data) {
        image_data = image_data.split(",")[1];
        var img = new Buffer(image_data, "base64");
        res.writeHead(200, {
            "Content-Type": "image/png",
            "Content-Length": img.length
        });
        res.end(img);
    });
});

module.exports = router;
