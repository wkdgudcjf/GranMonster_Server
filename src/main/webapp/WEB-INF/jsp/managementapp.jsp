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
			   <Button type="button" class="btn btn-success" data-toggle="modal" data-target="#registAppModal">앱 등록(Regist App)</Button>
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
              <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0" style="table-layout:fixed;">
              	<colgroup>
					<col style="width:5%;">
					<col style="width:10%;">
					<col style="width:10%;">
					<col style="width:20%;">
					<col style="width:10%;">
					<col style="width:10%;">
					<col style="width:9%;">
					<col style="width:13%;">
					<col style="width:13%;">
				</colgroup>
                <thead>
                  <tr>
                    <th>활성여부</th>
                    <th>앱이름</th>
                    <th>회사명</th>
                    <th>마켓경로</th>
                    <th>패키지명</th>
                    <th>등록시간</th>
                    <th>아이콘 이미지</th>
                    <th>가로 배너 이미지</th>
                    <th>세로 배너 이미지</th>
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
                    <th>Icon Image</th>
                    <th>Horizontal Banner Image</th>
                    <th>Vertical Banner Image</th>
                  </tr>
                </tfoot>
                <tbody>
                  <c:forEach var="item" items="${applist}">
	                <tr>
				        <td>
				        <c:choose>
						<c:when test="${item.appEnable==true}"> 
						    <img src="/img/enable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/img/disable.png" style="max-width: 30px; max-height: 30px;"> 
						</c:otherwise>
						</c:choose>
						</td>
				         <td><a href="#" onclick="modifyAppModal(${item.appID});">${item.appName}</a>
				         / <a href="#" onclick="goToEvent(${item.appID});">이벤트</a></td>
				        <td>${item.companyName}</td>
				        <td><a href="${item.appURL}">${item.appURL}</a></td>
				        <td>${item.appPackage}</td>
				        <td>${item.appDateTime}</td>
				        <td><img src="image/appIcon/${item.appImageIconPath}"  style="max-width: 57px; max-height: 57px;"></td>
				        <td><img src="image/appHBanner/${item.appImageHBannerPath}"  style="max-width: 100px; max-height: 150px;"></td>
				        <td><img src="image/appVBanner/${item.appImageVBannerPath}"  style="max-width: 150px; max-height: 100px;"></td>
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
	                  <label for="inputAppName">앱 이름(고유)</label>
	                  <input type="text" name="appName" class="form-control" id="inputAppName" aria-describedby="nameHelp" placeholder="앱이름">
	                </div>
	                <div class="col-md-6">
	                  <label for="inputAppPackage">패키지명</label>
	                  <input type="text" name="appPackage" class="form-control" id="inputAppPackage" aria-describedby="nameHelp" placeholder="패키지명">
	                </div>
	              </div>
	            </div>
	            <div class="form-group">
	              <label for="inputAppURL">URL</label>
	              <input type="text" name="appURL" class="form-control" id="inputAppURL" aria-describedby="nameHelp" placeholder="URL">
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
	            <label for="inputAppIconImage">아이콘 이미지 업로드(57x57)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="inputAppIconImage">이미지찾기</label> 
                    <input type="file" id="inputAppIconImage" name="appIconImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	            <label for="inputAppHBannerImage">가로형 배너 이미지 업로드(635x395)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="inputAppHBannerImage">이미지찾기</label> 
                    <input type="file" id="inputAppHBannerImage" name="appHBannerImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	            <label for="inputAppVBannerImage">세로형 배너 이미지 업로드(580x285)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="inputAppVBannerImage">이미지찾기</label> 
                    <input type="file" id="inputAppVBannerImage" name="appVBannerImage" class="upload-hidden" accept="image/*"> 
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
          	  <input type="hidden" id="modifyAppID" value="temp" name="appID">
	            <div class="form-group">
	              <div class="form-row">
	                <div class="col-md-6">
	                  <label for="modifyAppName">앱 이름(고유)</label>
	                  <input type="text" name="appName" class="form-control" id="modifyAppName" aria-describedby="nameHelp" placeholder="앱이름">
	                </div>
	                <div class="col-md-6">
	                  <label for="modifyAppPackage">패키지명</label>
	                  <input type="text" name="appPackage" class="form-control" id="modifyAppPackage" aria-describedby="nameHelp" placeholder="패키지명">
	                </div>
	              </div>
	            </div>
	            <div class="form-group">
	              <label for="appKey">고유키</label>
	              <p id="appKey"></p>
	            </div>
	            <div class="form-group">
	              <label for="modifyAppURL">URL</label>
	              <input type="text" name="appURL" class="form-control" id="modifyAppURL" aria-describedby="nameHelp" placeholder="URL">
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
	            <label for="modifyAppIconImage">아이콘 이미지 업로드(57x57)(부재시 기존이미지 사용)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="modifyAppIconImage">이미지찾기</label> 
                    <input type="file" id="modifyAppIconImage" name="appIconImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	            <label for="modifyAppHBannerImage">가로형 배너 이미지 업로드(635x395)(부재시 기존이미지 사용)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="modifyHAppBannerImage">이미지찾기</label> 
                    <input type="file" id="modifyAppHBannerImage" name="appHBannerImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	            <label for="modifyAppVBannerImage">세로형 배너 이미지 업로드(580x285)(부재시 기존이미지 사용)</label>
	             <div class="filebox bs3-primary preview-image">
                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
                      <label for="modifyAppVBannerImage">이미지찾기</label> 
                    <input type="file" id="modifyAppVBannerImage" name="appVBannerImage" class="upload-hidden" accept="image/*"> 
                  </div>
	            </div>
	            <div class="form-group">
	              <label for="modifyEnable">활성여부</label><br>
	              <label class="radio-inline">
			     	 <input type="radio" id="modifyAppEnable" value="true" name="appEnable">활성
				  </label>
				  <label class="radio-inline">
				     <input type="radio" id="modifyAppDisable" value="false" name="appEnable">비활성
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
			 var inputAppName = $('#inputAppName'),
			 inputAppPackage = $('#inputAppPackage'),
			 inputAppURL = $('#inputAppURL'),
			 inputAppIconImage = $('#inputAppIconImage');
			 inputAppHBannerImage = $('#inputAppHBannerImage');
			 inputAppVBannerImage = $('#inputAppVBannerImage');
			 if (inputAppName.val().length == 0) {
				 alert('앱 이름을 입력하세요.');
				 return;
			 }
			 if (inputAppPackage.val().length == 0) {
				 alert('패키지명을 입력하세요.');
				 return;
			 }
			 if (inputAppURL.val().length == 0) {
				 alert('URL을 입력하세요.');
				 return;
			 }
			 if (inputAppIconImage.val().length == 0) {
				 alert('아이콘 이미지를 선택하세요.');
				 return;
			 }
			 if (inputAppHBannerImage.val().length == 0) {
				 alert('가로형 배너 이미지를 선택하세요.');
				 return;
			 }
			 if (inputAppVBannerImage.val().length == 0) {
				 alert('세로형 배너 이미지를 선택하세요.');
				 return;
			 }
			$.ajax({
				url:"/registapp",
				type: "POST",
				data: new FormData($("#registappform")[0]),
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
		 function modifyApp(){
			 var inputAppName = $('#modifyAppName'),
			 inputAppPackage = $('#modifyAppPackage'),
			 inputAppURL = $('#modifyAppURL');
			 
			 if (inputAppName.val().length == 0) {
				 alert('앱 이름을 입력하세요.');
				 return;
			 }
			 if (inputAppPackage.val().length == 0) {
				 alert('패키지명을 입력하세요.');
				 return;
			 }
			 if (inputAppURL.val().length == 0) {
				 alert('URL을 입력하세요.');
				 return;
			 }
				$.ajax({
					url:"/modifyapp",
					type: "POST",
					data: new FormData($("#modifyappform")[0]),
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
		 function modifyAppModal(id){
				$.ajax({
					url:"/getapp",
					type: "POST",
					data : {'appID':id},
					dataType  : 'json',
			        success: function (data) {
			        	 $("#modifyAppName").val(data.appName);
			        	 $("#modifyAppPackage").val(data.appPackage);
			        	 $("#appKey").text(data.appKey);
			        	 $("#modifyAppURL").val(data.appURL);
			        	 $("#modify_"+data.companyID).prop("selected", true);
			           	 if(data.appEnable)
			        	{
			        		 $("#modifyAppEnable").prop("checked", true)
			        	}
			        	 else
			        	{
			        		 $("#modifyAppDisable").prop("checked", true)
			        	}
			        	 $("#modifyAppID").val(data.appID);
			        	 $("#modifyAppModal").modal('show');
			        },
			        error:function(request,status,error){
			        	alert(request.responseText);
			        }
				})
			}
		 function goToEvent(appID){
			 var form = document.createElement('form');
			 var objs;
			 objs = document.createElement('input');
			 objs.setAttribute('type', 'hidden');
			 objs.setAttribute('name' , 'appID');
			 objs.setAttribute('value', appID);
			 form.appendChild(objs);
			 form.setAttribute('method', 'post');
			 form.setAttribute('action', "/managementappevent");
			 document.body.appendChild(form);
			 form.submit();
			}
    </script>
  </body>

</html>
