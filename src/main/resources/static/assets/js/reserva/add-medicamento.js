function searchByNombre(){
	var criteria = $("#txtNombre").val();
	$.ajax({
		url : "/farmaco/search/" + criteria,		
		method : 'GET',
		success : function(response){		
			$("#medicamentoid").empty();	
			var count = Object.keys(response).length;			
			if(count > 0){								
				$("#medicamentoid").addClass('visible').removeClass('invisible');
				$.each( response, function(index, farmaco ) {									
					$("#medicamentoid").append("<option value='"+ farmaco.idfarmaco +"'>" + farmaco.nombre + " | Costo: "+ farmaco.costo+"</option>");					
				});
			}
			else{
				$("#medicamentoid").addClass('invisible').removeClass('visible');
				console.log("No hay farmacos ingresado con el nombre: " + criteria);				
			}			
		},
	});
}


function create(){		
	$.ajax({
		url : "/detallereserva/create",
		method : 'GET',
		success : function(response){
			console.log(response);
			$("#contentFrmMedicamento").empty();
			$("#contentFrmMedicamento").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function list(){
	$.ajax({
		url: "/reserva/pills",
		method: 'GET',
		success: function(response){
			$("#listMedicamentos").empty();
			$("#listMedicamentos").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}


function save(){	
	var dataForm = objectifyForm($("#frmMedicamento").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);			
	$.ajax({
		url : "/reserva/add",
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

$(document).ready(function(){
		list();

		$("#btnAdd").click(function(){
			create();		
		});

		$("#btnSubmit").click(function(){
			save();		
		});
}) 