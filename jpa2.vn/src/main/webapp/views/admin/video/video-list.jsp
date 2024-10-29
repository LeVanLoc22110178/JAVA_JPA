<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Video List</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<!-- Optional CSS for styling -->
</head>
<body>
	<h1>Video List</h1>

	<a href="${pageContext.request.contextPath}/admin/video/upload">Upload
		New Video</a>
	<!-- Link to upload video -->
	<form action="${pageContext.request.contextPath}/admin/video/find" method="post">
        <label for="name">Tìm kiếm:</label>
        <input type="text" id="name" name="name" placeholder="Nhập từ khóa tìm kiếm...">
        <button type="submit">Tìm kiếm</button>
    </form>

	<table border="1">
		<thead>
			<tr>
				<th>Video ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Poster</th>
				<th>Views</th>
				<!-- Keep the header for views -->
				<th>Status</th>
				<th>Actions</th>
				<!-- Adjusted for action links -->
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty videos}">
				<c:forEach var="video" items="${videos}">
					<tr>
						<td>${video.videoId}</td>
						<td>${video.title}</td>
						<td>${video.description}</td>
						<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
						<td><c:choose>
								<c:when
									test="${fn:startsWith(video.poster, 'http://') || fn:startsWith(video.poster, 'https://')}">
									<img height="150" width="200" src="${video.poster}" />
								</c:when>
								<c:otherwise>
									<img height="150" width="200" src="${imgUrl}" />
								</c:otherwise>
							</c:choose></td>
						<td>
							<!-- Embed the video in the Views column --> <video width="320"
								height="240" controls>
								<source
									src="${pageContext.request.contextPath}/video/${video.views}"
									type="video/mp4">
									</video>


						</td>
						<td>${video.active == 1 ? 'Active' : 'Inactive'}</td>
						<td> <a
							href="<c:url value='/admin/video/edit?id=${video.videoId}'/>"
							class="center">Edit</a> | <a
							href="<c:url value='/admin/video/delete?id=${video.videoId}'/>"
							class="center">Delete</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty videos}">
				<tr>
					<td colspan="7">No videos found.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>
