window.onload = function () {
  const form = document.querySelector("#student_form");

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const name = document.querySelector("#name").value.trim();
    const java = document.querySelector("#java").value.trim();
    const db = document.querySelector("#db").value.trim();
    const web = document.querySelector("#web").value.trim();
    if (
      !(
        isNameValid(name) &&
        isGradeOptionValid(java) &&
        isGradeOptionValid(db) &&
        isGradeOptionValid(web)
      )
    ) {
      window.alert("invalid");
    } else {
      e.target.submit();
    }
  });

  function isNameValid(name) {
    const n = name.trim();
    if (n.length >= 3 && n.length <= 10) {
      return true;
    }
    return false;
  }

  function isGradeOptionValid(option) {
    const options = ["상", "중", "하"];
    return options.includes(option);
  }
};
