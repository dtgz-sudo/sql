<%--
  Created by IntelliJ IDEA.
  User: 锋子
  Date: 2020/5/20
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <META charset="utf-8">
    <META http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <META http-equiv="X-UA-Compatible" content="IE=9">
    <title>学生答题成绩整体分析</title>
    <script src="js/plugins/jquery/jquery.min.js"></script>
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.css">
    <script src="plugins/bootstrap/js/bootstrap.js"></script>
    <script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-theme.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/home.css">
    <!--    使用angularjs框架的双向绑定-->
    <script src="plugins/angularjs/angular.min.js"></script>

    <script type="text/javascript" src="layer/2.4/layer.js"></script>

    <script src="js/echarts.min.js"></script>
    <script>
        var tid;
        $(function () {
            $.ajax({
                url: "../index/findLoginUser",// ajax请求的url地址
                type: "post",// 提交的方法
                success: function (data) { // ajax请求回调后执行的方法,data表示从服务器返回的数据
                    var loginName = data;
                    console.log(loginName.data.nickname    );
                    //   alert(loginName.data.nickname );
                    $("#loginName").html(loginName.data.nickname);
                    // 老师的id
                    tid = loginName.data.tid;
                    // 等待老师的id获取到之后再去执行绘图方法
                    select_student();
                }
            });
            function select_student() {
                $.ajax({
                    type : "post",//提交方式
                    url : "../index/findallstudent",//提交的路径
                    data:{"tid":tid},
                    dataType : "json",
                    cache : false,
                    success : function(result) {
                        if (result) {
                            for(var i=0;i<result.data.length;i++){
                                $('<option value="'+result.data[i].sid+'">'+result.data[i].nickname+'</option>').prependTo("#selectstudent")
                            }

                        }
                    }
                })
            }
        });

    </script>


</head>
<body class="vsc-initialized">

<div class="everything">
    <div class="banner">
        <div class="container">
        </div>
    </div>
    <NAV class="navbar navbar-default" role="navigation">
        <DIV class="container">
            <DIV class="navbar-header">
                <BUTTON class="navbar-toggle collapsed" type="button"
                        data-toggle="collapse" data-target="#navbar-collapse"><SPAN
                        class="sr-only">Toggle navigation</SPAN> <SPAN
                        class="icon-bar"></SPAN><SPAN class="icon-bar"></SPAN><SPAN
                        class="icon-bar"></SPAN></BUTTON>
                <A class="navbar-brand" href="">主页</A>
            </DIV>
            <DIV class="collapse navbar-collapse" id="navbar-collapse">
                <UL class="nav navbar-nav">
                    <LI><A href="">学生导入</A></LI>
                    <LI><A href="/addProblem.html">实验题目发布</A></LI>
                    <LI><A href="">学生答题情况整体分析</A></LI>
                    <LI><A href="">学生答题情况个体分析</A></LI>
                    <LI><A href="">评价反馈</A></LI>
                </UL>
                <UL class="nav navbar-nav navbar-right">
                    <LI><A href="" id="loginName" ></A></LI>
                    <LI><A
                            href="/logout">退出登录</A></LI>
                </UL>
            </DIV><!-- /.navbar-collapse -->
        </DIV><!-- /.container-fluid -->     </NAV>

    <div class="main">

        <div class="container">
            <div class="block block-success"></div>
            <div class="block-content">
                <table>
                    <tr>
                        <td><div class="form-group form-group-sm">
                            <label class="col-sm-2 control-label">选择学生</label>
                            <div class="col-sm-10 form-inline">
                                <select name="selectstudent" class="form-control input-sm" id="selectstudent">
                                </select>
                            </div>
                        </div>                <div id="test" style="width: 600px;height:400px;"></div>
<%--                            <script type="text/javascript">--%>

<%--                                var myChart = echarts.init(document.getElementById('test'));--%>
<%--                                var drawChart = function ()--%>
<%--                                {--%>
<%--                                    // 显示标题，图例和空的坐标轴--%>
<%--                                    myChart.setOption({--%>
<%--                                        title: {--%>
<%--                                            text: '学生成绩统计'--%>
<%--                                        },--%>
<%--                                        tooltip: {--%>
<%--                                            trigger: 'axis'--%>
<%--                                        },--%>
<%--                                        toolbox: {--%>
<%--                                            show : true,--%>
<%--                                            feature : {--%>
<%--                                                dataView : {show: true, readOnly: false},--%>
<%--                                                magicType : {show: true, type: ['line', 'bar']},--%>
<%--                                                restore : {show: true},--%>
<%--                                                saveAsImage : {show: true}--%>
<%--                                            }--%>
<%--                                        },--%>
<%--                                        legend: {--%>
<%--                                            data:['0分','50分','70分','100分']--%>
<%--                                        },--%>
<%--                                        xAxis: {--%>
<%--                                            data: []--%>
<%--                                        },--%>
<%--                                        yAxis: {},--%>
<%--                                        series: [{--%>
<%--                                            name: '0分',--%>
<%--                                            type: 'bar',--%>
<%--                                            data: []--%>
<%--                                        },{--%>
<%--                                            name: '50分',--%>
<%--                                            type: 'bar',--%>
<%--                                            data: []--%>
<%--                                        },{--%>
<%--                                            name: '70分',--%>
<%--                                            type: 'bar',--%>
<%--                                            data: []--%>
<%--                                        },{--%>
<%--                                            name: '100分',--%>
<%--                                            type: 'bar',--%>
<%--                                            data: []--%>
<%--                                        }]--%>
<%--                                    });--%>

<%--                                    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画--%>

<%--                                    var pid=[];    //题号数组（实际用来盛放X轴坐标值）--%>
<%--                                    var num0=[];    //0分数组（实际用来盛放Y坐标值）--%>
<%--                                    var num50=[];    //50分数组（实际用来盛放Y坐标值）--%>
<%--                                    var num70=[];    //70分数组（实际用来盛放Y坐标值）--%>
<%--                                    var num100=[];    //100分数组（实际用来盛放Y坐标值）--%>

<%--                                    $.ajax({--%>
<%--                                        type : "post",--%>
<%--                                        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）--%>
<%--                                        url : "../teacher/findalldata",    //请求发送--%>
<%--                                        data : {"tid": tid},--%>
<%--                                        dataType : "json",        //返回数据形式为json--%>
<%--                                        success : function(result) {--%>
<%--                                            //请求成功时执行该函数内容，result即为服务器返回的json对象--%>
<%--                                            if (result) {--%>
<%--                                                for(var i=0;i<result.data.length;i++){--%>
<%--                                                    pid.push(result.data[i].pid);    //挨个取出类别并填入类别数组--%>
<%--                                                }--%>
<%--                                                for(var i=0;i<result.data.length;i++){--%>
<%--                                                    num0.push(result.data[i].num0);--%>
<%--                                                }--%>
<%--                                                for(var i=0;i<result.data.length;i++){--%>
<%--                                                    num50.push(result.data[i].num50);--%>
<%--                                                }--%>
<%--                                                for(var i=0;i<result.data.length;i++){--%>
<%--                                                    num70.push(result.data[i].num70);--%>
<%--                                                }--%>
<%--                                                for(var i=0;i<result.data.length;i++){--%>
<%--                                                    num100.push(result.data[i].num100);--%>
<%--                                                }--%>
<%--                                                myChart.hideLoading();    //隐藏加载动画--%>
<%--                                                myChart.setOption({        //加载数据图表--%>
<%--                                                    xAxis: {--%>
<%--                                                        data: pid--%>
<%--                                                    },--%>
<%--                                                    series: [{--%>
<%--                                                        // 根据名字对应到相应的系列--%>
<%--                                                        name: '0分',--%>
<%--                                                        data: num0--%>
<%--                                                    },{--%>
<%--                                                        // 根据名字对应到相应的系列--%>
<%--                                                        name: '50分',--%>
<%--                                                        data: num50--%>
<%--                                                    },{--%>
<%--                                                        // 根据名字对应到相应的系列--%>
<%--                                                        name: '70分',--%>
<%--                                                        data: num70--%>
<%--                                                    },{--%>
<%--                                                        // 根据名字对应到相应的系列--%>
<%--                                                        name: '100分',--%>
<%--                                                        data: num100--%>
<%--                                                    }]--%>
<%--                                                });--%>

<%--                                            }--%>

<%--                                        },--%>
<%--                                        error : function(errorMsg) {--%>
<%--                                            //请求失败时执行该函数--%>
<%--                                            alert("图表请求数据失败!");--%>
<%--                                            myChart.hideLoading();--%>
<%--                                        }--%>
<%--                                    })--%>
<%--                                }--%>

<%--                            </script>--%>
                            </td>
                    </tr>
                </table>


            </div>
        </div>

    </div>

</div>
</body>
</html>
