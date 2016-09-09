/**
 * 工具模块
 * @param root
 * @param factory
 */
(function(root,factory){

	if (typeof define === "function" && define.amd) {
        define(["jquery", 'require','layer'], function($,require,layer) {
            return factory($, require,layer);
        });
    }
	
})(this,function($, require,layer, undefined){
	/*网络请求基本配置*/
	var server_addr = 'http://localhost:8090',
	  server_addr_https = 'https://localhost:8443';
	   
	var config = {
		server_addr : server_addr,
		server_addr_https : server_addr_https,
		sdp_url : server_addr + '/tk-sdp/',
		sdp_https_url : server_addr_https + '/tk-sdp/',
		current_url : server_addr + '/tk-portal/',
		current_url_https : server_addr_https + '/tk-portal/',
		attachment_url : server_addr + '/tk-attachment',
	};

	//toast优化处理//session有效期(毫秒)
	var toastCount=0,SESSION_LIVE=30*60*1000;
	//需要压缩缩放的图片
	var FILTERIMG=[".png",".PNG",".jpg",".JPG",".jpeg",".JPEG",".bmp",".BMP"];
	//util
	var util = {
		server_addr : server_addr,
		server_addr_https : server_addr_https,
		config : config,
		/**
         * ajax http 请求
         * url, data, success, error
         */
		ajaxJson : function (url, data, success, error) {
			if (typeof(data) == "function") { //data 参数被忽略
				error = success;
				success = data;
				data = {};
			}
			util.ajax({
				url : url,
				data : data,
				success : success,
				error : error
			});
		},
		/**
         * ajax https 请求
         * url, data, success, error
         */
		ajaxJsonForHttps:function(url, data, success, error){
                util.ajaxJson(util.sdp_https_url + url,data,success,error);
         },
		/**
		 * ajax请求
		 * url, data, success, error
		 */
		ajax : function (obj) { //ajax请求
			
			var url = obj.url;
			//url处理
			if (url.indexOf('http') !== 0) {
				if (url.charAt(0) === '/') {
					url = url.substr(1);
				}
				url = config.sdp_url + url;
			}
			//ajax默认参数可覆盖
			var ajaxSetting = {
				url : '',
				type : "POST",
				dataType : "json",
				isShowToast:true,
				offline : undefined,
				expires:300000,
				contentType : "application/json;",
				appVerify:true,
				//测试 ZDcxMDYyNTUyNzE0YWFjMTMxNGZhZTQz   redAlert2
				headers:{"TerminalCode":"m_web"},
				complete:function (xhr, status) {
                    
                }
			}
			//传入参数覆盖默认参数
			$.extend(ajaxSetting,obj);
			
			//等待动画
			if(!toastCount && obj.isShowToast){
    			util.openToast('', '0', '5', '1');
			}
			toastCount=toastCount+1;
			
			// contentType
            if ($.type(ajaxSetting.data) == "string") { //字符
                ajaxSetting.contentType = "text/html;charset=UTF-8";
            } else if ($.type(ajaxSetting.data) == "object") { //对象
                //文件上传
                if(ajaxSetting.data && ajaxSetting.data.file ){
                    //ajaxSetting.contentType = "multipart/form-data;";
                    ajaxSetting.contentType = "multipart/form-data; boundary=----WebKitFormBoundary77e7DDaBWVc4KfJw";
                }else{
                    ajaxSetting.data = JSON.stringify(obj.data);
                }
            }else if($.type(ajaxSetting.data) == "number"){ //数字
               // contentType = 'application/json';
				ajaxSetting.data +="";
            }
            
			//不允许覆盖参数及方法
			ajaxSetting.url=url;
			ajaxSetting.success=function (data) {
			         //关闭当前页面的toast
			         toastCount=toastCount-1;
					//是否开启等待动画，并是否需要关闭
			         if(!toastCount && obj.isShowToast){
                        util.closeToast();
                    }
					//服务器拦截是否登录
                    if (data.content == 'unlogin') {
                        var url = window.location.pathname;
                        //alert("url:"+url+"ajaxSetting:"+url);
                        util.toLoginPage();
                        /**避免执行其他自定义动作**/
                        return;
                    } 
                    if (data.msgKey == "remembered") {
                        
                    }
                    
                    if (typeof(obj.success) == "function") {
                        obj.success(data);
                    }

            };
               ajaxSetting.error=function (e) {
                     toastCount=toastCount-1;
                     if(!toastCount && obj.isShowToast){
                        util.closeToast();
                     }
				   //初始化弹动插件
				   util.initBounce();
                    
                    if (typeof(obj.error) == "function") {
                        obj.error(e);
                    }
                    //console.debug(e);
                };
               /** ajaxSetting.complete=function (xhr, status) {
                    //关闭当前页面的toast
                    util.closeToast();
                };**/
            //ajax请求
			$.ajax(ajaxSetting);
		},
		/**
		 * 获取地址栏参数
		 */
		getUrlParam : function () {
			var params = [];
			var search = document.location.search;
			var pattern = /[?&]([\w]+)=([^&]+)/g;
			var objStr = "",matcher;
			while ((matcher = pattern.exec(search)) != null) {
				objStr += "\"" + decodeURIComponent(matcher[1]) + "\":\"" + decodeURIComponent(matcher[2]) + "\","
			}
			if (objStr && objStr.charAt(objStr.length - 1) == ",") {
				objStr = objStr.substring(0, objStr.length - 1);
			}
			if (objStr) {
				return JSON.parse("{" + objStr + "}");
			}
			return null;
		},
		/**
         * 弹出框 btn只有一个时默认yes function
         * @param value
         */
		popup : function(obj){
		   //弹框默认参数可覆盖
           var defaults = {
                    title : "",
                    content : "",
                    style:"width: 50%;font-size: 1.2em;",
                    btn: ['确认','取消'],
                    calback:[function(index){
                    		
                    	},function(index){
                    		
                    	}],
                    hasIcon : true,
                    autoClose:true,
                    touchClass : 'btntan-active'
             };
            //传入参数覆盖默认参数
            $.extend(defaults,obj);
            //询问框
            layer.open({
                title: defaults.title,
                style:defaults.style,
                content: defaults.content,
                btn: defaults.btn,
                yes: function(index){
                	if(defaults.autoClose){
                		layer.close(index);
                	}
                	defaults.calback[0]();
                    
                },
                no: function(index){
                	if(defaults.autoClose){
                		layer.close(index);
                	}
                	defaults.calback[1]();
                }
            });
		},
        /**
         * 截取日期
         * @param value
         */
        splitDate:function(date) {
            if (!date) {
                return "";
            }
            return date.split(" ")[0];
        },
         /**
         * 截取日期
         * @param value
         */
        subDate:function(date) {
            if (!date) {
                return "";
            }
            return date.substring(0,date.lastIndexOf(":"));
        },
        /**
         * 验证是否登陆
         * @param value
         */
        isLogin:function(pageName){
            var loginstate=util.locStorage.getVal('loginstate');
            try{//local只存储字符
            	loginstate=parseInt(loginstate);
            }catch(e){
            	util.toLoginPage(pageName);
            	return false;
            }
            //手动退出
            if(!loginstate){
            	util.toLoginPage(pageName);
            	return false;
            }
            //是否超过session有效期
            if(loginstate-new Date().getTime()>SESSION_LIVE){
                util.toLoginPage(pageName);
                return false;
            }
            return true;
        },/**
         * 跳转登陆页面
         * @param value
         */
        toLoginPage:function(pageName){
            //var url = window.location.pathname,loginUrl="binding-login.html";
            var url = window.location.pathname,loginUrl="login.html";
                if(!pageName){
                    //个人中心
                    if(util.isSecMulu(url)){
                        //loginUrl="../binding-login.html";
                        loginUrl="../login.html";
                    }
                }
                //进入登陆页面最后跳转地址
                util.locStorage.setVal('lastloginpage',pageName?pageName:url);
                //打开登陆
                window.location.href=loginUrl;
        },
        /**
         *处理图片路径 
         */
        upimg : function(imgpath,w,h){
                //校验
                if(!imgpath || !w || !h){
                    return util.config.attachment_url+imgpath;
                }
                var CLIPSTR="/compress",UNLINE="_",STAR="x",SUFFIX=".",SUFFIX=".",DFS="/";
                
                if(imgpath.indexOf(SUFFIX)<0 || imgpath.indexOf(DFS)<0 ){
                    return util.config.attachment_url+imgpath;
                }
                //不需要压缩的图片
                if(!util.needFilterImg(imgpath)){
                    return util.config.attachment_url+imgpath;
                }
                //分割图片路径
                var t=imgpath.substring(0,imgpath.lastIndexOf(DFS));
                var sindex=t.lastIndexOf(DFS);
                var starti=imgpath.substring(0,sindex);
                var endi=imgpath.substring(sindex,imgpath.length);
                var lindex=endi.lastIndexOf(SUFFIX);
                var e1=endi.substring(0,lindex);
                var e2=endi.substring(lindex,endi.length);
                if(imgpath.indexOf("http")<0){
                    starti=util.config.attachment_url+starti;
                }
                w=Math.round(w);
                h=Math.round(h);
                //拼接新的图片地址
                return starti+CLIPSTR+e1+UNLINE+w+STAR+h+e2;
        },
        /**
         *验证该图片是否需要压缩裁剪， 
         */
        needFilterImg:function (imgpath){
             //不是需要过滤的文件
           for (var i=0; i < FILTERIMG.length; i++) {
             if(imgpath.indexOf(FILTERIMG[i])>=0){
                        return true;
                }
           };
           return false;
        },
        /**
         * localStorage
         */
        locStorage:{
        		setVal:function(key,val){
        			if(typeof(val) == "object"){
        				val=JSON.stringify(val);
        			}
        			localStorage.setItem(key,val);
        		},
        		getVal:function(key){
        			return localStorage.getItem(key);
        		},
        		remove:function(key){
        			localStorage.removeItem(key);
        		}
        },
        //提示层
        openToast:function(msg,duration,position,type){
        	if($.type(msg) == "object"){
        		duration=msg.duration;
        		position=msg.position;
        		type=msg.type;
        	}
        	if(type == 1){//进度提示
        		//加载层
        		layer.open({type: 2});
        	}else{//消息提示
        		layer.open({
				    content: msg,
				    style: 'background-color: rgba(0, 0, 0, 0.7); color:#fff; border:none;',
				    time: duration
				});
        	}
        },
        //关闭提示层
        closeToast:function(){
        	layer.closeAll();
        }
        
	};
	//初始化page
	function initPage() {
		//新的弹动 
        util.setBounce({
			autorf:false,
			downEndCall:function(scroll){
				//下拉
				dorpDownUpdate();
			},
			upEndCall:function(scroll){
				//上拉
				pullUpUpdate();
			}
		});
     }
	//下拉
	function dorpDownUpdate() {
		if (!canGet) {
			util.openToast('小瑞出去打酱油，还没回来，请等待..', '1000', '5', '0');
			return;
		}
		//util.openToast('下拉刷新...', '0', '5', '1');
		//uexWindow.resetBounceView('0');
		pageOptions.pageNo = 1;
		pageOptions.offline=false;
		pageList();

	}
	//上拖
	function pullUpUpdate() {
	   //上一个分页请求还没回来
		if (!canGet) {
			util.openToast('小瑞出去打酱油，还没回来，请等待..', '1000', '5', '0');
			return;
		}
		//uexWindow.resetBounceView('1');
		//下一页
		var pageIndex = pageOptions.pageNo + 1;
		if (pageIndex > pageOptions.totalPage) {
			$("#pullUp .pullUpLabel").html('没有更多的数据了');
			//util.openToast('没有更多的数据了', '1000', '5', '0');
			return;
		}
		//util.openToast('上拖加载第' + matchPageNo + '页...', '0', '5', '1');
		pageOptions.pageNo =pageIndex;
		//缓存
		pageOptions.offline=false;
		pageList();
	}
	//获取分页列表
	function pageList() {
	    //再次校验超限访问
	    if (pageOptions.totalPage && pageOptions.pageNo > pageOptions.totalPage) {
            return;
        }
        //标示当前请求未回来不进行下一页加载
		canGet = false;
		//查询条件
		var queryParam = {
			pageBean : {
				pageNo : pageOptions.pageNo,
				pageSize : pageOptions.pageSize
			}
		};
		//其他分页参数
		if(pageOptions.isOtherPage){
		    $.extend(queryParam,pageOptions.queryClause);
		    queryParam.sortItemMap = pageOptions.orderClause;
		}else{//通用分页参数
		    queryParam.queryClause = pageOptions.queryClause;
		    queryParam.orderClause = pageOptions.orderClause;
		}
		var isLv = false; //是否是listview 还是自己拼接的列表
		//if ($.type(pageOptions.id) == "string") {
		if (pageOptions.id.buildListview) {
			isLv = true;
		}
		util.ajax({
			url : pageOptions.url,
			data : queryParam,
			success : function (data) {
				//列表数据
				var listArray = [],
				html = '';
				if (data.status == 1) {
					pageOptions.totalPage = data.content.totalPage;
					pageOptions.totalCount = data.content.totalCount;
					//当前数据是否非空 -非
					if (data.content && data.content.resultList && data.content.resultList.length > 0) {

					    //遍历获取的数据集
						$.each(data.content.resultList, function (i, item) {
							var obj = pageOptions.templateMethod(item, i);
							if (obj) {
								if (isLv) { //listview
									listArray.push(obj);
								} else { //自定义列表
									html += obj;
								}
							}
						});
						//分页显示填充数据
						if (pageOptions.pageNo > 1) {//当前第一页，设置数据
							if (isLv) { //listview
							    if(listArray.length>0){
    								pageOptions.id.add(listArray, 1);
							    }
							} else { //自定义列表
							    if(html){
								    $(pageOptions.id).append(html);
                                }
							}

						} else {//当前非第一页，追加数据
							if (isLv) { //listview
							    if(listArray.length>0){
								    pageOptions.id.set(listArray);
								}
							} else { //自定义列表
							    if(html){
								    $(pageOptions.id).html(html);
								}
							}
						}
						//判断数据是否超过一屏幕，显示上啦加载
						var onePageHeight = $('#wrapper').height(),scrollAreaHeight = $('#thelist').height();
						if(scrollAreaHeight>onePageHeight){
							//上拉加载html显示
							$("#pullUp").show();
						}else {
							//上拉加载html显示
							$("#pullUp").hide();
						}

					} else {//当前数据是否非空 -是
						//上拉加载html隐藏
						$("#pullUp").hide();
					    //当前index为第一页才调用，其他页可能分页到最后一页没有数据
					    if(pageOptions.pageNo == 1){
					    //空列表展示方法
						  pageOptions.emptyList();
					    }
					}
				}
				pageOptions.onComplete(data, pageOptions);
				//到底部自动加载
				if(myScroll){//刷新控件
					myScroll.refresh();
				}
			},
			error : function (e) {
				console.debug(e);
			},
			complete : function (xhr, status) {
				//关闭当前页面的toast
				util.closeToast();
				canGet = true;
			},
			offline : pageOptions.offline,
			expires:300000
		});

	}
	//获取当前时间
    function getNowDate(date){
        var now=new Date();
        if(date){
            now=date;
        }
        return 1900+now.getYear()+"-"
        +(now.getMonth()+1<10?"0"+(now.getMonth()+1):now.getMonth()+1)+"-"
        +(now.getDate()<10?"0"+now.getDate():now.getDate())+" "
        +(now.getHours()<10?"0"+now.getHours():now.getHours())+":"
        +(now.getMinutes()<10?"0"+now.getMinutes():now.getMinutes())+":"
        +(now.getSeconds()<10?'0'+now.getSeconds():now.getSeconds()); 
    }

    //var util = $.extend({},methods,config);
	window.util = util;
	
	return util;
});
/**
*
获取仓储的值
utils.locStorage.getVal('loginusername');
//扩展
$.extend(obj,[obj]);
<!-- 时间插件样式 -->
<link rel="stylesheet" href="js/plugin/mobiscroll/mobiscroll.core-2.5.2.css">
<link rel="stylesheet" href="js/plugin/mobiscroll/mobiscroll.animation-2.5.2.css">
<link rel="stylesheet" href="js/plugin/mobiscroll/mobiscroll.android-ics-2.5.2.css">
<!-- 引入包名 -->
'mobiscroll'
//时间插件js初始化
var currYear = (new Date()).getFullYear();	
			var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
		        startYear: currYear - 10, //开始年份
		        endYear: currYear + 10 //结束年份
			};
		  	$("#mainserch").mobiscroll($.extend(opt['date'], $.extend(opt['default']),{onSelect:function(html,ini){
		  		alert(html+ini)
		  	}}));
			
		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
		  	var optTime = $.extend(opt['time'], opt['default']);
		    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
		    $("#appTime").mobiscroll(optTime).time(optTime);
用法百度mobiscroll
*/
