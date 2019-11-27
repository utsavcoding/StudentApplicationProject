$(document).ready(function(){
	var rollNo=window.localStorage.getItem('rollNo');
	var api = "rest/offers/?rollNo="+rollNo;
	debugger;
	$.get(api, function (offers) {
	        var offer_data_body = "";
	        for (var i = 0; i < offers.length; i++) {
	        	offer_data_body += '<tr>'
	                + '<th scope="row" class="' +(i)+'">' + (i+1) + '</td>'
	                + '<td>' + offers[i].organization.name + '</td>'
	                + '<td>' + offers[i].jobPosition + '</td>'
	                + '<td>' + offers[i].compensation + '</td>'
	                + '<td>' + offers[i].specialization.name + '</td>'
	                + '<td>' + '<form action="rest/application/apply" method="post" enctype="multipart/form-data">'
	                +'<p>Select a file : <input type="file" name="file" required/></p>'
	                +'<input type="submit" value="Apply" class="btn btn-primary btn-block mt-4" />'
	                +'<input type="hidden" name="minGrade" value="'+ offers[i].minGrade +'"/>'
	                +'<input type="hidden" name="id" value="'+ offers[i].id +'"/>'
	                +'<input type="hidden" name="rollNumber" value="'+ window.localStorage.getItem('rollNo') +'"/>'
	                +'</form>'
	                + '</tr>';
	        }
	        $('#offer_data tbody').html(offer_data_body);
	    $('#offer_data').DataTable();
	});
});