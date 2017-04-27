<%--
  Created by IntelliJ IDEA.
  User: forrestdale
  Date: 4/25/17
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Weather Predictor</title>
  </head>
  <body>
    <form action="predict">
      <label>Enter date:</label>
      <input type="text" name="date" value="" />
      <input type="submit" name="type" value="Daily"/>
      <input type="submit" name="type" value="Weekly"/>
    </form>
  </body>
</html>
