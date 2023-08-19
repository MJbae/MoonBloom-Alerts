function fetchMessage() {
    let dob = document.getElementById("dob").value;
    let calendarType = document.getElementById("calendarType").value;
    let relationship = document.getElementById("relationship").value;
    let name = document.getElementById("name").value;

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
        data = data.replace(/\n/g, '<br>');
        document.getElementById("message").innerHTML = data;
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
