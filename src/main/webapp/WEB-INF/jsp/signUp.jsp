<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
</head>
<body>

<form action="signUp" method="post">
    <input placeholder="Email" type="email" id="email" name="email" autocomplete="off" required/>
    <input placeholder="First name" type="text" id="first" name="firstname" autocomplete="off" required/>
    <input placeholder="Last name" type="text" id="last" name="lastname" autocomplete="off" required/>
    <input placeholder="Phone" type="tel" id="phone" name="phone" autocomplete="off" required/>
    <input placeholder="Password" type="password" name="password" autocomplete="off" required/>
    <button id="regBtn" class="btn">Register</button>
    <a href="/signIn"><h6>Already have an account?</h6></a>
</form>

<script>
    function fillInput() {
        <%
            String email = (String)session.getAttribute("email");
            String first = (String)session.getAttribute("firstname");
            String last = (String)session.getAttribute("lastname");
            String phone = (String)session.getAttribute("phone");
        %>
        document.getElementById('email').value = '<%=(session.getAttribute("email") == null) ? "" : email%>'
        document.getElementById('first').value = '<%=(session.getAttribute("firstname") == null) ? "" : first%>'
        document.getElementById('last').value = '<%=(session.getAttribute("lastname") == null) ? "" : last%>'
        document.getElementById('phone').value = '<%=(session.getAttribute("phone") == null) ? "" : phone%>'
    }
</script>


<%
    java.io.PrintWriter pw = response.getWriter();
    Integer retry = (Integer)session.getAttribute("retry");
    if (retry != null && retry == 1) {
        pw.println("<div class=\"notifyF\"><span id=\"notifyTypeF\" class=\"\"></span></div>");%>
        <script> fillInput(); </script>
<%  }
    session.setAttribute("retry", 0);
%>

<script>
    (function () {
        $(".notifyF").toggleClass("active");
        $("#notifyTypeF").toggleClass("wrong");

        setTimeout(function(){
            $(".notifyF").removeClass("active");
            $("#notifyTypeF").removeClass("wrong");
        },2500);
    }());
</script>

<footer>
    <h5>FWA Project by elaronda & heusebio</h5>
</footer>

<script type="text/javascript" src="../../js/script.js"></script>
</body>
</html>
