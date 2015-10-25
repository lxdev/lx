function $1(e) {
    return document.getElementById(e)
}
function JSstats(e, t, n, r, i, s, o, u, a, f, l, c, h, p) {
    var d = contextPath;
    var v = d + "/jsstats.html?activity=" + e + "&collegeId=" + t + "&courseId=" + p + "&countryId=" + n + "&secId=" + r + "&extraText=" + i + "&profileId=" + s + "&externalKey=" + o + "&searchHeader=" + u + "&qualcode=" + f + "&reqURL=" + l + "&refURL=" + c + "&suborderitem=" + h;
    var m = new sack;
    m.requestFile = v;
    m.runAJAX()
}
function CallJSstats(e) {
    var t = contextPath;
    var n = t + "/caljsstats.html?logId=" + e;
    var r = new sack;
    r.requestFile = n;
    r.runAJAX()
}
function addToShortList(e, t, n) {
    var r = window.event ? n: n;
    if (e != null && t != null) {
        var i = new sack;
        i.requestFile = contextPath + "/addshortlist.html?sid=" + e + "&action=" + t;
        i.onCompletion = function() {
            updateResponse(i, e, t, r)
        };
        i.runAJAX()
    }
}
function getTopPosition(e) {
    var t = e.offsetTop;
    while ((e = e.offsetParent) != null) {
        t += e.offsetTop
    }
    return t
}
function getLeftPosition(e) {
    var t = e.offsetLeft;
    while ((e = e.offsetParent) != null) {
        t += e.offsetLeft
    }
    return t
}
function getShortList() {
    clearTimeout(t);
    getBasketCount();
    if (ShrotlistCount != initialShrotlistCount || firstTime) {
        var e = $1("positionDiv");
        var n = getLeftPosition(e) + e.offsetWidth;
        var r = getTopPosition(e);
        $1("shrpop").style.position = "absolute";
        $1("shrpop").innerHTML = '<img src="http://images2.content-hca.com/int-cont/img/icons/nloading.gif" />';
        var i = new sack;
        i.requestFile = contextPath + "/navbasketdata.html";
        i.onCompletion = function() {
            showBasket(i)
        };
        i.runAJAX();
        firstTime = false;
        initialShrotlistCount = ShrotlistCount
    } else {
        $1("shrpop").style.display = "block"
    }
}
function showBasket(e) {
    var t = $1("positionDiv");
    var n = getLeftPosition(t) + t.offsetWidth;
    var r = getTopPosition(t);
    $1("shrpop").innerHTML = e.response;
    $1("shrpop").style.display = "block";
    $1("shrpop").style.position = "absolute"
}
function stop() {
    t = setTimeout("hide()", 500)
}
function hide() {
    $1("shrpop").style.display = "none"
}
function sack(file) {
    this.xmlhttp = null;
    this.resetData = function() {
        this.method = "POST";
        this.queryStringSeparator = "?";
        this.argumentSeparator = "&";
        this.URLString = "";
        this.encodeURIString = true;
        this.execute = false;
        this.element = null;
        this.elementObj = null;
        this.requestFile = file;
        this.vars = new Object;
        this.responseStatus = new Array(2)
    };
    this.resetFunctions = function() {
        this.onLoading = function() {};
        this.onLoaded = function() {};
        this.onInteractive = function() {};
        this.onCompletion = function() {};
        this.onError = function() {};
        this.onFail = function() {}
    };
    this.reset = function() {
        this.resetFunctions();
        this.resetData()
    };
    this.createAJAX = function() {
        try {
            this.xmlhttp = new ActiveXObject("Msxml2.XMLHTTP")
        } catch(e) {
            try {
                this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
            } catch(t) {
                this.xmlhttp = null
            }
        }
        if (!this.xmlhttp) {
            if (typeof XMLHttpRequest != "undefined") {
                this.xmlhttp = new XMLHttpRequest
            } else {
                this.failed = true
            }
        }
    };
    this.setVar = function(e, t) {
        this.vars[e] = Array(t, false)
    };
    this.encVar = function(e, t, n) {
        if (true == n) {
            return Array(encodeURIComponent(e), encodeURIComponent(t))
        } else {
            this.vars[encodeURIComponent(e)] = Array(encodeURIComponent(t), true)
        }
    };
    this.processURLString = function(e, t) {
        encoded = encodeURIComponent(this.argumentSeparator);
        regexp = new RegExp(this.argumentSeparator + "|" + encoded);
        varArray = e.split(regexp);
        for (i = 0; i < varArray.length; i++) {
            urlVars = varArray[i].split("=");
            if (true == t) {
                this.encVar(urlVars[0], urlVars[1])
            } else {
                this.setVar(urlVars[0], urlVars[1])
            }
        }
    };
    this.createURLString = function(e) {
        if (this.encodeURIString && this.URLString.length) {
            this.processURLString(this.URLString, true)
        }
        if (e) {
            if (this.URLString.length) {
                this.URLString += this.argumentSeparator + e
            } else {
                this.URLString = e
            }
        }
        this.setVar("rndval", (new Date).getTime());
        urlstringtemp = new Array;
        for (key in this.vars) {
            if (false == this.vars[key][1] && true == this.encodeURIString) {
                encoded = this.encVar(key, this.vars[key][0], true);
                delete this.vars[key];
                this.vars[encoded[0]] = Array(encoded[1], true);
                key = encoded[0]
            }
            urlstringtemp[urlstringtemp.length] = key + "=" + this.vars[key][0]
        }
        if (e) {
            this.URLString += this.argumentSeparator + urlstringtemp.join(this.argumentSeparator)
        } else {
            this.URLString += urlstringtemp.join(this.argumentSeparator)
        }
    };
    this.runResponse = function() {
        eval(this.response)
    };
    this.runAJAX = function(e, t) {
        if (this.failed) {
            this.onFail()
        } else {
            if (t != false) this.createURLString(e);
            if (this.element) {
                this.elementObj = $1(this.element)
            }
            if (this.xmlhttp) {
                var n = this;
                if (this.method == "GET") {
                    totalurlstring = this.requestFile + this.queryStringSeparator + this.URLString;
                    this.xmlhttp.open(this.method, totalurlstring, true)
                } else {
                    this.xmlhttp.open(this.method, this.requestFile, true);
                    try {
                        this.xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
                    } catch(r) {
                        alert("error")
                    }
                }
                this.xmlhttp.onreadystatechange = function() {
                    switch (n.xmlhttp.readyState) {
                    case 1:
                        n.onLoading();
                        break;
                    case 2:
                        n.onLoaded();
                        break;
                    case 3:
                        n.onInteractive();
                        break;
                    case 4:
                        n.response = n.xmlhttp.responseText;
                        n.responseXML = n.xmlhttp.responseXML;
                        n.responseStatus[0] = n.xmlhttp.status;
                        n.responseStatus[1] = n.xmlhttp.statusText;
                        if (n.execute) {
                            n.runResponse()
                        }
                        if (n.elementObj) {
                            elemNodeName = n.elementObj.nodeName;
                            elemNodeName.toLowerCase();
                            if (elemNodeName == "input" || elemNodeName == "select" || elemNodeName == "option" || elemNodeName == "textarea") {
                                n.elementObj.value = n.response
                            } else {
                                n.elementObj.innerHTML = n.response
                            }
                        }
                        if (n.responseStatus[0] == "200") {
                            n.onCompletion()
                        } else {
                            n.onError()
                        }
                        n.URLString = "";
                        break
                    }
                };
                this.xmlhttp.send(this.URLString)
            }
        }
    };
    this.reset();
    this.createAJAX()
}
function autocompleteOff(e, t) {
    self.focus();
    var n = $1(e);
    if (n) {
        n.setAttribute("autocomplete", "off")
    }
}
function ajax_getTopPos(e) {
    var t = e.offsetTop;
    while ((e = e.offsetParent) != null) {
        t += e.offsetTop
    }
    return t
}
function ajax_list_cancelEvent() {
    return false
}
function ajax_getLeftPos(e) {
    var t = e.offsetLeft;
    while ((e = e.offsetParent) != null) t += e.offsetLeft;
    return t
}
function ajax_option_setValue(e, t) {
    if (!t) t = this;
    var n = t.innerHTML;
    if (ajax_list_MSIE) n = t.innerText;
    else n = t.textContent;
    if (!n) n = t.innerHTML;
    ajax_list_activeInput.value = n;
    if ($1(ajax_list_activeInput.name + "_hidden")) $1(ajax_list_activeInput.name + "_hidden").value = t.id;
    ajax_options_hide();
    if (t.id.indexOf("$$$$") != -1) {
        urlcollegename(t.id, this)
    } else if (t.id.indexOf("$##$") != -1) {
        refinecollegesubmit(t.id, this)
    } else {}
}
function ajax_options_hide() {
    if (ajax_optionDiv) ajax_optionDiv.style.display = "none";
    if (ajax_optionDiv_iframe) ajax_optionDiv_iframe.style.display = "none"
}
function ajax_options_rollOverActiveItem(e, t) {
    if (ajax_list_activeItem) ajax_list_activeItem.className = "optionDiv";
    e.className = "optionDivSelected";
    ajax_list_activeItem = e;
    if (t) {
        if (ajax_list_activeItem.offsetTop > ajax_optionDiv.offsetHeight) {
            ajax_optionDiv.scrollTop = ajax_list_activeItem.offsetTop - ajax_optionDiv.offsetHeight + ajax_list_activeItem.offsetHeight + 2
        }
        if (ajax_list_activeItem.offsetTop < ajax_optionDiv.scrollTop) {
            ajax_optionDiv.scrollTop = 0
        }
    }
}
function ajax_option_list_buildList(e, t) {
    ajax_optionDiv.innerHTML = "";
    ajax_list_activeItem = false;
    if (ajax_list_cachedLists[t][e.toLowerCase()].length <= 1) {
        ajax_options_hide();
        return
    }
    ajax_list_optionDivFirstItem = false;
    var n = false;
    for (var r = 0; r < ajax_list_cachedLists[t][e.toLowerCase()].length - 1; r++) {
        if (ajax_list_cachedLists[t][e.toLowerCase()][r].length == 0) continue;
        n = true;
        var i = document.createElement("DIV");
        var s = ajax_list_cachedLists[t][e.toLowerCase()][r].split(/###/gi);
        if (ajax_list_cachedLists[t][e.toLowerCase()].length == 1 && ajax_list_activeInput.value == s[0]) {
            ajax_options_hide();
            return
        }
        i.innerHTML = s[s.length - 1];
        i.id = s[0];
        i.className = "optionDiv";
        i.onmouseover = function() {
            ajax_options_rollOverActiveItem(this, false)
        };
        i.onclick = ajax_option_setValue;
        if (!ajax_list_optionDivFirstItem) ajax_list_optionDivFirstItem = i;
        ajax_optionDiv.appendChild(i)
    }
    if (n) {
        ajax_optionDiv.style.display = "block";
        if (ajax_optionDiv_iframe) ajax_optionDiv_iframe.style.display = "";
        ajax_options_rollOverActiveItem(ajax_list_optionDivFirstItem, true)
    }
}
function ajax_option_list_showContent(e, t, n, r) {
    if (r != currentListIndex) return;
    var i = t.value;
    var s = ajax_list_objects[e].response;
    var o = s.split("|");
    ajax_list_cachedLists[n][i.toLowerCase()] = o;
    ajax_option_list_buildList(i, n)
}
function ajax_option_resize(e) {
    ajax_optionDiv.style.top = ajax_getTopPos(e) + e.offsetHeight + ajaxBox_offsetY + "px";
    ajax_optionDiv.style.left = ajax_getLeftPos(e) + ajaxBox_offsetX + "px";
    if (ajax_optionDiv_iframe) {
        ajax_optionDiv_iframe.style.left = ajax_optionDiv.style.left;
        ajax_optionDiv_iframe.style.top = ajax_optionDiv.style.top
    }
}
function ajax_showOptions(e, t, n, r, s) {
    if (n.keyCode == 13 || n.keyCode == 9) return;
    if (ajax_list_currentLetters[e.name] == e.value) return;
    ajax_list_cachedLists[t] = new Array;
    ajax_list_currentLetters[e.name] = e.value;
    if (!ajax_optionDiv) {
        ajax_optionDiv = document.createElement("DIV");
        ajax_optionDiv.id = "ajax_listOfOptions";
        ajax_optionDiv.className = "autocomplete";
        ajax_optionDiv.style.width = e.offsetWidth + "px";
        document.body.appendChild(ajax_optionDiv);
        if (ajax_list_MSIE) {
            ajax_optionDiv_iframe = document.createElement("IFRAME");
            ajax_optionDiv_iframe.border = "0";
            ajax_optionDiv_iframe.style.width = ajax_optionDiv.clientWidth + "px";
            ajax_optionDiv_iframe.style.height = ajax_optionDiv.clientHeight + "px";
            ajax_optionDiv_iframe.id = "ajax_listOfOptions_iframe";
            ajax_optionDiv_iframe.style.width = e.offsetWidth + "px";
            document.body.appendChild(ajax_optionDiv_iframe)
        }
        var o = document.getElementsByTagName("INPUT");
        for (var u = 0; u < o.length; u++) {
            if (!o[u].onkeyup) o[u].onfocus = ajax_options_hide
        }
        var a = document.getElementsByTagName("SELECT");
        for (var u = 0; u < a.length; u++) {
            a[u].onfocus = ajax_options_hide
        }
        var f = document.body.onkeydown;
        if (typeof f != "function") {
            document.body.onkeydown = ajax_option_keyNavigation
        } else {
            document.body.onkeydown = function() {
                f();
                ajax_option_keyNavigation()
            }
        }
        var l = document.body.onresize;
        if (typeof l != "function") {
            document.body.onresize = function() {
                ajax_option_resize(e)
            }
        } else {
            document.body.onresize = function() {
                l();
                ajax_option_resize(e)
            }
        }
    } else {
        if (e.id != null && (e.id == "topctitle" || e.id == "topsearchpodcollege")) {
            ajax_optionDiv.className = "autocompletesrch"
        } else {
            ajax_optionDiv.className = "autocomplete"
        }
        ajax_optionDiv.style.width = e.offsetWidth + "px";
        if (ajax_list_MSIE) {
            ajax_optionDiv_iframe.style.width = e.offsetWidth + "px"
        }
    }
    if (e.value.length < minimumLettersBeforeLookup) {
        ajax_options_hide();
        return
    }
    ajax_optionDiv.style.top = ajax_getTopPos(e) + e.offsetHeight + ajaxBox_offsetY + "px";
    ajax_optionDiv.style.left = ajax_getLeftPos(e) + ajaxBox_offsetX + "px";
    if (ajax_optionDiv_iframe) {
        ajax_optionDiv_iframe.style.left = ajax_optionDiv.style.left;
        ajax_optionDiv_iframe.style.top = ajax_optionDiv.style.top
    }
    ajax_list_activeInput = e;
    ajax_optionDiv.onselectstart = ajax_list_cancelEvent;
    currentListIndex++;
    if (ajax_list_cachedLists[t][e.value.toLowerCase()]) {
        ajax_option_list_buildList(e.value, t, currentListIndex)
    } else {
        var c = currentListIndex / 1;
        ajax_optionDiv.innerHTML = "";
        var h = ajax_list_objects.length;
        ajax_list_objects[h] = new sack;
        if (r != "null") {
            var p = r.split(",");
            for (i = 0; i < p.length; i++) {
                var d = p[i].split(":");
                v += d[0] + "=" + $1(d[1]).value + "&"
            }
        }
        var v = "";
        var m = "";
        var g = "";
        if (e.id == "collegeName") {
            m = $1("cName").value
        } else if (e.id == "college") {
            m = $1("countrySeId").value
        } else if (e.id == "searchpodcollege" || e.id == "topsearchpodcollege" || e.id == "countrysearchpodcollege") {
            if ($1("unicountryStyleId") != null) {
                if (e.id == "topsearchpodcollege") {
                    m = $1("topunicountryStyleId").value
                } else {
                    m = $1("unicountryStyleId").value
                }
            } else if (e.id == "topsearchpodcollege") {
                m = $1("topunicountryStyleId").value
            } else {
                if ($1("countryunicountryStyleId") != null) {
                    m = $1("countryunicountryStyleId").value
                }
            }
        } else if (e.id == "courseTitle") {
            m = $1("countryHidden").value
        } else if (e.id == "surveycourseId") {
            m = $1("college_hidden").value
        }
        if (s != null && s == "category") {
            if ($1("providerid") != null && e.id != "topctitle") {
                g = $1("providerid").value
            }
            var y = ajax_list_externalFile + "?" + t + "=1&catname=" + escape(encodeURIComponent(e.value)) + "&lang=" + minimumLettersBeforeLookup + "&from=" + s + "&providerId=" + g
        } else if (s != null && s == "masCourseTitle") {
            if ($1("enquiryId") != null) {
                var b = $1("enquiryId").value
            }
            var y = ajax_list_externalFile + "?" + t + "=1&letters=" + escape(encodeURIComponent(e.value)) + "&from=" + s + "&enqId=" + b
        } else {
            var y = ajax_list_externalFile + "?" + t + "=1&letters=" + e.value.replace(" ", "+") + "&" + v + "&from=" + s + "&id=" + m
        }
        ajax_list_objects[h].requestFile = y;
        ajax_list_objects[h].onCompletion = function() {
            ajax_option_list_showContent(h, e, t, c)
        };
        ajax_list_objects[h].runAJAX()
    }
}
function ajax_option_keyNavigation(e) {
    if (document.all) e = event;
    if (!ajax_optionDiv) return;
    if (ajax_optionDiv.style.display == "none") return;
    if (e.keyCode == 38) {
        if (!ajax_list_activeItem) return;
        if (ajax_list_activeItem && !ajax_list_activeItem.previousSibling) return;
        ajax_options_rollOverActiveItem(ajax_list_activeItem.previousSibling, true)
    }
    if (e.keyCode == 40) {
        if (!ajax_list_activeItem) {
            ajax_options_rollOverActiveItem(ajax_list_optionDivFirstItem, true)
        } else {
            if (!ajax_list_activeItem.nextSibling) return;
            ajax_options_rollOverActiveItem(ajax_list_activeItem.nextSibling, true)
        }
    }
    if (e.keyCode == 13 || e.keyCode == 9) {
        if (ajax_list_activeItem && ajax_list_activeItem.className == "optionDivSelected") ajax_option_setValue(false, ajax_list_activeItem);
        if (e.keyCode == 13) return false;
        else return true
    }
    if (e.keyCode == 27) {
        ajax_options_hide()
    }
}
function autoHideList(e) {
    if (document.all) e = event;
    if (e.target) source = e.target;
    else if (e.srcElement) source = e.srcElement;
    if (source.nodeType == 3) source = source.parentNode;
    if (source.tagName.toLowerCase() != "input" && source.tagName.toLowerCase() != "textarea") ajax_options_hide()
}
function show_town_dropdown(e, t, n, r, i) {
    if (n.keyCode == 13 || n.keyCode == 9) return;
    ajax_list_currentLetters[e.name] = e.value;
    if (i != null && i == "agent") {
        var s = ajax_list_externalFile + "?" + t + "=1&from=" + i + "&letters=" + e.value
    }
    var o = ajax_list_objects.length;
    ajax_list_objects[o] = new sack;
    currentListIndex++;
    var u = currentListIndex / 1;
    ajax_list_objects[o].requestFile = s;
    ajax_list_objects[o].onCompletion = function() {
        ajax_option_list_Content(o, e, r, u)
    };
    ajax_list_objects[o].runAJAX()
}
function ajax_option_list_Content(e, t, n, r) {
    if (r != currentListIndex) return;
    var i = ajax_list_objects[e].response;
    var s = i.split("|");
    var o = 1;
    if (s.length > 0) {
        for (var u = 0; u < s.length - 1; u++) {
            document.getElementById(n).options[o] = new Option(s[u], s[u].toLowerCase());
            o++
        }
    }
}
function show_town_fromscript(e, t, n, r) {
    ajax_list_currentLetters["countryid"] = e;
    if (r != null && r == "agent") {
        var i = ajax_list_externalFile + "?" + t + "=1&from=" + r + "&letters=" + e
    }
    var s = ajax_list_objects.length;
    ajax_list_objects[s] = new sack;
    currentListIndex++;
    var o = currentListIndex / 1;
    ajax_list_objects[s].requestFile = i;
    ajax_list_objects[s].onCompletion = function() {
        ajax_option_list_Content(s, "", n, o)
    };
    ajax_list_objects[s].runAJAX()
}
function selectDropdownValue(e, t) {
    for (var n = 0; n < $1(e).options.length; n++) {
        if ($1(e).options[n].value == t) {
            $1(e).selectedIndex = n;
            break
        }
    }
}
function trimString(e) {
    e = this != window ? this: e;
    return e.replace(/^\s+/g, "").replace(/\s+$/g, "")
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
function urlcollegename(e, t) {
    var n = e.split("$$$$");
    if ($1("selected_" + ajax_list_activeInput.name)) {
        if ($1(ajax_list_activeInput.name + "_hidden")) $1(ajax_list_activeInput.name + "_hidden").value = trim(n[0]);
        $1("selected_" + ajax_list_activeInput.name).value = trim(n[1])
    } else {
        var r = $1("unicountryStyleId").value;
        var i = cpeCountryname(t, r);
        if (i == "usa") {
            i = "us-usa"
        }
        var s = contextPath + "/" + i + "/school-college-university/" + n[1] + "/" + n[0] + "/international.html";
        location.href = s
    }
}
function setfunct(e, t, n, r, i) {
    var s = n;
    if ($1("sflflag") != null) {
        if (n == "sfl") {
            $1("sflflag").className = "sfl";
            $1("sb_1").style.display = "none"
        } else {
            if (n > 0) {
                $1("sflflag").className = "sfl-" + n
            }
            if ($1("country-" + n) != null) {
                $1("sflflag").innerHTML = $1("country-" + n).innerHTML;
                if (i != null && i != undefined) {
                    $1("catnamecid").innerHTML = $1(i + "-" + n).innerHTML
                }
            }
            if (i != null) {} else {
                $1("catnamecid").innerHTML = r
            }
            if (t == "sb_1") {
                var o = new Array("9", "114", "131", "134", "154", "168", "189", "210", "211");
                for (var u = 0; u < o.length; u++) {
                    document.getElementById("sb_2-" + o[u]).style.display = "none"
                }
            }
        }
    }
}
function accordianPanelClose(e, t, n) {
    if (n == "cid") {
        n = $1("sflflag").className.replace("sfl-", "");
        document.getElementById(e + "-" + n).style.display = "none"
    } else {
        document.getElementById(e).style.display = "none"
    }
}
function accordianPanel(e, t, n) {
    if (t == "mn_1") {
        if (document.getElementById(e).style.display == "none") {
            document.getElementById(e).style.display = "block"
        } else {
            document.getElementById(e).style.display = "none"
        }
    } else {
        if (n == "cid") {
            n = $1("sflflag").className.replace("sfl-", "")
        }
        if (document.getElementById(e + "-" + n) != null) {
            if (document.getElementById(e + "-" + n).style.display == "none") {
                document.getElementById(e + "-" + n).style.display = "block"
            } else {
                document.getElementById(e + "-" + n).style.display = "none"
            }
        }
    }
    if (document.getElementById(t).className != null) {
        classname = document.getElementById(t).className;
        if (classname == "brdlst" || classname == "brdlst1") {
            document.getElementById(t).className = document.getElementById(t).className + " active"
        }
    }
}
function refinecollegesubmit(e, t) {
    var n = e.split("$##$");
    $1("college_hidden").value = n[0];
    document.refinesearch.submit()
}
function chkvalidate(e) {
    if ($1(e).value != null && $1(e).value != "" && $1(e).value != $1("enterunimsg").value) {
        return true
    } else {
        alert($1("unimsg").value);
        return false
    }
}
function loadsearchColleges(e, t, n, r, i, s) {
    var o = $1("searchcollege").value;
    var u = null;
    var a = null;
    if ($1("collegeid_hidden") != null && $1("collegeid_hidden").value != null) {
        u = $1("collegeid_hidden").value
    }
    if ($1("timesRankId") != null && $1("timesRankId").value != null) {
        a = $1("timesRankId").value
    }
    var f = ajax_list_externalFile + "?" + t + "=1&from=" + i + "&collegeId=" + u + "&searchurl=" + s + "&inputCollege=" + o + "&timesRankId=" + a;
    var l = ajax_list_objects.length;
    ajax_list_objects[l] = new sack;
    currentListIndex++;
    var c = currentListIndex / 1;
    ajax_list_objects[l].requestFile = f;
    ajax_list_objects[l].onCompletion = function() {
        ajax_search_college_list_Content(l, "", r, c)
    };
    ajax_list_objects[l].runAJAX()
}
function loadsearchColleges(e, t, n, r, i, s) {
    var o = $1("searchcollege").value;
    var u = null;
    var a = null;
    if ($1("collegeid_hidden") != null && $1("collegeid_hidden").value != null) {
        u = $1("collegeid_hidden").value
    }
    if ($1("timesRankId") != null && $1("timesRankId").value != null) {
        a = $1("timesRankId").value
    }
    var f = ajax_list_externalFile + "?" + t + "=1&from=" + i + "&collegeId=" + u + "&searchurl=" + s + "&inputCollege=" + o + "&timesRankId=" + a;
    var l = ajax_list_objects.length;
    ajax_list_objects[l] = new sack;
    currentListIndex++;
    var c = currentListIndex / 1;
    ajax_list_objects[l].requestFile = f;
    ajax_list_objects[l].onCompletion = function() {
        ajax_search_college_list_Content(l, "", r, c)
    };
    ajax_list_objects[l].runAJAX()
}
function ajax_search_college_list_Content(e, t, n, r) {
    if (r != currentListIndex) return;
    var i = ajax_list_objects[e].response;
    $1("collegelistid").innerHTML = i;
    $1("collegelistid").style.display = "block"
}
function setCollidfun(e, t, n) {
    $1("searchcollege").value = n;
    $1("collegeid_hidden").value = t;
    $1("collegelistid").style.display = "none";
    document.refinecoursesearch.submit()
}
function chkcollvalidate(e) {
    if (trimString($1("searchcollege").value).length < 3) {
        alert($1("autocollmsg").value);
        $1("searchcollege").focus();
        return false
    }
    if ($1(e).value != null && $1(e).value != "" && $1(e).value != $1("enterunimsg").value) {
        return true
    } else {
        alert($1("autocollmsg").value);
        return false
    }
}
function clearcolinputText(e) {
    if (e.value = $1("searchcollege").value) {
        $1("collegeid_hidden").value = "";
        e.value = ""
    }
    $1("collegeid_hidden").focus()
}
function checksearchCollegename(e, t) {
    if (t == "N") {
        if ($1("searchcollege").value == "") {
            $1("searchcollege").value = $1("enterunimsg").value
        }
    } else {
        if ($1("searchcollege").value == "") {
            $1("searchcollege").value = $1("schcolval").value;
            $1("collegelistid").style.display = "none"
        }
    }
}
function show_collegelist_hide() {
    $1("collegelistid").style.display = "block"
}
function ajax_collegelist_hide() {
    $1("collegelistid").style.display = "none"
}
function loadajaxlistforsearchfilter(e, t, n, r, i, s, o, u, a, f) {
    var l = ajax_list_externalFile + "?" + i + "=1&from=" + u + "&lookup=" + r + "&searchurl=" + a + "&multi=" + o + "&applynow=" + n + "&levelflag=" + e + "&eflparams=" + t;
    var c = ajax_list_objects.length;
    ajax_list_objects[c] = new sack;
    currentListIndex++;
    var h = currentListIndex / 1;
    if ($1(f).innerHTML.trim() == "") {
        ajax_list_objects[c].requestFile = l;
        ajax_list_objects[c].onCompletion = function() {
            ajax_search_filter_drop_down_list_Content(c, "", o, h, r, f)
        };
        ajax_list_objects[c].runAJAX()
    }
}
function ajax_search_filter_drop_down_list_Content(e, t, n, r, i, s) {
    if (r != currentListIndex) return;
    var o = ajax_list_objects[e].response;
    if (i != null) {
        if (i == "C") {
            $1(s).innerHTML = o
        } else if (i == "Q") {
            $1(s).innerHTML = o
        } else if (i == "L") {
            $1("countylistid").innerHTML = o;
            $1("countylistid").style.display = "block";
            if ($1("sb_2") != null && $1("sb_2").style.display == "block") {
                $1("sb_2").style.display = "none"
            }
            if ($1("sb_5") != null && $1("sb_5").style.display == "block") {
                $1("sb_5").style.display = "none"
            }
        }
    }
}
function accordiansearchPanel(e, t) {
    if (e == "sb_1") {
        if (document.getElementById("sb_1") != null && document.getElementById("sb_1").style.display == "block") {
            document.getElementById("sb_1").style.display = "none"
        } else {
            document.getElementById("sb_1").style.display = "block"
        }
        if ($1("sb_5") != null) {
            $1("sb_5").style.display = "none"
        }
        if ($1("sb_4") != null) {
            $1("sb_4").style.display = "none"
        }
        if ($1("sb_2") != null) {
            $1("sb_2").style.display = "none"
        }
    }
    if (e == "sb_5") {
        if (document.getElementById("sb_5") != null && document.getElementById("sb_5").style.display == "block") {
            document.getElementById("sb_5").style.display = "none"
        } else {
            document.getElementById("sb_5").style.display = "block"
        }
        if ($1("sb_1") != null) {
            $1("sb_1").style.display = "none"
        }
        if ($1("sb_4") != null) {
            $1("sb_4").style.display = "none"
        }
        if ($1("sb_2") != null) {
            $1("sb_2").style.display = "none"
        }
    }
    if (e == "sb_2") {
        if (document.getElementById("sb_2") != null && document.getElementById("sb_2").style.display == "block") {
            document.getElementById("sb_2").style.display = "none"
        } else {
            document.getElementById("sb_2").style.display = "block"
        }
        if ($1("sb_1") != null) {
            $1("sb_1").style.display = "none"
        }
        if ($1("sb_4") != null) {
            $1("sb_4").style.display = "none"
        }
        if ($1("sb_5") != null) {
            $1("sb_5").style.display = "none"
        }
    }
    if (e == "sb_4") {
        if (document.getElementById("sb_4") != null && document.getElementById("sb_4").style.display == "block") {
            document.getElementById("sb_4").style.display = "none"
        } else {
            document.getElementById("sb_4").style.display = "block"
        }
        if ($1("sb_1") != null) {
            $1("sb_1").style.display = "none"
        }
        if ($1("sb_2") != null) {
            $1("sb_2").style.display = "none"
        }
        if ($1("sb_5") != null) {
            $1("sb_5").style.display = "none"
        }
    }
    if (document.getElementById(t) != null) {
        classname = document.getElementById(t).className;
        if (classname == "fcntr" || classname == "ar" || classname == "qsn") {
            document.getElementById(t).className = document.getElementById(t).className + " active"
        }
    }
}
function ajaxLogin(e, t) {
    if (t == "Y" || t == "S") {
        loadjsfile("http://js.content-hca.com/int-cont/scripts/Loginregister011013.js", "js")
    }
    setTimeout("ajaxforLogin('" + e + "')", 1e3)
}
function ajaxforLogin(e) {
    var t = contextPath;
    var n = t + "/ajaxForSignin.html?method=loadLogIn";
    var r = new sack;
    r.requestFile = n;
    r.onCompletion = function() {
        loadSignIn(r, e)
    };
    r.runAJAX()
}
function loadSignIn(e, t) {
    document.getElementById("ajaxloginarea").innerHTML = e.response;
    openLighbox("loginarea", "fadecommon")
}
function ajaxregister(e, t) {
    if (t == "Y" || t == "S") {
        loadjsfile("http://js.content-hca.com/int-cont/scripts/Loginregister011013.js", "js")
    }
    setTimeout("ajaxforregister('" + e + "')", 1e3)
}
function ajaxforregister(e) {
    var t = contextPath;
    var n = t + "/ajaxForSignin.html?method=loadSignUp";
    var r = new sack;
    r.requestFile = n;
    r.onCompletion = function() {
        loadSignInSignUp(r, e)
    };
    r.runAJAX()
}
function loadSignInSignUp(e, t) {
    document.getElementById("ajaxregisterarea").innerHTML = e.response;
    openLighbox("register", "fadecommon")
}
function selecttopJquery() {
    if ($1("selecttopJquery")) {
        eval($1("selecttopJquery").innerHTML)
    }
}
function ajaxtopsearch(e, t) {
    if ($1("socialMediaPod") != null && $1("socialMediaPod").style.display == "block") {
        $1("top-share-link").className = "tsh";
        $1("socialMediaPod").style.display = "none"
    }
    if ($1("ajaxtopsearchpod") != null && $1("ajaxtopsearchpod").innerHTML != "") {
        $1("ajaximgload").style.display = "none";
        if ($1("ajaxtopsearchpod") != null && $1("ajaxtopsearchpod").style.display == "none") {
            $1("ajaxtopsearchpod").style.display = "block"
        } else {
            $1("ajaxtopsearchpod").style.display = "none";
            $1("top-search").className = "tsr"
        }
    } else {
        if ($1("ajaxtopsearchpod") != null && $1("ajaxtopsearchpod").style.display == "none") {
            $1("ajaximgload").innerHTML = '<img src="http://images1.content-hca.com/int-cont/img/icons/ajx-ldn.gif" />';
            $1("ajaximgload").style.display = "block";
            if (t == "Y" || t == "S") {
                loadjsfile("http://css.content-hca.com/int-cont/css/ui_select_and_scroll180214.css", "css");
                if (t == "Y") {
                    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.min_180214.js", "js", "Y")
                } else {
                    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.mousewheel121113.js", "js");
                    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.scrollpane.js", "js");
                    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.core-ui-select.js", "js");
                    ajaxsearchtop()
                }
            } else {
                ajaxsearchtop()
            }
            $1("top-search").className = "tsr activ"
        } else {
            $1("top-search").className = "tsr"
        }
    }
}
function ajaxsearchtop() {
    if ($1("ajaxtopsearchpod") != null && $1("ajaxtopsearchpod").style.display == "none") {
        var e = contextPath;
        var t = e + "/ajaxForSignin.html?method=loadTopSearch";
        var n = new sack;
        n.requestFile = t;
        n.onCompletion = function() {
            loadTopSearch(n)
        };
        n.runAJAX()
    } else {
        $1("ajaxtopsearchpod").style.display = "none";
        $1("top-search").className = "tsr"
    }
}
function loadTopSearch(ajax) {
    document.getElementById("ajaxtopsearchpod").innerHTML = ajax.response;
    $1("ajaximgload").style.display = "none";
    if ($1("ajaxtopsearchpod") != null && $1("ajaxtopsearchpod").style.display == "none") {
        $1("ajaxtopsearchpod").style.display = "block";
        $1("top-search").className = "tsr activ";
        autocompleteOff("homecoursesearch", "1");
        autocompleteOff("homescholarshipsearch", "2");
        autocompleteOff("homecollegesearch", "3")
    } else {
        $1("ajaxtopsearchpod").style.display = "none";
        $1("top-search").className = "tsr"
    }
    if ($1("selecttopJquery")) {
        eval($1("selecttopJquery").innerHTML)
    }
}
function loadjsfile(e, t, n) {
    if (t == "js") {
        var r = document.createElement("script");
        r.setAttribute("type", "text/javascript");
        r.setAttribute("src", e);
        if (n != null && n != "" && n != "undefined" && n == "Y") {
            if (navigator.userAgent.indexOf("MSIE") >= 0) {
                r.onreadystatechange = function() {
                    if (this.readyState == "loaded" || this.readyState == "complete") {
                        callbackFunc()
                    }
                }
            } else {
                r.onload = callbackFunc
            }
        }
    } else if (t == "css") {
        var r = document.createElement("link");
        r.setAttribute("rel", "stylesheet");
        r.setAttribute("type", "text/css");
        r.setAttribute("href", e)
    }
    if (typeof r != "undefined") document.getElementsByTagName("head")[0].appendChild(r)
}
function getEventsValues() {
    var e = 10;
    var t = 1;
    var n = $1("eventsCount").value;
    var r = parseInt($1("curntEvntCount").value) + e;
    var i = parseInt($1("pageNum").value) + t;
    $1("curntEvntCount").value = r;
    $1("pageNum").value = i;
    if (parseInt(r) >= parseInt(n)) {
        $1("curntEvntCount").value = "0";
        $1("pageNum").value = "0";
        $1("shwMore").style.display = "none";
        $1("shwLess").style.display = "block"
    }
    var s = new sack;
    s.requestFile = contextPath + "/jsp/provider/getEventDetails.jsp?collegeId=" + $1("collegeId").value + "&countryid=" + $1("countryid").value + "&endmonth=" + $1("monthid").value + "&dispCount=10&pageNum=" + i;
    s.onCompletion = function() {
        eventResponse(s)
    };
    s.runAJAX()
}
function eventResponse(e) {
    var t = e.response.split("$$$$$&&&&");
    $1("lessEventDetails").style.display = "none";
    $1("morEventDetails").innerHTML = $1("morEventDetails").innerHTML + t[0];
    $1("morEventDetails").style.display = "block"
}
function refineEventsValaue(e) {
    $1("morEventDetails").innerHTML = "";
    var t = new sack;
    t.requestFile = contextPath + "/jsp/provider/getEventDetails.jsp?collegeId=" + $1("collegeId").value + "&countryid=" + $1("countryid").value + "&endmonth=" + $1("monthid").value + "&dispCount=2&pageNum=1";
    t.onCompletion = function() {
        refineResponse(t)
    };
    t.runAJAX()
}
function refineResponse(e) {
    var t = e.response.split("$$$$$&&&&");
    if (t[0].trim() != "") {
        $1("morEventDetails").style.display = "none";
        $1("lessEventDetails").innerHTML = t[0];
        $1("eventsCount").value = t[1].trim();
        if (parseInt(t[1]) <= 2) {
            if ($1("shwMoreId") != null) {
                $1("shwMoreId").style.display = "none"
            }
        } else {
            if ($1("shwMoreId") != null) {
                $1("shwMoreId").style.display = "block"
            }
            if ($1("shwMore") != null) {
                $1("shwMore").style.display = "block"
            }
            if ($1("shwLess") != null) {
                $1("shwLess").style.display = "none"
            }
        }
        if ($1("lessEventDetails") != null) {
            $1("lessEventDetails").style.display = "block"
        }
    } else {
        if ($1("shwMoreId") != null) {
            $1("shwMoreId").style.display = "none"
        }
        if ($1("lessEventDetails") != null) {
            $1("lessEventDetails").style.display = "none"
        }
    }
}
function addTickShortList(e, t, n) {
    var r = window.event ? n: n;
    if ($1("shrtId") != null) {
        if ($1("shrtId").className == "shrt") {
            $1("shrtId").className = "shrt act"
        }
    }
    if (e != null && t != null) {
        var i = new sack;
        i.requestFile = "/study/addshortlist.html?sid=" + e + "&action=" + t;
        i.onCompletion = function() {
            updateResponse(i, e, t, r)
        };
        i.runAJAX()
    }
}
function updateResponse(e, t, n, r) {
    if (screen.width <= 800) {
        responseText = e.response;
        if (responseText == "") {
            alert(e.response)
        } else if (responseText == "DUPLICATE_ITEM_ADDED") {
            alert("Your choice is already in your favourite list. Please add a different one.")
        } else if (responseText == "MAX_LIMIT_REACHED") {
            alert("You have reached the maximum favourite limit.")
        } else {
            callGaShortlist();
            $1("shrt_id").className = "bggrn fxd";
            $1("shrt_id").style.display = "block";
            if ($1("apply_sucs") != null) {
                $1("apply_sucs").style.display = "none"
            }
            var i = responseText.split("##");
            if (i != null && i[1] > 0) {
                $1("shortlistcountmobile").innerHTML = i[1];
                $1("shortlistcountmobile").className = "sht_cout";
                $1("myfavcountmobile").className = "grey family";
                if (i[2] || i[3] != null) {
                    $1("short_list_podmobile").style.display = "block";
                    if (i[2] > 0) {
                        $1("courseidmobile").style.display = "block";
                        $1("coursecountmobile").innerHTML = i[2]
                    }
                    if (i[3] > 0) {
                        $1("collegeidmobile").style.display = "block";
                        $1("collegecountmobile").innerHTML = i[3]
                    }
                }
            }
        }
    } else {
        responseText = e.response;
        if (responseText == "") {
            alert(e.response)
        } else if (responseText == "DUPLICATE_ITEM_ADDED") {
            alert("Your choice is already in your favourite list. Please add a different one.")
        } else if (responseText == "MAX_LIMIT_REACHED") {
            alert("You have reached the maximum favourite limit.")
        } else {
            callGaShortlist();
            $1("shrt_id").className = "bggrn fxd";
            $1("shrt_id").style.display = "block";
            if ($1("apply_sucs") != null) {
                $1("apply_sucs").style.display = "none"
            }
            var i = responseText.split("##");
            if (i != null && i[1] > 0) {
                $1("shortlistcount").innerHTML = i[1];
                $1("shortlistcount").className = "sht_cout";
                $1("myfavcount").className = "grey family";
                if (i[2] || i[3] != null) {
                    $1("short_list_pod").style.display = "block";
                    if (i[2] > 0) {
                        $1("courseid").style.display = "block";
                        $1("coursecount").innerHTML = i[2]
                    }
                    if (i[3] > 0) {
                        $1("collegeid").style.display = "block";
                        $1("collegecount").innerHTML = i[3]
                    }
                }
            }
        }
    }
}
function get_cookie(e) {
    var t = document.cookie.match("(^|;) ?" + e + "=([^;]*)(;|$)");
    if (t) return unescape(t[2]);
    else return null
}
function getBasketCount() {
    if ($(window).width() < 769) {
        var e = get_cookie("SLC_CK");
        if (e != null) {
            var t = e.split("##");
            if (t != null && t[1] > 0) {
                if ($1("usershortlistcount")) {
                    $1("usershortlistcount").innerHTML = t[1];
                    $1("usershortlistcount").className = "sht_cout"
                }
                $1("shortlistcountmobile").innerHTML = t[1];
                $1("shortlistcountmobile").className = "sht_cout";
                $1("myfavcountmobile").className = "grey family";
                if (t[2] || t[3] != null) {
                    $1("short_list_podmobile").style.display = "block";
                    if (t[2] > 0) {
                        $1("courseidmobile").style.display = "block";
                        $1("coursecountmobile").innerHTML = t[2]
                    }
                    if (t[3] > 0) {
                        $1("collegeidmobile").style.display = "block";
                        $1("collegecountmobile").innerHTML = t[3]
                    }
                }
            }
        }
    } else {
        var e = get_cookie("SLC_CK");
        if (e != null) {
            var t = e.split("##");
            if (t != null && t[1] > 0) {
                if ($1("usershortlistcount")) {
                    $1("usershortlistcount").innerHTML = t[1];
                    $1("usershortlistcount").className = "sht_cout"
                }
                $1("shortlistcount").innerHTML = t[1];
                $1("shortlistcount").className = "sht_cout";
                $1("myfavcount").className = "grey family";
                if (t[2] || t[3] != null) {
                    $1("short_list_pod").style.display = "block";
                    if (t[2] > 0) {
                        $1("courseid").style.display = "block";
                        $1("coursecount").innerHTML = t[2]
                    }
                    if (t[3] > 0) {
                        $1("collegeid").style.display = "block";
                        $1("collegecount").innerHTML = t[3]
                    }
                }
            }
        }
    }
}
function getScholarhip() {
    var e = 10;
    var t = 1;
    var n = $1("scholCount").value;
    var r;
    var i;
    if ($1("scholcourseId") != null) {
        r = $1("scholcourseId").value
    } else {
        r = 0
    }
    if ($1("scholcollegeId") != null) {
        i = $1("scholcollegeId").value
    }
    var s = parseInt($1("curntScholCount").value) + e;
    var o = parseInt($1("scholpageNum").value) + t;
    $1("curntScholCount").value = s;
    $1("scholpageNum").value = o;
    if (parseInt(s) >= parseInt(n)) {
        $1("curntScholCount").value = "0";
        $1("scholpageNum").value = "0";
        $1("shwschMore").style.display = "none";
        $1("shwschLess").style.display = "block"
    }
    var u = new sack;
    u.requestFile = contextPath + "/jsp/provider/scholarshipList.jsp?collegeId=" + i + "&courseId=" + r + "&dispCount=10&pageNum=" + o;
    u.onCompletion = function() {
        scholResponse(u)
    };
    u.runAJAX()
}
function scholResponse(e) {
    var t = e.response.split("$$$$$&&&&");
    $1("lessScholDetails").style.display = "none";
    $1("moreScholDetails").innerHTML = $1("moreScholDetails").innerHTML + t[0];
    $1("moreScholDetails").style.display = "block"
}
function showLessScholData(e, t) {
    if ($1("moreScholDetails").innerHTML != "") {
        $1("moreScholDetails").innerHTML = ""
    }
    $1(t).style.display = "none";
    $1(e).style.display = "block";
    $1("shwschMore").style.display = "block";
    $1("shwschLess").style.display = "none";
    $1("curntScholCount").value = "0";
    $1("scholpageNum").value = "0"
}
function checkIsCourseCollegeShortlist(e, t) {
    var n = e;
    if (n != null && n != "null") {
        if (n == "Y") {
            $1(t).className = "shrt act"
        } else {
            $1(t).className = "shrt"
        }
    }
}
function callGaShortlist() {
    ga("newTracker.set", "dimension1", "shortlisted");
    ga("newTracker.set", "dimension2", $1("gaInsiteSiteName").value);
    if ($1("gaCollegeId") != null && $1("gaCollegeId").value != null && $1("gaCollegeId").value != "null") {
        ga("newTracker.set", "dimension3", $1("gaCollegeId").value)
    }
    if ($1("gaCountryName") != null && $1("gaCountryName").value != null && $1("gaCountryName").value != "null") {
        ga("newTracker.set", "dimension13", $1("gaCountryName").value.toUpperCase())
    }
    if ($1("gaCourseTitle") != null && $1("gaCourseTitle").value != null && $1("gaCourseTitle").value != "null") {
        ga("newTracker.set", "dimension14", $1("gaCourseTitle").value)
    }
    if ($1("gaUserIdLog") != null && $1("gaUserIdLog").value != null && $1("gaUserIdLog").value != "null") {
        ga("newTracker.set", "dimension15", $1("gaUserIdLog").value)
    }
}
var contextPath = "/study";
if (typeof String.prototype.trim !== "function") {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g, "")
    }
}
var confirmDiv;
var t, firstTime = true;
var ajaxBox_offsetX = 0;
var ajaxBox_offsetY = 0;
var ajax_list_externalFile = contextPath + "/jsp/autocomplete/autoComplete.jsp";
//var ajax_list_externalFile = "programs";
var minimumLettersBeforeLookup = 3;
var ajax_list_objects = new Array;
var ajax_list_cachedLists = new Array;
var ajax_list_activeInput = false;
var ajax_list_activeItem;
var ajax_list_optionDivFirstItem = false;
var ajax_list_currentLetters = new Array;
var ajax_optionDiv = false;
var ajax_optionDiv_iframe = false;
var ajax_list_MSIE = false;
if (navigator.userAgent.indexOf("MSIE") >= 0 && navigator.userAgent.indexOf("Opera") < 0) ajax_list_MSIE = true;
var currentListIndex = 0;
document.documentElement.onclick = autoHideList;
var callbackFunc = function() {
    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.mousewheel121113.js", "js");
    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.scrollpane.js", "js");
    loadjsfile("http://js.content-hca.com/int-cont/scripts/jquery.core-ui-select.js", "js");
    ajaxsearchtop()
}