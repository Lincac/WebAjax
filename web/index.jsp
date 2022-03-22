<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message</title>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script>
        $(document).ready(function (){
            var is_empty = false;
          $(".out").click(function (){
            const name = $("#username").val();
            const email = $("#Email").val();
            const text = $("#text").val();
            $.ajax({
              type : 'get',
              url : '/WebAjax_war_exploded/Servlet',
              data :{
                UserName : name,
                UserEmail : email,
                UserText : text
              },
              dataType : 'text',
              success : function (result){
                  alert("上传成功！")
                  is_empty = true;
                result = JSON.parse(result)
                $('.new-message').append( "姓名："+ '<p style="color: blueviolet">'+result[0]+'</p>');
                $('.new-message').append("E-mail："+ '<p style="color: coral">'+result[1]+'</p>');
                $('.new-message').append("留言："+ '<p style="color: blueviolet">'+result[2]+'</p>');
              },
              error : function (err){
                alert("上传出错！");
              }
            });
          });
          $(".message_out").click(function (){
              if(is_empty){
                  alert("内容已经清除！");
                  $('.new-message').empty();
                  is_empty = false;
              }else{
                  alert("无内容！");
              }
          });
        });
    </script>
    <style>
        div.cla{
          float: left;
          width: 40%;
        }
        div#cc{
          padding-top: 10px;
        }
        div#cc button{
          margin-right: 10px;
        }
        .new-message{
          padding-top: 10px;
          margin-left: 10px;
        }
    </style>
</head>
<body>
  <div class="cla">
        <%--input没有设置name属性，jquery获取不到--%>
        <p>您的姓名：</p>
        <input id="username" name="user_name" type="text">
        <p>您的E-mail：</p>
        <input id="Email" name="user_email" type="text">
        <p>您的留言：</p>
        <textarea id="text" name="user_text" style="width: 400px;height: 200px"></textarea>
        <br>
        <div id="cc">
          <button class="out">传送留言</button>
          <button class="message_out">清除留言</button>
        </div>
  </div>
  <div>
      <span class="new-message"></span>
  </div>
</body>
</html>
