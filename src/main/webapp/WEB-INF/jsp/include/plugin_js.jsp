<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- jQuery 3 -->
<script src="bower_components_r/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components_r/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="bower_components_r/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="bower_components_r/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="bower_components_r/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="bower_components_r/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist_r/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist_r/js/demo.js"></script>

<!-- page script -->
<script>
  $(function () {
    $('#mytable').DataTable({
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
    })
  })
</script>