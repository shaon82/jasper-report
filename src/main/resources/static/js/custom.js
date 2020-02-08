

$(document).ready(function() {
	"use strict";



    $('#check').click(function(){
       //alert("working");

        getEmployeeDetails();

    });
});

function getEmployeeDetails(handleData){
   $.ajax({
        url:"/employee-list",
        success:function(result){
            console.log(result);

        }
    });
}



