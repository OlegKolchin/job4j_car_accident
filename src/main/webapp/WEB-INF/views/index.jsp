<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<head>
  <title>Accident</title>
</head>
<body>
<h3 style="text-align: center" >Accidents</h3>
<p class="lead" style="text-align: center">
  This is a lead paragraph. It stands out from regular paragraphs.
</p>
<div class="container" width="70%">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Text</th>
        <th scope="col">Address</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="accident" items="${accidents}">
      <tr>
        <th scope="row"></th>
        <td>${accident.name}</td>
        <td>${accident.text}</td>
        <td>${accident.address}</td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
</div>
</body>
</html>