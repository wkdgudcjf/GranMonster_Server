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
    <section class="content-header" 
   		style="padding:20px; 
    	background-color:#fff;">
      <h1>
       	 1. SDK 사용법
        <small>SDK Guide</small>
      </h1>
      <ol class="breadcrumb">
        <li>

         <Button type="button" class="btn btn-block btn-success btn-flat" onClick="downloadSDK()">SDK 다운로드</Button>
        </li>
      </ol>
      <br/>
      <h5>A. <a href="#sdk_import">Import 하기</a></h5>
    </section>
    <!-- Main content -->
    <section class="content">
    <div id="sdk_import">
    <h1>A. Import 하기</h1>
    <br/>
	<h5> <b> SDK를 다운받은 후 압축을 풀고 Package파일을 더블클릭합니다.</b></h5>
	<br/>
	<div style="text-align:center;">
		<img src="/sdk_image/sdk_import.png" style="max-width: 1000px; max-height: 1000px;">
		<h3>그림 - Unity Import 화면</h3>
	</div>
	<br/>
	<h5> <b>위와 같이 Import버튼을 클릭하면 SDK Import가 완료됩니다.</b></h5>
	</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <div id="layer_QuickMenu">
        <!-- Form Element sizes -->
   <div class="box box-success" style="margin:0px;">
     <div class="box-header with-border">
       <h3 class="box-title">Quick Menu</h3>
     </div>
     <div class="box-body">
       <h5>A. <a href="#sdk_import">Import 하기</a></h5>
     </div>
     <!-- /.box-body -->
   </div>
   <!-- /.box -->
  </div>
  <jsp:include page="include/main_footer.jsp" flush="false"/>
</div>
<!-- ./wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
<script type="text/javascript">
	window.onload = function(){
		 $('#navi_sdk').attr('class',"treeview menu-open active");
	};
	function downloadSDK()
	{
		 var form = document.createElement('form');
		 var objs;
		 objs = document.createElement('input');
		 objs.setAttribute('type', 'hidden');
		 form.appendChild(objs);
		 form.setAttribute('method', 'post');
		 form.setAttribute('action', "/downloadSDK");
		 document.body.appendChild(form);
		 form.submit();
	}
</script>
</body>
</html>
