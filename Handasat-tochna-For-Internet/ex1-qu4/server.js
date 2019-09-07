let express = require('express');
let path = require("path");
let app = express();
app.use(express.static(path.join(__dirname, 'public')));

app.listen(8081, function () {
    console.log('Ex1 Qu4 app listening on port 8081!')
  });