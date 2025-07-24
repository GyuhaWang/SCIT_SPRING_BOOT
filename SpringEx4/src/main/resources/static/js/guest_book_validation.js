/**
 * 
 */
window.onload = function () {
  const form = document.querySelector("#guest_book_form");

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const name = document.querySelector("#name").value.trim();
    const pw = document.querySelector("#password").value.trim();
    const content = document.querySelector("#content").value.trim();
   
    if (
      (
        isNameValid(name) &&
		isPwValid(pw)
      )
    ) {
		e.target.submit();

    }
  });

  function isNameValid(name) {
	const isValid = name.length >=5;
	const invalidMessage = "이름은 5자 이상으로 하세요."
	if(!isValid){
		invalidAlertAction(invalidMessage);
	}
	return isValid;
  }

  function isPwValid(pw) {
    const isValid = pw.length >=5;
	const invalidMessage = "비밀번호는 5자 이상으로 하세요."
	if(!isValid){
		invalidAlertAction(invalidMessage);
	}
	return isValid;
  }
  
  function invalidAlertAction(message){
	window.alert(message);
  }
};
