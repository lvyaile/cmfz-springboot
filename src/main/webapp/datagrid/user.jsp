<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script type="text/javascript">
    var toolbar = [ {
        text: "修改用户状态",
        iconCls: 'icon-help',
        handler: function () {
            /*获取选中行*/
            var row = $("#dg").edatagrid("getSelected");
            if (row != null) {
                /*把当前行变成可编辑模式*/
                var index = $("#dg").edatagrid("getRowIndex", row);
                $("#dg").edatagrid("editRow", index)

            } else {
                $.messager.alert("提示框", "请先选择行", "into");
            }
        }
    }, '-', {
        text: "保存",
        iconCls: 'icon-help',
        handler: function () {
            $("#dg").edatagrid("saveRow");
            $("#dg").edatagrid("reload");
        }
    }, '-',{
        iconCls: 'icon-edit',
        text: "导入用户信息",
        handler: function () {
            $("#User_ff").dialog("open");
        }
    }, '-',{
        iconCls: 'icon-edit',
        text: "导出用户信息",
        handler: function () {
            location.href = "${pageContext.request.contextPath}/user/export";
        }
    }, '-',{
        iconCls: 'icon-edit',
        text: "选择导出用户信息",
        handler: function () {
            $("#customer_dd").dialog("open");
        }
    }]

    $(function () {
        $('#dg').edatagrid({
            updateUrl: '${pageContext.request.contextPath}/user/update',
            url: '${pageContext.request.contextPath}/user/queryAll',
            columns: [[
                {field: 'name', title: '名字', width: 100},
                {field: 'dharma', title: '法名', width: 100},
                {field: 'sex', title: '性别', width: 100},
                {field: 'location', title: '地址', width: 100},
                {field: 'sign', title: '签名', width: 100},
                {field: 'phoneNum', title: '手机号', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {field: 'registDate', title: '注册时间', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 6,
            pageList: [3, 6, 9],
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + "${pageContext.request.contextPath}/upload/" + rowData.url + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.createDate + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },


        });
        $('#dg').edatagrid("disableEditing");

        $("#btn").linkbutton({
            onClick: function () {
                var title = $("#customer_cc").combotree("getText");
                var fileds = $("#customer_cc").combotree("getValues");
                var c = "";
                $.each(fileds, function (index, filed) {
                    if (index == fileds.length - 1) {
                        c += filed;
                    } else {
                        c += filed + ",";
                    }

                })
                $("#customer_ff").form("submit", {
                    url: "${pageContext.request.contextPath}/user/customerExport",
                    queryParams: {
                        "title": title,
                        "fileds": c

                    },
                    handler:function(){
                        $('#customer_dd').dialog('close');
                    }
                })
            }
        })
    });
    function submit() {
        $('#User_dd').form('submit', {
            url:"${pageContext.request.contextPath}/user/userImpl",

        });
    }
</script>

<table id="dg"></table>
<div id="User_ff" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true, buttons:[{
            text:'保存',
            handler:function(){
              submit();
              $('#User_ff').dialog('close');
              $('#dg').treegrid('reload')
            }
        },{
            text:'关闭',
            handler:function(){
              $('#User_ff').dialog('close');
            }
        }]">
    <form id="User_dd" method="post" enctype="multipart/form-data">
        <div>
            请导入文件:<input  type="file" class="offset10 lf" name="file"/>
        </div>
    </form>
</div>

<div id="customer_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="text-align: center">
        <select id="customer_cc" class="easyui-combotree" style="width:200px;"
                data-options='multiple:true,onlyLeafCheck:true,checkbox:true,required:true,data:[{
        "id":"custome",
        "text": "请选择",
        "children": [{
        "id":"id",
        "text": "编号"
        },{
        "id":"name",
        "text": "名字"
        },{
        "id":"dharma",
        "text": "法名"
        },{
        "id":"photo",
        "text": "头像"
        },{
        "id":"password",
        "text": "密码"
        },{
        "id":"password",
        "text": "盐值"
        },{
        "id":"sex",
        "text": "性别"
        },{
        "id":"location",
        "text": "所在地"
        },{
        "id":"sign",
        "text": "签名"
        },{
        "id":"status",
        "text": "状态"
        },{
        "id":"phoneNum",
        "text": "手机号"
        },{
        "id":"registDate",
        "text": "注册时间"
        },]
        }] '>
        </select>
        <form id="customer_ff" method="post">
            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>

        </form>
    </div>
</div>



