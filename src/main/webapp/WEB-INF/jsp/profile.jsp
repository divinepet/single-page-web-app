<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="../../css/profile.css">
</head>
<body>
<%
    String emailAttr = (String) session.getAttribute("email");
    String firstnameAttr = (String) session.getAttribute("firstname");
    String lastNameAttr = (String) session.getAttribute("lastname");
%>
<div class="profile">
    <header class="header">
        <form action="profile" method="post" class="hide-submit">
            <label>
                <input type="submit"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="white" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"></path>
                        <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"></path>
                </svg>
            </label>
        </form>
        <div class="details">
            <form action="images" method="post" class="avatar" id="avatar" enctype="multipart/form-data">
                    <div id="preview">
                        <img src="<c:out value="/FWA/images/${avatar}" />" id="avatar-image" class="avatar_img" alt="">
                    </div>
                    <div class="avatar_upload" >
                        <label class="upload_label">
                            <svg class="camera-icon" xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" viewBox="0 0 16 16">
                                <path d="M10.5 8.5a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"></path>
                                <path d="M2 4a2 2 0 0 0-2 2v6a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2h-1.172a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 9.172 2H6.828a2 2 0 0 0-1.414.586l-.828.828A2 2 0 0 1 3.172 4H2zm.5 2a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zm9 2.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0z"></path>
                            </svg>
                            <input type="file" name="file" size="100" id="upload">
                            <input type="submit" value="Upload File" id="formsubmit"/>
                        </label>
                    </div>
            </form>
            <script type="text/javascript">
                let file= document.getElementById('upload');
                let submit= document.getElementById('formsubmit');

                submit.parentNode.removeChild(submit);

                file.onchange= function() {
                    if (this.value!=='')
                        this.form.submit();
                };
            </script>
            <h1 class="heading">
                <%= firstnameAttr + " " + lastNameAttr %>
            </h1>
            <div class="location">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 20 20">
                <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"></path>
            </svg>
                <p>
                    <%= emailAttr %>
                </p>
            </div>
        </div>
    </header>

    <div class="generalTable">

        <div>
            <h1 align="center">Uploads</h1>
            <div class="tbl-header" style="float: left;margin-right:10px">
                <table>
                    <thead>
                    <tr>
                        <th>File name</th>
                        <th>Size</th>
                        <th>MIME</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: left;margin-right:10px">
                <table>
                    <tbody>
                    <c:forEach var="file" items="${fileInfo}">
                        <tr>
                            <td><a target="_blank" href='<c:out value="/FWA/images/${file.get(0)}" />'><c:url value="${file.get(0).substring(15)}" /></a></td>
                            <td><c:out value="${file.get(1)}" /></td>
                            <td><c:out value="${file.get(2)}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div>
            <h1 align="center">Analytic</h1>
            <div class="tbl-header" style="float: right;">
                <table style="float: left">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>IP</th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: right;">
                <table>
                    <tbody>
                    <c:forEach var="info" items="${logInfo}">
                        <tr>
                            <td><c:out value="${info.get(0)}" /></td>
                            <td><c:out value="${info.get(1)}" /></td>
                            <td><c:out value="${info.get(2)}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
