function removeQama(e) {
    while (e.substring(0, 1) == ",") {
        e = e.substring(1, e.length)
    }
    while (e.substring(e.length - 1, e.length) == ",") {
        e = e.substring(0, e.length - 1)
    }
    return e
}
function removeDuplicateCountries(e, t, n) {
    var r = "";
    var i = "";
    var s = "N";
    if (t.indexOf(",") != -1) {
        var o = t.split(",");
        var u = e.split(",");
        for (var a = 0; a < o.length; a++) {
            if (o[a].indexOf("-") != -1) {
                r = r + o[a] + ",";
                i = i + u[a] + ",";
                s = "Y"
            } else if (t.indexOf(o[a] + "-") == -1) {
                if (s == "N") {
                    r = r + o[a] + ",";
                    i = i + u[a] + ","
                } else {
                    if (a == 1) {
                        r = r + o[a] + ",";
                        i = i + u[a] + ","
                    } else {
                        r = r + o[a];
                        i = i + u[a]
                    }
                }
            }
        }
        r = removeQama(r);
        i = removeQama(i);
        return i + "$$$$" + r
    } else {
        return "NO-SPLIT"
    }
}
function removeCntryValues(e) {
    var t = "";
    var n = "";
    var r = "";
    var i = "NO-SPLIT";
    if ($1("filterCntrds") != null) {
        var s = $1("filterCntrds").value;
        if (s.indexOf(",") != -1) {
            var o = s.split(",");
            for (var u = 0; u < o.length; u++) {
                if (o[u].indexOf("-") != -1) {
                    var a = o[u].split("-");
                    if (a[1] != e) {
                        n = n + "," + a[0];
                        r = r + "," + a[1];
                        t = t + "," + a[0] + "-" + a[1]
                    } else {
                        n = n + "," + a[0];
                        t = t + "," + a[0]
                    }
                } else if (o[u] != e) {
                    n = n + "," + o[u];
                    t = t + "," + o[u]
                }
            }
            if (r != "") {
                i = removeQama(n) + "$$$$" + removeQama(r) + "$$$$" + removeQama(t);
                r
            } else {
                i = removeQama(n) + "&&&&" + removeQama(t)
            }
        } else {
            if (s.indexOf("-") != -1) {
                var a = s.split("-");
                n = n + a[0];
                t = t + a[0];
                i = removeQama(n) + "&&&&" + removeQama(t)
            } else {
                i = "NO-SPLIT"
            }
        }
    }
    return i
}
function finduplicateCountries(e) {
    var t = "";
    var n = "false";
    var r = "";
    var i = "N";
    if ($1("cntryIds") != null && $1("cntryIds").value != "-1") {
        t = $1("cntryIds").value;
        r = r + $1("cntryIds").value;
        n = "true"
    }
    if ($1("cntryIds1") != null && $1("cntryIds1").value != "-1") {
        if (n == "true") {
            t = t + "," + $1("cntryIds1").value;
            r = r + "," + $1("cntryIds1").value
        } else {
            t = $1("cntryIds1").value;
            r = r + $1("cntryIds1").value
        }
        n = "true"
    }
    if ($1("cntryIds2") != null && $1("cntryIds2").value != "-1") {
        if (n == "true") {
            t = t + "," + $1("cntryIds2").value;
            r = r + "," + $1("cntryIds2").value
        } else {
            t = $1("cntryIds2").value;
            r = r + $1("cntryIds2").value
        }
    }
    if (t != "") {
        t = removeQama(t);
        if (t.indexOf(",") != -1) {
            var s = t.split(",");
            var o = s.sort();
            for (var u = 0; u < s.length - 1; u++) {
                if (o[u + 1] == o[u]) {
                    i = "Y"
                }
            }
        }
    }
    if (i == "Y") {
        e = "Duplicate_Regions"
    }
    return e
}
function formRefineUrl(e, t, n) {
    var r = "";
    if (e == "CLEAR") {
        if ($1("natinalityid") != null) {
            r = r + "&nationCode=" + $1("natinalityid").value
        }
    } else {
        if ($1("nationId") != null && $1("nationId").value != "-1") {
            //r = r + "&nationCode=" + $1("nationId").value
        }
    }
    if($1("countryId") != null && $1("countryId").value != "-1"){
    	r = r + "&countryId=" + $1("countryId").value;
    }
    if($1("studyLevelId") != null && $1("studyLevelId").value != "-1"){
    	r = r + "&studyLevelId=" + $1("studyLevelId").value;
    }
    if($1("ctitle") != null && $1("ctitle").value != ""){
    	r = r + "&ctitle=" + $1("ctitle").value;
    }
    if (e == "CLEAR") {
        if ($1("hidnationCntryCode") != null) {
            r = r + "&nationCntryCode=" + $1("hidnationCntryCode").value
        }
    } else {
        if ($1("nationcntryid") != null && $1("nationcntryid").value != "-1") {
            if ($1("nationcntryid").value.indexOf(":") != -1) {
                var i = $1("nationcntryid").value.split(":");
                r = r + "&nationCntryCode=" + i[0]
            } else {
                r = r + "&nationCntryCode=" + $1("nationcntryid").value
            }
        }
    }
    if (e == "CLEAR") {
        if ($1("hidmyqualid") != null) {
            r = r + "&userQual=" + $1("hidmyqualid").value
        }
    } else {
        if ($1("userQualId") != null && $1("userQualId").value != "-1") {
            r = r + "&userQual=" + $1("userQualId").value
        }
    }
    if (e == "CLEAR") {
        if ($1("hiduserGradeid") != null) {
            r = r + "&userGrade=" + $1("hiduserGradeid").value
        }
    } else {
        if ($1("userGradeId") != null && $1("userGradeId").value != "-1") {
            r = r + "&userGrade=" + $1("userGradeId").value
        }
    }
    if (e == "CLEAR") {
        if ($1("grdScrId") != null) {
            r = r + "&gradeScore=" + encodeURIComponent($1("grdScrId").value)
        }
    } else {
        if ($1("userGradeScrId") != null && $1("userGradeScrId").value != "-1") {
            r = r + "&gradeScore=" + encodeURIComponent($1("userGradeScrId").value)
        } else {
            if ($1("gradeScoreid") != null && trim($1("gradeScoreid").value) != "") {
                if ($1("gradeScoreid").value != $1("deftextid").value) {
                    r = r + "&gradeScore=" + encodeURIComponent($1("gradeScoreid").value)
                }
            }
        }
    }
    if (e == "pageNo") {
        r = r + "&pageNo=" + t
    }
    if ($1("hidCountryid") != null) {
        r = r + "&countryid=" + $1("hidCountryid").value
    }
    if (e == "CLEAR") {
        if (t != "PARENT") {
            if ($1("hidparentslevelId") != null) {
                r = r + "&parentQualId=" + $1("hidparentslevelId").value
            }
        }
    } else {
        if ($1("parentSlevelId") != null && $1("parentSlevelId").value != "-1") {
            r = r + "&parentQualId=" + $1("parentSlevelId").value
        }
    }
    if (e == "CLEAR") {
        if (t != "SUBQUAL") {
            if ($1("hidsubQualid") != null) {
                r = r + "&subQualId=" + $1("hidsubQualid").value
            }
        }
    } else {
        if ($1("childSlevelId") != null && $1("childSlevelId").value != "-1") {
            r = r + "&subQualId=" + $1("childSlevelId").value
        }
    }
    if (t != "CATG") {
        if (e == "CLEAR") {
            if ($1("hidcatId") != null) {
                r = r + "&catCode=" + $1("hidcatId").value
            }
        } else {
            if ($1("subjectId") != null && $1("subjectId").value != "-1") {
                r = r + "&catCode=" + $1("subjectId").value
            }
        }
    } else if (t == "CATG") {
        if (n != undefined) {
            r = r + "&catCode=" + n
        }
    }
    if (e == "CLEAR") {
        if (t != "SMODE") {
            if ($1("smodeId") != null) {
                r = r + "&smode=" + $1("smodeId").value
            }
        }
    } else {
        if ($1("smode") != null && $1("smode").value != "-1") {
            r = r + "&smode=" + $1("smode").value
        }
    }
    if (e == "CLEAR") {
        if (t != "SLENGTH") {
            if ($1("studylengthId") != null) {
                r = r + "&studylength=" + $1("studylengthId").value
            }
        }
    } else {
        if ($1("sduration") != null && $1("sduration").value != "-1") {
            r = r + "&studylength=" + $1("sduration").value
        }
    }
    if ($1("applyNowId") != null) {
        r = r + "&applyNow=" + $1("applyNowId").value
    }
    if (t != "KEYWORD") {
        if ($1("hidkeywordid") != null) {
            //r = r + "&keyword=" + $1("hidkeywordid").value
        	r = r + "&college=" + $1("hidkeywordid").value
        }
    }
    if (e == "CLEAR") {
        if (t != "ENGLISH") {
            if ($1("hidenglishType") != null) {
                r = r + "&englishType=" + $1("hidenglishType").value
            }
        }
    } else {
        if ($1("englishId") != null && $1("englishId").value != "-1") {
            r = r + "&englishType=" + $1("englishId").value
        }
    }
    if (e == "CLEAR") {
        if (t != "ENGLISH") {
            if ($1("hidengscord") != null) {
                r = r + "&englishScore=" + $1("hidengscord").value
            }
        }
    } else {
        if ($1("englishScoreId") != null && $1("englishScoreId").value != "-1") {
            r = r + "&englishScore=" + $1("englishScoreId").value
        }
    }
    if (t != "ORDERBY") {
        if (e == "sortBy") {
            r = r + "&orderBy=" + t
        } else if ($1("sortbyid") != null) {
            r = r + "&orderBy=" + $1("sortbyid").value
        }
    }
    if (t != "COLLEGE") {
        if ($1("hidcollegeId") != null) {
            r = r + "&collegeId=" + $1("hidcollegeId").value
        }
    }
    if ($1("urlcatId") != null) {
        r = r + "&urlcatId=" + $1("urlcatId").value
    }
    if (! (t == "Next" || t == "Update")) {
        if (t == "tierFlag") {
            r = r + "&tier2Flag=T2"
        } else if ($1("tier2Flag") != null) {
            if ($1("tier2Flag").value == "Y") {
                r = r + "&tier2Flag=T2"
            }
        } else if (norestierflag == "Y") {
            r = r + "&tier2Flag=T2";
            norestierflag = "N"
        }
    }
//    if ($1("provCntryId") != null) {
//        r = r + "&provCntryId=" + $1("provCntryId").value
//    }
    if($1("unicountryStyleId") != null){
    	r = r + "&unicountryStyleId=" + $1("unicountryStyleId").value
    }
    if ($1("scholarSearch") != null) {
        r = r + "&scholarSearch=" + $1("scholarSearch").value
    }
    if (e == "CLEAR") {
        if (t != "FEES") {
            if ($1("lwTutFees") != null) {
                r = r + "&lwtutfees=" + $1("lwTutFees").value
            }
            if ($1("upTutFees") != null) {
                r = r + "&uptutfees=" + $1("upTutFees").value
            }
        }
    } else {
//        r = getTutionFees(r)
    }
    if ($1("hidcollegeId") != null) {
        if ($1("hidcountryIds") != null) {
            r = r + "&countryIds=" + $1("hidcountryIds").value + "&filterCntrds=" + $1("filterCntrds").value
        }
        if ($1("regionIds") != null) {
            r = r + "&regionIds=" + $1("regionIds").value
        }
    } else {
        if (e == "Lookup_Search") {
            if ($1("filterCntrds") != null) {
                r = r + "&countryIds=" + $1("hidcountryIds").value + "&filterCntrds=" + $1("filterCntrds").value
            } else {
                r = getContryIds(r, n)
            }
        } else {
            r = getContryIds(r, n)
        }
    }
    return r
}
function getTutionFees(e) {
    var t = null;
    var n = null;
    var r = $1("tutionId").value;
    if (r.indexOf(",") != -1) {
        var i = r.split(",");
        if ($1("lwTutFees") != null && $1("lwTutFees").value == $1("min").value) {
            t = $1("lwTutFees").value
        }
        if ($1("upTutFees") != null && $1("upTutFees").value == $1("max").value) {
            n = $1("upTutFees").value
        }
        if (trim($1("min").value) != "" && $1("min").value != i[0]) {
            t = $1("min").value
        }
        if (trim($1("max").value) != "" && $1("max").value != i[i.length - 1]) {
            n = $1("max").value
        }
    }
    if (n != null && t == null) {
        if (n != "0") {
            e = e + "&uptutfees=" + n
        }
    } else if (n == null && t != null) {
        e = e + "&lwtutfees=" + encodeURIComponent(t)
    } else if (n != null && t != null) {
        if (n == t) {
            e = e + "&uptutfees=" + n
        } else {
            e = e + "&lwtutfees=" + t + "&uptutfees=" + n
        }
    }
    return e
}
function getContryIds(e, t) {
    var n = "";
    var r = "false";
    var i = "";
    var s = "false";
    var o = "";
    var u = "N";
    if ($1("cntryIds") != null && $1("cntryIds").value != "-1") {
        if ($1("cntryIds").value != t) {
            n = $1("cntryIds").value;
            o = o + $1("cntryIds").value;
            r = "true"
        }
    }
    if ($1("countyIds") != null && $1("countyIds").value != "-1") {
        if ($1("countyIds").value != t) {
            i = $1("countyIds").value;
            o = o + "-" + $1("countyIds").value;
            s = "true"
        }
    }
    if ($1("cntryIds1") != null && $1("cntryIds1").value != "-1") {
        if ($1("cntryIds1").value != t) {
            if (r == "true") {
                n = n + "," + $1("cntryIds1").value;
                o = o + "," + $1("cntryIds1").value
            } else {
                n = $1("cntryIds1").value;
                o = o + $1("cntryIds1").value
            }
            r = "true"
        }
    }
    if ($1("countyIds1") != null && $1("countyIds1").value != "-1") {
        console.log("=== " + $1("countyIds1").value);
        if ($1("countyIds1").value != t) {
            if (s == "true") {
                i = i + "," + $1("countyIds1").value
            } else {
                i = $1("countyIds1").value
            }
            o = o + "-" + $1("countyIds1").value;
            s = "true"
        }
    }
    if ($1("cntryIds2") != null && $1("cntryIds2").value != "-1") {
        if ($1("cntryIds2").value != t) {
            if (r == "true") {
                n = n + "," + $1("cntryIds2").value;
                o = o + "," + $1("cntryIds2").value
            } else {
                n = $1("cntryIds2").value;
                o = o + $1("cntryIds2").value
            }
        }
    }
    if ($1("countyIds2") != null && $1("countyIds2").value != "-1") {
        if ($1("countyIds2").value != t) {
            if (s == "true") {
                i = i + "," + $1("countyIds2").value
            } else {
                i = $1("countyIds2").value
            }
            o = o + "-" + $1("countyIds2").value
        }
    }
    if (t != undefined && t.indexOf("-") == -1) {
        var a = removeCntryValues(t);
        if (a.indexOf("$$$$") != -1) {
            var f = a.split("$$$$");
            n = f[0];
            i = f[1];
            o = f[2]
        } else if (a.indexOf("&&&&") != -1) {
            var f = a.split("&&&&");
            n = f[0];
            o = f[1]
        } else {
            n = "";
            o = ""
        }
        var l = removeDuplicateCountries(n, o, t);
        if (l.indexOf("$$$$") != -1) {
            var a = l.split("$$$$");
            n = a[0];
            o = a[1]
        }
    }
    if (n != "") {
        n = removeQama(n);
        if (e.indexOf("countryIds") == -1) {
            e = e + "&countryIds=" + n
        }
    }
    if (i != "") {
        if (e.indexOf("regionIds") == -1) {
            e = e + "&regionIds=" + i
        }
    }
    if (o != "") {
        if (o.indexOf("-") != -1) {
            if (o.indexOf(",") != -1) {
                var c = o.split(",");
                var h = c.sort();
                for (var p = 0; p < c.length - 1; p++) {
                    if (h[p + 1] == h[p]) {
                        u = "Y"
                    }
                }
            }
        } else if (o.indexOf(",") != -1) {
            var c = o.split(",");
            var h = c.sort();
            for (var p = 0; p < c.length - 1; p++) {
                if (h[p + 1] == h[p]) {}
            }
        }
        if (e.indexOf("filterCntrds") == -1) {
            e = e + "&filterCntrds=" + o
        }
    }
    if (u == "Y") {
        e = "Duplicate_Regions"
    }
    return e
}

function searchAjaxResult(e, t, n){
    var url = "ajax_programs";
    if(n)
    	url = "ajax_universitys";
    var options = {};
    options = programCondition();
    jQuery.post(url, options, 
	        function(data)
	    	{
	    		debugger;
	    	},
	 "json");
}

/*点击查询按钮*/
function loadRefineResult(e, t, n) {
//	searchAjaxResult(e, t, n);
//	return;
    allowHashToUpdateApp = false;
    backbuttonflag = true;
    var r = "N";
    var i = "N";
    //var s = contextPath + "/searchajax.html?search=coursesearch";
    var s = "programs?search=coursesearch";
    if(n)
    	s = n + "?search=universitysearch";
    if (t == "Next" || t == "Update") {
        if ($1("nationId") != null && $1("nationId").value == "-1") {
            i = "Y";
            alert($1("msg4").value)
        } else if ($1("nationcntryid") != null && $1("nationcntryid").value == "-1") {
            i = "Y";
            alert($1("msg5").value)
        }
        if ($1("userGradeId") != null && $1("userGradeId").value != "-1") {
            if ($1("userGradeScrId") != null) {
                if ($1("userGradeScrId").value == "-1") {
                    i = "Y";
                    checkGrdValidation($1("msg3").value)
                }
            } else if ($1("gradeScoreid") != null) {
                if (trim($1("gradeScoreid").value) == "") {
                    i = "Y";
                    checkGrdValidation($1("msg3").value)
                } else if ($1("gradeScoreid").value == $1("deftextid").value) {
                    i = "Y";
                    checkGrdValidation($1("msg3").value)
                } else if (isNaN($1("gradeScoreid").value.replace("%", ""))) {
                    i = "Y";
                    checkGrdValidation($1("validateMsg").value)
                } else {
                    if (! (parseFloat($1("gradeScoreid").value) >= parseFloat($1("minval").value) && parseFloat($1("gradeScoreid").value) <= parseFloat($1("maxval").value) || parseFloat($1("gradeScoreid").value) <= parseFloat($1("minval").value) && parseFloat($1("gradeScoreid").value) >= parseFloat($1("maxval").value))) {
                        i = "Y";
                        checkGrdValidation($1("rangeMsgid").value)
                    }
                }
            }
        }
    }
    if (i == "N") {
        var o;
        if (e == "CLEARALL") {
            if ($1("hidkeywordid") != null) {
                o = "&keyword=" + $1("hidkeywordid").value
            } else {
                o = "&catCode=" + $1("urlcatId").value + "&urlcatId=" + $1("urlcatId").value
            }
            if ($1("clearallflag")) {
                o = o + "&nationCode=" + $1("natinalityid").value + "&nationCntryCode=" + $1("hidnationCntryCode").value
            }
        } else {
            //o = formRefineUrl(e, t, n)
        	if(n == 'universitys')
        		o = universityCondition();
        	else
        		o = programCondition();
        	/*排序*/
        	if (t != "ORDERBY") {
                if (e == "sortBy") {
                    r = r + "&orderBy=" + t
                } else if ($1("sortbyid") != null) {
                    r = r + "&orderBy=" + $1("sortbyid").value
                }
            }
        }
        if ($1("hidcollegeId") == null) {
            if (o.indexOf("regionIds") == -1 && o.indexOf("countryIds") != -1) {
                o = finduplicateCountries(o)
            }
        }
        if (o == "Duplicate_Regions") {
            alert("You've already selected the country/region. Choose another country/region")
        } else {
            if (t == "Next" || t == "Update") {
                //trackEventStats(t)
            }
            window.location.hash = "#search" + o;
            if (e == "sortBy") {
                //$1("ajax_sr_loading_id").style.display = "block";
                //$1("ajax_sr_light_id").style.display = "block"
            } else {
                //$1("ajax_sr_loading").style.display = "block";
                //$1("ajax_sr_light").style.display = "block"
            }
            var u = new sack;
            u.requestFile = s + o;
            u.onCompletion = function() {
                loadAjaxSearchResult(u.response, r, "N")
            };
            u.runAJAX()
        }
    }
}
/*设施院校查询的条件*/
function universityCondition(){
	var r = "";
	/*国家Id*/
	if($1("unicountryStyleId") != null){
    	r = r + "&unicountryStyleId=" + $1("unicountryStyleId").value
    }
	/*院校名称*/
	if($1("college") != null && $1("college").value != "" && $1("college").value != $1("defaultunival").value){
    	//r = r + "&college=" + $1("college").value;
    	r = r + "&college=" + encodeURI(encodeURI($1("college").value));
    }
	/*综合排名*/
	if($("input[name='ranking']:checked").length > 0){
		$('input[name="ranking"]:checked').each(function (index, el){
			r = r + "&ranking=" + encodeURI(encodeURI(el.value));
		});
	}
	/*地区*/
	if($("input[name='area']:checked").length > 0){
		var val = $("input[name='area']:checked").val();
		r = r + "&area=" + encodeURI(encodeURI(val));
	}
	/*学费*/
	if($("input[name='tuition']:checked").length > 0){
		var val = $("input[name='tuition']:checked").val();
		r = r + "&tuition=" + encodeURI(val);
	}
	/*公私立*/
	if($("input[name='is_public_school']:checked").length > 0){
		r = r + "&is_public_school=" + $("input[name='is_public_school']:checked").val();
	}
	
	return r;
}
function programCondition(){
	var r = "";
	var condition = {};
	/*国家Id*/
	if($1("countryId") != null){
    	r = r + "&countryId=" + $1("countryId").value;
    	condition.countryId = $1("countryId").value;
    }
	/*学位Id*/
	if($1("studyLevelId") != null){
    	r = r + "&studyLevelId=" + $1("studyLevelId").value;
    	condition.studyLevelId = $1("studyLevelId").value;
    }
	/*专业名称*/
	if($1("ctitle") != null && $1("ctitle").value != "" && $1("ctitle").value != $1("defaultval").value){
    	r = r + "&ctitle=" + encodeURI(encodeURI($1("ctitle").value));
    	condition.ctitle = encodeURI($1("ctitle").value);
    }
	/*综合排名*/
	if($("input[name='ranking']:checked").length > 0){
		$('input[name="ranking"]:checked').each(function (index, el){
			r = r + "&ranking=" + encodeURI(encodeURI(el.value));
	    	condition.ranking = encodeURI(el.value);
		});
	}
	/*地区*/
	if($("input[name='area']:checked").length > 0){
		var val = $("input[name='area']:checked").val();
		r = r + "&area=" + encodeURI(encodeURI(val));
    	condition.area = encodeURI(val);
	}
	//totef
	if($("input[name='totef']:checked").length > 0){
		r = r + "&totef=" + $("input[name='totef']:checked").val();
    	condition.totef = $("input[name='totef']:checked").val();
	}
	//ietls
	if($("input[name='ietls']:checked").length > 0){
		r = r + "&ietls=" + $("input[name='ietls']:checked").val();
    	condition.ietls = $("input[name='ietls']:checked").val();
	}
	//gre
	if($("input[name='gre']:checked").length > 0){
		r = r + "&gre=" + $("input[name='gre']:checked").val();
    	condition.gre = $("input[name='gre']:checked").val();
	}
	//gmat
	if($("input[name='gmat']:checked").length > 0){
		r = r + "&gmat=" + $("input[name='gmat']:checked").val();
    	condition.gmat = $("input[name='gmat']:checked").val();
	}
	//提供语言课
	if($("input[name='is_language_score']:checked").length > 0){
		r = r + "&is_language_score=" + $("input[name='is_language_score']:checked").val();
    	condition.is_language_score = $("input[name='is_language_score']:checked").val();
	}
	//入学时间
	if($("input[name='time_of_enrollment']:checked").length > 0){
		var val = $("input[name='time_of_enrollment']:checked").val();
		r = r + "&time_of_enrollment=" + encodeURI(encodeURI(val));
    	condition.time_of_enrollment = encodeURI(val);
	}
	//工作经验:
	if($("input[name='work_experience_require']:checked").length > 0){
		r = r + "&work_experience_require=" + $("input[name='work_experience_require']:checked").val();
    	condition.work_experience_require = $("input[name='work_experience_require']:checked").val();
	}
	
	
	return r;
	//return condition;
}
function loadAjaxSearchResult(response, dispFlag, refineRsltFlag) {
    var res = response;
    var noresultflag = "N";
    if (res.indexOf("No-Result") != -1) {
        var resSplit = res.split("$$$$");
        noresultflag = "Y";
        tier2flag = "Y";
        if (norestierflag == "Y") {
            norestierflag == "Y"
        }
        $1("noresId").innerHTML = resSplit[2];
        $1("sr_result").style.display = "none";
        $1("sr_result_id").style.display = "block";
        $1("noresId").style.display = "block";
        $1("headid").style.display = "none";
        $1("zeroheadid").innerHTML = resSplit[1];
        $1("zeroheadid").style.display = "block";
        $(".srmore").slideUp("fast");
        $1("updatefltr").style.display = "none";
        $1("bttnId").style.display = "block";
        $(".blu_btn").show();
        $1("ajax_sr_loading").style.display = "none";
        $1("ajax_sr_light").style.display = "none"
    } else {
        tier2flag = "N";
        if ($1("sr_result_id") != null) {
            $1("sr_result_id").style.display = "none";
            $1("noresId").style.display = "none"
        }
        if (refineRsltFlag == "Y") {
            $("#refineRst").html(response);
            if ($1("hidcollegeId") == null) {
                $1("updatefltr").style.display = "none"
            }
        } else {
            $1("intSrRst").innerHTML = response
        	//$("#intSrRst").replaceWith(response);
        }
    }
    if (dispFlag == "Y") {
        $1("srchid").style.display = "none";
        $1("editid").style.display = "block"
    }
    if ($1("hidcollegeId") == null) {
//        if ($1("updatefltr").style.display == "block") {
//            $1("updatefltr").style.display = "none"
//        }
    }
    if ($1("ajax_sr_loading_id").style.display == "block") {
        $1("ajax_sr_loading_id").style.display = "none";
        $1("ajax_sr_light_id").style.display = "none"
    } else {
        $1("ajax_sr_loading").style.display = "none";
        $1("ajax_sr_light").style.display = "none"
    }
    var skypescrapperres = parseInt(screen.width, 10);
    if (skypescrapperres == 1280) {
        $1("srch").className = "wrapper scrn";
        $1("srchsec").className = "wrapper scrn";
        $1("middle_cnt").className = "wrapper scrn";
        $1("sr_result").className = "wrapper scrn";
        if ($1("sr_result_id") != null) {
            $1("sr_result_id").className = "wrapper scrn"
        }
    }
    window.scrollTo(0, 0);
    if (refineRsltFlag == "N") {
        if ($1("sliderId") != null && noresultflag == "N") {
            eval($1("sliderId").innerHTML)
        }
        if ($1("scriptid") != null && noresultflag == "N") {
            eval($1("scriptid").innerHTML)
        }
    }
    if ($1("lazyloadjs") != null) {
        eval($1("lazyloadjs").innerHTML)
    }
    gaTrackingCode();
    replaceSkyBanner();
    if ($1("galoggingsearch") != null) {
        eval($1("galoggingsearch").innerHTML)
    }
    if ($1("gamscript1") != null) {
        eval($1("gamscript1").innerHTML)
    }
    if ($1("gamscript2") != null) {
        eval($1("gamscript2").innerHTML)
    }
    if ($1("gaId") != null) {
        eval($1("gaId").innerHTML)
    }
    if ($1("sortbyscript") != null) {
        eval($1("sortbyscript").innerHTML)
    }
}
function gaTrackingCode() {
    if ($1("gaPageNameId") != null) {
        var e = $1("gaPageNameId").value;
        _gaq.push(["_setCustomVar", 1, "PageName", e, 3])
    }
    if ($1("gacatNameId") != null) {
        var t = $1("gacatNameId").value;
        _gaq.push(["_setCustomVar", 2, "Subject", t, 3])
    }
    if ($1("gaStudylevelId") != null) {
        var n = $1("gaStudylevelId").value;
        _gaq.push(["_setCustomVar", 4, "Qualification", n, 3])
    }
    if ($1("gaCountryNameId") != null) {
        var r = $1("gaCountryNameId").value;
        _gaq.push(["_setCustomVar", 5, "Location", r, 3])
    }
    if ($1("hidkeywordid") != null) {
        var i = $1("hidkeywordid").value;
        var r = $1("gaCountryNameId") != null ? $1("gaCountryNameId").value: "";
        _gaq.push(["_trackEvent", "Search", i, r, 1, true])
    }
    _gaq.push(["_trackPageview"]); (function() {
        var e = document.createElement("script");
        e.type = "text/javascript";
        e.async = true;
        e.src = ("https:" == document.location.protocol ? "https://ssl": "http://www") + ".google-analytics.com/ga.js";
        var t = document.getElementsByTagName("script")[0];
        t.parentNode.insertBefore(e, t)
    })()
}
function getLookUpData(e, t, n, r) {
    if ($1("grdScrErrmsg") != null) {
        $1("grdScrErrmsg").style.display = "none"
    }
    if (t != "-1") {
        var i = n + "_icon";
        if (e == "NAT_QUALS" && t.indexOf(":") != -1) {
            var s = contextPath + "/ajax/lookupAjax.html?lookup=" + e;
            var o;
            if (e == "NAT_QUALS" && r == "Y") {
                o = formRefineUrl("Lookup_Search", "ajax")
            } else {
                o = formRefineUrl("search", "ajax")
            }
            if (e == "LOCATION") {
                o = o + "&selcCntry=" + t;
                if ($1("regionIds") != null) {
                    o = o + "&regnids=" + $1("regionIds").value
                }
            }
            if ($1(i) != null) {
                $1(i).style.display = "block"
            }
            if ($1("regionIds") != null) {
                if (o.indexOf("regionIds") == -1) {
                    o = o + "&regionIds=" + $1("regionIds").value
                }
            }
            var u = new sack;
            u.requestFile = s + o;
            u.onCompletion = function() {
                loadLookUpResult(u.response, n, e, i, r)
            };
            u.runAJAX()
        } else if (e == "NAT_QUALS" && t.indexOf(":") == -1) {} else if (e == "STLEVEL" && (t == "16" || t == "15" || t == "18" || t == "17" || t == "19")) {
            $1("locationList_sr7").innerHTML = $1("subqualtxt").value + '<span class="arw"></span>';
            $("#childSlevelId").html('<option value="-1">' + $1("subqualtxt").value + "</option>");
            $1("List_sr5").className = "selBx disable";
            $1("childSlevelId").className = "select disable";
            $1("childSlevelId").style.display = "none"
        } else if (e == "LOCATION" && (t == "168" || t == "189")) {
            if ($1("countyIds") != null && n == "countyIds") {
                $1("List_sr7").className = "selBx disable";
                $1("countyIds").className = "select disable";
                $1("countyIds").style.display = "none";
                $1("locationList_sr11").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#countyIds").html('<option value="-1">' + $1("regiontxt").value + "</option>")
            }
            if ($1("countyIds1") != null && n == "countyIds1") {
                $1("List_sr8").className = "selBx disable";
                $1("countyIds1").className = "select disable";
                $1("countyIds1").style.display = "none";
                $1("locationList_sr13").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#countyIds1").html('<option value="-1">' + $1("regiontxt").value + "</option>")
            }
            if ($1("countyIds2") != null && n == "countyIds2") {
                $1("List_sr9").className = "selBx disable";
                $1("countyIds2").className = "select disable";
                $1("countyIds2").style.display = "none";
                $1("locationList_sr15").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#countyIds2").html('<option value="-1">' + $1("regiontxt").value + "</option>")
            }
        } else {
            if ($1(i) != null) {
                $1(i).style.display = "block"
            }
            var s = contextPath + "/ajax/lookupAjax.html?lookup=" + e;
            var a = "N";
            var o;
            if (r == "selcData") {
                o = formRefineUrl("Lookup_Search", "ajax")
            } else if (e == "NAT_GRADES" && r == "Y") {
                o = formRefineUrl("Lookup_Search", "ajax")
            } else if (e == "NAT_GRADE_OPTIONS" && r == "true") {
                o = formRefineUrl("Lookup_Search", "ajax")
            } else {
                o = formRefineUrl("search", "ajax")
            }
            if (o == "Duplicate_Regions") {
                a = "Y";
                var f = n + "_icon";
                if ($1(f) != null) {
                    $1(f).style.display = "none"
                }
            }
            if (e == "LOCATION" && a == "N") {
                o = o + "&selcCntry=" + t + "&regCnt=" + regCnt;
                if ($1("regionIds") != null) {
                    if (r == "cntryflag") {} else {
                        if ($1("filterCntrds") != null) {
                            if (o.indexOf("regionIds") == -1) {
                                o = o + "&regnids=" + $1("regionIds").value + "&filterdIds=" + $1("filterCntrds").value + "&regionIds=" + $1("regionIds").value;
                            } else {
                                o = o + "&regnids=" + $1("regionIds").value + "&filterdIds=" + $1("filterCntrds").value
                            }
                        } else {
                            o = o + "&regnids=" + $1("regionIds").value
                        }
                    }
                }
            } else {
                if ($1("regionIds") != null) {
                    if (o.indexOf("regionIds") == -1) {
                        o = o + "&regionIds=" + $1("regionIds").value
                    }
                }
            }
            if ($1("tier2Flag") != null) {
                if ($1("tier2Flag").value == "Y") {
                    o = o + "&tier2Flag=T2"
                }
            }
            if (a == "Y") {
                alert("You�ve already selected the country/region. Choose another country/region")
            } else {
                var u = new sack;
                u.requestFile = s + o;
                u.onCompletion = function() {
                    loadLookUpResult(u.response, n, e, i, r)
                };
                u.runAJAX()
            }
        }
    } else {
        if (e == "NAT_QUALS") {
            $1("locationList_sr3").innerHTML = $1("crntQul").value + '<span class="arw"></span>';
            $("#" + n).html('<option value="-1">' + $1("crntQul").value + "</option>");
            $1("List_sr3").className = "selBx disable";
            $1(n).className = "select disable";
            $1(n).style.display = "none";
            $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>';
            $("#userGradeId").html('<option value="-1">' + $1("grdText").value + "</option>");
            $1("List_sr4").className = "selBx disable";
            $1("userGradeId").className = "select disable";
            $1("userGradeId").style.display = "none";
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block";
            $1("gradeScore").className = "c_txt ml0 disable";
            $1("gradeScore").disabled = true;
            $1("gradeScore").value = $1("deftext").value
        }
        if (e == "NAT_GRADES") {
            $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>';
            $("#" + n).html('<option value="-1">' + $1("grdText").value + "</option>");
            $1("List_sr4").className = "selBx disable";
            $1(n).className = "select disable";
            $1(n).style.display = "none";
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block";
            $1("gradeScore").className = "c_txt ml0 disable";
            $1("gradeScore").disabled = true;
            $1("gradeScore").value = $1("deftext").value
        }
        if (e == "NAT_GRADE_OPTIONS") {
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block"
        }
        if (e == "LOCATION") {
            if (n == "countyIds") {
                $1("locationList_sr11").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#" + n).html('<option value="-1">' + $1("regiontxt").value + "</option>");
                $1("List_sr7").className = "selBx disable";
                $1(n).className = "select disable";
                $1(n).style.display = "none"
            }
            if (n == "countyIds1") {
                $1("locationList_sr13").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#" + n).html('<option value="-1">' + $1("regiontxt").value + "</option>");
                $1("List_sr8").className = "selBx disable";
                $1(n).className = "select disable";
                $1(n).style.display = "none"
            }
            if (n == "countyIds2") {
                $1("locationList_sr15").innerHTML = $1("regiontxt").value + '<span class="arw"></span>';
                $("#" + n).html('<option value="-1">' + $1("regiontxt").value + "</option>");
                $1("List_sr9").className = "selBx disable";
                $1(n).className = "select disable";
                $1(n).style.display = "none"
            }
        }
        if (e == "STLEVEL") {
            $1("locationList_sr7").innerHTML = $1("subqualtxt").value + '<span class="arw"></span>';
            $("#" + n).html('<option value="-1">' + $1("subqualtxt").value + "</option>");
            $1("List_sr5").className = "selBx disable";
            $1(n).className = "select disable";
            $1(n).style.display = "none"
        }
        if (e == "EXAM") {
            $1("locationList_sr17").innerHTML = $1("engScrTxt").value + '<span class="arw"></span>';
            $("#" + n).html('<option value="-1">' + $1("engScrTxt").value + "</option>");
            $1("List_sr6").className = "selBx disable";
            $1(n).className = "select disable";
            $1(n).style.display = "none"
        }
    }
}
function loadLookUpResult(e, t, n, r, i) {
    $("#" + t).html(e);
    if ($1(r) != null) {
        $1(r).style.display = "none"
    }
    if (n == "NAT_QUALS") {
        if ($1("hidmyqualid") != null && i == "Y") {
            var s = "myqul-" + $1("hidmyqualid").value;
            if ($1(s) != null) {
                $1(s).selected = "selected";
                $1("locationList_sr3").innerHTML = $1(s).innerHTML + '<span class="arw"></span>'
            }
            var o = $1("hidmyqualid").value;
            $1("List_sr4").className = "selBx";
            $1("userGradeId").style.display = "block";
            if ($1("hiduserGradeid") == null) {
                getLookUpData("NAT_GRADES", o, "userGradeId");
                selectedcounty()
            } else {
                getLookUpData("NAT_GRADES", o, "userGradeId", "Y")
            }
        }
    }
    if (n == "STLEVEL") {
        if ($1("hidsubQualid") != null) {
            var s = "subqul-" + $1("hidsubQualid").value;
            if ($1("hidparentslevelId").value == $1("parentSlevelId").value) {
                $1(s).selected = "selected";
                $1("locationList_sr7").innerHTML = $1(s).innerHTML + '<span class="arw"></span>'
            } else {
                $1("locationList_sr7").innerHTML = $1("subqualtxt").value + '<span class="arw"></span>'
            }
        } else {
            $1("locationList_sr7").innerHTML = $1("subqualtxt").value + '<span class="arw"></span>'
        }
    }
    if (n == "EXAM") {
        if ($1("hidengscord") != null) {
            var s = "scr-" + $1("hidengscord").value;
            if ($1(s) != null) {
                $1(s).selected = "selected";
                $1("locationList_sr17").innerHTML = $1(s).innerHTML + '<span class="arw"></span>'
            } else {
                $1("locationList_sr17").innerHTML = $1("engScrTxt").value + '<span class="arw"></span>';
                if ($1("no-exam") != null) {
                    $1("List_sr6").className = "selBx disable";
                    $1(t).className = "select disable";
                    $1(t).style.display = "none"
                }
            }
        } else {
            $1("locationList_sr17").innerHTML = $1("engScrTxt").value + '<span class="arw"></span>';
            $1("englishScoreId").style.display = "block";
            if ($1("no-exam") != null) {
                $1("List_sr6").className = "selBx disable";
                $1(t).className = "select disable";
                $1(t).style.display = "none"
            }
        }
    }
    if (n == "NAT_GRADES") {
        if ($1("hiduserGradeid") != null && i == "Y") {
            var s = "grd-" + $1("hiduserGradeid").value;
            $1("userGradeId").className = "select";
            if ($1(s) != null) {
                $1(s).selected = "selected";
                $1("locationList_sr4").innerHTML = $1(s).innerHTML + '<span class="arw"></span>'
            } else if ($1("grdid") != null) {
                $1("locationList_sr4").innerHTML = $1("grdid").innerHTML + '<span class="arw"></span>'
            } else {
                $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>'
            }
            getLookUpData("NAT_GRADE_OPTIONS", $1("hiduserGradeid").value, "grd_drpdwn", "true")
        } else {
            $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>';
            if ($1("grdid") != null) {
                $("#locationList_sr4").html($("#grdid").html() + '<span class="arw"></span>');
                $1("List_sr4").className = "selBx";
                $1("userGradeId").className = "select";
                $1("userGradeId").style.display = "block";
                $1("gradeScore").className = "c_txt ml0";
                $1("gradeScore").disabled = false;
                $1("gradeScore").value = $1("deftext").value
            } else if (e.indexOf("grd-") != -1) {
                if ($1("grdmsgid") != null) {
                    if ($1("List_sr4").className.indexOf("err") == -1) {
                        $1("List_sr4").className = $1("List_sr4").className + " err"
                    }
                    if ($1("gradeScore").className.indexOf("err") == -1) {
                        $1("gradeScore").className = $1("gradeScore").className + " err"
                    }
                } else {
                    $1("List_sr4").className = "selBx"
                }
                $1("userGradeId").className = "select";
                $1("userGradeId").style.display = "block"
            } else {
                $1("List_sr4").className = "selBx disable";
                $1("userGradeId").className = "select disable";
                $1("userGradeId").style.display = "none";
                $1("grd_scr").style.display = "block";
                $1("grd_drpdwn").style.display = "none";
                $1("gradeScore").className = "c_txt ml0 disable";
                $1("gradeScore").disabled = true;
                $1("gradeScore").value = $1("deftext").value
            }
        }
    }
    if (n == "NAT_GRADE_OPTIONS") {
        $1("grd_scr").style.display = "none";
        $1("grd_drpdwn").style.display = "block";
        if ($1("grdScrId") != null && i == "true") {
            var u = "grdScr-" + $1("grdScrId").value;
            if ($1(u) != null) {
                $1("locationList_sr18").innerHTML = $1(u).innerHTML + '<span class="arw"></span>';
                $1(u).selected = "selected"
            } else {
                $1("gradeScoreid").value = $1("grdScrId").value;
                $1("gradeScoreid").className = "c_txt ml0"
            }
            selectedcounty()
        }
    }
    if (n == "LOCATION") {
        if ($1("countyIds") != null && t == "countyIds") {
            $1("List_sr7").className = "selBx";
            $1("countyIds").className = "select";
            $1("countyIds").style.display = "block";
            var a = $1("countyIds");
            if (a.options[a.selectedIndex] != null && a.options[a.selectedIndex].value != "-1") {
                $1("locationList_sr11").innerHTML = a.options[a.selectedIndex].text + '<span class="arw"></span>'
            } else {
                $1("locationList_sr11").innerHTML = $1("regiontxt").value + '<span class="arw"></span>'
            }
        }
        if ($1("countyIds1") != null && t == "countyIds1") {
            $1("List_sr8").className = "selBx";
            $1("countyIds1").className = "select";
            $1("countyIds1").style.display = "block";
            var a = $1("countyIds1");
            if (a.options[a.selectedIndex] != null && a.options[a.selectedIndex].value != "-1") {
                $1("locationList_sr13").innerHTML = a.options[a.selectedIndex].text + '<span class="arw"></span>'
            } else {
                $1("locationList_sr13").innerHTML = $1("regiontxt").value + '<span class="arw"></span>'
            }
        }
        if ($1("countyIds2") != null && t == "countyIds2") {
            $1("List_sr9").className = "selBx";
            $1("countyIds2").className = "select";
            $1("countyIds2").style.display = "block";
            var a = $1("countyIds2");
            if (a.options[a.selectedIndex] != null && a.options[a.selectedIndex].value != "-1") {
                $1("locationList_sr15").innerHTML = a.options[a.selectedIndex].text + '<span class="arw"></span>'
            } else {
                $1("locationList_sr15").innerHTML = $1("regiontxt").value + '<span class="arw"></span>'
            }
        }
    }
}
function loadSlider() {
    var e = $1("tutionId").value.split(",");
    var t = $1("hidafflid").value;
    var n = new Array;
    for (var r = 0; r < e.length; r++) {
        n[r] = e[r]
    }
    var i = 0;
    var s = n.length - 1;
    var o = false;
    if (t == "90409") {
        o = true;
        n = n.reverse();
        if ($1("upTutFees") != null) {
            s = s - n.indexOf($1("upTutFees").value)
        }
    } else {
        n = n;
        if ($1("upTutFees") != null) {
            s = n.indexOf($1("upTutFees").value)
        }
    }
    $("#slider").slider({
        isRTL: o,
        range: "min",
        animate: true,
        min: 0,
        max: n.length - 1,
        value: s,
        slide: function(e, r) {
            if (t == "90409") {
                var i = n.length - 1;
                $("#max").val(n[i - r.value])
            } else {
                $("#max").val(n[r.value])
            }
        }
    });
    if ($1("upTutFees") != null) {
        $("#max").val($1("upTutFees").value)
    } else {
        $("#max").val(e[e.length - 1])
    }
    if ($(window).width() >= 992) {
        loadSliderValue()
    }
}
function appendSelectRow(e, t, n) {
    var r = $1("rowid").value;
    var i = "cntryIds" + r;
    var s = "countyIds" + r;
    var o = "countyIds_icon";
    var u = "List_sr" + (parseInt(r) + 7);
    var a = "locationList_sr12";
    var f = "locationList_sr13";
    var l = "setValue_AMW(this,'locationList_sr12')";
    var c = "setValue_AMW(this,'locationList_sr13')";
    var h = "getLookUpData('LOCATION',this.value,'" + s + "','cntryflag')";
    var p = "showDropDown('" + u + "','" + s + "')";
    var d = 'id="List_sr' + (parseInt(r) + 7) + '"';
    var v = 'id="' + s + '"';
    var m = s + "_icon";
    $1("rowid").value = parseInt(r) + 1;
    if (r == 2) {
        $1("addId").style.display = "none";
        a = "locationList_sr14";
        f = "locationList_sr15";
        l = "setValue_AMW(this,'locationList_sr14')";
        c = "setValue_AMW(this,'locationList_sr15')"
    }
    var g = "setValue_AMW(this,'locationList_sr10')";
    var y = "setValue_AMW(this,'locationList_sr11')";
    var b = "getLookUpData('LOCATION',this.value,'countyIds','cntryflag')";
    var w = "showDropDown('List_sr7','countyIds')";
    var E = 'id="List_sr7"';
    var S = 'id="countyIds"';
    var x = 'selected="selected"';
    var T = $1(t).innerHTML.replace("cntryIds", i);
    T = T.replace(S, v);
    T = T.replace(E, d);
    T = T.replace("locationList_sr10", a).replace("locationList_sr11", f);
    T = T.replace(g, l).replace(y, c);
    T = T.replace(b, h);
    T = T.replace(w, p);
    T = T.replace(o, m);
    T = T.replace(x, "");
    var N = "row" + r;
    var C = "removeRow('" + N + "')";
    var k = $1("cntryIds1") != null ? $1("cntryIds1").value: null;
    var L = $1("countyIds1") != null ? $1("countyIds1").value: null;
    var A = $1(e).innerHTML + '<div class="frm_sub" id="' + N + '"><label class="lbltxt">' + $1("desId").value + '</label><fieldset class="fd_st">' + T + '</fieldset><span class="hldr add"><a href="javascript:void(0);" onclick="' + C + '">X ' + $1("removeId").value + "</a></span></div>";
    $1(e).innerHTML = A;
    $1(a).innerHTML = $1("msg1").value;
    $1(f).innerHTML = $1("msg2").value;
    $1(s).innerHTML = '<option value="-1">' + $1("msg2").value; + "</option>";
    if (k != null) {
        $1("cntryIds1").value = k
    }
    if (L != null) {
        $1("countyIds1").value = L
    }
    if (n != undefined) {
        $1(i).value = n
    }
    $1(s).style = "display:none";
    $1(s).className = "select disable";
    $1(u).className = "selBx disable"
}
function removeRow(e) {
    var t = $1("rowid").value;
    e = "#" + e;
    $(e).remove();
    $1("rowid").value = parseInt(t) - 1;
    if (t == 2) {
        $1("addId").style.display = "block"
    } else if (t == 3) {
        $1("addId").style.display = "block"
    }
}
function showDropDown(e, t) {
    if (t == "childSlevelId") {
        if ($1("parentSlevelId").value == "2" || $1("parentSlevelId").value == "3") {
            $1("engsdrpid").className = "selBx";
            $1("englishId").style.display = "block"
        } else {
            $1("engsdrpid").className = "selBx disable";
            $1("locationList_sr16").innerHTML = $1("engsdeftxtid").value;
            $1("englishId").value = "-1";
            $1("englishId").style.display = "none"
        }
        $1("List_sr6").className = "selBx disable";
        $1("locationList_sr17").innerHTML = $1("engScrTxt").value;
        $1("englishScoreId").value = "-1";
        $1("englishScoreId").style.display = "none"
    }
    if ($1("rowid").value == "1" && t == "countyIds") {
        if ($1("englishId").className == "select") {
            if ($1("cntryIds").value == "210") {
                $1("locationList_sr16").innerHTML = "IELTS";
                $1("englishId").value = "IELTS";
                $("#toeflid").html("TOEFL (currently not accepted in the UK)");
                $1("toeflid").disabled = true;
                getLookUpData("EXAM", "IELTS", "englishScoreId");
                $1("List_sr6").className = "selBx";
                $1("englishScoreId").style.display = "block";
                $1("englishScoreId").className = "select"
            } else {
                $1("englishId").value = "-1";
                $1("locationList_sr16").innerHTML = $1("engsdeftxtid").value;
                $("#toeflid").html("TOEFL IBT");
                $1("toeflid").disabled = false;
                $1("List_sr6").className = "selBx disable";
                $1("locationList_sr17").innerHTML = $1("engScrTxt").value;
                $1("englishScoreId").value = "-1";
                $1("englishScoreId").style.display = "none";
                $1("englishScoreId").className = "select disable"
            }
        }
    }
    if ($1("grdScrErrmsg") != null) {
        $1("grdScrErrmsg").style.display = "none"
    }
    if (t == "userQualId") {
        if ($1("nationcntryid").value.indexOf(":") != -1) {
            if ($1(e).className == "selBx disable") {
                $1(e).className = "selBx"
            }
            if ($1(t).className == "select disable") {
                $1(t).className = "select";
                $1(t).style.display = "block"
            }
            $1("locationList_sr3").innerHTML = $1("crntQul").value + '<span class="arw"></span>';
            $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>';
            $("#userGradeId").html('<option value="-1">' + $1("grdText").value + "</option>");
            $1("List_sr4").className = "selBx disable";
            $1("userGradeId").className = "select disable";
            $1("userGradeId").style.display = "none";
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block";
            $1("gradeScore").className = "c_txt ml0 disable";
            $1("gradeScore").disabled = true;
            $1("gradeScore").value = $1("deftext").value
        } else {
            $1("locationList_sr3").innerHTML = $1("crntQul").value + '<span class="arw"></span>';
            $("#" + t).html('<option value="-1">' + $1("crntQul").value + "</option>");
            $1(e).className = "selBx disable";
            $1(t).className = "select disable";
            $1(t).style.display = "none";
            $1("locationList_sr4").innerHTML = $1("grdText").value + '<span class="arw"></span>';
            $("#userGradeId").html('<option value="-1">' + $1("grdText").value + "</option>");
            $1("List_sr4").className = "selBx disable";
            $1("userGradeId").className = "select disable";
            $1("userGradeId").style.display = "none";
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block";
            $1("gradeScore").className = "c_txt ml0 disable";
            $1("gradeScore").disabled = true;
            $1("gradeScore").value = $1("deftext").value
        }
    } else if (e == "textbox") {
        $1("List_sr4").className = "selBx";
        if ($1("userGradeId").value != "-1") {
            if ($1(t).className == "c_txt ml0 disable" || $1(t).className == "c_txt ml0 disable err") {
                $1(t).className = "c_txt ml0";
                $1(t).disabled = false
            }
        } else {
            $1("gradeScore").value = $1("deftext").value;
            $1(t).className = "c_txt ml0 disable";
            $1(t).disabled = true
        }
        if ($1("tier2Flag") != null) {
            $1("tier2Flag").value = "N"
        }
    } else if (t == "nationcntryid") {
        if ($1("tier2Flag") != null) {
            $1("tier2Flag").value = "N"
        }
        if ($1("nationId").value == "-1") {
            var n = "cnt" + $1("nationId").value;
            $1(n).selected = "selected";
            $1(e).className = "selBx disable";
            $1(t).className = "select disable";
            $1(t).style.display = "none";
            $1("locationList_sr2").innerHTML = $1(n).innerHTML + '<span class="arw"></span>';
            if ($1("userQualId").style.display = "block") {
                $1("List_sr3").className = "selBx disable";
                $1("userQualId").className = "select disable";
                $1("userQualId").style.display = "none";
                $("#locationList_sr3").html($1("crntQul").value + '<span class="arw"></span>');
                $("#userQualId").html('<option value="-1">' + $1("crntQul").value + "</option>")
            }
            if ($1("userGradeId").className == "select") {
                $1("List_sr4").className = "selBx disable";
                $1("userGradeId").className = "select disable";
                $1("userGradeId").style.display = "none";
                $("#locationList_sr4").html($1("grdText").value + '<span class="arw"></span>');
                $("#userGradeId").html('<option value="-1">' + $1("grdText").value + "</option>");
                $1("grd_drpdwn").style.display = "none";
                $1("grd_scr").style.display = "block";
                $1("gradeScore").value = $1("deftext").value;
                $1("gradeScore").className = "c_txt ml0 disable";
                $1("gradeScore").disabled = true
            }
        } else {
            var n = "cnt" + $1("nationId").value;
            $1(n).selected = "selected";
            $1("locationList_sr2").innerHTML = $1(n).innerHTML + '<span class="arw"></span>';
            $1(e).className = "selBx";
            $1(t).className = "select";
            $1(t).style.display = "block";
            if ($1("userGradeId").className == "select") {
                $1("List_sr4").className = "selBx disable";
                $1("userGradeId").className = "select disable";
                $1("userGradeId").style.display = "none";
                $("#locationList_sr4").html($1("grdText").value + '<span class="arw"></span>');
                $("#userGradeId").html('<option value="-1">' + $1("grdText").value + "</option>");
                $1("grd_drpdwn").style.display = "none";
                $1("grd_scr").style.display = "block";
                $1("gradeScore").value = $1("deftext").value;
                $1("gradeScore").className = "c_txt ml0 disable";
                $1("gradeScore").disabled = true
            }
            if ($1("nationcntryid").value.indexOf(":") != -1) {
                $("#locationList_sr3").html($1("crntQul").value + '<span class="arw"></span>');
                getLookUpData("NAT_QUALS", $1("nationcntryid").value, "userQualId");
                $1("List_sr3").className = "selBx";
                $1("userQualId").className = "select";
                $1("userQualId").style.display = "block"
            } else {
                $("#locationList_sr3").html($1("crntQul").value + '<span class="arw"></span>');
                $("#userQualId").html('<option value="-1">' + $1("crntQul").value + "</option>");
                $1("List_sr3").className = "selBx disable";
                $1("userQualId").className = "select disable";
                $1("userQualId").style.display = "none"
            }
        }
    } else {
        if (t == "userGradeId") {
            if ($1("tier2Flag") != null) {
                $1("tier2Flag").value = "N"
            }
            $1("List_sr3").className = "selBx";
            $1("grd_drpdwn").style.display = "none";
            $1("grd_scr").style.display = "block";
            $1("gradeScore").value = $1("deftext").value;
            $1("gradeScore").className = "c_txt ml0 disable";
            $1("gradeScore").disabled = true
        } else {
            if ($1(e).className == "selBx disable") {
                $1(e).className = "selBx"
            }
            if ($1(t).className == "select disable") {
                $1(t).className = "select";
                $1(t).style.display = "block"
            }
        }
    }
}
function selectedUrlValues(e) {
    if (e.indexOf("parentQualId") == -1) {
        if ($1("parentSlevelId") != null && $1("parentSlevelId").value != "-1") {
            e = e + "&parentQualId=" + $1("parentSlevelId").value
        }
    }
    if (e.indexOf("subQualId") == -1) {
        if ($1("childSlevelId") != null && $1("childSlevelId").value != "-1") {
            e = e + "&subQualId=" + $1("childSlevelId").value
        }
    }
    if (e.indexOf("catCode") == -1) {
        if ($1("subjectId") != null && $1("subjectId").value != "-1") {
            e = e + "&catCode=" + $1("subjectId").value
        }
    }
    if (e.indexOf("smode") == -1) {
        if ($1("smode") != null && $1("smode").value != "-1") {
            e = e + "&smode=" + $1("smode").value
        }
    }
    if (e.indexOf("applyNow") == -1) {
        if (document.location.href.indexOf("applynow=Y") != -1) {
            e = e + "&applyNow=Y"
        }
    }
    e = getContryIds(e);
    return e
}
function reLoadHashValues(type) {
    var e = contextPath + "/searchajax.html?search=coursesearch";
    if(type)
    	e = type + "?search=universitysearch";
    if (window.location.hash) {
        if (window.location.hash.indexOf("#search") != -1) {
            isloader = true;
            if (window.location.hash.indexOf("&") != -1) {
                var t = window.location.hash.replace(/^#/, "").split("&"),
                n = {};
                for (var r = 0; r < t.length; r++) {
                    if (r != 0) {
                        var i = t[r].split("=");
                        if (i[0] != "restRefineFlag") {
                            if (i[0] != "clearAll") {
                                if (i[0] == "gradeScore") {
                                    e = e + "&" + i[0] + "=" + encodeURIComponent(i[1])
                                } else {
                                    e = e + "&" + i[0] + "=" + i[1]
                                }
                            }
                        }
                    }
                }
            }
        }
        $1("ajax_sr_loading").style.display = "block";
        $1("ajax_sr_light").style.display = "block";
        var s = selectedUrlValues(e);
        var o = new sack;
        o.requestFile = s;
        o.onCompletion = function() {
            loadAjaxSearchResult(o.response, "N", "N")
        };
        o.runAJAX()
    }
}
function reloadOnHashChange() {
    var e = contextPath + "/searchajax.html?search=coursesearch";
    if (window.location.hash) {
        if (window.location.hash.indexOf("#search") != -1) {
            var t = window.location.hash.split("#search");
            e = e + t[1]
        }
        var n = new sack;
        n.requestFile = e.replace("&restRefineFlag=Y", "");
        n.onCompletion = function() {
            loadAjaxSearchResult(n.response, "N", "N")
        };
        n.runAJAX()
    }
}
function errorMsg() {
    if (tier2flag == "Y") {
        $1("List_sr3").className = "selBx";
        $1("List_sr4").className = "selBx";
        $1("gradeScore").className = "c_txt ml0"
    } else {
        if ($1("qulmsgid") != null) {
            if ($1("List_sr3").className.indexOf("err") == -1) {
                $1("List_sr3").className = $1("List_sr3").className + " err"
            }
            if ($1("List_sr4").className.indexOf("err") == -1) {
                $1("List_sr4").className = $1("List_sr4").className + " err"
            }
            if ($1("gradeScore").className.indexOf("err") == -1) {
                $1("gradeScore").className = $1("gradeScore").className + " err"
            }
        }
    }
}
function selectedcounty() {
    regCnt = 0;
    if ($1("hidparentslevelId") != null) {
        var e = $1("hidparentslevelId").value;
        $1("List_sr5").className = "selBx";
        $1("childSlevelId").style.display = "block";
        getLookUpData("STLEVEL", e, "childSlevelId", "selcData")
    }
    if ($1("hidenglishType") != null || $1("englishId").value != "-1") {
        var t;
        if ($1("hidenglishType")) {
            t = $1("hidenglishType").value
        } else {
            t = $1("englishId").value
        }
        $1("List_sr6").className = "selBx";
        $1("englishScoreId").style.display = "block";
        getLookUpData("EXAM", t, "englishScoreId", "selcData")
    }
    if ($1("hidcountryIds") != null) {
        var n = $1("hidcountryIds").value;
        if (n.indexOf(",") != -1) {
            var r = n.split(",");
            $1("rowid").value = "1";
            if (r.length == 3) {
                $1("addId").style.display = "none"
            }
            for (var i = 0; i < r.length - 1; i++) {
                appendSelectRow("extraRow", "filedId", r[i + 1])
            }
            for (var i = 0; i < r.length; i++) {
                var s = r[i];
                var o = "countyIds" + i;
                if (i == 0) {
                    o = "countyIds"
                }
                getLookUpData("LOCATION", s, o);
                regCnt++
            }
        } else {
            getLookUpData("LOCATION", n, "countyIds")
        }
    }
    if ($1("bulb") != null) {
        var u = $1("nationcntryid").value;
        if (u != "-1") {
            if (u.indexOf(":Y") == -1) {
                $1("alertTextId").style.display = "none"
            } else {
                errorMsg()
            }
        }
    }
    var a = ["nationId", "nationcntryid", "userQualId", "userGradeId", "subjectId", "parentSlevelId", "childSlevelId", "smode", "sduration", "cntryIds", "countyIds", "cntryIds1", "countyIds1", "cntryIds2", "countyIds2", "englishId", "englishScoreId"];
    for (var i = 0; i < a.length; i++) {
        var f = document.getElementById(a[i]);
        if (f != null) {
            if (f.options[f.selectedIndex] != null && f.options[f.selectedIndex].value != "-1") {
                document.getElementById("locationList_sr" + (i + 1)).innerHTML = f.options[f.selectedIndex].text + '<span class="arw"></span>'
            } else if ($1("hidkeywordid") != null && a[i] == "subjectId") {
                if (f.options[f.selectedIndex] != null && f.options[f.selectedIndex].value == "-1") {
                    document.getElementById("locationList_sr" + (i + 1)).innerHTML = f.options[f.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function inlineScript() {
    if ($1("hidnationCntryCode") != null) {
        if ($1("nationcntryid").value.indexOf(":") != -1) {
            $1("List_sr2").className = "selBx";
            $1("nationcntryid").style.display = "block";
            $1("List_sr3").className = "selBx";
            $1("userQualId").style.display = "block";
            getLookUpData("NAT_QUALS", $1("nationcntryid").value, "userQualId", "Y")
        } else {
            $1("List_sr2").className = "selBx";
            $1("nationcntryid").style.display = "block"
        }
    }
//    if ($1("hidmyqualid") == null && $1("hiduserGradeid") == null) {
//        selectedcounty()
//    }
    if ($(window).width() < 768) {
        $(".srmore").slideUp("fast")
    }
    if (document.location.href.indexOf("#search") == -1) {
        if ($1("bulb")) {
            $1("bulb").style.display = "none"
        }
    }
}
function setTierValue() {
    if ($1("tier2Flag") != null) {
        $1("tier2Flag").value = "N"
    }
}
function formthePaginationUrl() {
    var e = "";
    if ($1("parentSlevelId") != null && $1("parentSlevelId").value != "-1") {
        e = e + "&parentQualId=" + $1("parentSlevelId").value
    }
    if ($1("childSlevelId") != null && $1("childSlevelId").value != "-1") {
        e = e + "&subQualId=" + $1("childSlevelId").value
    }
    if ($1("subjectId") != null && $1("subjectId").value != "-1") {
        e = e + "&catCode=" + $1("subjectId").value
    }
    if ($1("smode") != null && $1("smode").value != "-1") {
        e = e + "&smode=" + $1("smode").value
    }
    if ($1("hidkeywordid") != null) {
        e = e + "&keyword=" + $1("hidkeywordid").value
    }
    if ($1("sortbyid") != null) {
        e = e + "&orderBy=" + $1("sortbyid").value
    }
    if ($1("scholarSearch") != null) {
        e = e + "&scholarSearch=" + $1("scholarSearch").value
    }
    e = getContryIds(e);
    return e
}
function loadPaginationResult(e, t) {
    allowHashToUpdateApp = false;
    var n = contextPath + "/searchajax.html?search=coursesearch";
    var r = "";
    if (window.location.hash) {
        if (window.location.hash.indexOf("#search") != -1) {
            isloader = true;
            if (window.location.hash.indexOf("&") != -1) {
                var i = window.location.hash.replace(/^#/, "").split("&"),
                s = {};
                for (var o = 0; o < i.length; o++) {
                    if (o != 0) {
                        var u = i[o].split("=");
                        if (u[0] != "pageNo") {
                            if (u[0] != "urlcatId") {
                                if (e == "sortBy") {
                                    if (u[0] != "orderBy") {
                                        r = r + "&" + u[0] + "=" + u[1]
                                    }
                                } else {
                                    r = r + "&" + u[0] + "=" + u[1]
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (r == "" && window.location.href.indexOf("/provider-result.html") != -1) {
        var a = window.location.href.slice(window.location.href.indexOf("?") + 1);
        r = r + "&" + a;
        if (e == "pageNo") {
            r = r + "&pageNo=" + t
        }
        if (e == "sortBy") {
            r = r + "&orderBy=" + t
        }
    } else if (r == "") {
        var f = formthePaginationUrl();
        r = r + f;
        if (e == "pageNo") {
            r = r + "&pageNo=" + t
        }
        if (e == "sortBy") {
            r = r + "&orderBy=" + t
        }
        if ($1("urlcatId") != null) {
            r = r + "&urlcatId=" + $1("urlcatId").value
        }
    } else {
        if (e == "pageNo") {
            r = r + "&pageNo=" + t
        }
        if (e == "sortBy") {
            r = r + "&orderBy=" + t
        }
        if ($1("urlcatId") != null) {
            r = r + "&urlcatId=" + $1("urlcatId").value
        }
    }
    if ($1("provCntryId") != null) {
        r = r + "&provCntryId=" + $1("provCntryId").value
    }
    if (r.indexOf("restRefineFlag") == -1) {
        r = r + "&restRefineFlag=Y"
    }
    if (r.indexOf("boostedafflid") == -1) {
        if ($1("boostedafflid") != null) {
            r = r + "&boostedafflid=" + $("#boostedafflid").val()
        }
    }
    n = n + r;
    window.location.hash = "#search" + r;
    $1("ajax_sr_loading_id").style.display = "block";
    $1("ajax_sr_light_id").style.display = "block";
    var l = new sack;
    l.requestFile = n;
    l.onCompletion = function() {
        loadAjaxSearchResult(l.response, "N", "Y")
    };
    l.runAJAX()
}
function tierResult() {
    allowHashToUpdateApp = false;
    var e = contextPath + "/searchajax.html?search=coursesearch";
    var t = "";
    if (window.location.hash) {
        if (window.location.hash.indexOf("#search") != -1) {
            isloader = true;
            if (window.location.hash.indexOf("&") != -1) {
                var n = window.location.hash.replace(/^#/, "").split("&"),
                r = {};
                for (var i = 0; i < n.length; i++) {
                    if (i != 0) {
                        var s = n[i].split("=");
                        if (s[0] == "gradeScore") {
                            t = t + "&" + s[0] + "=" + encodeURIComponent(s[1])
                        } else {
                            t = t + "&" + s[0] + "=" + s[1]
                        }
                    }
                }
            }
        }
    }
    t = t + "&tier2Flag=T2";
    window.location.hash = "#search" + t;
    $1("ajax_sr_loading").style.display = "block";
    $1("ajax_sr_light").style.display = "block";
    var o = new sack;
    o.requestFile = e + t;
    o.onCompletion = function() {
        loadAjaxSearchResult(o.response, "N")
    };
    o.runAJAX()
}
function changeClassName(e) {
    $1(e).className = "c_txt ml0";
    if ($1("grdScrErrmsg") != null) {
        $1("grdScrErrmsg").style.display = "none"
    }
}
function checkGrdValidation(e) {
    var t = $(this).width();
    if ($1("bulb") != null) {
        $1("bulb").style.display = "none"
    }
    if (t < 768) {
        alert(e)
    } else {
        $1("screrrMsg").innerHTML = e;
        if ($1("grdScrErrmsg") != null) {
            $1("grdScrErrmsg").style.display = "block"
        }
    }
    $1("List_sr3").className = "selBx";
    if ($1("userGradeScrId") != null) {
        $1("List_sr18").className = $1("List_sr18").className + " er_red"
    }
    if ($1("gradeScoreid") != null) {
        $1("gradeScoreid").className = $1("gradeScoreid").className + " er_red"
    }
}
function removeValidation(e) {
    $1(e).className = "selBx";
    if ($1("grdScrErrmsg") != null) {
        $1("grdScrErrmsg").style.display = "none"
    }
}
function trackEventStats(e) {
    if ($1("userGradeId") != null && $1("userGradeId").value != "-1") {
        _gaq.push(["_trackEvent", "Ultimate Search", e, "ultimate search with grade", 1, true])
    } else if ($1("userQualId") != null && $1("userQualId").value != "-1") {
        _gaq.push(["_trackEvent", "Ultimate Search", e, "ultimate search", 1, true])
    } else {
        _gaq.push(["_trackEvent", "Ultimate Search", e, "normal search", 1, true])
    }
}
function replaceSkyBanner() {
    if ($1("srtByid") != null) {
        var e = $("#srtByid").position();
        $1("rbanner").style.top = e.top + "px"
    }
    if ($1("noresId") != null && $1("noresId").style.display == "block") {
        var e = $("#noresId").position();
        $1("rbanner").style.top = e.top + "px"
    }
}
function setBannerPostion() {
    setTimeout("replaceSkyBanner()", 500)
}
function showRefinebutton() {
    if ($("#bttnId")) {
        $("#bttnId").show()
    }
}
function showRefine(e, t, n, r) {
    $("#" + t).hide();
    $("#" + e).show();
    $("#" + n).hide();
    $("#" + r).show();
    $("#mobileid").show();
    $(".srmore").show();
    $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-minus'></em></a>")
}
function lightBoxYoutube() {
    if ($1("youtubeVideo").src != null && $1("youtubeVideo").src != "about:blank") {
        urlofyoutube = $1("youtubeVideo").src
    } else {
        if (urlofyoutube != "") {
            $1("youtubeVideo").src = urlofyoutube
        }
    }
    if ($(window).width() < 768) {
        var e = contextPath + "/jsp/search/searchLightBox.jsp";
        location.href = e
    } else {
        $1("overlayId").style.display = "block";
        $1("shwId").style.display = "block";
        $1("videoclose").style.display = "block"
    }
}
function lightBoxYoutubeClose() {
    $1("youtubeVideo").src = "about:blank";
    $1("overlayId").style.display = "none";
    $1("shwId").style.display = "none";
    $1("videoclose").style.display = "none"
}
function loadSliderValue() {
    var e = $(".slr_lbl label").length - 1;
    var t = $(window).width();
    var n = 980 / e;
    var r = 0;
    var i = 0;
    var s = $1("hidafflid").value;
    $(".slr_lbl label").each(function() {
        if (r == 0) {
            if (s == "90409") {
                $(this).css("right", r + "%")
            } else {
                $(this).css("left", r + "%")
            }
        } else if (i == e) {
            var t = $(this).html();
            t = t.replace(" ", "");
            $(this).html(t);
            var o = 0;
            o = r - 80;
            if (s == "90409") {
                $(this).css("right", o + "px")
            } else {
                $(this).css("left", o + "px")
            }
        } else {
            var o = 0;
            o = r - 40;
            if (s == "90409") {
                $(this).css("right", o + "px")
            } else {
                $(this).css("left", o + "px")
            }
        }
        r = r + n;
        i = i + 1
    })
}
var contextPath = "/study";
var norestierflag = "N";
var allowHashToUpdateApp = true;
var backbuttonflag = false;
var tier2flag = "N";
var regCnt = 0;
var difHashFlag = false;
window.onhashchange = function(e) {
    if (allowHashToUpdateApp) {
        if (document.location.href.indexOf("#search") != -1) {
            difHashFlag = true;
            $1("ajax_sr_loading").style.display = "block";
            $1("ajax_sr_light").style.display = "block";
            reloadOnHashChange()
        } else {
            if (window.location.hash) {
                if (difHashFlag) {
                    location.reload()
                }
            } else {
                if (backbuttonflag) {
                    $1("ajax_sr_loading").style.display = "block";
                    $1("ajax_sr_light").style.display = "block";
                    location.reload()
                }
            }
        }
    } else {
        allowHashToUpdateApp = true
    }
};
var urlofyoutube = "";
$(window).resize(function() {
    if ($(window).width() >= 992) {
        loadSliderValue()
    }
})