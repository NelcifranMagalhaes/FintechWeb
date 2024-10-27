<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Login Fintech</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="icon" type="ico" sizes="16x16" href="resources/assets/favicon.ico" >
  </head>
  <body>
    <section class="vh-100">
      <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-9 col-lg-6 col-xl-5">
            <img src="${pageContext.request.contextPath}/resources/assets/logo.jpeg"
                 class="img-fluid" alt="Sample image">
          </div>
          <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <c:if test="${not empty error}">
              <span class="navbar-text text-danger" style="margin-right:10px">
                  ${error}
              </span>
            </c:if>
            <form action="login" method="post">
              <!-- Email input -->
              <div data-mdb-input-init class="form-outline mb-4">
                <input type="email" id="email-id" name="email" class="form-control form-control-lg"
                       placeholder="Enter a valid email address" />
                <label class="form-label" for="email-id">Email address</label>
              </div>

              <!-- Password input -->
              <div data-mdb-input-init class="form-outline mb-3">
                <input type="password" id="password-id" name="password" class="form-control form-control-lg"
                       placeholder="Enter password" />
                <label class="form-label" for="password-id">Password</label>
              </div>

              <div class="d-flex justify-content-between align-items-center">
                <!-- Checkbox -->
                <div class="form-check mb-0">
                  <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                  <label class="form-check-label" for="form2Example3">
                    Remember me
                  </label>
                </div>
                <a href="#!" class="text-body">Forgot password?</a>
              </div>

              <div class="text-center text-lg-start mt-4 pt-2">
                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg"
                         style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="#!"
                                                                                  class="link-danger">Register</a></p>
              </div>

            </form>
          </div>
        </div>
      </div>
      <%@include file="WEB-INF/footer.jsp"%>
      <script src="./resources/js/bootstrap.bundle.js"></script>
    </section>
  </body>
</html>