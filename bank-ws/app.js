var express = require("express"),
    app = express(),
    bodyParser = require("body-parser");

app.use(bodyParser.json());
app.use("/production", require("./controllers"));

app.listen(3000, function() {
    console.log("Listening on port 3000...");
});
