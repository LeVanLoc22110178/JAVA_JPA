<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>upload video</title>
</head>
<body>
    <h1>upload video</h1>
    
       <form action="${pageContext.request.contextPath}/admin/video/upload" method="post" enctype="multipart/form-data">
        
        <label for="Title">Title :</label>
        <input type="text" id="Title" name="Title" value="" required>
        <br><br>
        <label for="views">Upload Video:</label>
        <input type="file" id="views" name="views">
        <br><br>
        <label for="category_id">Category ID :</label>
        <input type="text" id="category_id" name="category_id" value="" required>
        <br><br>
<label for="description">description :</label>
        <input type="text" id="description" name="description" value="" required>
        <br><br>
        <label for="poster">Upload Video Poster:</label>
        <input type="file" id="poster" name="poster">
        <br><br>
        

        <label for="status">Active :</label><br>
    <select id="status" name="status" required>
        <option value="1">Hoat dong</option>
        <option value="2">Khong hoat dong</option>
    </select><br><br>

        <button type="submit">Upload Video</button>
    </form>
</body>
</html>
