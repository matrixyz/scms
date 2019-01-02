layui.config({
	base : "js/"
}).use([ 'layer','jquery' ],function(){
	var  layer = parent.layer === undefined ? layui.layer : parent.layer,

		$ = layui.jquery;

	//加载页面数据

	$.get("/SysCol/list", function(data){

        var newData = data;
        $.get("/SysDataType/list", function(data){
            SysDataType=data;
            //执行数据渲染
            newsList(newData, $('#SysCol-list'),renderData);
        });


	});



    //全选事件
    $("#cbx-select-all").click(function () {
        var className=($(this).attr("class"));
        var child = $('#SysCol-list').find('tbody input[type="checkbox"][name="colId"]');
        if(className.indexOf("layui-form-checked")>0){
            $(this).removeClass("layui-form-checked");
            child.each(function( ){
                $(this).next().removeClass("layui-form-checked");
            });
        }else{
            $(this).addClass("layui-form-checked");
            child.each(function( ){

                $(this).next().addClass("layui-form-checked");
            });
        }
    });



    //渲染字段表的表格中的数据
    function renderData(currData  ){
        var dataHtml = '';
        if(currData.length != 0){
            for(var i=0;i<currData.length;i++){
                dataHtml += '<tr>'
                    +'<td><input name="colId"   type="checkbox"><div class="layui-unselect layui-form-checkbox  "><span>勾选</span><i class="layui-icon"></i></div></td>'
                    +'<td align="left"  >'+currData[i].colNam+'</td>'
                    +'<td align="left">'+ getCombox(SysDataType,currData[i].colTy)+'</td>'
                    +'<td> <div class="layui-unselect layui-form-select  "> ' +
                    '    <div class="layui-select-title"><input placeholder="varchar16" value="text" readonly="" ' +
                    '                                           class="layui-input layui-unselect" type="text"><i class="layui-edge"></i> ' +
                    '    </div> ' +
                    '    <dl class="layui-anim layui-anim-upbit"> ' +
                    '        <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea> ' +
                    '    </dl> ' +
                    '</div> </td>'
                    +'<td   >  '+currData[i].colShowFront+ '</td>'
                    +'<td   >  '+currData[i].colDef+ '</td>'


                    +'<td>'
                    +  '<a class="layui-btn layui-btn-mini news_edit" tips="'+currData[i].colId+'"><i class="iconfont icon-edit"></i> 编辑</a>'
                    +  '<a class="layui-btn layui-btn-normal layui-btn-mini news_collect"><i class="layui-icon">&#xe600;</i> 收藏</a>'
                    +  '<a class="layui-btn layui-btn-danger layui-btn-mini SysCol_del_btn" tips="'+currData[i].colId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
                    +'</td>'
                    +'</tr>';
            }
        }else{
            dataHtml = '<tr><td colspan="10">暂无数据</td></tr>';
        }
        return dataHtml;
    }


    function getPageHtml(totalBtnNo,currNo,pageNaviNo,nums) {

        var begin,end;
        if(totalBtnNo<=pageNaviNo){
            begin=1,end=totalBtnNo;
        }else {
            begin=Math.ceil(currNo/pageNaviNo)*pageNaviNo- pageNaviNo+1;
            if(currNo>pageNaviNo){
                end=begin+(currNo%pageNaviNo);
            }else {
                end=Math.ceil(totalBtnNo/nums);
            }

        }
        var html=' <div id="page" class="page">'+
            '<div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-0">'+
            '<a href="javascript:gotopage('+(currNo-1)+');" class="layui-laypage-prev" data-page="1">上一页</a>';
        for(var i=begin;i<=end;i++){
            if(i==currNo){
                html+='<span class="layui-laypage-curr"> <em class="layui-laypage-em"></em> <em>'+i+'</em> </span>';
            }else {
                html+='<a href="javascript:gotopage('+i+');" class="pgbtn"> '+i+ '</a>';
            }

        }

        if(totalBtnNo>end){
            html+='<span>…</span>';
        }

        html+='<a href="javascript:;" class="layui-laypage-last" title="尾页" data-page="'+totalBtnNo+'">末页</a>';
        if(totalBtnNo!=currNo){
            html+='<a href="javascript:gotopage('+(i+1)+');" class="layui-laypage-next" data-page="3">下一页</a>';

        }
        html+='</div> </div>';

        return html;

    }


	function getCombox(options,tar) {
        var dataHtml = '<select name="interest" lay-filter="aihao">';
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
        dataHtml+='<dd lay-value="asdf" class="">varchar16</dd>';
        for(var i=0;i<options.length;i++){
            if(options[i].dtyId==tar){
                dataHtml+='<dd lay-value="asdf" class="layui-this">'+options[i].dtyNam+'</dd>'
            }else {
                dataHtml+='<dd lay-value="asdf"  > '+options[i].dtyNam+ '</dd>'
            }
        }
        dataHtml+='</dl></div>';
        return dataHtml;
    }
    var SysDataType = '';

	function newsList(newsData,tableDom ,renderData_){
        //分页
        var nums = 10; //每页出现的数据量
        var pageNaviNo=8;//导航分页
        var page=Math.ceil(newsData.length/nums);//数据总页数


		function gotopage(currNo) {
			var totalBtnNo=newsData.length;
            tableDom.find(".pageContainer").html(getPageHtml(totalBtnNo,currNo,pageNaviNo, nums) );
            var currData = newsData.concat().splice(currNo*nums-nums, nums);//截取对应分页的数据
            tableDom.find(".news_content").html(renderData_(currData ));
            renderDatas();
        }
        window.gotopage=gotopage;



        //绑定数据表中的各种表单按钮的事件
		function renderDatas() {
            tableDom.find(".layui-select-title").each(function () {

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
                    })
                });


            })
            var child = tableDom.find('tbody input[type="checkbox"][name="colId"]');
            child.each(function(index, item){
                $(this).next().click(function () {

                    var className=($(this).attr("class"));
                    if(className.indexOf("checked")>0)
                        $(this).removeClass("layui-form-checked");
                    else
                        $(this).addClass("layui-form-checked");

                })
            });

            //radio 表单事件
            var child = tableDom.find('tbody input[type="checkbox"][name="colSer"]');
            child.each(function( ){
                $(this).next().click(function () {

                    var className=($(this).attr("class"));
                    if(className.indexOf("onswitch")>0)
                        $(this).removeClass("layui-form-onswitch");
                    else
                        $(this).addClass("layui-form-onswitch");

                })
            });
            var text = tableDom.find("textarea");
            text.each(function( ){

                $(this).on("blur",function () {
                    $(this).parent().parent().find("input").val($(this).val());
                    $(this).parent().parent().removeClass("layui-form-selected");
                });
            });

            tableDom.find('.news_edit').each(function( ){
                var id=$(this).attr("tips");
                $(this).click(function(){
                    var index = layui.layer.open({
                        title : "修改字段",
                        type : 2,
                        content : "/sys/SysCol-form.html?colId="+id,
                        success : function(layero, index){
                            setTimeout(function(){
                                layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            },500)
                        }
                    });
                    layui.layer.full(index);
                });
            });

            tableDom.find('.news_collect').each(function( ){

                $(this).click(function(){
                    var index = layui.layer.open({
                        title : "添加文章",
                        type : 2,
                        area: ['700px', '450px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content : '/sys/SysCol-form.html' ,
                        success : function(layero, index){
                            setTimeout(function(){
                                layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            },500)
                        }
                    });

                });
            });

            $(".SysCol_del_btn").click(function(){
                var colId=$(this).attr("tips");
                var confirm_index =layer.confirm('确认删除吗？', {
                    btn: ['确认', '取消' ] //可以无限个按钮
                    ,yes: function(index, layero){
                        var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
                        $.ajax({
                            url :  "/SysCol?colId="+colId,
                            type :'DELETE' ,
                            success: function (data) {
                                $(".search_btn").click();
                                layer.close(index);
                                layer.close(confirm_index);
                            }
                        });
                    }
                });
            });

        }


        gotopage(1);

	}
    //添加文章

    function setTableNameCallback(tabName,tabId) {
        $("#select-table-input").val(tabName);
        $("#colTbid").val(tabId);

        $("#select-table-input").next().css("display","none");
    }
    window.setTableNameCallback=setTableNameCallback;

    //查询
    $(".search_btn").click(function(){
        var param = '';
        var t = $("#SysCol-search-form").serializeArray();
        $.each(t, function() {
            param+=this.name+'='+this.value+'&';
        });


        var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
        $.Deferred().done(function() {
            $.ajax({
                url :  "/SysCol/list?"+param,//将json参数里的花括号转换成，以便后台可以接受参数
                type :'GET' ,
                // data : JSON.stringify(d)  , //转JSON字符串
                dataType: "json",//说明返回的数据类型，这里返回的是json类型
                contentType:'application/json;charset=UTF-8', //contentType很重要
                success: function (data) {
                    newsList(data, $('#SysCol-list'),renderData);
                },
                complete: function (data) {

                },
                error: function (XMLHttpRequest, textStatus, thrownError) {
                }
            });
        }, function() {
            layer.close(index);
        }).resolve();





    });
    var x='<div class="float-div-iframe"  >        <iframe src="/sys/SysTable-list.html" style="width:100%;height:100%;"></iframe>     </div>';
    $("#select-table-input").after(x);
    $("#select-table-input").click(function(){

        if($(this).next().css("display")=='none'){
            $(this).next().css("display","block");
        }else {
            $(this).next().css("display","none");
        }

    });
    $(".SysCol_Add_btn").click(function(){

        var index = layui.layer.open({
            title : "添加字段",
            type : 2,
            content : '/sys/SysCol-form.html?colTbid='+$("#colTbid").val() ,
            success : function( ){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回字段列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        });
        layui.layer.full(index);
    });



});
