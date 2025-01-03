<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="d-flex justify-content-center align-center">
		<div class="w-50 align-middle border border-info border-3 mt-2"
			style="height: 570px">
			<h6 class="p-3 text-primary ">
				<u>Batch Details :-</u>
			</h6>
			<div class="border border-secondary m-3 p-2">
				<table class="table table-hover border border-secondary ">
					<tbody>
						<tr class="table-primary fs-6">
							<th>Student Id</th>
							<td>${st.studentId}</td>
						</tr>
						<tr class="table-primary fs-6">
							<th>Student Name</th>
							<td>${st.studentFullName}</td>
						<tr class="table-primary fs-6">
							<th>Course Name</th>
							<td>${st.studentCourse}</td>
						</tr>


						<tr class="table-primary fs-6">
							<th>Fees Paid</th>
							<td>${st.feesPaid}</td>
						</tr>

						<tr class="table-danger fs-6">
							<th>Batch Number</th>
							<td>${st.batchNumber}</td>
						</tr>

						<tr class="table-danger fs-6">
							<th>Batch mode</th>
							<td>${st.batchMode}</td>
						</tr>
					</tbody>
				</table>
				<form action="shiftbatch">
					<input type="text" name="studentid" value="${st.studentId}"
						hidden="true">

					<div class="col">
						<select class="select form-control-sm" name="batch">
							<option value="#" disabled>Select Batch Number</option>
							<option value="FDJ-185">FDJ-185</option>
							<option value="REG-185">REG-185</option>
							<option value="FDJ-161">FDJ-161</option>
							<option value="REG-161">REG-161</option>
							<option value="FDJ-162">FDJ-162</option>
							<option value="REG-162">REG-162</option>
							<option value="FDJ-163">FDJ-163</option>
							<option value="REG-163">REG-163</option>
						</select> <label for="batch" class="form-label select-label"><b>Change Batch Number</b></label>
					</div>

			<br>
			<div class="col">
				<select class="select form-control-sm" name="mode">
					<option value="#" disabled>Select Batch Mode</option>
					<option value="Online">Online</option>
					<option value="Offline">Offline</option>

				</select> <label for="mode" class="form-label select-label"><b>Batch
						Mode </b></label>
			</div>




			<div class="d-flex justify-content-center pt-5">
				<button class="btn btn-success btn-sm ">Change Batch</button>
			</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>