window.onload=function(){ 
	_ajax('education');
	_ajax("industry");
	_ajax("sex");
	_ajax("address");
}

function _ajax(name){ 
	console.log("name="+name);
	    var xmlhttp;  
		if (window.XMLHttpRequest) {
			// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
			xmlhttp = new XMLHttpRequest();
		} else {
			// IE6, IE5 浏览器执行代码
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {		
				var result = xmlhttp.responseText;
				/* console.log(result); */
				var json = eval("(" + result + ")");
				/* for(var i = 0;i < json.length;i++){
					console.log(json[i]);
				} */
				var _arr=new Array();   				
		    	for(var i = 0;i < json.length;i++){
		    		_arr.push({"value":json[i].num*100,"name":json[i].condition});
		 		}
		    	
		    	if(name=='sex'){
		    		draw_sex(_arr);
		    	}else if(name=='education'){
		    		draw_education(_arr);
		    	}else if(name=='industry'){
		    		draw_industry(_arr);
		    	}else if(name=='address'){
		    		draw_address(_arr);
		    	}   
			}
		}
		xmlhttp.open("POST", "getJson", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send("name="+name);
}

/*
 * 行业
 * */
function draw_industry(_arr){
	 console.log(_arr);
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('industry'));

    // 指定图表的配置项和数据
   	option = {
	    backgroundColor: '#566478',
	
	    title: {
	        text: '行业分布分析图',
	        left: 'center',
	        top: 20,
	        textStyle: {
	            color: '#ccc'
	        }
	    },
	
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	
	    visualMap: {
	        show: false,
	        min: 80,
	        max: 600,
	        inRange: {
	            colorLightness: [0, 1]
	        }
	    },
	    series : [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '50%'],
	            data:_arr.sort(function (a, b) { return a.value - b.value; }),
	            roseType: 'radius',
	            label: {
	                normal: {
	                    textStyle: {
	                        color: 'rgba(255, 255, 255, 0.3)'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    lineStyle: {
	                        color: 'rgba(255, 255, 255, 0.3)'
	                    },
	                    smooth: 0.2,
	                    length: 10,
	                    length2: 20
	                }
	            },
	            itemStyle: {
	                normal: {
	                    color: '#c23531',
	                    shadowBlur: 200,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            },
	
	            animationType: 'scale',
	            animationEasing: 'elasticOut',
	            animationDelay: function (idx) {
	                return Math.random() * 200;
	            }
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
/*
 * 性别
 * */   
function draw_sex(_arr){
	var _title=new Array();
	for(var i=0;i<_arr.length;i++){
		var v=
		_title.push(_arr[i].name);
	}
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById("sex"));
    option = {
    		backgroundColor: '#566478',
    		
    	    title: {
    	        text: '性别分布分析图',
    	        left: 'center',
    	        top: 20,
    	        textStyle: {
    	            color: '#ccc'
    	        }
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient: 'vertical',
    	        left: 'left',
    	        data: _title
    	    },
    	    series : [
    	        {
    	            name: '访问来源',
    	            type: 'pie',
    	            radius : '55%',
    	            center: ['50%', '60%'],
    	            data:_arr,
    	            itemStyle: {
    	                emphasis: {
    	                    shadowBlur: 10,
    	                    shadowOffsetX: 0,
    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    	                }
    	            }
    	        }
    	    ]
    	};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}	

/*
 * 学历
 * */
function draw_education(_arr){
	
	var _title=new Array();
	for(var i=0;i<_arr.length;i++){
		var v=
		_title.push(_arr[i].name);
	}
	console.log(_title);
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById("education"));
    //app.title = '学历统计分析图'; 

    option = { 
   		backgroundColor: '#566478',
   		
   	    title: {
   	        text: '学历分布分析图',
   	        left: 'center',
   	        top: 20,
   	        textStyle: {
   	            color: '#ccc'
   	        }
   	    },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:_title
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:_arr
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
} 


/*
 * 人员分布
 * */
function draw_address(_arr){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('address'));       	           
    var geoCoordMap = {
    	    "北京": [116.46, 39.92],
    	    "南京": [118.78, 32.04],
    	    "吉林": [126.57, 43.87],
    	    "上海": [121.48, 31.22],
    	    "成都": [104.06, 30.67],
    	    "哈尔滨": [126.63, 45.75],
    	    "沈阳": [123.38, 41.8],
    	    // "合肥":[117.27,31.86],
    	    "武汉": [114.31, 30.52],
    	    "石家庄": [114.48, 38.03],
    	    "天津": [117.2, 39.13],
    	    "太原": [112.53, 37.87],
    	    "西安": [108.95, 34.27],
    	    "南宁": [108.33, 22.84],
    	    "南昌": [115.89, 28.68],
    	    "济南": [117, 36.65],
    	};

    	var data = [{
    	    name: "北京",
    	    value: 38
    	}, {
    	    name: "南京",
    	    value: 147
    	}, {
    	    name: "吉林",
    	    value: 74
    	}, {
    	    name: "上海",
    	    value: 33
    	}, {
    	    name: "成都",
    	    value: 192
    	}, {
    	    name: "哈尔滨",
    	    value: 35
    	}, {
    	    name: "沈阳",
    	    value: 0
    	}, {
    	    name: "武汉",
    	    value: 36
    	}, {
    	    name: "石家庄",
    	    value: 32
    	}, {
    	    name: "天津",
    	    value: 7
    	}, {
    	    name: "太原",
    	    value: 1
    	}, {
    	    name: "西安",
    	    value: 63
    	}, {
    	    name: "南宁",
    	    value: 29
    	}, {
    	    name: "南昌",
    	    value: 48
    	}, {
    	    name: "济南",
    	    value: 61
    	}];
    	var convertData = function(data) {
    	    var res = [];
    	    for (var i = 0; i < data.length; i++) {
    	        var geoCoord = geoCoordMap[data[i].name];
    	        if (geoCoord) {
    	            res.push({
    	                name: data[i].name,
    	                value: geoCoord.concat(data[i].value)
    	            });
    	        }
    	    }
    	    return res;
    	};

    	var convertedData = [
    	    convertData(data),
    	    convertData(data.sort(function(a, b) {
    	        return b.value - a.value;
    	    }).slice(0, 6))
    	];
    	data.sort(function(a,b){
    	 return    a.value-b.value;
    	})

    	var selectedItems = [];
    	    var categoryData = [];
    	    var barData = [];
    	 //   var maxBar = 30;
    	    var sum = 0;
    	    var count = data.length;
    	    for(var i=0;i<data.length;i++){
    	    categoryData.push(data[i].name);
    	    barData.push(data[i].value);
    	    sum+=data[i].value;
    	    }
    	console.log(categoryData);
    	console.log(sum+"   "+count)
    	option = {
    	    backgroundColor: '#404a59',
    	    animation: true,
    	    animationDuration: 1000,
    	    animationEasing: 'cubicInOut',
    	    animationDurationUpdate: 1000,
    	    animationEasingUpdate: 'cubicInOut',
    	    title: [{
    	        text: '人员分布统计分析图',    	        
    	        left: 'center',
    	        textStyle: {
    	            color: '#fff'
    	        }
    	    }, {
    	        id: 'statistic',
    	        text: count ? '平均: ' +parseInt((sum / count).toFixed(4)) : '',
    	        right: 120,
    	        top: 40,
    	        width: 100,
    	        textStyle: {
    	            color: '#fff',
    	            fontSize: 16
    	        }
    	    }],
    	    toolbox: {
    	        iconStyle: {
    	            normal: {
    	                borderColor: '#fff'
    	            },
    	            emphasis: {
    	                borderColor: '#b1e4ff'
    	            }
    	        },
    	        feature: {
    	            dataZoom: {},
    	            brush: {
    	                type: ['rect', 'polygon', 'clear']
    	            },
    	            saveAsImage: {
    	                show: true
    	            }
    	        }
    	    },
    	    brush: {
    	        outOfBrush: {
    	            color: '#abc'
    	        },
    	        brushStyle: {
    	            borderWidth: 2,
    	            color: 'rgba(0,0,0,0.2)',
    	            borderColor: 'rgba(0,0,0,0.5)',
    	        },
    	        seriesIndex: [0, 1],
    	        throttleType: 'debounce',
    	        throttleDelay: 300,
    	        geoIndex: 0
    	    },
    	    geo: {
    	        map: 'china',
    	        left: '10',
    	        right: '35%',
    	        center: [117.98561551896913, 31.205000490896193],
    	        zoom: 1.5,
    	        label: {
    	            emphasis: {
    	                show: false
    	            }
    	        },
    	        roam: true,
    	        itemStyle: {
    	            normal: {
    	                areaColor: '#323c48',
    	                borderColor: '#111'
    	            },
    	            emphasis: {
    	                areaColor: '#2a333d'
    	            }
    	        }
    	    },
    	    tooltip: {
    	        trigger: 'item'
    	    },
    	    grid: {
    	        right: 40,
    	        top: 100,
    	        bottom: 40,
    	        width: '30%'
    	    },
    	    xAxis: {
    	        type: 'value',
    	        scale: true,
    	        position: 'top',
    	        boundaryGap: false,
    	        splitLine: {
    	            show: false
    	        },
    	        axisLine: {
    	            show: false
    	        },
    	        axisTick: {
    	            show: false
    	        },
    	        axisLabel: {
    	            margin: 2,
    	            textStyle: {
    	                color: '#aaa'
    	            }
    	        },
    	    },
    	    yAxis: {
    	        type: 'category',
    	        //  name: 'TOP 20',
    	        nameGap: 16,
    	        axisLine: {
    	            show: true,
    	            lineStyle: {
    	                color: '#ddd'
    	            }
    	        },
    	        axisTick: {
    	            show: false,
    	            lineStyle: {
    	                color: '#ddd'
    	            }
    	        },
    	        axisLabel: {
    	            interval: 0,
    	            textStyle: {
    	                color: '#ddd'
    	            }
    	        },
    	        data: categoryData
    	    },
    	    series: [{
    	        // name: 'pm2.5',
    	        type: 'scatter',
    	        coordinateSystem: 'geo',
    	        data: convertedData[0],
    	        symbolSize: function(val) {
    	            return Math.max(val[2] / 10, 8);
    	        },
    	        label: {
    	            normal: {
    	                formatter: '{b}',
    	                position: 'right',
    	                show: false
    	            },
    	            emphasis: {
    	                show: true
    	            }
    	        },
    	        itemStyle: {
    	            normal: {
    	                color: '#ddb926',
    	                position: 'right',
    	                show: true
    	            }
    	        }
    	    }, {
    	        //  name: 'Top 5',
    	        type: 'effectScatter',
    	        coordinateSystem: 'geo',
    	        data: convertedData[0],
    	        symbolSize: function(val) {
    	            return Math.max(val[2] / 10, 8);
    	        },
    	        showEffectOn: 'emphasis',
    	        rippleEffect: {
    	            brushType: 'stroke'
    	        },
    	        hoverAnimation: true,
    	        label: {
    	            normal: {
    	                formatter: '{b}',
    	                position: 'right',
    	                show: true
    	            }
    	        },
    	        itemStyle: {
    	            normal: {
    	                color: '#f4e925',
    	                shadowBlur: 10,
    	                shadowColor: '#333'
    	            }
    	        },
    	        zlevel: 1
    	    }, {
    	        id: 'bar',
    	        zlevel: 2,
    	        type: 'bar',
    	        symbol: 'none',
    	        itemStyle: {
    	            normal: {
    	                color: '#ddb926'
    	            }
    	        },

    	        data: data
    	    }]
    	};




    	//  var myChart= echarts.init(document.getElementById('map'));
    	//  myChart.setOption(option);
    	//myChart.on('brushselected', renderBrushed);


    	/*
    	setTimeout(function () {
    	    myChart.dispatchAction({
    	        type: 'brush',
    	        areas: [
    	            {
    	                geoIndex: 0,
    	                brushType: 'polygon',
    	                coordRange: [[119.72,34.85],[119.68,34.85],[119.5,34.84],[119.19,34.77],[118.76,34.63],[118.6,34.6],[118.46,34.6],[118.33,34.57],[118.05,34.56],[117.6,34.56],[117.41,34.56],[117.25,34.56],[117.11,34.56],[117.02,34.56],[117,34.56],[116.94,34.56],[116.94,34.55],[116.9,34.5],[116.88,34.44],[116.88,34.37],[116.88,34.33],[116.88,34.24],[116.92,34.15],[116.98,34.09],[117.05,34.06],[117.19,33.96],[117.29,33.9],[117.43,33.8],[117.49,33.75],[117.54,33.68],[117.6,33.65],[117.62,33.61],[117.64,33.59],[117.68,33.58],[117.7,33.52],[117.74,33.5],[117.74,33.46],[117.8,33.44],[117.82,33.41],[117.86,33.37],[117.9,33.3],[117.9,33.28],[117.9,33.27],[118.09,32.97],[118.21,32.7],[118.29,32.56],[118.31,32.5],[118.35,32.46],[118.35,32.42],[118.35,32.36],[118.35,32.34],[118.37,32.24],[118.37,32.14],[118.37,32.09],[118.44,32.05],[118.46,32.01],[118.54,31.98],[118.6,31.93],[118.68,31.86],[118.72,31.8],[118.74,31.78],[118.76,31.74],[118.78,31.7],[118.82,31.64],[118.82,31.62],[118.86,31.58],[118.86,31.55],[118.88,31.54],[118.88,31.52],[118.9,31.51],[118.91,31.48],[118.93,31.43],[118.95,31.4],[118.97,31.39],[118.97,31.37],[118.97,31.34],[118.97,31.27],[118.97,31.21],[118.97,31.17],[118.97,31.12],[118.97,31.02],[118.97,30.93],[118.97,30.87],[118.97,30.85],[118.95,30.8],[118.95,30.77],[118.95,30.76],[118.93,30.7],[118.91,30.63],[118.91,30.61],[118.91,30.6],[118.9,30.6],[118.88,30.54],[118.88,30.51],[118.86,30.51],[118.86,30.46],[118.72,30.18],[118.68,30.1],[118.66,30.07],[118.62,29.91],[118.56,29.73],[118.52,29.63],[118.48,29.51],[118.44,29.42],[118.44,29.32],[118.43,29.19],[118.43,29.14],[118.43,29.08],[118.44,29.05],[118.46,29.05],[118.6,28.95],[118.64,28.94],[119.07,28.51],[119.25,28.41],[119.36,28.28],[119.46,28.19],[119.54,28.13],[119.66,28.03],[119.78,28],[119.87,27.94],[120.03,27.86],[120.17,27.79],[120.23,27.76],[120.3,27.72],[120.42,27.66],[120.52,27.64],[120.58,27.63],[120.64,27.63],[120.77,27.63],[120.89,27.61],[120.97,27.6],[121.07,27.59],[121.15,27.59],[121.28,27.59],[121.38,27.61],[121.56,27.73],[121.73,27.89],[122.03,28.2],[122.3,28.5],[122.46,28.72],[122.5,28.77],[122.54,28.82],[122.56,28.82],[122.58,28.85],[122.6,28.86],[122.61,28.91],[122.71,29.02],[122.73,29.08],[122.93,29.44],[122.99,29.54],[123.03,29.66],[123.05,29.73],[123.16,29.92],[123.24,30.02],[123.28,30.13],[123.32,30.29],[123.36,30.36],[123.36,30.55],[123.36,30.74],[123.36,31.05],[123.36,31.14],[123.36,31.26],[123.38,31.42],[123.46,31.74],[123.48,31.83],[123.48,31.95],[123.46,32.09],[123.34,32.25],[123.22,32.39],[123.12,32.46],[123.07,32.48],[123.05,32.49],[122.97,32.53],[122.91,32.59],[122.83,32.81],[122.77,32.87],[122.71,32.9],[122.56,32.97],[122.38,33.05],[122.3,33.12],[122.26,33.15],[122.22,33.21],[122.22,33.3],[122.22,33.39],[122.18,33.44],[122.07,33.56],[121.99,33.69],[121.89,33.78],[121.69,34.02],[121.66,34.05],[121.64,34.08]]
    	            }
    	        ]
    	    });
    	}, 0);
    	*/

    	function renderBrushed(params) {
    	    var mainSeries = params.batch[0].selected[0];

    	    var selectedItems = [];
    	    var categoryData = [];
    	    var barData = [];
    	    var maxBar = 30;
    	    var sum = 0;
    	    var count = 0;

    	    for (var i = 0; i < mainSeries.dataIndex.length; i++) {
    	        var rawIndex = mainSeries.dataIndex[i];
    	        var dataItem = convertedData[0][rawIndex];
    	        var pmValue = dataItem.value[2];

    	        sum += pmValue;
    	        count++;

    	        selectedItems.push(dataItem);
    	    }

    	       selectedItems.sort(function (a, b) {
    	        //   return b.value[2] - a.value[2];
    	        return a.value-b.value;
    	       });

    	    for (var i = 0; i < Math.min(selectedItems.length, maxBar); i++) {
    	        categoryData.push(selectedItems[i].name);
    	        barData.push(selectedItems[i].value[2]);
    	    }

    	    this.setOption({
    	        yAxis: {
    	            data: categoryData
    	        },
    	        xAxis: {
    	            axisLabel: {
    	                show: !!count
    	            }
    	        },
    	        title: {
    	            id: 'statistic',
    	            text: count ? '平均: ' + (sum / count).toFixed(4) : ''
    	        },
    	        series: {
    	            id: 'bar',
    	            //        sort:'descending',
    	            data: barData
    	        }
    	    });
    	}
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
} 