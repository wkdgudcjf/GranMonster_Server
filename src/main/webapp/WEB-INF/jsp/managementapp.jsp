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
     /* imaged preview */ 
	 	.filebox input[type="file"] {
		    position: absolute;
		    width: 1px;
		    height: 1px;
		    padding: 0;
		    margin: -1px;
		    overflow: hidden;
		    clip:rect(0,0,0,0);
		    border: 0;
		}
		
		.filebox label {
		    display: inline-block;
		    padding: .5em .75em;
		    color: #999;
		    font-size: inherit;
		    line-height: normal;
		    vertical-align: middle;
		    background-color: #fdfdfd;
		    cursor: pointer;
		    border: 1px solid #ebebeb;
		    border-bottom-color: #e2e2e2;
		    border-radius: .25em;
		}
		
		/* named upload */
		.filebox .upload-name {
		    display: inline-block;
		    padding: .5em .75em;
		    font-size: inherit;
		    font-family: inherit;
		    line-height: normal;
		    vertical-align: middle;
		    background-color: #f5f5f5;
		  border: 1px solid #ebebeb;
		  border-bottom-color: #e2e2e2;
		  border-radius: .25em;
		  -webkit-appearance: none; /* 네이티브 외형 감추기 */
		  -moz-appearance: none;
		  appearance: none;
		}
		
		/* imaged preview */
		.filebox .upload-display {
		    margin-bottom: 5px;
		}
		
		@media(min-width: 768px) {
		    .filebox .upload-display {
		        display: inline-block;
		        margin-right: 5px;
		        margin-bottom: 0;
		    }
		}
		
		.filebox .upload-thumb-wrap {
		    display: inline-block;
		    width: 54px;
		    padding: 2px;
		    vertical-align: middle;
		    border: 1px solid #ddd;
		    border-radius: 5px;
		    background-color: #fff;
		}
		
		.filebox .upload-display img {
		    display: block;
		    max-width: 100%;
		    width: 100% \9;
		    height: auto;
		}
		
		.filebox.bs3-primary label {
		  color: #fff;
		  background-color: #337ab7;
		    border-color: #2e6da4;
		    margin-top: 8px;
		}
	 </style>
  </head>

  <body class="fixed-nav sticky-footer bg-dark" id="page-top">
    <div class="content-wrapper">
      <div class="container-fluid">
		<jsp:include page="navi.jsp" flush="false"/>
        <!-- Breadcrumbs -->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <h4>앱 관리</h4>
          </li>
          <li class="breadcrumb-item active">Management App</li>
        </ol>
        
        <!-- 추가 -->
        <div class="row">
          <div class="col-xl-3 col-sm-4 mb-3">
			   <Button type="button" class="btn btn-success" data-toggle="modal" data-target="#registAppModal">앱 등록</Button>
          </div>
        </div>
        
        <!--  Tables  -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-table"></i>
            App List
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>앱이름</th>
                    <th>회사명</th>
                    <th>마켓경로</th>
                    <th>패키지명</th>
                    <th>등록시간</th>
                    <th>이미지</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>Enable</th>
                    <th>App Name</th>
                    <th>Company Name</th>
                    <th>Market URL</th>
                    <th>Package</th>
                    <th>Date</th>
                    <th>Image</th>
                  </tr>
                </tfoot>
                <tbody>
                  <c:forEach var="item" items="${applist}">
	                <tr>
				        <th>
				        <c:choose>
						<c:when test="${item.appEnable==true}"> 
						    <img src="/img/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/img/disable.png" style="max-width: 35px; max-height: 35px;"> 
						</c:otherwise> 
						</c:choose>
						</th>
				         <th><a href="#" onclick="modifyModal(${item.appID});">${item.appName}</a></th>
				        <th>${item.companyName}</th>
				        <th>${item.appURL}</th>
				        <th>${item.appPackage}</th>
				        <th>${item.appDateTime}</th>
				        <th><img src="image/${item.appImagePath}"  style="max-width: 150px; max-height: 150px;"></th>
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
	
	 <!-- RegistApp Modal -->
    <div class="modal fade" id="registAppModal" tabindex="-1" role="dialog" aria-labelledby="registAppModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registAppModalLabel">Regist App</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="registappform">
	            <div class="form-group">
	              <div class="form-row">
	                <div class="col-md-6">
	                  <label for="inputName">앱 이름</label>
	                  <input type="text" name="appName" class="form-control" id="inputName" aria-describedby="nameHelp" placeholder="앱이름">
	                </div>
	                <div class="col-md-6">
	                  <label for="inputPackage">패키지명</label>
	                  <input type="text" name="appPackage" class="form-control" id="inputPackage" aria-describedby="nameHelp" placeholder="패키지명">
	                </div>
	              </div>
	            </div>
	            <div class="form-group">
	              <label for="inputURL">URL</label>
	              <input type="text" name="appURL" class="form-control" id="inputURL" aria-describedby="nameHelp" placeholder="URL">
	            </div>
	             <div class="form-group">
	             	<label for="inputCompany">회사 선택</label>
             		  <select name="companyID" class="input-large form-control">
	             		  <c:forEach var="item" items="${companylist}">
			                 <option value="${item.companyID}">${item.companyName}</option>
						    </c:forEach>
                 	  </select>
		         </div>
	            <div class="form-group">
	            <label for="inputImage">이미지 업로드</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="inputImage">이미지찾기</label> 
                    <input type="file" id="inputImage" name="appImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="registApp();">등록하기</a>
          </div>
        </div>
      </div>
    </div>
    
     <!-- ModifyApp Modal -->
    <div class="modal fade" id="modifyAppModal" tabindex="-1" role="dialog" aria-labelledby="modifyAppModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modifyAppModalLabel">Modify App</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          	  <form id="modifyappform">
          	  <input type="hidden" id="modifyID" value="temp" name="appID">
	            <div class="form-group">
	              <div class="form-row">
	                <div class="col-md-6">
	                  <label for="modifyName">앱 이름</label>
	                  <input type="text" name="appName" class="form-control" id="modifyName" aria-describedby="nameHelp" placeholder="앱이름">
	                </div>
	                <div class="col-md-6">
	                  <label for="modifyPackage">패키지명</label>
	                  <input type="text" name="appPackage" class="form-control" id="modifyPackage" aria-describedby="nameHelp" placeholder="패키지명">
	                </div>
	              </div>
	            </div>
	            <div class="form-group">
	              <label for="modifyURL">URL</label>
	              <input type="text" name="appURL" class="form-control" id="modifyURL" aria-describedby="nameHelp" placeholder="URL">
	            </div>
	             <div class="form-group">
	             	<label for="modifyCompany">회사 선택</label>
             		  <select name="companyID" class="input-large form-control">
	             		  <c:forEach var="item" items="${companylist}">
			                 <option value="${item.companyID}" id="modify_${item.companyID}">${item.companyName}</option>
						    </c:forEach>
                 	  </select>
		         </div>
	            <div class="form-group">
	            <label for="modifyImage">이미지 업로드(부재시 기존이미지 사용)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="modifyImage">이미지찾기</label> 
                    <input type="file" id="modifyImage" name="appImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	              <label for="modifyEnable">활성여부</label><br>
	              <label class="radio-inline">
			     	 <input type="radio" id="modifyEnable" value="true" name="appEnable">활성
				  </label>
				  <label class="radio-inline">
				     <input type="radio" id="modifyDisable" value="false" name="appEnable">비활성
				  </label>
	            </div>
	          </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="#" onclick="modifyApp();">수정하기</a>
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
		   var fileTarget = $('.filebox .upload-hidden');

		    fileTarget.on('change', function(){
		        if(window.FileReader){
		            // 파일명 추출
		            var filename = $(this)[0].files[0].name;
		        } 

		        else {
		            // Old IE 파일명 추출
		            var filename = $(this).val().split('/').pop().split('\\').pop();
		        };

		        $(this).siblings('.upload-name').val(filename);
		    });

		    //preview image 
		    var imgTarget = $('.preview-image .upload-hidden');

		    imgTarget.on('change', function(){
		        var parent = $(this).parent();
		        parent.children('.upload-display').remove();

		        if(window.FileReader){
		            //image 파일만
		            if (!$(this)[0].files[0].type.match(/image\//)) return;
		            
		            var reader = new FileReader();
		            reader.onload = function(e){
		                var src = e.target.result;
		                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
		            }
		            reader.readAsDataURL($(this)[0].files[0]);
		        }

		        else {
		            $(this)[0].select();
		            $(this)[0].blur();
		            var imgSrc = document.selection.createRange().text;
		            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

		            var img = $(this).siblings('.upload-display').find('img');
		            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
		        }
		    });
		});
		 function registApp(){
			$.ajax({
				url:"/registapp",
				type: "POST",
				data: new FormData($("#registappform")[0]),
				enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false,
		        cache: false,
		        success: function () {
		        	location.reload();
		        },
		        error: function () {
		        }
			});
		}
		 function modifyApp(){
				$.ajax({
					url:"/modifyapp",
					type: "POST",
					data: new FormData($("#modifyappform")[0]),
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
					url:"/getapp",
					type: "POST",
					data : {'appID':id},
					dataType  : 'json',
			        success: function (data) {
			        	 $("#modifyName").val(data.appName);
			        	 $("#modifyPackage").val(data.appPackage);
			        	 $("#modifyURL").val(data.appURL);
			        	 $("#modify_"+data.companyID).prop("selected", true);
			           	 if(data.appEnable)
			        	{
			        		 $("#modifyEnable").prop("checked", true)
			        	}
			        	 else
			        	{
			        		 $("#modifyDisable").prop("checked", true)
			        	}
			        	 $("#modifyID").val(data.appID);
			        	 $("#modifyAppModal").modal('show');
			        },
			        error: function () {
			        }
				})
			}
    </script>
  </body>

</html>
