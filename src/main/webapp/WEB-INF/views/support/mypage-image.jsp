<%@include file="../commonpatches/topjsp.jsp" %>

<html>
<head>
    <title>Edit my profile</title>
    <%@include file="patches/head.jsp" %>
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
                        <a href="${SUPPORT_MYPAGE_DATA}" class="btn button btn-danger">Edit data</a>
                    </div>
                    <div class="row"></div>
                </div>
            </div>
            <br>

            <div style="" class="col-md-4">
                <a href="<c:url value='${SUPPORT_USERIMAGE_SHOW_READY}${user.login}' />" class="w-75 h-75"
                   style=""><img src="${SUPPORT_USERIMAGE_SHOW_READY}${user.login}" class="img-thumbnail" height="180"
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
                        href="<c:url value='${SUPPORT_MAIN}' />">Cancel</a>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</body>
</html>
