<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix = "c" uri = "jakarta.tags.core" %>
	
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	
	<a href="${pageContext.request.contextPath}/admin/categories/upload" class="center">upload categories</a>
<table border = "1" width="100%">
<tr>
		<th>STT</th>
		<th>Images</th>
		<th>CategoryID</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>

	</tr>
<c:forEach items="${listcate}" var="cate" varStatus="STT">
    <tr>
        <td>${STT.index + 1}</td>
        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
        <td>
            <c:choose>
                <c:when test="${fn:startsWith(cate.images, 'http://') || fn:startsWith(cate.images, 'https://')}">
                    <img height="150" width="200" src="${cate.images}" />
                </c:when>
                <c:otherwise>
                    <img height="150" width="200" src="${imgUrl}" />
                </c:otherwise>
            </c:choose>
        </td>
        <td>${cate.category_id}</td>
        <td>${cate.category_name}</td>
        <td>${cate.status}</td>
        <td>
            <a href="<c:url value='/admin/categories/edit?id=${cate.category_id}'/>" class="center">Sửa</a> | 
            <a href="<c:url value='/admin/categories/delete?id=${cate.category_id}'/>" class="center">Xóa</a>
        </td>
    </tr>
</c:forEach>

</table>