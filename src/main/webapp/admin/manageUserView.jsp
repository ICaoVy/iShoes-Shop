<%-- 
    Document   : manageUserView
    Created on : Feb 22, 2024, 8:45:42 PM
    Author     : sangn
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!--Boxicons-->
        <title>iShoes</title>
        <link rel="Website Icon" href="../images/LogoICafy.png" type="png" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!--My CSS-->
        <link rel="stylesheet" href="../cssAdmin/styleA.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMIN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>Manage User</title>
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/user.css">
        <link rel="stylesheet" href="../sang/css/addnew.css">
        <link rel="stylesheet" href="../cssAdmin/styleA.css"/>
        <link rel="stylesheet" href="../jsAdmin/scriptA.js"/>


        <!-- font-awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <!-- bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- datepicker styles -->
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker3.min.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link href=”https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css” rel=”stylesheet”
              type=”text/css” />

    <body>
        <!--SIDEBAR-->
        <section id="siderbar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">Admin</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="/AdminController">
                        <i class='bx bxs-dashboard' ></i> 
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="/AdminController/MyStore">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Store</span>
                    </a>
                </li>
                <li>
                    <a href="/AdminController/Analytics">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Analytics</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-chat' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <li class="active">
                    <a href="/AdminController/ManegerUser">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Manage User</span>
                    </a>
                </li>
                <li>
                    <a href="/AdminController/ListOrder">
                        <i class='bx bx-list-check' ></i>
                        <span class="text">List orders</span>
                    </a>
                </li>
                <li>
                    <a href="./importproduct.jsp">
                        <i class='bx bxs-plus-circle' ></i>
                        <span class="text">Import product</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu top">
                <li>
                    <a href="#">
                        <i class='bx bxs-cog' ></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="/LogoutController" class="logout">
                        <i class='bx bx-log-out' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>

        <section id="content">
            <!--NAVBAR-->
            <nav>
                <i class='bx bx-menu'></i>
                <a href="#" class="nav-link">Categories</a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" name="" id="" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                    <span class="num">8</span>
                </a>
                <a href="#">
                    <img src="" alt="">
                </a>
            </nav>
            <div class="container-fluid">
                <div class="table-title">
                    <div class="row justify-content-around">
                        <div class="col-md-auto">
                            <h2 id="titlemanage">Manage Customer</h2>
                        </div>
                        <div class="col-sm-3">
                            <a id="AddStaffBtn" class="btn btn-success" data-toggle="modal" data-target="#addStaffModal"
                               style="background-color: #007bff; border-color: #007bff; position: absolute; top: 4px; right: 140px; display: none">
                                <span>
                                    ADD NEW
                                </span>
                            </a>
                        </div>
                        <div class="col-sm-3">
                            <a id="showCustomer" class="btn btn-success"
                               style="background-color: #dc3545; border-color: #dc3545;  position: absolute; top: 4px; right: 125px;">
                                <span>
                                    CUSTOMER
                                </span>
                            </a>
                            <a id="showStaff" class="btn btn-success"
                               style="background-color: #46C17F; border-color: #46C17F;  position: absolute; top: 4px; right: 25px;">
                                <span>
                                    STAFF
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
                <br>
                <!--               
                <!--Form management of Customer Account-->
                <div id="viewManageCustomer">
                    <form action="manageUser" method="POST">
                        <table id="CusForm" class="table table-striped" style="width:100%">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Full Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Create at</th>
                                    <th>Deleted</th>
                                    <th>Detail</th>
                                    <th>Tools</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listCus}" var="c" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>

                                        <td>${c.cus_fullname}</td>

                                        <td>${c.cus_email}</td>

                                        <td>${c.cus_phone}</td>

                                        <td>${c.cus_create_at}</td>

                                        <td>${c.cus_deleted}</td>

                                        <td>
                                            <a data-toggle="modal" data-target="#viewAllCustomerModal${c.cus_id}">
                                                <i id="viewCustomerBtn${c.cus_id}" class="fa fa-info-circle" title="View more info"></i>
                                            </a>
                                        </td>

                                        <td>
<!--                                            <a data-toggle="modal" data-target="#editCustomerModal${c.cus_id}">
                                                <i id="editCustomerBtn${c.cus_id}" class="fa fa-pencil-square-o col-3" title="Edit"  style="display: none"></i>
                                            </a>-->
                                            <c:if test="${c.cus_deleted!=1}">
                                                <a data-toggle="modal" data-target="#deleteCustomerModal${c.cus_id}">
                                                    <i id="deleteCustomerBtn${c.cus_id}" class="fa fa-trash col-3" title="Delete"></i>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <!--End Form-->

                <!--Form management of Staff Account-->
                <div id="viewManageStaff" style="display: none">
                    <form action="viewStaf" method="POST">
                        <table id="StaffForm" class="table table-striped" style="width:100%">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Full Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Role</th>
                                    <th>Create at</th>
                                    <th>Deleted</th>
                                    <th>Detail</th>
                                    <th>Tools</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listStaff}" var="st" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>

                                        <td>${st.staff_fullname}</td>

                                        <td>${st.staff_email}</td>

                                        <td>${st.staff_phone}</td>

                                        <td>
                                            <c:forEach items="${listRole}" var="r">
                                                <c:if test="${st.role_id == r.role_id}">${r.role_name}</c:if>
                                            </c:forEach>
                                        </td>

                                        <td>${st.staff_create_at}</td>

                                        <td>${st.staff_deleted}</td>

                                        <td>
                                            <a data-toggle="modal" data-target="#viewAllStaffModal${st.staff_id}">
                                                <i id="viewStaffBtn${st.staff_id}" class="fa fa-info-circle" title="View more info"></i>
                                            </a>
                                        </td>

                                        <td>
                                            <a data-toggle="modal" data-target="#editStaffModal${st.staff_id}">
                                                <i id="editStaffBtn${st.staff_id}" class="fa fa-pencil-square-o col-3" title="Edit"></i>
                                            </a>
                                            <a data-toggle="modal" data-target="#deleteStaffModal${st.staff_id}">
                                                <i id="deleteStaffBtn${st.staff_id}" class="fa fa-trash col-3" title="Delete"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <!--End Form-->
            </div>

            <!--All modal for manage Staff Account-->
            <div id="viewCustomerModal">
                <c:forEach items="${listCus}" var="c" varStatus="i">

                    <!--Modal Edit info of Customer-->
<!--                    <div class="modal fade" id="editCustomerModal${c.cus_id}" tabindex="-1" role="dialog"
                        aria-labelledby="editCustomerModalLabel" aria-hidden="true">
                       <div class="modal-dialog" role="document">
                           <div class="modal-content">
                               <div class="modal-header">
                                   <h5 class="modal-title" id="editCustomerModalLabel">Edit info of Customer</h5>
                               </div>
                               <div class="modal-body">
                                   <form id="editAnswerForm${i.index+1}" action="insertTest_EditAnswer" method="post">
                                       <div class="input-group mb-3">
                                           <div class="input-group-prepend">
                                               <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name</span>
                                           </div>
                                           <input type="text" class="form-control" aria-label="Sizing fullname input"
                                                  aria-describedby="inputGroup-sizing-fullname"
                                                  oninput="checkEntity('cus_fullname','validationAddCustomerFeedback_Fullname')"
                                                  id="cus_fullname" placeholder="Please enter Full name" value="${c.cus_fullname}" required>
                                           <div id="validationAddCustomerFeedback_Fullname" class="invalid-feedback">
                                               Please enter a Full name of that Customer
                                           </div>
                                       </div>

                                       <div class="input-group mb-3">
                                           <div class="input-group-prepend">
                                               <span class="input-group-text" id="inputGroup-sizing-email">Email</span>
                                           </div>
                                           <input type="text" class="form-control" aria-label="Sizing email input"
                                                  aria-describedby="inputGroup-sizing-email"
                                                  oninput="checkEntity('cus_email','validationAddCustomerFeedback_Email')"
                                                  id="cus_email" placeholder="Please enter Email" value="${c.cus_email}" required>
                                           <div id="validationAddCustomerFeedback_Email" class="invalid-feedback">
                                               Please enter a Email of that Customer
                                           </div>
                                       </div>

                                       <div class="input-group mb-3">
                                           <div class="input-group-prepend">
                                               <span class="input-group-text" id="inputGroup-sizing-phone">Phone</span>
                                           </div>
                                           <input type="text" class="form-control" aria-label="Sizing phone input"
                                                  aria-describedby="inputGroup-sizing-phone"
                                                  oninput="checkNumber('cus_phone','validationAddCustomerFeedback_Phone')"
                                                  id="cus_phone" placeholder="Please enter Phone" value="${c.cus_phone}" required>
                                           <div id="validationAddCustomerFeedback_Phone" class="invalid-feedback">
                                               Please enter a Phone of that Customer
                                           </div>
                                       </div>

                                       <div class="input-group mb-3">
                                           <div class="input-group-prepend">
                                               <span class="input-group-text" id="inputGroup-sizing-birthday">Birthday</span>
                                           </div>
                                           <input type="text" class="form-control datepicker" aria-label="Sizing birthday input"
                                                  aria-describedby="inputGroup-sizing-birthday"
                                                  oninput="checkNumber('cus_birthday','validationAddCustomerFeedback_Birthday')"
                                                  id="cus_birthday" placeholder="Please enter Birthday"
                                                  data-date-format="yyyy/MM/dd" value="${c.cus_birthday}" required>
                                           <div id="validationAddCustomerFeedback_Birthday" class="invalid-feedback">
                                               Please enter a Birthday of that Customer
                                           </div>
                                       </div>

                                       <div class="input-group">
                                           <div class="input-group-prepend">
                                               <span class="input-group-text" id="inputGroup-sizing-address">Address</span>
                                           </div>
                                           <textarea class="form-control" aria-describedby="inputGroup-sizing-address"
                                                     oninput="checkEntity('cus_address','validationAddCustomerFeedback_Address')"
                                                     id="cus_address" placeholder="Please enter Address" required>${c.cus_address}</textarea>
                                           <div id="validationAddCustomerFeedback_Address" class="invalid-feedback">
                                               Please enter a Description about that shoe
                                           </div>
                                       </div>
                                   </form>
                               </div>
                               <div class="modal-footer">
                                   <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Hủy">
                                   <input type="submit" form="editAnswerForm${i.index+1}" name="submit" class="btn btn-primary"
                                          value="Cập Nhật">
                               </div>
                           </div>
                       </div>
                   </div>-->

                    <!-- Modal Delete Customer-->
                    <div class="modal fade" id="deleteCustomerModal${c.cus_id}" tabindex="-1" role="dialog"
                         aria-labelledby="deleteCustomerModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteCustomerModalLabel">Delete Customer Account </h5>
                                </div>
                                <div class="modal-body">
                                    <form id="deleteCustomerForm${i.index+1}" action="ManegerUser" method="post">
                                        <div class="form-group">
                                            <label>Are you sure you want to delete this customer's account?</label>
                                            <input name="cus_id" value="${c.cus_id}" style="display: none" />
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                    <input id="deleteCustomerForm${c.cus_id}btn" class="btn btn-primary" type="submit" form="deleteCustomerForm${i.index+1}" name="delete-btn" value="DELETE">
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Modal View Detail all info of Customer -->
                    <div class="modal fade" id="viewAllCustomerModal${c.cus_id}" tabindex="-1" role="dialog"
                         aria-labelledby="viewAllCustomerModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="viewAllCustomerModalLabel">More Information of Customer</h5>
                                </div>
                                <div class="modal-body">
                                    <form id="ViewAllCustomerForm${i.index+1}">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name</span>
                                            </div>
                                            <input type="text" class="form-control" aria-label="Sizing viewAllCustomer fullname input"
                                                   aria-describedby="inputGroup-sizing-fullname"
                                                   id="cus_fullname" value="${c.cus_fullname}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-email">Email</span>
                                            </div>
                                            <input type="text" class="form-control" aria-label="Sizing viewAllCustomer email input"
                                                   aria-describedby="inputGroup-sizing-email"
                                                   id="cus_email" value="${c.cus_email}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-phone">Phone</span>
                                            </div>
                                            <input type="text" class="form-control" aria-label="Sizing viewAllCustomer phone input"
                                                   aria-describedby="inputGroup-sizing-phone"
                                                   id="cus_phone" value="${c.cus_phone}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-birthday">Birthday</span>
                                            </div>
                                            <input type="text" class="form-control" aria-label="Sizing viewAllCustomer birthday input"
                                                   aria-describedby="inputGroup-sizing-birthday"
                                                   id="cus_birthday" value="${c.cus_birthday}" readonly>
                                        </div>

                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-address">Address</span>
                                            </div>
                                            <textarea class="form-control" aria-describedby="inputGroup-sizing-address"
                                                      id="cus_address" readonly>${c.cus_address}</textarea>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!--Modal Add new Customer-->
                <!--                <div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="addCustomerModalLabel">Add new Customer</h5>
                                            </div>
                                            <div class="modal-body">
                                                <form id="addAnswerForm" action="insertTest_CreateAnswer" method="post">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name</span>
                                                        </div>
                                                        <input type="text" class="form-control" aria-label="Sizing addCustomer fullname input"
                                                               aria-describedby="inputGroup-sizing-fullname"
                                                               oninput="checkEntity('cus_fullname','validationAddCustomerFeedback_Fullname')"
                                                               id="cus_fullname" placeholder="Please enter Full name" required>
                                                        <div id="validationAddCustomerFeedback_Fullname" class="invalid-feedback">
                                                            Please enter a Full name of that Customer
                                                        </div>
                                                    </div>
                
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="inputGroup-sizing-email">Email</span>
                                                        </div>
                                                        <input type="text" class="form-control" aria-label="Sizing addCustomer email input"
                                                               aria-describedby="inputGroup-sizing-email"
                                                               oninput="checkEntity('cus_email','validationAddCustomerFeedback_Email')"
                                                               id="cus_email" placeholder="Please enter Email" required>
                                                        <div id="validationAddCustomerFeedback_Email" class="invalid-feedback">
                                                            Please enter a Email of that Customer
                                                        </div>
                                                    </div>
                
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="inputGroup-sizing-phone">Phone</span>
                                                        </div>
                                                        <input type="text" class="form-control" aria-label="Sizing addCustomer phone input"
                                                               aria-describedby="inputGroup-sizing-phone"
                                                               oninput="checkNumber('cus_phone','validationAddCustomerFeedback_Phone')"
                                                               id="cus_phone" placeholder="Please enter Phone" required>
                                                        <div id="validationAddCustomerFeedback_Phone" class="invalid-feedback">
                                                            Please enter a Phone of that Customer
                                                        </div>
                                                    </div>
                
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="inputGroup-sizing-birthday">Birthday</span>
                                                        </div>
                                                        <input type="text" class="form-control datepicker" aria-label="Sizing addCustomer birthday input"
                                                               aria-describedby="inputGroup-sizing-birthday"
                                                               oninput="checkNumber('cus_birthday','validationAddCustomerFeedback_Birthday')"
                                                               id="cus_birthday" placeholder="Please enter Birthday" data-date-format="yyyy/MM/dd" required>
                                                        <div id="validationAddCustomerFeedback_Birthday" class="invalid-feedback">
                                                            Please enter a Birthday of that Customer
                                                        </div>
                                                    </div>
                
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="inputGroup-sizing-address">Address</span>
                                                        </div>
                                                        <textarea class="form-control" aria-describedby="inputGroup-sizing-address"
                                                                  oninput="checkEntity('cus_address','validationAddCustomerFeedback_Address')"
                                                                  id="cus_address" placeholder="Please enter Address" required></textarea>
                                                        <div id="validationAddCustomerFeedback_Address" class="invalid-feedback">
                                                            Please enter a Description about that shoe
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                
                                            <div class="modal-footer">
                                                <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                                <input type="submit" form="addAnswerForm" name="submit" class="btn btn-primary" value="Add new">
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
            </div>
            <!--End info Customer Modal-->

            <!--All modal for manage Staff Account-->
            <div id="viewStaffModal">
                <c:forEach items="${listStaff1}" var="st" varStatus="i">

                    <!--Modal Edit info of Staff-->
                    <div class="modal fade" id="editStaffModal${st.staff_id}" tabindex="-1" role="dialog" aria-labelledby="editStaffModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editStaffModalLabel">Edit info of Staff</h5>
                                </div>
                                <div class="modal-body">
                                    <form id="editStaffForm${i.index+1}" action="ManegerUser" method="post">
                                        <div class="form-group">
                                            <input name="editStaff_id" value="${st.staff_id}" style="display: none">
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name</span>
                                            </div>
                                            <input type="text" class="form-control" id="editStaff_fullname" name="editStaff_fullname"
                                                   aria-label="Sizing editStaff fullname input"
                                                   aria-describedby="inputGroup-sizing-fullname"
                                                   oninput="checkEntity('editStaff_fullname','validationEditStaffFeedback_Fullname')"
                                                   placeholder="Please enter Full name" value="${st.staff_fullname}" required>
                                            <div id="validationEditStaffFeedback_Fullname" class="invalid-feedback">
                                                Please enter a Full name of that Staff
                                            </div>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-email">Email</span>
                                            </div>
                                            <input type="text" class="form-control" id="editStaff_email" name="editStaff_email"
                                                   aria-label="Sizing editStaff email input"
                                                   aria-describedby="inputGroup-sizing-email"
                                                   oninput="checkEmail('editStaff_email','validationEditStaffFeedback_Email')"
                                                   placeholder="Please enter Email" value="${st.staff_email}" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text">@ishoes.vn</span>
                                            </div>
                                            <div id="validationEditStaffFeedback_Email" class="invalid-feedback">
                                                Please enter a Email of that Staff
                                            </div>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-phone">Phone</span>
                                            </div>
                                            <input type="text" class="form-control" id="editStaff_phone" name="editStaff_phone"
                                                   aria-label="Sizing editStaff phone input"
                                                   aria-describedby="inputGroup-sizing-phone"
                                                   oninput="checkPhone('editStaff_phone','validationEditStaffFeedback_Phone')"
                                                   placeholder="Please enter Phone" value="${st.staff_phone}" required>
                                            <div id="validationEditStaffFeedback_Phone" class="invalid-feedback">
                                                Please enter a Phone of that Staff
                                            </div>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-password">Password</span>
                                            </div>
                                            <input type="password" class="form-control" id="editStaff_password" name="editStaff_password"
                                                   aria-label="Sizing editStaff password input"
                                                   aria-describedby="inputGroup-sizing-password"
                                                   oninput="checkPass('editStaff_password','validationEditStaffFeedback_Password')"
                                                   placeholder="Please enter Password" value="${st.staff_password}" required>
                                            <div id="validationEditStaffFeedback_Password" class="invalid-feedback">
                                                Please enter a Password of that Staff
                                            </div>
                                        </div>

                                        <!--                                        <div class="input-group mb-3">
                                                                                    <div class="input-group-prepend">
                                                                                        <span class="input-group-text" id="inputGroup-sizing-Role">Role</span>
                                                                                    </div>
                                                                                    <select class="custom-select form-control" id="editStaff_RoleAccount" name="editStaff_RoleAccount"
                                                                                            aria-describedby="inputGroup-sizing-Role" required>
                                                                                            <option selected disabled value="">Role Account is</option>
                                        <c:forEach items="${listRole}" var="r">
                                            <option value="${st.role_id}" ${st.role_id == r.role_id?"selected":""}>${r.role_name}</option>
                                        </c:forEach>
                                    </select>
                                <div id="validationEditStaffFeedback_RoleAccount" class="invalid-feedback">
                                    Please Choose Role Account
                                </div>
                            </div>-->

                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-address">Address</span>
                                            </div>
                                            <textarea class="form-control" id="editStaff_address" name="editStaff_address"
                                                      aria-describedby="inputGroup-sizing-address"
                                                      oninput="checkEntity('editStaff_address','validationEditStaffFeedback_Address')"
                                                      placeholder="Please enter Address" required>${st.staff_address}</textarea>
                                            <div id="validationEditStaffFeedback_Address" class="invalid-feedback">
                                                Please enter a Address of that Staff is valid
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                    <input id="editStaffForm${st.staff_id}btn" type="submit" form="editStaffForm${i.index+1}" name="editStaff-btn" class="btn btn-primary" value="UPDATE NOW">
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Modal Delete Staff-->
<!--                    <div class="modal fade" id="deleteStaffModal${c.cus_id}" tabindex="-1" role="dialog"
                        aria-labelledby="deleteCustomerModalLabel" aria-hidden="true">
                       <div class="modal-dialog" role="document">
                           <div class="modal-content">
                               <div class="modal-header">
                                   <h5 class="modal-title" id="deleteCustomerModalLabel">Delete Customer Account </h5>
                               </div>
                               <div class="modal-body">
                                   <form id="deleteAnswerForm${i.index+1}"
                                         action="insertTest_DeleteAnswer" method="post">
                                       <div class="form-group">
                                           <label>Are you sure you want to delete this customer's account?</label>
                                           <input name="cus_id" value="${c.cus_id}" style="display: none" />
                                       </div>
                                   </form>
                               </div>
                               <div class="modal-footer">
                                   <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Hủy">
                                   <input type="submit" form="deleteAnswerForm${i.index+1}" name="submit"
                                          class="btn btn-primary" value="Xóa">
                               </div>
                           </div>
                       </div>
                   </div>-->

                    <!--Modal View Detail all info of Staff--> 
                    <div class="modal fade" id="viewAllStaffModal${st.staff_id}" tabindex="-1" role="dialog"
                         aria-labelledby="viewAllStaffModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="viewAllStaffModalLabel">More Information of Customer</h5>
                                </div>
                                <div class="modal-body">
                                    <form id="viewAllStaffForm${i.index+1}">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name</span>
                                            </div>
                                            <input type="text" class="form-control" aria-describedby="inputGroup-sizing-fullname"
                                                   value="${st.staff_fullname}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-email">Email</span>
                                            </div>
                                            <input type="text" class="form-control" aria-describedby="inputGroup-sizing-email"
                                                   value="${st.staff_email}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-phone">Phone</span>
                                            </div>
                                            <input type="text" class="form-control" aria-describedby="inputGroup-sizing-phone"
                                                   value="${st.staff_phone}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-password">Password</span>
                                            </div>
                                            <input type="password" class="form-control" aria-describedby="inputGroup-sizing-password"
                                                   value="${st.staff_password}" readonly>
                                        </div>

                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-Role">Role</span>
                                            </div>
                                            <c:forEach items="${listRole}" var="r">
                                                <c:if test="${st.role_id == r.role_id}">
                                                    <input type="text" class="form-control" aria-describedby="inputGroup-sizing-phone" value="${r.role_name}" readonly>
                                                </c:if>
                                            </c:forEach>
                                        </div>

                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroup-sizing-address">Address</span>
                                            </div>
                                            <textarea class="form-control" readonly>${st.staff_address}</textarea>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Modal Add new Staff Account-->
                <div class="modal fade" id="addStaffModal" tabindex="-1" role="dialog" aria-labelledby="addStaffModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addStaffModalLabel">Add new Staff</h5>
                            </div>
                            <div class="modal-body">
                                <form id="addnewStaffForm" action="ManegerUser" method="post">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroup-sizing-fullname">Full Name:</span>
                                        </div>
                                        <input type="text" class="form-control" id="addStaff_fullname" name="addStaff_fullname"
                                               aria-label="Sizing addStaff fullname input"
                                               aria-describedby="inputGroup-sizing-fullname"
                                               oninput="checkEntity('addStaff_fullname','validationAddStaffFeedback_Fullname')"
                                               placeholder="Please enter Full name" required>
                                        <div id="validationAddStaffFeedback_Fullname" class="invalid-feedback">
                                            Please enter a Full name of that Staff
                                        </div>
                                    </div>

                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroup-sizing-email">Email:</span>
                                        </div>
                                        <input type="text" class="form-control" id="addStaff_email" name="addStaff_email"
                                               aria-label="Sizing addStaff email input"
                                               aria-describedby="inputGroup-sizing-email"
                                               oninput="checkEmail('addStaff_email','validationAddStaffFeedback_Email')"
                                               placeholder="Please enter Email" required>
                                        <div class="input-group-append">
                                            <span class="input-group-text">@ishoes.vn</span>
                                        </div>
                                        <div id="validationAddStaffFeedback_Email" class="invalid-feedback">
                                            Please enter a Email of that Staff
                                        </div>
                                    </div>

                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroup-sizing-phone">Phone:</span>
                                        </div>
                                        <input type="text" class="form-control" id="addStaff_phone" name="addStaff_phone"
                                               aria-label="Sizing addStaff phone input"
                                               aria-describedby="inputGroup-sizing-phone"
                                               oninput="checkPhone('addStaff_phone','validationAddStaffFeedback_Phone')"
                                               placeholder="Please enter Phone" required>
                                        <div id="validationAddStaffFeedback_Phone" class="invalid-feedback">
                                            Please enter a Phone of that Staff
                                        </div>
                                    </div>

                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroup-sizing-password">Password:</span>
                                        </div>
                                        <input type="password" class="form-control" id="addStaff_password" name="addStaff_password"
                                               aria-label="Sizing addStaff password input"
                                               aria-describedby="inputGroup-sizing-password"
                                               oninput="checkPass('addStaff_password','validationAddStafffFeedback_Password')"
                                               placeholder="Please enter Password" value="${st.staff_password}" required>
                                        <div id="validationAddStaffFeedback_Password" class="invalid-feedback">
                                            Please enter a Password of that Staff
                                        </div>
                                    </div>

                                    <!--                                    <div class="input-group mb-3">
                                                                            <div class="input-group-prepend">
                                                                                <span class="input-group-text" id="inputGroup-sizing-Role">Role:</span>
                                                                            </div>
                                                                            <select class="custom-select form-control" id="addStaff_RoleAccount" name="addStaff_RoleAccount"
                                                                                    aria-describedby="inputGroup-sizing-Role" required>
                                                                                <option selected disabled value="">Role Account is</option>
                                    <c:forEach items="${listRole}" var="r">
                                        <option value="${r.role_id}">${r.role_name}</option>
                                    </c:forEach>
                                </select>
                                <div id="validationAddStaffFeedback_ChooseColor" class="invalid-feedback">
                                    Please Choose Role Account
                                </div>
                            </div>-->

                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroup-sizing-address">Address:</span>
                                        </div>
                                        <textarea class="form-control" id="addStaff_address" name="addStaff_address"
                                                  aria-describedby="inputGroup-sizing-address"
                                                  oninput="checkEntity('addStaff_address','validationAddStaffFeedback_Address')"
                                                  placeholder="Please enter Address" required></textarea>
                                        <div id="validationAddStaffFeedback_Address" class="invalid-feedback">
                                            Please enter a Address of that Staff is valid
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                <input id="addnewStaffFormbtn" class="btn btn-primary" type="submit" form="addnewStaffForm" name="addStaffbtn" value="ADD NEW">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--End info Staff Modal-->                          

        </section>

        <script src="https://code.jquery.com/jquery-3.6.1.min.js"
        integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

        <!--    <script>
            $(function () {
                $('.datepicker').datepicker({
                  language: "en",
                  autoclose: true,
                  format: "yyyy/MM/dd"
                });
            });
        </script> 
        <!-- <script>
            $('.datepicker').datepicker({
                format: 'yyyy/MM/dd',
                startDate: '-3d'
            }); 
        </script> -->
        <script>
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

            function checkPhone(phone, feedback) {
                var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
                const entity = document.getElementById(phone).value;
                if (!entity.length === 0) {
                    document.getElementById(phone).style.borderColor = 'red';
                    document.getElementById(feedback).setAttribute("style", "display: block;");
                } else {
                    if (entity.trim().length === 0) {
                        document.getElementById(phone).style.borderColor = 'red';
                        document.getElementById(feedback).setAttribute("style", "display: block;");
                    } else {
                        if (isNaN(entity)) {
                            document.getElementById(phone).style.borderColor = 'red';
                            document.getElementById(feedback).setAttribute("style", "display: block;");
                        } else {
                            if (entity.match(phoneno)) {
                                document.getElementById(phone).style.borderColor = 'green';
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
            function checkEmail(email, feedback) {
                var emailValid = /^[a-zA-Z0-9]+$/;
                const entity = document.getElementById(email).value;
                if (!entity.length === 0) {
                    document.getElementById(email).style.borderColor = 'red';
                    document.getElementById(feedback).setAttribute("style", "display: block;");
                } else {
                    if (entity.trim().length === 0) {
                        document.getElementById(email).style.borderColor = 'red';
                        document.getElementById(feedback).setAttribute("style", "display: block;");
                    } else {
                        if (!isNaN(entity)) {
                            document.getElementById(email).style.borderColor = 'red';
                            document.getElementById(feedback).setAttribute("style", "display: block;");
                        } else
                        if (entity.match(emailValid)) {
                            document.getElementById(email).style.borderColor = 'green';
                            document.getElementById(feedback).setAttribute("style", "display: none;");
                        }
                    }
                }
            }
            function checkPass(pass, feedback) {
                var passValid = /^[a-zA-Z0-9]+$/;
                const entity = document.getElementById(pass).value;
                if (!entity.length === 0) {
                    document.getElementById(pass).style.borderColor = 'red';
                    document.getElementById(feedback).setAttribute("style", "display: block;");
                } else {
                    if (entity.trim().length === 0) {
                        document.getElementById(pass).style.borderColor = 'red';
                        document.getElementById(feedback).setAttribute("style", "display: block;");
                    } else {
                        if (!isNaN(entity)) {
                            document.getElementById(pass).style.borderColor = 'red';
                            document.getElementById(feedback).setAttribute("style", "display: block;");
                        } else
                        if (entity.match(passValid) && entity.length >= 8) {
                            document.getElementById(pass).style.borderColor = 'green';
                            document.getElementById(feedback).setAttribute("style", "display: none;");
                        }
                    }
                }
            }
        </script>
        <script language="javascript">
            document.getElementById("showCustomer").onclick = function () {
                document.getElementById("viewManageCustomer").style.display = 'block';
                document.getElementById("viewManageStaff").style.display = 'none';
                document.getElementById("AddStaffBtn").style.display = 'none';
                document.getElementById("titlemanage").innerHTML = "Manage Customer";
            };

            document.getElementById("showStaff").onclick = function () {
                document.getElementById("viewManageStaff").style.display = 'block';
                document.getElementById("viewManageCustomer").style.display = 'none';
                document.getElementById("AddStaffBtn").style.display = 'block';
                document.getElementById("titlemanage").innerHTML = "Manage Staff";
            };
        </script>

        <script src="../js/script.js"></script>
        <script src="../sang/js/addproduct.js"></script>

        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

        <!-- Datepicker -->
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
                integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
        crossorigin="anonymous"></script>

    </body>

</html>