<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>upload Category</title>
</head>
<body>
    <h1>upload Category</h1>
    
    <form action="${pageContext.request.contextPath}/admin/categories/upload" method="post" enctype="multipart/form-data">
        
        <label for="name-cate">name :</label>
        <input type="text" id="name" name="name-cate" value="" required>
        <br><br>

        <label for="image">Upload New Image:</label>
        <input type="file" id="image" name="image">
        <br><br>

        <label for="status">Status:</label><br>
    <select id="status" name="status" required>
        <option value="active">Active</option>
        <option value="inactive">Inactive</option>
    </select><br><br>

        <button type="submit">Upload Category</button>
    </form>
</body>
</html>
