(function(){
		var pathName =  window.document.location.pathname;
		var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		window.appName = window.location.origin+projectName+"/";
		if(appName.indexOf('jsessionid')!=-1){
			appName += '/';
		}
		require.config({
			baseUrl: appName,
			paths : {
				'jquery'   :'js/jquery-1.11.1.min',
				'utils'    :'js/utils',
				'layer'    :'plugins/layer-v2.4/layer',
				'bootstrap':'plugins/bootstrap-3.3.5-dist/js/bootstrap.min',
				'waterfall':'js/waterfall',
				'base64'   :'js/base64',
			},
			shim:{
				/*'jmobile':{
					deps: ['jquery']
				},
				'datebox':{
					deps: ['jquery','jquery-ui']
				}*/
			}
		});
		
		require.config({
			baseUrl: appName,
			packages: [/* 
			           {
			               name: 'echarts',
			               location: 'lib/echarts',
			               main: 'echarts'
			           },
			           {
			               name: 'zrender',
			               location: 'lib/zrender',
			               main: 'zrender'
			           } */
			       ]
		});
	})();