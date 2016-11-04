define(function(require, exports, module) {
	var init = function(options) {
		
		
		var options = $.extend({},options);
		
		var clickListener;
		map = new AMap.Map(options.itemId, {
	    	resizeEnable: true,
            zoom: 18,
            center: [116.39,39.9]
	    });
		//通过AMap.service加载检索服务
	    AMap.service(['AMap.Geocoder','AMap.Autocomplete','AMap.PlaceSearch','AMap.ToolBar'],function(){
	        var geocoder = new AMap.Geocoder({
	            city: "010"//城市，默认：“全国”
	        });
	        var marker = new AMap.Marker({
	            map:map,
	            bubble:true
	        })
	        var autocomplete= new AMap.Autocomplete({
	        	city: "北京", //城市，默认全国
	            input: "textarea"//使用联想输入的input的id
	        });
	        var placeSearch = new AMap.PlaceSearch({
	            city:'北京',
	            map:map
	        })
	        AMap.event.addListener(autocomplete, "select", function(e){
	            //TODO 针对选中的poi实现自己的功能
	            placeSearch.search(e.poi.name);
	        });
	        //放大缩小
	        //map.addControl(new AMap.ToolBar());
	        
	        
	        
	        
	        var textarea = $('#textarea');
	        var message = $('#message');
	        map.on('click',function(e){
	            marker.setPosition(e.lnglat);
	            $('input[id="lngX"]').val(e.lnglat.lng);
	            $('input[id="lngY"]').val(e.lnglat.lat);
	        })
	        //绑定按钮事件
	        $('#btnPrse').live('click',function(){
	        	var lnglatXY = [$('input[id="lngX"]').val(),$('input[id="lngY"]').val()];
	        	
	        })
	        /*if(we.utils.isEmpty($('#textarea').val())){
	        	$('.prse').addClass('btn-gray');
	        	return;
	        }
	        $('#textarea').live('blur',function(){
	        	if(we.utils.isEmpty($(this).val())){
		        	$('.prse').addClass('btn-gray');
		        	return;
		        }else{
		        	$('.prse').removeClass('btn-gray');
		        }
	        })*/
	        
	        //保存
	        $('.prse').live('click',function(){
	        	
	        	var provinceUuid = $('.province').attr('uuid');
		        var cityUuid = $('.city').attr('uuid');
		        var areaUuid = $('.country').attr('uuid');
		        var province = $('div[box='+provinceUuid+'] .menu-l');
		        var city = $('div[box='+cityUuid+'] .menu-l');
		        var area = $('div[box='+areaUuid+'] .menu-l');
	        	var provinceAdd = province.html().replace(/\s/g,'');
	        	var cityAdd = city.html().replace(/\s/g,'');
	        	var areaAdd = area.html().replace(/\s/g,'');
	        	var detailedAdd = textarea.val().replace(/\s/g,'');
	        	var teacherId = $('input[name="teacherId"][type="hidden"]').val();
	        	var isDefault = $('input[name="defadd"]').attr('checked') == 'checked';
	        	
	        	var provinceCode = $('.sel-sub[uuid="'+provinceUuid+'"]').find('.crt').attr('val');
	        	var cityCode = $('.sel-sub[uuid="'+cityUuid+'"]').find('.crt').attr('val');
	        	var countryCode = $('.sel-sub[uuid="'+areaUuid+'"]').find('.crt').attr('val');
	        	var country = $('li[val="'+countryCode+'"]').html();
	        	var code = $('input[name="addressId"]').val();
	        	
	        	//seajs.log(provinceCode+','+cityCode+','+countryCode);
	        	
	        	var address = detailedAdd;
	        	
	        	geocoder.getLocation(address,function(status,result){
	        		var adcode = result.geocodes[0].adcode;
	        		if(areaAdd!==result.geocodes[0].addressComponent.district){
		        		$wedialog.dialog({
		        			title:'温馨提示',
		        			content:'小秘书发现你输入的地址似乎在"'+result.geocodes[0].addressComponent.district+'"<br/>是否需要小秘书帮你把"'+areaAdd+'"修改为"'+result.geocodes[0].addressComponent.district+'"呢？',
		        			ok:function(){
	        					if(we.utils.isEmpty(result.geocodes[0].addressComponent.city)){
	        						$('.viemCity').hide();
	        					}
	        					$ajax.weAjax({
	        						url:'/address/preservation.json',
	        						data:{
	        							"province":result.geocodes[0].addressComponent.province,
	        							"city":result.geocodes[0].addressComponent.city,
	        							"country":result.geocodes[0].addressComponent.district,
	        							"streetName":detailedAdd,
	        							"isDefault":isDefault,
	        							"longitude":result.geocodes[0].location.lng,
	        							"latitude":result.geocodes[0].location.lat,
	        							"teacherId":teacherId,
	        							"provinceCode":provinceCode,
	        							"cityCode":cityCode,
	        							"countryCode":adcode,
	        							"id":code
	        						},
	        						success:function(data){
	        							if(areaAdd==result.geocodes[0].addressComponent.district){
	        								location.reload();
	        							}
	        							location.reload();
	        						}
	        					})
	        					
		        			},
		        			okValue:'帮我修改',
		        			cancel:function(){
		        				if(we.utils.isEmpty(result.geocodes[0].addressComponent.city)){
	        						$('.viemCity').hide();
	        					}
		        				$ajax.weAjax({
	        						url:'/address/preservation.json',
	        						data:{
	        							"province":result.geocodes[0].addressComponent.province,
	        							"city":result.geocodes[0].addressComponent.city,
	        							"country":country,
	        							"streetName":detailedAdd,
	        							"isDefault":isDefault,
	        							"longitude":result.geocodes[0].location.lng,
	        							"latitude":result.geocodes[0].location.lat,
	        							"teacherId":teacherId,
	        							"provinceCode":provinceCode,
	        							"cityCode":cityCode,
	        							"countryCode":countryCode,
	        							"id":code
	        						},
	        						success:function(data){
	        							if(areaAdd==result.geocodes[0].addressComponent.district){
	        								location.reload();
	        							}
	        							location.reload();
	        						}
	        					})
		        				
		        			},
		        			cancelValue:'不修改'
		        		})
		        	}else{
		        		$ajax.weAjax({
    						url:'/address/preservation.json',
    						data:{
    							"province":result.geocodes[0].addressComponent.province,
    							"city":result.geocodes[0].addressComponent.city,
    							"country":result.geocodes[0].addressComponent.district,
    							"streetName":detailedAdd,
    							"isDefault":isDefault,
    							"longitude":result.geocodes[0].location.lng,
    							"latitude":result.geocodes[0].location.lat,
    							"teacherId":teacherId,
    							"provinceCode":provinceCode,
    							"cityCode":cityCode,
    							"countryCode":countryCode,
    							"id":code
    						},
    						success:function(data){
    							if(areaAdd==result.geocodes[0].addressComponent.district){
    								location.reload();
    							}
    							location.reload();
    						}
    					})
    					
		        	}
				})
	        	/*map.getCity(function(result){
	        		var provinceUuid = $('.province').attr('uuid');
			        var cityUuid = $('.city').attr('uuid');
			        var areaUuid = $('.country').attr('uuid');
			        var province = $('div[box='+provinceUuid+'] .menu-l');
			        var city = $('div[box='+cityUuid+'] .menu-l');
			        var area = $('div[box='+areaUuid+'] .menu-l');
		        	var provinceAdd = province.html().replace(/\s/g,'');
		        	var cityAdd = city.html().replace(/\s/g,'');
		        	var areaAdd = area.html().replace(/\s/g,'');
		        	var detailedAdd = textarea.val().replace(/\s/g,'');
		        	if(areaAdd!==result.district){
		        		$wedialog.dialog({
		        			title:'温馨提示',
		        			content:'小秘书发现你输入的地址似乎在"'+result.district+'"<br/>是否需要小秘书帮你把"'+areaAdd+'"修改为"'+result.district+'"呢？',
		        			ok:function(){
		        				seajs.log(result);
		        				$('.country option').each(function(){
		        					if($(this).html()==result.district){
		        						$(this).attr('selected',true);
		        						area.html(result.district);
		        					}
		        				});
		        				
		        				provinceAdd
		        				cityAdd
		        				result.district
		        				detailedAdd
		        				$('.viemProv').html(provinceAdd);
		        				$('.viemCity').html(cityAdd);
		        				$('.viemArea').html(result.district);
		        				$('.viemDeta').html(detailedAdd);
		        				
	        					var region = '<dd><p><span class="fir viemProv">'+
	        									provinceAdd+'</span><span class="viemCity">'+
	        									cityAdd+'</span><span class="sec viemArea">'+
		       							  	 	result.district+'</span></p><span class="viemDeta">'+
		       							  	 	detailedAdd+'</span><span class="thi">'+
		                                        '<a class="revise">修改</a>'+
		                                        '<a>删除</a>'+
		                                        '<a class="special">默认地址</a>'+
		                                        '</span>'+
		       							  	 '</dd>';
	        					$('.con-biaoge dt').after(region);
	        					$ajax.weAjax({
	        						url:'/address/preservation.json',
	        						data:{
	        							"province":provinceAdd,
	        							"city":cityAdd,
	        							"country":result.district,
	        							"streetCode":
	        							"streetName":detailedAdd
	        							"status":
	        							"longitude":
	        							"latitude":
	        						},
	        						success:function(data){
	        							seajs.log(data);
	        						}
	        					})
	        					if(we.utils.isEmpty(result.city)){
	        						$('.viemCity').hide();
	        					}
		        			},
		        			okValue:'帮我修改',
		        			cancel:function(){
		        				$('.viemProv').html(provinceAdd);
		        				$('.viemCity').html(cityAdd);
		        				$('.viemArea').html(result.district);
		        				$('.viemDeta').html(detailedAdd);
		        				var region = '<li><span class="viemProv">'+
		        							 	provinceAdd+'</span><span class="viemCity">'+
		        							 	cityAdd+'</span><span class="viemArea">'+
		        							 	areaAdd+'</span><span class="viemDeta">'+
		        							 	detailedAdd+'</span>'+
		        							 '</li>';
		        				$('.addCon').html(region);
		        			},
		        			cancelValue:'不修改'
		        		})
		        	}else{
		        		var region = '<dd><span class="fir viemProv">'+
						provinceAdd+'</span><span class="viemCity">'+
						cityAdd+'</span><span class="sec viemArea">'+
					  	 	result.district+'</span><span class="viemDeta">'+
					  	 	detailedAdd+'</span>'+
					  	 '</dd>';
						$('.con-biaoge dt').after(region);
						if(we.utils.isEmpty(result.city)){
							$('.viemCity').hide();
						}
		        	}
		        	
		        	
	        	});*/
	        })
	        
	        //设置位置
	       /* var setAdd = function(){
				var address = $('#textarea').val();
				geocoder.getLocation(address,function(status,result){
					seajs.log(result);
					if(status=='complete'&&result.geocodes.length){
						marker.setPosition(result.geocodes[0].location); 
						map.setCenter(marker.getPosition());
						message.innerHTML = '';
					}else{
						message.innerHTML = '无法获取位置';
					}
				})
	        }*/
	        //修改
	       $('.reviseAdd').live('click',function(){
	        	var code = $(this).attr('code');
	        	$ajax.weAjax({
	        		url:'/address/modify.json',
	        		data:{
	        			"teachingAddressId":code
	        		},
	        		success:function(data){
	        			var province = data.teachingaddress.province;
	        			var city = data.teachingaddress.city;
	        			var country = data.teachingaddress.country;
	        			var streetName = data.teachingaddress.streetName;
	        			var countryCode = data.teachingaddress.countryCode;
	        			var status = data.teachingaddress.status;
	        			var defadd = $('input[name="defadd"][type="checkbox"]');
	        			
	        			$('input[name="addressId"]').val(code);
	        			$('.country').attr('val',country);
	        			$('.country').find('option[value="'+countryCode+'"]').attr('selected','selected');
	        			we.utils.initUI($('#country'));
	        			
	        			status==true?defadd.attr('checked',true):defadd.attr('checked',false);
	        			
	        			geocoder.getLocation(streetName, function(status, result) {
	        				if(status=='complete'&&result.geocodes.length){
	        					$('#textarea').val(streetName);
	        					$('#textarea').focus().select();
	        	                marker.setPosition(result.geocodes[0].location);
	        	                map.setCenter(marker.getPosition())
	        	                document.getElementById('message').innerHTML = ''
	        	              }else{
	        	                document.getElementById('message').innerHTML = '获取位置失败'
	        	              }
	        	        });
	        		}
	        	})
	        })
	        
	        //删除
	        $('.deleteAdd').live('click',function(){
	        	var code = $(this).attr('code');
	        	$wedialog.confirm({
	        		title:'温馨提示',
	        		content:'确定删除么？',
	        		ok:function(){
	        			$ajax.weAjax({
	    	        		url:'/address/delete.json',
	    	        		data:{
	    	        			"teachingAddressId":code
	    	        		},
	    	        		success:function(data){
	    	        			seajs.log(data);
	    	        			if(data.message=='fail'){
	    	        				$wedialog.alert({
	    	        				    type:'success',
	    	        				    title:'温馨提示',
	    	        				    content:'抱歉。最少要留下一个地址。',
	    	        				    padding:'30px 100px',
	    	        				    ok:function(){},
	    	        				    okValue:'知道啦'
	    	        				});
	    	        				return;
	    	        			}
	    	        			location.reload();
	    	        		}
	    	        	})
	        		}
	        	})
	        })
	        
	        //设置默认地址
	        $('.setDefAdd').live('click',function(){
	        	var code = $(this).attr('code');
	        	$wedialog.confirm({
	        		title:'温馨提示',
		        		content:'确定设置默认地址么？',
	        		ok:function(){
	        			$ajax.weAjax({
	    	        		url:'/address/modifyDefaultAddress.json',
	    	        		data:{
	    	        			"teachingAddressId":code
	    	        		},
	    	        		success:function(data){
	    	        			seajs.log(data);
	    	        			location.reload();
	    	        		}
	    	        	})
	        		}
	        	})
	        })
	        
	        //保存位置
	        /*$('#btnPrse').live('click',function(){
	        	var provinceUuid = $('.province').attr('uuid');
		        var cityUuid = $('.city').attr('uuid');
		        var areaUuid = $('.country').attr('uuid');
		        var province = $('div[box='+provinceUuid+'] .menu-l');
		        var city = $('div[box='+cityUuid+'] .menu-l');
		        var area = $('div[box='+areaUuid+'] .menu-l');
	        	var provinceAdd = province.html();
	        	var cityAdd = city.html();
	        	var areaAdd = area.html();
	        	var detailedAdd = textarea.val();
	        	map.getCity(function(result){
	        		if(areaAdd!==result.district){
	        			$wedialog.dialog({
		        			title:'温馨提示',
		        			content:'小秘书发现你输入的地址似乎在"'+result.district+'"<br/>是否需要小秘书帮你把"'+areaAdd+'"修改为"'+result.district+'"呢？',
		        			ok:function(){
		        				$('.country option').each(function(){
		        					if($(this).html()==result.district){
		        						$(this).attr('selected',true);
		        						area.html(result.district);
		        					}
		        				});
		        			},
		        			okValue:'帮我修改',
		        			cancel:function(){

		        			},
		        			cancelValue:'不修改'
		        		})
	        		}
	        	})
	        })
	        $('#btnBack').live('click',function(){
	        	setAdd();
	        })*/

	    });
	}
	
	return{
		init:init
	}
});