th:onclick="deleteFunc(this.getAttribute('data-tid'))"


window.onload = function () {
  const delete_form = document.querySelector("#delete_form");
  const items = document.getElementsByClassName("item");

  setRandomBackground(items)
  
  delete_form.addEventListener("submit", (e) => {
    e.preventDefault();
	const pw = document.querySelector("#pw").value;
	const password= document.querySelector("#password").value;
	
		if(pw.trim() === password.trim()){
			e.target.submit();
		}	
		else{
			window.alert("비밀번호가 달라용");
		}
		
	
   
  });
	function setRandomBackground(list){
		
		Array.from(list).forEach((item)=>{
			const color = generateRandomHexColor();
			item.style.backgroundColor =color;
			 
			
		})
	}
  
  function generateRandomHexColor() {
    
    const randomColorNumber = Math.floor(Math.random() * 16777215);
    let hexColor = randomColorNumber.toString(16);
    hexColor = hexColor.padStart(6, '0');
    return '#' + hexColor;
  }
  
  
};
