window.onload  = function() {
	const updateButton = document.querySelector("#update_button");
	const deleteButton = document.querySelector("#delete_button");
	
	updateButton.addEventListener("click",(e)=>{
		console.log(e)
	})
	deleteButton.addEventListener("click",(e)=>{
		e.preventDefault();
		const isConfirmed = window.confirm("정말로 삭제하시겠습니까?");
		
	})

}