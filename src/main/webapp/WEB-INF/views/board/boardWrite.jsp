<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("�ۼ��Ϸ�");
					
					alert("�޼���:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("����");
			    }
			});
		});
	});
</script>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>last demo</title>
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
<script>
    //�߰� ��ư
    $(document).on("click","button[name=addStaff]",function(){
        
        var addStaffText =     '<tr name="trStaff">'+
		'<td>'+
		'<table id="mytable" border ="1"> '+
			'<tr>'+
				'<td width="120" align="center">'+
				'Title'+
				'</td>'+
				'<div id="box">'+
				'<td width="400">'+
				'<input type="text" name="boardTitle"  id="search_value" size="50" value="${board.boardTitle}">'+
			'	</td>'+
			'</tr>'+
			'<tr>'+
				'<td height="300" align="center">'+
				'Comment'+
			'	</td>'+
			'	<td valign="top">'+
			'	<textarea name="boardComment" id="search_value"  rows="20" cols="55">${board.boardComment}</textarea>'+
			'	</td>'+
			'</tr>'+
			'</div>'+
			'<tr>'+
				'<td align="center">'+
				'Writer'+
				'</td>'+
			'<td>'+
				'</td>'+
			'</tr>'+
		'</table>'+
		'<button class="btn btn-default" name="delStaff">����</button>'+
	'</td>'+
'</tr>;'
            
        var trHtml = $( "tr[name=trStaff]:last" ); //last�� ����Ͽ� trStaff��� ���� ���� ������ �±� ȣ��
        
        trHtml.after(addStaffText); //������ trStaff�� �ڿ� ���δ�.
        
    });
    
    //���� ��ư
    $(document).on("click","button[name=delStaff]",function(){
        
        var trHtml = $(this).parent().parent();
        
        trHtml.remove(); //tr �ױ� ����
        
    });
</script>
<button name="addStaff">���̺� �߰�</button>
<form class="boardWrite">
	<tr>
	<table align="center" id="box">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="�ۼ�">
			</td>
		</tr>
		<tr name="trStaff">
			<td>
				<table id="mytable" border ="1"> 
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<div id="box">
						<td width="400">
						<input type="text" name="boardTitle"  id="search_value" size="50" value="${board.boardTitle}">
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardComment" id="search_value"  rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
					</div>
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
			</td>
		</tr>
	</table>
	</tr>	
		</form>
</body>
</html>