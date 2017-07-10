<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <title>JSP Page</title>
        <link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <body>
        <h4>회원 가입</h4>
        <form method="post" style="padding: 0px 20px" enctype="multipart/form-data">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-user"></span>
                    </span> <input type="text" class="form-control" placeholder="아이디" name="mid"  value="hong"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-tag"></span>
                    </span> <input type="text" class="form-control" placeholder="이름" name="mname" value="홍길동"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-lock"></span>
                    </span> <input type="password" class="form-control" placeholder="비밀번호" name="mpassword" value="12345"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-tag"></span>
                    </span> <input type="text" class="form-control" placeholder="전화번호" name="mtel" value="010-123-1234"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-tag"></span>
                    </span> <input type="text" class="form-control" placeholder="이메일" name="memail" value="hong@naver.com"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-tag"></span>
                    </span> <input type="text" class="form-control" placeholder="나이" name="mage" value="25"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-tag"></span>
                    </span> <input type="text" class="form-control" placeholder="주소" name="maddress" value="서울시 송파구 가락동 IT 벤처타워 서관 12층"/>
                </div>
            </div>
          

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"> <span
                            class="glyphicon glyphicon-camera"></span>
                    </span> <input type="file" class="form-control" placeholder="선택" name="mattach" />
                </div>
            </div>
            <input type="submit" class="btn btn-info" value="가입" />
        </form>
    </body>

</html>