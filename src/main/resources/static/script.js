function isValidPhoneNumber(phone) {
    const pattern = /^01[016789]-\d{3,4}-\d{4}$/;
    return pattern.test(phone);
}

function fetchMessage() {
    let dob = document.getElementById("dob").value;
    let calendarType = document.getElementById("calendarType").value;
    let relationship = document.getElementById("relationship").value;
    let name = document.getElementById("name").value;
    let phone = document.getElementById("phone").value;

    if (!dob || !calendarType || !relationship || !isValidPhoneNumber(phone)) {
        alert("모든 필수 입력 사항을 완료해주세요!");
        return;
    }

    fetch('/api/notifications', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            dob: dob,
            calendarType: calendarType,
            relationship: relationship,
            name: name
        })
    })
    .then(response => response.text())
    .then(data => {
        // Redirect to the new page with the message as a query parameter
        window.location.href = `result.html?msg=${encodeURIComponent(data)}`;
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
