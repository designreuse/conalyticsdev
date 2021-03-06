<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.conalytics.controller.Menu"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if IE 9]> 		   <html class="ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<style>
#preview {
	position: absolute;
	border: 1px solid #ccc;
	background: #333;
	padding: 5px;
	display: none;
	color: #fff;
}
</style>

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700&subset=latin,cyrillic-ext,latin-ext,cyrillic'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<link
	href="<c:url value="/resources/css/jquery.multilevelpushmenu_brown.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.dataTables.css"/>"
	rel="stylesheet">

<link href="<c:url value="/resources/css/expandcollapse.css"/>"
	rel="stylesheet">

<link href="<c:url value="/resources/css/jquery.idealforms.css"/>"
	rel="stylesheet">

<link href="<c:url value="/resources/css/select2.css"/>"
	rel="stylesheet">

<script type="text/javascript"
	src="http://oss.maxcdn.com/libs/modernizr/2.6.2/modernizr.min.js"></script>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
<script	src="<c:url value="/resources/js/jquery.multilevelpushmenu.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.idealforms.min.js" />"></script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&region=IN"></script>

<script src="<c:url value="/resources/js/googlemapapi.js" />"></script>

<script src="<c:url value="/resources/js/select2.js" />"></script>



<script>
	$(document).ready(function() {

		//$('form').idealforms();
		$('#conTable').dataTable();
		$('#menu').multilevelpushmenu({
			menu :<%=Menu.getMenuItems(request.getContextPath())%>,
			containersToPush : [ $('#conBody') ],
			// Just for fun also changing the look of the menu
			wrapperClass : 'mlpm_w',
			menuInactiveClass : 'mlpm_inactive',
			mode : 'cover',
			onTitleItemClick : function() {
				// $("#templateForm").submit();
			},
			onBackItemClick : function() {
				var title = arguments[1].find('h2:first').text();
				if (title == 'Master Data') {
					$("#templateForm").submit();
				}

			}
		});
		$.ajaxSetup({
		    beforeSend:function(){
		        // show image here
		        $("#ajaxBusy").show();
		    },
		    complete:function(){
		        // hide image here
		        $("#ajaxBusy").hide();
		    }
		});
		updateMenuLinks();
		updateMenuSelection();

	});

	function updateMenuLinks() {
		$('#menu li').each(function(i) {
			if ($(this).attr('id')) {
				var aLink = $("a", this);
				if (aLink) {
					aLink.click(function() {
						location.href = aLink.attr('href');
					});
				}
			}
		});
	}

	function imagePreview() {
		/* CONFIG */

		xOffset = 10;
		yOffset = 30;

		// these 2 variable determine popup's distance from the cursor
		// you might want to adjust to get the right result

		/* END CONFIG */
		$("a.preview").hover(
				function(e) {
					this.t = this.title;
					this.title = "";
					var c = (this.t != "") ? "<br/>" + this.t : "";
					$("body").append(
							"<p id='preview'><img src='"+ this.href +"' alt='Image preview' />"
									+ c + "</p>");
					$("#preview").css("top", (e.pageY - xOffset) + "px").css(
							"left", (e.pageX + yOffset) + "px").fadeIn("fast");
				}, function() {
					this.title = this.t;
					$("#preview").remove();
				});
		$("a.preview").mousemove(
				function(e) {
					$("#preview").css("top", (e.pageY - xOffset) + "px").css(
							"left", (e.pageX + yOffset) + "px");
				});
	}
</script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<input type="hidden" id="selectedMenuItem" value="" />
	<div id="conBody" style="width: 75%;">
		<div style="background: #926738; height: 100%;">
			<tiles:insertAttribute name="header" />
		</div>
		<div style="background-color: #E3CEB5;">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div id="menu"></div>

	<form:form method="post" action="home" id="templateForm">
	</form:form>


</body>
</html>
