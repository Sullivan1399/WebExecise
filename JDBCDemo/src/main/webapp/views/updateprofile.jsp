<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Profile</title>

</head>

<body>
<form action="/updateprofile" method="post" enctype="multipart/form-data">
    <div class="main">
        <div class="container">
            <div class="col-md-12 col-sm-12">
                <h1>My account</h1>
                <div class="content-form-page">
                    <div class="row">
                        <div class="col-md-7 col-sm-7">
                            <form action="${pageContext.request.contextPath}/profile" method="post" class="form-horizontal" role="form">
                                <fieldset>
                                    <legend>Your personal details</legend>
                                    <div class="form-group">
                                        <label for="username" class="col-lg-4 control-label">Username <span
                                                class="require">*</span></label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" placeholder="Enter Username" value="${username}" id="username"
                                                   name="username" readonly required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="col-lg-4 control-label">Fullname <span
                                                class="require">*</span></label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" placeholder="Enter Fullname" value="${fullname}" id="fullname"
                                                   name="fullname" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="col-lg-4 control-label">Phone <span
                                                class="require">*</span></label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" placeholder="Enter Phone number" value="${phone}" id="phone"
                                                   name="phone" required>
                                        </div>
                                    </div>
                                </fieldset>
                                <div class="row">
                                    <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <button type="button" class="btn btn-default">Cancel</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-5 col-sm-5 pull-right">
                            <div class="form-info">
                                <h2><em>Upload</em> Avatar</h2>
                                <form action="${pageContext.request.contextPath}/uploadfile" method="post" enctype="multipart/form-data" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="file" class="col-lg-4 control-label">Choose file <span
                                                class="require">*</span></label>
                                        <div class="col-lg-8">
                                            <input type="file" class="form-control" name="file" id="file" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                                            <button type="submit" class="btn btn-primary">Upload</button>
                                        </div>
                                    </div>
                                </form>
                                <c:if test="${not empty message}">
                                    <div class="alert alert-info">${message}</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </br>
</form>

</body>
</html>