# single-page-web-app
<div>
<img src="https://img.shields.io/badge/tomcat-f7d473.svg?logo=apache-tomcat&logoColor=black"/>
<img src="https://img.shields.io/badge/postgresql-326790.svg?logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/spring-69ac3c.svg?logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/js-black.svg?logo=javascript&logoColor=efd819"/>
<img src="https://img.shields.io/badge/html-ee6329.svg?logo=html5&logoColor=white"/>
<img src="https://img.shields.io/badge/css-31a4d6.svg?logo=css3&logoColor=white"/>
</div>
</br>
<details>
  <summary><b>SCREENSHOTS</b></summary>
  <img src="https://raw.githubusercontent.com/divinepet/single-page-web-app/main/img/screenshot2.png" alt="screnshot1"></img>
  <img src="https://raw.githubusercontent.com/divinepet/single-page-web-app/main/img/screenshot1.png" alt="screnshot2"></img>
</details>


# What is it?
Website deployed on Tomcat server with Java Servlets. This is single-page website with authentication displays two tables:
- first table shows all uploaded images on a server
- second table shows login history for current account

# Getting started
Write your postgresql database credentials in ```application.properties``` <a href="https://github.com/divinepet/single-page-web-app/blob/main/src/main/webapp/WEB-INF/application.properties" target="_blank">file</a> and use this command in terminal:
```shell
mvn clean tomcat7:run
```
now site available on <b>localhost:8080</b>

# Features
### Authentication
- store session
- store account data in DB
- login form switches to register form
- dynamic check input in register form
- correct input check in login form
- existing login check
- encoding passwordes (bcrypt)
- error 403, 404 filter

### Profile
- logout button
- change avatar (jpeg, png, gif)
- displaying actual account email
- filter to access profile page for login users only

Tables description can be found in project-about
