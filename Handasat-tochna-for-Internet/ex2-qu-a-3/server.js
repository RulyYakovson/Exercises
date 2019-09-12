let express = require('express');
let path = require("path");
let app = express();
app.use(express.static(path.join(__dirname, 'public')));

app.get('/flower/order', function (req, res) {
  const type = req.query.type;
  const amount = req.query.amount;
  const address = req.query.address;
  const tel = req.query.tel;
  const packageColor = req.query.packageColor;
  
  res.send(`ORDER DETAILS:    Flower type: ${type},   Amount: ${amount},   Address: ${address},   Tel: ${tel},   Package color: ${packageColor}.`);
});

app.listen(8080, function () {
    console.log('Ex2 Qu A3 app listening on port 8080!')
  });