// ########## Show Hide NavBar ###############

const nodeBar = document.getElementById('nodebar');

const clickClose = document.querySelectorAll(".clickClose");

const hihi = document.getElementById('hihi');

const hehe = document.getElementById('hehe');

nodeBar.addEventListener('click', () => {
    hihi.classList.toggle('clickClose');
    hehe.classList.toggle('clickClose');
});

const headClose = document.querySelector('.main-close');

headClose.addEventListener('click', () => {
    hihi.classList.toggle('clickClose');
    hehe.classList.toggle('clickClose');
});

//######################### Login and Register ############################

const signIn = document.querySelector('.sign-in');

const register = document.querySelector('.register-form');

const eventClickRes = document.querySelector('.register-control');

const registerID = document.getElementById('register-id');

const loginID = document.getElementById('login-id');

eventClickRes.addEventListener('click', () => {
    registerID.classList.toggle('register-form');
    loginID.classList.add('sign-close');
});

const eventReturnLogin = document.querySelector('.return-login');

eventReturnLogin.addEventListener('click', () => {
    registerID.classList.toggle('register-form');
    loginID.classList.remove('sign-close');
});

// ########## Cart Infomation ###############

// const title = document.querySelectorAll('.main-inf-pro');

// const content = document.querySelectorAll('.inf-pro');

// title.addEventListener('click',() =>{
//     alert('oke');
// });


//#################### Check form ##############################

const formLogin = document.querySelector('#login-final');
const formRegister = document.querySelector('#register-final');

//#####Check validate login#####//
function validateLogin() {
    var email = document.getElementById('email-login').value;
    var password = document.getElementById('password-login').value;
    var errorMessages = document.getElementById('error-messages');

    //Clear previous error messages
    errorMessages.innerHTML = '';

    //Check empty
    if (email.trim() === '' || password.trim() === '') {
        errorMessages.innerHTML = 'Please fill in all fields';
        return false;
    }

    //Check email format
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        errorMessages.innerHTML = 'Invalid email format';
        return false;
    }

    //Check password length
    if (password.length < 8) {
        errorMessages.innerHTML = 'Password must be at least 8 characters long';
        return false;
    }

    //If all checks pass, the form is valid
    return true;
}
//###################################################


//#####Check validate register#####//
function validateRegister() {
    var fullName = document.querySelector('#register-final input[placeholder="Full name"]').value;
    var email = document.querySelector('#register-final input[placeholder="Email"]').value;
    var phoneNumber = document.querySelector('#register-final input[placeholder="Phone number"]').value;
    var birthday = document.querySelector('#register-final input[placeholder="Birthday"]').value;
    var address = document.querySelector('#register-final input[placeholder="Address"]').value;
    var password = document.querySelector('#register-final input[placeholder="Password"]').value;
    var confirmPassword = document.querySelector('#register-final input[placeholder="Confirm password"]').value;

    // Reset error message before check validate
    resetErrorMessages();

    // Check form
    if (fullName === '') {
        displayErrorMessage('full-name-error', 'Please enter your Full Name');
        return false;
    }

    if (email === '') {
        displayErrorMessage('email-error', 'Please enter your Email');
        return false;
    } else {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            displayErrorMessage('email-error', 'Your Email invalid!');
            return false;
        }
    }

    if (phoneNumber === '') {
        displayErrorMessage('phone-number-error', 'Please enter your phone number');
        return false;
    } else {
        var phonePattern = /^\d{10}$/;
        if (!phonePattern.test(phoneNumber)) {
            displayErrorMessage('phone-number-error', 'Your phone number invalid!');
            return false;
        }
    }

    if (birthday === '') {
        displayErrorMessage('birthday-error', 'Please choose your Birthday');
        return false;
    }
    
    if(address === ""){
        displayErrorMessage('address-error', 'Your address invalid!');
        return false;
    }

    if (password === '') {
        displayErrorMessage('password-error', 'Please enter your Password');
        return false;
    }
    
        if (password.length >= 8) {
        displayErrorMessage('password-error', 'Password must be getter than or equal 8');
        return false;
    }

    if (confirmPassword === '') {
        displayErrorMessage('confirm-password-error', 'Please enter your Password again');
        return false;
    }

    if (password !== confirmPassword) {
        displayErrorMessage('confirm-password-error', 'Password does not match password confirmation!');
        return false;
    }

    // If there is an error, prevent form submission
    if (document.querySelectorAll('.error-message:not([style="display: none;"])').length > 0) {
        return false;
    }

    // If all is valid, submit the form
    return true;
}
//###################################################

//##### Displays an error message - Reset an error message #####//
function displayErrorMessage(elementId, message) {
    var errorElement = document.getElementById(elementId);
    errorElement.style.display = 'block';
    errorElement.innerHTML = message;
}

function resetErrorMessages() {
    var errorElements = document.querySelectorAll('.error-message');
    errorElements.forEach(function (element) {
        element.style.display = 'none';
        element.innerHTML = '';
    });
}
//#################################################

//##### Check form contact #####//
function validateContact() {
    var fullName = document.querySelector('#contact input[placeholder="Full name"]').value;
    var phone = document.querySelector('#contact input[placeholder="Phone"]').value;
    var address = document.querySelector('#contact input[placeholder="Address"]').value;

    // Reset error message before check validate
    resetErrorMessages();

    // Check form
    if (fullName === '') {
        displayErrorMessage('full-name-error', 'Please enter your Full Name');
    }

    if (phone === '') {
        displayErrorMessage('phone-error', 'Please enter your phone number');
    } else {
        var phonePattern = /^\d{10}$/;
        if (!phonePattern.test(phone)) {
            displayErrorMessage('phone-error', 'Your phone number invalid!');
        }
    }

    if (address === '') {
        displayErrorMessage('address-error', 'Please enter your Address');
    }

    // If there is an error, prevent form submission
    if (document.querySelectorAll('.error-message:not([style="display: none;"])').length > 0) {
        return false;
    }

    // If all is valid, submit the form
    return true;
}
//#################################################

//##### Check form pay #####//
function validatePay() {
    var fullName = document.querySelector('#pay input[placeholder="Full name"]').value;
    var email = document.querySelector('#pay input[placeholder="Email"]').value;
    var phone = document.querySelector('#pay input[placeholder="Phone"]').value;
    var deliveryAddress = document.querySelector('#pay textarea[placeholder="Delivery Address"]').value;

    // Reset error message before check validate
    resetErrorMessages();

    // Check form
    if (fullName === '') {
        displayErrorMessage('full-name-error', 'Please enter your Full name');
    }

    if (email === '') {
        displayErrorMessage('email-error', 'Please enter your Email');
    } else {
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            displayErrorMessage('email-error', 'Your Email invalid!');
        }
    }

    if (phone === '') {
        displayErrorMessage('phone-error', 'Please enter your phone number');
    } else {
        var phonePattern = /^\d{10}$/;
        if (!phonePattern.test(phone)) {
            displayErrorMessage('phone-error', 'Your phone number invalid!');
        }
    }

    if (deliveryAddress === '') {
        displayErrorMessage('delivery-address-error', 'Please enter delivery address');
    }

    // If there is an error, prevent form submission
    if (document.querySelectorAll('.error-message:not([style="display: none;"])').length > 0) {
        return false;
    }

    // If all is valid, submit the form
    return true;
}

// ########## Decrease and Increase ###############


function increasesQuantity() {
    var quantityInput = document.getElementById('value-quantity');
    var currentQuantity = parseInt(quantityInput.value);

    if (!isNaN(currentQuantity)) {
        quantityInput.value = currentQuantity + 1;
        updateTotalPrice();
    }
}

function decreasesQuantity() {
    var quantityInput = document.getElementById('value-quantity');
    var currentQuantity = parseInt(quantityInput.value);

    if (!isNaN(currentQuantity) && currentQuantity > 1) {
        quantityInput.value = currentQuantity - 1;

        updateTotalPrice();

    } else {
        alert("Quantity cannot be less than one");
    }
}

function updateTotalPrice() {
    // var price = document.getElementById("price").textContent;
    var quantityInput = document.getElementById('value-quantity').value;
    var totalprice = quantityInput * 2000;
    document.getElementById("price").innerHTML = totalprice.toFixed(3) + "vnd";


}

updateTotalPrice();


function handleClick() {
    var checkbox = document.getElementById("checkbox");
    var price = document.getElementById("price");

    if (checkbox.checked) {
        price.innerHTML = "200,000 VND";
    } else {
        price.innerHTML = "0 VND";
    }
}

document.getElementById("checkbox").addEventListener("click", handleClick);
function clickCheckbox() {
    // Assuming you have a checkbox element with id "btncheck1"
    var checkbox = document.getElementById("btncheck1");

    // Check if the checkbox is checked
    if (checkbox) {
        // Prompt the user for confirmation
        var userConfirmation = confirm("Do you want to delete Nike Air Force 1?");

        // Check the user's response
        if (userConfirmation) {
            // User clicked OK, perform the delete action
            // Add your delete logic here
            alert("Nike Air Force 1 deleted successfully!");
            // Assuming you want to hide or remove the checkbox after deletion
            checkbox.style.display = 'none';
        } else {
            // User clicked Cancel, do nothing or handle accordingly
            alert("Deletion canceled.");
            // Uncheck the checkbox if the user cancels the deletion
            checkbox.checked = false;
        }

    } else {
        alert("Checkbox not found. Please check the checkbox before attempting to delete.");
    }
}

function changeImage(image) {
    var largeImage = document.getElementById("largeImage");
    largeImage.src = image.src;
}




