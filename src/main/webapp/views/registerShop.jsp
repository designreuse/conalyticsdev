<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<center>
	<form:form method="post" class="idealforms" autocomplete="off"
		action="/insertState" modelAttribute="shop">
		<div style="width: 100%;">

			<div style="float: left; width: 49%;">
				<div class="idealforms-field-select-one">
					<label class="main">State*:</label>
					<form:select path="shopId">
						<form:options items="${stateMap}" />
					</form:select>
					<span class="error"></span>
				</div>
				<div class="idealforms-field-select-one">
					<label class="main">City*:</label>
					<form:select path="cityId">
						<form:options items="${cityMap}" />
					</form:select>
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">TOWN MUNI:</label>
					<form:input path="townMuni" />
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">SHOP NAME*:</label>
					<form:input path="shopName" />
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">SHOP DESC:</label>
					<form:input path="shopDesc" />
					<span class="error"></span>
				</div>

				<div class="field">
					<label class="main">HRLY RATE:</label>
					<form:input path="stdHrlyLaborRate" />
					<span class="error"></span>
				</div>
			</div>

			<div style="float: right; width: 50%; margin-left: 10px;">
				<div class="field">
					<label class="main">PHONE NUMBER:</label>
					<form:input path="phoneNumber" />
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">WEBSITE:</label>
					<form:input path="website" />
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">EMAIL:</label>
					<form:input path="email" />
					<span class="error"></span>
				</div>
				<div class="field">
					<label class="main">ADDRESS*:</label>
					<form:input path="address" />
					<span class="error"></span>
				</div>
			</div>
			<div>
				<div class="field button">
					<label class="main">&nbsp;</label>
					<button type="submit">Submit</button>
				</div>
			</div>
		</div>

	</form:form>
</center>
<script>
	function updateMenuSelection() {
		$('#menu').multilevelpushmenu('expand', 'Shops');
		$('#currentAction').text('Add Shop');
	}
</script>
