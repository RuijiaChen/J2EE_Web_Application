<%@page import="databean.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!(empty users)}">
	<c:forEach var="entry" varStatus="loop" items="${users}">
		<li class="nav-item">
			<c:choose>
				<c:when test="${(empty user)}">
					<form method="POST" action="visit.do">
						<input type="hidden" name="userEmail" value="${entry.email}" />
						<button class="nav-link" type="submit">
							${entry.firstName} ${entry.lastName}
						</button>
					</form>	
				</c:when>
				<c:when test="${entry.email != user.email}">
					<form method="POST" action="visit.do">
						<input type="hidden" name="userEmail" value="${entry.email}" />
						<button class="nav-link" type="submit">
							${entry.firstName} ${entry.lastName}
						</button>
					</form>	
						
				</c:when>
				<c:otherwise>
					<form method="POST" action="home.do">
						<input type="hidden" name="userEmail" value="${entry.email}" />
						<button class="nav-link" type="submit">
							${entry.firstName} ${entry.lastName}
						</button>
					</form>
				</c:otherwise>
			</c:choose>

		</li>
	</c:forEach>
</c:if>
