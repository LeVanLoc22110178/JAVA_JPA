<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Category</title>
</head>
<body>
    <h1>Edit Category</h1>
    
    <form action="${pageContext.request.contextPath}/admin/categories/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${cate.category_id}">
        
        <label for="name">Category Name:</label>
        <input type="text" id="name" name="name" value="${cate.category_name}" required>
        <br><br>

        <label>Current Image:</label><br>
        <img src="${cate.images}" alt="Category Image" width="200px">
        <br><br>

        <label for="image">Upload New Image:</label>
        <input type="file" id="image" name="image">
        <br><br>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="${cate.status}" required>
        <br><br>

        <button type="submit">Update Category</button>
    </form>
</body>
</html>
