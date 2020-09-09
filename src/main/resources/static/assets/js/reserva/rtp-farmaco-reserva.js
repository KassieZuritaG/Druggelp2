function reportArticulosMeses(){	
	$.ajax({
		url : "/reserva/dataRptFarmacoReserva",
		method : 'GET',
		success : function(response){
			console.log(response);

			var toData = [];
			var color = Chart.helpers.color;
			var toMesesAll= [];
			var toArticulosAll= [];
			var toBarrasMat = [];
			var toNumero= [];
			
			$.each(response, function(i, item){
				console.log(item);
				toMesesAll.push(item.mes);
				toArticulosAll.push(item.articulo);
				toData.push(item.numero); 
			});
			
			//eliminar los farmacias repetidos 
			var toArticulos = [];
			const myObjU = {}
			toArticulosAll.forEach(y => !(y in myObjU) && (myObjU[y] = true) && toArticulos.push(y))


			
			//eliminar las materias repetidas 
			var toMeses = [];
			const myObjM = {}
			toMesesAll.forEach(x => !(x in myObjM) && (myObjM[x] = true) && toMeses.push(x))
			
			
			//cargar las barras
			var usuTemporal = ".";
			var cantTemporal = 0;
			$.each(toArticulos, function(i, item){
				console.log(item);
				toMeses.forEach(function(element1){
					cantTemporal = 0;
					response.forEach(function(element){ 
						if(item == element.articulo && item != usuTemporal && element1 == element.mes){
							console.log(element.numero);
							console.log(element.mes);
							cantTemporal=1;
							toNumero.push(element.numero);
						}	
					});
					if(cantTemporal == 0){
						toNumero.push(0);
					}
				});
				usuTemporal = item;
				var barra = { 
						label: item,
						backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
						borderWidth: 1,
						data: toNumero,
					}
				toBarrasMat.push(barra);
				toNumero = []; 
			});
			
			var barChartData = {
					labels: toMeses, 
					datasets: toBarrasMat
			};
			
				var ctx = document.getElementById('canvasArticulosMeses').getContext('2d');
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
							text: 'Reserva de Articulos por Meses'
						}
					}
				});
		},
		error : function(err){
			console.log(err);
		}		
	});	
}

$(document).ready(function(){
	window.onload = function() {
	
		reportArticulosMeses();

	};
});