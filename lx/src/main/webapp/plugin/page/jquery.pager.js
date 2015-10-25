jQuery.pagefoot =
{
    //生成分页脚
    create: function(_this, s) {
    	//总数量
        var pageCount = 0;
        pageCount = (s.count / s.pagesize <= 0) ? 1 : (parseInt(s.count / s.pagesize) + ((s.count % s.pagesize > 0) ? 1 : 0));
        
        //获取cookie里面的页码
        s.current=$.cookie('cookie_page_'+s.pluginCode)==null?1:$.cookie('cookie_page_'+s.pluginCode);
        
        //当前页码
        s.current = (s.current > pageCount) ? pageCount : parseInt(s.current);
        
        //由于已经修改好之前的页码判断，所以 此代码暂时没用了 edited by dongminghao
        //$.cookie('cookie_page_'+s.pluginCode,s.current);
        
        //var orderString='<select id="selectOrder" onchange="search001();"><option selected="selected" value="id">ID</option><option value="fraction">分数</option></select>';
        //var sortString='<select id="selectSort" onchange="search001();"><option selected="selected" value="asc">升序</option><option value="desc">降序</option></select>' ; 
        //var pageSizeString='<select id="pageSizeSelect'+pluginCode+'" onchange="psize=this.value;search'+pluginCode+'();"><option selected="selected" value="15">15条</option><option value="50">50条</option><option value="100">100条</option><option value="200">200条</option></select>' ;
        //设置pageSize参数
        var pageSizeString='<select id="pageSizeSelect'+s.pluginCode+'" onchange="psize'+s.pluginCode+'=this.value;search'+s.pluginCode+'();"><option selected="selected" value="15">15条</option><option value="30">30条</option><option value="50">50条</option></select>' ;
        //执行自定义工具栏方法
        
        var strPage ="<span style='float:left;'><label>每页显示&nbsp;&nbsp;"+pageSizeString+"&nbsp;&nbsp;</label><label>共有"+s.count+"条记录</label></span><span id='"+s.customToolbarID+"' style='float:left;'></span><span class=\"disabled\"><label>当前" + s.current + "页/总" + pageCount + "页</label></span>";
        if (s.current <= 1) {
            strPage += "<span  class=\"csspage\" >"+s.firstpage+"</span><span>" + s.previous + "</span>";
        } else {
            strPage += "<a href='1'>" + s.firstpage + "</a><a href=\"" + (s.current - 1) + "\">" + s.previous + "</a>";
            
        }
        var startP = 1;
        startP = 1
        var anyMore;
        //中间显示页码数
        anyMore = parseInt(s.displaynum / 2)
        var endP = ( (parseInt(s.current) + anyMore)) > pageCount ? pageCount : (parseInt(s.current) + anyMore);

        var pCount = s.pagesize - s.displaylastNum;
        if (s.current > s.displaynum) {
            startP = s.current - anyMore;
            for (i = 1; i <= s.displaylastNum; i++) {
                strPage += "<a href=\"" + i + "\">" + i + "</a>";
            }
            strPage += "...";
        }
        
        for (i = startP; i <= endP; i++) {
            if (s.current == i) {
                strPage += "<span class=\"current\">" + i + "</span>";
            } else {
                strPage += "<a href=\"./" + i + "\">" + i + "</a>";
            }
        }
        if ((parseInt(s.current) + parseInt(s.displaynum)) <= pageCount) {
        	strPage += "...";
        }
        
        if ((parseInt(s.current) + s.displaynum) <= pageCount) {
            for (i = pageCount - s.displaylastNum + 1; i <= pageCount; i++) {
                strPage += "<a href=\"" + i + "\">" + i + "</a>";
            }
        }
        if (s.current >= pageCount) {
            strPage += "<span>" + s.next + "</span><span>" + s.endpage+ "</span>";
        } else {
            strPage += "<a href=\"" + (parseInt(s.current) + 1) + "\">" + s.next + "</a><a href=\""+pageCount+"\">" + s.endpage + "</a>";
        }
        strPage+="<span class=\"disabled\"><input type=\"text\" class=\"topage\" value=\""+s.textvalue+"\"/><input class=\"btnto\" type=\"button\" value=\"GO\" /></span>";
        $(_this).empty().append(strPage).find("a").click(function() {
            var ln = this.href.lastIndexOf("/");
            var href = this.href;
            var page = parseInt(href.substring(ln + 1, href.length));
            s.current = page;
    
            if (!$.pagefoot.paging(page, s.paging))
                return false;

            $.pagefoot.create(_this, s);
            return false;
        });
        
        $(_this).find(":button").click(function(){
        	var text = $(_this).find(":text").val();
        	var page = parseInt(text);
        	if($.pagefoot.checktype(page)){
        		if(page>pageCount){
        			alert("不在查询范围内！");
        			return false;
        		}
        	}else{
        		alert("请输入正确页码！");
        		return false;
        	}
        	s.current=page;
        	s.textvalue=page;
        	 if (!$.pagefoot.paging(page, s.paging))
                 return false;

             $.pagefoot.create(_this, s);
             return false;
        });
        //选中pageSize
        $("#pageSizeSelect"+s.pluginCode).val(s.pagesize);
        return this;
    },
    paging: function(page, callback) {
        if (callback) {
            if (callback(page) == false)
                return false;
        }
        return true;
    },
    checktype:function(typevalue){
    	var re =/^[1-9][0-9]*$/;
    	return re.test(typevalue);
    }
}
jQuery.fn.pagefoot = function(opt) {
    var setting = { pagesize: 10  //每页显示的页码数
        , count: 0                //数据条数
        , css: "mj_pagefoot"      //分页脚css样式类
        , current: 1              //当前页码
		, displaynum: 7			//中间显示页码数
		, displaylastNum: 5		//最后显示的页码数
        , previous: "<img border='0'  src='"+WEB_PLUGINS+"/plugin/page/images/page_left.gif' />"      //上一页显示样式
        , next: "<img border='0' src='"+WEB_PLUGINS+"/plugin/page/images/page_right.gif' />"          //下一页显示样式
        , paging: null            //分页事件触发时callback函数
        , firstpage:"<img border='0' src='"+WEB_PLUGINS+"/plugin/page/images/navLeft.gif' />"
        , endpage:"<img border='0'  src='"+WEB_PLUGINS+"/plugin/page/images/navRight.gif' />"
        , textvalue:1
        , customToolbarID:null
        , pluginCode:null
    };
    opt = opt || {}	
    $.extend(setting, opt);
    return this.each(function() {
        $(this).addClass(setting.css);
        $.pagefoot.create(this, setting);
    });
}
