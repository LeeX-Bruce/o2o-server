var vm = new Vue({
	el:'#rrapp',
	data:{
		weanalysisappiddailyvisittrend:{}
	},
	methods: {
		query: function() {
			$.get(baseURL + "wechatdatacube/getweanalysisappiddailyvisittrend", function(r){
				vm.weanalysisappiddailyvisittrend = r.data.list[0]
			});
		}
	},
	
	created: function(){
		this.query();
	}
});
