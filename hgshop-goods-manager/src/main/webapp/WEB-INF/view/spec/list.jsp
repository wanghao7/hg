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
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModal" >添加规格</button>&nbsp;&nbsp;
	<!-- 	批量删除按钮 -->
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" onclick="plsc()"  >批量删除</button>&nbsp;&nbsp;
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">添加规格</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="form">
          <div class="form-group ">
            <label for="recipient-name"  class="col-form-label">规格:</label>
            <input type="text" name="specName"  >
          </div>
          <div class="form-group">
            <label for="message-text"  class="col-form-label">属性:</label>
             <input type="text" id="options" name="options[0].optionName" >
             <input onclick="addProp()" type="button" value="添加属性" >
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">离开</button>
        <button type="button" onclick="commitSpec()" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

	<table class="table">
		<tr> 
			<th><input type="checkbox" id="id"  >id
				<button type="button" onclick="qx()" class="btn btn-outline-secondary">全选</button>
				<button type="button" onclick="qbx()" class="btn btn-outline-success">全不选</button>
				<button type="button" onclick="re()" class="btn btn-outline-danger">反选</button>
			 </th>
			<th>名称</th>
			<th>详情</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageInfo.list}" var="b">
			<tr>
				<td><input type="checkbox" value="${b.id}"  onclick="idChecked()" name="check" ></td>
				<td>${b.id}</td>
				<td>${b.specName}</td>
				<td>
					<c:forEach items="${b.options}" var="op" >
						 ${op.optionName}&nbsp;&nbsp;  &nbsp;&nbsp;
					</c:forEach>
				</td>
				<td>
					<button type="button" class="btn btn-warning">修改</button>
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
		$("#div").load("/spec/list?page="+page+'&name='+name );
	}
	
	var index=1;
	function addProp(){
		$("#form").append('<div id="addSpec" class="form-group">'+
	            '<label for="message-text"  class="col-form-label">属性:</label>'+
	             '<input type="text" name="options['+index+'].optionName" >'+
	             '<input onclick="addProp()" type="button" value="添加属性" >'+
	             '<button onclick="$(this).parent().remove()">删除</button>'+
          		 '</div>');
          index++;		 
	}
	
	/**
	  提交数据	
	*/
	function commitSpec(){
		//addspec
		var formData = new FormData($("#form")[0]);
		$.ajax({url:"/spec/add",
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
	
	function query(){
		var nam =$("[name=name]").val();
		$("#div").load("/spec/list?name="+nam );
	}
	
	//模态框隐藏之后调用此函数
	$('#exampleModal').on('hidden.bs.modal', function (e) {
		  // do something...
		resetAddFrom();//调用resetAddFrom函数
	})
	
	//添加规格之后重置添加的模态框
	function resetAddFrom(){
		$("#addSpec").each(function(){
			$(this).remove();
		})
		$("[name=specName]").val("");
		$("#options").val("");
		
	}
	
	function qx() {
		$("[name=check]").each(function(){
			this.checked="true";
		})
		// 判断是否所有的都被选中了
		var allSelected = $("[name=check]").length==$("[name=check]:checked").length;
		//设置全选的框
		$("#id").prop("checked",allSelected)
	}
	function qbx() {
		$("[name=check]").each(function(){
			$(this).prop("checked",false)
		})
		// 判断是否所有的都被选中了
		var allSelected = $("[name=check]").length==$("[name=check]:checked").length;
		//设置全选的框
		$("#id").prop("checked",allSelected)
	}
	function re() {
		$("[name=check]").each(function(){
			this.checked=!(this).checked;
		})
		// 判断是否所有的都被选中了
		var allSelected = $("[name=check]").length==$("[name=check]:checked").length;
		//设置全选的框
		$("#id").prop("checked",allSelected)
	}
	function idChecked() {
		// 判断是否所有的都被选中了
		var allSelected = $("[name=check]").length==$("[name=check]:checked").length;
		//设置全选的框
		$("#id").prop("checked",allSelected)
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
		$("#div").load("/spec/list?name="+mohu+'&page=${pageInfo.pageNum}');
	}
	function dele(id){
		var ids = new Array();
		ids.push(id);
		$.post("/spec/delSpecBatch",{ids:ids},function(data){
			if(data="success"){
				alert("删除成功")
				refresh();
			}else{
				alert("删除失败")
			}
		})
	}
</script>

