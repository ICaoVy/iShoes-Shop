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

const eventReturnLogin = document.querySelector('.return-login');

const registerID = document.getElementById('register-id');

const loginID = document.getElementById('login-id');

Register = () => {
    registerID.classList.toggle('register-form');
    loginID.classList.add('sign-close');
}

SignIn = () => {
    registerID.classList.toggle('register-form');
    loginID.classList.remove('sign-close');
}

//#################### Show Category Product ##############################

const luxuryHeading = document.getElementById("luxuryClick");
const luxUL = document.querySelector('.luxuryUL');
var bi_caret_1 = document.querySelector(".bi-caret-1");

luxClick = () => {
    luxUL.classList.toggle('hide-lux');
    if (bi_caret_1.classList.contains('bi-chevron-up')) {
        bi_caret_1.classList.remove('bi-chevron-up');
        bi_caret_1.classList.add('bi-chevron-down');
    } else if (bi_caret_1.classList.contains('bi-chevron-down')) {
        bi_caret_1.classList.remove('bi-chevron-down');
        bi_caret_1.classList.add('bi-chevron-up');
    }
}

const sportHeading = document.getElementById("sportClick");
const sportUL = document.querySelector('.sportUL');
var bi_caret_2 = document.querySelector(".bi-caret-2");

sportClick = () => {
    sportUL.classList.toggle('hide-sport');
    if (bi_caret_2.classList.contains('bi-chevron-up')) {
        bi_caret_2.classList.remove('bi-chevron-up');
        bi_caret_2.classList.add('bi-chevron-down');
    } else if (bi_caret_2.classList.contains('bi-chevron-down')) {
        bi_caret_2.classList.remove('bi-chevron-down');
        bi_caret_2.classList.add('bi-chevron-up');
    }
}

const sandalUL = document.querySelector('.sandalUL');
var bi_caret_3 = document.querySelector(".bi-caret-3");

SandalClick = () => {
    sandalUL.classList.toggle('hide-sandal');
    if (bi_caret_3.classList.contains('bi-chevron-up')) {
        bi_caret_3.classList.remove('bi-chevron-up');
        bi_caret_3.classList.add('bi-chevron-down');
    } else if (bi_caret_3.classList.contains('bi-chevron-down')) {
        bi_caret_3.classList.remove('bi-chevron-down');
        bi_caret_3.classList.add('bi-chevron-up');
    }
}

const priceUL = document.querySelector('.priceUL');
var bi_caret_4 = document.querySelector(".bi-caret-4");

PriceClick = () => {
    priceUL.classList.toggle('hide-price');
    if (bi_caret_4.classList.contains('bi-chevron-up')) {
        bi_caret_4.classList.remove('bi-chevron-up');
        bi_caret_4.classList.add('bi-chevron-down');
    } else if (bi_caret_4.classList.contains('bi-chevron-down')) {
        bi_caret_4.classList.remove('bi-chevron-down');
        bi_caret_4.classList.add('bi-chevron-up');
    }
}

const sizeUL = document.querySelector('.sizeUL');
var bi_caret_5 = document.querySelector(".bi-caret-5");

SizeClick = () => {
    sizeUL.classList.toggle('hide-size');
    if (bi_caret_5.classList.contains('bi-chevron-up')) {
        bi_caret_5.classList.remove('bi-chevron-up');
        bi_caret_5.classList.add('bi-chevron-down');
    } else if (bi_caret_5.classList.contains('bi-chevron-down')) {
        bi_caret_5.classList.remove('bi-chevron-down');
        bi_caret_5.classList.add('bi-chevron-up');
    }
}

const colourUL = document.querySelector('.colourUL');
var bi_caret_6 = document.querySelector(".bi-caret-6");

ColourClick = () => {
    colourUL.classList.toggle('hide-colour');
    if (bi_caret_6.classList.contains('bi-chevron-up')) {
        bi_caret_6.classList.remove('bi-chevron-up');
        bi_caret_6.classList.add('bi-chevron-down');
    } else if (bi_caret_6.classList.contains('bi-chevron-down')) {
        bi_caret_6.classList.remove('bi-chevron-down');
        bi_caret_6.classList.add('bi-chevron-up');
    }
}

const brandUL = document.querySelector('.brandUL');
var bi_caret_7 = document.querySelector(".bi-caret-7");

BrandClick = () => {
    brandUL.classList.toggle('hide-brand');
    if (bi_caret_7.classList.contains('bi-chevron-up')) {
        bi_caret_7.classList.remove('bi-chevron-up');
        bi_caret_7.classList.add('bi-chevron-down');
    } else if (bi_caret_7.classList.contains('bi-chevron-down')) {
        bi_caret_7.classList.remove('bi-chevron-down');
        bi_caret_7.classList.add('bi-chevron-up');
    }
}

var showOther = document.querySelectorAll('.show-list-other');
const checkOther = document.querySelector('.check-other');

ShowOther = (event) => {
    var targetElemment = event.target;
    var showListOther = targetElemment.querySelector('.show-list-other');
    if (showListOther) {
        showListOther.classList.add('hide-show-other');
    }
    // show.classList.add('hide-show-other');
}

HideOther = (event) => {
    var targetElement = event.target;
    var showListOther = targetElement.querySelector('.show-list-other');
    if (showListOther) {
        showListOther.classList.remove('hide-show-other');
    }
    // show.classList.remove('hide-show-other'); 
}

///////////////////////// Change Images //////////////////////////////////////////

var root = document.querySelector('.images-change').src;

ChangeImg = (newImage) => {
    var imagesChange = document.querySelector('.images-change');
    imagesChange.src = newImage;
}

ImageRoot = () => {
    document.querySelector('.images-change').src = root;
}

///////////////////////////////////////////////////////////////////////////

FiltersButton = () => {
    const left_pro = document.querySelector(".left-pro");
    const list_pro_right = document.querySelector(".list-product-right");
    const right_pro = document.querySelector('.right-pro');
    const footer = document.querySelector('.footer');
    var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    left_pro.classList.toggle('left-pro-hide');
    // if(width<=1100){
    //     if(list_pro_right.classList.contains('dnone')){
    //         list_pro_right.classList.add('col-md-12')
    //         list_pro_right.classList.remove('dnone')
    //         left_pro.classList.remove('col-md-12')
    //         left_pro.style.marginTop = '0px'
    //         footer.classList.remove('dnone')
    //     }else{
    //         list_pro_right.classList.remove('col-md-12')
    //         right_pro.classList.remove('col-md')
    //         list_pro_right.classList.add('dnone')
    //         left_pro.classList.add('col-md-9')
    //         left_pro.style.marginTop = '100px'
    //         footer.classList.add('dnone')
    //     }
    // }
    if (right_pro.classList.contains('col-md-9')) {
        right_pro.classList.remove('col-md-9');
        right_pro.classList.add('col-md-12');
        left_pro.classList.remove('col-md-3');
        right_pro.style.margin = '0px';
        // list_pro_right.classList.add('dnone')
    } else {
        right_pro.classList.remove('col-md-12');
        right_pro.classList.add('col-md-9');
        left_pro.classList.add('col-md-3');
        right_pro.style.marginLeft = '300px';
        // list_pro_right.classList.remove('dnone')
    }
}

////////////////////////////// Scroll /////////////////////////////////////////

window.addEventListener('scroll', () => {
    const left_pro = document.querySelector(".left-pro");
    var scrollPosition = window.scrollY;

    // Thực hiện các thay đổi dựa trên vị trí cuộn
    if (scrollPosition > 80) {
        left_pro.style.top = '40px';
    } else {
        left_pro.style.top = '150px';
    }
})

////////////////////////////// Product Details /////////////////////////////////////////

var rootDetails = document.querySelector('.images-main');

ChangeImgProDetails = (newImage) => {
    console.log('change');
    var imagesChangeProductDetails = document.querySelector('.images-change-product-details');
    imagesChangeProductDetails.src = newImage;
}

ImageRootProDetails = () => {
    console.log('leave');
    document.querySelector('.images-change-product-details').src = rootDetails.src;
}

////////////////////////////// Hover Product /////////////////////////////////////////
// var screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
// const left_pro = document.querySelector(".left-pro");
// const right_pro = document.querySelector('.right-pro');
// if(screenWidth <= 1000){
//     console.log('1000px')
//     if (right_pro.classList.contains('col-md-9')) {
//         right_pro.classList.remove('col-md-9')
//         right_pro.classList.add('col-md-12')
//         right_pro.style.margin = '0px';
//     }
// }else{
//     console.log('more')
//     if (right_pro.classList.contains('col-md-12')) {
//         right_pro.classList.remove('col-md-12')
//         right_pro.classList.add('col-md-9')
//     }
// }

// Hàm xử lý sự kiện thay đổi kích thước màn hình
function handleResize() {
    // Lấy kích thước mới của màn hình
    var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    var height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

    // Hiển thị thông điệp với kích thước mới
    console.log("Kích thước màn hình: " + width + " x " + height);
    const left_pro = document.querySelector(".left-pro");
    const right_pro = document.querySelector('.right-pro');
    if (width <= 1100) {
        console.log('1100px');
        right_pro.classList.remove('col-md-9');
        right_pro.classList.add('col-md-12');
        left_pro.classList.remove('col-md-3');
        left_pro.classList.add('left-pro-hide');
        right_pro.style.margin = '0px';
    } else {
        console.log('more');
        right_pro.classList.remove('col-md-12');
        right_pro.classList.add('col-md-9');
        left_pro.classList.add('col-md-3');
        left_pro.classList.remove('left-pro-hide');
        right_pro.style.marginLeft = '300px';
    }
}

// Đăng ký hàm xử lý sự kiện khi trang web được tải
window.onload = function () {
    // Gọi hàm xử lý sự kiện ban đầu
    handleResize();

    // Đăng ký hàm xử lý sự kiện cho sự kiện thay đổi kích thước màn hình
    window.addEventListener("resize", handleResize);
};

