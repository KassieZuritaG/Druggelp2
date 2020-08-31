function save(){	
	var dataForm = objectifyForm($("#frmReserva").serializeArray());	
	var requestBody = JSON.stringify(dataForm);
	console.log(requestBody);
			
	$.ajax({
		url : "http://localhost:8080/reserva/save",
		method : 'POST',
		contentType : "application/json",
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},		
		data : requestBody,
		success : function(response){
			console.log(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function create(){
	var id = $("#idusuario").val();	
	$.ajax({
		url : "/reserva/create/" + id,
		method : 'GET',
		success : function(response){			
			$("#contentReserva").empty();
			$("#contentReserva").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});
}

function list(){
	var id = $("#idusuario").val();
	$.ajax({
		url : "/reserva/list/" + id,
		method : 'GET',
		success : function(response){
			$("#listReserva").empty();
			$("#listReserva").html(response);
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	$("#btnAdd").click(function(){
		create();		
	});
	
	$("#tab--2").click(function(){
		list();		
	});
	
});	