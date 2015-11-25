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
    						<div class="panel-heading">我关注的问题</div>
                  			<div class="panel-body">
                    			<table class="table table-striped">
                    				<thead>
				                        <tr>
				                          <th>问题标题</th>
				                          <th>问题内容</th>
				                          <th>提问人</th>
				                          <th>回答人</th>
				                          <th>操作</th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    	<s:iterator value="#myFollows">
				                    		<tr>
					                    		<td><s:property value="source_title"/></td>
					                    		<td><s:property value="source_content"/></td>
					                    		<td><s:property value="source_user.full_name"/></td>
					                    		<td>
					                    			<s:if test="source_user2 != null">
					                    				<s:property value="source_user2.full_name"/>
					                    			</s:if>
					                    			<s:else>无</s:else>
					                    			
					                    		</td>
					                    		<td>
					                    			<button type="button" class="btn btn-danger" value="取消关注" data-id='<s:property value="id"/>' onclick="remove(this)"></button>
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
				var url = '<s:url value="../crm/my_follow_remove"/>';
	            var option = { id: id };
	            jQuery.post(url,option,
	                    function(data)
	                    {
	                        if(data.message != ""){
	                            alert(data.message);
	                            return;
	                        }
	                        alert("取消关注成功！");
	                        $(obj).parents("tr").remove();
	                    },
	             "json");
			}
		</script>
		
	</body>
</html>
