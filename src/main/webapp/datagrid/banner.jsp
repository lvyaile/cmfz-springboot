<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "添加",
        handler: function () {
            /*打开dialog*/
            $("#dd").dialog("open");
        }
    }, '-', {
        text: "修改",
        iconCls: 'icon-help',
        handler: function () {
            /*获取选中行*/
            var row = $("#dg").edatagrid("getSelected");
            if (row != null) {
                /*把当前行变成可编辑模式*/
                var index = $("#dg").edatagrid("getRowIndex", row);
                $("#dg").edatagrid("editRow", index)

            } else {
                alert("请先选中行")
            }
        }
    }, '-', {
        text: "删除",
        iconCls: 'icon-help',
        handler: function () {
            /*删除当前选中行*/
            /*刷新页面*/
            var allRows = $("#dg").datagrid("getSelections");
            if (allRows.length == 0) {
                $.messager.alert("提示框", "请选中要删除的信息", "warning");
            } else {
                $.messager.confirm("确认框", "确定要删除选中信息？", function (r) {
                    if (r) {
                        var id = new Array(allRows.length);
                        for (var i = 0; i < allRows.length; i++) {
                            id[i] = allRows[i].id;
                        }
                        //转到后台
                        $.ajax({
                            url: "${pageContext.request.contextPath}/banner/delete",
                            data: "id=" + id,
                            type: "post",
                            success: function (data) {
                                $.messager.alert("提示框", data.isDelete, "into");
                                $('#dg').edatagrid('reload')
                            }
                        });
                    }
                });
            }
        }
    }, '-', {
        text: "保存",
        iconCls: 'icon-help',
        handler: function () {
            $("#dg").edatagrid("saveRow");
            $("#dg").edatagrid("reload");
        }
    }]

    $(function () {
        $('#dg').edatagrid({
            updateUrl: '${pageContext.request.contextPath}/banner/update',
            url: '${pageContext.request.contextPath}/banner/queryAll',
            columns: [[
                {field: 'title', title: '名字', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {field: 'createDate', title: '时间', width: 100, align: 'right'}
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
    })

    function submit() {
        $('#ff').form('submit', {
            url: "${pageContext.request.contextPath}/banner/add",
            success: function (data) {
                var jsObj = JSON.parse(data);
                /*alert(jsObj.isAdd)*/
                if (jsObj.isAdd == "添加成功") {
                    //1.关闭添加对话框
                    $('#dd').dialog('close');
                    $.messager.alert("提示框", jsObj.isAdd, "into");
                    $('#dg').edatagrid('reload')
                }
            }
        });


    }
</script>

<table id="dg"></table>

<div id="dd" class="easyui-dialog" title="My Dialog" style="width:450px;height:250px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
     buttons:[{
				text:'保存',
				handler:function(){
                  submit();
                  $('#dd').dialog('close');
                  $('#dg').edatagrid('reload')
				}
			},{
				text:'关闭',
				handler:function(){
                  $('#dd').dialog('close');
				}
			}]">

    <form id="ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="title">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsptitle:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <br/>
        <div>
            <label for="desc">&nbsp&nbsp&nbsp&nbsp&nbspdesc:</label>
            <input id="desc" class="easyui-validatebox" type="text" name="description" data-options="required:true"/>
        </div>
        <br/>
        &nbsp&nbsp&nbspstatus:
        <select id="cc" class="easyui-combobox" name="status" style="width:200px;">
            <option value="Y">展示</option>
            <option value="N">不展示</option>
        </select><br/>

        <br/>文件上传
        <input class="easyui-filebox" name="img" style="width:300px">

    </form>


</div>



