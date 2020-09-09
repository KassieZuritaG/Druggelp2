function searchByNombre(){ 
	var criteria = $("#txtNombre").val();
	$.ajax({
		url : "/articulo/search/" + criteria,
		method : 'GET',
		success : function(response){
			$("#articuloid").empty();			
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#articuloid").addClass('visible').removeClass('invisible');
				$.each( response, function(index, farmaco ) {					
					$("#articuloid").append("<option value='"+ articulo.farmaco.idfarmaco +"'>" + articulo.farmaco.nombre + "</option>");		
					
				});
			}
			else{
				$("#articuloid").addClass('invisible').removeClass('visible');
				console.log("No hay articulos ingresado con el nombre: " + criteria);				
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
		url : "/detallereserva/create",
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#contentFrmDetalleReserva").empty();
			$("#contentFrmDetalleReserva").html(response);
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
			$("#listDetalleReservas").empty();
			$("#listDetalleReservas").html(response);
		},
		error: function(err){
			console.log(err);
		}
	});
}

//save 

function save(){	
	var dataForm = objectifyForm($("#frmDetalleReserva").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : developURL + "reserva/add",
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