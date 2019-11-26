$(document).ready(function(){
	var rollNo=window.localStorage.getItem('rollNo');
	var api = "rest/offers/?rollNo="+rollNo;
	debugger;
	$.get(api, function (offers) {
	        var offer_data_body = "";
	        for (var i = 0; i < offers.length; i++) {
	            offer_data_body += '<tr>'
	                + '<td>' + i + '</td>'
	                + '<td>' + offers[i].minGrade + '</td>'
	                + '<td>' + offers[i].organization.name + '</td>'
	                + '<td>' + offers[i].specialization.name + '</td>'
	                + '<td>' + offers[i].domain.domainName + '</td>'
	                + '<td>' + offers[i].jobPosition + '</td>'
	                + '<td>' + offers[i].compensation + '</td>'
	                + '</tr>';
	        }
	        $('#offer_data tbody').html(offer_data_body);
	    $('#offer_data').DataTable();
	});
});

