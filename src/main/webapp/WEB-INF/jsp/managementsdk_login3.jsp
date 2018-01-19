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
       	 2.3 로그인 예외처리
        <small>2.3 Login Exception</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    	return "managementsdk";
			}
		</code></pre>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <jsp:include page="include/main_footer.jsp" flush="false"/>
</div>
<!-- ./wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
<script type="text/javascript">
	window.onload = function(){
		 $('#navi_sdk').attr('class',"treeview menu-open active");
		 $('#managementsdk_login').attr('class',"treeview menu-open");
	};
</script>
</body>
</html>
