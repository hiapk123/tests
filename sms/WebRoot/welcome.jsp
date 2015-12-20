<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.uestc.bean.Users"%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>欢迎页面</title>

  <style>
html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}body{margin:0}article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block}audio,canvas,progress,video{display:inline-block;vertical-align:baseline}audio:not([controls]){display:none;height:0}[hidden],template{display:none}a{background:transparent}a:active,a:hover{outline:0}abbr[title]{border-bottom:1px dotted}b,strong{font-weight:bold}dfn{font-style:italic}h1{font-size:2em;margin:0.67em 0}mark{background:#ff0;color:#000}small{font-size:80%}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-0.5em}sub{bottom:-0.25em}img{border:0}svg:not(:root){overflow:hidden}figure{margin:1em 40px}hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}pre{overflow:auto}code,kbd,pre,samp{font-family:monospace, monospace;font-size:1em}button,input,optgroup,select,textarea{color:inherit;font:inherit;margin:0}button{overflow:visible}button,select{text-transform:none}button,html input[type="button"],input[type="reset"],input[type="submit"]{-webkit-appearance:button;cursor:pointer}button[disabled],html input[disabled]{cursor:default}button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}input{line-height:normal}input[type="checkbox"],input[type="radio"]{-moz-box-sizing:border-box;box-sizing:border-box;padding:0}input[type="number"]::-webkit-inner-spin-button,input[type="number"]::-webkit-outer-spin-button{height:auto}input[type="search"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;box-sizing:content-box}input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration{-webkit-appearance:none}fieldset{border:1px solid #c0c0c0;margin:0 2px;padding:0.35em 0.625em 0.75em}legend{border:0;padding:0}textarea{overflow:auto}optgroup{font-weight:bold}table{border-collapse:collapse;border-spacing:0}td,th{padding:0}

</style>

    <style>
.overlay-figure {
  position: relative;
/*
   You can safely give the entire complex a size, or just a width or height. If a
   size isn't specified then the overlay-figure-content element's size defines
   the overall size (i.e. the IMG element defines the overall size).

   width: 480px;
   height: 320px;
*/
  display: table;
  border: solid 1px black;
}

.overlay-figure-content {
  width: 100%;
  height: 100%;
 -webkit-transform: scale(1.0) translateZ(0);
    -moz-transform: scale(1.0) translateZ(0);
         transform: scale(1.0) translateZ(0);
  -webkit-transition: -webkit-transform 0.5s ease 0.25s;
     -moz-transition:    -moz-transform 0.5s ease 0.25s;
          transition:         transform 0.5s ease 0.25s;
}

.overlay-figure:hover
.overlay-figure-content {
    -webkit-transform: scale(0.95) translateZ(0);
       -moz-transform: scale(0.95) translateZ(0);
            transform: scale(0.95) translateZ(0);
}

.overlay-figure-content > img {
    display: block;
}

.overlay-figure-background {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  background-image: linear-gradient(to bottom, rgba(38,50,72, 0.5), rgba(38,50,72, 0.85));
}

.overlay-figure:hover
.overlay-figure-background {
  opacity: 1;
}

.overlay-figure-caption {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  font-family: adobe-clean, 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: white;
  opacity: 0;
  -webkit-transition: opacity ease 0.25s;
     -moz-transition: opacity ease 0.25s;
          transition: opacity ease 0.25s;
}

.overlay-figure:hover
.overlay-figure-caption {
    opacity: 1;
}

.overlay-figure-caption
.contents {
  display: table;
  width: 100%;
  height: 100%;
}

.overlay-figure-caption
.contents-stack {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  padding: 5%;
}

.overlay-figure-caption
.title {
  display: block;
  margin-bottom: 0;
  opacity: 0;
  -webkit-transform: translateY(-100%) translateZ(0);
     -moz-transform: translateY(-100%) translateZ(0);
          transform: translateY(-100%) translateZ(0);
  -webkit-transition: -webkit-transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
     -moz-transition:    -moz-transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
          transition:         transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
}

.overlay-figure:hover 
.title {
  opacity: 1;
  -webkit-transform: translateY(0) translateZ(0);
     -moz-transform: translateY(0) translateZ(0);
          transform: translateY(0) translateZ(0);
}

.overlay-figure
.separator {
  display: block;
  margin: 12px;
  margin-left: auto;
  margin-right: auto;
  height: 2px;
  width: 30%;
  background-color: white;
  -webkit-transform: scaleX(0) translateZ(0);
     -moz-transform: scaleX(0) translateZ(0);
          transform: scaleX(0) translateZ(0);
  -webkit-transition: -webkit-transform 0.25s ease 0.25s;
     -moz-transition:    -moz-transform 0.25s ease 0.25s;
          transition:         transform 0.25s ease 0.25s;
}

.overlay-figure:hover
.separator {
  -webkit-transform: scaleX(1) translateZ(0);
     -moz-transform: scaleX(1) translateZ(0);
          transform: scaleX(1) translateZ(0);
}

.overlay-figure-caption 
.text {
  display: block;
  margin-top: 0;
  opacity: 0;
  -webkit-transform: translateY(100%) translateZ(0);
     -moz-transform: translateY(100%) translateZ(0);
          transform: translateY(100%) translateZ(0);
  -webkit-transition: -webkit-transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
     -moz-transition:    -moz-transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
          transition:         transform 0.5s ease 0.25s, opacity 0.15s ease 0.25s;
}

.overlay-figure:hover
.text {
  opacity: 1;
  -webkit-transform: translateY(0) translateZ(0);
     -moz-transform: translateY(0) translateZ(0);
          transform: translateY(0) translateZ(0);
}

</style>

    <script src="js/prefixfree.min.js"></script>

</head>

<body>

  <figure class="overlay-figure">
  <div class="overlay-figure-content">
    <img src="november.jpg">
    <div class="overlay-figure-background"></div>
  </div>
  <figcaption class="overlay-figure-caption">
    <div class="contents"> 
      <div class="contents-stack">
        <h2 class="title">欢迎登录</h2>
        <div class="separator"></div>
        <p class="text">您好！<%=((Users)session.getAttribute("sessionUser")).getUName() %></p>
      </div>
    </div>
  </figcaption>
</figure>
<div style="text-align:center;clear:both">
</div>
</body>

</html>