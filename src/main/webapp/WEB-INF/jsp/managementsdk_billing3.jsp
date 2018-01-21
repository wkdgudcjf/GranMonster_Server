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
       	  4.3 Gran샵 플로팅 버튼
        <small> Gran Shop Floating Button</small>
      </h1>
      <h5>A. <a href="#sdk_billing_shop_button">그랑코인 샵 호출</a></h5>
      <h5>B. <a href="#sdk_billing_shop_button_sourcecode">그랑코인 샵 호출 소스코드</a></h5>
      <h5>C. <a href="#sdk_billing_shop_button_notice">notice</a></h5>
    </section>

    <!-- Main content -->
    <section class="content">
		<div id="sdk_billing_shop_button">
		    <h1>A. 그랑코인 샵 호출</h1>
		    <br/>
			<h5> 
			 <b> 그랑코인 샵을 열기 위해서는 권장사항으로써 그랑코인 샵 다이얼로그가 미리 로딩되어 있어야 하며, 필수사항으로는 그랑코인 샵 플로팅 버튼을 클릭해야만 합니다.</b>
			</h5>
			<br/>
			<div style="text-align:center;">
				<img src="/sdk_image/sdk_billing_shop_button.png" style="max-width: 800px; max-height: 800px;">
				<h3>그림 - 결제 모듈 연동</h3>
			</div>
			<br/>
			<div class="box box-success">
			     <div class="box-header with-border">
			       <h5 class="box-title">Explane</h5>
			     </div>
			     <div class="box-body">
			    <h5> 사용되는 파라미터의 값입니다.</h5>
			    <br/>
				<h5><b>appKey</b> : 관리자 페이지에서 게임 등록 시 발급되는 게임 키입니다.</h5>
				<h5><b>orientation</b> : 그랑코인 샵은 가로형 디자인과 세로형 디자인이 약간의 차이가 있습니다. 따라서 현재 게임이 어떤 형태의 게임이냐에 따른 값을 넣어줍니다.</h5>
				<h5><b>shopParent</b> : 그랑코인 샵 플로팅 버튼의 부모 컴포넌트를 지정해줍니다.</h5>
				<h5><b>initializer</b> : Unity IAP 연동 모듈을 연동하는 과정에서 ConfigurationBuilder 클래스에 상품 키를 등록하는 부분이 있습니다. </h5>
				<h5>그랑몬스터 SDK를 사용하는 경우, 그랑몬스터 서버가 내려주는 상품 키를 Unity IAP에 등록해야 하기 때문에 서버로부터 정상적인 응답을 받은 경우 해당 데이터를 가지고 Unity IAP 모듈에 상품을 등록하는 함수를 등록해야 합니다. </h5>
				<h5>이때 그랑몬스터 서버가 내려주는 상품 키는 반드시 해당 앱의 인앱 상품으로 등록되어 있어야 합니다.</h5>
				<h5><b>onPurchaseButtonClick</b> : 그랑코인 샵의 UI와 UI Event 등은 모두 granmonster.dll 모듈 안에 구현되어 있습니다. </h5>
 				<h5>그러나 실제 구매 과정에서 그랑몬스터 서버를 거쳐가기 전에 Google App Store 서버 또는 Apple App Store 서버를 먼저 거쳐야 합니다. </h5>
 				<h5>따라서 Unity IAP 모듈의 구매 진행을 수행하는 함수를 등록하여 주어야 합니다.</h5>
				<h5><b>onDialogCloseCallBack</b> : 그랑코인 샵 다이얼로그가 닫힐 때 그 이후 추가해야할 작업이 필요하다면 이 곳에 함수를 등록합니다. 이것은 옵션이기 때문에 필요하지 않다면 공란으로 비워두어도 됩니다.</h5>
				<br/>
				<h5>그랑코인 샵 플로팅 버튼 초기화가 완료되었다면 그랑코인 샵 플로팅 버튼을 화면에 보여주는 작업만 남았습니다.</h5>
				<h5>그랑코인 샵 플로팅 버튼을 보여주려면 위 스크린샷 2번 항목처럼 granmonster.GranmonsterShopButton.Show() 함수를 사용하면 됩니다.</h5>
				<h5>이 함수가 필요로 하는 매개변수는 다음과 같습니다.</h5>
				<br/>
				<h5><b>parent</b> : 플로팅 버튼의 부모 컴포넌트가 될 오브젝트입니다.</h5>
				<h5><b>anchoredPosition3D</b> : 플로팅 버튼이 처음 보여지게 될 위치입니다.</h5>
				<br/>
				<h5>만약 그랑코인 샵 플로팅 버튼을 중간에 보여주지 않으려면 granmonster.GranmonsterShopButton.Hide() 함수를 사용하면 됩니다.	</h5>
			    </div>
			     <!-- /.box-body -->
		   </div>
		</div>
		<br/>
		<div id="sdk_billing_shop_button_sourcecode">
		 <h1>B. 그랑코인 샵 호출 소스코드</h1>
			<pre><code>
using UnityEngine;

using granmonster;

public class ShopTestManager : MonoBehaviour {
	public Transform buttonParent;
	public Transform widgetParent;

	public IAPManager IAPManager;

	private void Start ()
	{
		// 반드시 그랑몬스터 상점 버튼을 먼저 초기화 해야합니다.
		GranmonsterShopButton.Initialize(GlobalConstants.GranmonsterGameKey, new Orientation(Orientation.Type.Vertical),
			widgetParent, this, IAPManager.InitializePurchasing, IAPManager.ProcessPurchase, OnShopClose);

		// 200, 200 위치에 플로팅 버튼을 띄웁니다.
		// 위치는 상황에 맞게 어느 곳에서나 띄울 수 있습니다.
		GranmonsterShopButton.Show(buttonParent, new Vector3(200, 200, 0));
	}

	public void OnShopClose()
	{
		// NOTE: 그랑코인 상점 다이얼로그를 닫은 후에 해야할 작업이 있다면 이곳에 코드를 작성합니다.
	}
}
			</code></pre>
	</div>
	<br/>
	<div id="sdk_billing_shop_button_notice">
		 <h1>C. notice</h1>
		<div class="callout callout-warning">
	        <h4>Notice!</h4>
	        <p><b>※</b> 위젯 플로팅 버튼의 경우 Canvas에서 동작하게 구현되었습니다.</p>
	        <p>NGUI와 같이 Canvas를 사용하지 않는 3rd Party 라이브러리를 사용하는 경우 별도로 Canvas 오브젝트를 생성하여 준 뒤 버튼의 부모 컴포넌트로 지정해야 합니다. </p>
	     </div>
   	 </div>
   	 <br/>
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
       <h5>A. <a href="#sdk_billing_shop_button">그랑코인 샵 호출</a></h5>
       <h5>B. <a href="#sdk_billing_shop_button_sourcecode">그랑코인 샵 호출 소스코드</a></h5>
       <h5>C. <a href="#sdk_billing_shop_button_notice">notice</a></h5>
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
