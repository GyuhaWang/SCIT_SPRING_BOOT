const submitButton = document.getElementById("inquiry_submit_button");
const validationInputs = document.getElementsByClassName("required-inputs");


function validateInput(inputs) {
  for (let i = 0; i < inputs.length; i++) {
    const element = inputs[i];
    const value = element.value.trim(); 

 
    if (!isNullValid(value)) {
      alert(`입력값을 모두 채워주세요 (${element.name})`);
      element.focus();
      return false;
    }


    if (element.type === "email" && !isValidateEmail(value)) {
      alert("이메일 형식이 올바르지 않습니다.");
      element.focus();
      return false;
    }
  }

  return true;
}


function isNullValid(input) {
  return input !== null && input !== "";
}


function isValidateEmail(email) {
  return email.includes("@");
}


submitButton.addEventListener("click", (event) => {
  event.preventDefault(); 

  const isValid = validateInput(validationInputs);
  if (isValid) {
    alert("제출 완료");
    document.querySelector(".inquiry-form").submit(); 
  } else {
    alert("제출 실패");
  }
});
