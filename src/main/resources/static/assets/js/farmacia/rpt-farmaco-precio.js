function reportArticulosPrecio(){	
	$.ajax({
		url : "/farmacia/dataRptFarmacoPrecio",
		method : 'GET',
		success : function(response){
			console.log(response);

			var toData = [];
			var color = Chart.helpers.color;
			var toFarmaciasAll= [];
			var toArticulosAll= [];
			var toBarrasMat = [];
			var toPrecio= [];
			
			$.each(response, function(i, item){
				console.log(item);
				toFarmaciasAll.push(item.nombrefarmacia);
				toArticulosAll.push(item.articulo);
				toData.push(item.precio); 
			});
			
			//eliminar los farmacias repetidos 
			var toArticulos = [];
			const myObjU = {}
			toArticulosAll.forEach(y => !(y in myObjU) && (myObjU[y] = true) && toArticulos.push(y))


			
			//eliminar las materias repetidas 
			var toFarmacias = [];
			const myObjM = {}
			toFarmaciasAll.forEach(x => !(x in myObjM) && (myObjM[x] = true) && toFarmacias.push(x))
			
			
			//cargar las barras
			var usuTemporal = ".";
			var cantTemporal = 0;
			$.each(toArticulos, function(i, item){
				console.log(item);
				toFarmacias.forEach(function(element1){
					cantTemporal = 0;
					response.forEach(function(element){ 
						if(item == element.articulo && item != usuTemporal && element1 == element.nombrefarmacia){
							console.log(element.precio);
							console.log(element.nombrefarmacia);
							cantTemporal=1;
							toPrecio.push(element.precio);
						}	
					});
					if(cantTemporal == 0){
						toPrecio.push(0);
					}
				});
				usuTemporal = item;
				var barra = { 
						label: item,
						backgroundColor: color(getRandomColor()).alpha(0.5).rgbString(),			
						borderWidth: 1,
						data: toPrecio,
					}
				toBarrasMat.push(barra);
				toPrecio = []; 
			});
			
			var barChartData = {
					labels: toFarmacias, 
					datasets: toBarrasMat
			};
			
				var ctx = document.getElementById('canvasFarmacos').getContext('2d');
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
							text: 'Precios de Articulos por Farmacias'
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
	
		reportArticulosPrecio();

	};
});