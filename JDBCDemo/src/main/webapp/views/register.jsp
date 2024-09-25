<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BEGIN CONTENT -->
<div class="col-md-12 col-sm-12">
	<h1>Create an account</h1>
	<div class="content-form-page">
		<div class="row">
			<div class="col-md-7 col-sm-7">
				<c:if test="${alert !=null}">
					<h3 class="alert alertdanger">${alert}</h3>
				</c:if>
				<form action="${pageContext.request.contextPath}/register" method="post" class="form-horizontal" role="form" >
					<fieldset>
						<legend>Your personal details</legend>
						<div class="form-group">
							<label for="username" class="col-lg-4 control-label">Username<span
								class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control"
									placeholder="Enter Username" name="username" id="username"
									required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-lg-4 control-label">Email <span
								class="require">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="Enter Email" name="email" id="email">
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-lg-4 control-label">Phone
								number <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control"
									placeholder="Enter Phone" name="phone" id="phone" required>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend>Your password</legend>
						<div class="form-group">
							<label for="password" class="col-lg-4 control-label">Password
								<span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control"
									placeholder="Enter Password" name="password" id="password"
									required>
							</div>
						</div>
						<div class="form-group">
							<label for="confirm-password" class="col-lg-4 control-label">Confirm
								password <span class="require">*</span>
							</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" 
								placeholder="Enter Confirm Password" name="confirm-password" id="confirm-password">
							</div>
						</div>

					</fieldset>
					<fieldset>
						<div class="col-lg-8 col-sm-8">
							<label for="role">Role:</label> <select id="role" name="role"
								required>
								<option value="admin">Administrator</option>
								<option value="User">Manager</option>
								<option value="Manager">User</option>
							</select>
						</div>
						<hr>
						<p>
							By creating an account you agree to our <a href="#">Terms & Privacy</a>. 
							| Already have an account? <a href="${pageContext.request.contextPath }/login">Sign in</a>.
						</p>						
					</fieldset>
					<div class="row">
						<div
							class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
							<button type="submit" class="btn btn-primary">Create an
								account</button>
							<button type="button" class="btn btn-default">Cancel</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->

