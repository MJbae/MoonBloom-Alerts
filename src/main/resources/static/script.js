function isValidPhoneNumber(phoneNo) {
    const pattern = /^01[016789]\d{6,8}$/;
    return pattern.test(phoneNo);
}

function checkMandatoryFields() {
    let dob = document.getElementById("dob").value;
    let calendarType = document.getElementById("calendarType").value;
    let relationship = document.getElementById("relationship").value;
    let phoneNo = document.getElementById("phoneNo").value;
    let name = document.getElementById("name").value;

    const button = document.querySelector("button");
    if (dob && calendarType && relationship && name && isValidPhoneNumber(phoneNo)) {
        button.disabled = false;
    } else {
        button.disabled = true;
    }
}


// Attach event listeners to input fields
["dob", "calendarType", "relationship", "phoneNo", "name"].forEach(id => {
    document.getElementById(id).addEventListener('input', checkMandatoryFields);
});

// Disable the button initially
document.querySelector("button").disabled = true;


function fetchMessage() {
    let dob = document.getElementById("dob").value;
    let calendarType = document.getElementById("calendarType").value;
    let relationship = document.getElementById("relationship").value;
    let name = document.getElementById("name").value;
    let phoneNo = document.getElementById("phoneNo").value;

    if (!dob || !calendarType || !relationship || !isValidPhoneNumber(phoneNo)) {
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
            name: name,
            phoneNo: phoneNo
        })
    })
    .then(response => response.json())  // Since we're expecting JSON from server
    .then(data => {
        // Redirect to the new page with the message as a query parameter
        window.location.href = `result.html?msg=${encodeURIComponent(data.msg)}&phoneNo=${encodeURIComponent(data.phoneNo)}`;
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
