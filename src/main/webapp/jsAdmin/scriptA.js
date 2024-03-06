const allSideMenu = document.querySelectorAll('#siderbar .side-menu.top li a');

allSideMenu.forEach(item=> {
    const li = item.parentElement;

    item.addEventListener('click', function () {
        allSideMenu.forEach(i=> {
            i.parentElement.classList.remove('active');
        })
        li.classList.add('active');
    })
});

//TOGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('siderbar');

menuBar.addEventListener('click', function () {
    sidebar.classList.toggle('hide');
})

if (window.innerWidth < 768) {
    sidebar.classList.add('hide');
} 


    const searchButton = document.querySelector('#content nav form .form-input button');
    const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
    const searchForm = document.querySelector('#content nav form');

    searchButton.addEventListener('click', function (e){
        if (window.innerWidth < 576) {
            e.preventDefault();
            searchForm.classList.toggle('show');
            if (searchForm.classList.contains('show')) {
                // searchButtonIcon.classList.replace('bx-search', 'bx-x');
            } else {
                // searchButtonIcon.classList.replace('bx-x', 'bx-search');
            }
        }
        
    })

    
    function changeStatus(statusID) {
        const st = document.getElementById(statusID);
        if (st.textContent === "Active") {
        st.textContent = "Ban";
        st.style.color = "red";
        }else {
            st.textContent ="Active"
            st.style.color = "black";
        }
    
}

function editProduct() {
    // Implement your edit personal info logic here
    window.location.href = 'editProduct.html';
}

/* delete product*/
function deleteProduct(productId) {
    // Assuming you have product information stored in variables
    const productCode = "ABC123";
    const productName = "Product A";
    const productImage = "product_a.jpg";
    const productPrice = "200$";
    const productSize = "40";

    // Redirect to the delete page with product information as query parameters
    window.location.href = `deleteProduct.html?code=${productCode}&name=${productName}&image=${productImage}&price=${productPrice}&size=${productSize}`;
}

function createProduct() {
    // Implement your edit personal info logic here
    window.location.href = 'create.html';
}