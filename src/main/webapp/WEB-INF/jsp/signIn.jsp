<%@ page import="java.io.PrintWriter" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
</head>
<body>
<%
    Integer auth = (Integer)session.getAttribute("auth");
    if (auth != null) {
        PrintWriter pw = response.getWriter();
        if (auth == 1) {
            pw.println("<div class=\"notifyS\"><span id=\"notifyTypeS\" class=\"\"></span></div>");
        } else if (auth == -1) {
            pw.println("<div class=\"notifyF\"><span id=\"notifyTypeF\" class=\"\"></span></div>");
        }
    }
    session.setAttribute("auth", 0);
%>
<script type="text/javascript">
    (function () {
        $(".notifyS").toggleClass("active");
        $("#notifyTypeS").toggleClass("success");

        setTimeout(function(){
            $(".notifyS").removeClass("active");
            $("#notifyTypeS").removeClass("success");
        },2000);
    }());
    (function () {
        $(".notifyF").toggleClass("active");
        $("#notifyTypeF").toggleClass("failure");

        setTimeout(function(){
            $(".notifyF").removeClass("active");
            $("#notifyTypeF").removeClass("failure");
        },2000);
    }());
</script>

<form action="signIn" method="post">
    <input type="text" placeholder="Email" name="email" autocomplete="off" required/>
    <input type="password" placeholder="Password" name="password" autocomplete="off" required/>
    <button class="btn">Log in</button>
    <a href="/signUp"><h6>Create New Account</h6></a>
</form>

<footer>
    <h5>FWA Project by elaronda & heusebio</h5>
</footer>

</body>
</html>
