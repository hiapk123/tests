if (typeof (pospal) == 'undefined') {
    var pospal = new Object();
}

/*站点命名空间*/
pospal.website = {};

/******************************* 常用方法 ***************************************/

pospal.ajax = function (opts) {
    opts = opts || {};
    opts.beforeSend = opts.beforeSend || function () { return true; };
    opts.type = opts.type || 'post';
    opts.async = opts.async || true;
    opts.dataType = opts.dataType || 'json';
    opts.url = opts.url || '';
    opts.data = opts.data || {};
    opts.error = opts.error || function (XmlHttpRequest, textStatus, errorThrown) { if (XmlHttpRequest.responseText != "") alert(opts.errorMsg); }
    opts.errorMsg = opts.errorMsg || '处理数据出现异常';
    opts.success = opts.success || function () { return true; };
    opts.complete = opts.complete || function () { return true; };

    if (opts.url != '') {
        $.ajax
	    ({
	        beforeSend: opts.beforeSend,
	        type: opts.type,
	        async: opts.async,
	        dataType: opts.dataType,
	        url: opts.url,
	        data: opts.data,
	        error: opts.error,
	        success: opts.success,
	        complete: opts.complete
	    });
    }
}

pospal.getCookie = function (objName) {
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if (temp[0] == objName) {
            return unescape(temp[1]);
        }
    }
    return '';
}

pospal.setCookie = function (objName, objValue, objHours) {

    var str = objName + "=" + escape(objValue);

    var date = new Date();
    var ms = objHours * 3600 * 1000;
    date.setTime(date.getTime() + ms);
    str += "; expires=" + date.toGMTString();
    document.cookie = str;
}

pospal.isInArray = function (arr, item) {
    var s = String.fromCharCode(2);
    var r = new RegExp(s + item + s);
    return (r.test(s + arr.join(s) + s));
}

pospal.browser = function (type) {
    var userAgent = navigator.userAgent.toLowerCase();
    switch (type) {
        case "isSafari":
            return /webkit/.test(userAgent);
        case "isOpera":
            return /opera/.test(userAgent);
        case "isMsie":
            if (!!window.ActiveXObject || "ActiveXObject" in window)
                return true;
            else
                return false;
        case "isMozilla":
            return /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent);
        default:
            alert("无法识别检测的浏览器类型");
            break;
    }

}

pospal.getRandomNum = function (min,max){   
    var range = max - min;   
    var rand = Math.random();   
    return (min + Math.round(rand * range));
}

pospal.htmlDecode = function (str) {
    return $('<div/>').html(str).text();
}

pospal.buildCategoryOptions = function (categorys) {
    
    function buildSubOptions(parentUid) {
        var subOptions = [];

        var subCategorys = $.grep(categorys, function (category, i) {
            return category.txtParentUid == parentUid;
        });

        if (subCategorys.length > 0) {           
            for (var i = 0; i < subCategorys.length; i++) {
                var subOption = {};
                subOption.text = subCategorys[i].name;
                subOption.value = subCategorys[i].txtUid;
                subOption.subOptions = buildSubOptions(subOption.value);
                if (subOption.subOptions.length == 0)
                    subOption.subOptions = null;

                subOptions.push(subOption);
            }
        }
        
        return subOptions;
    }

    return buildSubOptions(0);
}

pospal.formatSmallImageUrl = function (imagePath) {
    if (imagePath != "") {
        var indexOfDot = imagePath.lastIndexOf('.');
        if (indexOfDot > 0) {
            var smallImageUrl = imagePath.substring(0, indexOfDot) + "_200x200" + imagePath.substring(indexOfDot, imagePath.length);
            return smallImageUrl;
        }
    }
}

pospal.buildProductUnitOptions = function (productUnitExchangeList) {
    var unitOptions = [{ text: "无", value: "" }];

    if (productUnitExchangeList != null && productUnitExchangeList.length > 0) {
        unitOptions = [];
        for (var i = 0; i < productUnitExchangeList.length; i++) {
            var option = {};
            option.text = productUnitExchangeList[i].productUnitName;
            option.value = productUnitExchangeList[i].productUnitTxtUid;

            unitOptions.push(option);
        }
    }

    return unitOptions;
}

pospal.buildStoreUnitOptions = function (storeProductUnits){
    var unitOptions = [];
    if (storeProductUnits != null && storeProductUnits.length > 0) {
        for (var i = 0; i < storeProductUnits.length; i++) {
            var option = {};
            option.text = storeProductUnits[i].name;
            option.value = storeProductUnits[i].txtUid;

            unitOptions.push(option)
        }
    }
    return unitOptions;
}

pospal.getBaseUnitUid = function (productUnitExchangeList) {
    var baseUnitUid = "";
    if (productUnitExchangeList != null && productUnitExchangeList.length > 0) {
        var arr = $.grep(productUnitExchangeList, function (item, i) {
            return item.isBase == 1
        });

        return arr[0].productUnitTxtUid;
    }
}

pospal.getLocationParams = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

pospal.buildGPOptions = function () {
    var options = [];
    if(typeof(GP) != 'undefined'){
        for (var i = 0; i < GP.length; i++) {
            options.push({ text: GP[i], value: GP[i] });
        }
    }
    return options;
}

pospal.buildGTOptions = function (first) {
    var options = [];
    if (typeof (GP) != 'undefined') {
        var index = $.inArray(first, GP);
        if (index >= 0) {
            for (var i = 0; i < GT[index].length; i++) {
                options.push({ text: GT[index][i], value: GT[index][i] });
            }
        }
    }
    return options;
}

/****************************** 扩展方法 *****************************************/

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Date.prototype.addDays = function (days) {
    var sdate = this.getTime();
    var edate = new Date(sdate + (days * 24 * 60 * 60 * 1000));
    return edate;
}

Date.prototype.getThisWeekStart = function () {
    //星期一为第一天
    var getDay = new Date().getDay();
    if (getDay == 0) {
        return new Date().addDays(-6);
    } else {
        return new Date().addDays(getDay * -1 + 1);
    }
}

Date.prototype.getLastWeekStart = function () {
    //星期一为第一天
    var getDay = new Date().getDay();
    if (getDay == 0) {
        return new Date().addDays(-13);
    } else {
        return new Date().addDays(getDay * -1 - 6);
    }
}

Date.prototype.getLastWeekEnd = function () {
    //星期一为第一天
    var getDay = new Date().getDay();
    if (getDay == 0) {
        return new Date().addDays(-7);
    } else {
        return new Date().addDays(getDay * -1);
    }
}

Date.prototype.getThisMonthStart = function () {
    var currentYear=new Date().getFullYear();
    var currentMonth = new Date().getMonth();
    return new Date(currentYear, currentMonth, 1);
}

Date.prototype.getLastMonthStart = function () {
    var lastMonthEnd = this.getLastMonthEnd();
    var days = lastMonthEnd.getDate();
    return lastMonthEnd.addDays(days * -1 + 1);
}

Date.prototype.getLastMonthEnd = function () {
    var currentYear = new Date().getFullYear();
    var currentMonth = new Date().getMonth();
    return new Date(currentYear, currentMonth, 1).addDays(-1);
}

String.prototype.toDate = function () {
    return new Date(this.replace(/-/g, "/"));
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

/********************************** jquery 扩展方法 ************************************/
/*定位光标位置*/
$.fn.setCursorPosition = function (start, end) {
    return this.each(function () {
        if (this.setSelectionRange) {
            this.focus();
            this.setSelectionRange(start, end);
        } else if (this.createTextRange) {
            var range = this.createTextRange();
            range.collapse(true);
            range.moveEnd('character', end);
            range.moveStart('character', start);
            range.select();
        }
    });
};

/****************************************** UI ****************************************/

pospal.ui = {};

/*二选一switch控件*/
pospal.ui.switchBox = function (opts) {
    opts = opts || {};
    opts.clickCallBack = opts.clickCallBack || function () { return true };
    this.opts = opts;
    this.init();
}

pospal.ui.switchBox.prototype = {
    init: function (){
        this.selectedValue = this.opts.selectedValue == null ? this.opts.options[0].value : this.opts.selectedValue;
        this.createUI();
        this.reset();
    },
    
    createUI: function () {
        var _this = this;
        var htmlStr = "<div class='sLeft'>" + this.opts.options[0].text + "</div>";
        htmlStr += "<div class='sRight'>" + this.opts.options[1].text + "</div>";
        htmlStr += "<div class='sBotton'></div>";
        this.ui = $(this.opts.container).html(htmlStr);

        if (!this.ui.hasClass("swicth")) this.ui.addClass("swicth");

        $(this.ui).bind("click", function () {
            _this.click();
        })
    },

    click: function () {
        
        if (this.selectedValue == this.opts.options[0].value) {
            this.selectedValue = this.opts.options[1].value;
        } else {
            this.selectedValue = this.opts.options[0].value;
        }

        this.reset();

        this.opts.clickCallBack.call(this);

    },

    reset: function () {
        if (this.selectedValue == this.opts.options[0].value) {
            this.ui.removeClass("off");
        } else {
            this.ui.addClass("off");
        }
    },

    set: function (value) {
        this.selectedValue = value;
        this.reset();
    },

    getSelectedValue: function () {
        return this.selectedValue;
    }
}

/*单选条件*/
pospal.ui.radioBox = function (opts) {
    opts = opts || {};
    opts.clickCallBack = opts.clickCallBack || function () { return true };
    opts.checked = opts.checked != null ? opts.checked : false;
    opts.text = opts.text || "单选条件";
    this.opts = opts;
    this.init();
}

pospal.ui.radioBox.prototype = {
    init: function () {
        this.checked = this.opts.checked;
        this.createUI();
        this.reset();
    },
    createUI: function () {
        var _this = this;
        this.ui = $(this.opts.container);
        $("<i></i><em>" + this.opts.text + "</em>").appendTo(this.ui);
        if (!this.ui.hasClass("radioBox")) this.ui.addClass("radioBox");
        
        this.ui.bind("click", function () {
            _this.click();
        })
    },
    click: function () {
        if (this.checked)
            this.checked = false;
        else
            this.checked = true;

        this.reset();

        this.opts.clickCallBack.call(this);
    },
    reset: function () {
        if (this.checked)
            this.ui.addClass("radioBoxOn")
        else
            this.ui.removeClass("radioBoxOn");
    }
}


/*单选按钮组*/
pospal.ui.radioGroup = function (items) {
    this.init(items);
}

pospal.ui.radioGroup.prototype = {
    init: function (items) {
        var _this = this;
        this.items = items;

        $.each(items, function (i, item) {
            $(item).bind("click", function () {
                _this.click(this);
            });
        });
    },

    click: function (e) {
        $.each(this.items, function (i, item) {
            $(item).removeClass("on");
        });

        $(e).addClass("on");
    },

    clean: function () {
        $.each(this.items, function (i, item) {
            $(item).removeClass("on");
        });
    },

    setSelectedValue:function(value){
        this.clean();
        $.each(this.items, function (i, item) {
            if ($(item).attr("optionvalue") == value) {
                $(item).addClass("on");
            }
        });
    },

    getSelectedItem: function () {
        var selectedItem = null
        $.each(this.items, function (i, item) {
            if ($(item).hasClass("on")) {
                selectedItem = item;
            }
        });
        return selectedItem;
    },

    getSelectedValue: function () {
        var selectedValue = null;
        $.each(this.items, function (i, item) {
            if ($(item).hasClass("on")) {
                selectedValue = $(item).attr("optionvalue");
            }
        });
        return selectedValue;
    }
}


/*
    selectBox，选项选择框

    option选项li添加属性value,如果value值是18位数字的uid，ie下会异常，类似($(li).attr("value",option.value))
    但是属性改为其他名称就正常， 所以option下li使用属性名称optionValue，可能value在ie下是保留关键字
*/
pospal.ui.selectBox = function (opts) {
    opts = opts || {};
    opts.hide = opts.hide || function () { return true };
    opts.selectBoxWidth = opts.selectBoxWidth || 180;
    opts.selectBoxMaxHeight = opts.selectBoxMaxHeight || 260;
    opts.showInput = opts.showInput == false ? false : true;
    opts.minOptionNumShowInput = opts.minOptionNumShowInput || 20;
    opts.arrow = opts.arrow || "down";
    //opts.selectedValue = opts.selectedValue || null;
    opts.onChange = opts.onChange || function () { return true };
    opts.syncText = opts.syncText || function () { return true };
    opts.bottom = opts.bottom || null;
    this.opts = opts;
    this.selectedValue = opts.selectedValue;
    this.currentText = "";

    this.init();
}

pospal.ui.selectBox.prototype = {
    init: function () {
        var _this = this;

        this.getOptionGrades();

        this.createUI();

        //如果已经绑定过，要先解除，这个用在update选项的时候
        var selector = this.selectBox.parent();
        if ($._data(selector[0], "events") != undefined) {
            selector.unbind("keydown");
        }
        selector.attr('tabindex', 1).keydown(function (event) {
            _this.keyDown(event);
        });

    },

    getOptionGrades: function () {
        var _this = this;
        
        var maxGrade = 1;
        for (var i = 0; i < _this.opts.options.length; i++) {
            getGrades(_this.opts.options[i], 1);
        }

        function getGrades(option, grade) {
            if (option.subOptions != null && option.subOptions.length > 0) {
                for (var i = 0; i < option.subOptions.length; i++) {
                    if (grade + 1 > maxGrade) maxGrade = grade + 1;
                    getGrades(option.subOptions[i], grade + 1);
                }
            }
        }

        this.maxGrade = maxGrade;

        this.moreGrades = false;
        if (maxGrade > 1) {
            this.moreGrades = true;
        }

        //单独用一个属性保存，是因为避免多级情况下，调用update方法时，这个值一直累加
        this.selectBoxWidth = this.opts.selectBoxWidth + maxGrade * 24;
    },

    createUI: function () {
        var _this = this;

        this.selectBox = $(this.opts.container).addClass("selectBox");
        
        this.inputDiv = $("<div class='inputDiv' style='width:" + (this.selectBoxWidth - 12) + "px; display:none;'></div>").appendTo(this.selectBox);
        $("<input class='textInput' style='width:" + (this.selectBoxWidth - 44) + "px' type='text' value='输入关键字搜索' maxlength='10' />").appendTo(this.inputDiv);
        $("<div class='clearText' title='清除内容'>清除内容</div>").appendTo(this.inputDiv);

        this.inputDiv.bind("click", function () {
            return false;
        })

        this.inputDiv.find("input").bind("click", function () {
            if ($.trim(_this.inputDiv.find("input").val()) == "输入关键字搜索") {
                $(this).val("");
            }
        })

        this.inputDiv.find(".clearText").bind("click", function () {
            _this.inputDiv.find("input").val("");
            _this.inputDiv.find("input").focus();
            _this.filtOptions();
        })

        this.inputDiv.keyup(function (event) {
            if (event.keyCode != 13 && event.keyCode != 38 && event.keyCode != 40) {
                _this.filtOptions();
            }
        })

        this.optionDiv = $("<div class='optionDiv' style='float:left'><ul style='width:" + this.selectBoxWidth + "px'></ul></div>");
        this.optionDiv.css("width", this.selectBoxWidth);
        this.optionDiv.appendTo(this.selectBox);

        if (this.moreGrades) {
            this.buildMoreGradesOptionList(this.opts.options);
        } else {
            this.buildOneGradeOptionList(this.opts.options);
        }
        
        if (this.opts.showInput && this.optionDiv.find("li").length > this.opts.minOptionNumShowInput) {
            this.inputDiv.show();
        }

        if (this.opts.bottom != null) {
            var bottom = this.opts.bottom;
            this.bottomDiv = $("<div class='bottom'>").html(bottom.content).appendTo(this.selectBox);
            if (bottom.clickCallBack != null) {
                this.bottomDiv.addClass("btnEdit");
                this.bottomDiv.bind("click", function () {
                    bottom.clickCallBack.call(this);
                });
            } else {
                this.bottomDiv.bind("click", function () {
                    return false;
                });
            }
        }
    },

    buildOneGradeOptionList: function (options) {
        var _this = this;
        var ul = this.optionDiv.find("ul").empty();

        if (options.length < 2) {
            var li = "";
            if (options.length == 0) {
                li = $("<li style='color:red;'>无相关选项</li>").attr("optionValue", 'null');
            } else {
                var option = options[0];
                var li = $("<li>" + option.text + "</li>").attr("optionValue", option.value);
                li.addClass("selected");
            }

            li.bind("click", function () { _this.clickOption(this) });
            li.appendTo(ul);
        } else {
            for (var i = 0; i < options.length; i++) {
                var option = options[i];

                var li = $("<li>" + option.text + "</li>").attr("optionValue", option.value);
                li.bind("click", function () { _this.clickOption(this) });
                if (option.value == this.selectedValue) {
                    li.addClass("selected");
                }
                li.appendTo(ul);
            }
        }
        
        this.checkScrollbar();
    },

    buildMoreGradesOptionList: function (options) {
        var _this = this;
        var ul = this.optionDiv.find("ul").empty();

        function createSubOptions(subOptions, grade) {
            for (var i = 0; i < subOptions.length; i++) {
                if (subOptions[i] != undefined) {
                    createSubOption(subOptions[i], grade);
                }
            }
        }
        
        function createSubOption(option, grade) {
            var li = $("<li style='text-indent:" + (grade * 24 + 14) + "px'>" + option.text + "</li>").attr("optionValue", option.value);;
            li.bind("click", function () { _this.clickOption(this) });
            if (option.value == _this.selectedValue) {
                li.addClass("selected");

                _this.currentText = option.text;
                _this.opts.syncText.call(_this);
            }
            li.appendTo(ul);

            if (option.subOptions != null) {
                createSubOptions(option.subOptions, ++grade);
            }
        }

        createSubOptions(options, 0);

        this.checkScrollbar();
    },

    checkScrollbar: function () {
        var needScrollbar = false;
        var selectBox_height = this.optionDiv.find("ul li").length * 24;
        if (selectBox_height > this.opts.selectBoxMaxHeight) {
            selectBox_height = this.opts.selectBoxMaxHeight;
            needScrollbar = true;
        }
        this.optionDiv.height(selectBox_height);

        if (needScrollbar) {
            this.scrollbar = this.optionDiv.mCustomScrollbar({ keyboard: { enable: false} });
            this.scrollbar.find(".mCSB_scrollTools").bind("click", function () { return false; })
        } else if (this.scrollbar != undefined) {
            this.scrollbar.mCustomScrollbar("destroy");
        }
    },

    filtOptions: function () {
        var text = $.trim(this.inputDiv.find("input").val());

        if (this.moreGrades) {
            if (this.scrollbar != undefined) {
                var toObj = null;

                var hasMatchOption = false;
                var lis = this.optionDiv.find("ul li");
                for (var i = 0; i < lis.length; i++) {
                    if ($(lis[i]).html().toLowerCase().indexOf(text.toLowerCase()) > -1) {
                        hasMatchOption = true;
                        toObj = $(lis[i]);
                        break;
                    }
                }
                if ((!hasMatchOption || text.length ==0)  && this.selectedValue != null) {
                    toObj = this.optionDiv.find("ul li[optionValue='" + this.selectedValue + "']");
                }

                $(lis).removeClass("selected");
                toObj.addClass("selected");

                this.scrollbar.mCustomScrollbar('scrollTo', toObj.prev().length > 0 ? toObj.prev() : toObj, {
                    scrollInertia: 300
                });
                
            }
        } else {

            var options = this.opts.options;
            if (text.length > 0) {
                options = $.grep(options, function (item, index) {
                    return item.text.toLowerCase().indexOf(text.toLowerCase()) > -1;
                })
            }
            this.buildOneGradeOptionList(options);
        } 
    },

    show: function () {
        var _this = this;

        var parentObj = this.selectBox.parent();
        if (parentObj.length > 0) {
            //定位selectBox显示位置
            var winWidth = $(window).width();
            var winHeight = $(window).height();
            var parent_left = parentObj.offset().left;
            var parent_top = parentObj.offset().top;

            (parent_left + this.selectBoxWidth) >= winWidth ? this.selectBox.css("right", -1) : this.selectBox.css("left", -1);
            if (this.opts.arrow == "up") this.selectBox.addClass("up");
            if (parent_top + 28 + this.selectBox.height() >= winHeight && !this.selectBox.hasClass("up")) this.selectBox.addClass("up");
            this.selectBox.show();

            if (this.scrollbar != undefined) {
                setTimeout(function () {
                    var currentSelectedLi = _this.selectBox.find("li.selected");
                    var toObj = currentSelectedLi.prev().length > 0 ? currentSelectedLi.prev() : currentSelectedLi;
                    _this.scrollbar.mCustomScrollbar('scrollTo', toObj, {
                        scrollInertia: 0
                    });
                }, 50);
            }

            setTimeout(function () {
                $(document).one("click", function () {
                    _this.opts.hide.call(_this);
                    setTimeout(function () { _this.selectBox.css("display", "none") }, pospal.browser("isMsie") ? 0 : 150);
                    _this.selected();
                });
            }, 20);

            this.focusInput();

        }
    },

    keyDown: function (event) {
        if (!this.selectBox.is(":hidden")) {

            var _this = this;

            var newSelectedLi = null;
            var currentSelectedLi = this.selectBox.find("li.selected");

            if (event.keyCode == 38) {
                newSelectedLi = currentSelectedLi.prev();
            } else if (event.keyCode == 40) {
                newSelectedLi = currentSelectedLi.length == 0 ? this.selectBox.find("li").eq(0) : currentSelectedLi.next();
            } else if (event.keyCode == 13) {
                $(document).click();
            }

            if (newSelectedLi != null && newSelectedLi.length > 0) {
                currentSelectedLi.removeClass("selected");
                newSelectedLi.addClass("selected");

                if (newSelectedLi.attr("optionValue") != 'null') {
                    this.currentText = newSelectedLi.html();
                } else {
                    this.currentText = "";
                }
                this.opts.syncText.call(this);
                this.focusInput();
                
                if (this.scrollbar != undefined) {
                    var optionDivTop = this.optionDiv.offset().top;
                    var optionDivBottom = optionDivTop + this.optionDiv.height();

                    if (newSelectedLi.offset().top < (optionDivTop + 6) || newSelectedLi.offset().top > (optionDivBottom - 6 - 24)) {
                        var toObj = null;
                        if (event.keyCode == 40)
                            toObj = newSelectedLi.prev().length > 0 ? newSelectedLi.prev() : newSelectedLi;
                        else
                            toObj = '+=' + (this.optionDiv.height() - 24);

                        this.scrollbar.mCustomScrollbar('scrollTo', toObj, {
                            scrollInertia: 600
                        });
                    }
                }
            }
        } 
    },

    clickOption: function (e) {
        $(e).siblings().removeClass("selected");
        $(e).addClass("selected");
    },

    focusInput: function () {
        var _this = this;
        var textLength = $.trim(this.inputDiv.find("input").val()).length;
        setTimeout(function () { _this.inputDiv.find("input").setCursorPosition(textLength, textLength); }, 10);
    },

    selected: function () {

        var newSelectedLi = this.selectBox.find("li.selected");

        if (newSelectedLi.length == 0 || newSelectedLi.attr("optionValue") == "null") {
            this.currentText = "";
        } else {
            this.currentText = newSelectedLi.html();
        }
        this.opts.syncText.call(this);

        if (newSelectedLi.length > 0 && newSelectedLi.attr("optionValue") != "null") {
            var newSelectedValue = newSelectedLi.attr("optionValue");
            if (this.selectedValue != newSelectedValue) {
                this.selectedValue = newSelectedValue;
                this.opts.onChange.call(this);
            }
        }

        if (!this.moreGrades) {
            this.buildOneGradeOptionList(this.opts.options);
        }
        
    },

    setSelectedValue: function (value) {

        var oldSelectedLi = this.selectBox.find("li.selected");
        if (oldSelectedLi.length > 0){
            oldSelectedLi.removeClass("selected");
        }

        var newSelectedLi = this.selectBox.find("li[optionValue='" + value + "']");
        if (newSelectedLi.length > 0) {
            this.selectedValue = value;
            this.currentText = newSelectedLi.html();
            newSelectedLi.addClass("selected");

            this.opts.syncText.call(this);
        } 
    },

    getSelectedText: function () {
        var selectedOption = this.getSelectedOption();

        return selectedOption.text;
    },

    getSelectedOption: function () {
        var _this = this;
        var selectedOption = null;

        for (var i = 0; i < this.opts.options.length; i++) {
            if (selectedOption == null)
                querySelectedOption(this.opts.options[i]);
            else
                break;
        }

        function querySelectedOption(option) {

            if (option.value == _this.selectedValue) {
                selectedOption = option;
                return true;
            }
            
            if (option.subOptions != null && option.subOptions.length > 0) {
                for (var i = 0; i < option.subOptions.length; i++) {
                    var subOption = option.subOptions[i];
                    querySelectedOption(subOption);
                }
            }
        }

        return selectedOption;
    },

    getSelectedSubOptionValues: function () {

        var subValues = [];

        var selectedOption = this.getSelectedOption();
        if (selectedOption.value != "") {
            subValues.push(selectedOption.value);
            getSubOptionValue(selectedOption);
        }

        function getSubOptionValue(option) {
            if (option.subOptions != null && option.subOptions.length > 0) {
                for (var i = 0; i < option.subOptions.length; i++) {
                    var subOption = option.subOptions[i];
                    subValues.push(subOption.value);
                    getSubOptionValue(subOption);
                }
            }
        }

        return subValues;
    }
}


/*单选下拉菜单控件*/
pospal.ui.singleSelector = function (opts) {
    opts = opts || {};
    opts.selectBoxWidth;
    opts.selectBoxMaxHeight;
    opts.textWidth = opts.textWidth || 100;
    opts.showInput;
    opts.minOptionNumShowInput;
    opts.selectedValue = opts.selectedValue || null;
    opts.title = opts.title || null;
    opts.onChange = opts.onChange || function () { return true };
    this.opts = opts;

    this.disabled = false;

    this.init();
}

pospal.ui.singleSelector.prototype = {
    init: function () {
        this.createUI();
    },

    createUI: function () {
        var _this = this;

        this.ui = $(this.opts.container).addClass("singleSelector");
        this.ui.width(this.opts.textWidth);

        this.font = $("<font class='on'></font>").appendTo(this.ui);
        this.arrow = $("<b class='arrow'></b>").appendTo(this.ui);

        if (this.opts.selectedValue != null) {
            var textList = $.grep(this.opts.options, function (item, index) {
                return item.value == _this.opts.selectedValue;
            });
            if (textList.length > 0) {
                this.font.html(textList[0].text);
            }
        } else if (this.opts.title != null) {
            this.font.html(this.opts.title);
        } else {
            this.font.html(this.opts.options[0].text);
            this.opts.selectedValue = this.opts.options[0].value;
        }
 
        $("<div class='selectBox'></div>").appendTo(this.ui);
        
        this.opts.container = this.ui.find(".selectBox");
        this.opts.hide = function () { _this.ui.removeClass("on") }
        this.opts.syncText = function () {
            if (this.currentText != "") {
                _this.font.html(this.currentText);
            }
        }
        this.selectBox = new pospal.ui.selectBox(this.opts);

        this.ui.bind("click", function () {
            if (!_this.ui.hasClass("on") && !_this.disabled) {
                _this.selectBox.show();
                setTimeout(function () { _this.ui.addClass("on") }, 10);
            } 
        })
    },

    destroy: function () {
        this.ui.html("");
    },

    update: function (options) {
        this.opts.options = options;
        $(this.opts.container).html("");

        this.selectBox = new pospal.ui.selectBox(this.opts);
        this.setSelectedValue(options[0].value);        
    },

    getSelectedValue: function () { return this.selectBox.selectedValue; },

    getSelectedText: function () { return this.selectBox.getSelectedText(); },

    getSelectedSubOptionValues: function () { return this.selectBox.getSelectedSubOptionValues();},

    setSelectedValue: function (value) { this.selectBox.setSelectedValue(value); },

    setDisabled: function (value) { this.disabled = value; },

    getMaxGrade: function(){return this.selectBox.maxGrade}
}


/*时间日期选择器*/
pospal.ui.dateTimePicker = function (opts) {
    opts = opts || {};
    opts.type = opts.type || "range";
    opts.dateFormat = opts.dateFormat || (opts.type == "range" ? "yy.mm.dd" : "yy-mm-dd");
    opts.yearRange = opts.yearRange || (opts.type == "range" ? "2013:" : "1980:");
    if (opts.showTimepicker == null) opts.showTimepicker = (opts.type == "range" ? true : false);
    if (opts.showArrow == null) opts.showArrow = true;
    if (opts.showBtnClean == null) opts.showBtnClean = false;
    if (opts.type == "range" && opts.defaultDateTime == null) {
        opts.defaultDateTime =  [new Date().format("yyyy.MM.dd 00:00"), new Date().format("yyyy.MM.dd 23:59")]
    }
    this.opts = opts;

    this.dateTimePickerOpts = {};
    this.initDateTimePickerOpts();

    this.createUI();

    this.bindDateTimePicker();
    
}

pospal.ui.dateTimePicker.prototype = {
    regional: function () {
        //日期部分
        $.datepicker.regional['zh-CN'] = {
            closeText: '关闭',
            prevText: '&#x3c;上月',
            nextText: '下月&#x3e;',
            currentText: '今天',
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
            monthNamesShort: ['01', '02', '03', '04', '05', '06',
                    '07', '08', '09', '10', '11', '12'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            weekHeader: '周',
            dateFormat: 'yy-mm-dd',
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: '年'
        };
        $.datepicker.setDefaults($.datepicker.regional['zh-CN']);

        //时间部分
        $.timepicker.regional['zh-CN'] = {
            timeOnlyTitle: '选择时间',
            timeText: '时间',
            hourText: '小时',
            minuteText: '分钟',
            secondText: '秒钟',
            millisecText: '微秒',
            timezoneText: '时区',
            currentText: '现在时间',
            closeText: '关闭',
            timeFormat: 'HH:mm',
            amNames: ['AM', 'A'],
            pmNames: ['PM', 'P'],
            isRTL: false
        };
        $.timepicker.setDefaults($.timepicker.regional['zh-CN']);
    },

    createUI: function () {
        var _this = this;

        if (this.opts.type == "range") {
            var container = $(this.opts.container);
            if (!container.hasClass("dateTimeRangeBox")) container.addClass("dateTimeRangeBox");

            var beginId = pospal.getRandomNum(1, 999999);
            var endId = pospal.getRandomNum(1, 999999);

            var beginContainer = $("<input id='ui-timePicker-begin-" + beginId + "' type='text' class='timeInput' value='" + this.opts.defaultDateTime[0] + "' size='16' readonly='readonly'>");
            beginContainer.bind("click", function () {
                _this.setFocus(this);
            });
            beginContainer.appendTo(container);

            $("<span>-</span>").appendTo(container);

            var endContainer = $("<input id='ui-timePicker-end-" + endId + "' type='text' class='timeInput' value='" + this.opts.defaultDateTime[1] + "' size='16' readonly='readonly'>");
            endContainer.bind("click", function () {
                _this.setFocus(this);
            });
            endContainer.appendTo(container);

            this.dateTimePickerOpts.beginContainer = $("#ui-timePicker-begin-" + beginId);
            this.dateTimePickerOpts.endContainer = $("#ui-timePicker-end-" + endId);
            this.dateTimePickerOpts.onClose = function () { container.find("input").removeClass("on") }
        }
    },

    initDateTimePickerOpts: function () {
        this.dateTimePickerOpts = {
            timeFormat: "HH:mm",
            dateFormat: this.opts.dateFormat,
            changeYear: true,
            changeMonth: true,
            showTime: false,
            showArrow: this.opts.showArrow,
            showBtnClean: this.opts.showBtnClean,
            yearRange: this.opts.yearRange,
            showTimepicker: this.opts.showTimepicker,
            showDateRangeSelector: this.opts.type == "range" ? true : false,
            dateTimePickerWidth: this.opts.type == "range" ? 400 : 266,
            container: this.opts.container
        };
    },

    bindDateTimePicker: function () {
        var _this = this;
        
        this.regional();

        if (this.opts.type == "range")
            $(this.opts.container).find("input").datetimepicker(this.dateTimePickerOpts);
        else
            $(this.opts.container).datetimepicker(this.dateTimePickerOpts);
    },

    setFocus: function (e) {
        $(e).parent().find("input").removeClass("on");
        $(e).addClass("on");
    },

    getSelectedDatetime: function (withoutSecond) {
        if (this.opts.type == "range") {
            var arr = [2];
            if (typeof (withoutSecond) != "undefined" && withoutSecond) {
                arr[0] = $(this.dateTimePickerOpts.beginContainer).val();
                arr[1] = $(this.dateTimePickerOpts.endContainer).val();
            } else {
                arr[0] = $(this.dateTimePickerOpts.beginContainer).val() + ":00";
                arr[1] = $(this.dateTimePickerOpts.endContainer).val() + ":59";
            }
            return arr;
        } else {
            return $(this.opts.container).val();
        }

    }
}

/*时间选择器*/
$.fn.timeSlidePicker = function () {
    $.timepicker.regional['zh-CN'] = {
        timeOnlyTitle: '选择时间',
        timeText: '时间',
        hourText: '小时',
        minuteText: '分钟',
        secondText: '秒钟',
        millisecText: '微秒',
        timezoneText: '时区',
        currentText: '现在时间',
        closeText: '关闭',
        timeFormat: 'HH:mm',
        amNames: ['AM', 'A'],
        pmNames: ['PM', 'P'],
        isRTL: false
    };
    $.timepicker.setDefaults($.timepicker.regional['zh-CN']);

    var timeSlidePickerOptions = {
        timeFormat: "HH:mm:ss",
        showArrow: false,
        showTime: false,
        showTimepicker: true,
        timeOnly: true,
        showButtonPanel: false
    };

    $(this).datetimepicker(timeSlidePickerOptions);
}

/*信息提示框*/
pospal.ui.msgBox = function (opts) {
    if (typeof (opts) == 'string') {
        var content = opts;
        opts = { content: content };
    }

    opts = opts || {};
    opts.boxType = opts.boxType || "alert";
    opts.width = opts.width || 360;
    opts.height = opts.height || (opts.boxType == "confirm" ? 240 : 220);
    opts.title = opts.title || '系统消息';
    opts.content = opts.content || '';
    opts.ajaxUrl = opts.ajaxUrl || '';
    opts.onConfirm = opts.onConfirm || function () { return true };

    if (opts.autoCloseSec == undefined) {
        if(opts.boxType == "alert")
            opts.autoCloseSec = 1800;
        else
            opts.autoCloseSec = 0;
    }

    this.opts = opts;

    this.createUI();
    this.show();
}

pospal.ui.msgBox.prototype = {

    createUI: function () {
        var _this = this;

        this.background = $("<div class='popupBg' style='z-index:9999'></div>").appendTo('body');

        var mainWrapHtml = "<div class='popup' style='z-index:9999'>";
        mainWrapHtml +="        <div class='popupTitle bg'>";
        mainWrapHtml += "            <h1></h1>";
        mainWrapHtml +="            <div class='popupClose' title='关闭'>关闭</div>";
        mainWrapHtml +="        </div>";   
        mainWrapHtml +="        <div class='popupArea'>";
        mainWrapHtml +="            <div class='popupAreaCenter'>";
        mainWrapHtml +="            </div>";
        mainWrapHtml +="        </div>";
        mainWrapHtml += "   </div>";
        this.mainWrap = $(mainWrapHtml).appendTo('body');

        this.mainWrap.css({ "width": this.opts.width + 'px', "height": this.opts.height + 'px', "margin-top": this.opts.height / -2 + 'px', "margin-left": this.opts.width / -2 + 'px' })

        this.initContent();

        //绑定关闭事件
        $(this.mainWrap).find(".popupClose").bind("click", function () {
            _this.close();
        });

    },

    initContent: function () {
        var _this = this;

        this.mainWrap.find(".popupTitle h1").html("● " + this.opts.title);

        switch (this.opts.boxType) {
            case 'html':
                $(this.mainWrap).find(".popupAreaCenter").html(this.opts.content);
                break;
            case 'ajax':
                $(this.mainWrap).find(".popupAreaCenter").html(this.opts.ajaxUrl);
                break;
            case 'confirm':
                $(this.mainWrap).find(".popupAreaCenter").html(this.opts.content);

                var confirmContainer = $("<div class='popupBottom'></div>").appendTo(this.mainWrap);

                $("<div class='popupBtn popupBtnCancel' style='width:60px;'>取消</div>").click(function () {
                    _this.onConfirm(false);
                    _this.close();
                }).appendTo(confirmContainer);

                $("<div class='popupBtn popupBtnSure' style='width:60px;'>确定</div>").click(function () {
                    _this.onConfirm(true);
                    _this.close();
                }).appendTo(confirmContainer);

                $(this.mainWrap).find(".popupAreaCenter").css({ "text-align": "center", "padding-top": ((this.opts.height - 90) / 2 - 24) + "px" });

                break;
            default:
                $(this.mainWrap).find(".popupAreaCenter").html("<p>" + this.opts.content + "</p>");
                $(this.mainWrap).find(".popupAreaCenter").css({ "text-align": "center", "padding-top": ((this.opts.height - 40) / 2 - 40) + "px" });
                break;
        }
    },
    
    show: function () {

        var _this = this;
        this.background.show();
        this.mainWrap.fadeIn(500);

        if (this.opts.autoCloseSec > 0) {
            setTimeout(function () { _this.close() }, this.opts.autoCloseSec);
        }
    },

    close: function () {
        var _this = this;
        this.background.fadeOut(pospal.browser("isMsie") ? 0 : 600);
        this.mainWrap.fadeOut(600);
        setTimeout(function () { _this.background.remove(); _this.mainWrap.remove(); this.opts = null; }, 1000);
    },

    onConfirm: function (e) {

        this.confirmValue = e;
        this.opts.onConfirm.call(this);
    }
}

/*tip冒泡提示器*/
pospal.ui.tip = function (opts) {
    opts = opts || {};
    this.opts = opts;

    this.createUI();
}

pospal.ui.tip.prototype = {
    createUI: function () {
        var _this = this;

        this.tip = $("<div class='bubbleInfo'/>").appendTo(this.opts.target);
        $("<div class='arrow' />").appendTo(this.tip);
        $("<p>" + this.opts.content + "</p>").appendTo(this.tip);

        if (this.opts.width != undefined) {
            this.tip.width(this.opts.width);
        }

        var arrow = this.opts.arrow;
        var positionKey = this.opts.position.key;
        var positionValue = this.opts.position.value;

        this.tip.addClass(arrow);
        this.tip.css(positionKey, positionValue + "px");
        this.tip.find(".arrow").css(positionKey, (arrow == "up" || arrow =="down") ? (10 - positionValue) : positionValue * -1) + "px";

        this.show();

        this.tip.bind("click", function () {
            return false;
        })

        setTimeout(function () {
            $(document).one("click", function () {
                _this.destory();
            })
        }, 1);
        
    },

    show: function () {
        var _this = this;

        this.tip.css("display", "inline");
        setTimeout(function () { _this.tip.addClass("on"); }, 1);
    },

    destory: function () {
        this.tip.remove();
        this.opts = null;
    }
}


/*分页*/
pospal.ui.paging = function (opts) {
    opts = opts || {};
    opts.loadSummaryUrl = opts.loadSummaryUrl || "";
    opts.loadContentUrl = opts.loadContentUrl || "";
    opts.pagingContainer = opts.pagingContainer || ($("#summaryInfo").length > 0 ? $("#summaryInfo") : null);
    opts.contentContainer = opts.contentContainer || ($("#mainTable").length > 0 ? $("#mainTable") : null);
    opts.loadingContainer = opts.loadingContainer || ($("#mainArea").length > 0 ? $("#mainArea") : null);
    opts.pageSize = opts.pageSize || 50;
    opts.loadedSummary = opts.loadedSummary || function () { return true; };
    opts.loadedContent = opts.loadedContent || function () { return true; };

    this.opts = opts;
    
    if (this.isValid()) {
        this.queryPageIndex = 1;
        this.queryPageSize = this.opts.pageSize;
        this.totalPageNum = 4;
        this.totalRecord = 200;
        this.queryCriterias = null;
        this.orderColumn = "";
        this.asc = false;

        this.createUI();
    } 
}

pospal.ui.paging.prototype = {
    isValid: function () {
        if (this.opts.loadSummaryUrl == "" || this.opts.loadContentUrl == "" || this.opts.pagingContainer == null || this.opts.contentContainer == null) {
            alert("初始分页控件参数不正确！");
            return false;
        } else {
            return true;
        }
        
    },

    createUI: function () {
        var _this = this;

        //this.opts.pagingContainer.html("");

        this.summaryDiv = $("<div class='pLeft'></div>").appendTo(this.opts.pagingContainer);

        var pagingDiv = $("<div class='pRight textBtn'></div>");
        $("<span class='first'><i></i>首页</span>").appendTo(pagingDiv);
        $("<span class='prev'><i></i>前一页</span>").appendTo(pagingDiv);
        $("<i>第</i><input class='appointPage quantity' type='text' value='1' maxlength='3' /><i>页</i>").appendTo(pagingDiv);
        $("<i class='pageNum'>共" + this.totalPageNum + "页</i>").appendTo(pagingDiv);
        $("<span class='next'>下一页<i></i></span>").appendTo(pagingDiv);
        $("<span class='end'>尾页<i></i></span>").appendTo(pagingDiv);
        $("<div class='pageSize'></div> ").appendTo(pagingDiv);
        this.pagingDiv = pagingDiv.appendTo(this.opts.pagingContainer);

        this.pageSizeSelector = new pospal.ui.singleSelector({
            container: this.pagingDiv.find(".pageSize"),
            textWidth: 60,
            selectBoxWidth: 63,
            options: [{ text: "每页100条", value: "100" }, { text: "每页50条", value: "50" }, { text: "每页20条", value: "20" }],
            selectedValue: this.queryPageSize,
            onChange: function () { _this.changePageSize();}
        });

        this.loading = new pospal.ui.loading(this.opts.loadingContainer);
        this.loading.hide();

        this.pagingDiv.find(".appointPage").blur(function () {
            _this.goToAppointPage();
        });

        this.pagingDiv.find(".appointPage").keyup(function (event) {
            if (event.keyCode == 13) {
                _this.goToAppointPage();
            }
        })

        this.pagingDiv.find(".first").bind("click", function () {
            _this.goToFirstPage();
        });

        this.pagingDiv.find(".prev").bind("click", function () {
            _this.goToPrePage();
        });

        this.pagingDiv.find(".next").bind("click", function () {
            _this.goToNextPage();
        });

        this.pagingDiv.find(".end").bind("click", function () {
            _this.goToEndPage();
        });
    },

    changePageSize: function () {
        this.queryPageSize = this.pageSizeSelector.getSelectedValue();
       
        this.queryPageIndex = 1;
        this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

        this.totalPageNum = this.totalRecord % this.queryPageSize == 0 ? this.totalRecord / this.queryPageSize : Math.floor(this.totalRecord / this.queryPageSize) + 1;
        this.pagingDiv.find(".pageNum").html("共" + this.totalPageNum + "页");

        this.loadContent();
    },

    goToFirstPage: function () {
        this.queryPageIndex = 1;
        this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

        this.loadContent();
    },

    goToPrePage: function () {
        if (this.queryPageIndex > 1) {
            this.queryPageIndex = parseInt(this.queryPageIndex) - 1;
            this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

            this.loadContent();
        } else {
            new pospal.ui.msgBox("当前已经是第一页了");
        }
    },

    goToNextPage: function () {
        if (this.queryPageIndex < this.totalPageNum) {
            this.queryPageIndex = parseInt(this.queryPageIndex) + 1;
            this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

            this.loadContent();
        }
        else {
            new pospal.ui.msgBox("当前已经是最后一页了");
        } 
    },

    goToEndPage: function () {
        this.queryPageIndex = this.totalPageNum;
        this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

        this.loadContent();
    },

    goToAppointPage: function () {
        var appointPage = $.trim(this.pagingDiv.find(".appointPage").val());

        if (this.queryPageIndex != appointPage) {
            if (isNaN(appointPage)) {
                new pospal.ui.msgBox("您输入的跳转页数有误！");
            } else {
                if (appointPage < 1 || appointPage > this.totalPageNum) {
                    new pospal.ui.msgBox("您输入的跳转页数超出有效范围！");
                }
                else {
                    this.queryPageIndex = appointPage;
                    this.loadContent();
                }
            }
        }
    },

    loadSummary: function () {
        var _this = this;

        this.queryPageIndex = 1;
        this.pagingDiv.find(".appointPage").val(this.queryPageIndex);

        this.loading.show();
        pospal.ajax({
            url: this.opts.loadSummaryUrl,
            data: this.queryCriterias,
            success: function (result) {
                if (result.successed) {
                    if (result.totalRecord > 0) {
                        _this.totalRecord = result.totalRecord;
                        _this.totalPageNum = _this.totalRecord % _this.queryPageSize == 0 ? _this.totalRecord / _this.queryPageSize : Math.floor(_this.totalRecord / _this.queryPageSize) + 1;
                        _this.pagingDiv.find(".pageNum").html("共" + _this.totalPageNum + "页");

                        if (result.summaryView != undefined) _this.summaryDiv.html(result.summaryView);
                        _this.opts.pagingContainer.show();

                        _this.loadContent();
                    } else {
                        _this.opts.pagingContainer.hide();
                        _this.opts.contentContainer.html(result.contentView);

                        _this.loading.hide();
                    }
                } else {
                    _this.loading.hide();
                    new pospal.ui.msgBox({ content: result.msg, boxType: result.type });
                }
            },
            complete: function () { _this.opts.loadedSummary.call(_this); }
        });
    },

    loadContent: function () {
        var _this = this;

        this.queryCriterias.pageIndex = this.queryPageIndex;
        this.queryCriterias.pageSize = this.queryPageSize;
        this.queryCriterias.orderColumn = this.orderColumn;
        this.queryCriterias.asc = this.asc;

        this.loading.show();
        pospal.ajax({
            url: this.opts.loadContentUrl,
            data: this.queryCriterias,
            success: function (result) {
                if (result.successed) {
                    _this.opts.contentContainer.html(result.contentView);

                    _this.opts.contentContainer.resizableFixedHeader({
                        resized: function () {
                            _this.updateMCustomScrollbar();
                        }
                    });

                    $("#contentArea").mCustomScrollbar("update");
                } else {
                    new pospal.ui.msgBox({ content: result.msg, boxType: result.type });
                }
            },
            complete: function () {
                setTimeout(function () { _this.loading.hide(); }, 500);
                _this.opts.loadedContent.call(_this);
            }
        });
    },

    load: function (queryCriterias) {
        if (this.queryCriterias == null) {
            this.queryCriterias = queryCriterias;
            this.loadSummary();
        } else {
            //checked：0为查询条件没有变动，1为查询条件变动，-1为无效条件
            var checked = 0; 
            for (var criteria in queryCriterias) {
                if (!this.queryCriterias.hasOwnProperty(criteria)) {
                    checked = -1;
                    break;
                } else {
                    if (queryCriterias[criteria] != this.queryCriterias[criteria]) {
                        checked = 1;
                        break;
                    }
                }
            }

            if (checked == -1) {
                new pospal.ui.msgBox("无效查询条件");
            } else if (checked == 1) {
                this.queryCriterias = queryCriterias;
                this.loadSummary();
            } else {
                this.queryCriterias = queryCriterias;
                this.loadContent();
            }

        }
    }
}

/*Loading*/
pospal.ui.loading = function (container) {
    this.container = container || $("body");
    this.createUI();
}

pospal.ui.loading.prototype = {
    createUI: function () {
        this.background = $("<div class='loadingBg'></div>").appendTo(this.container);
        this.lodingGif = $("<div class='loading'><div class='loadingGIF'></div></div>").appendTo(this.container);
    },

    show: function () {
        this.background.show();
        this.lodingGif.show();
    },

    hide: function () {
        this.background.hide();
        this.lodingGif.hide();
    },

    destroy: function () {
        var _this = this;

        setTimeout(function () {
            _this.background.remove();
            _this.lodingGif.remove();
        }, 500);
    }
}

/*补全行*/
pospal.ui.buildBlankRows = function (opts) {
    opts = opts || {};
    opts.rowClass = opts.rowClass || "";
    this.opts = opts;

    this.build();
}

pospal.ui.buildBlankRows.prototype = {
    build: function () {
        var tBody = this.opts.tableContainer.find("table tbody");

        var rowNum = parseInt(this.opts.tableContainer.height() / 40);
        var blankRowNum = rowNum - tBody.find("tr").length;
        if (blankRowNum > 0) {
            for (var i = 0; i < blankRowNum; i++) {
                var blankRow = $("<tr/>").appendTo(tBody);
                if (this.opts.rowClass != "") blankRow.addClass(this.opts.rowClass);

                for (var j = 0; j < this.opts.colNum; j++) {
                    $("<td></td>").appendTo(blankRow);
                }
            }
        }       
    }
}

/**************************************** 表单验证器 **************************************/
pospal.formValidator = function (opts) {
    opts = opts || {};
    
    opts.showMsgType = opts.showMsgType || "1"; //0不现实错误信息，1为显示错误信息在文本框下方
    if (opts.realTime == null) opts.realTime = true;
    opts.formItems = opts.formItems || [];

    this.opts = opts;

    this.init();
}

pospal.formValidator.prototype = {
    init: function () {
        var _this = this;

        if (this.opts.realTime) {
            $.each(this.opts.formItems, function (i, formItem) {
                if (formItem.needValidate == undefined || formItem.needValidate) {
                    if (formItem.isSelector != undefined || formItem.isSelector) {
                        formItem.ele.blur(function () {
                            _this.validateDDLItem(formItem);
                        });
                    } else {
                        formItem.ele.keyup(function () {
                            _this.validateInputItem(formItem);
                        });
                    }
                }
            });
        }
    },

    isValid: function () {
        var isValid = true;
        for (var index in this.opts.formItems) {
            var formItem = this.opts.formItems[index];
            if (formItem.needValidate == undefined || formItem.needValidate) {
                if (formItem.isSelector != undefined || formItem.isSelector) {
                    this.validateDDLItem(formItem);
                } else {
                    this.validateInputItem(formItem);
                }

                if (formItem.msg != "") {
                    isValid = false;
                }
            }
        }

        return isValid;
    },

    validateInputItem: function (formItem) {
        var value = formItem.ele.val().trim();
        var rules = formItem.rules;
        formItem.msg = "";
        
        for (var index in rules)
        {
            var rule = rules[index];
            if (rule.type != undefined && rule.msg != undefined) {

                if (this[rule.type] != undefined) {
                    var isValid = true;
                    switch (rule.type)
                    {
                        case "required":
                            isValid = this.required(value);
                            break;
                        case "dataRange":
                            isValid = this.dataRange(value, rule.max, rule.min);
                            break;
                        case "lengthRange":
                            isValid = this.lengthRange(value, rule.maxLength, rule.minLength);
                            break;
                        case "notEqual":
                            isValid = this.notEqual(value, rule.objValue);
                            break;
                        case "isSame":
                            isValid = this.isSame(value, rule.toObj);
                            break;
                        default:
                            if (value != "") {
                                isValid = this[rule.type](value);
                            }
                            break;
                    }
                    if (!isValid) {
                        formItem.msg = rule.msg;
                        break;
                    } 

                } else {
                    new pospal.ui.msgBox("表单验证方法 " + rule.type + " 不存在！");
                }
            }
        }

        this.displayMsg(formItem);
    },

    validateDDLItem: function (formItem) {
        var value = formItem.selectorObj.getSelectedValue();
        if (value == "") {
            formItem.msg = formItem.rule.msg;
        } else {
            formItem.msg = "";
        }

        this.displayMsg(formItem);
    },

    displayMsg: function (formItem) {
        switch (this.opts.showMsgType)
        {
            case "1":
                //错误信息在元素下方
                var msgDiv = formItem.ele.parent().find(".errorMessage");
                if (msgDiv.length > 0) {
                    formItem.ele.parent().removeClass("error");
                    msgDiv.remove();
                }

                if (formItem.msg != "") {
                    formItem.ele.parent().addClass("error");
                    $("<div class='PS errorMessage' />").html(formItem.msg).appendTo(formItem.ele.parent());
                } 

                break;
            default:
                break;
        }
    },

    cleanMsg: function () {
        switch (this.opts.showMsgType) {
            case "1":
                //错误信息在元素下方
                $(".item.error").each(function () {
                    $(this).removeClass("error");
                    $(this).find(".errorMessage").remove();
                });
                break;
            default:
                break;
        }
    },

    required: function (value) {
        return (value.length > 0);
    },

    isNumeric: function (value) {
        return ($.isNumeric(value));
    },

    isPositiveNumber: function (value) {
        return ($.isNumeric(value) && Number(value) > 0);
    },

    isNonnegativeNumber: function (value) {
        return ($.isNumeric(value) && Number(value) >= 0);
    },

    isInteger: function (value) {
        return (!(value.match(/^[-+]?\d*$/) == null));
    },

    isPositiveInteger: function (value) {
        return ((/^[0-9]*[1-9][0-9]*$/).test(value));
    },

    isNonnegativeInteger: function (value) {
        return ((/^(0|[1-9]\d*)$/).test(value));
    },

    isEmail: function (value) {
        return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value));
    },

    isPhoneNum: function (value) {
        return (/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value));
    },

    isAlphabetNumberUnderline: function (value) {
        return /^[a-zA-Z0-9_]{1,}$/.test(value);
    },

    isAlphabetNumber: function(value){
        return /^[a-zA-Z0-9]+$/.test(value);
    },

    dataRange: function (value, max, min) {
        if (this.isNumeric(value)) {
            value = parseFloat(value);
            if (max != undefined && value > max) {
                return false;
            }
            if (min != undefined && value < min) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    },

    lengthRange: function (value, maxLength, minLength) {
        if (value.length > maxLength || value.length < minLength) {
            return false;
        }

        return true;
    },

    notEqual: function(value, objValue){
        if(value == objValue) return false;

        return true;
    },

    isSame: function (value, toObj) {
        if(value != toObj.val().trim()) return false;

        return true;
    },

    isValidBarcode: function (value) {
        var isValid = true;
        for (var i = 0; i < value.length; i++) {
            var n = value.charCodeAt(i);
            if (!((n >= 48 && n <= 57) || (n >= 65 && n <= 90) || (n >= 97 && n <= 122) || n == 95 || n == 45 || n == 42)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    },

    isValidPosPalAccount: function (value) {
        if (!(value.length >= 6 && value.length <= 32)) return false;
        if (!this.isAlphabetNumberUnderline) return false;

        return true;
    }
}

/*************************创建form跨页提交**********************/
pospal.formPost = function (url, data, isBlank) {
    var form = $("<form/>").attr('action', url).attr('method', 'post');

    if (isBlank) form.attr('target', '_blank');

    $.each(data, function (i, val) {
        $('<input type="hidden" name="' + i + '" />').val(val).appendTo(form);
    });

    form.appendTo("body").css('display', 'none').submit();

    form.remove();
}


/*************************上传组件**********************/
pospal.buildUploader = function (opts) {
    plupload.addI18n({ "Stop Upload": "停止上传", "Upload URL might be wrong or doesn't exist.": "上传的URL可能是错误的或不存在。", "tb": "tb", "Size": "大小", "Close": "关闭", "Init error.": "初始化错误。", "Add files to the upload queue and click the start button.": "将文件添加到上传队列，然后点击”开始上传“按钮。", "Filename": "文件名", "Image format either wrong or not supported.": "图片格式错误或者不支持。", "Status": "状态", "HTTP Error.": "请求失败。", "Start Upload": "开始上传", "mb": "mb", "kb": "kb", "Duplicate file error.": "重复文件错误。", "File size error.": "文件大小错误", "N/A": "N/A", "gb": "gb", "Error: Invalid file extension:": "错误：无效的文件扩展名:", "Select files": "选择文件", "%s already present in the queue.": "%s 已经在当前队列里。", "File: %s": "文件: %s", "b": "b", "Uploaded %d/%d files": "已上传 %d/%d 个文件", "Upload element accepts only %d file(s) at a time. Extra files were stripped.": "每次只接受同时上传 %d 个文件，多余的文件将会被删除。", "%d files queued": "%d 个文件加入到队列", "File: %s, size: %d, max file size: %d": "文件: %s, 大小: %d, 最大文件大小: %d", "Drag files here.": "把文件拖到这里。", "Runtime ran out of available memory.": "运行时已消耗所有可用内存。", "File count error.": "文件数量错误。", "File extension error.": "文件扩展名错误。", "Error: File too large:": "错误: 文件太大:", "Add Files": "增加文件" });

    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,html4',
        browse_button: opts.browse_button,
        url: opts.url,
        flash_swf_url: '/Scripts/Plupload/Moxie.swf',
        multi_selection: opts.multi_selection,

        filters: {
            max_file_size: opts.max_file_size + 'mb',
            mime_types: [
                { title: "mineType", extensions: opts.extensions }
            ]
        },

        init: {
            PostInit: function () {
                if (opts.PostInit != undefined) {
                    opts.PostInit(uploader);
                }
            },

            FilesAdded: function (up, files) {
                if (opts.FilesAdded != undefined) {
                    opts.FilesAdded(up, files);
                }
            },

            FilesRemoved: function (up, files) {
                if (opts.FilesRemoved != undefined) {
                    opts.FilesRemoved(up, files);
                }
            },

            UploadProgress: function (up, file) {
                if (opts.UploadProgress != undefined) {
                    opts.UploadProgress(up, file);
                }
            },

            FileUploaded: function (up, file, response) {
                if (opts.FileUploaded != undefined) {
                    opts.FileUploaded(up, file, response);
                }
            },

            UploadComplete: function (up, file) {
                if (opts.UploadComplete != undefined) {
                    opts.UploadComplete(up, file);
                }
            },

            Error: function (up, err) {
                if (opts.Error != undefined) {
                    opts.Error(up, err);
                }
            }
        }
    });

    uploader.init();

    return uploader;
}

pospal.previewImage = function (file, callback) {
    if (!file || !/image\//.test(file.type)) return; 
    if (file.type == 'image/gif') {
        //gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            preloader.downsize(200, 200);
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL();
            callback && callback(imgsrc);
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}

/************************商品绑定**********************/
pospal.productBinder = function (opts){
    opts = opts || {};
    opts.container = opts.container || ($("#mainArea").length > 0 ? $("#mainArea") : null);
    opts.boundProductUids = opts.boundProductUids || [];
    opts.onClose = opts.onClose || function () { return true };
    this.opts = opts;

    this.loadUI();
}

pospal.productBinder.prototype = {
    loadUI: function () {
        var _this = this;

        pospal.ajax({
            url: "/Product/LoadProductBinderView",
            success: function (result) {
                $(result.view).appendTo(_this.opts.container);
                $("#bindProductsDiv .contentArea, #selectProductDiv .contentArea").mCustomScrollbar();
                setTimeout(function () { layout.fixedTableHeader($("#bindProductsDiv table,#selectProductDiv table")) }, 1);
            },
            complete: function () {
                _this.init();
            }
        });

        if ($(".popupBg").length == 0)
            $("<div/>").addClass("popupBg").hide().appendTo(this.opts.container);
    },

    init: function () {
        var _this = this;

        this.boundProductUids = this.opts.boundProductUids;

        _this.ddl_category = new pospal.ui.singleSelector({
            container: $('#selectProductDiv .ddl_category'),
            textWidth: 100,
            selectBoxWidth: 200,
            selectBoxMaxHeight: 300,
            options: [{ text: "- 全部商品分类 -", value: "" }],
            onChange: function () {
                if (_this.ddl_category.getSelectedValue() != "") {
                    _this.loadProductOptions("byCategory");
                }
            }
        });

        $(".popupClose").bind("click", function () {
            _this.hide();
        });

        $(".btnToogleSelectProductDiv").bind("click", function () {
            $(".flavorPopup").hasClass("in") ? $(".flavorPopup").removeClass("in") : $(".flavorPopup").addClass("in");
            $(".selectGoods").hasClass("in") ? $(".selectGoods").removeClass("in") : $(".selectGoods").addClass("in");
        });

        $("#selectProductDiv .btnAddProducts").bind("click", function () {
            _this.addProducts();
        });

        $("#selectProductDiv input.clean").one("click", function () {
            $(this).val("");
        });

        $("#selectProductDiv .btnSearchByKeyword").bind("click", function () {
            _this.loadProductOptions("byKeyword");
        });

        $(document).on("click", "#selectProductDiv .cb_checkAllProducts", function () {
            _this.clickSelectAll(this);
        });

        $("#bindProductsDiv .btnCleanAddedProducts").bind("click", function () {
            _this.cleanBoundProducts();
        });

        $(document).on("click", "#bindProductsDiv .btnDelBoundProduct", function () {
            _this.delBoundProduct(this);
        });
    },

    show: function (boundProductUids) {
        $(".popupBg").show();
        $("#bindProductsDiv").show();
        $("#selectProductDiv").show();

        if (boundProductUids.length > 0 && $("#bindProductsDiv table tbody tr.added").length == 0) {
            this.boundProductUids = boundProductUids;
            this.loadSelectedProducts();
        }

        $(".flavorPopup").removeClass("in");
        $(".selectGoods").removeClass("in");
    },

    hide: function () {
        $(".popupBg").hide();
        $("#bindProductsDiv").hide();
        $("#selectProductDiv").hide();
      
        this.opts.onClose.call(this);
    },

    reset: function(){
        this.boundProductUids = [];
        $("#bindProductsDiv table tbody").html("");
        $("#bindProductsDiv .bindProductNum").html("0");
        new pospal.ui.buildBlankRows({ tableContainer: $("#bindProductsDiv .contentArea"), colNum: 5, rowClass: "blank" });

        $("#selectProductDiv table tbody").html("");
        new pospal.ui.buildBlankRows({ tableContainer: $("#selectProductDiv .contentArea"), colNum: 5, rowClass: "blank" });

        $(".flavorPopup").removeClass("in");
        $(".selectGoods").removeClass("in");
    },

    updateCategoryOptions: function (userId) {
        var _this = this;

        this.userId = userId;
        pospal.ajax({
            url: "/Category/LoadCategoryDDLJson",
            data: { "userId": userId },
            success: function (result) {
                if (result.successed) {
                    var categoryOptions = pospal.buildCategoryOptions(result.categorys);
                    categoryOptions.unshift({ text: "- 全部商品分类 -", value: "" });
                    _this.ddl_category.update(categoryOptions);
                }
            },
            complete: function () {
            }
        });
    },

    loadSelectedProducts: function () {
        var _this = this;

        var loading = new pospal.ui.loading($("#bindProductsDiv"));
        pospal.ajax({
            url: "/Product/LoadBoundProducts",
            data: { "userId": _this.userId, "productUidsJson": JSON.stringify(_this.boundProductUids) },
            success: function (result) {
                if (result.successed) {
                    var tableObj = $("#bindProductsDiv table");
                    tableObj.html(result.view);
                    if (tableObj.find("tr.noRecord").length == 0) {
                        new pospal.ui.buildBlankRows({ tableContainer: $("#bindProductsDiv .contentArea"), colNum: 5, rowClass: "blank" });
                    }
                    layout.fixedTableHeader(tableObj);

                    _this.recountBoundProductNum();
                }
            },
            complete: function () {
                loading.destroy();
            }
        });
    },

    loadProductOptions: function (searchType) {
        var _this = this;

        var keywordInput = $("#selectProductDiv .popupTitle .redTextBox input");
        var keyword = keywordInput.val().trim();
        if (keyword == "条码/名称/拼音码") { keyword = "" };
        var categorysJson = JSON.stringify(this.ddl_category.getSelectedSubOptionValues());
        
        if (searchType == "byCategory") {
            keyword = "";
            keywordInput.val("");
        }

        if (searchType == "byKeyword" && this.ddl_category.getSelectedValue() == "") {
            if (keyword == "") {
                new pospal.ui.msgBox("请输入搜索关键字");
                keywordInput.focus();
                return false;
            }
        }

        var doing = new pospal.ui.loading($("#selectProductDiv"));
        pospal.ajax({
            url: "/Product/LoadProductsForBind",
            data: { "userId": _this.userId, "categorysJson": categorysJson, "keyword": keyword },
            success: function (result) {
                if (result.successed) {
                    var tableObj = $("#selectProductDiv table");
                    tableObj.html(result.view);
                    if (tableObj.find("tr.noRecord").length == 0) {
                        new pospal.ui.buildBlankRows({ tableContainer: $("#selectProductDiv .contentArea"), colNum: 5, rowClass: "blank" });
                    }
                    layout.fixedTableHeader(tableObj);
                    _this.filtBoundProduct();
                }
            },
            complete: function () {
                doing.destroy();
            }
        });
    },

    filtBoundProduct: function () {
        var _this = this;

        var rows = $("#selectProductDiv table tr.option");
        $.each(rows, function (i, item) {
            var uid = $(item).attr("data");
            if ($.inArray(uid, _this.boundProductUids) > -1) {
                $(item).find("td input[type=checkbox]").hide();
                $(item).addClass("added").removeClass("option");
            }
        });
    },

    clickSelectAll: function (e) {
        var allChecked = $(e).prop("checked");

        $("#selectProductDiv table tr.option td input[type=checkbox]").each(function () {
            $(this).prop("checked", allChecked);
        });
    },

    addProducts: function () {
        var _this = this;

        $("#selectProductDiv table tr.option").each(function () {
            if ($(this).find("td input[type=checkbox]").prop("checked")) {
                var newRow = $("<tr/>").addClass("added").attr("data", $(this).attr("data"));
                $("<td/>").addClass("tdAlignCenter").html("<a class='operation2 btnDelBoundProduct'>删除</a>").appendTo(newRow);
                $("<td/>").html($(this).find(".productName").text()).appendTo(newRow);
                $("<td/>").html($(this).find(".productBarcode").text()).appendTo(newRow);
                $("<td/>").html($(this).find(".productCategoryName").text()).appendTo(newRow);
                $("<td/>").addClass("tdAlignRight").html($(this).find(".productSellPrice").text()).appendTo(newRow);

                if ($("#bindProductsDiv table tbody tr.blank").length > 0) {
                    $("#bindProductsDiv table tbody tr.blank").eq(0).replaceWith(newRow);
                } else {
                    newRow.appendTo($("#bindProductsDiv table tbody"));
                }

                _this.boundProductUids.push($(this).attr("data"));

                $(this).find("td input[type=checkbox]").hide();
                $(this).addClass("added").removeClass("option");
            }
        });

        if ($("#selectProductDiv .cb_checkAllProducts").prop("checked")) {
            $("#selectProductDiv .cb_checkAllProducts").prop("checked", false);
        }

        this.recountBoundProductNum();
    },

    cleanBoundProducts: function () {
        for (var i = 0; i < this.boundProductUids.length; i++) {
            var productUid = this.boundProductUids[i];
            this.recoverProductOption(productUid);
        }

        this.boundProductUids = [];
        $("#bindProductsDiv table tbody").html("");
        new pospal.ui.buildBlankRows({ tableContainer: $("#bindProductsDiv .contentArea"), colNum: 5, rowClass: "blank" });

        this.recountBoundProductNum();
    },

    delBoundProduct: function (e) {
        var rowObj = $(e).parent().parent();
        var productUid = rowObj.attr("data");
        
        this.recoverProductOption(productUid);

        var index = $.inArray(productUid, this.boundProductUids);
        if (index > -1) this.boundProductUids.splice(index, 1);
        rowObj.remove();
        new pospal.ui.buildBlankRows({ tableContainer: $("#bindProductsDiv .contentArea"), colNum: 5, rowClass: "blank" });

        this.recountBoundProductNum();
    },

    recoverProductOption: function (productUid) {
        var rowObj = $("#selectProductDiv table tr[data=" + productUid + "]");
        if (rowObj.length > 0) {
            rowObj.find("td input[type=checkbox]").prop("checked", false).show();
            rowObj.addClass("option").removeClass("added");
        }
    },

    recountBoundProductNum: function () {
        var productNum = $("#bindProductsDiv table tbody tr.added").length;
        $("#bindProductsDiv .bindProductNum").html(productNum);
    }
}

/********************* 测试 *****************************/
$(function () {

});