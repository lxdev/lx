<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+(request.getServerPort() == 80 ? "": ":"+ request.getServerPort())+path; 
	String actionUrl = basePath + "/ask/home";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>留学芒果</title>
		<jc:plugin name="jquery_new"/>
		<jc:plugin name="jquery_ui"/>
		<jc:plugin name="bootstrap3"/>
		<jc:plugin name="bootstrap_main"/>
	</head>
	<body>
		<div class="container-fluid">
    		<s:action namespace="/user" name="head" executeResult="true"/>
    	</div>
		<div class="container-fluid">
			<div class="template-page-wrapper">
				<s:action namespace="/user" name="left" executeResult="true"/>
				<div class="templatemo-content-wrapper">
        			<div class="templatemo-content">
    					<div class="panel panel-primary">
    						<div class="panel-heading">我的问题
    							<a class="btn btn-primary" role="button" href="../ask/my_ask_modify" >新增</a>
    						</div>
                  			<div class="panel-body">
                    			<table class="table table-striped">
                    				<thead>
				                        <tr>
				                          <th>问题标题</th>
				                          <th>问题内容</th>
				                          <th>问题类型</th>
				                          <th>状态</th>
				                          <th>回答人</th>
				                          <th>时间</th>
				                          <th>操作</th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    	<s:iterator value="#myAsks">
				                    		<tr>
					                    		<td><s:property value="title"/></td>
					                    		<td><s:property value="body"/></td>
					                    		<td>
					                    			<s:if test="ask_type == 1">指定顾问</s:if>
					                    			<s:else>不指定顾问提问</s:else>
					                    		</td>
					                    		<td>
					                    			<s:if test="status==0">草稿</s:if>
					                    			<s:elseif test="status==1">提交</s:elseif>
					                    			<s:elseif test="status==2">回答中</s:elseif>
					                    			<s:else>完成</s:else>
					                    		</td>
					                    		<td><s:if test="reply_user_id>0"><s:property value="reply_user.full_name"/></s:if>
					                    			<s:else>暂无</s:else>
					                    		</td>
					                    		<td><s:property value="created_date"/></td>
					                    		<td>
					                    			<a class="btn btn-primary" role="button" href="../ask/my_ask_chart?id=<s:property value="id"/>">交谈</a>
					                    			<a class="btn btn-default" role="button" href="../ask/my_ask_modify?id=<s:property value="id"/>">编辑</a>
					                    			<button type="button" class="btn btn-danger" value="删除" data-id='<s:property value="id"/>' onclick="remove(this)"></button>
					                    		</td>
					                    	</tr>
				                    	</s:iterator>
				                    </tbody>
                    			</table>
    						</div>
    					</div>
        			</div>
        		</div>
		    </div>
		</div>
	
		<script type="text/javascript">
			var remove = function(obj){
				var id = $(obj).attr("data-id");
				alert(id);
				return;
				if(confirm("确认要删除吗？")){
					var url = '<s:url value="../ask/my_ask_remove"/>';
		            var option = { id: id };
		            jQuery.post(url,option,
		                    function(data)
		                    {
		                        if(data.message != ""){
		                            alert(data.message);
		                            return;
		                        }
		                        alert("删除成功！");
		                        $(obj).parents("tr").remove();
		                    },
		             "json");
				}
			}
		</script>
	</body>
</html>
