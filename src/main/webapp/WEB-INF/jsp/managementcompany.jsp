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
            <h4>회사 관리</h4>
          </li>
          <li class="breadcrumb-item active">Management Company</li>
        </ol>
        
        <!-- 추가 -->
        <div class="row">
          <div class="col-xl-3 col-sm-4 mb-3">
			   <Button type="button" class="btn btn-success" data-toggle="modal" data-target="#registCompanyModal">회사 등록</Button>
          </div>
        </div>
        
        <!-- Tables -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-table"></i>
            Company List
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>회사명</th>
                    <th>등록시간</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Enable</th>
                    <th>Company Name</th>
                    <th>Date</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="item" items="${companylist}">
                <tr>
			        <th>
			        <c:choose>
						<c:when test="${item.companyEnable==true}"> 
						    <img src="/img/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/img/disable.png" style="max-width: 35px; max-height: 35px;"> 
						</c:otherwise> 
					</c:choose>
			        <th><a href="#" onclick="modifyModal(${item.companyID});">${item.companyName}</a></th>
			        <th>${item.companyDateTime}</th>
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
	
	 <!-- ModifyCompany Modal -->
    <div class="modal fade" id="modifyCompanyModal" tabindex="-1" role="dialog" aria-labelledby="modifyCompanyModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modifyCompanyModalLabel">Modify Company</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="modifycompanyform">
          	  <input type="hidden" id="modifyID" value="temp" name="companyID">
	            <div class="form-group">
	              <label for="modifyName">회사명</label>
	              <input type="text" name="companyName" class="form-control" id="modifyName" aria-describedby="nameHelp" placeholder="회사명">
	            </div>
	            <div class="form-group">
	              <label for="modifyEnable">활성여부</label><br>
	              <label class="radio-inline">
			     	 <input type="radio" id="modifyEnable" value="true" name="companyEnable">활성
				  </label>
				  <label class="radio-inline">
				     <input type="radio" id="modifyDisable" value="false" name="companyEnable">비활성
				  </label>
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="modifyCompany();">수정하기</a>
          </div>
        </div>
      </div>
    </div>
    
	 <!-- RegistCompany Modal -->
    <div class="modal fade" id="registCompanyModal" tabindex="-1" role="dialog" aria-labelledby="registCompanyModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registCompanyModalLabel">Regist Company</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="registcompanyform">
	            <div class="form-group">
	              <label for="inputName">회사명</label>
	              <input type="text" name="companyName" class="form-control" id="inputName" aria-describedby="nameHelp" placeholder="회사명">
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="registCompany();">등록하기</a>
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
			 $('#navi_company').attr('class',"nav-item active");
			 $('#tableTime').text('Updated ' + d.getFullYear()+'/'+(d.getMonth() + 1)+'/'+d.getDate()+' '+d.getHours()
					 +':'+d.getMinutes()+':'+d.getSeconds());
		});
		
		 function registCompany(){
			$.ajax({
				url:"/registcompany",
				type: "POST",
				data: new FormData($("#registcompanyform")[0]),
				enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false,
		        cache: false,
		        success: function () {
		        	location.reload();
		        },
		        error: function () {
		        }
			})
		 }
		 function modifyCompany(){
				$.ajax({
					url:"/modifycompany",
					type: "POST",
					data: new FormData($("#modifycompanyform")[0]),
					enctype: 'multipart/form-data',
			        processData: false,
			        contentType: false,
			        cache: false,
			        success: function () {
			        	location.reload();
			        },
			        error: function () {
			        }
				})
			 }
		 function modifyModal(id){
				$.ajax({
					url:"/getcompany",
					type: "POST",
					data : {'companyID':id},
					dataType  : 'json',
			        success: function (data) {
			        	 $("#modifyName").val(data.companyName);
			        	 if(data.companyEnable)
			        	{
			        		 $("#modifyEnable").prop("checked", true)
			        	}
			        	 else
			        	{
			        		 $("#modifyDisable").prop("checked", true)
			        	}
			        	 $("#modifyID").val(data.companyID);
			        	 $("#modifyCompanyModal").modal('show');
			        },
			        error: function () {
			        }
				})
			}
    </script>
  </body>

</html>
