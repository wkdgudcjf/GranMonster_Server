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
       	 4.2 Gran샵 다이얼로그
        <small> Gran Shop Dialog</small>
      </h1>
      <h5>A. <a href="#sdk_billing_shop_dialog_setting">그랑코인 샵 생성</a></h5>
      <h5>B. <a href="#sdk_billing_shop_dialog_setting_sourcecode">그랑코인 샵 생성 소스코드</a></h5>
    </section>

    <!-- Main content -->
    <section class="content">
		<div id="sdk_billing_shop_dialog_setting">
		    <h1>A. 그랑코인 샵 생성</h1>
		    <br/>
			<h5> <b> 그랑코인 샵 다이얼로그에는 많은 그래픽 리소스가 들어있습니다.</b></h5>
			<h5> <b>서버가 내려주는 데이터를 구성하기 위해 blocking 방식으로 통신하기 때문에 게임이 처음 켜질 때나 씬 전환을 할때 등등 적절한 타이밍에 미리 로딩을 할 필요가 있습니다.</b>
			</h5>
			<br/>
			<div style="text-align:center;">
				<img src="/sdk_image/sdk_billing_shop_dialog.png" style="max-width: 800px; max-height: 800px;">
				<h3>그림 - 그랑코인 샵 생성</h3>
			</div>
			<br/>
			<div class="box box-primary">
			     <div class="box-header with-border">
			       <h5 class="box-title">Explane</h5>
			     </div>
			     <div class="box-body">
			    <h5> 위 그림의 1번 항목에서는 그랑코인 샵 다이얼로그를 초기화하는 방법을 보여줍니다. </h5>
			    <h5> 그랑코인 샵 다이얼로그를 초기화하기 위해서는 granmonster.GrancoinShopManager.Initialize() 함수를 사용합니다.
				</h5>
			     </div>
			     <!-- /.box-body -->
		   </div>
		</div>
		<br/>
		<div id="sdk_billing_shop_dialog_setting_sourcecode">
		 <h1>B. 결제 모듈 셋팅 소스코드</h1>
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

	int firstLogin = PlayerPrefs.GetInt("HasPlayed");
	if (firstLogin == 0)
	{
		PlayerPrefs.SetInt("HasPlayed", 1);

		if (ServerConnection.FirstLogin(GlobalConstants.GranmonsterGameKey))
		{
			// 어플리케이션을 설치하고 첫 실행을 하였다 하더라도 서버의 데이터도 함께 검증해야 합니다.
			// 유저가 고의적으로 어플리케이션을 삭제 후 재설치를 반복하여 부당 이득을 취하는 것을 방지하기 위함입니다.
			ResponseProtocol.ResponseEvent responseEvent = granmonster.EventManager.EventComplete(GlobalConstants.GranmonsterGameKey, "install");
			if (responseEvent.State != ResponseProtocol.ResponseEvent.StatusCode.SUCCESS)
			{
				Utility.DebugLog("install 이벤트 완료 요청을 보냈지만 실패했습니다.(Error Code : " + responseEvent.State + ")");
				return;
			}

			responseEvent = granmonster.EventManager.EventComplete(GlobalConstants.GranmonsterGameKey, "first_run");
			if (responseEvent.State != ResponseProtocol.ResponseEvent.StatusCode.SUCCESS)
			{
				Utility.DebugLog("first_run 이벤트 완료 요청을 보냈지만 실패했습니다.(Error Code : " + responseEvent.State + ")");
				return;
			}

			Utility.DebugLog("설치 후 첫 실행 이벤트를 완료하였습니다.");
		}
		else
		{
			Utility.DebugLog("ServerConnection.FirstLogin() 함수가 false를 리턴하였습니다.");
		}
	}
	else
	{
		Utility.DebugLog("설치 후 두 번 이상 로그인 하였습니다. 이벤트 완료 요청을 하지 않습니다.");
	}
}
			</code></pre>
	</div>
	<br/>
    </section>
    <!-- /.content -->
  </div>
   <!-- /.content-wrapper -->
  <div id="layer_QuickMenu">
  <!-- Form Element sizes -->
   <div class="box box-primary" style="margin:0px;">
     <div class="box-header with-border">
       <h3 class="box-title">Quick Menu</h3>
     </div>
     <div class="box-body">
       <h5>A. <a href="#sdk_billing_shop_dialog_setting">그랑코인 샵 생성</a></h5>
       <h5>B. <a href="#sdk_billing_shop_dialog_setting_sourcecode">그랑코인 샵 생성 소스코드</a></h5>
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
		 $('#managementsdk_billing').attr('class',"treeview menu-open");
	};
</script>
</body>
</html>
