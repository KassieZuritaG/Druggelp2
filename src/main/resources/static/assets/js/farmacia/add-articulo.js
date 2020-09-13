function searchByNombre(){ 
	var criteria = $("#txtNombre").val();
	$.ajax({
		url : "/farmaco/search/" + criteria,
		method : 'GET',
		success : function(response){
			$("#farmacoid").empty();			
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#farmacoid").addClass('visible').removeClass('invisible');
				$.each( response, function(index, farmaco ) {					
					$("#farmacoid").append("<option value='"+ farmaco.idfarmaco +"'>" + farmaco.nombre + "</option>");		
					
				});
			}
			else{
				$("#farmacoid").addClass('invisible').removeClass('visible');
				console.log("No hay farmacos ingresado con el nombre: " + criteria);				
			}			
		},
		error : function(err){
			console.error(err);
		}		
	});
}


//create
function create(){		
	$.ajax({
		url : "/articulo/create",
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#contentFrmArticulo").empty();
			$("#contentFrmArticulo").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}


//list


function list(){
	$.ajax({
		url: "/farmacia/items",
		method: 'GET',
		success: function(response){
			$("#listArticulos").empty();
			$("#listArticulos").html(response);
		},
		error: function(err){
			console.log(err);
		}
	});
}

//save 

function save(){	
	var dataForm = objectifyForm($("#frmArticulo").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : "/farmacia/add",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			console.log(response);			 
			list();
		},
		error : function(err){
			console.log(err);
		}		
	});
	
}


//document

$(document).ready(function(){
	
	list();
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#btnSubmit").click(function(){
		save();		
	});
		
});