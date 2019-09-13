let path = require('path');
let express = require('express');
let app = express();

//let students = require('./data.js').student;
// students.forEach(element => {
//   console.log(element);
// });

// set the view engine to ejs
app.set('view engine', 'ejs');

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function (req, res) {
    let drinks = [ { name: 'Bloody Mary', drunkness:  3 },
                 { name: 'Martini',     drunkness:  5 },
                 { name: 'Scotch',      drunkness: 10 }
               ];
let students = [ {firstName: "אברהם", lastName: "לוי" , id: "1234"},
               {firstName: "יצחק",lastName: "שמון", id: "4567"},
             {firstName: "יהודה",lastName: "סלמון", id: "7896"},
             {firstName: "חיים",lastName: "ישראל", id: "2589"},
             {firstName: "שמעון",lastName: "מועלם", id: "7896"},
             {firstName: "תנחום",lastName: "רבינוביץ", id: "2541"},
             {firstName: "ישראל",lastName: "גבריאלוב", id: "2368"},
             {firstName: "נחמן",lastName: "מליק", id: "8732"},
             {firstName: "אריאל",lastName: "רבין", id: "2574"},
             {firstName: "אהרון",lastName: "גרין", id: "9713"}];
    let line = "Any your code that you haven't looked at for few months might have been written by someone else";
  res.render('index', { drinks: drinks, tagline: line, students: students });
  //res.render('index');
});

app.get('/about', function(req, res) {
  res.render('about');
});

app.listen(8080, function () {
  console.log('Listening on port 8080!');
});
