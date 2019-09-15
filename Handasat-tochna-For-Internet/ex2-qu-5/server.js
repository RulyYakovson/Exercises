let path = require('path');
let express = require('express');
let app = express();

app.set('view engine', 'ejs');

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function (req, res) {
    
let students = [ 
  {name: "Haim Stein", university: "Machon Lev" , course: "Computer Siences"},
  {name: "Avi Stern", university: "Tel Aviv" , course: "Computer Siences"},
  {name: "Yossi Gorli", university: "Machon Lev" , course: "Computer Siences"},
  {name: "Ruli Yakov", university: "Tel Aviv" , course: "Grass Siences"},
  {name: "Noa Leshes", university: "Machon Lev" , course: "Some other"},
  {name: "Yael Nisel", university: "Haifa" , course: "Grass Siences"},
  {name: "Meir Marga", university: "Tel Aviv" , course: "Helth Siences"},
  {name: "Neria Yolo", university: "Machon Lev" , course: "Computer Siences"},
  {name: "Simcha Stein", university: "Haifa" , course: "Computer Siences"},
  {name: "Dani Zilber", university: "Tel Aviv" , course: "Some other"},
  {name: "Haim Stein", university: "Haifa" , course: "Helth Siences"},
]
  res.render('index', { students: students });
});

app.get('/about', function(req, res) {
  res.render('about');
});

app.listen(8080, function () {
  console.log('Listening on port 8080!');
});
