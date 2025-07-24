
// Modal 열기
function orderFunc() {
	//code
	if(!isOrderValid()){
		return;
	}
	setOrderInfo();
	document.querySelector("#receiptModal").style.display = "flex";
/*	const orderForm = document.querySelector('#orderForm');
	orderForm.addEventListener('submit',formSubmitHandelr)
	
	
	function formSubmitHandelr(e){
		e.preventDefault();
		orderForm.submit();
		orderForm.removeEventListener('submit',formSubmitHandelr)
	}*/
	
	
}

// Modal 닫기
function closeModal() {
	document.querySelector("#receiptModal").style.display = "none";
	receiptExtras.innerHTML = '';
}

function isOrderValid(){
	const chickenType = document.querySelector('#chickenType');	
	const selectedText = chickenType.options[chickenType.selectedIndex].textContent;
	const quantity = document.querySelector('#quantity');
	const extraOptions = document.querySelectorAll("input[name=extraOption]:checked");
	const deliveryType = document.querySelector('input[name=deliveryType]:checked');
	
	if(chickenType?.value=== "" ||chickenType?.value== null ){
		window.alert("치킨 종류를 선택해 주세요.");
		return false;
	}
	if(quantity?.value== null || quantity?.value <=0){
			window.alert("수량은 1개 이상");
		return false;
	}
	if(deliveryType?.value === null){
		window.alert("직접 가지러옴?");
		return false;
	}
	return true;
}
function setOrderInfo(){
	/*get elemets */
	
	const chickenType = document.querySelector('#chickenType');	
	const selectedText = chickenType.options[chickenType.selectedIndex].textContent;
	const quantity = document.querySelector('#quantity');
	const extraOptions = document.querySelectorAll("input[name=extraOption]:checked");
	const deliveryType = document.querySelector('input[name=deliveryType]:checked');
	const extraOptionsAmount = Array.from(extraOptions).map(option => Number(option.value)).reduce((sum, price) => sum + price, 0);
	
	/*set modal content*/
	const receiptChickenType = document.querySelector('#receiptChickenType');
	const receiptQuantity = document.querySelector('#receiptQuantity');
	const receiptExtras = document.querySelector('#receiptExtras');
	const receiptDelivery = document.querySelector('#receiptDelivery');
	const receiptTotal = document.querySelector('#receiptTotal');

	receiptChickenType.textContent = selectedText;
	receiptQuantity.textContent = quantity.value;
	extraOptions.forEach(input => {
	  const label = input.closest("label");
	  const selectedOption = document.createElement("div"); // 새로운 div 생성
	  		selectedOption.textContent = label.textContent;
	  		receiptExtras.appendChild(selectedOption);
	});
	receiptDelivery.textContent =deliveryType.closest("label").textContent;
	
	/*calculate total amount */
	let totalAmount = 0 ;
	totalAmount= (Number(chickenType?.value ??0) *quantity?.value??0) +
	 extraOptionsAmount+
	Number(deliveryType.value);
	receiptTotal.textContent = totalAmount;
	
	/*set orderForm*/
	
	const formChickenType = document.querySelector('form>input[name=chickenType]');
	const formChickenPrice = document.querySelector('form>input[name=chickenPrice]');
	const formQuantity = document.querySelector('form>input[name=quantity]');
	const formExtraOptions = document.querySelector('form>input[name=extraOptions]');
	const formExtraTotalPrice = document.querySelector('form>input[name=extraTotalPrice]');
	const formDeliveryType = document.querySelector('form>input[name=deliveryType]');
	const formDeliveryPrice = document.querySelector('form>input[name=deliveryPrice]');
	const formTotalPrice = document.querySelector('form>input[name=totalPrice]');
	
	formChickenType.value = selectedText;
	formChickenPrice.value = Number(chickenType?.value ??0);
	formQuantity.value = quantity.value;
	let extraOrderTexts = '';
	let extraOrderAmount =0;
	extraOptions.forEach(input => {
		extraOrderAmount += Number(input.value); 
		  const label = input.closest("label");
		  extraOrderTexts += `, ${label.textContent}`;
		});
	
	formExtraOptions.value =extraOrderTexts; 
	formExtraTotalPrice.value = extraOptionsAmount;
	formDeliveryType.value = deliveryType.closest("label").textContent;
	console.log(deliveryType.value);
	formDeliveryPrice.value =Number(deliveryType?.value??0);
	console.log(formDeliveryPrice.value)
	formTotalPrice.value = totalAmount;
	
}