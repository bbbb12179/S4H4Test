<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/common/JPageHeader.jsp" flush="yes" /><!-- 帶入相關設定與樣式-->
<!DOCTYPE html>
<html lang="zh_TW">
<head>
<c:url var="home" value="/" scope="request" />
<title><spring:message code="acie.title" /></title>
</head>


<script type="text/javascript">
	$(function(){
  		$("#listTable").tablesorter();
  		
  		$("#checkboxAll").click(function(){ 
   			var checkbox = document.getElementsByName("checkbox");
			var allcheckbox = document.getElementsByName("checkboxAll");
			if (allcheckbox[0].checked == true){
				for(var i=0;i<checkbox.length;i++){
				   	checkbox[i].checked = true;		   	
				}
			}else{
				for(var i=0;i<checkbox.length;i++){
				   	checkbox[i].checked = false;
				}
			}
		}); 
		
		
		$("#connectBtn").click(function(){ 
			var checkbox = document.getElementsByName("checkbox");
			var nodeArray = new Array();
			for(var i=0,j=0;i<checkbox.length;i++){
				if(checkbox[i].checked == true){
					nodeArray[j]={"nodeId" : document.getElementsByName("nodeIdArray")[i].value,
									"nodeQueueName" : document.getElementsByName("nodeQueueNameArray")[i].value,
									"nodeIp" : document.getElementsByName("nodeIpArray")[i].value,
									"nodePort" : document.getElementsByName("nodePortArray")[i].value
					};
					j++;
				}
			}
			
			$.ajax({
				async:false,
				type:"GET",
				contentType : "application/json",
				url:"/adms/connectNode",
				data: {connectNodeArray : JSON.stringify(nodeArray) },
		        datatype : 'json',
		        success : function(){
		        	window.location.reload();
		        }
	        });     
		});  
		
		
		$("#disConnectBtn").click(function(){ 
			var checkbox = document.getElementsByName("checkbox");
			var nodeArray = new Array();
			for(var i=0,j=0;i<checkbox.length;i++){
				if(checkbox[i].checked == true){
					nodeArray[j]={"nodeId" : document.getElementsByName("nodeIdArray")[i].value,
									"nodeQueueName" : document.getElementsByName("nodeQueueNameArray")[i].value,
									"nodeIp" : document.getElementsByName("nodeIpArray")[i].value,
									"nodePort" : document.getElementsByName("nodePortArray")[i].value
					};
					j++;
				}
			}
			$.ajax({
				async:false,
				type:"GET",
				contentType : "application/json",
				url:"/adms/disConnectNode",
				data: {disConnectNodeArray : JSON.stringify(nodeArray) },
		        datatype : 'json'
	        });     
			
		});
		
		
	});
</script>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>
				Test
			</h1>
		</div>
		<table id="listTable" class="table table-bordered" >
			<thead>
				<tr>
					<th width="8%" align="center"><spring:message code="acie.select" />&nbsp;<input type="checkbox" name="checkboxAll" id="checkboxAll"></th>
					<th><spring:message code="acie.nodeId" /></th>
					<th><spring:message code="acie.nodeQueueName" /></th>
					<th><spring:message code="acie.nodeIp" /></th>
					<th><spring:message code="acie.nodePort" /></th>
					<th><spring:message code="acie.state" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="node" items="${nodeList}" varStatus="status">
					<tr>
						<td align="center"><input type="checkbox" name="checkbox"></td>
						<td>${node.nodeId }</td>
						<td>${node.nodeQueueName }</td>
						<td>${node.nodeIp }</td>
						<td>${node.nodePort }</td>
						<td>&nbsp;</td>
						<!-- hidden -->
						<td align="left" style="display:none;"><input type="hidden" name="nodeIdArray" value="${node.nodeId}"/></td>
						<td align="left" style="display:none;"><input type="hidden" name="nodeQueueNameArray" value="${node.nodeQueueName}"/></td>
						<td align="left" style="display:none;"><input type="hidden" name="nodeIpArray" value="${node.nodeIp}"/></td>
						<td align="left" style="display:none;"><input type="hidden" name="nodePortArray" value="${node.nodePort}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container">
		<h2>
			<spring:message code="acie.OperationSelect" />
		</h2>
		<button id="connectBtn" type="button" class="btn btn-primary">
			<spring:message code="acie.connect" />
		</button>
		<button id="disConnectBtn" type="button" class="btn btn-primary">
			<spring:message code="acie.disConnect" />
		</button>
		<button id="taskSetBtn" type="button" class="btn btn-primary">
			<spring:message code="acie.task" />
		</button>
	</div>

</body>
</html>