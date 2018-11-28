var express = require("express"),
    router = express.Router();

router.get("/", function(req, res) {
    return res.json({
                success: true,
                message: "API is on!"
            });
});

module.exports = router;
