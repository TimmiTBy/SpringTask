<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Upload/Download/Delete Documents</title>
</head>
<body>
    <div>
        <div>
            <div><span>List of Documents </span></div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>File Name</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${documents}" var="doc" varStatus="counter">
                        <tr>
                            <td>${counter.index + 1}</td>
                            <td>${doc.name}</td>
                            <td><a href="<c:url value='/download-document-${user.name}-${doc.id}' />">download</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <div><span>Upload New Document</span></div>
            <div>
                <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data">
                    <div>
                        <div>
                            <label for="file">Upload a document</label>
                            <div>
                                <form:input type="file" path="file" id="file"/>
                                <div class="has-error">
                                    <form:errors path="file"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div>
                            <input type="submit" value="Upload">
                        </div>
                    </div>
                     <input type="hidden" name="${_csrf.parameterName}"
                                  			value="${_csrf.token}" />
                </form:form>
                </div>
        </div>
    </div>
</body>
</html>