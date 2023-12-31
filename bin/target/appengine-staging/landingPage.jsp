<!doctype html>
<head>
<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
.btn {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 12px 16px;
	font-size: 16px;
	cursor: pointer;
	width: 500px;
	border-radius: 10px;
}

.btn-no-link {
	background-color: DodgerBlue;
	border: none;
	color: white;
	padding: 12px 16px;
	font-size: 16px;
	width: 500px;
	border-radius: 10px;
}

/* Darker background on mouse-over */
.btn:hover {
	background-color: RoyalBlue;
}

.error-messages-wrapper {
	border: 5px solid #fffb00;
	padding: 1em;
	margin-bottom: 1em;
}
</style>
<body>
	<div class=main-container>

		<p>
			<button class="btn" onClick="">
				<i class="fa fa-desktop"></i> Website
			</button>
		</p>
		<p>
			<button class="btn"
				onClick="https://www.facebook.com/profile.php?id=100012240851154">
				<i class="fa fa-facebook-official"></i> Facebook
			</button>
		</p>
		<p>
			<button class="btn">
				<i class="fa fa-instagram"></i> Instagram
			</button>
		</p>
		<p>
			<button class="btn">
				<i class="fa fa-linkedin-square"></i> LinkedIn
			</button>
		</p>
		<p>
			<button class="btn-no-link">
				<i class="fa fa-mobile"></i> 6977458737
			</button>
		</p>
		<p>
			<button class="btn-no-link">
				<i class="fa fa-envelope"></i> billy.verras@icloud.com
			</button>
		</p>

	</div>

</body>

<script type="text/javascript">
	function displayUserInfo(userInfo) {
		if(userInfo.websiteLink !== null){
		$('#main-container').append(
				'<p><button class="btn" onClick="'+userInfo.websiteLink+'"><i class="fa fa-desktop"></i> Website</button></p>');
		}
		if(userInfo.facebookLink !== null){
			$('#main-container').append(
					'<p><button class="btn"onClick="'+userInfo.facebookLink+'"><i class="fa fa-facebook-official"></i> Facebook</button></p>');
			}
	}

	function getLandingPage() {
		clearErrorOrSuccess();
		var jqxhr = $.get("landingPage", function(data) {
			try {
				var response = JSON.parse(data);
				displayUserInfo(response);
			} catch (e) {
				displayMessage('${LANG.GENERAL_ERROR}');
			}
		}).fail(function() {
			displayMessage('${LANG.GENERAL_ERROR}');
		});
	}

	$(document).ready(function() {
		getLandingPage();
	});
</script>