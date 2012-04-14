function assert( /** Object */ expession, /** string */ message) {
	if(!expession) {
		throw message;
	}
}

// {'vasa' : '127.0.0.1',..}
var users = {};

var app = require('express').createServer();

app.get('/user?name=:name', function(req, res){

	var user = users[req.params.name];
	assert(user, 'There is no user "' + req.params.name + '"');
	res.send(
				JSON.stringify(
					user
				)
			);
});

app.listen(3000);

console.log('Сервер запущен. Порт: 3000');