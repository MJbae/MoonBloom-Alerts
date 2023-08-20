window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('msg');
    const phoneNo = urlParams.get('phoneNo');

    document.getElementById("fetchMsg").textContent = decodeURIComponent(message);
    document.getElementById("fetchPhoneNumber").textContent = decodeURIComponent(phoneNo);
}
