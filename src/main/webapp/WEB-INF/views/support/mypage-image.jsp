<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Edit my profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <%@include file="patches/authheader.jsp" %>
    <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
    <div class="container">
        <div>
            <div class="col-md-4 col-md-offset-1 offset-md-1" style="">
                <div class="feature-content">
                    <h1 class="content-title" contenteditable="true">Your data:</h1>
                    <p>${user.firstName} ${user.lastName}</p>
                    <p>Login: ${user.login}</p>
                    <p>Mail: ${user.email}</p>
                    <div class="text-left">
                        <a href="/support/mypage/data" class="btn button btn-danger">Edit data</a>
                    </div>
                    <div class="row"></div>
                </div>
            </div>
            <br>

            <div style="" class="col-md-4">
                <a href="<c:url value='/support/userimage-${user.login}' />" class="w-75 h-75"
                   style=""><img src="/support/userimage-${user.login}" class="img-thumbnail" height="180"
                                 width="180"></a>
            </div>
            <br>

            <div class="row" style="">
                <div class="form-group col-md-3">
                    <div class="row" style=""><label for="file">Upload an image</label>
                        <div class="form-group">
                            <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="file" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" name="Update image" value="Update image"
                           onclick="return confirm('Are you sure you want to update it? If no file uploaded, it will remove the image.')"
                           class="btn btn-sm btn-success"> or <a
                        href="<c:url value='/support/' />">Cancel</a>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</body>
</html>
