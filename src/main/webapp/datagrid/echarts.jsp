<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>


<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    $(function () {
        $.ajax({
            url:'${pageContext.request.contextPath}/user/num',
            type:"POST",
            success:function (data) {
                var myChart = echarts.init(document.getElementById('main'));
                alert(data)
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '持明法州近三周用户数量'
                    },
                    tooltip: {},
                    legend: {
                        data:['柱状','折线']
                    },
                    xAxis: {
                        data: ["近一周","近二周","近三周"]
                    },
                    yAxis: {},
                    series: [{
                        name: '柱状',
                        type: 'bar',
                        data: data
                    },{
                        name: '折线',
                        type: 'line',
                        data: data
                    }]
                };
                myChart.setOption(option);
            }
        })
    })

</script>