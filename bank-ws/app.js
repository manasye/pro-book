var express = require('express'),
    app = express(),
    bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use('/production', require('./controllers'));

app.listen(process.env.PORT, function() {
    console.log(`Listening on port ${process.env.PORT}...`);
});
