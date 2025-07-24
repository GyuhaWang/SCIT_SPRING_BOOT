/**
 * 
 */
window.onload = function() {
    var form = document.getElementById('perfumeForm');
	const linkButton = document.getElementById('link_button');
	
	linkButton.addEventListener('click',(e)=>{ window.location.href ="result"})
	
    form.onsubmit = function(event) {
        var name = form.name.value.trim();
        var gender = form.gender.value;
        var age = form.age.value.trim();
        var usageFrequency = form.querySelector('input[name="usageFrequency"]:checked');
        var purchaseBudget = form.purchaseBudget.value;

        if (!name) {
            alert('이름을 입력해주세요.');
            form.name.focus();
            event.preventDefault();
            return false;
        }

        if (!gender) {
            alert('성별을 선택해주세요.');
            form.gender.focus();
            event.preventDefault();
            return false;
        }

        if (!age) {
            alert('나이를 입력해주세요.');
            form.age.focus();
            event.preventDefault();
            return false;
        } else if (isNaN(age) || age < 0 || age > 120) {
            alert('나이는 0에서 120 사이의 숫자여야 합니다.');
            form.age.focus();
            event.preventDefault();
            return false;
        }

        if (!usageFrequency) {
            alert('사용 빈도를 선택해주세요.');
            event.preventDefault();
            return false;
        }

        if (!purchaseBudget) {
            alert('구매 예산을 선택해주세요.');
            form.purchaseBudget.focus();
            event.preventDefault();
            return false;
        }

        // 모든 조건 통과 시 제출
      	event.submit();
    };
};
