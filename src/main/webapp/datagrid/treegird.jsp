<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "专辑详情",
        handler: function () {
            var row = $("#tg").treegrid("getSelected")
            if (row.brief != null) {
                /*显示详情*/
                $("#album_dd").dialog("open");
                $("#album_ff").form("load", row)
                $("#album_img").prop("src", row.img);
            } else {
                $.messager.alert("提示框", "请选择专辑", "into");
            }
        }
    }, '-', {
        text: "添加专辑",
        iconCls: 'icon-help',
        handler: function () {
            /*打开dialog*/
            $("#album_add").dialog("open");
            $("#album_aa").form("load")
        }
    }, '-', {
        text: "添加章节",
        iconCls: 'icon-help',
        handler: function () {
            var row = $("#tg").treegrid("getSelected");
            if (row != null) {
                if (row.brief != null) {
                    $("#chapter_dd").dialog("open")
                    $("#albumId").val(row.id)
                }
            }else {
                $.messager.alert("提示框", "请选择专辑", "into");
            }
        }
    }, '-', {
        text: "下载音频",
        iconCls: 'icon-help',
        handler: function () {
            /*选中章节*/
            var row = $("#tg").treegrid("getSelected");
            if (row != null) {
                if (row.brief == null) {
                    location.href = "${pageContext.request.contextPath}/chapter/down?name=" + row.name + "&url=" + row.url;
                }
            }
        }
    }]

    $(function () {
        $('#tg').treegrid({
            onDblClickRow: function (row) {
                $("#audio_dd").dialog("open");
                $("#audio_ff").prop("src", "${pageContext.request.contextPath}/audio/" + row.url)
            },
            url: '${pageContext.request.contextPath}/album/queryAlbum',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '章节名字'},
                {field: 'size', title: '大小', width: 80},
                {field: 'url', title: '链接', width: 80},
                {field: 'brief', title: '简介', width: 80}
            ]],
            fitColumns: true,
            fit: true,
            toolbar: toolbar,
            pagination: true,
            pageSize: 6,
            pageList: [3, 6, 9],
        });
    })


    //添加
    function submit() {
        $('#album_aa').form('submit', {
            url: "${pageContext.request.contextPath}/album/add",
            success: function (data) {
                var jsObj = JSON.parse(data);
                /*alert(jsObj.isAdd)*/
                if (jsObj.isAdd == "添加成功") {
                    //1.关闭添加对话框
                    $('#album_add').dialog('close');
                    $.messager.alert("提示框", jsObj.isAdd, "into");
                    $('#tg').edatagrid('reload')
                }
            }
        });
        $('#chapter_ff').form('submit', {
            url: "${pageContext.request.contextPath}/chapter/addChapter",
            success: function (data) {
                var jsObj = JSON.parse(data);
                /*alert(jsObj.isAdd)*/
                if (jsObj.isAdd == "添加成功") {
                    //1.关闭添加对话框
                    $('#album_add').dialog('close');
                    $.messager.alert("提示框", jsObj.isAdd, "into");
                    $('#tg').edatagrid('reload')
                }
            }
        });
    }
</script>

<table id="tg"></table>

<div id="album_dd" class="easyui-dialog" title="专辑详情" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <table id="album_ff">
        <tr>
            <td>
                标题：<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>
                作者：<input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>简介：<input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/></td>
        </tr>
        <tr>
            <td><img id="album_img" src=""></td>
        </tr>

    </table>


</div>
<div id="album_add" class="easyui-dialog" title="添加专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
buttons:[{
				text:'保存',
				handler:function(){
                  submit();
                  $('#album_add').dialog('close');
                  $('#tg').edatagrid('reload')
				}
			},{
				text:'关闭',
				handler:function(){
                  $('#album_add').dialog('close');
				}
			}]">

    <form id="album_aa" method="post" enctype="multipart/form-data">
        <br/><div>
            <label for="title">&nbsp&nbsp&nbsp&nbsp&nbsp title:</label>
            <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div><br/>
        <div>
            <label for="score">&nbsp&nbsp&nbsp score:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div><br/>
        <div>
            <label for="author">&nbsp author:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div><br/>
        <div>
            <label for="broadcast">&nbsp&nbsp broad:</label>
            <input id="broadcast" class="easyui-validatebox" type="text" name="broadcast" data-options="required:true"/>
        </div><br/>
        <div>
            <label for="count">&nbsp&nbsp count:</label>
            <input id="count" class="easyui-validatebox" type="text" name="count" data-options="required:true"/>
        </div>
        <br/>
        <div>
            <label for="brief">&nbsp&nbsp&nbsp&nbsp brief:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
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
    <div id="chapter_dd" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true, buttons:[{
				text:'保存',
				handler:function(){
                  submit();
                  $('#chapter_dd').dialog('close');
                  $('#tg').treegrid('reload')
				}
			},{
				text:'关闭',
				handler:function(){
                  $('#chapter_dd').dialog('close');
				}
			}]">
        <form id="chapter_ff" method="post" enctype="multipart/form-data">
            <div>
                <input class="easyui-validatebox" type="hidden" id="albumId" name="albumId"
                       data-options="required:true"/>
            </div><br/>
            <div>
                &nbsp&nbsp&nbsp &nbsp&nbsp title:
                <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
            </div><br/>
            上传文件
            <input class="easyui-filebox" name="audio" style="width:300px">
        </form>
    </div>
</div>
<div id="audio_dd" class="easyui-dialog" title="章节播放" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <audio src="" id="audio_ff" controls="controls">

    </audio>
</div>