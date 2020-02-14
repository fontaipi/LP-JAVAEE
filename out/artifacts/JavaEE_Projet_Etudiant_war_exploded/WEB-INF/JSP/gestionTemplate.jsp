<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="content" class="java.lang.String" scope="request"/>

<html>
<head>
    <title><%= application.getInitParameter("title")%>
    </title>
    <jsp:include page="<%= application.getInitParameter(\"style\")%>"/>
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="<%= application.getInitParameter(\"sidebar\")%>"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <jsp:include page="<%= application.getInitParameter(\"topbar\")%>"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <jsp:include page="<%=content%>"/>

            </div>

        </div>

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <jsp:include page="<%= application.getInitParameter(\"pieddepage\")%>"/>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>

</div>
<jsp:include page="<%= application.getInitParameter(\"script\")%>"/>
</body>
</html>
