<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>jeeplus-Admin | 登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- #FAVICONS -->
  <link rel="shortcut icon" href="../static/adminlte/dist/img/favicon/favicon.ico" type="image/x-icon">
  <link rel="icon" href="../static/adminlte/dist/img/favicon/favicon.ico" type="image/x-icon">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../static/adminlte/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../resources/font-awesome/4.6.3/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../static/adminlte/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="../static/adminlte/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>jeeplus</b>-Admin</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">请使用邮箱账号登录</p>

    <form action="" method="post">
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="邮箱">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox" checked="checked"> 下次自动登录
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
            <div class="checkbox icheck">
                <label>
                    <a href="#">遇到登录问题</a>
                </label>
            </div>
        </div>
        <!-- /.col -->
      </div>
        <div class="row">
            <div class="col-xs-12">
                <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label></label>
                </div>
            </div>
            <div class="col-xs-4" style="text-align: end">
                <div class="checkbox icheck">
                    <!-- /.social-auth-links -->
                    <label>
                        <a href="register.html" class="text-center">立即注册</a>
                    </label>
                </div>
            </div>
        </div>
    </form>
      <hr>

    <div class="social-auth-links text-left">
      <p >可以使用以下方式登录</p>
      <a href="#" class="btn "><i class="fa fa-qq fa-2x"></i></a>
      <a href="#" class="btn "><i class="fa fa-weibo fa-2x"></i></a>
        <a href="#" class="btn "><i class="fa fa-weixin fa-2x"></i></a>
    </div>


  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="../static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="../static/adminlte/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
