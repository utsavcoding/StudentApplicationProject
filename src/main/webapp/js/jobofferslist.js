$(document).ready(function(){
	var api = "rest/offers";
	$.get(api, function (offers) {
	        var offer_data_body = "";
	        for (var i = 0; i < offers.length; i++) {
	            offer_data_body += '<tr>'
	                + '<td>' + offers[i].id + '</td>'
	                + '<td>' + offers[i].minGrade + '</td>'
	                + '<td>' + offers[i].organization + '</td>'
	                + '<td>' + offers[i].specialization + '</td>'
	                + '<td>' + offers[i].domain + '</td>'
	                + '<td>' + offers[i].jobPosition + '</td>'
	                + '<td>' + offers[i].compensation + '</td>'
	                + '</tr>';
	        }
	        $('#offer_data tbody').html(offer_data_body);
	    $('#offer_data').DataTable();
	});
});

