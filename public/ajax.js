

post = function() {
	var obj = {
			id: "moke",
			value: 100,
			flag: true,
			dueDate: "2015-02-08T01:44:35+09:00"
	};
	postAjax(obj);
};

get = function() {
	getAjax();
};


postAjax = function(obj) {
	$.ajax(
			'http://localhost:9000/api/db/post',
			{
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
			}).done(success).fail(fail);
};

getAjax = function() {
	$.ajax(
			'http://localhost:9000/api/db/get',
			{
				type: 'get',
			}).done(success).fail(fail);
};

success = function(data) {
	console.log(data);
};

fail = function(XMLHttpRequest, textStatus, errorThrown) {
	console.log( textStatus );
	console.log( errorThrown );
};