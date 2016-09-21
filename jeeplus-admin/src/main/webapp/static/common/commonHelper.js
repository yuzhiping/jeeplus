/**
 * Created by yuzp17311 on 2016/9/2.
 */


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
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
};


var helper = helper || {};

//添加一个转换性别的函数
helper.formatSex = function (data) {
    if (data == 0)
        return "不详";
    else if (data == 1)
        return "女";
    else
        return "男";
};

// Ajax 文件下载 
jQuery.download = function (url, data, method) {
    // 获取url和data 
    if (url && data) {
        // data 是 string 或者 array/object 
        data = typeof data == 'string' ? data : jQuery.param(data);
        // 把参数组装成 form的 input 
        var inputs = '';
        jQuery.each(data.split('&'), function () {
            var pair = this.split('=');
            inputs += '<input type="hidden" name="' + pair[0] + '" value="' + pair[1] + '" />';
        });
        // request发送请求 
        jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' + inputs + '</form>')
            .appendTo('body').submit().remove();
    }
};//详细出处参考：http://www.jb51.net/article/33275.htm



///格式化日期
helper.formatDate = function (value) {

    if (value == null || value == "")
        return "";
    var typeStr = typeof (value);
    if (typeStr == "object") {
        //value = value.toLocaleString();
    } else if (typeStr == 'string') {
        value = new Date(Date.parse(value));
    }
    return value.Format("yyyy/MM/dd");//value.substring(0, 10);
};

/**
 * 格式化时间
 * @param value
 * @returns {string}
 */
helper.formatDateTime = function (value) {

    if (value == null || value == "")
        return "";
    var typeStr = typeof (value);
    if (typeStr == "object") {
        //value = value.toLocaleString();
    } else if (typeStr == 'string') {
        value = new Date(Date.parse(value));
    }
    return value.Format("yyyy-MM-dd hh:mm:ss"); //value.substring(0, 10);
};


//规范时间显示格式
helper.formatNewDate = function (date) {
    if (date == "" || date == null)
        return "";
    var dd = date.slice(0, 10);
    var ss = date.slice(11, 19);
    return dd + " " + ss;
};

helper.formatTimeCurrent = function (value) {

    value = eval('new ' + eval(value).source);

    var curDate = new Date();

    var diffValue = curDate - value;
    var minC = Math.floor(diffValue / (60 * 1000));

    if (minC < 1) {
        return "刚刚";
    } else if (minC < 60) {
        return minC + "分钟";
    } else if (minC > 60) {

        return "1小时以上";
    }
    return value.Format("yyyy-MM-dd hh:mm:ss"); //value.substring(0, 10);

};

helper.optionItem = function (name, population) {
    this.name = name;
    this.value = population;
};//用于select option的类


helper.getSelectedText = function (jqSelectObj) {
    return jqSelectObj.find("option:selected").text();
};
helper.formatOnline = function (value) {
    if (value == true) {

        return "在线";
    } else if (value == false) {
        return "离线";
    } else {
        return "未知";
    }
};

helper.getChecked = function (checkboxName) {  //jquery获取复选框值    
    var chk_value = [];
    $('input[name="' + checkboxName + '"]:checked').each(function () {
        var newObj = {};
        newObj.id = $(this).val();
        newObj.text = $(this).attr("data-text");//如果有text的话
        chk_value.push(newObj);
    });
    return chk_value;
};

helper.setChecked = function (checkboxName, values) {  //jquery设定复选框值    
    var chk_value = [];
    var ids = values.split(",");//按,分割

    $('input[name="' + checkboxName + '"]').each(function () {
        var id = $(this).val();
        for (var m = 0; m < ids.length; m++) {
            if (ids[m] == id) {
                //需要checked
                $(this).attr("checked", true);//打勾
                break;
            }
        }

    });
    return chk_value;
};

helper.clearChecked = function (checkboxName) {  //jquery清空复选框值    


    $('input[name="' + checkboxName + '"]:checked').each(function () {

        $(this).attr("checked", false);//打勾



    });
    //alert(chk_value.length == 0 ? '你还没有选择任何内容！' : chk_value);
};




helper.getScriptArgs = function () {//获取多个参数
    var scripts = document.getElementsByTagName("script"),
        script = scripts[scripts.length - 1],//因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
        src = script.src,
        reg = /(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
        temp, res = {};
    while ((temp = reg.exec(src)) != null) res[temp[1]] = decodeURIComponent(temp[2]);
    return res;
};

helper.getScriptArg = function (key) {//获取单个参数
    var scripts = document.getElementsByTagName("script"),
        script = scripts[scripts.length - 1],
        src = script.src;
    return (src.match(new RegExp("(?:\\?|&)" + key + "=(.*?)(?=&|$)")) || ['', null])[1];
};


//只取一个：
helper.queryString = function (key) {
    return (document.location.search.match(new RegExp("(?:^\\?|&)" + key + "=(.*?)(?=&|$)")) || ['', null])[1];
};

///取指定月的天数,month是1-12
helper.getMonthDays = function (year, month) {
    var day = new Date(year, month, 0);
    return day.getDate();
};


//添加于12.22日
//去除多余的分割符，例如：",a",结果为"a"
helper.dropRsplit = function (a, splitC) {
    var tmpA = a.split(splitC);
    var tmpObj = [];
    for (var i = 0; i < tmpA.length; i++) {
        if (tmpA[i] != "") {
            tmpObj.push(tmpA[i]);
        }

    }
    return tmpObj.join(splitC);
};

$(function () {




    // 手机号码验证
    jQuery.validator.addMethod("mobile", function (value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "手机号码格式错误");


    // 电话号码验证   
    jQuery.validator.addMethod("phone", function (value, element) {
        var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
        return this.optional(element) || (tel.test(value));
    }, "电话号码格式错误");


    // 邮政编码验证   
    jQuery.validator.addMethod("zipCode", function (value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "邮政编码格式错误");


    // QQ号码验证   
    jQuery.validator.addMethod("qq", function (value, element) {
        var tel = /^[1-9]\d{4,9}$/;
        return this.optional(element) || (tel.test(value));
    }, "qq号码格式错误");


    // IP地址验证
    jQuery.validator.addMethod("ip", function (value, element) {
        var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
        return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
    }, "Ip地址格式错误");


    // 字母和数字的验证
    jQuery.validator.addMethod("chrnum", function (value, element) {
        var chrnum = /^([a-zA-Z0-9]+)$/;
        return this.optional(element) || (chrnum.test(value));
    }, "只能输入数字和字母(字符A-Z, a-z, 0-9)");


    // 中文的验证
    jQuery.validator.addMethod("chinese", function (value, element) {
        var chinese = /^[\u4e00-\u9fa5]+$/;
        return this.optional(element) || (chinese.test(value));
    }, "只能输入中文");


    // 下拉框验证
    $.validator.addMethod("selectNone", function (value, element) {
        return value == "请选择";
    }, "必须选择一项");


    // 字节长度验证
    jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || (length >= param[0] && length <= param[1]);
    }, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));




    // 字符验证   
    jQuery.validator.addMethod("stringCheck", function (value, element) {
        return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
    }, "只能包括中文字、英文字母、数字和下划线");

    // 中文字两个字节   
    jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || (length >= param[0] && length <= param[1]);
    }, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

    // 身份证号码验证   
    jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请正确输入您的身份证号码");

    // 手机号码验证   
    jQuery.validator.addMethod("isMobile", function (value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");

    // 电话号码验证   
    jQuery.validator.addMethod("isTel", function (value, element) {
        var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678   
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码");

    // 联系电话(手机/电话皆可)验证   
    jQuery.validator.addMethod("isPhone", function (value, element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));

    }, "请正确填写您的联系电话");

    // 邮政编码验证   
    jQuery.validator.addMethod("isZipCode", function (value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的邮政编码");
    // 身份证号码验证
    jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请正确输入您的身份证号码");



    //增加身份证验证
    function isIdCardNo(num) {
        var factorArr = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];
        var parityBit = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"];
        var varArray = [];
        var intValue;
        var lngProduct = 0;
        var intCheckDigit;
        var intStrLen = num.length;
        var idNumber = num;
        // initialize
        if ((intStrLen != 15) && (intStrLen != 18)) {
            return false;
        }
        // check and set value
        for (i = 0; i < intStrLen; i++) {
            varArray[i] = idNumber.charAt(i);
            if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
                return false;
            } else if (i < 17) {
                varArray[i] = varArray[i] * factorArr[i];
            }
        }

        if (intStrLen == 18) {
            //check date
            var date8 = idNumber.substring(6, 14);
            if (isDate8(date8) == false) {
                return false;
            }
            // calculate the sum of the products
            for (i = 0; i < 17; i++) {
                lngProduct = lngProduct + varArray[i];
            }
            // calculate the check digit
            intCheckDigit = parityBit[lngProduct % 11];
            // check last digit
            if (varArray[17] != intCheckDigit) {
                return false;
            }
        }
        else {        //length is 15
            //check date
            var date6 = idNumber.substring(6, 12);
            if (isDate6(date6) == false) {
                return false;
            }
        }
        return true;
    }
    function isDate6(sDate) {
        if (!/^[0-9]{6}$/.test(sDate)) {
            return false;
        }
        var year, month, day;
        year = sDate.substring(0, 4);
        month = sDate.substring(4, 6);
        if (year < 1700 || year > 2500) return false;
        if (month < 1 || month > 12) return false;
        return true
    }

    function isDate8(sDate) {
        if (!/^[0-9]{8}$/.test(sDate)) {
            return false;
        }
        var year, month, day;
        year = sDate.substring(0, 4);
        month = sDate.substring(4, 6);
        day = sDate.substring(6, 8);
        var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        if (year < 1700 || year > 2500) return false;
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > iaMonthDays[month - 1]) return false;
        return true
    }
});
