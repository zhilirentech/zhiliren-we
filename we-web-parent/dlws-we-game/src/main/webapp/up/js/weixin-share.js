/**
 * @param res
 * res参数
 * clientUrl: 浏览器当前的url
 * targetUrl: 要分享的url
 * title: 分享标题
 * desc: 分享描述
 * link: 分享链接
 * imgUrl: 分享图标
 * type: 分享类型,music、video或link，不填默认为link
 * dataUrl: 如果type是music或video，则要提供数据链接，默认为空
 */
function weixinShare(res) {
				//请求后台，获取jssdk支付所需的参数
	
				//去掉连接中的#
				clientUrl = res.clientUrl.split('#')[0];
				var shareUrl= encodeURIComponent(clientUrl);
				$.ajax({
					type : 'post',
					url : res.reqPath,
					dataType : 'json',
					data : {
						"clientUrl" : clientUrl
					//当前页面所在的浏览器URL全路径,由于该支付为jssdk支付，所以需要url地址.参与后台sign签名
					},
					cache : false,
					error : function() {
						//alert("系统错误，请稍后重试");
						return false;
					},
					success : function(data) {
						//微信支付功能只有微信客户端版本大于等于5.0的才能调用
						var return_date = eval(data);
						
						if (parseInt(data[0].agent) < 5) {
							alert("您的微信版本低于5.0无法使用微信支付");
							return;
						}
						//JSSDK支付所需的配置参数，首先会检查signature是否合法。
						wx.config({
							debug : !true, //开启debug模式，测试的时候会有alert提示
							appId : return_date[0].appId, //公众平台中-开发者中心-appid
							timestamp : return_date[0].config_timestamp, //时间戳
							nonceStr : return_date[0].config_nonceStr, //随机字符串,不长于32位
							signature : return_date[0].config_sign, //这里的signature是后台使用SHA1签名算法得出，不是MD5，与下面的wx.chooseWXPay中的paySign不同，下面的paySign是后台使用MD5加密得出
							jsApiList : [ 'onMenuShareAppMessage','onMenuShareTimeline' ]
						});
						//上方的config检测通过后，会执行ready方法
						wx.ready(function() {
							wx.onMenuShareAppMessage({
							    title: res.title, // 分享标题
							    desc: res.desc, // 分享描述
							    link: res.link+"?shareUrl="+res.targetUrl, // 分享链接
							    imgUrl:res.imgUrl, // 分享图标
							    type: res.type, // 分享类型,music、video或link，不填默认为link
							    dataUrl: res.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
							    success: function () { 
							    },
							    cancel: function () { 
							        // 用户取消分享后执行的回调函数
							    }
							});
							
							//分享朋友圈
						//	alert("分享朋友圈title"+title);
							wx.onMenuShareTimeline({
								title: res.title, // 分享标题
							    desc: res.desc, // 分享描述
							    link: res.link+"?shareUrl="+res.targetUrl, // 分享链接
							    imgUrl:res.imgUrl, // 分享图标
							    type: res.type, // 分享类型,music、video或link，不填默认为link
							    dataUrl: res.dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
							    success: function () { 
							    },
							    cancel: function () { 
							        // 用户取消分享后执行的回调函数
							    }
							});
		
						});
						wx.error(function(res) {
							//alert(res.errMsg);
						});
					}
				});
			}
