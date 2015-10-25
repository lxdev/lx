<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String actionUrl = basePath + "/university/evaluate";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>写点评</title>
	<jc:plugin name="jquery_new"/>
	<jc:plugin name="jquery_ui"/>
	<jc:plugin name="bootstrap3"/>
	<jc:plugin name="bootstrap_main"/>
	<jc:plugin name="star_rating"/>
  </head>
  
  <body>
    <div class="container-fluid">
    	<s:action namespace="/user" name="head" executeResult="true"/>
   	</div>
   	<div class="container-fluid">
   		<div class="template-page-wrapper">
			<s:action namespace="/user" name="left" executeResult="true"/>
			<div class="templatemo-content-wrapper">
        		<div class="templatemo-content">
        			<div class="row">
        <div class="box paint color_7">
          <div class="title">
            <h4> <i class="icon-book"></i><span>写评论向导</span> </h4>
          </div>
          <div class="content full">
            <section id="wizard">
              <div id="rootwizard">
                <div class="content row-fluid mb5">
                  <ul class="row-fluid fluid mb5 nav nav-pills">
                    <li class='span3 <s:if test="step==0">active</s:if>'><a class="btn btn-default btn-large" href="#tab1" data-toggle="tab"><small>1.</small><strong> 选择求学经历</strong></a></li>
                    <li class='span3 <s:if test="step==1">active</s:if>'><a class="btn btn-default btn-large" href="#tab2" data-toggle="tab"><small>2.</small> <strong>评分</strong></a></li>
                    <li class='span3 <s:if test="step==2">active</s:if>'><a class="btn btn-default btn-large" href="#tab3" data-toggle="tab"><small>3.</small> <strong>留学感受</strong></a></li>
                    <li class='span3 <s:if test="step==3">active</s:if>'><a class="btn btn-default btn-large" href="#tab4" data-toggle="tab"><small>4.</small> <strong>上传照片</strong></a></li>
                    <li class='span3 <s:if test="step==4">active</s:if>'><a class="btn btn-default btn-large" href="#tab5" data-toggle="tab"><small>5.</small> <strong>谢谢</strong></a></li>
                  </ul>
                </div>
                <div class="tab-content content">
                  <div class='tab-pane <s:if test="step==0">active</s:if>' id="tab1">
                    <form id="form_user_info" class="form-horizontal" action="../university/evaluate_wizard" method="POST">
                    	<input type="hidden" name="step" value="0"/>
                    	<input type="hidden" name="userExtend.id" value="<s:property value="userExtend.id"/>"/>
                    	<input type="hidden" name="userExtend.user_id" value="<s:property value="userExtend.user_id"/>"/>
                    	<input type="hidden" name="userExtend.user_type" value="<s:property value="userExtend.user_type"/>"/>
                        <div class="form-group">
                            <label for="country_id" class="col-lg-2 control-label">请选择国家</label>
                            <div class="col-lg-4">
                            	<s:select list="%{countryList}" listKey="id" listValue="name" value="%{userExtend.country_id}" headerKey="0" headerValue="--请选择--" theme="simple" name="userExtend.country_id" id="country_id" cssClass="form-control" onchange="countryChange()"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="university_id" class="col-lg-2 control-label">选择学校</label>
                            <div class="col-lg-4">
                            	<s:select list="%{universityList}" listKey="id" listValue="university_name" value="%{userExtend.university_id}" headerKey="0" headerValue="--请选择--" theme="simple" name="userExtend.university_id" id="university_id" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="specialty_id" class="col-lg-2 control-label">就读专业</label>
                            <div class="col-lg-2">
                               	<s:select list="%{specialtyList}" listKey="id" listValue="specialty_name" value="%{userExtend.specialty_id}" headerKey="0" headerValue="--请选择--" theme="simple" name="userExtend.specialty_id" id="specialty_id" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="study_level_id" class="col-lg-2 control-label">就读学位</label>
                            <div class="col-lg-2">
                               	<s:select list="%{studyLevelList}" listKey="id" listValue="name" value="%{userExtend.study_level_id}" headerKey="0" headerValue="--请选择--" theme="simple" name="userExtend.study_level_id" id="study_level_id" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="graduate_date" class="col-lg-2 control-label">入学时间</label>
                            <div class="col-lg-2">
                            	<s:select list="%{graduateDateList}" listKey="id" listValue="name" value="%{userExtend.graduate_date}" headerKey="0" headerValue="--请选择--" theme="simple" name="userExtend.graduate_date" id="graduate_date" cssClass="form-control"/>
                            </div>
                        </div>
                        <input type="submit" id="" style="display:none"/>
                    </form>
                  </div>
                  <div class='tab-pane <s:if test="step==1">active</s:if>' id="tab2">
                    <form id="form_evaluate_score" class="form-horizontal" action="../university/evaluate_wizard" method="POST" onsubmit="return submit_evaluate()">
                    	<input type="hidden" name="step" value="1"/>
                    	<input type="hidden" name="evaluate.id" value="<s:property value="evaluate.id"/>"/>
                    	<input type="hidden" name="evaluateUniversity.university_id" value="<s:property value="evaluateUniversity.university_id"/>"/>
                    	<div class="form-group">
                    		<label for="option_quality" class="col-lg-1 col-sm-1 control-label">教学质量</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_quality" id="option_quality" value="<s:property value="evaluateUniversity.option_quality"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_professor" class="col-lg-1 col-sm-1 control-label">教授水平</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_professor" id="option_professor" value="<s:property value="evaluateUniversity.option_professor"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_reputation" class="col-lg-1 col-sm-1 control-label">当地声望</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_reputation" id="option_reputation" value="<s:property value="evaluateUniversity.option_reputation"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_environment" class="col-lg-1 col-sm-1 control-label">校园环境</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_environment" id="option_environment" value="<s:property value="evaluateUniversity.option_environment"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_outclass" class="col-lg-1 col-sm-1 control-label">课余生活</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_outclass" id="option_outclass" value="<s:property value="evaluateUniversity.option_outclass"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_security" class="col-lg-1 col-sm-1 control-label">治安状况</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_security" id="option_security" value="<s:property value="evaluateUniversity.option_security"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_job" class="col-lg-1 col-sm-1 control-label">就业情况</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_job" id="option_job" value="<s:property value="evaluateUniversity.option_job"/>" type="number">
                    		</div>
                    	</div>
                    	<div class="form-group">
                    		<label for="option_cost" class="col-lg-1 col-sm-1 control-label">性价比</label>
                    		<div class="col-lg-10 col-sm-10">
                    			<input name="evaluateUniversity.option_cost" id="option_cost" value="<s:property value="evaluateUniversity.option_cost"/>" type="number">
                    		</div>
                    	</div>
                    	<button id="btn_evaluate_submit" type="submit" class="btn btn-primary" style="display:none;">Submit</button>
                    </form>
                  </div>
                  <div class="tab-pane <s:if test="step==2">active</s:if>" id="tab3">
                    <form class="form-horizontal" action="../university/evaluate_wizard" method="POST">
                    	<input type="hidden" name="step" value="2"/>
                    	<input type="hidden" name="evaluate.id" value="<s:property value="evaluate.id"/>"/>
                        <div class="form-group">
                            <label for="" class="col-lg-2 control-label">留学感受</label>
                            <div class="col-lg-8">
                                <textarea id="feel" name="evaluate.evaluate_content" value="<s:property value="evaluate.evaluate_content"/>" style="width:100%" rows="15" placeholder="
        【最满意的地方】
        【最不满意的地方】
        【地理位置和学校规模】
        【学校在所在州和全美的受认可程度】
        【教学质量】
        【安全程度】
        【学费及生活费水平】
        【课余生活】
        【中国学生数量】
        【中国学生就业情况】
        【你所就读Program的情况】（如总人数、中国学生人数、教学情况、就业情况等）
        【其他描述】"><s:property value="evaluate.evaluate_content"/></textarea>
                            </div>
                        </div>
                    	<button id="btn_evaluate_content_submit" type="submit" class="btn btn-primary" style="display:none;">Submit</button>
                    </form>
                  </div>
                  <div class="tab-pane <s:if test="step==3">active</s:if>" id="tab4">

                  </div>
                  <div class="tab-pane" id="tab5">
                    <h2>谢谢!</h2>
                    <p>您的提交已收到了.</p>
                  </div>
                  <div class="description content">
                    <ul class="pager wizard mb5">
                      <li class="previous disabled">
                        <button class="btn btn-primary pull-left btn-large"><i class="icon-caret-left"></i> Previous</button>
                      </li>
                      <li class="next" style="display: inline;">
                        <button class="btn btn-primary btn-large pull-right">Next <i class="icon-caret-right"></i></button>
                      </li>
                      <li class="next finish" style="display: none;">
                        <button class="btn btn-success pull-right btn-large">Finish</button>
                      </li>
                    </ul>
                  </div>
                  
                </div>
              </div>
            </section>
          </div>
        <a class="btn change_color_outside"><i class="paint_bucket"></i></a></div>
        			</div>
        		</div>
        	</div>
		</div>
   		
   	</div>
    <script type="text/javascript">
    	/*选择求学经历*/
        var getUniversity = function(country){
            var url = '<s:url value="../user/register_university_list_by_country"/>';
            var option = { countryid: country };
            jQuery.post(url,option,
                    function(data)
                    {
                        if(data.message != ""){
                            alert(data.message);
                            return;
                        }
                        var lists="";
                        lists+="<option value='0'>---请选择专业---</option>";
                        if(null!=data.universityList)
                        jQuery.each(data.universityList, function()
                        {
                             lists+="<option value='"+this.id+"'>"+this.university_name+"</option>";
                        });
                        jQuery('#university_id').html(lists);

                        getSpecialty(country);
                    },
             "json");
        }
        var getSpecialty = function(country){
            var url = '<s:url value="../user/register_specialty_list_by_country"/>';
            var option = { countryid: country };
            jQuery.post(url,option,
                    function(data)
                    {
                        if(data.message != ""){
                            alert(data.message);
                            return;
                        }
                        var lists="";
                        lists+="<option value='0'>---请选择院校---</option>";
                        if(null!=data.specialtyList)
                        jQuery.each(data.specialtyList, function()
                        {
                             lists+="<option value='"+this.id+"'>"+this.specialty_name+"</option>";
                        });
                        jQuery('#specialty_id').html(lists);

                    },
             "json");
        }
        var countryChange = function(){
            var country = $("#country_id").val();
            if($.trim(country) == "" || country == 0){
                alert("请选择一个国家！");
                return;
            }
            getUniversity(country);
        }
    
        $(document).ready(function(){
            var _this = this;
            var element = $('#rootwizard');
            $navigation = element.find('ul:first', element);
            $activeTab = $navigation.find('li.active', element);
            var $total = $navigation.find('li').length;
            this.nextIndex = function(){
                $activeTab = $("li.span3.active", element);
                return $navigation.find('li').index($activeTab) + 1;
            };
            this.previousIndex = function() {
                $activeTab = $("li.span3.active", element);
                return $navigation.find('li').index($activeTab) - 1;
            };
            this.firstIndex = function() {
                return 0;
            };
            this.lastIndex = function() {
                return _this.navigationLength();
            };
            this.navigationLength = function() {
                return $navigation.find('li').length - 1;
            };
            this.currentIndex = function() {
                return $navigation.find('li').index($activeTab);
            };
            this.setBtnShow = function(index){
                var $current = index + 1;
                if($current >= $total) {
                    element.find('.pager .next').hide();
                    element.find('.pager .finish').show();
                    element.find('.pager .finish').removeClass('disabled');
                } else {
                    element.find('.pager .next').show();
                    element.find('.pager .finish').hide();
                }
            };
            this.fixNavigationButtons = function(){
                // Get the current active tab
                if(!$activeTab.length) {
                    // Select first one
                    $navigation.find('a:first').tab('show');
                    $activeTab = $navigation.find('li:first');
                }

                // See if we currently in the first then disable the previous and last buttons
                if(_this.firstIndex() >= _this.currentIndex()) {
                    $('li.previous', element).addClass('disabled');
                } else{
                    $('li.previous', element).removeClass('disabled');
                }

                if(_this.currentIndex() >= _this.navigationLength()) {
                    $('li.next', element).addClass('disabled');
                } else {
                    $('li.next', element).removeClass('disabled');
                }
            };
            // Work the next/previous buttons
            this.fixNavigationButtons();

            this.form_submit = function(index){
            	switch(index){
            	case 1:
            		$("#form_user_info input[type='submit']").click();
            		break;
            	case 2:
            		$("#btn_evaluate_submit").trigger('click');
            		break;
            	case 3:
            		$("#btn_evaluate_content_submit").trigger('click');
            		break;
            	}
            }
            $("#rootwizard .next").unbind("click").bind("click", function(){
                if(element.hasClass('last')) {
                    return false;
                }
                $index = _this.nextIndex();
                _this.form_submit($index);
                if(!is_submit_success)
                	return false;
                if($index > _this.navigationLength()) {
                } else {
                    $navigation.find('li:eq('+$index+') a').tab('show');
                }
                _this.setBtnShow($index);
            });
            $("#rootwizard .previous").unbind("click").bind("click", function(){
                // If we clicked the first then dont activate this
                if(element.hasClass('first')) {
                    return false;
                }
                is_submit_success = true;
                $index = _this.previousIndex();
                if($index < 0) {
                } else {
                    $navigation.find('li:eq('+$index+') a').tab('show');
                }
                _this.setBtnShow($index);
            });
            $('#rootwizard .finish').click(function() {
               alert('Finished! Starting over!');
               //$('#rootwizard').find("a[href*='tab1']").trigger('click');
            });
        });
        var is_submit_success = true;
        /*评分*/
        var rating_init = function(){
        	var options = {
        			min: 0,
        			max: 10,
        			step: 1,
        			size: 'sm',
        			stars: 10,
        			starCaptions: {
                        1: '非常水',
                        2: '很水',
                        3: '水',
                        4: '稍差',
                        5: '马马虎虎',
                        6: '一般',
                        7: '还可以',
                        8: '好',
                        9: '很好',
                        10: '非常好'
                    },
                    starCaptionClasses: {
                        1: 'label label-danger',
                        2: 'label label-warning',
                        3: 'label label-info',
                        4: 'label label-primary',
                        5: 'label label-success',
                        6: 'label label-danger',
                        7: 'label label-warning',
                        8: 'label label-info',
                        9: 'label label-primary',
                        10: 'label label-success'
                    },
                    clearCaption: '未选择'
        	};

        	$("#option_quality").rating(options);
        	$("#option_security").rating(options);
        	$("#option_job").rating(options);
        	$("#option_cost").rating(options);
			options.starCaptions = { 1: '教授水平差', 2: '很水', 3: '水', 4: '稍差', 5: '马马虎虎', 6: '一般', 7: '还可以', 8: '好', 9: '很好', 10: '学术水平高，人也非常好' };
        	$("#option_professor").rating(options);
        	options.starCaptions = { 1: '野鸡大学一枚', 2: '很水', 3: '水', 4: '稍差', 5: '马马虎虎', 6: '一般', 7: '还可以', 8: '好', 9: '很好', 10: '全美做好的学校' };
        	$("#option_reputation").rating(options);
        	options.starCaptions = { 1: '脏乱差', 2: '很水', 3: '水', 4: '稍差', 5: '马马虎虎', 6: '一般', 7: '还可以', 8: '好', 9: '很好', 10: '环境优雅气候宜人' };
        	$("#option_environment").rating(options);
        	options.starCaptions = { 1: '无聊之极', 2: '很水', 3: '水', 4: '稍差', 5: '马马虎虎', 6: '一般', 7: '还可以', 8: '好', 9: '很好', 10: '各种Party' };
        	$("#option_outclass").rating(options);
        }
        $(function(){
        	rating_init();
        });
        var submit_evaluate = function(){
        	var parent = $("#form_evaluate_score");
        	var result = "";
        	$("#form_evaluate_score").find("input[type='number']").each(function(index, el){
        		result += el.value + ",";
        	});
        	result = result.substring(0, result.length-1);
        	var tempStrs = result.replace('10', '11');
        	if(tempStrs.indexOf(0) >= 0){
        		alert("您还有未评分的选项！");
        		is_submit_success = false;
        		return false;
        	}
        	is_submit_success = true;
        	return true;
        }
        

    </script>
  </body>
</html>
