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

formLogin.addEventListener('submit', function (e) {
    e.preventDefault();
    checkEmtyLogin();
})

function checkEmtyLogin() {
    const emailLogin = document.querySelector('#email-login');

    const passLogin = document.querySelector('#password-login');

    if(emailLogin.value ===""){
        alert('empty');
    }

    if(passLogin.value===""){
        alert('empty');
    }
}


// ########## Decrease and Increase ###############

const dec = document.getElementById('descrease');

const inc = document.getElementById('increase');

const valueFinal = document.getElementById('value-quantity');

function descreaseQuantity(){
    let currentValue = document.getElementById('value-quantity');

    let currentValueFinal = parseInt(currentValue.value);

    if(currentValueFinal > 1){
        currentValue.value = currentValueFinal - 1;
    }


}

function increaseQuantity(){
    let currentValue = document.getElementById('value-quantity');

    let currentValueFinal = parseInt(currentValue.value);

        currentValue.value = currentValueFinal + 1;


}

const formCart = document.getElementById('form-cart-1');

// formCart.addEventListener('submit',function(e){
//     e.preventDefault();
// })

