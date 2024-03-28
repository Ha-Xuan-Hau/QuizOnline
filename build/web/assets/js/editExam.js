function isValidDateFormat(dateString) {
  var pattern = /^\d{4}-\d{2}-\d{2}$/;
  return pattern.test(dateString);
}
function validateForm(event) {
  // Get form elements
  var examStart = document.getElementById('exam-start');
  var examEnd = document.getElementById('exam-end');
  var examEndError = document.getElementById('exam-end-error');
  examEndError.textContent = '';



  // Error flag
  var hasError = false;



  // Check exam-start and exam-end dates and times
  var startDateTime = new Date(examStart.value);
  var endDateTime = new Date(examEnd.value);
  
  if (!isValidDateFormat(examStart.value)) {
    examEndError.textContent = 'Invalid date format. Please use the format YYYY-MM-DD.';
    hasError = true;
  }

  if (!isValidDateFormat(examEnd.value)) {
    examEndError.textContent = 'Invalid date format. Please use the format YYYY-MM-DD.';
    hasError = true;
  }

  if (startDateTime.toString() === 'Invalid Date') {
    examEndError.textContent = 'Invalid date format';
    hasError = true;
  }

  if (endDateTime.toString() === 'Invalid Date') {
    examEndError.textContent = 'Invalid date format';
    hasError = true;
  }

  if (endDateTime <= startDateTime) {
    examEndError.textContent = 'End date should be after the start date';
    hasError = true;
  }


  // Check if exam-end is greater than or equal to the current time
  var currentDateTime = new Date();

  if (endDateTime < currentDateTime) {
    examEndError.textContent = 'End date should be greater than or equal to the current time';
    hasError = true;
  }

  // Prevent form submission if there are errors
  if (hasError) {
    event.preventDefault();
    return false;
  }
}


