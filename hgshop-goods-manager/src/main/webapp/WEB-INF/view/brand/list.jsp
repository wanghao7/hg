<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<!-- 模糊 -->
	<div class="form-row">
		<div class="col-2">
			<input  name="name" class="form-control" value="${name }" >
	    </div>
	<!-- 	查询按钮 -->
		<button type="button" class="btn btn-primary btn-sm" onclick="query()">查询 </button>&nbsp;&nbsp;
	<!-- 	添加按钮 -->
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal" >添加品牌</button>&nbsp;&nbsp;
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">添加品牌</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="form">
          <div class="form-group ">
            <label for="recipient-name"  class="col-form-label">名称:</label>
            <input type="text" name="name"  >
          </div>
          <div class="form-group">
            <label for="message-text"  class="col-form-label">首字母:</label>
             <input type="text" id="options" name="firstChar" >
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">离开</button>
        <button type="button" onclick="commitBrand()" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
<!-- 修改的模态框 -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">修改品牌</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="updateBrand">
          <div class="form-group ">
            <label for="recipient-name"  class="col-form-label">名称:</label>
            <input type="hidden" name="id" id="id" >
            <input type="text" name="name" id="name" >
          </div>
          <div class="form-group">
            <label for="message-text"  class="col-form-label">首字母:</label>
             <input type="text"  name="firstChar" id="char" >
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">离开</button>
        <button type="button" onclick="updateBrand()" class="btn btn-primary">确认修改</button>
      </div>
    </div>
  </div>
</div>

	<table class="table">
		<tr> 
			<th><input type="checkbox" id="id"  >id
			 </th>
			<th>名称</th>
			<th>首字母</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageInfo.list}" var="b">
			<tr>
				<td>${b.id}</td>
				<td>${b.name}</td>
				<td>${b.firstChar}</td>
				<td>
					<button type="button" onclick="openUpdateBrand(${b.id})"  class="btn btn-warning">修改</button>
					<button type="button"  onclick="dele(${b.id})" class="btn btn-info">删除</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</td>
		</tr>
		
		
	</table>
<script type="text/javascript">

	function goPage(page){
		var name =$("[name=name]").val();
		$("#div").load("/brand/list?page="+page+'&name='+name );
	}
	
	
	/**
	  提交数据	
	*/
	function commitBrand(){
		//addspec
		var formData = new FormData($("#form")[0]);
		$.ajax({url:"/brand/add",
// 			  dataType:"json",!!!!!!!!!
			  data:formData,
			  // 让jQuery 不要再提交数据之前进行处理
			  processData : false,
			  // 提交的数据不能加消息头
			  contentType : false,
			  // 提交的方式 
			  type:"post",
			  // 成功后的回调函数
			  success:function(data){
				  if(data=="success"){
					  alert("数据提交成功")
					 $('#exampleModal').modal('hide');//提交后直接隐藏boostrap函数 
				  }else{
					 alert("数据保存失败")
				 }
			  }
		})
		
	}
	/**
	  修改数据	
	*/
	function updateBrand(){
		var formData = new FormData($("#updateBrand")[0]);
		$.ajax({url:"/brand/update",
// 			  dataType:"json",!!!!!!!!!
			  data:formData,
			  // 让jQuery 不要再提交数据之前进行处理
			  processData : false,
			  // 提交的数据不能加消息头
			  contentType : false,
			  // 提交的方式 
			  type:"post",
			  // 成功后的回调函数
			  success:function(data){
				  if(data=="success"){
					  alert("数据修改成功")
					 $('#exampleModal2').modal('hide');//提交后直接隐藏boostrap函数 
				  }else{
					 alert("数据修改失败")
				 }
			  }
		})
		
	}
	
	function query(){
		var nam =$("[name=name]").val();
		$("#div").load("/brand/list?name="+nam );
	}
	// 弹出修改的窗口
	function openUpdateBrand(id){
		
		$.post("/brand/getBrand",{id:id},function(data){
			//规格的id
			$("#id").val(data.id);
			$("#name").val(data.name);
			$("#char").val(data.firstChar);
			
			$("#exampleModal2").modal("show");
			
			});
		
	}
	//模态框隐藏之后调用此函数
// 	$('#exampleModal2').on('show.bs.modal', function (e) {
// 		  // do something...
// 		  $("[name=name]").val("123");
// 		  $("#exampleModal2").modal("show");
// // 		resetAddFrom();//调用resetAddFrom函数
// 	})
	//模态框隐藏之后调用此函数
	$('#exampleModal').on('hidden.bs.modal', function (e) {
		  // do something...
		resetAddFrom();//调用resetAddFrom函数
	})
	//模态框2隐藏之后调用此函数
	$('#exampleModal2').on('hidden.bs.modal', function (e) {
		  // do something...
		refresh();
	})
	
	//添加规格之后重置添加的模态框
	function resetAddFrom(){
		$("[name=name]").val("");
		$("#options").val("");
		
	}
	
	//批量删除
	function plsc() {
		
		if($("[name=check]:checked").length<=0){
			alert("请选中要删除的数据!");
		}else{
			var ids = new Array();
			$("[name=check]:checked").each(function(){
				ids.push($(this).val());
			})
			if(confirm("您确认删除这些数据吗?")){
				$.post("/spec/delSpecBatch",{ids:ids},function(data){
					if(data="success"){
						alert("删除成功")
						refresh();
					}else{
						alert("删除失败")
					}
				})
			}
		}
	}
	function refresh(){
		var mohu =$("[name=name]").val();
		$("#div").load("/brand/list?name="+mohu+'&page=${pageInfo.pageNum}');
	}
	function dele(id){
		var ids = new Array();
		ids.push(id);
		$.post("/brand/delBrand",{ids:ids},function(data){
			if(data="success"){
				alert("删除成功")
				refresh();
			}else{
				alert("删除失败")
			}
		})
	}
</script>


