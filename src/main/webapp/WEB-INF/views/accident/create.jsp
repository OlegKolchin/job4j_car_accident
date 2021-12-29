<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
    <p></p>
    <div class="container" width="70%">
        <div class="input-group mb-3">
            <button type="submit" class="btn btn-primary" id="button-addon1">Сохранить</button>
            <input type="number" class="form-control" name="id" aria-describedby="button-addon1" placeholder="id">
            <input type="text" class="form-control" placeholder="Название" aria-describedby="button-addon1" name="name">
            <input type="text" class="form-control" placeholder="Описание" aria-describedby="button-addon1" name="text">
            <input type="text" class="form-control" placeholder="Адрес" aria-describedby="button-addon1" name="address">
        </div>
        <a href="<c:url value='/'/>">На главную</a>
    </div>
</form>
</body>
</html>