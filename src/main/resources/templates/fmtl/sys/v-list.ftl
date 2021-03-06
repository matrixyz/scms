<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="/sys/common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/sys/common/bootstrap/css/bootstrap.css" media="all">
    <link rel="stylesheet" type="text/css" href="/sys/common/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="/sys/css/personal.css" media="all">

</head>
<body>
<section class="layui-larry-box">
    <div class="larry-personal">
        <div class="layui-tab">
            <blockquote class="layui-elem-quote news_search">

                <div class="layui-inline">
                    <div class="layui-input-inline">所属表名称: </div>
                    <div class="layui-input-inline">
                        <input value="" placeholder="请输入关键字" class="layui-input search_input" type="text">
                    </div>
                    <div class="layui-input-inline">表名称: </div>
                    <div class="layui-input-inline">
                        <input value="" placeholder="请输入关键字" class="layui-input search_input" type="text">
                    </div>
                    <div class="layui-input-inline">数据类型: </div>
                    <div class="layui-input-inline">
                        <input value="" placeholder="请输入关键字" class="layui-input search_input" type="text">
                    </div>
                    <div class="layui-input-inline">前端显示: </div>
                    <div class="layui-input-inline">
                        <input value="" placeholder="请输入关键字" class="layui-input search_input" type="text">
                    </div>
                    <a class="layui-btn search_btn">查询</a>
                </div><div class="layui-inline">
                <a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
            </div>
                <div class="layui-inline">
                    <a class="layui-btn recommend" style="background-color:#5FB878">推荐文章</a>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn audit_btn">审核文章</a>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-danger batchDel">批量删除</a>
                </div>
                <div class="layui-inline">
                    <div class="layui-form-mid layui-word-aux">本页面刷新后除新添加的文章外所有操作无效，关闭页面所有数据重置</div>
                </div>
            </blockquote>
            <div class="layui-row  ">

                <div class=" col-lg-12" style="padding:0px;">
                    <!-- 字段列表 -->
                    <div class="layui-form news_list" id="${className}-list">
                        <table class="layui-table">

                            <thead>
                            <tr>
                                <th><input   type="checkbox">

                                    <div id="cbx-select-all" class="layui-unselect layui-form-checkbox  "><span>勾选</span><i class="layui-icon"></i></div>

                                </th>
                               <#list attrs as attrChild>
                                    <th><#if attrChild.comment?length gt 15>${attrChild.comment?substring(0,15)}<#else>${attrChild.comment}</#if> </th>
                               </#list>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody class="news_content">


                            </tbody>
                        </table>
                        <div class="larry-table-page clearfix">
                            <a href="javascript:;" class="layui-btn layui-btn-small"><i class="iconfont icon-shanchu1"></i>删除</a>
                            <div class="pageContainer" ></div>
                        </div>
                    </div>
                </div>

            </div>


        </div>
    </div>

</section>
<script type="text/javascript" src="/sys/common/layui/layui.js"></script>
<script type="text/javascript" src="/sys/js/sys-${className}-list.js"></script>

</body>
</html>