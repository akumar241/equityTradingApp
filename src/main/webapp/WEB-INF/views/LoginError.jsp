<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=''>
<head>
<title>Sorry Page Not Found</title>
<meta name="keywords" content="404 " />


<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, dl, dt, dd, ol, nav ul, nav li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

article, aside, details, figcaption, figure, footer, header, hgroup,
	menu, nav, section {
	display: block;
}

ol, ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

blockquote, q {
	quotes: none;
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}
/* start editing from here */
a {
	text-decoration: none;
}

.txt-rt {
	text-align: right;
} /* text align right */
.txt-lt {
	text-align: left;
} /* text align left */
.txt-center {
	text-align: center;
} /* text align center */
.float-rt {
	float: right;
} /* float right */
.float-lt {
	float: left;
} /* float left */
.clear {
	clear: both;
} /* clear float */
.pos-relative {
	position: relative;
} /* Position Relative */
.pos-absolute {
	position: absolute;
} /* Position Absolute */
.vertical-base {
	vertical-align: baseline;
} /* vertical align baseline */
.vertical-top {
	vertical-align: top;
} /* vertical align top */
.underline {
	padding-bottom: 5px;
	border-bottom: 1px solid #eee;
	margin: 0 0 20px 0;
} /* Add 5px bottom padding and a underline */
nav.vertical ul li {
	display: block;
} /* vertical menu */
nav.horizontal ul li {
	display: inline-block;
} /* horizontal menu */
img {
	max-width: 100%;
}
/*end reset*
 */
body {
	background: url(CSS/ramu.png);
	font-family: "Century Gothic", Arial, Helvetica, sans-serif;
}

.content p {
	margin: 18px 0px 45px 0px;
}

.content p {
	font-family: "Century Gothic";
	font-size: 2em;
	color: dimgrey;

	text-align: center;
}

.content p span, .logo h1 a {
	color: #e54040;
}

.content {
	text-align: center;
	padding: 115px 0px 0px 0px;
}

.content a {
	color: #fff;
	font-family: "Century Gothic";
	/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#2c539e+0,2c539e+100;Blue+3D+%232 */
	background: rgb(44, 83, 158); /* Old browsers */
	background: -moz-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(44,
		83, 158, 1)), color-stop(100%, rgba(44, 83, 158, 1)));
	/* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* IE10+ */
	background: linear-gradient(to bottom, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#2c539e',
		endColorstr='#2c539e', GradientType=0); /* IE6-9 */
	width: auto;
}

.content a:hover {
	color: #e54040;
}

.logo {
	text-align: center;
	-webkit-box-shadow: 0 8px 6px -6px rgb(97, 97, 97);
	-moz-box-shadow: 0 8px 6px -6px rgb(97, 97, 97);
	box-shadow: 0 8px 6px -6px rgb(97, 97, 97);
}

.logo h1 {
	font-size: 2em;
	font-family: "Century Gothic";
	/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#2c539e+0,2c539e+100;Blue+3D+%232 */
	background: rgb(44, 83, 158); /* Old browsers */
	background: -moz-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(44,
		83, 158, 1)), color-stop(100%, rgba(44, 83, 158, 1)));
	/* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* IE10+ */
	background: linear-gradient(to bottom, rgba(44, 83, 158, 1) 0%,
		rgba(44, 83, 158, 1) 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#2c539e',
		endColorstr='#2c539e', GradientType=0); /* IE6-9 */
	width: auto;
}

.logo h1 a {
	font-size: 1em;
}

.copy-right {
	padding-top: 20px;
}

.copy-right p {
	font-size: 0.9em;
}

.copy-right p a {
	background: none;
	color: #e54040;
	padding: 0px 0px 5px 0px;
	font-size: 0.9em;
}

.copy-right p a:hover {
	color: #666;
}
/*------responive-design--------*/
@media screen and (max-width: 1366px) {
	.content {
		padding: 20px 0px 0px 0px;
	}
}

@media screen and (max-width:1280px) {
	.content {
		padding: 58px 0px 0px 0px;
	}
}

@media screen and (max-width:1024px) {
	.content {
		padding: 20px 0px 0px 0px;
	}
	.content p {
		font-size: 1.5em;
	}
	.copy-right p {
		font-size: 0.9em;
	}
}

@media screen and (max-width:640px) {
	.content {
		padding: 20px 0px 0px 0px;
	}
	.content p {
		font-size: 1.3em;
	}
	.copy-right p {
		font-size: 0.9em;
	}
}

@media screen and (max-width:460px) {
	.content {
		padding: 10px 0px 0px 0px;
		margin: 0px 12px;
	}
	.content p {
		font-size: 0.9em;
	}
	.copy-right p {
		font-size: 0.8em;
	}
}

@media screen and (max-width:320px) {
	.content {
		padding: 10px 0px 0px 0px;
		margin: 0px 12px;
	}
	.content a {
		padding: 10px 15px;
		font-size: 0.8em;
	}
	.content p {
		margin: 18px 0px 22px 0px;
	}
}

.button {
	display: inline-block;
	text-align: center;
	vertical-align: middle;
	padding: 7px 7px;
	border: 1px solid #074276;
	border-radius: 2px;
	background: #0d7dde;
	background: -webkit-gradient(linear, left top, left bottom, from(#0d7dde),
		to(#10548f));
	background: -moz-linear-gradient(top, #0d7dde, #10548f);
	background: linear-gradient(to bottom, #0d7dde, #10548f);
	font: normal normal normal 14px arial;
	color: #ffffff;
	text-decoration: none;
	position: relative;
	left: 25px
}

.button:hover, .button:focus {
	border: 1px solid##084e8b;
	background: #1096ff;
	background: -webkit-gradient(linear, left top, left bottom, from(#1096ff),
		to(#1365ac));
	background: -moz-linear-gradient(top, #1096ff, #1365ac);
	background: linear-gradient(to bottom, #1096ff, #1365ac);
	color: #ffffff;
	text-decoration: none;
	cursor: pointer;
}

#cssbutton ul {
	list-style: none;
	/*margin: 0;*/
	padding: 0;
	line-height: 1;
	display: block;
	zoom: 1;
}

#cssbutton ul li {
	display: inline-block;
	padding: 0;
	margin: 0;
}

.button:active {
	background: #074276;
	background: -webkit-gradient(linear, left top, left bottom, from(#074276),
		to(#10548f));
	background: -moz-linear-gradient(top, #074276, #10548f);
	background: linear-gradient(to bottom, #074276, #10548f);
}
</style>
</head>
<body>
	<!--start-wrap--->
	<div class="wrap">
		<!---start-header---->
		<div class="header">
			<div class="logo">
				<h1>
					<a href="#">Ohh</a>
				</h1>
			</div>
		</div>
		<!---End-header---->
		<!--start-content------>
		<div class="content"">
			<img src="CSS/404.jpg" title="error" height="280" width="420" />
			<p>
				<span>Ohh.....</span>You provided invalid credentials<br>
				Please login with valid credentials again.
			</p>

			<div id='cssbutton'>
				<ul>
					<li><a class="button" href="welcome">Login</a></li>
				</ul>
			</div>
			<div class="copy-right">
				<p>&#169 All rights Reserved | Sapient Global Markets</p>
			</div>
		</div>
		<!--End-Cotent------>
	</div>
	<!--End-wrap--->
</body>
</html>
