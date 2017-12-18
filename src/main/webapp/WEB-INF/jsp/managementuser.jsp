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
            <h4>유저 관리</h4>
          </li>
          <li class="breadcrumb-item active">Management User</li>
        </ol>
        
        <!-- Tables -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-table"></i>
            User List
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>키</th>
                    <th>이메일</th>
                    <th>비밀번호</th>
                    <th>돈</th>
                    <th>캐쉬</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Enable</th>
                    <th>Key</th>
                    <th>email</th>
                    <th>password</th>
                    <th>money</th>
                    <th>cash</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="item" items="${userlist}">
                  <tr>
				        <td>
				        <c:choose>
						<c:when test="${item.userEnable==true}"> 
						    <img src="/image/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/image/disable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:otherwise> 
						</c:choose>
						</td>
				         <td><a href="#" onclick="goToUserInfo(${item.userID});">${item.userKey}</a></td>
				        <td>${item.userEmail}</td>
				        <td>${item.userPassword}</td>
				        <td>${item.userMoney}</td>
				        <td>${item.userCoin}</td>
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
		 $('#navi_user').attr('class',"nav-item active");
		 $('#tableTime').text('Updated ' + d.getFullYear()+'/'+(d.getMonth() + 1)+'/'+d.getDate()+' '+d.getHours()
				 +':'+d.getMinutes()+':'+d.getSeconds());
		});
	 function goToUserInfo(userID){
		 var form = document.createElement('form');
		 var objs;
		 objs = document.createElement('input');
		 objs.setAttribute('type', 'hidden');
		 objs.setAttribute('name' , 'userID');
		 objs.setAttribute('value', userID);
		 form.appendChild(objs);
		 form.setAttribute('method', 'post');
		 form.setAttribute('action', "/managementuserinfo");
		 document.body.appendChild(form);
		 form.submit();
		}
    </script>
  </body>

</html>
