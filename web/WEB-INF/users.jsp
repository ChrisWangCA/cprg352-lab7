<%-- 
    Document   : users
    Created on : 23-Oct-2022, 10:19:33 PM
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/users.css"%></style>
        <title>User Page</title>
    </head>
    <body>
        <h1>User Information</h1>

        <div>
            <form method="POST" action="user">
                <h3>Add User</h3>
                <label for="add_email">Email:</label>
                <input type="email" id="add_email" name="add_email" placeholder="Email" value="${add_email}" required>
                <br>

                Active:
                <label for="add_active_yes">Yes</label>
                <input type="radio" id="add_active_yes" name="add_active"  value="true" required>

                <label for="add_active_no">No</label>
                <input type="radio" id="add_active_no" name="add_active"  value="false" required>
                <br>

                <label for="add_firstname">Firstname:</label>
                <input type="text" id="add_firstname" name="add_firstname" placeholder="First Name" value="${add_firstname}" required>
                <br>
                <label for="add_lastname">Lastname:</label>
                <input type="text" id="add_lastname" name="add_lastname" placeholder="Last Name" value="${add_lastname}" required>
                <br>
                <label for="add_password">Password:</label>
                <input type="password" id="add_password" name="add_password" placeholder="Password" value="${add_password}" required>
                <br>

                <select name="add_role">
                    <c:forEach var="role" items="${roles}">
                        <option>${role.role_name}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="add">
            </form>

            ${err_msg}
            <br>
        </div>

        <div>
            <h3>Manager Users</h3>

            <table>
                <tr>
                    <th>Email</th>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.getEmail()}</td>
                        <td>${user.getFirst_name()}</td>
                        <td>${user.getLast_name()}</td>
                        <td>${user.getRole()}</td>
                        <td><a href="user?action=edit&amp;email=${user.email}">Edit</a></td>
                        <td><a href="user?action=delete&amp;email=${user.email}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <p>${message}</p>
        </div>


        <div>
            <form method="POST" action="user">

                <h3>Edit User</h3>


                <input type="email" id="edit_email" name="edit_email" placeholder="Email" value="${edit_email}" required>
                <br>

                Active:
                <label for="edit_active_yes">Yes</label>
                <input type="radio" id="edit_active_yes" name="edit_active"  value="true" required>

                <label for="edit_active_no">No</label>
                <input type="radio" id="edit_active_no" name="edit_active"  value="false" required>
                <br>

                <input type="text" id="edit_firstname" name="edit_firstname" placeholder="First Name" value="${edit_firstname}" required>
                <br>

                <input type="text" id="edit_lastname" name="edit_lastname" placeholder="Last Name" value="${edit_lastname}" required>
                <br>

                <input type="password" id="edit_password" name="edit_password" placeholder="Password" value="${edit_password}" required>
                <br>



                <select name="edit_role">
                    <c:forEach var="role" items="${roles}">
                        <option>${role.role_name}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="edit">


            </form>

            ${edit_err_msg}
        </div>



    </body>
</html>
