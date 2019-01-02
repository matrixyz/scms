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
        <header class="larry-personal-tit">
            <span>个人信息</span>
        </header><!-- /header -->
        <div class="larry-personal-body clearfix">
            <form class="layui-form col-lg-12" id="form-${className}" action="" method="post">

                <#list attrs as attrChild>
                    <div class="layui-form-item">
                    <#list attrChild as attr>
                        <div class="layui-inline ">
                            <label class="layui-form-label" style="overflow: hidden;white-space:nowrap;width:180px; " id="${attr.name}" tips=" ${attr.comment}"  >
                            <#if attr.comment?length gt 15>${attr.comment?substring(0,15)}<#else>${attr.comment}</#if> </label>
                            <div class="layui-input-block" style="margin-left:190px;">

                                <input type="text" name="${attr.name}" id="${attr.name}_id"
                                       autocomplete="off"  class="layui-input <#if attr.column==primaryColumn> layui-disabled</#if>"    />
                            </div>
                        </div>

                    </#list>
                    </div>
                </#list>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <a class="layui-btn" id="btn-submit">立即提交</a>
                        <a type="reset" class="layui-btn layui-btn-primary">重置</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="text/javascript" src="/sys/common/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form','upload','jquery' ],function(){
        var form = layui.form();
        layui.upload({
            url: '' ,//上传接口
            success: function(res){
                //上传成功后的回调
                console.log(res)
            }
        });
    $ = layui.jquery;
        $('.layui-form-label').each(function () {
            var dom=$(this);
            var domId=$(this).attr("id");
            $(dom).on( 'mouseover', function() {
                layui.layer.tips($(dom).attr("tips"), '#'+domId, {
                    tips: [2, '#0FA6D8'], //设置tips方向和颜色 类型：Number/Array，默认：2 tips层的私有参数。支持上右下左四个方向，通过1-4进行方向设定。如tips: 3则表示在元素的下面出现。有时你还可能会定义一些颜色，可以设定tips: [1, '#c00']
                    tipsMore: false, //是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
                    time:2000  //2秒后销毁，还有其他的基础参数可以设置。。。。这里就不添加了
                });
            });
        });
        var submitType='POST' ;
        $( "#btn-submit" ).click(function () {

            var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var d = {};
            var t = $("#form-${className}").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                url :  "/${className}",
                type :submitType ,
                data : JSON.stringify(d)  , //转JSON字符串
                //dataType: "json",//说明返回的数据类型，这里返回的是json类型
                contentType:'application/json;charset=UTF-8', //contentType很重要
                success: function (msg) {


                },
                complete: function (data) {
                    if(data.responseText=='添加信息成功!'||data.responseText=='修改信息成功!'){
                        submitType='PUT';
                        top.layer.close(index);
                        top.layer.msg(data.responseText);
                    }
                },
                error: function (XMLHttpRequest, textStatus, thrownError) {
                }
            });

        });

        function getCombox(options,tar) {
            var dataHtml = '<select   >';
            var tarText;
            for(var i=0;i<options.length;i++){

                if(options[i].dtyId==tar){
                    dataHtml+='<option value="'+options[i].dtyId+'" selected="selected">'+options[i].dtyNam+'</option>'
                    tarText=options[i].dtyNam;


                }else {
                    dataHtml+='<option value="'+options[i].dtyId+'"  >'+options[i].dtyNam+'</option>'

                }
            }
            dataHtml+='</select>';
            dataHtml+='<div class="layui-unselect layui-form-select"><div class="layui-select-title"><input placeholder="varchar16" value="'+tarText+'" readonly="" class="layui-input layui-unselect" type="text"><i class="layui-edge"></i></div><dl class="layui-anim layui-anim-upbit"> ';

            for(var i=0;i<options.length;i++){
                if(options[i].dtyId==tar){
                    dataHtml+='<dd lay-value="'+options[i].dtyId+'" class="layui-this">'+options[i].dtyNam+'</dd>'
                }else {
                    dataHtml+='<dd lay-value="'+options[i].dtyId+'"  > '+options[i].dtyNam+ '</dd>'
                }
            }
            dataHtml+='</dl></div>';
            return dataHtml;
        }
        var SysDataType = '';
        $.get("/SysDataType/list", function(data){
            SysDataType=data;
            $("#colTy_id").html(getCombox(SysDataType,'ghf4'));
            renderForm();
            bindData( );
        });
        function renderForm() {
            $(".layui-select-title").each(function () {
                var x=$(this);
                x.click(function () {
                    var className=(x.parent().attr("class"));
                    if(className.indexOf("selected")>0)
                        x.parent().removeClass("layui-form-selected");
                    else
                        x.parent().addClass("layui-form-selected");

                });
                x.next().find("dd").each(function () {
                    $(this).click(function () {
                        $(this).addClass("layui-this");
                        $(this).siblings().removeClass("layui-this");
                        $(this).parent().parent().removeClass("layui-form-selected");

                        $(this).parent().prev().find("input").val($(this).text());
                        var temp=$(this).parent().parent().prev();
                        temp.find("option[value = '"+$(this).attr("lay-value")+"']").attr("selected","selected");
                        temp.find("option[value = '"+$(this).attr("lay-value")+"']").siblings().removeAttr("selected");
                    })
                });
                x.parent().prev().css("display","none");

                var child = $('input[type="checkbox"]');
                child.each(function( ){
                    $(this).next().click(function () {

                        var className=($(this).attr("class"));
                        if(className.indexOf("onswitch")>0){
                            $(this).removeClass("layui-form-onswitch");
                            $(this).prev().prop("checked",false);

                        } else{
                            $(this).addClass("layui-form-onswitch");
                            $(this).prev().val("是");
                            $(this).prev().prop("checked","checked");
                        }


                    });
                    $(this).css("display","none");
                });



            });
        }
        function getQueryString(name) {


            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg); //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
        function bindData( ) {
            $.get("/${className}?${primaryProp}="+getQueryString("${primaryProp}"), function(data){
                bindFromData(data);

            });

        }
        function bindFromData(data) {
            if(data!=undefined){
                $.each(data,function(n,value){
                    //console.log(n+'||'+value);
                    var dom=$("#"+n+"_id");
                    if(dom!=undefined){

                        if(dom.attr("type")=='text' )
                        {
                            dom.val( value);
                        }
                        else if(dom.attr("type")=='checkbox' )
                        {
                            if(dom.attr("lay-text")!=undefined)
                            {
                                if(value=='是'){
                                    dom.next().addClass("layui-form-onswitch");
                                    dom.val("是");
                                    dom.prop("checked","checked");
                                }else{
                                    dom.next().removeClass("layui-form-onswitch");
                                    dom.prop("checked",false);
                                }
                            }
                        }else if(dom.attr("tips")=='select' )
                        {
                            dom.find("dd").each(function () {
                                if($(this).attr("lay-value")==value){
                                    $(this).addClass("layui-this");
                                    $(this).siblings().removeClass("layui-this");
                                    dom.find("input").val($(this).text());

                                }
                            });
                            dom.find("select").attr("name", n);
                            dom.find("select").find("option[value = '"+value+"']").attr("selected","selected");
                            dom.find("select").find("option[value = '"+value+"']").siblings().removeAttr("selected");
                        }
                    }
                });
            }
        }



    });


</script>
</body>
</html>