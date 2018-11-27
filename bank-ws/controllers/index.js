var express = require("express"),
    router = express.Router();

router.use("/validate", require("./validate"));
router.use("/payment", require("./payment"));
router.use("/secret", require("./secret"));

module.exports = router;
