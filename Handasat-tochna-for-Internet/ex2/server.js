let path = require('path');
let express = require('./node_modules/express');
let app = express();

// set the view engine to ejs
app.set('view engine', 'ejs');

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function (req, res) {
    let drinks = [ { name: 'Bloody Mary', drunkness:  3 },
                 { name: 'Martini',     drunkness:  5 },
                 { name: 'Scotch',      drunkness: 10 }
               ];
    let line = "Any your code that you haven't looked at for few months might have been written by someone else";
  res.render('index', { drinks: drinks, tagline: line });
  //res.render('index');
});

app.get('/about', function(req, res) {
  res.render('about');
});

app.listen(8080, function () {
  console.log('Listening on port 8080!');
});
