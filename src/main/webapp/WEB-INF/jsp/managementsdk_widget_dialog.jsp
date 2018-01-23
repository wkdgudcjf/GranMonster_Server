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
       	 3.1 위젯 다이얼로그
        <small> Widget Dialog</small>
      </h1>
      <h5>A. <a href="#sdk_widget_dialog">widget 생성</a></h5>
      <h5>B. <a href="#sdk_widget_sourcecode">소스코드</a></h5>
    </section>

    <!-- Main content -->
    <section class="content">
		<div id="sdk_widget_dialog">
		    <h1>A. widget 생성</h1>
		    <br/>
			<h5><b> 그랑몬스터 위젯에는 디자인을 위한 여러가지 그래픽 리소스가 있으며, 내용물을 채우기 위해서 서버와의 블록킹 통신이 필요합니다.</b></h5>
			<h5><b>따라서 꽤 많은 로딩 시간이 필요하기 때문에 게임을 처음 시작할 때나 씬 전환 시에 미리 로딩하는 작업을 하는 것을 권장 드립니다.</b></h5>
			<br/>
			<div style="text-align:center;">
				<img src="/sdk_image/sdk_widget_dialog.png" style="max-width: 800px; max-height: 800px;">
				<h3>그림 - 위젯 생성</h3>
			</div>
			<br/>
			<div class="box box-primary">
			     <div class="box-header with-border">
			       <h5 class="box-title">Explane</h5>
			     </div>
			     <div class="box-body">
			       <h5>위 그림에 보이는 1번 항목처럼 Granmonster SDK 안에 있는 함수를 호출하여 위젯을 미리 로딩할 수 있습니다.</h5>
			       <h5>함수 호출 시 필요한 매개변수는 다음과 같습니다.</h5>
			       <br/>
			       <h5><b>appKey</b> : 관리자 페이지에서 앱 등록 시 발급된 게임 키를 넣습니다.</h5>
			       <h5><b>orientation</b> : 위젯은 가로형 디자인과 세로형 디자인이 약간의 차이가 있습니다. 따라서 현재 게임이 어떤 형태의 게임이냐에 따른 값을 넣어줍니다.</h5>
			       <h5>위젯을 객체로 새로 생성하였을 때 상위 부모 컴포넌트를 지정하여 줍니다. 이를 지정하여 줄 때에는 depth를 고려하여 적절한 위치에 지정하여야만 합니다. </h5>
			       <h5>그렇지 않을 경우 위젯이 다른 UI에 가려 보이지 않는다던가, UI 관련 이벤트 핸들링이 의도했던 대로 이루어지지 않을 수도 있습니다.</h5>
			       <h5><b>monoBehavior</b> : granmonster.GranmonsterWidgetButton.Initialize() 함수 내부에서는 코루틴을 통한 로직을 처리합니다.</h5>
			       <h5>따라서 이 코루틴을 돌릴 주체가 되는 MonoBehaviour 타입이 필요합니다. 대개의 경우에는 이 함수를 호출하는 MonoBehaviour를 상속받는 클래스의 this가 되는 것이 좋습니다. </h5>
			       <h5><b>onDialogCloseCallBack</b> : 만약 유저가 위젯을 의도적으로 닫았을 때 어떤 작업이 추가로 실행되도록 하고 싶다면 이 곳에 콜백함수를 넣어줍니다. 이는 옵션이기 때문에 필요하지 않다면 공란으로 비워두셔도 좋습니다. </h5>
			       <h5>위의 경우에는 OnLoginSuccess() 함수로 추가 작업을 시행합니다.</h5>
			     </div>
			     <!-- /.box-body -->
		   </div>
	   </div>
	   <br/>
		<div id="sdk_widget_sourcecode">
		 <h1>B. 소스코드</h1>
		<pre><code>
private void Start()
{
   if (WidgetManager.IsInitialized() == false)
   {
      Utility.DebugLog("로딩 시간을 줄이기 위해 위젯을 미리 로드합니다.");
      string debugMessage = WidgetManager.Initialize ?
         "위젯 미리 로드에 성공하였습니다." : "위젯 미리 로드에 실패하였습니다.";
      Utility.DebugLog(debugMessage);
   }

   if (GrancoinShopManager.IsInitialized() == false)
   {
      Utility.DebugLog("로딩 시간을 줄이기 위해 그랑코인 샵을 미리 로드합니다.");
      string debugMessage = GrancoinShopManager.Initialize ?
         "그랑코인 샵 미리 로드에 성공하였습니다." : "그랑코인 샵 미리 로드에 실패하였습니다.";
      Utility.DebugLog(debugMessage);
   }
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
       <h5>A. <a href="#sdk_widget_dialog">widget 생성</a></h5>
       <h5>B. <a href="#sdk_widget_sourcecode">소스코드</a></h5>
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
		 $('#managementsdk_widget').attr('class',"treeview menu-open");
	};
</script>
</body>
</html>
