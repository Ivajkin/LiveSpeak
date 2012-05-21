//- Utilities -----------------------------+

function assert( /** Object */ expession, /** string */ message) {
	if(!expession) {
		throw message;
	}
}

//- Functions -----------------------------+
var User = {
	set: function(req, res) {
		users[req.params.name] = req.params.address;
		res.send('OK');
	}
	get: function(req, res) {

		var user = users[req.params.name];
		assert(user, 'There is no user "' + req.params.name + '"');
		res.send(
					JSON.stringify(
						user
					)
				);
	}
}

//- Variables -----------------------------+

// {'vasa' : '127.0.0.1',..}
var users = {};

//- Run server ----------------------------+

var express = require('express');
var app = express.createServer();

app.use(express.bodyParser());

app.get('/setUser/:name/:address', User.set);
app.get('/getUser/:name', User.get);

app.listen(3000);

console.log('Сервер запущен. Порт: 3000');