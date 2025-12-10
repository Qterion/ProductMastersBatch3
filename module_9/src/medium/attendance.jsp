<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Attendance System - Medium</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }

                form {
                    margin-bottom: 20px;
                    padding: 15px;
                    border: 1px solid #ddd;
                    border-radius: 5px;
                }

                input {
                    margin: 5px;
                    padding: 5px;
                }

                button {
                    padding: 8px 15px;
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    border-radius: 3px;
                    cursor: pointer;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                th,
                td {
                    border: 1px solid #ddd;
                    padding: 8px;
                    text-align: left;
                }

                th {
                    background-color: #4CAF50;
                    color: white;
                }

                .info {
                    background-color: #e3f2fd;
                    padding: 10px;
                    border-radius: 5px;
                    margin-bottom: 20px;
                }
            </style>
        </head>

        <body>
            <h1>Student Attendance System</h1>

            <div class="info">
                <strong>Normalized Database:</strong> Groups are stored in a separate table with foreign key
                relationship.
            </div>

            <form method="POST" action="/attendance">
                <h2>Add New Student</h2>
                <label>Name:</label>
                <input type="text" name="name" required><br>

                <label>Group Name:</label>
                <input type="text" name="groupName" required><br>

                <label>Attended:</label>
                <input type="checkbox" name="isAttended"><br>

                <button type="submit">Add Student</button>
            </form>

            <h2>Students List</h2>
            <c:if test="${not empty students}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Group Name</th>
                            <th>Attended</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.groupName}</td>
                                <td>${student.attended ? 'Yes' : 'No'}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty students}">
                <p>No students found.</p>
            </c:if>
        </body>

        </html>