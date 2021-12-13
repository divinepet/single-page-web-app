<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>403 Forbidden</title>
    <link rel="stylesheet" type="text/css" href="../../css/403.css">
</head>

<body>

<div class="Content">
    <svg height="80" width="200" class="Content-item">
        <defs>
            <clipPath id="eye-iris-clip">
                <path d="M 25 40 q 75 -60 150 0 M 25 40 q 75 60 150 0" />
            </clipPath>
        </defs>
        <path id="eye-back" d="M 25 40 q 75 -60 150 0 M 25 40 q 75 60 150 0" />
        <circle cx="100" cy="40" r="23" id="eye-iris" clip-path="url(#eye-iris-clip)"/>

        <circle cx="100" cy="40" r="5" id="eye-pupil" clip-path="url(#eye-iris-clip)"/>
    </svg>

    <svg width="155" height="225" class="Content-item">
        <rect class="server-item" x="2.5" y="2.5" width="150" height="50" rx="5" ry="5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <rect x="27.5" y="27.5" width="62.5" height="1" rx="0.5" ry="0.5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <rect x="27.5" y="77.5" width="62.5" height="1" rx="0.5" ry="0.5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <circle cx="125" cy="27.5" r="10" stroke="#FFFFFF" stroke-width="5" fill="#40ff40" />

        <rect class="server-item" x="2.5" y="52.5" width="150" height="50" rx="5" ry="5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <rect x="27.5" y="127.5" width="62.5" height="1" rx="0.5" ry="0.5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <circle cx="125" cy="77.5" r="10" stroke="#FFFFFF" stroke-width="5" fill="#ff6060" />

        <rect class="server-item" x="2.5" y="102.5" width="150" height="50" rx="5" ry="5" fill="none" stroke-width="5" stroke="#FFFFFF"/>
        <circle cx="125" cy="127.5" r="10" stroke="#FFFFFF" stroke-width="5" fill="#40ff40" />

        <line x1="57.5" y1="162.5" x2="92.5" y2="162.5" stroke="gray" stroke-width="5" />

        <line x1="75" y1="152.5" x2="75" y2="175" stroke="white" stroke-width="5" />

        <line x1="0" y1="175" x2="155" y2="175" stroke="white" stroke-width="5" />

        <line x1="60" y1="162.5" x2="60" y2="180" stroke="gray" stroke-width="5" />
        <line x1="90" y1="162.5" x2="90" y2="180" stroke="gray" stroke-width="5" />

        <rect x="50" y="170" width="50" height="50" rx="5" ry="5" fill="gold"/>
        <circle cx="75" cy="190.5" r="10" fill="#cc8200"/>

        <polygon points="75,180 69,210 81,210" fill="#cc8200"/>
    </svg>
    <div class="Content-item">
        <span class="title">403</span>
        <br>
        <span class="desc">It looks like the resource you are trying to access is forbidden.</span>
        <a href="/signIn"><button class="btn">Home</button></a>
    </div>
</div>

</body>

</html>
