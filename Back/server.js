"use strict";

require('colors');

var express 	= require('express'),
	bodyParser  = require('body-parser'),
	http        = require('http'),
	path        = require('path'),
	serveStatic = require('serve-static'),
	httpProxy 	= require('http-proxy'),
	querystring 	= require('querystring');

var app = express();
app.use(bodyParser.json());
var server = http.createServer(app);
var apiProxy = httpProxy.createProxyServer();

// proxify api routes
app.all("/apitest/*", function(req, res){
console.log("param");
delete req.headers.host;
var data = JSON.stringify(req.body);
if(req.method=="DELETE"){
	req.headers['Content-Length']=0;
}
console.log(req.headers);
var options = {
	host: 'cache.univ-lille1.fr',
	port: 3128,
	//host:'http://iagl-server.cloudapp.net' + req.path + '?' + querystring.stringify(req.query),
	method: req.method,
	path: 'http://iagl-server.cloudapp.net' + req.path + '?' + querystring.stringify(req.query),
	headers: req.headers
}; 
//console.log(options,data);
var request = http.request(options, function (response) {
	res.writeHead(response.statusCode, response.headers);
	response.on('data', function (chuck) {
		res.write(chuck);
	});
	response.on('end', function () {
//console.log('test');
		res.end();
	});
}).on('error', function () {});
request.write(data);
request.end();
//req.headers['host'] ="iagl-server.cloudapp.net";

  /*apiProxy.web(req, res, {
	 target: 'http://cache.univ-lille1.fr',
port: 3128,
    toProxy: true});*/
});

app.set('port', process.env.PORT || 3001);
app.use(serveStatic(path.join(__dirname, '.')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', function (req, res) {
	res.sendFile(path.join(__dirname, 'admin.html'));
});

server.listen(app.get('port'), function() {
	console.log('✔︎︎ Express server listening on http://localhost:%d/'.green, app.get('port'));
});
