<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<jsp:include page="include/head.jsp" flush="false"/>
<style type="text/css">
    #layer_QuickMenu
    {
        width:230px;
        color: #555;
        font-size:13px;
        position:fixed;
        z-index:999;
        bottom:0px;
        left:0px;
        -webkit-box-shadow: 0 1px 2px 0 #777;
        box-shadow: 0 1px 2px 0 #777;
        background-color:#fff;
    }
</style>
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
       	 SDK 사용법
        <small>SDK Guide</small>
      </h1>
      <ol class="breadcrumb">
        <li>
         <Button type="button" class="btn btn-block btn-success btn-flat" data-toggle="modal" data-target="#registCompanyModal">SDK 다운로드</Button>
        </li>
      </ol>
      <br/>
      <h5>1. <a href="#ex1">ex1로 이동.</a></h5>
      <h5>2. <a href="#ex2">ex2로 이동.</a></h5>
      <h5>3. <a href="#ex3">ex3로 이동.</a></h5>
    </section>
    <!-- Main content -->
    <section class="content">
    <div id="ex1">
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
	</div>
	<div id="ex2">
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
	</div>
	<div id="ex3">
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
		<pre><code>
			private String setManagementSDK(Model model) {
				model.addAttribute("user",userService.searchAuthUser(sessionWire.getId()));
		    		return "managementsdk";
			}
		</code></pre>
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
       <h5>1. <a href="#ex1">ex1로 이동.</a></h5>
       <h5>2. <a href="#ex2">ex2로 이동.</a></h5>
       <h5>3. <a href="#ex3">ex3로 이동.</a></h5>
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
</script>
</body>
</html>
