<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<jsp:include page="include/head.jsp" flush="false"/>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <jsp:include page="include/main_header.jsp" flush="false"/>
  
  <jsp:include page="include/left_column.jsp" flush="false"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="padding:20px;">
      <h1>
       	 SDK 사용법
        <small>SDK Guide</small>
      </h1>
      <ol class="breadcrumb">
        <li>
         <Button type="button" class="btn btn-block btn-success btn-flat" data-toggle="modal" data-target="#registCompanyModal">SDK 다운로드</Button>
        </li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <jsp:include page="include/main_footer.jsp" flush="false"/>
</div>
<!-- ./wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
</body>
</html>
