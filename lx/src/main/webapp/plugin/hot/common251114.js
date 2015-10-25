function $1(e) {
    return document.getElementById(e)
}
function searchToggleMenu(e, t) {
    if (document.getElementById(e).style.display == "block") {
        document.getElementById(e).style.display = "none";
        document.getElementById(t).className = ""
    } else {
        document.getElementById(e).style.display = "block";
        document.getElementById(t).className = "act"
    }
}
function toggle(e, t) {
    if (screen.width <= 800) {
        if (document.getElementById(e).style.display == "block") {
            document.getElementById(e).style.display = "none";
            document.getElementById(t).className = "bsub"
        } else {
            document.getElementById(e).style.display = "block";
            document.getElementById(t).className = "bsub act"
        }
    }
}
function $$$(e) {
    return document.getElementById(e)
}
function setValue_AMW(e, t) {
    var n = e.getElementsByTagName("option");
    for (var r = 0; r < n.length; r++) {
        if (n[r].selected == true) {
            $$$(t).innerHTML = n[r].childNodes[0].nodeValue
        }
    }
}
function setValue_AMW(e, t) {
    $$$(t).innerHTML = e.options[e.selectedIndex].innerHTML + "<span class='arw'></span>"
}
function setValue_AMWCountry(e, t) {
    var n = e.getElementsByTagName("option");
    for (var r = 0; r < n.length; r++) {
        if (n[r].selected == true) {
            $$$(t).innerHTML = n[r].childNodes[0].nodeValue
        }
    }
}
function setValue_AMWCountry(e, t) {
    $$$(t).innerHTML = e.options[e.selectedIndex].innerHTML + '<i class="fa fa-chevron-down arw"></i>'
}
function openclosesearchDiv() {
    if (document.getElementById("mobileid").style.display == "block") {
        $(".srmore").slideUp("fast");
        $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-plus'></em></a>");
        if ($1("hidcollegeId") != null) {
            $1("editid").style.display = "block";
            $("#srchid").hide();
            $("#updatefltr").hide()
        }
    } else {
        $(".srmore").slideDown("fast");
        $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-minus'></em></a>")
    }
}
function hideandShow() {
    $(document).ready(function() {
        if (document.getElementById("ifqualavail") != null) {
            if (document.getElementById("ifqualavail").value != null) {
                $(".srmore").slideUp("fast");
                $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-plus'></em></a>")
            }
        }
    });
    $(document).ready(function() {
        if ($(".srmore").css("display") == "block") {
            $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-minus'></em></a>");
            if (document.getElementById("ifqualavail") != null) {
                if (document.getElementById("ifqualavail").value != null) {
                    $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-plus'></em></a>")
                }
            }
            if ($(window).width() < 768) {
                $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-plus'></em></a>")
            }
        } else {
            $(".srpshw").html("<a class='srpshw' href='javascript:void(0);' onclick='openclosesearchDiv();setBannerPostion()'><em class='fa fa-plus'></em></a>")
        }
    });
    $(document).ready(function() {
        $("#fltr_close").click(function() {
            $("#refine .blu_btn").show(500);
            $("#updatefltr").hide(500)
        })
    });
    $(document).ready(function() {
        $("#sortpopup").click(function() {
            $(".sr_drop").toggle(500)
        })
    });
    $(document).ready(function() {
        $(".tcls").click(function() {
            $("#bulb").hide(500)
        })
    });
    $(document).ready(function() {
        $("#refine .blu_btn").click(function() {
            $("#bttnId").hide();
            $("#updatefltr").show(500);
            $(this).hide()
        })
    });
    var e = 0;
    setInterval(function() {
        $(".clud").css("background-position", (e -= 10) + "px")
    },
    60)
}
function $1(e) {
    return document.getElementById(e)
}
function clearDefaultText(e, t) {
    if (e.value == t) {
        if ($1(e.name + "_hidden")) {
            $1(e.name + "_hidden").value = ""
        }
        e.value = ""
    }
}
function setDefaultText(e, t) {
    if (isEmpty(e.id)) {
        e.value = t
    }
}
function isEmpty(e) {
    if (document.getElementById(e).value != null && document.getElementById(e).value != "undefined" && document.getElementById(e).value != "" && document.getElementById(e).value.length > 0) {
        return false
    }
    return true
}
function clearErrorMessagesforHomeText(e) {
    if ($1(e) != null) {
        $1(e).className = "c_txt"
    }
}
function clearErrorMessagesforHomeDrop(e) {
    if ($1(e) != null) {
        $1(e).parentNode.className = "selBx"
    }
}
function defaultMessages(e, t) {
    if (e == "topsearchpodcollege" || e == "searchpodcollege" || e == "countrysearchpodcollege") {
        if ($1(e) != null) {
            $1(e).value = document.getElementById(t).value;
            if (e == "searchpodcollege") {
                $1("college_hidden").value = "";
                $1("selected_college").value = ""
            }
            if (e == "topsearchpodcollege") {
                $1("topCollege_hidden").value = "";
                $1("selected_topCollege").value = ""
            }
            if (e == "countrysearchpodcollege") {
                $1("countryCollege_hidden").value = "";
                $1("selected_countryCollege").value = ""
            }
        }
    }
    if (e == "ctitle") {
        if ($1("studyLevelId") != null) {
            if ($1("studyLevelId").value == "18" || $1("studyLevelId").value == "17" || $1("studyLevelId").value == "16") {} else {
                if (document.getElementById(e).value == "") {
                    document.getElementById(e).value = document.getElementById(t).value
                }
            }
        }
    }
    if (e == "topctitle") {
        if ($1("topstudyLevelId") != null) {
            if ($1("topstudyLevelId").value == "18" || $1("topstudyLevelId").value == "17" || $1("topstudyLevelId").value == "16") {} else {
                if (document.getElementById(e).value == "") {
                    document.getElementById(e).value = document.getElementById(t).value
                }
            }
        }
    }
}
function getCourseForm(e) {
    var t = $1("clgDisName").value;
    var n = $1("clgName").value;
    var r = $1("clgId").value;
    var i = $("pFlag").value;
    var s = contextPath + "/getCourseForm.html?from=" + e + "&providerFlag=" + i + "&collegeName=" + n + "&collegeDisplayName=" + t + "&collegeId=" + r;
    var o = new sack;
    o.requestFile = s;
    o.onCompletion = function() {
        fillCourseForm(o)
    };
    o.runAJAX()
}
function fillCourseForm(e) {
    if ($1("tab1")) {
        $1("tab1").innerHTML = e.response;
        $1("tab1").style.display = "block"
    }
}
function setDropDownValue(e, t) {
    var n = e.getElementsByTagName("option");
    for (var r = 0; r < n.length; r++) {
        if (n[r].selected == true) {
            $1(t).value = n[r].value
        }
    }
}
function clearSubjectValue(e) {
    if (document.getElementById(e)) {
        if ($1("studyLevelId").value == "18" || $1("studyLevelId").value == "17" || $1("studyLevelId").value == "16") {
            if (isEmpty(e) || document.getElementById(e).value == $1("crsDefText").value) {
                document.getElementById(e).value = ""
            }
        }
    }
}
function getForTopEflCourse(e) {
    var t = contextPath;
    var n = t + "/getCourseForm.html?from=" + e;
    var r = new sack;
    r.requestFile = n;
    r.onCompletion = function() {
        EflCourseForm(r)
    };
    r.runAJAX()
}
function EflCourseForm(e) {
    if ($1("tab4")) {
        $1("tab4").innerHTML = e.response;
        $1("tab4").style.display = "block"
    }
}
function cleartopSubjectValue(e, t) {
    if (document.getElementById(e)) {
        if ($1("topstudyLevelId").value == "18" || $1("topstudyLevelId").value == "17" || $1("topstudyLevelId").value == "16") {
            if (isEmpty(e) || document.getElementById(e).value == $1("defaultval").value) {
                document.getElementById(e).value = ""
            }
        }
    }
}
function getCourseFormRegion(e) {
    var t = $1("clgDisName").value;
    var n = $1("clgName").value;
    var r = $1("clgId").value;
    var i = $("pFlag").value;
    var s = contextPath + "/getCourseForm.html?from=" + e + "&providerFlag=" + i + "&collegeName=" + n + "&collegeDisplayName=" + t + "&collegeId=" + r;
    var o = new sack;
    o.requestFile = s;
    o.onCompletion = function() {
        fillCourseForm(o)
    };
    o.runAJAX()
}
function selectingDefaultValue() {
    document.getElementById("countryId").selectedIndex = 0;
    document.getElementById("studyLevelId").selectedIndex = 0;
    document.getElementById("unicountryStyleId").selectedIndex = 0;
    document.getElementById("studynat").selectedIndex = 0;
    document.getElementById("cat").selectedIndex = 0;
    document.getElementById("stdlvid").selectedIndex = 0;
    document.getElementById("college_hidden").value = ""
}
function selectingDefaultValueRegion() {
    autocompleteOff("homecoursesearch", "1");
    autocompleteOff("homescholarshipsearch", "2");
    autocompleteOff("homecollegesearch", "3");
    document.getElementById("countryIdforRegion").selectedIndex = 0;
    document.getElementById("studyLevelId").selectedIndex = 0;
    document.getElementById("unicountryStyleId").selectedIndex = 0;
    document.getElementById("studynatregion").selectedIndex = 0;
    document.getElementById("cat").selectedIndex = 0;
    document.getElementById("stdlvid").selectedIndex = 0;
    document.getElementById("college_hidden").value = "";
    document.getElementById("courseTitle_hidden").value = ""
}
function selectingDefaultValueCountry() {
    document.getElementById("cntstudyLevelId").selectedIndex = 0;
    document.getElementById("cntstudyModeId").selectedIndex = 0;
    document.getElementById("cntstdlvid").selectedIndex = 0;
    document.getElementById("cntcat").selectedIndex = 0;
    document.getElementById("countryCollege_hidden").value = "";
    document.getElementById("cntCourseTitle_hidden").value = ""
}
/* 验证 首页 课程 输入 */
function validateCourseSearchForm() {
	
	if($("#countryId").val() == "" || $("#countryId").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	if($("#studyLevelId").val() == "" || $("#studyLevelId").val() == "0"){
		alert("请选择一个学历");
		return false;
	}
	if($("#program_specialty_id").val() == "" || $("#program_specialty_id").val() == "0"){
		alert("请选择一个专业");
		return false;
	}
	return true;
	
    var e = false;
    if ($1("studyLevelId").value == "18" || $1("studyLevelId").value == "17" || $1("studyLevelId").value == "16") {
        if ($1("ctitle")) {
            if (isEmpty("ctitle") || $1("ctitle").value == $1("crsDefText").value) {
                $1("formSubmit").value = "3"
            } else {
                $1("formSubmit").value = "1"
            }
        } else {
            $1("formSubmit").value = "3"
        }
        if ($1("studyLevelId").value == "18" || $1("studyLevelId").value == "17" || $1("studyLevelId").value == "16") {
            var t = null;
            if ($1("ctitle")) {
                t = $1("ctitle").value
            }
            if ($1("studyLevelId")) {
                if (t != null && t != "") {
                    t = t + " | " + $1("studyLevelId").options[$1("studyLevelId").selectedIndex].text
                } else {
                    t = $1("studyLevelId").options[$1("studyLevelId").selectedIndex].text
                }
            }
        }
        if ($1("countryId")) {
            if ($1("countryId").value != "null" && $1("countryId").value != "") {
                t = t + " | " + $1("countryId").options[$1("countryId").selectedIndex].text
            } else {
                if ($1("countryId").selectedIndex == 1) {
                    t = t + " | " + $1("countryId").options[$1("countryId").selectedIndex].text
                }
            }
        }
        if ($1("studyModeId")) {
            if ($1("studyModeId").value != "none") {
                t = t + " | " + $1("studyModeId").options[$1("studyModeId").selectedIndex].text
            } else {
                if ($1("studyModeId").selectedIndex == 1) {
                    t = t + " | " + $1("studyModeId").options[$1("studyModeId").selectedIndex].text
                }
            }
        }
        _gaq.push(["_trackEvent", "homepage", "searchbox-[course-search]", t, 1, true]);
        return true
    }
    if ($1("ctitle")) {
        if (isEmpty("ctitle") || $1("ctitle").value == $1("crsDefText").value || $1("ctitle").value.trim() == "") {
            e = true;
            $1("ctitle").className = "c_txt err"
        }
    }
    if (document.getElementById("studyLevelId").value == "stdlvlid"||document.getElementById("studyLevelId").value == "0") {
        if ($1("locationList_h2")) {
            $1("locationList_h2").parentNode.className = "selBx err";
            e = true
        }
    }
// if ($1("countryIdforRegion")) {
// if (!checkValue("countryIdforRegion", "")) {
// $1("countryIdforRegion").value =
// $1("countryIdforRegion").options[$1("countryIdforRegion").selectedIndex].text
// } else {
// e = true;
// $1("locationList_h30").parentNode.className = "selBx err"
// }
// }
    if (checkValue("countryId", "0")) {
    	if($1("locationList_h1")) {
    		$1("locationList_h1").parentNode.className = "selBx err";
    		e = true
    	}
    }
    if ($1("studyLevelId").value == "18" || $1("studyLevelId").value == "17" || $1("studyLevelId").value == "16") {
        $1("formSubmit").value = "3"
    } else {
        $1("formSubmit").value = "1"
    }
    if (e) {
        return false
    } else {
        var n = null;
        if ($1("ctitle")) {
            n = $1("ctitle").value
        } else {
            if ($1("eflctypeId")) {
                if ($1("eflctypeId").value != "null" && $1("eflctypeId").value != "") {
                    n = $1("eflctypeId").options[$1("eflctypeId").selectedIndex].text
                }
            }
        }
        if ($1("studyLevelId")) {
            if ($1("studyLevelId").value != "null" && $1("studyLevelId").value != "") {
                if (n != null) {
                    n = n + " | " + $1("studyLevelId").options[$1("studyLevelId").selectedIndex].text
                } else {
                    n = $1("studyLevelId").options[$1("studyLevelId").selectedIndex].text
                }
            }
        }
        if ($1("countryId")) {
            if ($1("countryId").value != "null" && $1("countryId").value != "") {
                n = n + " | " + $1("countryId").options[$1("countryId").selectedIndex].text
            } else {
                if ($1("countryId").selectedIndex == 1) {
                    n = n + " | " + $1("countryId").options[$1("countryId").selectedIndex].text
                }
            }
        }
        if ($1("countryIdforRegion")) {
            if ($1("countryIdforRegion").value != "null" && $1("countryIdforRegion").value != "") {
                n = n + " | " + $1("countryIdforRegion").options[$1("countryIdforRegion").selectedIndex].text
            } else {
                if ($1("countryIdforRegion").selectedIndex == 1) {
                    n = n + " | " + $1("countryIdforRegion").options[$1("countryIdforRegion").selectedIndex].text
                }
            }
        }
        if ($1("studyModeId")) {
            if ($1("studyModeId").value != "none") {
                n = n + " | " + $1("studyModeId").options[$1("studyModeId").selectedIndex].text
            } else {
                if ($1("studyModeId").selectedIndex == 1) {
                    n = n + " | " + $1("studyModeId").options[$1("studyModeId").selectedIndex].text
                }
            }
        }
        _gaq.push(["_trackEvent", "homepage", "searchbox-[course-search]", n, 1, true]);
        return true
    }
}
function makeDisableCollege(e, t) {
    if (checkValue(t, "")) {
        $1(e).disabled = true
    } else {
        $1(e).disabled = false
    }
}
function makeDisableCollegereadonly(e, t) {
    if (checkValue(t, "")) {
        $("#topsearchpodcollege").prop("readonly", true);
        $1(e).readonly = true
    } else {
        $1(e).readonly = false;
        $("#topsearchpodcollege").removeAttr("readonly")
    }
}
function checkCountry(e, t) {
    if (!checkValue("unicountryStyleId", "")) {
        if (ajax_list_activeInput.value != $1("searchpodcollege").value) {
            setValue("college_hidden", "")
        }
        ajax_showOptions(t, "getCountriesByLetters", e, "countryId:unicountryStyleId", "homesearch")
    } else {
        $1("locationList_h3").parentNode.className = "selBx err";
        return false
    }
}
/* 验证 首页 院校 输入 */
function validateCollegeSearchForm() {

	if($("#unicountryStyleId").val() == "" || $("#unicountryStyleId").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	if($("#university_name_id").val() == "" || $("#university_name_id").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	return true;
	
    var e = "N";
    if (checkValue("unicountryStyleId", "0")) {
        $1("locationList_h3").parentNode.className = "selBx err";
        setValue("college_hidden", "");
        e = "Y"
    }
// if (checkValue("college_hidden", "")) {
// $1("searchpodcollege").className = "c_txt err";
// e = "Y"
// }
    if ($1("ctitle")) {
        if (isEmpty("searchpodcollege") || $1("searchpodcollege").value == $1("defaultunival").value || $1("searchpodcollege").value.trim() == "") {
            e = "Y";
            $1("searchpodcollege").className = "c_txt err"
        }
    }
    if (e != "N") {
        return false
    } else {
        var t = $1("unicountryStyleId").options[$1("unicountryStyleId").selectedIndex].text + " | " + $1("searchpodcollege").value;
        _gaq.push(["_trackEvent", "homepage", "searchbox-[uni-search]", t, 1, true]);
        return true
    }
}
function checkValue(e, t) {
    if (document.getElementById(e)) {
        if (trim(document.getElementById(e).value) == t) return true
    }
    return false
}
function trim(e) {
    while (e.substring(0, 1) == " ") {
        e = e.substring(1, e.length)
    }
    while (e.substring(e.length - 1, e.length) == " ") {
        e = e.substring(0, e.length - 1)
    }
    return e
}
function setValue(e, t) {
    if (document.getElementById(e)) document.getElementById(e).value = t
}
/* 验证 首页 攻略 输入 */
function validateSpecialtyForm() {

	if($("#studynat").val() == "" || $("#studynat").val() == "0"){
		alert("请选择一个国家");
		return false;
	}
	if($("#guide_specialty_id").val() == "" || $("#guide_specialty_id").val() == "0"){
		alert("请选择一个专业");
		return false;
	}
	return true;
	
    var e = "N";
    var t = $1("schError").value;
    // if (document.getElementById("cat").value == "sub") {
    if (document.getElementById("cat").value == "0") {
        if ($1("locationList_h5")) {
            $1("locationList_h5").parentNode.className = "selBx err";
            e = "Y"
        }
        if ($1("locationList_h7")) {
            $1("locationList_h7").parentNode.className = "selBx err";
            e = "Y"
        }
    }
// if (document.getElementById("stdlvid").value == "stdlvl") {
// $1("locationList_h6").parentNode.className = "selBx err";
// e = "Y"
// }
    // if ($1("studynat") != null && document.getElementById("studynat").value
	// == "scon") {
    if ($1("studynat") != null && document.getElementById("studynat").value == "0") {
        $1("locationList_h8").parentNode.className = "selBx err";
        e = "Y"
    }
// if ($1("studynatregion") != null &&
// document.getElementById("studynatregion").value == "scon") {
// $1("locationList_h15").parentNode.className = "selBx err";
// e = "Y"
// }
    console.log("erflag " + e);
    if (e == "Y") {
        return false
    } else {
        var n = null;
        var r = "N";
        if ($1("livingnat")) {
            if ($1("engsite")) {
                r = $1("engsite").value;
                if (r != null && r != "Y") {
                    n = $1("localsitename").value
                } else {
                    n = $1("livingnat").options[$1("livingnat").selectedIndex].text
                }
            }
        }
        if ($1("stdlvid")) {
            n = n + " | " + $1("stdlvid").options[$1("stdlvid").selectedIndex].text
        }
        if ($1("studynat")) {
            if ($1("studynat").value != "scon") {
                n = n + " | " + $1("studynat").options[$1("studynat").selectedIndex].text
            }
        } else {
            if ($1("studynatregion")) {
                if ($1("studynatregion").value != "scon") {
                    n = n + " | " + $1("studynatregion").options[$1("studynatregion").selectedIndex].text
                }
            }
        }
        if ($1("cat")) {
            if ($1("cat").value != "sub") {
                n = n + " | " + $1("cat").options[$1("cat").selectedIndex].text
            }
        }
        _gaq.push(["_trackEvent", "homepage", "searchbox-[schol-search]", n, 1, true]);
        return true
    }
}
function slideDiv() {
    $(".accordion .click:first").addClass("tab");
    $(".accordion .click").click(function() {
        $(this).next(".shw").slideToggle("slow").siblings(".shw:visible").slideUp("slow");
        $(this).toggleClass("tab");
        $(this).siblings(".click").removeClass("tab")
    })
}
function ajaxtopsearchpods(e) {
    var t = contextPath + "/ajaxTopSearch.html";
    if ($1(e).innerHTML.trim() == 0) {
        var n = new sack;
        n.requestFile = t;
        $1("loidingImage").style.display = "block";
        n.onCompletion = function() {
            ajaxTopSearchForm(n, e)
        };
        n.runAJAX()
    } else {
        $1(e).style.display = "block";
        $1("searchlink").className = "icn act"
    }
}
function ajaxTopSearchForm(ajax, id) {
    if ($1(id)) {
        $1("loidingImage").style.display = "none";
        $1(id).innerHTML = ajax.response;
        $1(id).style.display = "block";
        $1("searchlink").className = "icn act";
        eval($1("slideLoad").innerHTML)
    }
}
function submitOnEnter(e) {
    var e = e ? e: event ? event: null;
    var t = e.target ? e.target: e.srcElement ? e.srcElement: null;
    if (e.keyCode == 13 && t.type == "text") {
        subScribe("semail_id")
    }
}
function subScribe(e) {
    clearErrorMessagesForEmailFooter("semail_id");
    clearErrorMessages("esucess");
    clearErrorMessages("foot_emailalready_msg");
    if ($1(e) != null && ($1(e).value == "" || $1(e).value == " " || $1(e).value == $1("def_id").value)) {
        $1("semail_id").className = "grey err";
        return false
    } else if (echeck(e) == false) {
        $1("semail_id").className = "grey err";
        $1(e).focus();
        return false
    } else {
        var t = contextPath;
        var n = t + "/emailsubscribe.html?emailId=" + $1(e).value;
        var r = new sack;
        r.requestFile = n;
        r.onCompletion = function() {
            var e = r.response;
            if (e != null) {
                if (e.trim() == "0") {
                    $1("esucess").style.display = "block"
                } else if (e.trim() == "1") {
                    $1("semail_id").className = "grey err"
                } else if (e.trim() == "2") {
                    $1("semail_id").className = "grey err";
                    $1("foot_emailalready_msg").style.display = "block"
                }
            }
        };
        r.runAJAX()
    }
    return false
}
function clearErrorMessagesForEmailFooter(e) {
    if ($1(e) != null) {
        $1(e).className = "grey"
    }
}
function echeck(e) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(trim($1(e).value))) {
        return true
    }
    return false
}
function clearDefaultTxt(e, t) {
    if (e.value == $1(t).value) {
        e.value = ""
    }
}
function addDefaultTxt(e, t) {
    if (e.value == "") {
        e.value = $1(t).value
    }
}
function clearErrorMessages(e) {
    if ($1(e) != null) {
        $1(e).style.display = "none"
    }
}
function clearText(e) {
    var t = e.value;
    if ($1("courseTitle_hidden")) {
        $1("courseTitle_hidden").value = ""
    }
    if (t.indexOf("*") != -1) {
        e.value = ""
    }
}
function get_cookie(e) {
    var t = document.cookie.match("(^|;) ?" + e + "=([^;]*)(;|$)");
    if (t) return unescape(t[2]);
    else return null
}
function clearDefaultTextTop(e, t) {
    if (e.value == t) {
        if ($1(e.name + "_hidden")) {
            $1(e.name + "_hidden").value = ""
        }
        e.value = ""
    }
}
function clearErrorMessagesforTopText(e) {
    if ($1(e) != null) {
        $1(e).className = "c_txt"
    }
}
function clearErrorMessagesforTopDrop(e) {
    if ($1(e) != null) {
        $1(e).parentNode.className = "selBx"
    }
}
function validateTopCourseSearchForm(e, t) {
    var n = false;
    if ($1("topstudyLevelId").value == "18" || $1("topstudyLevelId").value == "17" || $1("topstudyLevelId").value == "16") {
        if (document.getElementById("topctitle")) {
            if (isEmpty("topctitle") || document.getElementById("topctitle").value == t) {
                $1("formSubmit").value = "3"
            } else {
                $1("formSubmit").value = "1"
            }
        } else {
            $1("formSubmit").value = "3"
        }
        var r = null;
        if ($1("topctitle")) {
            r = document.getElementById("topctitle").value
        }
        if ($1("topstudyLevelId")) {
            if (r != null && r != "") {
                r = r + " | " + $1("topstudyLevelId").options[$1("topstudyLevelId").selectedIndex].text
            } else {
                r = $1("topstudyLevelId").options[$1("topstudyLevelId").selectedIndex].text
            }
        }
        if ($1("topcountryId")) {
            if ($1("topcountryId").value != "null" && $1("topcountryId").value != "") {
                r = r + " | " + $1("topcountryId").options[$1("topcountryId").selectedIndex].text
            } else {
                if ($1("topcountryId").selectedIndex == 1) {
                    r = r + " | " + $1("topcountryId").options[$1("topcountryId").selectedIndex].text
                }
            }
        }
        if ($1("topstudyModeId")) {
            if ($1("topstudyModeId").value != "none") {
                r = r + " | " + $1("topstudyModeId").options[$1("topstudyModeId").selectedIndex].text
            } else {
                if ($1("topstudyModeId").selectedIndex == 1) {
                    r = r + " | " + $1("topstudyModeId").options[$1("topstudyModeId").selectedIndex].text
                }
            }
        }
        _gaq.push(["_trackEvent", "header-engagement", "course-search", r, 1, true]);
        return true
    }
    if (document.getElementById("topctitle")) {
        if (isEmpty("topctitle") || document.getElementById("topctitle").value == t || document.getElementById("topctitle").value.trim() == "") {
            n = true;
            $1("topctitle").className = "c_txt err"
        }
        // if (document.getElementById("topstudyLevelId").value == "topstdlvl")
		// {
        if (document.getElementById("topstudyLevelId").value == "0") {
            if ($1("locationList_h16")) {
                $1("locationList_h16").parentNode.className = "selBx err";
                n = true
            }
        }
    }
    if ($1("topstudyLevelId").value == "18" || $1("topstudyLevelId").value == "17" || $1("topstudyLevelId").value == "16") {
        $1("formSubmit").value = "3"
    } else {
        $1("formSubmit").value = "1"
    }
    if (n) {
        return false
    } else {
        var i = null;
        if ($1("topctitle")) {
            i = document.getElementById("topctitle").value
        } else {
            if ($1("eflctypeId")) {
                if ($1("eflctypeId").value != "null" && $1("eflctypeId").value != "") {
                    i = $1("eflctypeId").options[$1("eflctypeId").selectedIndex].text
                }
            }
        }
        if ($1("topstudyLevelId")) {
            if ($1("topstudyLevelId").value != "null" && $1("topstudyLevelId").value != "") {
                if (i != null) {
                    i = i + " | " + $1("topstudyLevelId").options[$1("topstudyLevelId").selectedIndex].text
                } else {
                    i = $1("topstudyLevelId").options[$1("topstudyLevelId").selectedIndex].text
                }
            }
        }
        if ($1("topcountryId")) {
            if ($1("topcountryId").value != "null" && $1("topcountryId").value != "") {
                i = i + " | " + $1("topcountryId").options[$1("topcountryId").selectedIndex].text
            } else {
                if ($1("topcountryId").selectedIndex == 1) {
                    i = i + " | " + $1("topcountryId").options[$1("topcountryId").selectedIndex].text
                }
            }
        }
        if ($1("topstudyModeId")) {
            if ($1("topstudyModeId").value != "none") {
                i = i + " | " + $1("topstudyModeId").options[$1("topstudyModeId").selectedIndex].text
            } else {
                if ($1("topstudyModeId").selectedIndex == 1) {
                    i = i + " | " + $1("topstudyModeId").options[$1("topstudyModeId").selectedIndex].text
                }
            }
        }
        _gaq.push(["_trackEvent", "header-engagement", "course-search", i, 1, true]);
        return true
    }
}
function setDefaultTextTop(e, t) {
    if (isEmpty(e.id)) {
        e.value = t
    }
}
function topvalidateCollegeSearchForm() {
    var e = "N";
    if (checkValue("topunicountryStyleId", "")) {
        $1("locationList_h18").parentNode.className = "selBx err";
        setValue("topCollege_hidden", "");
        e = "Y"
    }
    if (checkValue("topCollege_hidden", "")) {
        $1("topsearchpodcollege").className = "c_txt err";
        e = "Y"
    }
    if ($1("topsearchpodcollege")) {
        if (isEmpty("topsearchpodcollege") || $1("topsearchpodcollege").value == $1("defaultunival").value || $1("topsearchpodcollege").value.trim() == "") {
            e = "Y";
            $1("topsearchpodcollege").className = "c_txt err"
        }
    }
    if (e != "N") {
        return false
    } else {
        var t = $1("topunicountryStyleId").options[$1("topunicountryStyleId").selectedIndex].text + " | " + $1("topsearchpodcollege").value;
        _gaq.push(["_trackEvent", "header-engagement", "uni-search", t, 1, true]);
        return true
    }
}
function topcheckCountry(e, t) {
    if (!checkValue("topunicountryStyleId", "")) {
        if (ajax_list_activeInput.value != $1("topsearchpodcollege").value) {
            setValue("topCollege_hidden", "")
        }
        ajax_showOptions(t, "getCountriesByLetters", e, "countryId:topunicountryStyleId", "homesearch")
    } else {
        $1("locationList_h18").parentNode.className = "selBx err";
        return false
    }
}
function topvalidateScholarshipForm() {
    var e = "N";
    if (document.getElementById("topstdlvid").value == "stdlvl") {
        $1("locationList_h20").parentNode.className = "selBx err";
        e = "Y"
    }
    if ($1("topstudynat") != null && document.getElementById("topstudynat").value == "scon") {
        $1("locationList_h8").parentNode.className = "selBx err";
        e = "Y"
    }
    if (document.getElementById("topcat").value == "sub") {
        $1("locationList_h19").parentNode.className = "selBx err";
        e = "Y"
    }
    if (e != "N") {
        return false
    } else {
        var t = null;
        var n = "N";
        if ($1("toplivingnat")) {
            if ($1("engsite")) {
                n = document.getElementById("engsite").value;
                if (n != null && n != "Y") {
                    t = document.getElementById("localsitename").value
                } else {
                    t = $1("toplivingnat").options[$1("toplivingnat").selectedIndex].text
                }
            }
        }
        if ($1("topstdlvid")) {
            t = t + " | " + $1("topstdlvid").options[$1("topstdlvid").selectedIndex].text
        }
        if ($1("topstudynat")) {
            if ($1("topstudynat").value != "scon") {
                t = t + " | " + $1("topstudynat").options[$1("topstudynat").selectedIndex].text
            }
        }
        if ($1("topcat")) {
            if ($1("topcat").value != "sub") {
                t = t + " | " + $1("topcat").options[$1("topcat").selectedIndex].text
            }
        }
        _gaq.push(["_trackEvent", "header-engagement", "schol-search", t, 1, true]);
        return true
    }
}
function addImageDiv(e, t, n) {
    var r = document.createElement("SPAN");
    var i = document.getElementById("imageHolder");
    t = t.replace(" ", "");
    e = e.replace(" ", "");
    e = e.replace(" ", "");
    e = e.replace(" ", "");
    var s = n + "/hca-cont/img/map/county/" + t + "/" + e + ".gif";
    r.id = e;
    r.className = "tnyMap";
    r.style.backgroundImage = "url('" + s + "')";
    r.style.backgroundRepeat = "no-repeat";
    i.appendChild(r)
}
function loadMultiCountyMap(e, t, n) {
    var r = e.split(",");
    if (t == "uk") {
        for (var i = 0; i < r.length; i++) {
            r[i] = r[i].toLowerCase();
            r[i] = r[i].replace(" ", "_")
        }
    }
    for (var i = 0; i < r.length; i++) {
        addImageDiv(r[i], t, n)
    }
}
function cpeWebLinkClick(e, t, n, r, i, s, o, u) {
    cpeJSstats(e, n, r, t, i, s, o, u, null, null)
}
function cpeJSstats(e, t, n, r, i, s, o, u, a, f) {
    var l = contextPath + "/cpejsstats.html?collegeId=" + t + "&countryId=" + n + "&suborderitem=" + i + "&webflag=" + s + "&quallevel=" + o + "&courseid=" + u + "&sectionId=" + a + "&redirecturl=" + f;
    if (r != null) {
        l = l + "&affID=" + r
    }
    var c = new sack;
    c.requestFile = l;
    c.runAJAX()
}
function hcAdClick(e, t, n, r, i, s) {
    var o = queryString("url", e);
    cpeJSstats(e, t, s, n, i, null, null, null, r, o)
}
function queryString(e, t) {
    var n = t.search.substring(1, t.search.length);
    var r = false;
    var s = n.split("&");
    for (i = 0; i < s.length; i++) {
        param_name = s[i].substring(0, s[i].indexOf("="));
        if (param_name == e) {
            r = s[i].substring(s[i].indexOf("=") + 1)
        }
    }
    if (r) {
        return r
    } else {
        return false
    }
}
function LightBoxProspectusData(e, t, n, r, i, s, o, u, a, f, l, c) {
    var h = "collegeId=" + n + "&affiliateId=" + r + "&countryId=" + i + "&categoryCode=" + s + "&keyword=" + o + "&qualId=" + u + "&prospectusfrompage=" + f + "&courseId=" + l + "&subOrderItemId=" + c;
    var p = contextPath + "/prospectus.html?" + h;
    location.href = p
}
function lightBoxPlay(e, t, n, r, i, s, o, u, a, f, l, c) {
    isVideoPlayed = false;
    if ($(window).width() < 768) {
        if ($1("eflvedio") != null) {
            var h = contextPath + "/lightbox.html?videopath=" + n + "&vimage=" + r + "&collegeId=" + i + "&countryId=" + s + "&suborderitemid=" + o + "&keyProfileId=" + u + "&sectext=" + a + "&secid=" + f + "&reqsurl=" + $1("reqsid").value + "&refurl=" + $1("refrid").value + "&eflvideolog=Y&sectionlog=" + c
        } else {
            var h = contextPath + "/lightbox.html?videopath=" + n + "&vimage=" + r + "&collegeId=" + i + "&countryId=" + s + "&suborderitemid=" + o + "&keyProfileId=" + u + "&sectext=" + a + "&secid=" + f + "&activity=" + l + "&reqsurl=" + $1("reqsid").value + "&refurl=" + $1("refrid").value + "&sectionlog=" + c
        }
        location.href = h
    } else {
        $1("overlayId").style.display = "block";
        $1("shwId").style.display = "block";
        $1("videoclose").style.display = "block";
        jwplayer(e).setup({
            flashplayer: t,
            file: n,
            image: r.replace("_116px", ""),
            height: 315,
            autostart: "false",
            events: {
                onPlay: function() {
                    if (!isVideoPlayed) {
                        if ($1("eflvedio") != null) {
                            JSstats("HOTCOURSES: VIDEO CLICK: EFL PROFILE:" + c, i, null, null, null, $1("refrid").value, null, null, null, null, null, null)
                        } else {
                            JSstats(l, i, s, f, c, u, null, null, null, null, $1("reqsid").value, $1("refrid").value, o)
                        }
                    }
                    isVideoPlayed = true
                }
            }
        })
    }
}
function showImage(e, t, n, r) {
    if ($(window).width() < 768) {
        var i = contextPath + "/lightbox.html?imagepath=" + t + "&collegeId=" + n + "&sectext=" + r;
        location.href = i
    } else {
        $1("overlayId").style.display = "block";
        $1("shwId").style.display = "block";
        $1("videoclose").style.display = "block";
        $1(e).innerHTML = '<div id="flashbanner"><img src="' + t + '"/></div>'
    }
}
function checkValidation() {
    if ($1("courseTitle_hidden") != null && $1("courseTitle_hidden").value.length == 0) {
        alert("Please enter a subject and pick the suggestion from list of options");
        return false
    }
    return true
}
function showData(e, t) {
    var n = t.split(":");
    if ($1(n[0]) != null) {
        $1(n[0]).style.display = "none"
    }
    if ($1(n[1]) != null) {
        $1(n[1]).style.display = "none"
    }
    var r = e.split(":");
    if ($1(r[0]) != null) {
        $1(r[0]).style.display = "block"
    }
    if ($1(r[1]) != null) {
        $1(r[1]).style.display = "block"
    }
}
function showLessData(e, t) {
    if ($1("morEventDetails").innerHTML != "") {
        $1("morEventDetails").innerHTML = ""
    }
    $1(t).style.display = "none";
    $1(e).style.display = "block";
    $1("shwMore").style.display = "block";
    $1("shwLess").style.display = "none";
    $1("curntEvntCount").value = "0";
    $1("pageNum").value = "0"
}
function videoClose() {
    $1("overlayId").style.display = "none";
    $1("shwId").style.display = "none";
    if ($1("flashbanner")) {
        jwplayer().onBuffer(function() {
            jwplayer("flashbanner").stop()
        });
        jwplayer("flashbanner").stop()
    }
}
function ctryvalidateCollegeSearchForm() {
    var e = "N";
    if (checkValue("countryunicountryStyleId", "")) {
        $1("locationList_h1").parentNode.className = "selBx err";
        setValue("countryCollege_hidden", "");
        e = "Y"
    }
    if (checkValue("countryCollege_hidden", "")) {
        $1("countrysearchpodcollege").className = "c_txt err";
        e = "Y"
    }
    if ($1("countrysearchpodcollege")) {
        if (isEmpty("countrysearchpodcollege") || $1("countrysearchpodcollege").value == $1("cntdefaultunival").value || $1("countrysearchpodcollege").value.trim() == "") {
            e = "Y";
            $1("countrysearchpodcollege").className = "c_txt err"
        }
    }
    if (e != "N") {
        return false
    } else {
        var t = $1("countryunicountryStyleId").options[$1("countryunicountryStyleId").selectedIndex].text + " | " + $1("countrysearchpodcollege").value;
        _gaq.push(["_trackEvent", "header-engagement", "uni-search", t, 1, true]);
        return true
    }
}
function checkCountryId(e, t) {
    if (!checkValue("countryunicountryStyleId", "")) {
        if (ajax_list_activeInput.value != $1("countrysearchpodcollege").value) {
            setValue("countryCollege_hidden", "")
        }
        ajax_showOptions(t, "getCountriesByLetters", e, "countryId:countryunicountryStyleId", "homesearch")
    } else {
        $1("locationList_h1").parentNode.className = "selBx err";
        return false
    }
}
function validateCountryCourseSearchForm(e, t) {
    var n = false;
    if ($1("cntstudyLevelId").value == "18" || $1("cntstudyLevelId").value == "17" || $1("cntstudyLevelId").value == "16") {
        if (document.getElementById("cntctitle")) {
            if (isEmpty("cntctitle") || document.getElementById("cntctitle").value == t) {
                $1("formSubmit").value = "3"
            } else {
                $1("formSubmit").value = "1"
            }
        } else {
            $1("formSubmit").value = "3"
        }
        _gaq.push(["_trackEvent", "Interaction", "course-search", "", 1, true]);
        return true
    }
    if (document.getElementById("cntctitle")) {
        if (isEmpty("cntctitle") || document.getElementById("cntctitle").value == t || document.getElementById("cntctitle").value.trim() == "") {
            n = true;
            $1("cntctitle").className = "c_txt err"
        }
    }
    if (document.getElementById("cntstudyLevelId").value == "cntstdlvl") {
        if ($1("locationList_h3")) {
            $1("locationList_h3").parentNode.className = "selBx err";
            n = true
        }
    }
    if ($1("cntstudyLevelId").value == "18" || $1("cntstudyLevelId").value == "17" || $1("cntstudyLevelId").value == "16") {
        $1("formSubmit").value = "3"
    } else {
        $1("formSubmit").value = "1"
    }
    if (n) {
        return false
    } else {
        var r = null;
        if ($1("cntctitle")) {
            r = document.getElementById("cntctitle").value
        } else {
            if ($1("eflctypeId")) {
                if ($1("eflctypeId").value != "null" && $1("eflctypeId").value != "") {
                    r = $1("eflctypeId").options[$1("eflctypeId").selectedIndex].text
                }
            }
        }
        if ($1("cntstudyLevelId")) {
            if ($1("cntstudyLevelId").value != "null" && $1("cntstudyLevelId").value != "") {
                if (r != null) {
                    r = r + " | " + $1("cntstudyLevelId").options[$1("cntstudyLevelId").selectedIndex].text
                } else {
                    r = $1("cntstudyLevelId").options[$1("cntstudyLevelId").selectedIndex].text
                }
            }
        }
        if ($1("cntcountryId")) {
            if ($1("cntcountryId").value != "null" && $1("cntcountryId").value != "") {
                r = r + " | " + $1("cntcountryId").options[$1("cntcountryId").selectedIndex].text
            }
        }
        if ($1("cntstudyModeId")) {
            if ($1("cntstudyModeId").value != "none") {
                r = r + " | " + $1("cntstudyModeId").options[$1("cntstudyModeId").selectedIndex].text
            }
        }
        _gaq.push(["_trackEvent", "header-engagement", "course-search", r, 1, true]);
        return true
    }
}
function cntValidateScholarshipForm() {
    var e = "N";
    if (document.getElementById("cntstudynat").value == "scon") {
        $1("locationList_h6").parentNode.className = "selBx err";
        e = "Y"
    }
    if (document.getElementById("cntstdlvid").value == "stdlvl") {
        $1("locationList_h7").parentNode.className = "selBx err";
        e = "Y"
    }
    if (document.getElementById("cntcat").value == "sub") {
        $1("locationList_h8").parentNode.className = "selBx err";
        e = "Y"
    }
    if (e != "N") {
        return false
    } else {
        var t = null;
        var n = "N";
        if ($1("cntlivingnat")) {
            if ($1("cntengsite")) {
                n = document.getElementById("cntengsite").value;
                if (n != null && n != "Y") {
                    t = document.getElementById("cntlocalsitename").value
                } else {
                    t = $1("cntlivingnat").options[$1("cntlivingnat").selectedIndex].text
                }
            }
        }
        if ($1("cntstdlvid")) {
            t = t + " | " + $1("cntstdlvid").options[$1("cntstdlvid").selectedIndex].text
        }
        if ($1("cntstudynat")) {
            if ($1("cntstudynat").value != "scon") {
                t = t + " | " + $1("cntstudynat").options[$1("cntstudynat").selectedIndex].text
            }
        }
        if ($1("cntcat")) {
            if ($1("cntcat").value != "sub") {
                t = t + " | " + $1("cntcat").options[$1("cntcats").selectedIndex].text
            }
        }
        _gaq.push(["_trackEvent", "header-engagement", "schol-search", t, 1, true]);
        return true
    }
}
function checkSearchText(e) {
    var t = $1("searchText").value;
    var n = $1("noinputmsg").value;
    var r = $1("defaulttext").value;
    if (t == null || t.trim() == "" || t.trim() == r) {
        alert(n);
        return false
    }
    return true
}
function placeHolderArticle(e) {
    var t = $1("searchText");
    var n = $1("defaulttext").value;
    if (t.value.trim() == "" && e.type == "blur") {
        t.value = n;
        t.className = ""
    } else if (t.value.trim() == n) {
        t.value = "";
        t.className = "black"
    }
}
function moreArticleshowHide(e, t) {
    if ($1(e).style.display == "none") {
        $1(e).style.display = "block";
        $1(t).className = "act"
    } else {
        $1(e).style.display = "none";
        $1(t).className = ""
    }
}
function populateSelectedValue(e, t) {
    if (e != null) {
        var n = $1(e);
        var r = n.options[n.selectedIndex].text;
        document.getElementById(t).innerHTML = r + '<i class="fa fa-chevron-down arw"></i>'
    }
}
function clearText(e) {
    var t = e.value;
    if ($1("courseTitle_hidden")) {
        $1("courseTitle_hidden").value = ""
    }
    if (t.indexOf("*") != -1) {
        e.value = ""
    }
}
function checkCourseName(e) {
    if ($1("ctitle").value == "") {
        $1("ctitle").value = e
    }
}
function hideData(e, t) {
    if ($1(e) != null) {
        $1(e).style.display = "none"
    }
    if ($1(t) != null) {
        $1(t).style.display = "block"
    }
}
function closeAjaxDiv(e, t) {
    if ($1("ajax_listOfOptions")) {
        if ($1("ajax_listOfOptions").innerHTML != "") {
            $1("ajax_listOfOptions").style.display = "none"
        }
    }
}
function closeDivtop(e) {
    if (e) {
        if ($1(e).style.display == "block" && $1("ajax_listOfOptions") != null && $1("ajax_listOfOptions").style.display == "block") {} else {
            if ($1("selectedstudy") != null && $1("selectedstudy").value == "Y") {
                $1("selectedstudy").value = "N"
            } else {
                $1(e).style.display = "none";
                if ($1("ajax_listOfOptions")) {
                    $1("ajax_listOfOptions").style.display = "none"
                }
                $1("searchlink").className = "icn"
            }
        }
    }
}
function placevalue(e) {
    $1(e).value = "Y"
}
function enquiry(e) {
    var t = e;
    var n = new sack;
    n.requestFile = t;
    n.onCompletion = function() {
        checkenquiryResponse(n, e)
    };
    n.runAJAX()
}
function checkenquiryResponse(e, t) {
    var n = e.response.split("~");
    if (n[0] == "Y") {
        stats(n[2], n[1], n[3], n[4], n[5]);
        window.location.href = n[1]
    } else {
        window.location.href = t + "&ajax=true"
    }
}
function enquirysignup(e, t, n) {
    var r = "N";
    if (e == "newenqformuser") {
        r = "Y"
    } else {
        if (validateEnquiryLogin(t) != false) {
            if ($1(t) != null) {
                if (t == "newuser") {
                    $1("registration_div").style.display = "none"
                } else {
                    if (t != "registereduser") {
                        $1("login_div").style.display = "none"
                    }
                }
            }
            r = "Y"
        } else {
            if (t == "registereduser") {
                return false
            }
        }
    }
    if (r == "Y") {
        var i = null,
        s = null,
        o = null,
        u = null,
        a = null;
        var f = null,
        l = null,
        c = null,
        h = null,
        p = null;
        var d = null;
        if (t == "newuser") {
            if ($1("eMail").value != null) {
                i = $1("eMail").value
            }
            if ($1("confirmEmailId").value != null) {
                confirmEmailenqnewuserId = $1("confirmEmailId").value
            }
            if (t == "newuser") {
                if ($1("passwordValue").value != null) {
                    o = $1("passwordValue").value
                }
                if ($1("fname").value != null) {
                    u = $1("fname").value;
                    $1("firstName").value = u
                }
                if ($1("lname").value != null) {
                    a = $1("lname").value;
                    $1("lastName").value = a
                }
                if ($1("subscribe").value != null) {
                    if ($1("subscribe").checked == true) {
                        f = "off"
                    } else {
                        f = "on"
                    }
                }
            }
        } else {
            if (t == "newformuser") {
                if (n != "MAS" && $1("passwordno").checked == true) {
                    l = "notregister"
                } else {
                    l = "yesregister"
                }
                if ($1("passwordValue") != null) {
                    if ($1("passwordValue").value != null) {
                        o = $1("passwordValue").value
                    }
                }
                if ($1("confirmEmailId") != null) {
                    if ($1("confirmEmailId").value != null) {
                        i = $1("confirmEmailId").value
                    }
                }
                if ($1("fstname") != null) {
                    if ($1("fstname").value != null) {
                        u = $1("fstname").value
                    }
                }
                if ($1("lstname") != null) {
                    if ($1("lstname").value != null) {
                        a = $1("lstname").value
                    }
                }
                if ($1("yournationality") != null) {
                    if ($1("yournationality").value != null) {
                        d = $1("yournationality").value
                    }
                }
                if ($1("phonetype") != null) {
                    if ($1("phonetype").value != null) {
                        c = $1("phonetype").value;
                        if (c != null && c == "-") {
                            c = null
                        }
                    }
                }
                if ($1("code") != null) {
                    if ($1("code").value != null) {
                        h = $1("code").value;
                        if (h != null && h == "-") {
                            h = null
                        }
                    }
                }
                if ($1("phoneNumber") != null) {
                    if ($1("phoneNumber").value != null) {
                        p = $1("phoneNumber").value;
                        if (p != null && p == "Number") {
                            p = null
                        }
                    }
                }
                if ($1("subscribe").value != null) {
                    if ($1("subscribe").checked == true) {
                        f = "off"
                    } else {
                        f = "on"
                    }
                }
            } else {
                if ($1("eMail").value != null) {
                    i = $1("eMail").value
                }
                if ($1("passwordVal").value != null) {
                    o = $1("passwordVal").value
                }
            }
        }
        if (n != null && n == "DP") {
            var v = "/study/RegisterViaEnquiry.html?emailId=" + encodeURIComponent(i) + "&passWord=" + encodeURIComponent(o) + "&confirmEmailId=" + encodeURIComponent(s) + "&fname=" + u + "&lname=" + a + "&userid=" + t + "&subscribeflag=" + f + "&from=" + l + "&nationality=" + d + "&phonetype=" + c + "&cntrycode=" + h + "&phoneno=" + p + "&pagename=" + n;
            $.ajax({
                type: "POST",
                async: false,
                url: v,
                dataType: "html",
                success: function(r) {
                    showLoginDetails(r, e, t, n)
                }
            })
        } else {
            var m = new sack;
            m.requestFile = "/study/RegisterViaEnquiry.html?emailId=" + encodeURIComponent(i) + "&passWord=" + encodeURIComponent(o) + "&confirmEmailId=" + encodeURIComponent(s) + "&fname=" + u + "&lname=" + a + "&userid=" + t + "&subscribeflag=" + f + "&from=" + l + "&nationality=" + d + "&phonetype=" + c + "&cntrycode=" + h + "&phoneno=" + p + "&pagename=" + n;
            m.onCompletion = function() {
                showLoginDetails(m, e, t, n)
            };
            m.runAJAX()
        }
    }
}
function showForgotPassword(e) {
    if (e == "show") {
        animatedcollapse.show("forgorpassword_div");
        animatedcollapse.hide("errordiv");
        blockNone("forgotpass_spinner", "none")
    } else {
        animatedcollapse.hide("forgorpassword_div");
        animatedcollapse.hide("errordiv")
    }
}
function showLoginDetails(e, t, n, r) {
    var i = null;
    if (r != null && r == "DP") {
        i = e.split("##")
    } else {
        i = e.response.split("##")
    }
    if (n != null) {
        if (n == "newuser" || n == "newformuser") {
            if (i[4] != null && i[4] == "BBB") {
                if (n == "newuser") {
                    $1("registration_div").style.display = "block";
                    $1("useralreadyexist").style.display = "block"
                } else {
                    if (r == "DP") {
                        openDPForm(r, "useralreadyexist", i[0])
                    } else if (r == "ReviewPage") {
                        submitReviewPage(r, "useralreadyexist", i[0])
                    } else {
                        $1("qlform1").style.display = "block";
                        $1("step2").style.display = "none";
                        $1("useralreadyexist").style.display = "block"
                    }
                }
            } else {
                if (n == "existuser") {
                    if ($1("returninguserid") != null) {
                        $1("returninguserid").style.display = "none"
                    }
                    showForgotPassword("show");
                    if ($1("eMail") != null) {
                        $1("eMail").readonly = "true"
                    }
                } else {
                    if (i[1] != null && i[1] == "notregister") {
                        if (r == "DP") {
                            openDPForm(r, "sucessuser", i[0])
                        } else if (r == "ReviewPage") {
                            submitReviewPage(r, "sucessuser", i[0])
                        } else {
                            $1("qlform1").style.display = "none";
                            $1("step2").style.display = "block"
                        }
                    } else {
                        if (i[1] != null && i[1] == "notregister") {
                            alert("insdie not register function if")
                        } else {
                            if ($1("hiduserid") != null) {
                                $1("hiduserid").value = i[0]
                            }
                            if ($1("firstName") != null) {
                                $1("firstName").value = i[1]
                            }
                            if ($1("lastName") != null) {
                                $1("lastName").value = i[2]
                            }
                            if ($1("yournationality") != null) {
                                if (i[3] != null) {
                                    $1("yournationality").value = i[3]
                                }
                            } else {
                                if ($1("yournationality") != null) {
                                    $1("yournationality").value = "-"
                                }
                            }
                            if ($1("phonetype") != null) {
                                if (i[5] != null) {
                                    $1("phonetype").value = i[5]
                                }
                            }
                            if ($1("code") != null) {
                                if (i[6] != null) {
                                    $1("code").value = i[6]
                                }
                            }
                            if ($1("phoneNumber") != null) {
                                if (i[7] != null && i[7] != "null") {
                                    $1("phoneNumber").value = i[7]
                                }
                            }
                            if (r == "DP") {
                                openDPForm(r, "sucessuser", i[0]);
                                proceedstep2Dp("ql_sub1")
                            } else if (r == "ReviewPage") {
                                submitReviewPage(r, "sucessuser", i[0]);
                                return true
                            } else {
                                if (n != null && (n != "newformuser" || (n = "newformuser" && r == "MAS"))) {
                                    document.location.reload(true)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (i[0] != null && i[0] == "AAA") {
                if (n == "existuser") {
                    $1("login_div").style.display = "block";
                    $1("notanexistalert").style.display = "block"
                } else {
                    if (r == "ReviewPage") {
                        submitReviewPage(r, "usernotexist", i[0])
                    } else {
                        $1("ql_sub1").style.display = "block";
                        $1("step2").style.display = "none";
                        $1("notanexistalert").style.display = "block"
                    }
                }
            } else if (i[4] == null || $1("passwordVal").value.toLowerCase() != i[4].toLowerCase()) {
                if (n == "existuser") {
                    $1("login_div").style.display = "block";
                    $1("passalert").style.display = "block";
                    $1(n).style.display = "block"
                } else {
                    if (r == "DP") {
                        $1("passnotrecognised").style.display = "block"
                    } else if (r == "ReviewPage") {
                        submitReviewPage(r, "passnotrecognised", i[0]);
                        return false
                    } else {
                        $1("ql_sub1").style.display = "block";
                        $1("passnotrecognised").style.display = "block";
                        $1("step2").style.display = "none"
                    }
                }
            } else {
                if (n == "existuser") {
                    if ($1("returninguserid") != null) {
                        $1("returninguserid").style.display = "none"
                    }
                    showForgotPassword("show");
                    if ($1("eMail") != null) {
                        $1("eMail").readonly = "true"
                    }
                } else {
                    if (r == "DP") {
                        openDPForm(r, "reguser", i[0])
                    } else if (r == "ReviewPage") {
                        submitReviewPage(r, "sucessuser", i[0])
                    } else {}
                }
                $1("ql_sub1").style.display = "none";
                $1("step2").style.display = "block";
                if ($1("hiduserid") != null) {
                    $1("hiduserid").value = i[0]
                }
                if (n == "existuser" || n == "registereduser" && r == "MAS") {
                    if ($1("firstName") != null) {
                        $1("firstName").value = i[1]
                    }
                    if ($1("lastName") != null) {
                        $1("lastName").value = i[2]
                    }
                    if ($1("yournationality") != null) {
                        if (i[3] != null) {
                            $1("yournationality").value = i[3]
                        }
                    }
                    if ($1("phonetype") != null) {
                        if (i[5] != null) {
                            $1("phonetype").value = i[5]
                        }
                    }
                    if ($1("code") != null) {
                        if (i[6] != null) {
                            $1("code").value = i[6]
                        }
                    }
                    if ($1("phoneNumber") != null) {
                        if (i[7] != null && i[7] != "null") {
                            $1("phoneNumber").value = i[7]
                        }
                    }
                    document.location.reload(true)
                } else {
                    if (i[3] != null) {
                        if (i[3] == "-") {
                            document.location.reload(true)
                        } else {
                            if ($1("yournationality") != null) {
                                $1("yournationality").value = i[3]
                            }
                            if ($1("nationalitynew") != null) {
                                $1("nationalitynew").value = i[3]
                            }
                        }
                    }
                    if ($1("fstname") != null) {
                        $1("fstname").value = i[1]
                    }
                    if ($1("lstname") != null) {
                        $1("lstname").value = i[2]
                    }
                    if ($1("confirmEmailId") != null) {
                        if (i[8] != null) {
                            $1("confirmEmailId").value = i[8]
                        }
                    }
                }
            }
        }
    }
}
function validateEnquiryLogin(e) {
    if (trim($1("eMail").value) == "" || trim($1("eMail").value).length == 0 || trim($1("eMail").value) == $1("cnfemail").value) {
        $1("emailalertalert").style.display = "block";
        $1("eMail").focus();
        return false
    }
    if ($1("enquirystudylevelid2") != null) {
        if ($1("enquirystudylevelid2").value == "none") {
            $1("studyleveldiv2").style.display = "block";
            $1("enquirystudylevelid2").focus();
            return false
        }
    }
    if ($1("enquirySubjectId2") != null) {
        if ($1("enquirySubjectId2").value == "none") {
            if ($1("catsubjectdropdown") != null) {
                $1("catsubjectdropdown").style.display = "block"
            }
            if ($1("catsubjectdropdown1") != null) {
                $1("catsubjectdropdown1").style.display = "block"
            }
            $1("enquirySubjectId2").focus();
            return false
        }
    }
    if (echeck("eMail") == false) {
        $1("validemailalertalert").style.display = "block";
        $1("eMail").focus();
        return false
    }
    if (e == "newuser") {
        if ($1("confirmEmailId").value == "" || $1("confirmEmailId").value == $1("cnfemail").value) {
            $1("confirmemailalertalert").style.display = "block";
            $1("confirmEmailId").focus();
            return false
        }
        if (echeck("confirmEmailId") == false) {
            $1("validconfirmemailalertalert").style.display = "block";
            $1("confirmEmailId").focus();
            return false
        }
        if ($1("confirmEmailId").value.toLowerCase() != $1("eMail").value.toLowerCase()) {
            $1("sameemailalertalert").style.display = "block";
            $1("confirmEmailId").focus();
            return false
        }
        if (trim($1("fname").value) == "" || trim($1("fname").value).length == 0 || trim($1("fname").value) == $1("fnameId").value) {
            $1("fnamealert").style.display = "block";
            $1("fname").focus();
            return false
        }
        if (trim($1("lname").value) == "" || trim($1("lname").value).length == 0 || trim($1("lname").value) == $1("lnameid").value) {
            $1("lnamealert").style.display = "block";
            $1("lname").focus();
            return false
        }
        if ($1("passwordValue") != null) {
            if (trim($1("passwordValue").value) == "" || trim($1("passwordValue").value).length == 0 || trim($1("passwordValue").value) == $1("pwdid").value) {
                $1("passwordValuealert").style.display = "block";
                $1("passwordValue").value = "";
                $1("passwordValue").focus();
                return false
            }
            if (trim($1("passwordValue").value).length < 6) {
                $1("passwordValuelengthalert").style.display = "block";
                $1("passwordValue").focus();
                return false
            }
        }
    } else {
        if ($1("passwordVal") != null) {
            if (trim($1("passwordVal").value) == "" || trim($1("passwordVal").value).length == 0 || trim($1("passwordVal").value) == $1("pwddefId").value) {
                $1("passwordValalert").style.display = "block";
                $1("passwordVal").value = "";
                $1("passwordVal").focus();
                return false
            }
        }
    }
}
function preventEnterKey(e, t) {
    var n;
    if (window.event) {
        n = window.event.keyCode
    } else {
        n = e.which
    }
    if (n == 13) {
        if (t != "enquiry") {
            document.getElementById(t).click()
        }
        return false
    }
}
function clearErrorMessages(e) {
    if ($1(e) != null) {
        $1(e).style.display = "none"
    }
}
function nonLocalLanguage(e, t) {
    var n;
    var r;
    if ($1(e) != null) {
        n = $1(e).value;
        if (e == "enquiry") {
            r = $1("providerNameId").value;
            if (n != null) {
                n = n.replace(r, "")
            }
        }
    }
    var i = null;
    if (e == "enquiry") {
        i = /^[\-a-zA-Z0-9\s\.\,\:\[\]\`\;\~\''\""\-\@\#\?\%\/\=\&\^\{\}\<\>\$\*\|\!\(\)\-\+\_\\]*$/
    } else {
        i = /^[\-a-zA-Z\s]*$/
    }
    if (n.match(i)) {
        $1(t).style.display = "none";
        return true
    } else {
        if (e == "enquiry") {
            $1("splcharquestionerror").style.display = "block";
            if ($1("enquirydeftext") != null) {
                $1(e).value = $1("enquirydeftext").value
            }
        } else {
            if (e == "firstName" || e == "fname" || e == "regfirstName") {
                $1(t).style.display = "block"
            } else {
                $1(t).style.display = "block"
            }
            $1(e).value = ""
        }
        return false
    }
}
function showmydetails(e, t) {
    if ($1(e) != null) {
        $1(e).style.display = "block"
    }
    if ($1(t) != null) {
        $1(t).style.display = "none"
    }
}
function loadcategory(e, t, n, r, i, s, o, u, a, f) {
    var l = ajax_list_externalFile + "?" + t + "=1&from=" + i + "&studylvl=" + e + "&cid=" + s + "&profileid=" + o + "&keyword=" + u + "&catcode=" + a + "&suborderItem=" + f;
    var c = ajax_list_objects.length;
    ajax_list_objects[c] = new sack;
    currentListIndex++;
    var h = currentListIndex / 1;
    ajax_list_objects[c].requestFile = l;
    ajax_list_objects[c].onCompletion = function() {
        ajax_subject_list_Content(c, "", r, h)
    };
    ajax_list_objects[c].runAJAX()
}
function ajax_subject_list_Content(e, t, n, r) {
    if (r != currentListIndex) return;
    var i = ajax_list_objects[e].response;
    if ($1("enquirySubjectId") != null) {
        $1("enquirySubjectId").innerHTML = null
    }
    if ($1("ldcscode") != null) {
        $1("ldcscode").innerHTML = null;
        $1("ldcscode").innerHTML = i
    }
    if ($1("oneCate") != null) {
        if ($("#subjectList_arg1") != null) {
            $("#subjectList_arg1").html($("#oneCate").html() + "<span class='arw'></span>")
        }
    }
    if ($1("courseTitleForId") != null) {} else {
        if (document.getElementById("keywordid") != null) {
            if ($1("enquirystudylevelid") != null) {
                if ($1("enquirySubjectId") != null) {} else {}
            }
            $1("labelldcscode").style.display = "none";
            $1("ldcscode").style.display = "none"
        }
        if ($1("enquirystudylevelid") != null) {
            if ($1("enquirySubjectId") != null) {}
        }
    }
}
function sameTickerTime() {
    if (tickerWidth == $1("tapetext").offsetWidth) {
        clearTimeout(timer);
        showTickerTimer = setTimeout("showTicker()", 1e3);
        return
    }
    $1("revealtext").style.marginLeft = tickerWidth + 1 + "px";
    $1("revealtext").style.width = $1("tapetext").offsetWidth - tickerWidth + "px";
    tickerWidth++;
    if (navigator.appVersion.indexOf("Chrome") != -1) {
        speed = 20
    } else {
        speed = 1
    }
    timer = setTimeout("sameTickerTime()", speed)
}
function revealTickerAtATime() {
    clearTimeout(timer);
    clearTimeout(showTickerTimer);
    $1("revealtext").style.marginLeft = $1("tapetext").offsetWidth + "px";
    $1("revealtext").style.width = 0 + "px"
}
function tickerScroll() {
    if (!startPoint) {
        if ($1("tickerBody") != null) {
            $1("tickerBody").innerHTML += $1("tickerBody").innerHTML;
            $1("tickerBody").scrollTop = 0
        }
        if (200 > lineHeight * 3) {
            startPoint = setTimeout("tickerScroll()", timeDelay)
        } else {
            $1("tickerTape").onmouseover = $1("tickerTape").onmouseout = null
        }
        return
    }
    if (tickerCount == lineHeight) {
        if ($1("tickerBody") != null) {
            if ($1("tickerBody").scrollHeight - lineHeight <= $1("tickerBody").scrollTop) {
                $1("tickerBody").scrollTop = $1("tickerBody").scrollHeight / 2 - lineHeight
            }
        }
        tickerCount = 0;
        startPoint = setTimeout("tickerScroll()", timeDelay)
    } else {
        if ($1("tickerBody") != null) {
            $1("tickerBody").scrollTop++
        }
        tickerCount++;
        startPoint = setTimeout("tickerScroll()", 10)
    }
}
function placeHolder(e, t, n) {
    var r = $1(t);
    var i = $1(n).value;
    if (r.value.trim() == "" && e.type == "blur") {
        r.value = i
    } else if (r.value.trim() == i.trim()) {
        r.value = ""
    }
}
function toggleMenu(e, t) {
    if (document.getElementById(e).style.display == "none") {
        document.getElementById(e).style.display = "block";
        document.getElementById(t).className = "rda"
    } else {
        document.getElementById(e).style.display = "none";
        document.getElementById(t).className = "rda act"
    }
}
function universityshowActive(e, t) {
    for (var n = 1; n <= 4; n++) {
        if (e == "t" + n) {
            if ($1(e).className == "res_ftr wduni") {
                $1(e).className = "res_ftr wduni act"
            } else {
                $1(e).className = "res_ftr act"
            }
        } else {
            if ($1(e).className == "res_ftr wduni act") {
                $1(e).className = "res_ftr wduni"
            } else {
                $1("t" + n).className = "res_ftr";
                $1("t4").className = "res_ftr wduni"
            }
        }
    }
    $1("subjectId").value = "";
    $1("selectedvalue").value = t
}
function submitDetails() {
    var e = null;
    var t = $1("selectedvalue").value;
    var n = $1("countryId").value;
    if ($1("subjectId") != null && $1("notselect").className != "selBx disable") {
        e = trim($1("subjectId").value)
    }
    if ($1("countryId").value != null && $1("countryId").value != "") {
        var r = n.split("-");
        var i = "/study/" + r[1] + "/international/schools-colleges-university/" + trim(r[0]) + "/list.html";
        if (t != null && trim($1("selectedvalue").value) != "") {
            i = i + "?sortby=" + t;
            if ($1("subjectId").value != null && trim($1("subjectId").value) != "" && $1("notselect").className != "selBx disable") {
                i = i + "&subjectId=" + e
            }
        }
    } else {
        var i = "/study" + "/international/schools-colleges-university/" + "list.html";
        if (t != null && trim($1("selectedvalue").value) != "") {
            i = i + "?sortby=" + t;
            if ($1("subjectId").value != null && trim($1("subjectId").value) != "") {
                i = i + "&subjectId=" + e
            }
        }
    }
    window.location.href = i
}
function checkrankid(e) {
    var t = e.getElementsByTagName("option");
    for (var n = 0; n < t.length; n++) {
        if (t[n].selected == true) {
            if (t[n].value == 2) {
                $1("subjectId").disabled = false;
                $1("notselect").className = "selBx"
            } else {
                $1("subjectId").disabled = true;
                $1("notselect").className = "selBx disable"
            }
        }
    }
}
function populateSelectedRank(e) {
    $1("timesgoodnoselect").className = "selBx";
    $1("timesgoodrankId").disabled = false;
    var t = e.getElementsByTagName("option");
    for (var n = 0; n < t.length; n++) {
        if (t[n].selected == true) {
            var r = t[n].value;
            var i = r.split("-");
            if (i[0] == "210") {
                if ($1("timesgood").style.display == "block") {
                    $1("timesgood").style.display = "none"
                }
                $1("timesrank").style.display = "block";
                if ($1("selectedvalue").value == "") {
                    $1("selectedvalue").value = $1("timesrankId").value
                }
                if ($1("timesrankId") != null && $1("timesrankId").value == 2) {
                    $1("subjectId").disabled = false;
                    $1("notselect").className = "selBx"
                }
            } else if (i[0] != "210") {
                if ($1("timesrank").style.display == "block") {
                    $1("timesrank").style.display = "none"
                }
                $1("timesgood").style.display = "block";
                if ($1("selectedvalue").value == "") {
                    $1("selectedvalue").value = $1("timesgoodrankId").value
                }
                $1("subjectId").disabled = true;
                $1("notselect").className = "selBx disable";
                if (i[0] == "114") {
                    $1("timesgoodnoselect").className = "selBx disable";
                    $1("timesgoodrankId").disabled = true;
                    if ($1("selectedvalue").value == "1" || $1("selectedvalue").value == "2") {
                        $1("selectedvalue").value = ""
                    }
                }
                if ($1("selectedvalue").value == "2") {
                    $1("selectedvalue").value = ""
                }
            }
        }
    }
}
function getRankComboValue(e) {
    $1("selectedvalue").value = e.value
}
function loginServlet(e, t, n, r) {
    var i = false;
    if (trim($1(e).value).length == 0 || $1(e).value == "Email address") {
        $1("emailadderror").style.display = "block";
        i = true
    }
    if (trim($1(t).value).length == 0 || $1(t).value == "Password") {
        $1("passworderror").style.display = "block";
        i = true
    }
    if (i) {
        $1("loginerrormsg").innerHTML = "";
        return false
    }
    if (echeck(e) == false) {
        $1("error").style.display = "block";
        $1("loginerrormsg").innerHTML = $1("erroremail").value;
        return false
    }
    var s;
    if ($1("logpod") != null) {
        s = $1("logpod").value
    }
    var o = "/study";
    var u = o + "/login.html";
    var a = "pod=" + s + "&password=" + encodeURIComponent(trim($1(t).value)) + "&userName=" + trim($1(e).value) + "&remchk=" + $1(r).checked;
    var f = new sack;
    f.requestFile = u;
    f.URLString = a;
    f.onCompletion = function() {
        checkResponse(f, t, e, s, n)
    };
    f.runAJAX("", false);
    firstTime = true
}
function checkResponse(e, t, n, r, i) {
    if (e.response == "Invalid user or password") {
        $1(t).value = "";
        $1("error").style.display = "block";
        $1("loginerrormsg").innerHTML = $1("errorinvalid").value;
        return false
    } else if (e.response == "success") {
        window.location.href = $1("previousurl").value
    } else {
        if (r != null) {
            self.location = e.response
        } else {
            $1("login").innerHTML = e.response;
            setTimeout("getBasketCount()", 500)
        }
    }
}
function loadcategory2(e, t, n, r, i, s, o, u, a, f, l) {
    var c = ajax_list_externalFile + "?" + t + "=1&from=" + i + "&studylvl=" + e + "&cid=" + s + "&profileid=" + o + "&keyword=" + u + "&catcode=" + a + "&suborderItem=" + f + "&divid=" + r;
    var h = ajax_list_objects.length;
    ajax_list_objects[h] = new sack;
    currentListIndex++;
    var p = currentListIndex / 1;
    ajax_list_objects[h].requestFile = c;
    ajax_list_objects[h].onCompletion = function() {
        ajax_subject_list_Content2(h, "", r, p, l)
    };
    ajax_list_objects[h].runAJAX()
}
function ajax_subject_list_Content2(e, t, n, r, i) {
    var s = ajax_list_objects[e].response;
    if ($1(n) != null) {
        $1(n).innerHTML = null
    }
    if ($1(i) != null) {
        $1(i).innerHTML = null;
        $1(i).innerHTML = s;
        $1(i).style.display = "block"
    }
    if ($1("oneCate") != null) {
        if ($("#subjectList_arg1") != null) {
            $("#subjectList_arg1").html($("#oneCate").html() + "<span class='arw'></span>")
        }
        if ($("#subjectList_arg2") != null) {
            $("#subjectList_arg2").html($("#oneCate").html() + "<span class='arw'></span>")
        }
    }
    if ($1("courseTitleForId") != null) {} else {
        if (document.getElementById("keywordid") != null) {
            if ($1(n) != null) {
                if ($1(n) != null) {} else {}
            }
            $1("labelldcscode").style.display = "none";
            $1("ldcscode").style.display = "none"
        }
        if ($1(n) != null) {
            if ($1(n) != null) {}
        }
    }
}
function closeCommonLighBox(e, t) {
    $1(e).style.display = "none";
    $1(t).style.display = "none"
}
function popups(e) {
    window.open("/study/" + e, "", "height=550,width=750,scrollbars=yes,resizable=yes")
}
function defaultClose() {
    if ($1("e2") != null) {
        $1("e2").style.display = "none";
        $1("e1").className = "rda act"
    }
    if ($1("e4") != null) {
        $1("e4").style.display = "none";
        $1("e3").className = "rda act"
    }
    if ($1("e6") != null) {
        $1("e6").style.display = "none";
        $1("e5").className = "rda act"
    }
    if ($1("e8") != null) {
        $1("e8").style.display = "none";
        $1("e7").className = "rda act"
    }
    if ($1("e10") != null) {
        $1("e10").style.display = "none";
        $1("e9").className = "rda act"
    }
}
function showForgotpwd() {
    $1("forgotpwdd").style.display = "block";
    if ($1("ql_sub1")) {
        $1("ql_sub1").style.display = "none"
    }
}
function validateScholarshipPage() {
    var e = "N";
    if (document.getElementById("countryCoumbo").value == "scon") {
        $1("locationList_sr11").parentNode.className = "selBx err";
        e = "Y"
    }
    if (document.getElementById("catCoumbo").value == "sub") {
        $1("locationList_sr12").parentNode.className = "selBx err";
        e = "Y"
    }
    if ($1("levelCoumbo") != null && document.getElementById("levelCoumbo").value == "stdlvl") {
        $1("locationList_sr13").parentNode.className = "selBx err";
        e = "Y"
    }
    if (e == "Y") {
        return false
    } else {
        return true
    }
}
function tickerstats(e, t, n, r, i) {
    JSstats("HOTCOURSES: TICKER TAPE: CLICK", e, t, null, n, null, r, null, null, null, null, i)
}
function defaultValueScholarshipPage() {
    var e = document.getElementById("countryCoumbo");
    var t = e.options[e.selectedIndex].text;
    document.getElementById("locationList_sr11").innerHTML = t + '<span class="arw"></span>';
    var n = document.getElementById("catCoumbo");
    var r = n.options[n.selectedIndex].text;
    document.getElementById("locationList_sr12").innerHTML = r + '<span class="arw"></span>';
    var i = document.getElementById("levelCoumbo");
    var s = i.options[i.selectedIndex].text;
    document.getElementById("locationList_sr13").innerHTML = s + '<span class="arw"></span>'
}
function assignDefaultValue(e, t, n, r) {
    document.getElementById("livingnat").value = $1(e).value;
    document.getElementById("studynat").value = $1(t).value;
    document.getElementById("cat").value = $1(n).value;
    document.getElementById("stdlvid").value = $1(r).value
}
function clearDefaultData(e) {
    var t = document.getElementById(e);
    var n = document.getElementById("txtAreaDefaultTxt").value;
    if (t.value == n) {
        t.value = ""
    }
}
function resetData(e) {
    var t = document.getElementById(e);
    var n = document.getElementById("txtAreaDefaultTxt").value;
    if (t.value == "") {
        t.value = n
    }
}
function setAttributeValue(e) {
    document.getElementById("selectedAttributeId").value = document.getElementById(e).value;
    if (e == "attributeId_1") {
        document.getElementById("txtArea").style.display = "block"
    } else {
        document.getElementById("txtArea").style.display = "none"
    }
}
function validateUnsubscribeForm() {
    var e = document.getElementById("selectedAttributeId").value;
    var t = document.getElementById("txtAreaDefaultTxt").value;
    var n = document.getElementById("txtArea").value;
    var r = document.getElementById("optionAlertMsg").value;
    var i = document.getElementById("txtSlertMsg").value;
    if (e == "") {
        alert(r);
        return false
    } else if (e == "253" && n == t) {
        alert(i);
        return false
    } else if (n.trim().length == 0) {
        alert(i);
        document.getElementById("txtArea").value = t;
        return false
    }
}
function allSubjectPage(e) {
    var t = document.getElementById("countryCombo");
    var n = t.options[t.selectedIndex].text;
    document.getElementById("locationList_sr10").innerHTML = n + '<span class="arw"></span>';
    var r = document.getElementById("studyCombo");
    var i = r.options[r.selectedIndex].text;
    if (i == $1(e).value) {
        document.getElementById("studyCombo").selectedIndex = 1;
        var s = r.options[r.selectedIndex].text;
        document.getElementById("locationList_sr5").innerHTML = s + '<span class="arw"></span>'
    } else {
        document.getElementById("locationList_sr5").innerHTML = i + '<span class="arw"></span>'
    }
}
function defaultValueProviderReview(e) {
    var t = new Array;
    var n = document.getElementById("sortId");
    for (var r = 0; r < n.options.length; r++) {
        t.push(n.options[r].value);
        if (n.options[r].value == $1(e).value) {
            document.getElementById("sortId").selectedIndex = r;
            var i = n.options[n.selectedIndex].text;
            document.getElementById("locationList_sr22").innerHTML = i + '<span class="arw"></span>'
        }
    }
}
function submitForm(e, t, n) {
    $1(n).value = $1(t).value;
    $1(e).submit()
}
function prepopulateUnilistCombo() {
    var e = ["countryId", "timesrankId", "timesgoodrankId", "subjectId"];
    for (var t = 0; t < e.length; t++) {
        var n = document.getElementById(e[t]);
        if (e[t] == "countryId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr10").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        } else if (e[t] == "timesrankId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr5").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        } else if (e[t] == "timesgoodrankId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr7").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        } else if (e[t] == "subjectId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr6").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function onloaddivopen(e) {
    if (e == "210") {
        $1("timesrank").style.display = "block"
    } else if (e != "210") {
        if (e == "114") {
            $1("timesgoodnoselect").className = "selBx disable";
            $1("timesgoodrankId").disabled = true
        }
        $1("timesgood").style.display = "block"
    }
    if ($1("timesrankId").value == 2) {
        $1("subjectId").disabled = false;
        $1("notselect").className = "selBx"
    }
}
function setValueEbook_AMW(e, t, n) {
    if ($$$("yournationality") != null) {
        $$$("onErr").className = "selBx"
    }
}
function ebookValidation() {
    var e = "";
    if ($1("name").value.trim() == "" || $1("name").value == $1("deftext_name").value) {
        $1("name").className = "eror";
        e = "error"
    }
    if ($1("lname").value.trim() == "" || $1("lname").value == $1("deftext_lname").value) {
        $1("lname").className = "eror";
        e = "error"
    }
    if ($1("email").value.trim() == "" || $1("email").value == $1("deftext_email").value) {
        $1("email").className = "eror";
        e = "error"
    } else if (regEcheck($1("email").value) == false) {
        $1("email").className = "eror";
        e = "error"
    }
    if ($1("yournationality").value == "-") {
        $1("onErr").className = "selBx eror";
        e = "error"
    }
    if (e != "") {
        return false
    } else {
        $1("ebookFormId").submit();
        return true
    }
}
function clearErrorMsgs(e) {
    $1(e).className = ""
}
function regEcheck(e) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(trim(e))) {
        return true
    }
    return false
}
function clearValue(e) {
    $1(e).value = ""
}
function hideshowcompareDiv(e, t, n, r) {
    $1(e).style.display = "block";
    $1(t).className = "active";
    $1(n).style.display = "none";
    $1(r).className = ""
}
function logStats(e, t, n, r, i, s, o) {
    var u = "#sec-" + i;
    if ($(u).css("display") == "none") {
        JSstats(n, e, t, i, s, o, "null", "null", "null", "null", $1("reqsid").value, $1("refrid").value, r)
    }
}
function eflAdClick(e, t, n, r, i, s) {
    var o = queryString("url", e);
    eflJSstats(t, s, n, null, null, r, i, o)
}
function eflWebLinkClick(e, t, n, r, i, s) {
    eflJSstats(n, r, t, i, s, null, null, null)
}
function eflJSstats(e, t, n, r, i, s, o, u) {
    var a = "/study";
    var u = a + "/efljsstats.html?collegeId=" + e + "&countryId=" + t + "&affID=" + n + "&eflflag=" + r + "&courseId=" + i + "&sectionId=" + s + "&keyId=" + o + "&url=" + u;
    var f = new sack;
    f.requestFile = u;
    f.runAJAX()
}
function closetopsearchdiv(e) {
    if (e != null) {
        if ($1(e).style.display == "block" && $1("ajax_listOfOptions") != null && $1("ajax_listOfOptions").style.display == "block") {
            $1(e).style.display = "none";
            $1("ajax_listOfOptions").style.display = "none";
            $1("searchlink").className = "icn"
        } else {
            if ($1(e).style.display == "block") {
                $1(e).style.display = "none";
                $1("searchlink").className = "icn"
            }
        }
    }
}
function validateSurveyForm() {
    if ($1("surveycollegeId") != null) {
        if ($1("surveycollegeId").value == $1("defaultcollegename").value) {
            $1("surveycollegeId_error_msg").style.display = "block";
            return false
        }
    }
    if ($1("surveycourseId") != null) {
        if ($1("surveycourseId").value == $1("defaultcoursename").value) {
            $1("surveycourseId_error_msg").style.display = "block";
            return false
        }
    }
    if ($1("num0") != null || $1("num1") != null) {
        if ($1("num0").checked == false && $1("num1").checked == false) {
            $1("fnamealert3_error_msg").style.display = "block";
            return false
        }
    }
    if (checkValue("college_hidden", "")) {
        $1("surveycollegeId_error_msg").style.display = "block";
        return false
    }
    var e;
    if ($1("surveycourseId") != null) {
        e = $1("surveycourseId").value;
        var t = /^[\-a-zA-Z0-9\s\.\,\:\[\]\`\;\~\''\""\-\@\#\?\%\/\=\&\^\{\}\<\>\$\*\|\!\(\)\-\+\_\\]*$/;
        if (!e.match(t)) {
            $1("surveycourseId_error_msg").style.display = "block";
            return false
        }
    }
    return true
}
function showMe(e, t) {
    var n = document.getElementsByName(t);
    var r = "none";
    for (var i = 0; i < n.length; i++) {
        if (n[i].checked) {
            r = "block";
            break
        }
    }
    document.getElementById(e).style.display = r
}
function hideMe(e, t) {
    var n = document.getElementsByName(t);
    for (var r = 0; r < n.length; r++) {
        if (n[r].checked) {
            n[r].checked = false
        }
    }
    if (document.getElementById(e).style.display == "block") {
        document.getElementById(e).style.display = "none"
    }
}
function validateSurveySearchForm(e, t) {
    var n = e.id + "_error_msg";
    var r;
    if (e.value != null) {
        r = e.value;
        var i = /^[\-a-zA-Z0-9\s\.\,\:\[\]\`\;\~\''\""\-\@\#\?\%\/\=\&\^\{\}\<\>\$\*\|\!\(\)\-\+\_\\]*$/;
        if (!r.match(i)) {
            e.value = t;
            $1(n).style.display = "block";
            return false
        }
        if (r.length > 255) {
            e.value = t;
            $1(n).style.display = "block";
            return false
        }
    }
    return true
}
function clearData(e, t) {
    if (e == "courseTitle" && $1("courseTitle").value == $1("ectitle").value) {
        $1(e).value = "";
        $1(t).value = ""
    } else if (e == "collegeName") {
        $1(e).value = $1("euni").value
    } else if (e == "college") {
        $1(e).value = $1("euni").value;
        $1(t).value = ""
    }
}
function validate(e, t, n, r, i, s) {
    if ($1("unifilter") != null) {
        s = $1("unifilter").value
    }
    if (n == "clear") {
        $1(i).value = ""
    }
    if ($1(e).value == "Select country" || $1(e).value == " " || $1(e).value.length == 0 || $1(e).value == "selected") {
        $1(e).focus();
        alert($1("countrymsg").value);
        return false
    }
    if (n == "dontclear") {
        if (s != "N") {
            if (trim($1(r).value).length == 0) {
                alert($1("unimsg").value);
                $1(r).focus();
                return false
            }
            if (trim($1(i).value).length == 0) {
                alert($1("autounimsg").value);
                $1(r).focus();
                return false
            }
        }
    }
    return true
}
function noNavigationStatus(e) {
    var t = null;
    t = e.value;
    $1("navigationStatus").value = t
}
function prepopulateNationality() {
    var e = ["yournationality"];
    for (var t = 0; t < e.length; t++) {
        var n = document.getElementById(e[t]);
        if (e[t] == "yournationality") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_rg1").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function prepopulateReview() {
    var e = ["yournationality", "gradyear", "reviewstudyLevelId", "cName", "ambassadorId"];
    for (var t = 0; t < e.length; t++) {
        var n = document.getElementById(e[t]);
        if (e[t] == "yournationality") {
            if (n != null) {
                if (document.getElementById("locationList_stp21") != null) {
                    if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                        document.getElementById("locationList_stp21").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                    }
                }
            }
        }
        if (e[t] == "gradyear") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_stp2").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
        if (e[t] == "reviewstudyLevelId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_stp1").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
        if (e[t] == "cName") {
            if (n != null) {
                if (document.getElementById("locationList_stp111") != null) {
                    if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                        document.getElementById("locationList_stp111").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                    }
                }
            }
        }
        if (e[t] == "ambassadorId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_stp4").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function clearajaxtextvalue(e) {
    if ($1(e)) {
        if ($1(e).value != null) {
            $1(e).value = ""
        }
    }
}
function uploadImage(e) {
    if (e != null) {
        if (e == "c") {
            document.getElementById("browseFile").value = ""
        }
        $1("uploadImageDiv").style.display = "none"
    } else {
        $1("uploadImageDiv").style.display = "block"
    }
}
function checkImageFromat() {
    var e = new Array("jpg", "jpeg", "gif", "png");
    var t = document.getElementById("browseFile").value;
    if (t.length > 0) {
        var n = t.lastIndexOf(".") + 1;
        var r = t.substring(n, t.image_length);
        var s = r.toLowerCase();
        for (i = 0; i < e.length; i++) {
            if (e[i] == s) {
                return true
            }
        }
        alert("You must upload an image file with one of the following extensions: " + e.join(", ") + ".");
        return false
    } else {
        alert("Please choose a file to upload");
        return false
    }
}
function imageuploadErrorMsg() {
    document.getElementById("uploadImgalert").innerHTML = "File size should be less than 2 MB"
}
function switchHeight(e, t) {
    if (e.style.height == "auto") {
        e.style.height = "10px";
        t.innerHTML = "more"
    } else {
        e.style.height = "auto";
        t.innerHTML = $1("forlesstext").value
    }
}
function ajaxtopsearchpodsmobile(e) {
    var t = contextPath + "/ajaxTopSearch.html";
    if ($1(e).innerHTML.trim() == 0) {
        var n = new sack;
        n.requestFile = t;
        $1("loidingImagemobile").style.display = "block";
        n.onCompletion = function() {
            ajaxTopSearchFormMobile(n, e)
        };
        n.runAJAX()
    } else {
        $1(e).style.display = "block"
    }
}
function ajaxTopSearchFormMobile(ajax, id) {
    if ($1(id)) {
        $1("loidingImagemobile").style.display = "none";
        $1(id).innerHTML = ajax.response;
        $1(id).style.display = "block";
        eval($1("slideLoad").innerHTML)
    }
}
function enabledisableDeviceDesktop() {
    if ($(window).width() < 769) {
        $1("searchlink").style.display = "none";
        $1("classadd1").style.display = "block";
        $1("desktop2").style.display = "none";
        $1("classadd2").style.display = "block";
        $1("inerdevice1").style.display = "block";
        $1("desktop3").style.display = "none";
        $1("classadd3").style.display = "block"
    } else {
    	if($1("searchlink") != null){
	        $1("searchlink").style.display = "block";
	        $1("classadd1").style.display = "none";
	        $1("desktop2").style.display = "block";
	        $1("classadd2").style.display = "none";
	        $1("inerdevice1").style.display = "none";
	        $1("desktop3").style.display = "block";
	        $1("classadd3").style.display = "none"
    	}
    }
}
function toptoggle(e, t, n, r) {
    if (screen.width <= 800) {
        if (document.getElementById(t).className == "icn act" || document.getElementById(t).className == "act") {
            document.getElementById(e).style.display = "none";
            if (t == "classadd3") {
                document.getElementById(t).className = ""
            } else {
                document.getElementById(t).className = "icn"
            }
        } else {
            if (document.getElementById(t).className == "icn" || document.getElementById(t).className == "") {
                document.getElementById(e).style.display = "block";
                if (t == "classadd3") {
                    document.getElementById(t).className = "act";
                    document.getElementById("classadd1").className = "icn";
                    document.getElementById("classadd2").className = "icn"
                } else if (t == "classadd2") {
                    document.getElementById(t).className = "icn act";
                    document.getElementById("classadd1").className = "icn";
                    document.getElementById("classadd3").className = ""
                } else if (t == "classadd1") {
                    document.getElementById(t).className = "icn act";
                    document.getElementById("classadd2").className = "icn";
                    document.getElementById("classadd3").className = ""
                }
            }
        } {
            document.getElementById(n).style.display = "none";
            document.getElementById(r).style.display = "none"
        }
    }
}
function opencloseinnerdiv(e, t, n, r, i, s) {
    if (screen.width <= 800) {
        if (document.getElementById(e).style.display == "block") {
            document.getElementById(e).style.display = "none";
            document.getElementById(r).className = "lnk"
        } else {
            document.getElementById(e).style.display = "block";
            document.getElementById(r).className = "lnk actv"
        }
        document.getElementById(t).style.display = "none";
        document.getElementById(i).className = "lnk";
        document.getElementById(n).style.display = "none";
        document.getElementById(s).className = "lnk"
    }
}
function dvShow(e) {
    var t = ["popshw1", "durshw1", "priceshw1", "revshw1", "ranshw1"];
    for (var n = 0; n < t.length; n++) {
        if ($1(t[n]) != null) {
            if (t[n] == e) {
                if ($1(t[n]).style.display == "block") {
                    $1(t[n]).style.display = "none"
                } else {
                    $1(t[n]).style.display = "block"
                }
            } else {
                $1(t[n]).style.display = "none"
            }
        }
    }
}
function reviewlistvalidate(e, t, n, r, i, s) {
    if (n == "clear") {
        $1(i).value = ""
    }
    if ($1(e).value == "Select country" || $1(e).value == " " || $1(e).value.length == 0 || $1(e).value == "selected") {
        $1(e).focus();
        $1("locationList_sr4").parentNode.className = "selBx err";
        return false
    }
    if (n == "dontclear") {
        if (s != "N") {
            if ($1(r).value.length == 0) {
                $1(r).className = "c_txt grey err";
                $1(r).focus();
                return false
            }
            if (trim($1(i).value).length == 0) {
                $1(r).className = "c_txt grey err";
                $1(r).focus();
                return false
            }
        }
    }
    return true
}
function clearEventDefaultText(e, t) {
    if (e.value == t) {
        e.value = ""
    }
}
function setEventDefaultText(e, t) {
    if (isEmpty(e.id)) {
        e.value = t
    }
}
function prepopulateReviewListCombo() {
    var e = ["countrySeId"];
    for (var t = 0; t < e.length; t++) {
        var n = document.getElementById(e[t]);
        if (e[t] == "countrySeId") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr4").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function selectingDefaultReviewValue() {
    document.getElementById("countrySeId").selectedIndex = 0;
    document.getElementById("locationList_sr4").innerHTML = document.getElementById("countrymsg1").value + '<span class="arw"></span>';
    document.getElementById("college_hidden").value = ""
}
function defaultMessagesCityEvent(e, t) {
    if ($1(e) != null) {
        $1(e).value = $1(t).value
    }
}
function validateCourseTitle() {
    if (checkValue("courseTitle_hidden", "")) {
        alert("Please choose the course from the list in order to proceed");
        return false
    } else {
        return true
    }
}
function submitDpDetails() {
    if ($1("cName") != null) {
        var e = $1("cName").value;
        if ($1("cName").value != null && $1("cName").value != "-") {
            var t = "/study/" + e + "/university-prospectus.html"
        } else if ($1("cName").value.length == 0 || $1("cName").value == "-") {
            var t = "/study/international/university-prospectus.html"
        }
    }
    window.location.href = t
}
function prepopulateDPCountry() {
    var e = ["cName"];
    for (var t = 0; t < e.length; t++) {
        var n = document.getElementById(e[t]);
        if (e[t] == "cName") {
            if (n != null) {
                if (n.options[n.selectedIndex] != null && n.options[n.selectedIndex].value != "-1") {
                    document.getElementById("locationList_sr10").innerHTML = n.options[n.selectedIndex].text + '<span class="arw"></span>'
                }
            }
        }
    }
}
function selectingDefaultDPValue() {
    var e = window.location.href;
    var t = e.split("/")[4];
    var n = new Array;
    var r = document.getElementById("cName");
    for (var i = 0; i < r.options.length; i++) {
        n.push(r.options[i].value);
        if (r.options[i].value == t && r.options[i].value != "-") {
            document.getElementById("cName").selectedIndex = i;
            var s = r.options[r.selectedIndex].text;
            document.getElementById("locationList_sr10").innerHTML = s + '<span class="arw"></span>'
        } else if (r.options[i].value == "-") {
            document.getElementById("cName").selectedIndex = i;
            var s = r.options[r.selectedIndex].text;
            document.getElementById("locationList_sr10").innerHTML = s + '<span class="arw"></span>'
        }
    }
}
function gaInsightLog(e, t, n, r, i, s, s, s) {
    ga("newTracker.set", "dimension1", "webclick");
    ga("newTracker.set", "dimension2", $1("gaInsiteSiteName").value);
    if (n != null && n != "null" && n != "") {
        ga("newTracker.set", "dimension3", n)
    }
    if (r != null && r != "" && r != "null") {
        ga("newTracker.set", "dimension13", $1("gaCountryName").value.toUpperCase())
    }
    if ($1("gaUserIdLog") != null && $1("gaUserIdLog").value != null && $1("gaUserIdLog").value != "null") {
        ga("newTracker.set", "dimension15", $1("gaUserIdLog").value)
    }
    if (i != null && i != "" && i != "null") {
        ga("newTracker.set", "dimension16", "Advertiser")
    }
}
var contextPath = "/study";
$(document).ready(function() {
    $(".accordion .click:first").addClass("tab");
    $(".accordion .click").click(function() {
        $(this).next(".shw").slideToggle("slow");
        $(this).toggleClass("tab");
        $(this).siblings(".click").removeClass("tab")
    })
});
$(document).ready(function() {
    $(".tabc").not(":first").hide();
    $("ul.tbs li:first").addClass("acte").show();
    $("ul.tbs li").click(function() {
        $("ul.tbs li.acte").removeClass("acte");
        $(this).addClass("acte");
        $(".tabc").hide();
        $($("a", this).attr("href")).fadeIn("slow");
        return false
    })
});
$(document).ready(function() {
    $("#back-top").hide();
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 200) {
                $("#back-top").fadeIn()
            } else {
                $("#back-top").fadeOut()
            }
        });
        $("#back-top a").click(function() {
            $("body,html").animate({
                scrollTop: 0
            },
            800);
            return false
        })
    })
});
window.onscroll = function(e) {
    if (document.getElementById("back-top") != null) {
        var t = document.getElementById("back-top");
        var n = document.getElementsByTagName("body")[0].clientHeight;
        var r = n - 1203;
        var i = navigator.appVersion;
        if (i.indexOf("Chrome") != -1) {
            if (document.body.scrollTop >= r) {
                t.className = "stick"
            } else t.className = ""
        } else {
            if (document.documentElement.scrollTop >= r) {
                t.className = "stick"
            } else t.className = ""
        }
    }
};
$(document).ready(function() {
// $('a[href^="#bottom"]').on("click",
// function(e) {
// e.preventDefault();
// var t = this.hash,
// n = $(t);
// $("html, body").stop().animate({
// scrollTop: n.offset().top
// },
// 800, "swing",
// function() {
// window.location.hash = t
// })
// })
});
jQuery(document).ready(function() {
    $(".more").hide();
    jQuery(".pshw").click(function() {
        var e = $(this).closest(".toggle");
        e.find(".less").toggleClass("actv");
        e.find(".more").slideDown("1000")
    });
    jQuery(".les").click(function() {
        var e = $(this).closest(".toggle");
        e.find(".less").removeClass("actv");
        e.find(".more").slideUp("1000")
    })
});
$(document).ready(function() {
    $(function() {
        var e = $("body, html").scrollTop();
        $(".jumper").click(function(e) {
            e.preventDefault();
            var t = 0;
            $("body, html").animate({
                scrollTop: $($(this).attr("href")).offset().top - t
            },
            600)
        })
    })
});
var skypescrapperres = parseInt(screen.width, 10);
if (document.getElementById("rbanner")) {
    if (skypescrapperres == 1280 && parseInt(document.getElementById("rbanner").offsetWidth) > 0) {
        document.getElementById("wrapper").className = "scrn";
        var ids = ["middle_cnt", "ftr"];
        for (var i = 0; i < ids.length; i++) {
            if (document.getElementById(ids[i])) {
                document.getElementById(ids[i]).className = "wrapper scrn"
            }
        }
    }
}
var isVideoPlayed = false;
var ajax_list_externalFile = "/study/jsp/autocomplete/autoComplete.jsp";
var ajax_list_objects = new Array;
var currentListIndex = 0;
var tickerIndex = 0;
var tickerWidth = 0;
var timer;
var showTickerTimer;
var speed;
var timeDelay = 3e3;
var tickerCount = 0;
var lineHeight = 30;
var startPoint = 0;
$(document).ready(function() {
    $(".shrt").click(function() {
        setTimeout(function() {
            $(".bggrn").fadeOut()
        },
        4e3)
    })
})