function checkEntity(emt, feedback) {
    const entity = document.getElementById(emt).value;
    if (!entity.length === 0) {
        document.getElementById(emt).style.borderColor = 'red';
        document.getElementById(feedback).setAttribute("style", "display: block;");
    } else {
        if (entity.trim().length === 0) {
            document.getElementById(emt).style.borderColor = 'red';
            document.getElementById(feedback).setAttribute("style", "display: block;");
        } else {
            if (!isNaN(entity)) {
                document.getElementById(emt).style.borderColor = 'red';
                document.getElementById(feedback).setAttribute("style", "display: block;");
            } else {
                document.getElementById(emt).style.borderColor = 'green';
                document.getElementById(feedback).setAttribute("style", "display: none;");
            }
        }
    }
}

function checkNumber(num, feedback) {
    const entity = document.getElementById(num).value;
    if (!entity.length === 0) {
        document.getElementById(num).style.borderColor = 'red';
        document.getElementById(feedback).setAttribute("style", "display: block;");
    } else {
        if (entity.trim().length === 0) {
            document.getElementById(num).style.borderColor = 'red';
            document.getElementById(feedback).setAttribute("style", "display: block;");
        } else {
            if (isNaN(entity)) {
                document.getElementById(num).style.borderColor = 'red';
                document.getElementById(feedback).setAttribute("style", "display: block;");
            } else {
                if (entity >= 0) {
                    document.getElementById(num).style.borderColor = 'green';
                    document.getElementById(feedback).setAttribute("style", "display: none;");
                }
            }
        }
    }
}

// function checkPrice(price, feedback) {
//     const entity = document.getElementById(price).value;
//     if (!entity.length === 0) {
//         document.getElementById(price).style.borderColor = 'red';
//         document.getElementById(feedback).setAttribute("style", "display: block;");
//     } else {
//         if (entity.trim().length === 0) {
//             document.getElementById(price).style.borderColor = 'red';
//             document.getElementById(feedback).setAttribute("style", "display: block;");
//         } else {
//             if (isNaN(entity)) {
//                 document.getElementById(price).style.borderColor = 'red';
//                 document.getElementById(feedback).setAttribute("style", "display: block;");
//             } else {
//                 if (entity >= 0) {
//                     document.getElementById(price).style.borderColor = 'green';
//                     document.getElementById(feedback).setAttribute("style", "display: none;");
//                 }
//             }
//         }
//     }
// }

// function checkBeforeSubmit() {
//     const color = document.getElementById('ProductColor').value;
//     const size = document.getElementById('ProductSize').value;
//     const img = document.getElementById('addProductIMG').value;
//     if (!color === 0) {
//         document.getElementById(num).style.borderColor = 'red';
//         document.getElementById(feedback).setAttribute("style", "display: block;");
//     }
//     if (!size === 0) {
//         document.getElementById(num).style.borderColor = 'red';
//         document.getElementById(feedback).setAttribute("style", "display: block;");
//     }
//     if (!img.length === 0) {
//         document.getElementById(num).style.borderColor ='red';
//         document.getElementById(feedback).setAttribute("style", "display: block;");
//     }
// }

//const fileUploader = document.getElementById('addProductIMG');
//
//fileUploader.addEventListener('change', (event) => {
//    const files = event.target.files;
//    console.log('files', files);
//
//    const feedback = document.getElementById('showIMG');
//    const msg = `File ${files[0].name} uploaded successfully!`;
//    feedback.innerHTML = msg;
//});
//$(function () {
//    $('.datepicker').datepicker({
//        language: "es",
//        autoclose: true,
//        format: "dd/mm/yyyy"
//    });
//});
