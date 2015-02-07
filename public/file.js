

upload = function() {
	if ( $('#fileForm').val() !== '' ) {
		var fd = new FormData();
		fd.append( 'file', $('#fileForm').prop('files')[0] );
		console.log(fd);
	}
	postAjax(fd);
};

multiUpload = function() {
	_.forEach($('#multiFileForm').prop('files'), function(value, index, ar){
		var fd = new FormData();
		fd.append('file', value);
		postAjax(fd);
	});
};


postAjax = function(fd) {
	$.ajax(
			'http://localhost:9000/api/file/upload',
			{
				type: 'post',
				processData: false,
				contentType: false,
				data: fd,
			}).done(success).fail(fail);
};

success = function(data) {
	console.log(data);
};

fail = function(XMLHttpRequest, textStatus, errorThrown) {
	console.log( textStatus );
	console.log( errorThrown );
};