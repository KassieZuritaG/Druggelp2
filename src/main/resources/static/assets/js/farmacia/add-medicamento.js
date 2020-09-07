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
				$.each( response, function(index, alumno ) {					
					$("#farmacoid").append("<option value='"+ farmaco.idfarmaco+"'>" + farmaco.costo + "</option>");					
				});
			}
			else{
				$("#farmacoid").addClass('invisible').removeClass('visible');
				console.log("No hay fármacos registrados con el nombre: " + criteria);				
			}			
		},
		error : function(err){
			console.error(err);
		}		
	});
}

function create(){		
	$.ajax({
		url : "/medicamento/create",
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
		url : "/farmacia/medicamentos/",
		method : 'GET',
		success : function(response){
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
		url : developURL + "farmacia/add",
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
		
});
