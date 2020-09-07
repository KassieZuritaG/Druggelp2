function report(){	
	$.ajax({
		url : "/reserva/dataRptReservaUsuario",
		method : 'GET',
		success : function(response){
			console.log(response);
			
			var toData = [];
			var toLabels = [];
			var toColors = [];
			
			$.each(response, function(i, item){
				console.log(item);
				toData.push(item.numero);
				toLabels.push(item.usuario);						
				toColors.push(getRandomColor());
			});
									
			var barChartData = {
				labels: toLabels,
				datasets: [{
					label: 'Usuarios',
					backgroundColor: getRandomColor(),
					borderColor: getRandomColor(),
					borderWidth: 1,
					data: toData
				}]

			};

			window.onload = function() {
				var ctx = document.getElementById('chart-area-1').getContext('2d');
				window.myBar = new Chart(ctx, {
					type: 'bar',
					data: barChartData,
					options: {
						responsive: true,
						legend: {
							position: 'top',
						},
						title: {
							display: true,
							text: 'Número de reservas por usuario'
						}
					}
				});

			};
			
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	
	report();	

	
});

