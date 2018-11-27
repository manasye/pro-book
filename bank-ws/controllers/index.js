var express = require("express"),
    router = express.Router();
    // Comment = require("../models/comment");

router.use("/validate", require("./validate"));
router.use("/transfer", require("./transfer"));
// router.use("/users", require("./users"));

// router.get("/", function(req, res) {
//     Comments.all(function(err, comments) {
//         res.render("index", { comments: comments });
//     });
// });

module.exports = router;
