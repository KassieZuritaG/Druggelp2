function onDelete(id){	
		/*<![CDATA[*/
			//var message = /*[[${confirmar}]]*/
			Swal.fire({
					title : 'Eliminar registro', 
					icon : 'warning',
					showCancelButton: true,
					confirmButtonText: 'Eliminar',
					cancelButtonText:'Cancelar',
					closeOnConfirm: false
					}).then((choice) => {
				        if (choice.value == true) {
				            $.ajax({
				                type: "GET",
				                url : "/farmacia/delete/" + id,
				            });
				        }		
				        location.reload();
				    });
		/*]]>*/
			
}