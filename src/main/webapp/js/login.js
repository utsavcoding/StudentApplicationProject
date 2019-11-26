$(document).ready(function() {
	$('#errorAlert').hide();
    validateLogin();
});

function validateLogin(){
    $('#login').click(function(event) {
           event.preventDefault();
           var rollNo = $('#rollNo').val();
           var password = $('#password').val();
           var json =  {'rollNumber':rollNo, 
                        'password':password
                        };
           $.ajax({
                url: "/alumni-placement/rest/student/login",
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(json),
                cache: false,
                async: true,
                timeout: 600000,
                success: function(response) {
                	if(response.status=="200"){
                		window.localStorage.setItem('rollNo', response.rollNumber);
                		window.location.href = "/alumni-placement/offers.html";
                	}
                	else{
                		$('#errorAlert').show();
                		$('#errorAlert').delay(10000).fadeOut();
                	}
                	$('#rollNo').val("");
                	$('#password').val("") ;
                },
                error : function(response){
                    
                }
            });  
    });
}