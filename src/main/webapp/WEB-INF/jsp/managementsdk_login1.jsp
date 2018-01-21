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
       	 2. 로그인 연동
        <small> Login Process</small>
      </h1>
      <br/>
      <h5>A. <a href="#sdk_login_callback">Login 콜백</a></h5>
      <h5>B. <a href="#sdk_login_sourcecode">소스코드</a></h5>
      <h5>C. <a href="#sdk_login_notice">notice</a></h5>
    </section>

    <!-- Main content -->
    <section class="content">
   		<div id="sdk_login_callback">
		    <h1>A. Login 콜백</h1>
		    <br/>
			<h5> <b> Google Login 또는 IPhone Login을 성공시 Callback 메소드에서 그랑몬스터 로그인을 처리합니다.</b></h5>
			<br/>
			<div style="text-align:center;">
				<img src="/sdk_image/sdk_login_callback.png" style="max-width: 800px; max-height: 800px;">
				<h3>그림 - Login Callback 함수</h3>
			</div>
			<br/>
			<div class="box box-primary">
			     <div class="box-header with-border">
			       <h5 class="box-title">Explane</h5>
			     </div>
			     <div class="box-body">
			       <h5><b>1)</b> granmonster 네임 스페이스 안에 그랑몬스터 서버와 클라이언트의 모듈이 통신하기 위한 프로토콜이 정의되어 있습니다.</h5>
			       <h5>RequestProtocol.Login 지역 변수를 선언하고 서버가 필요로 하는 데이터를 넣어줍니다.</h5>
			       <h5>userKey에는 구글 아이디 또는 아이폰 아이디를 넣고, appKey에는 	관리자 페이지에서 게임 등록 시 발급되는 게임 키를 넣어줍니다.</h5>
			       <h5>값을 모두 세팅한 다음에 ServerConnection 클래스에 있는 Login() 함수를 호출하여 서버에 요청을 합니다.</h5>
			       <h5>서버의 응답을 받고 난 뒤 RestApiCallStatusMethods 유틸리티 클래스에 있는 함수들을 이용하여 유효성 검사를 할 수 있습니다. </h5>
			       <br/>
			       <h5><b>2)</b> 만약 그랑몬스터 로그인 완료 후 추가로 해야할 작업이 있다면 해당 블록의 시점에서 원하는 작업을 추가로 진행하면 됩니다. </h5>
			       <h5>위의 경우에는 OnLoginSuccess() 함수로 추가 작업을 시행합니다.</h5>
			     </div>
			     <!-- /.box-body -->
		   </div>
		</div>
		<br/>
		<div id="sdk_login_sourcecode">
		 <h1>B. 소스코드</h1>
			<pre><code>
IEnumerator TryAuthentification()
{
     yield return new WaitForSeconds(2.0f);

     Social.localUser.Authenticate((bool success) =>
     {
        if (success)
        {
           RequestProtocol.Login requestLogin = new RequestProtocol.Login
           {
              userKey = Social.localUser.id,
              appKey = GlobalConstants.GranmonsterGameKey
           };

           ServerResponse loginResponse = ServerConnection.Login(LoginType.Google, JsonUtility.ToJson(requestLogin));
           if (RestApiCallStatusMethods.Error(loginResponse.Status))
           {
              Utility.DebugLog(ToString() + "/TryAuthentification/" + loginResponse.Data);
              return;
           }

           OnLoginSuccess();
        }
        else
        {
           Debug.Log("구글 로그인에 실패하였습니다.");
        }
     });

     EndBlocking();
}
			</code></pre>
	</div>
	<div id="sdk_login_notice">
		 <h1>C. notice</h1>
		<div class="callout callout-warning">
	        <h4>Notice!</h4>
	        <p><b>※</b> AppKey는 어플리케이션 소스 내에 따로 저장해 놓는 것이 좋습니다.</p>
	        <p><b>※</b> 만약 매번 Google Login이나 IPhone Login을 하지 않으려면 발급받은 UserKey를 따로 저장하여 그랑 몬스터 로그인에 사용하시면 됩니다. </p>
	     	<p><b>※</b> GranMonster SDK는 앱을 구동하고 Login을 하면 앱이 종료될 때 까지 Login된 UserKey를 재사용합니다.</p>
	     </div>
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
       <h5>A. <a href="#sdk_login_callback">Login 콜백</a></h5>
       <h5>B. <a href="#sdk_login_sourcecode">소스코드</a></h5>
       <h5>C. <a href="#sdk_login_notice">notice</a></h5>
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
		 //$('#managementsdk_login').attr('class',"treeview menu-open");
	};
</script>
</body>
</html>
