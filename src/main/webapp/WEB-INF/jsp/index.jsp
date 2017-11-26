<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  	<script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/css/bootstrap-datetimepicker.min.css" rel="stylesheet" /> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment-with-locales.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
	<form>
   <input id="fromDate" type="text">
   <input id="toDate" type="text">
   </form>
   <script type="text/javascript">
	$(document).ready(function(){
			 $('#fromDate').datetimepicker({
			 		language : 'ko', // 화면에 출력될 언어를 한국어로 설정한다.
			 		pickTime : false, // 사용자로부터 시간 선택을 허용하려면 true를 설정하거나 pickTime 옵션을 생략한다. 
			 		defalutDate : new Date() // 기본값으로 오늘 날짜를 입력한다. 기본값을 해제하려면 defaultDate 옵션을 생략한다.
			 	});
			 $('#toDate').datetimepicker({ language : 'ko', pickTime : false, defalutDate : new Date() });
		});
</script>
</body>

</html>