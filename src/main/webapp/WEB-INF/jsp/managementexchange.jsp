<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>그랑몬스터</title>
	
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin.css" rel="stylesheet">
  </head>

  <body class="fixed-nav sticky-footer bg-dark" id="page-top">
    <div class="content-wrapper">
      <div class="container-fluid">
		<jsp:include page="navi.jsp" flush="false"/>
        <!-- Breadcrumbs -->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <h4>그랑코인 관리</h4>
          </li>
          <li class="breadcrumb-item active">Management Grancoin</li>
        </ol>
        
        <!-- 추가 -->
        <div class="row">
          <div class="col-xl-3 col-sm-4 mb-3">
			   <Button type="button" class="btn btn-success" data-toggle="modal" data-target="#registExchangeModal">그랑코인 등록(Regist Grancoin)</Button>
          </div>
        </div>
        
        <!-- Tables -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-table"></i>
            Grancoin List
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>번호</th>
                    <th>금액</th>
                    <th>그랑코인</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Enable</th>
                    <th>No</th>
                    <th>Money</th>
                    <th>Gran Coin</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="item" items="${exchangelist}">
                <tr>
			        <td>
			        <c:choose>
						<c:when test="${item.exchangeEnable==true}">
						    <img src="/img/enable.png" style="max-width: 30px; max-height: 30px;">
						</c:when>
						<c:otherwise>
						    <img src="/img/disable.png" style="max-width: 30px; max-height: 30px;">
						</c:otherwise>
					</c:choose>
					</td>
			        <td><a href="#" onclick="modifyExchangeModal(${item.exchangeID});">${item.exchangeID}</a></td>
			        <td>${item.exchangeMoney}</td>
			        <td>${item.exchangeCoin}</td>
			    </tr>
			    </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <div id="tableTime" class="card-footer small text-muted">
            Updated yesterday at 11:59 PM
          </div>
        </div>
        
      </div>
      <!-- /.container-fluid -->

    <!-- /.content-wrapper -->
	<jsp:include page="footer.jsp" flush="false"/>
    </div>
	
	 <!-- ModifyExchange Modal -->
    <div class="modal fade" id="modifyExchangeModal" tabindex="-1" role="dialog" aria-labelledby="modifyExchangeModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modifyExchangeModalLabel">Modify Exchange</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="modifyexchangeform">
          	  <input type="hidden" id="modifyExchangeID" value="temp" name="exchangeID">
	            <div class="form-group">
	              <label for="modifyExchangeMoney">금액</label>
	              <input type="text" name="exchangeMoney" class="form-control" id="modifyExchangeMoney" aria-describedby="nameHelp" placeholder="금액">
	            </div>
	             <div class="form-group">
	              <label for="modifyExchangeCoin">코인</label>
	              <input type="text" name="exchangeCoin" class="form-control" id="modifyExchangeCoin" aria-describedby="nameHelp" placeholder="코인">
	            </div>
	            <div class="form-group">
	              <label for="modifyEnable">활성여부</label><br>
	              <label class="radio-inline">
			     	 <input type="radio" id="modifyExchangeEnable" value="true" name="exchangeEnable">활성
				  </label>
				  <label class="radio-inline">
				     <input type="radio" id="modifyExchangeDisable" value="false" name="exchangeEnable">비활성
				  </label>
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="modifyExchange();">수정하기</a>
          </div>
        </div>
      </div>
    </div>
    
	 <!-- RegistCompany Modal -->
    <div class="modal fade" id="registExchangeModal" tabindex="-1" role="dialog" aria-labelledby="registExchangeModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registExchangeModalLabel">Regist Exchange</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="registexchangeform">
	          	  <div class="form-group">
	            	<label for="inputExchangeName">금액</label>
		            <input type="text" name="exchangeMoney" class="form-control" id="inputExchangeMoney" aria-describedby="nameHelp" placeholder="금액">
		          </div>
		           <div class="form-group">
		            <label for="inputExchangeCoin">코인</label>
		            <input type="text" name="exchangeCoin" class="form-control" id="inputExchangeCoin" aria-describedby="nameHelp" placeholder="코인">
		          </div>
          	 </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="registExchange();">등록하기</a>
          </div>
        </div>
      </div>
    </div>

     <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/sb-admin.min.js"></script>
    
    <!-- Custom scripts for this template -->
	<script type="text/javascript">
		 $(document).ready(function(){
			 var d = new Date();
			 $('#navi_exchange').attr('class',"nav-item active");
			 $('#tableTime').text('Updated ' + d.getFullYear()+'/'+(d.getMonth() + 1)+'/'+d.getDate()+' '+d.getHours()
					 +':'+d.getMinutes()+':'+d.getSeconds());
		});
		
		 function registExchange(){
			 var inputExchangeMoney = $('#inputExchangeMoney');
			 var inputExchangeCoin = $('#inputExchangeCoin');
			 if (inputExchangeMoney.val().length == 0) {
				 alert('금액을 입력하세요.');
				 return;
			 }
			 if (inputExchangeCoin.val().length == 0) {
				 alert('코인을 입력하세요.');
				 return;
			 }
			$.ajax({
				url:"/registexchange",
				type: "POST",
				data: new FormData($("#registexchangeform")[0]),
				enctype: 'multipart/form-data',
				dataType : "text",
		        processData: false,
		        contentType: false,
		        cache: false,
		        success: function (data) {
		        	location.reload();
		        },
		        error:function(request,status,error){
		        	alert(request.responseText);
		        }
			})
		 }
		 function modifyExchange(){
			 var modifyExchangeMoney = $('#modifyExchangeMoney');
			 var modifyExchangeCoin = $('#modifyExchangeCoin');
			 if (modifyExchangeMoney.val().length == 0) {
				 alert('금액을 입력하세요.');
				 return;
			 }
			 if (modifyExchangeCoin.val().length == 0) {
				 alert('코인을 입력하세요.');
				 return;
			 }
				$.ajax({
					url:"/modifyexchange",
					type: "POST",
					data: new FormData($("#modifyexchangeform")[0]),
					enctype: 'multipart/form-data',
			        processData: false,
			        contentType: false,
			        dataType : "text",
			        cache: false,
			        success: function (data) {
			        	location.reload();
			        },
			        error:function(request,status,error){
			        	alert(request.responseText);
			        }
				})
			 }
		 function modifyExchangeModal(id){
				$.ajax({
					url:"/getexchange",
					type: "POST",
					data : {'exchangeID':id},
					dataType  : "json",
			        success: function (data) {
			        	 $("#modifyExchangeMoney").val(data.exchangeMoney);
			        	 $('#modifyExchangeCoin').val(data.exchangeCoin)
			        	 if(data.exchangeEnable)
			        	{
			        		 $("#modifyExchangeEnable").prop("checked", true)
			        	}
			        	 else
			        	{
			        		 $("#modifyExchangeDisable").prop("checked", true)
			        	}
			        	 $("#modifyExchangeID").val(data.exchangeID);
			        	 $("#modifyExchangeModal").modal('show');
			        },
			        error:function(request,status,error){
			        	alert(request.responseText);
			        }
				})
			}
    </script>
  </body>

</html>
