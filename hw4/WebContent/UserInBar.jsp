<%@page import="databean.UserBean"%>

<%
	UserBean user = (UserBean) session.getAttribute("user");
	UserBean[] users = (UserBean[]) request.getAttribute("users");
	if ((users!= null) && (users.length > 0)) {
		for (int i=0; i < users.length; i++) {
			UserBean userI = users[i];
%>
<li class="nav-itesm">
	<form method="POST" action=
		<%
			if(user == null){
				out.print("\"Visitor\"");
			}
			else if((user.getEmail().equals(userI.getEmail())) && (user.getLastName().equals(userI.getLastName())))
			{
				out.print("\"Home\"");
			}
			else{
				out.print("\"Visitor\"");
			}
		%>
		>
		<button class="nav-link" type="submit" name="index" value=<%=Integer.toString(i)%>>
			<%=userI.getFirstName() + " " + userI.getLastName()%>
		</button>
	</form>
</li>

<%
		}
	}
%>
