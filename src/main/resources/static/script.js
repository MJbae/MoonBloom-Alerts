function isValidPhoneNumber(phoneNo) {
  const pattern = /^01[016789]\d{6,8}$/;
  return pattern.test(phoneNo);
}

function checkMandatoryFields() {
  let dob = document.getElementById("dob").value;
  let relationship = document.getElementById("relationship").value;
  let phoneNo = document.getElementById("phoneNo").value;
  let name = document.getElementById("name").value;

  const button = document.querySelector("button");
  if (dob && relationship && name && isValidPhoneNumber(phoneNo)) {
    button.disabled = false;
  } else {
    button.disabled = true;
  }
}

["dob", "relationship", "phoneNo", "name"].forEach((id) => {
  document.getElementById(id).addEventListener("input", checkMandatoryFields);
});

document.querySelector("button").disabled = true;

function fetchMessage() {
  const phoneNo = document.getElementById("phoneNo").value;
  const dob = document.getElementById("dob").value;
  const relationship = document.getElementById("relationship").value;
  const name = document.getElementById("name").value;

  const requestData = {
    phoneNo: phoneNo,
    dob: new Date(dob),
    relationship: relationship,
    name: name,
  };

  // Assuming you have an endpoint /api/notifications that will process this information
  fetch("/api/notifications", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.honoree && data.phoneNo) {
        window.location.href = `result.html?relationship=${encodeURIComponent(
          data.honoree.relationship
        )}&gregorianBirthday=${encodeURIComponent(
          data.honoree.gregorianBirthday
        )}&phoneNo=${encodeURIComponent(data.phoneNo)}`;
      }
    })
    .catch((error) => {
      console.error("There was an error with the fetch operation:", error);
      document.getElementById("message").textContent =
        "An error occurred. Please try again.";
    });
}
