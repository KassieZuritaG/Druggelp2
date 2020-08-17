function onDeletefarmaco(id){	
			Swal.fire({
				  title: '¿Estás seguro?',
				  text : "Una vez eliminado no se podrá recuperar el registro" ,
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Eliminar',
				  cancelButtonText: 'Cancelar'
				}).then((result) => {
				  if (result.value) {
				    eliminar(id)
				  }
				})
}

function eliminar(id) {
	$.ajax({
		url : "/farmaco/delete/" + id,
		method : 'GET',
		success : function(response){
			Swal.fire(
		      'Fármaco eliminado',
		      'Fármaco eliminado correctamente.',
		      'success'
		    ).then((result) => {
		    	location.reload();
			})		
		},
		error : function(err){
			Swal.fire(
		      'Ha ocurrido un error',
		      'No se ha podido eliminar el fármaco, intente nuevamente.',
		      'warning'
		    )
			console.error(err);
		}		
	});
}