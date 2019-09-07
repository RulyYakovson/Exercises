let path = require("path");
let express = require('express');
let app = express();
const authenticatedMan = { firstName: 'plony', lastName: 'almony', password: '1234'};
app.use(express.static(path.join(__dirname, 'public')));

app.get('/authntication', function (req, res) {
    const firstName = req.query.first;
    const lastName = req.query.last;
    let approved = false;
    if (authenticate(req.query)) {
      approved = true;
      console.log(approved);
    }
    res.send(`Access ${!approved ? 'NOT' : ''} approved for: ${firstName} ${lastName}`);
});

app.listen(8081, function () {
  console.log('Ex1 qu5 app listening on port 8081!')
});

const authenticate = query =>
  query.first === authenticatedMan.firstName 
  && query.last === authenticatedMan.lastName 
  && query.password === authenticatedMan.password;
