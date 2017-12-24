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
    <section class="content-header" style="padding:20px;">
      <h1>
       	 ${app.appName} 이벤트 관리
        <small>Management Event</small>
      </h1>
      <ol class="breadcrumb">
        <li><Button type="button" class="btn btn-block btn-success btn-flat" onclick="registAppEventModal()">이벤트 등록(Regist Event)</Button></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Event List</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="mytable" class="table table-bordered table-striped">
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
						    <img src="/image/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/image/disable.png" style="max-width: 30px; max-height: 30px;"> 
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
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <jsp:include page="include/main_footer.jsp" flush="false"/>
  
    <!-- RegistAppEvent Modal -->
    <div class="modal fade" id="registAppEventModal" tabindex="-1" role="dialog" aria-labelledby="registAppEventModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header" style="text-align: center;">
            <h1 class="modal-title" id="registAppEventModalLabel">Regist AppEvent</h1>
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
	            
	            <!-- Date and time range -->
                <div class="form-group">
                <label>이벤트 기간</label>
                  <div class="input-group">
                    <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>
                    <input type="text" name="appEventReservationTime" class="form-control pull-right" id="inputAppEventReservationTime"/>
                  </div>
                  <!-- /.input group -->
                </div>
              <!-- /.form group -->
	            <div class="form-group">
	              <label for="inputAppEventLimit">제한 인원수(0은 무제한)</label>
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
          <div class="modal-header" style="text-align: center;">
            <h1 class="modal-title" id="modifyAppModalLabel">Modify AppEvent</h1>
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
	            <!-- Date and time range -->
                <div class="form-group">
                <label>이벤트 기간</label>
                  <div class="input-group">
                    <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>
                    <input type="text" name="appEventReservationTime" class="form-control pull-right" id="modifyAppEventReservationTime"/>
                  </div>
                  <!-- /.input group -->
                </div>
              <!-- /.form group -->
	            <div class="form-group">
	              <label for="modifyAppEventCount">달성 인원수</label>
	              <p id="modifyAppEventCount"></p>
	            </div>
	            <div class="form-group">
	              <label for="modifyAppEventLimit">제한 인원수(0은 무제한)</label>
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
</div>
<!-- ./wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
<!-- Page script -->
 <!-- Custom scripts for this template -->
	<script type="text/javascript">
	 $(document).ready(function(){
		 $('#navi_app').attr('class',"active");
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
		 var count = parseInt(modifyAppEventLimit.val(), 10);
		 if(count <= modifyAppEventCount && count !=0)
		 {
			 alert('최대 인원수가 현재 인원수보다 작거나 같습니다.');
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
		    $("#inputAppEventReservationTime").daterangepicker({
		        timePicker: true,
		        timePickerIncrement: 1,
		        minDate: date,
		        startDate: date,
		        locale: {
		            format: 'YYYY-MM-DD HH:mm:ss'
		        }
		    });
		 $("#registAppEventModal").modal('show');
	 }
	 function modifyAppEventModal(id){
			$.ajax({
				url:"/getappevent",
				type: "POST",
				data : {'appEventID':id},
				dataType  : 'json',
		        success: function (data) {
		        	 var date = new Date();
		        	 alert(date);
		        	 var startdate = new Date(data.appEventStartTime); 
		        	 alert(startdate);
		        	 var enddate = new Date(data.appEventEndTime);
		        	 alert(enddate);
		        	 if(date>startdate)
		        	 {
		        		 date = startdate;
		        	 }
		        	 $("#modifyAppEventContent").val(data.appEventContent);
		        	 $("#modifyAppEventCoin").val(data.appEventCoin);
				    $("#modifyAppEventReservationTime").daterangepicker({
				        timePicker: true,
				        timePickerIncrement: 1,
				        minDate: date,
				        startDate: startdate,
				        endDate:enddate,
				        locale: {
				            format: 'YYYY-MM-DD HH:mm:ss'
				        }
				    });
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
