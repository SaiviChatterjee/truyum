<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TruYUM</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
body {
	font-family: Cusrive !important;
	font-weight: 800;
	font-size: 20px;
}

.nounderline {
	text-decoration: none !important;
}
</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
			<a class="navbar-brand" href="/">TruYum</a>
			<ul class="nav navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-white"
					href="show-menu-list-customer">Menu</a></li>
				<li class="nav-item"><a class="nav-link text-white active"
					href="show-cart?userId=1">Cart</a></li>

			</ul>
		</nav>
	</header>
	<div class="container">
		<h1>Cart</h1>
		<table class="table table-striped">
			<caption style="caption-side: top;">
				<c:if test="${removeCartItemStatus }">
					<div class="sandsad alert-success">
						<strong>Item removed from Cart Successfully</strong>
					</div>
				</c:if>
			</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Free Delivery</th>
					<th>Price</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${cart.menuItemList }" var="menuItem">
				<tr>
					<td>${menuItem.name }</td>
					<td><c:choose>
							<c:when test="${menuItem.freeDelivery }">YES</c:when>
							<c:otherwise>NO</c:otherwise>
						</c:choose></td>
					<td><fmt:formatNumber type="currency" value="${menuItem.price }"/></td>
					<td><a type="button" class="btn btn-danger"
						href="remove-cart-item?userId=${userId }&menuItemId=${menuItem.id }">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<th>Total</th>
				<td><fmt:formatNumber type="currency" value="${cart.total }"/></td>
				<td></td>
			</tr>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- Footer -->
	<footer
		class="page-footer font-small bg-primary navbar-dark fixed-bottom">
		<!-- Copyright -->
		<div class="footer-copyright text-center py-3">
			© 2020 Copyright: <a class="text-white nounderline"
				href="https://localhost:8080"> TruYum.com</a>
		</div>
		<!-- Copyright -->
	</footer>
	<!-- Footer -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>
</body>
</html>