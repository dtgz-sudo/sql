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
                    select_head();
                }
            });
            function select_head() {
                $.ajax({
                    type : "post",//提交方式
                    url : "../index/findallhead",//提交的路径
                    data:{"tid":tid},
                    dataType : "json",
                    cache : false,
                    success : function(result) {
                        if (result) {
                            for(var i=0;i<result.data.length;i++){
                                $('<option value="'+result.data[i].hid+'">'+result.data[i].hid+'</option>').prependTo("#selecthead")
                            }
                            drawCharts();
                        }
                    }
                })
            };
        });
        function drawCharts() {
            drawChart();
            drawPieChart();
        }
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
                    <LI><A href="importStudent.html">学生导入</A></LI>
                    <LI><A href="addProblem.html">实验题目发布</A></LI>
                    <LI><A href="allAnalysis.jsp">学生答题情况整体分析</A></LI>
                    <LI><A href="personalAnalysis.jsp">学生答题情况个体分析</A></LI>
                    <LI><A href="historyAnswer.html">评价反馈</A></LI>
                    <LI><A href="historyProblem.html">题目管理</A></LI>
                    <LI><A href="head.html">教学头次管理</A></LI>
                    <LI><A href="classes.html">教学班级管理</A></LI>
                    <LI><A href="student.html">学生管理</A></LI>
                </UL>
                <UL class="nav navbar-nav navbar-right">
                    <LI><A href="updateUser.html" id="loginName" ></A></LI>
                    <LI><A href="/logout">退出登录</A></LI>
                </UL>
            </DIV><!-- /.navbar-collapse -->
        </DIV><!-- /.container-fluid -->     </NAV>

    <div class="main">

        <div class="container">
            <div class="block block-success"></div>
            <div class="block-content">
                <table>
                    <tr><td><div class="form-group form-group-sm">
                        选择头次
                            <select name="selecthead" class="form-control input-sm" id="selecthead" onchange="drawCharts()">
                            </select>
                    </div></td></tr>
                    <tr><br></tr>
                    <tr>
                        <td><div id="test" style="width: 600px;height:400px;"></div>
                            <script type="text/javascript">

                                var myChart = echarts.init(document.getElementById('test'));
                                var drawChart = function ()
                                {
                                    // 显示标题，图例和空的坐标轴
                                    myChart.setOption({
                                        title: {
                                            text: '学生成绩统计'
                                        },
                                        tooltip: {
                                            trigger: 'axis'
                                        },
                                        toolbox: {
                                            show : true,
                                            feature : {
                                                dataView : {show: true, readOnly: false},
                                                magicType : {show: true, type: ['line', 'bar']},
                                                restore : {show: true},
                                                saveAsImage : {show: true}
                                            }
                                        },
                                        legend: {
                                            data:['0-49分','50-69分','70-99分','100分']
                                        },
                                        xAxis: {
                                            data: []
                                        },
                                        yAxis: {},
                                        series: [{
                                            name: '0-49分',
                                            type: 'bar',
                                            data: []
                                        },{
                                            name: '50-69分',
                                            type: 'bar',
                                            data: []
                                        },{
                                            name: '70-99分',
                                            type: 'bar',
                                            data: []
                                        },{
                                            name: '100分',
                                            type: 'bar',
                                            data: []
                                        }]
                                    });

                                    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

                                    var pid=[];    //题号数组（实际用来盛放X轴坐标值）
                                    var num0=[];    //0分数组（实际用来盛放Y坐标值）
                                    var num50=[];    //50分数组（实际用来盛放Y坐标值）
                                    var num70=[];    //70分数组（实际用来盛放Y坐标值）
                                    var num100=[];    //100分数组（实际用来盛放Y坐标值）
                                    var hid = $("#selecthead option:selected").val();
                                    $.ajax({
                                        type : "post",
                                        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                                        url : "../teacher/findalldata",    //请求发送
                                        data : {"hid": hid},
                                        dataType : "json",        //返回数据形式为json
                                        success : function(result) {
                                            //请求成功时执行该函数内容，result即为服务器返回的json对象
                                            if (result) {
                                                for(var i=0;i<result.data.length;i++){
                                                    pid.push(result.data[i].pid);    //挨个取出类别并填入类别数组
                                                }
                                                for(var i=0;i<result.data.length;i++){
                                                    num0.push(result.data[i].num0);
                                                }
                                                for(var i=0;i<result.data.length;i++){
                                                    num50.push(result.data[i].num50);
                                                }
                                                for(var i=0;i<result.data.length;i++){
                                                    num70.push(result.data[i].num70);
                                                }
                                                for(var i=0;i<result.data.length;i++){
                                                    num100.push(result.data[i].num100);
                                                }
                                                myChart.hideLoading();    //隐藏加载动画
                                                myChart.setOption({        //加载数据图表
                                                    xAxis: {
                                                        data: pid
                                                    },
                                                    series: [{
                                                        // 根据名字对应到相应的系列
                                                        name: '0-49分',
                                                        data: num0
                                                    },{
                                                        // 根据名字对应到相应的系列
                                                        name: '50-69分',
                                                        data: num50
                                                    },{
                                                        // 根据名字对应到相应的系列
                                                        name: '70-99分',
                                                        data: num70
                                                    },{
                                                        // 根据名字对应到相应的系列
                                                        name: '100分',
                                                        data: num100
                                                    }]
                                                });

                                            }

                                        },
                                        error : function(errorMsg) {
                                            //请求失败时执行该函数
                                            alert("图表请求数据失败!");
                                            myChart.hideLoading();
                                        }
                                    })
                                }

                            </script></td>
                        <td><div id="pietest" style="width: 600px;height:400px;"></div>
                            <script type="text/javascript">

                                var myChartpie = echarts.init(document.getElementById('pietest'));
                                var drawPieChart = function ()
                                {
                                    // 显示标题，图例和空的坐标轴
                                    myChartpie.setOption({
                                        title : {
                                            text: '本班学生题目完成情况',
                                            x:'center'
                                        },
                                        tooltip : {
                                            trigger: 'item',
                                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                                        },
                                        legend: {
                                            orient: 'vertical',
                                            left: 'right',
                                            data: ['已全部完成','部分完成','未完成']
                                        },
                                        series : [
                                            {
                                                name: '完成情况',
                                                type: 'pie',
                                                radius : '55%',
                                                center: ['50%', '60%'],
                                                data:[
                                                    // {value:335, name:'直接访问'},
                                                    // {value:310, name:'邮件营销'},
                                                    // {value:234, name:'联盟广告'},
                                                    // {value:135, name:'视频广告'},
                                                    // {value:1548, name:'搜索引擎'}
                                                ],
                                                itemStyle: {
                                                    emphasis: {
                                                        shadowBlur: 10,
                                                        shadowOffsetX: 0,
                                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                                    }
                                                }
                                            }
                                        ]
                                    })
                                    myChartpie.showLoading();    //数据加载完之前先显示一段简单的loading动画
                                    var hid = $("#selecthead option:selected").val();
                                    var piedata = [];
                                    var allvalue;
                                    var partvalue;
                                    var notvalue;
                                    $.ajax({
                                        type : "post",
                                        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                                        url : "../teacher/findpiedata",    //请求发送
                                        data : {"hid": hid},
                                        dataType : "json",        //返回数据形式为json
                                        success : function(result) {
                                            //请求成功时执行该函数内容，result即为服务器返回的json对象
                                            if (result) {
                                                allvalue = result.data.allvalue;
                                                partvalue = result.data.partvalue;
                                                notvalue = result.data.notvalue;
                                                var obj1=new Object();
                                                obj1.name = "已全部完成";
                                                obj1.value = allvalue;
                                                piedata[0] = obj1;
                                                var obj2=new Object();
                                                obj2.name = "部分完成";
                                                obj2.value = partvalue;
                                                piedata[1] = obj2;
                                                var obj3=new Object();
                                                obj3.name = "未完成";
                                                obj3.value = notvalue;
                                                piedata[2] = obj3;
                                                myChartpie.hideLoading();    //隐藏加载动画
                                                myChartpie.setOption({        //加载数据图表
                                                    series: [{
                                                        // 根据名字对应到相应的系列
                                                        name: '完成情况',
                                                        data: piedata
                                                    }]
                                                });

                                            }

                                        },
                                        error : function(errorMsg) {
                                            //请求失败时执行该函数
                                            alert("图表请求数据失败!");
                                            myChart.hideLoading();
                                        }
                                    })
                                }
                            </script></td>
                    </tr>
                </table>


            </div>
        </div>

    </div>

</div>
</body>
</html>
