<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  String exampleData = (String) request.getAttribute("ExampleData");
  System.out.println(request.toString());
%>
<html>
<head>
  <title>연습용 페이지 입니다.</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>
</head>
<body>

<header>
  <div class="collapse bg-dark" id="navbarHeader">
    <div class="container">
      <div class="row">
        <div class="col-sm-8 col-md-7 py-4">
          <h4 class="text-white">연습용 페이지</h4>
          <p class="text-muted">이 페이지의 뼈대는 부트스트랩5.0의 example album에서 가져왔습니다. 디자인에 관한 자세한 내용은 우측 링크에서 확인하세요</p>
        </div>
        <div class="col-sm-4 offset-md-1 py-4">
          <h4 class="text-white">디자인 관련 참고 링크</h4>
          <ul class="list-unstyled">
            <li><a href="https://getbootstrap.com/" class="text-white">부트스트랩 공식 홈페이지</a></li>
            <li><a href="https://getbootstrap.com/docs/5.0/getting-started/introduction/" class="text-white">부트스트랩 공식 문서</a></li>
            <li><a href="https://getbootstrap.com/docs/5.0/examples/" class="text-white">부트스트랩 각종 예시</a></li>
            <li><a href="https://getbootstrap.com/docs/5.0/examples/cheatsheet/" class="text-white">부트스트랩 치트 시트</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container">
      <a href="#" class="navbar-brand d-flex align-items-center">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2" viewBox="0 0 24 24"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
        <strong>Album</strong>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>
  </div>
</header>

<main>

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">연습용 페이지입니다.</h1>
        <p class="lead text-muted">이 페이지에는 온갖 예제가 들어올 예정입니다. DB불러오기, DB전송하기, 부트스트랩, 제이쿼리 등 연습용 페이지로 활용될 계획이며, 만약에 더 괜찮은 연습 자료가 있다면 수정해서 Push해주세요. 감사합니다.</p>
        <p>
          <a href="#" class="btn btn-primary my-2">버튼을 눌렀을 때 Modal 페이지 띄우는 예제(미구현)</a>
          <a href="main.do" class="btn btn-secondary my-2">홈으로 돌아가기</a>
        </p>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-light">
    <div class="container">

      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" id="card">
      </div>
    </div>
  </div>

</main>

<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#">Back to top</a>
    </p>
    <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
    <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a href="../getting-started/introduction/">getting started guide</a>.</p>
  </div>
</footer>
</body>
<script>
  $(document).ready(function(){
    makeExampleAll();
  })
  function makeExampleAll(){
    var makeExampleAll = <%=exampleData%>;
    var list = $('#card');
    var text = '';

    for(var i=0; i<makeExampleAll.length; i++){
      text+= '<div class="col">'
              +'<div class="card shadow-sm">'
              +'<svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">'+makeExampleAll[i].title+'</text></svg>'
              +'<div class="card-body">'
              +'<p class="card-text">'+makeExampleAll[i].content+'</p>'
              +'<div class="d-flex justify-content-between align-items-center">'
              +'<div class="btn-group">'
              +'<button type="button" class="btn btn-sm btn-outline-secondary">View</button>'
              +'<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>'
              +'</div>'
              +'<small class="text-muted">'+makeExampleAll[i].date+'</small>'
              +'</div></div></div></div>';
    }
    list.append(text);
  }
</script>
</html>