function searchByCedula(){
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
					$("#farmacoid").append("<option value='"+ farmaco.idfarmaco +"'>" + farmaco.nombre"</option>");					
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



function list(){
	$.ajax({
		url: "/reserva/pills",
		method: 'GET',
		success: function(response){
			$("#listMedicamentos").empty();
			$("#listMedicamentos").html(response);
		},
		error: function(err){
			console.log(err);
		}
	})
}



$(document).ready(function(){
		list();
})