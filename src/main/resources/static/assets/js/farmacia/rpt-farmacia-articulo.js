function reportFarmaciasArticulos(){	
	$.ajax({
		url : "/farmacia/dataRptFarmacoFarmacia",
		method : 'GET',
		success : function(response){
			console.log(response);

			var toData = [];
			var color = Chart.helpers.color;
			var toArticulosAll= [];
			var toFarmaciasAll= [];
			var toBarrasMat = [];
			var toCantidad= [];
			
			$.each(response, function(i, item){
				console.log(item);
				toArticulosAll.push(item.articulo);
				toFarmaciasAll.push(item.nombrefarmacia);
				toData.push(item.cantidad); 
			});
			
			
			//eliminar las materias repetidas 
			var toFarmacias = [];
			const myObjM = {}
			toFarmaciasAll.forEach(x => !(x in myObjM) && (myObjM[x] = true) && toFarmacias.push(x))
			
			//eliminar los farmacias repetidos 
			var toArticulos = [];
			const myObjU = {}
			toArticulosAll.forEach(y => !(y in myObjU) && (myObjU[y] = true) && toArticulos.push(y))
			
			//cargar las barras
			var usuTemporal = ".";
			var cantTemporal = 0;
			$.each(toFarmacias, function(i, item){
				console.log(item);
				toArticulos.forEach(function(element1){
					cantTemporal = 0;
					response.forEach(function(element){ 
						if(item == element.nombrefarmacia && item != usuTemporal && element1 == element.articulo){
							console.log(element.cantidad);
							console.log(element.articulo);
							cantTemporal=1;
							toCantidad.push(element.cantidad);
						}	
					});
					if(cantTemporal == 0){
						toCantidad.push(0);
					}
				});
				usuTemporal = item;
				var barra = { 
						label: item,
						backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
						borderWidth: 1,
						data: toCantidad,
					}
				toBarrasMat.push(barra);
				toCantidad = []; 
			});
			
			var barChartData = {
					labels: toArticulos, 
					datasets: toBarrasMat
			};
			
				var ctx = document.getElementById('canvasFarmaciaArticulos').getContext('2d');
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
							text: 'Farmacias por articulos'
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
	
		reportFarmaciasArticulos();

	};
});