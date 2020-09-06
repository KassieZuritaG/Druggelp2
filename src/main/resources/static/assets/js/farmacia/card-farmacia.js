function onDelete(id){	
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
					  deleteFarmacia(id)
				  }
				})
}

function deleteFarmacia(id) {
	$.ajax({
		url : "/farmacia/delete/" + id,
		method : 'GET',
		success : function(response){
			Swal.fire(
		      'Farmacia eliminada',
		      'Farmacia eliminada correctamente.',
		      'success'
		    ).then((result) => {
		    	location.reload();
			})		
		},
		error : function(err){
			Swal.fire(
		      'Ha ocurrido un error',
		      'No se ha podido eliminar la farmacia, intente nuevamente.',
		      'warning'
		    )
			console.error(err);
		}		
	});
}