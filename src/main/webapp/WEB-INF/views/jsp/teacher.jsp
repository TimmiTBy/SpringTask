<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	Welcome teacher, ${user.name}
	<span>
        <a href="<c:url value='/add-document-${user.name}' />">Click here to upload/manage your documents</a>
    </span>
</body>
</html>