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
       	 영수증 관리
        <small>Management Billing</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Billing List</h3>
            </div>
            <!-- box-header -->
            <div class="box-body">
              <table id="mytable" class="table table-bordered table-striped">
              	<thead>
                  <tr>
                    <th>유저 키</th>
                    <th>앱 이름</th>
                    <th>금액</th>
                    <th>그랑코인</th>
                    <th>날짜</th>
                    <th>타입</th>
                  </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>합계</th>
                  <th></th>
                  <th id="money-sum"></th>
                  <th id="coin-sum"></th>
                  <th></th>
                  <th></th>
                </tr>
                  <tr>
                    <th>User Key</th>
                    <th>App Name</th>
                    <th>Money</th>
                    <th>Gran Coin</th>
                    <th>Date</th>
                    <th>Type</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="item" items="${billinglist}">
                <tr>
			        <td>${item.userKey}</td>
			        <td>${item.appName}</td>
			        <td>${item.billingMoney}</td>
			        <td>${item.billingCoin}</td>
			        <td>${item.billingDateTime}</td>
			        <td>${item.billingType}</td>
			    </tr>
			    </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- box-body -->
          </div>
          <!-- box -->
        </div>
        <!-- col -->
      </div>
      <!-- row -->
    </section>
    <!-- content -->
  </div>
  <!-- content-wrapper -->
  
  <jsp:include page="include/main_footer.jsp" flush="false"/>
</div>
<!-- wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
 <!-- Custom scripts for this template -->
	<script type="text/javascript">
	 $(document).ready(function(){
		 $('#navi_billing').attr('class',"active");
         $(".content").on("keyup", "input[type=search]", tableSum);
        // $(".content").on("change", "li[class=paginate_button]", tableSum);
         tableSum();
      });

	 function tableSum() {
         var money = 0;
         $('#mytable tbody tr td:nth-child(3)').each(function() {
             money += parseInt($(this).text());
         });

         var coin = 0;
         $('#mytable tbody tr td:nth-child(4)').each(function() {
             coin += parseInt($(this).text());
         });

         $('#money-sum').text(money);
         $('#coin-sum').text(coin);
     }
    </script>
</body>
</html>