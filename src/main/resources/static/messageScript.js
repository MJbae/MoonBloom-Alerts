window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('msg');
    document.getElementById("generatedMessage").textContent = decodeURIComponent(message);
}