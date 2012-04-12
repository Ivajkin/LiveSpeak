var net = require('net');

var server = net.createServer(function (socket) {
	socket.write('Echo server\r\n');
	socket.pipe(socket);
});

server.listen(1337, '127.0.0.1');

console.log('Сервер запущен. Порт: 1337');

/*
var http = require('http');

http.createServer(function (req, res) {

	res.writeHead(200, {'Content-Type': 'text/plain'});
	res.end('Hello World\n');
	
}).listen(1337, '127.0.0.1');

console.log('Server running at http://127.0.0.1:1337/');
*/