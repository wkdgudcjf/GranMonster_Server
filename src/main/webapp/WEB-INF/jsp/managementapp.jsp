<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>

<jsp:include page="include/head.jsp" flush="false"/>

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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <jsp:include page="include/main_header.jsp" flush="false"/>
  
  <jsp:include page="include/left_column.jsp" flush="false"/>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="padding:20px;">
      <h1>
       	 앱 관리
        <small>Management App</small>
      </h1>
      <ol class="breadcrumb">
        <li><Button type="button" class="btn btn-success btn-flat" data-toggle="modal" data-target="#registAppModal">앱 등록(Regist App)</Button></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">App List</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="mytable" class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>활성</th>
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
						    <img src="/image/enable.png" style="max-width:30px; max-height:30px;"> 
						</c:when> 
						<c:otherwise> 
						    <img src="/image/disable.png" style="max-width:30px; max-height:30px;"> 
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
				        <td><img src="image/appHBanner1/${item.appImageHBannerPath1}"  style="max-width: 100px; max-height: 150px;"
				         onClick="imagePopup('image/appHBanner1/${item.appImageHBannerPath1}','image/appHBanner2/${item.appImageHBannerPath2}','image/appHBanner3/${item.appImageHBannerPath3}')">
				         </td>
				        <td><img src="image/appVBanner1/${item.appImageVBannerPath1}"  style="max-width: 150px; max-height: 100px;"
				         onClick="imagePopup('image/appVBanner1/${item.appImageVBannerPath1}','image/appVBanner2/${item.appImageVBannerPath2}','image/appVBanner3/${item.appImageVBannerPath3}')">
				        </td>
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
  
  <!-- Image Modal -->
    <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header" style="text-align: center;">
            <h1 class="modal-title" id="imageModalLabel">Image List</h1>
          </div>
          <div class="modal-body">
                <!-- Form Element sizes -->
	          <div class="box box-success">
	            <div class="box-header with-border">
	              <h3 class="box-title">이미지 정보</h3>
	            </div>
	            <div class="box-body">
	              <img src="" id="image1" style="max-width: 300px; max-height: 300px;">
	              <br><br>
	              <img src="" id="image2" style="max-width: 300px; max-height: 300px;">
	              <br><br>
	              <img src="" id="image3" style="max-width: 300px; max-height: 300px;">
	            </div>
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
          </div>
        </div>
      </div>
    </div>
    
   <!-- RegistApp Modal -->
    <div class="modal fade" id="registAppModal" tabindex="-1" role="dialog" aria-labelledby="registAppModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header" style="text-align: center;">
            <h1 class="modal-title" id="registAppModalLabel">Regist App</h1>
          </div>
          <div class="modal-body">
          	  <form id="registappform">
	            <div class="form-group">
                  <label for="inputAppName">앱 이름(고유)</label>
                  <input type="text" name="appName" class="form-control" id="inputAppName" aria-describedby="nameHelp" placeholder="앱이름">
	            </div>
	             <div class="form-group">
	                <label for="inputAppPackage">패키지명</label>
	                <input type="text" name="appPackage" class="form-control" id="inputAppPackage" aria-describedby="nameHelp" placeholder="패키지명">
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
	            
	            <!-- Form Element sizes -->
		          <div class="box box-success">
		            <div class="box-header with-border">
		              <h3 class="box-title">가로형 배너 이미지 업로드(635x395)</h3>
		            </div>
		            <div class="box-body">
		                <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="inputAppHBannerImage1">이미지찾기</label> 
		                    <input type="file" id="inputAppHBannerImage1" name="appHBannerImage1" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="inputAppHBannerImage2">이미지찾기</label> 
		                    <input type="file" id="inputAppHBannerImage2" name="appHBannerImage2" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="inputAppHBannerImage3">이미지찾기</label> 
		                    <input type="file" id="inputAppHBannerImage3" name="appHBannerImage3" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		            </div>
		            <!-- /.box-body -->
		          </div>
		          <!-- /.box -->
          
          		<!-- Form Element sizes -->
		          <div class="box box-success">
		            <div class="box-header with-border">
		              <h3 class="box-title">세로형 배너 이미지 업로드(580x285)</h3>
		            </div>
		            <div class="box-body">
		                <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="inputAppVBannerImage1">이미지찾기</label> 
	                    <input type="file" id="inputAppVBannerImage1" name="appVBannerImage1" class="upload-hidden" accept="image/*"> 
	                 	 </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="inputAppVBannerImage2">이미지찾기</label> 
	                    <input type="file" id="inputAppVBannerImage2" name="appVBannerImage2" class="upload-hidden" accept="image/*"> 
	                  	</div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="inputAppVBannerImage3">이미지찾기</label> 
	                    <input type="file" id="inputAppVBannerImage3" name="appVBannerImage3" class="upload-hidden" accept="image/*"> 
	                  </div>
		            </div>
		            <!-- /.box-body -->
		           </div>
	              <!-- /.box -->
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
          <div class="modal-header" style="text-align: center;">
            <h1 class="modal-title" id="modifyAppModalLabel">Modify App</h1>
          </div>
          <div class="modal-body">
          	  <form id="modifyappform">
          	  <input type="hidden" id="modifyAppID" value="temp" name="appID">
	            <div class="form-group">
                  <label for="modifyAppName">앱 이름(고유)</label>
                  <input type="text" name="appName" class="form-control" id="modifyAppName" aria-describedby="nameHelp" placeholder="앱이름">
	            </div>
	            <div class="form-group">
                  <label for="modifyAppPackage">패키지명</label>
                  <input type="text" name="appPackage" class="form-control" id="modifyAppPackage" aria-describedby="nameHelp" placeholder="패키지명">
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
	            
	            <!-- Form Element sizes -->
		          <div class="box box-success">
		            <div class="box-header with-border">
		              <h3 class="box-title">가로형 배너 이미지 업로드(580x285)(부재시 기존이미지 사용)</h3>
		            </div>
		            <div class="box-body">
		                <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="modifyAppHBannerImage1">이미지찾기</label> 
		                    <input type="file" id="modifyAppHBannerImage1" name="appHBannerImage1" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="modifyAppHBannerImage2">이미지찾기</label> 
		                    <input type="file" id="modifyAppHBannerImage2" name="appHBannerImage2" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
		                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
		                      <label for="modifyAppHBannerImage3">이미지찾기</label> 
		                    <input type="file" id="modifyAppHBannerImage3" name="appHBannerImage3" class="upload-hidden" accept="image/*"> 
		                  </div>
		          	   </div>
		            </div>
		            <!-- /.box-body -->
		          </div>
		          <!-- /.box -->
		          
          		<!-- Form Element sizes -->
		          <div class="box box-success">
		            <div class="box-header with-border">
		              <h3 class="box-title">세로형 배너 이미지 업로드(580x285)(부재시 기존이미지 사용)</h3>
		            </div>
		            <div class="box-body">
		                <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="modifyAppVBannerImage1">이미지찾기</label> 
	                    <input type="file" id="modifyAppVBannerImage1" name="appVBannerImage1" class="upload-hidden" accept="image/*"> 
	                 	 </div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="modifyAppVBannerImage2">이미지찾기</label> 
	                    <input type="file" id="modifyAppVBannerImage2" name="appVBannerImage2" class="upload-hidden" accept="image/*"> 
	                  	</div>
		          	   </div>
		              <br>
		              <div class="form-group" style="margin-bottom: 0px;">
			             <div class="filebox bs3-primary preview-image">
	                      <input class="upload-name" value="파일선택" disabled="disabled" style="width: 200px;">
	                      <label for="modifyAppVBannerImage3">이미지찾기</label> 
	                    <input type="file" id="modifyAppVBannerImage3" name="appVBannerImage3" class="upload-hidden" accept="image/*"> 
	                  </div>
		            </div>
		            <!-- /.box-body -->
		           </div>
	              <!-- /.box -->
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
</div>
<!-- ./wrapper -->
<jsp:include page="include/plugin_js.jsp" flush="false"/>
 <!-- Custom scripts for this template -->
	<script type="text/javascript">
	 $(document).ready(function(){
		 $('#navi_app').attr('class',"active");
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
			 inputAppHBannerImage1 = $('#inputAppHBannerImage1');
			 inputAppVBannerImage1 = $('#inputAppVBannerImage1');
			 inputAppHBannerImage2 = $('#inputAppHBannerImage2');
			 inputAppVBannerImage2 = $('#inputAppVBannerImage2');
			 inputAppHBannerImage3 = $('#inputAppHBannerImage3');
			 inputAppVBannerImage3 = $('#inputAppVBannerImage3');
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
			 if (inputAppHBannerImage1.val().length == 0) {
				 alert('가로형 배너 이미지를 선택하세요. - 1');
				 return;
			 }
			 if (inputAppVBannerImage1.val().length == 0) {
				 alert('세로형 배너 이미지를 선택하세요. - 1');
				 return;
			 }
			 if (inputAppHBannerImage2.val().length == 0) {
				 alert('가로형 배너 이미지를 선택하세요. - 2');
				 return;
			 }
			 if (inputAppVBannerImage2.val().length == 0) {
				 alert('세로형 배너 이미지를 선택하세요. - 2');
				 return;
			 }
			 if (inputAppHBannerImage3.val().length == 0) {
				 alert('가로형 배너 이미지를 선택하세요. - 3');
				 return;
			 }
			 if (inputAppVBannerImage3.val().length == 0) {
				 alert('세로형 배너 이미지를 선택하세요. - 3');
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
		 function imagePopup(image1,image2,image3){
		     $("#image1").attr("src",image1);
		     $("#image2").attr("src",image2);
		     $("#image3").attr("src",image3);
			 $("#imageModal").modal('show');
		 }
    </script>
</body>
</html>
