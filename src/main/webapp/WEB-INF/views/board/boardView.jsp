<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<body>
<script>
//싱글 삭제처리
	$j(document).ready(function(){
		
		$j("#btn").on("click",function(){
			var $frm = $j('.deleteBoard :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/deleteBoardAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("삭제완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("삭제실패");
			    }
			    
			   
			});
		});
	});
</script>
<body>
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td align="right">
			<a href="/board/boardList.do">List</a>
				<a href="/board/${board.boardType}/${board.boardNum}/updateBoard.do?pageNo=${pageNo}">수정</a>
	              <input type="hidden" name="boardNum" value="${board.boardNum}" readonly="readonly">
	              <form method="post" action="${pageContext.request.contextPath}/board/deleteBoardAction.do?boardNum=${board.boardNum}">
	              		<input type="button" id="btn" value="삭제">
	              </form>
		</td>
	</tr>
</table>
</body>
</html>