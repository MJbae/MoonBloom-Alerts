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
  let dob = document.getElementById("dob").value;
  let relationship = document.getElementById("relationship").value;
  let phoneNo = document.getElementById("phoneNo").value;
  let name = document.getElementById("name").value;

  if (!dob || !relationship || !name || !isValidPhoneNumber(phoneNo)) {
    alert("모든 필수 입력 사항을 완료해주세요!");
    return;
  }

  fetch("/api/notifications", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      dob: dob,
      relationship: relationship,
      name: name,
      phoneNo: phoneNo,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      window.location.href = `result.html?msg=${encodeURIComponent(
        data.msg
      )}&phoneNo=${encodeURIComponent(data.phoneNo)}`;
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
