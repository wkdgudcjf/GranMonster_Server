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
     <style type="text/css">
		p {word-break:break-all;}
	 </style>
  </head>

  <body class="fixed-nav sticky-footer bg-dark" id="page-top">
    <div class="content-wrapper">
      <div class="container-fluid">
		<jsp:include page="navi.jsp" flush="false"/>
        <!-- Breadcrumbs -->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <h4>${app.appName} 이벤트 관리</h4>
          </li>
          <li class="breadcrumb-item active">Management Event</li>
        </ol>
        
        <!-- 추가 -->
        <div class="row">
          <div class="col-xl-3 col-sm-4 mb-3">
			   <Button type="button" class="btn btn-success" onclick="registAppEventModal()">이벤트 등록(Regist Event)</Button>
          </div>
        </div>
        
        <!--  Tables  -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-table"></i>
            Event List
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>이벤트키</th>
                    <th>내용</th>
                    <th>보상</th>
                    <th>시작 시간</th>
                    <th>종료 시간</th>
                    <th>달성 인원수</th>
                    <th>제한 인원수</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Enable</th>
                    <th>Event Key</th>
                    <th>Content</th>
                    <th>Coin</th>
                    <th>StartTime</th>
                    <th>EndTime</th>
                    <th>Count</th>
                    <th>Limit</th>
                  </tr>
                </tfoot>
                <tbody>
                  <c:forEach var="item" items="${eventList}">
	                <tr>
				        <td>
				        <c:choose>
						<c:when test="${item.appEventEnable==true}"> 
						    <img src="/img/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/img/disable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:otherwise> 
						</c:choose>
						</td>
				         <td><a href="#" onclick="modifyAppEventModal(${item.appEventID});">${item.appEventKey}</a> 
				        </td>
				        <td>${item.appEventContent}</td>
				        <td>${item.appEventCoin}</td>
				        <td>${item.appEventStartTime}</td>
				        <td>${item.appEventEndTime}</td>
				        <td>${item.appEventCount}</td>
				        <td>${item.appEventLimit}</td>
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
	
	 <!-- RegistAppEvent Modal -->
    <div class="modal fade" id="registAppEventModal" tabindex="-1" role="dialog" aria-labelledby="registAppEventModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registAppEventModalLabel">Regist AppEvent</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="registappeventform">
          	  	<input type="hidden" id="appEventAppID" value="${app.appID}" name="appID">
	            <div class="form-group">
	              <label for="inputAppEventContent">내용</label>
	              <input type="text" name="appEventContent" class="form-control" id="inputAppEventContent" aria-describedby="nameHelp" placeholder="이벤트 내용">   
	            </div>
	            <div class="form-group">
	              <label for="inputAppEventCoin">보상코인</label>
	              <input type="text" name="appEventCoin" class="form-control" id="inputAppEventCoin" aria-describedby="nameHelp" placeholder="보상 Coin">
	            </div>
	            <div class="form-group">
	              <label for="inputAppEventKey">이벤트 키(고유)</label>
	              <input type="text" name="appEventKey" class="form-control" id="inputAppEventKey" aria-describedby="nameHelp" placeholder="api 키">
	            </div>
	            <div class="form-group">
	              <label for="inputAppEventStartTime">시작 시점</label>
	              <input type="datetime-local" name="appEventStartTime" class="form-control" id="inputAppEventStartTime" aria-describedby="nameHelp" placeholder="시작 시점">
	            </div>
	            <div class="form-group">
	              <label for="inputAppEventEndTime">종료 시점</label>
	              <input type="datetime-local" name="appEventEndTime" class="form-control" id="inputAppEventEndTime" aria-describedby="nameHelp" placeholder="종료 시점">
	            </div>
	            <div class="form-group">
	              <label for="inputAppEventLimit">제한 인원수</label>
	              <input type="text" name="appEventLimit" class="form-control" id="inputAppEventLimit" aria-describedby="nameHelp" placeholder="제한 인원수">
	            </div>
	            <!-- 이벤트 기간 , 인원수 추가 고려. -->
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="registAppEvent();">등록하기</a>
          </div>
        </div>
      </div>
    </div>
 
   <!-- ModifyApp Modal -->
    <div class="modal fade" id="modifyAppEventModal" tabindex="-1" role="dialog" aria-labelledby="modifyAppEventModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modifyAppModalLabel">Modify AppEvent</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="modifyappeventform">
          	  <input type="hidden" id="modifyAppEventID" value="temp" name="appEventID">
	            <div class="form-group">
	              <label for="modifyAppEventContent">내용</label>
	              <input type="text" name="appEventContent" class="form-control" id="modifyAppEventContent" aria-describedby="nameHelp" placeholder="이벤트 내용">   
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventCoin">보상코인</label>
	              <input type="text" name="appEventCoin" class="form-control" id="modifyAppEventCoin" aria-describedby="nameHelp" placeholder="보상 Coin">
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventKey">이벤트 키(고유)</label>
	              <input type="text" name="appEventKey" class="form-control" id="modifyAppEventKey" aria-describedby="nameHelp" placeholder="api 키">
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventStartTime">시작 시점</label>
	              <input type="datetime-local" name="appEventStartTime" class="form-control" id="modifyAppEventStartTime" aria-describedby="nameHelp" placeholder="시작 시점">
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventEndTime">종료 시점</label>
	              <input type="datetime-local" name="appEventEndTime" class="form-control" id="modifyAppEventEndTime" aria-describedby="nameHelp" placeholder="종료 시점">
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventCount">달성 인원수</label>
	              <p id="modifyAppEventCount"></p>
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventLimit">제한 인원수</label>
	              <input type="text" name="appEventLimit" class="form-control" id="modifyAppEventLimit" aria-describedby="nameHelp" placeholder="제한 인원수">
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventEnable">활성여부</label><br>
	              <label class="radio-inline">
			     	 <input type="radio" id="modifyAppEventEnable" value="true" name="appEventEnable">활성
				  </label>
				  <label class="radio-inline">
				     <input type="radio" id="modifyAppEventDisable" value="false" name="appEventEnable">비활성
				  </label>
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="modifyAppEvent();">수정하기</a>
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
			 $('#navi_app').attr('class',"nav-item active");
			 $('#tableTime').text('Updated ' + d.getFullYear()+'/'+(d.getMonth() + 1)+'/'+d.getDate()+' '+d.getHours()
					 +':'+d.getMinutes()+':'+d.getSeconds());
		});
	 	function registAppEvent(){
			 var inputAppEventContent = $('#inputAppEventContent'),
			 inputAppEventCoin = $('#inputAppEventCoin'),
			 inputAppEventKey = $('#inputAppEventKey'),
			 inputAppEventLimit = $('#inputAppEventLimit');
			 if (inputAppEventContent.val().length == 0) {
				 alert('이벤트 내용을 입력하세요.');
				 return;
			 }
			 if (inputAppEventCoin.val().length == 0) {
				 alert('보상을 입력하세요.');
				 return;
			 }
			 if (inputAppEventKey.val().length == 0) {
				 alert('이벤트키를 입력하세요.');
				 return;
			 }
			 if (inputAppEventLimit.val().length == 0) {
				 alert('인원수를 입력하세요.');
				 return;
			 }
			$.ajax({
				url:"/registappevent",
				type: "POST",
				data: new FormData($("#registappeventform")[0]),
				enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false,
		        dataType : "text",
		        cache: false,
		        success: function () {
		        	location.reload();
		        },
		        error:function(request,status,error){
		        	alert(request.responseText);
		        }
			});
		}
		 function modifyAppEvent(){
			 var modifyAppEventContent = $('#modifyAppEventContent'),
			 modifyAppEventCoin = $('#modifyAppEventCoin'),
			 modifyAppEventKey = $('#modifyAppEventKey'),
			 modifyAppEventLimit = $('#modifyAppEventLimit'),
			 modifyAppEventCount = $('#modifyAppEventCount').text();
			 if (modifyAppEventContent.val().length == 0) {
				 alert('이벤트 내용을 입력하세요.');
				 return;
			 }
			 if (modifyAppEventCoin.val().length == 0) {
				 alert('보상을 입력하세요.');
				 return;
			 }
			 if (modifyAppEventKey.val().length == 0) {
				 alert('이벤트키를 입력하세요.');
				 return;
			 }
			 if (modifyAppEventLimit.val().length == 0) {
				 alert('인원수를 입력하세요.');
				 return;
			 }
			 if(modifyAppEventLimit.val() <= modifyAppEventCount)
			 {
				 alert('최대 인원수가 현재 인원수보다 작습니다.');
				 return;
		     }
				$.ajax({
					url:"/modifyappevent",
					type: "POST",
					data: new FormData($("#modifyappeventform")[0]),
					enctype: 'multipart/form-data',
			        processData: false,
			        dataType : "text",
			        contentType: false,
			        cache: false,
			        success: function () {
			        	location.reload();
			        },
			        error:function(request,status,error){
			        	alert(request.responseText);
			        }
				})
			 }
		 function registAppEventModal(){
			 var date = new Date();
			 var afterdate = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toJSON();
			 $('#inputAppEventStartTime').val(afterdate.slice(0,19));
			 $('#inputAppEventEndTime').val(afterdate.slice(0,19));
			 document.getElementById("inputAppEventStartTime").min = afterdate.slice(0,19);
			 document.getElementById("inputAppEventEndTime").min = afterdate.slice(0,19);
			 $("#registAppEventModal").modal('show');
		 }
		 function modifyAppEventModal(id){
				$.ajax({
					url:"/getappevent",
					type: "POST",
					data : {'appEventID':id},
					dataType  : 'json',
			        success: function (data) {
			        	 var startdate = new Date(data.appEventStartTime); 
			        	 var enddate = new Date(data.appEventEndTime); 
			        	 var afterstartdate = new Date(startdate.getTime() - (startdate.getTimezoneOffset() * 60000)).toJSON();
			        	 var afterenddate = new Date(enddate.getTime() - (enddate.getTimezoneOffset() * 60000)).toJSON();
			        	 $("#modifyAppEventContent").val(data.appEventContent);
			        	 $("#modifyAppEventCoin").val(data.appEventCoin);
			        	 $('#modifyAppEventStartTime').val(afterstartdate.slice(0,19));
						 $('#modifyAppEventEndTime').val(afterenddate.slice(0,19));
						 $('#modifyAppEventKey').val(data.appEventKey);
						 $('#modifyAppEventLimit').val(data.appEventLimit);
						 $("#modifyAppEventCount").text(data.appEventCount);
			           	 if(data.appEventEnable)
			        	{
			        		 $("#modifyAppEventEnable").prop("checked", true)
			        	}
			        	 else
			        	{
			        		 $("#modifyAppEventDisable").prop("checked", true)
			        	}
			        	 $("#modifyAppEventID").val(data.appEventID);
			        	 $("#modifyAppEventModal").modal('show');
			        },
			        error:function(request,status,error){
			        	alert(request.responseText);
			        }
				})
			}
    </script>
  </body>

</html>
