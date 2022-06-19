$(function () {
	
	var columns = [
        { checkbox: true, align: 'center' },
      	{ title: '图片', field: 'picUrl',
			formatter: function (value, row, index) {
				return '<img width="60px" height="60px" src="'+value+'" />';
			}	
		}, 
		{ title: '帮忙主题', field: 'goodsName'},
		{ title: '分类', field: 'category.categoryName'},
		{ title: '价格', field: 'price'},

		{ title: '时间/小时', field: 'qtime'},


		{ title: '发布人', field: 'user.nickname'},
		{ title: '社区地址', field: 'location'},
		{ title: '状态', field: 'status', formatter: function(value, row, index){
			if(value == 0){
				return '<span class="btn btn-primary btn-xs">未接单</span>';
			}else if(value == 1){
				return '<span class="btn btn-success btn-xs">已接单未完成</span>';
			}else if(value == 2){
				return '<span class="btn btn-warning btn-xs">已完成</span>';
			}
		}},
		{ title: '发布时间', field: 'createTime'}
		];
	
	$("#table").bootstrapTable({
        url: baseURL + 'o2o/goods/list',
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        sidePagination: 'server',
        columns: columns,
        queryParams: function (params) {
        	return {
	        	page: params.offset / params.limit + 1,
	        	limit: params.limit,

				goodsName: vm.q.goodsName
        	}
        }
	});
	
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		goods: {
			picUrls: []
		},
		categoryList: [],
		q:{
			goodsName: null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.goods = {
				picUrls: [],
				categoryId : ""
			};
			
			this.getCategoryList();
		},
		update: function (event) {
			var goodsId = getSelectedVal("goodsId");
			if(goodsId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.goods = {
    			picUrls: []
    		};
            
            vm.getInfo(goodsId);
            this.getCategoryList();
		},
		saveOrUpdate: function (event) {
			if(vm.validator()){
				return;
			}
			
			var url = vm.goods.goodsId == null ? "o2o/goods/save" : "o2o/goods/update";
			vm.goods.picUrl = vm.goods.picUrls[0];
			vm.goods.describe = ue.getContent();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goods),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var goodsIds = getSelectedVals("goodsId");
			if(goodsIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "o2o/goods/delete",
                    contentType: "application/json",
				    data: JSON.stringify(goodsIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		// confirmOk: function(){
		// 	var goodsIds = getSelectedVals("goodsId");
		// 	if(goodsIds == null){
		// 		return ;
		// 	}
		//
		// 	confirm('确定要审核选中的记录？', function(){
		// 		$.ajax({
		// 			type: "POST",
		// 		    url: baseURL + "o2o/goods/confirmOk",
        //             contentType: "application/json",
		// 		    data: JSON.stringify(goodsIds),
		// 		    success: function(r){
		// 				if(r.code == 0){
		// 					alert('操作成功', function(index){
		// 						vm.reload();
		// 					});
		// 				}else{
		// 					alert(r.msg);
		// 				}
		// 			}
		// 		});
		// 	});
		// },
		// confirmFail: function(){
		// 	var goodsIds = getSelectedVals("goodsId");
		// 	if(goodsIds == null){
		// 		return ;
		// 	}
		//
		// 	confirm('确定要审核选中的记录？', function(){
		// 		$.ajax({
		// 			type: "POST",
		// 		    url: baseURL + "o2o/goods/confirmFail",
        //             contentType: "application/json",
		// 		    data: JSON.stringify(goodsIds),
		// 		    success: function(r){
		// 				if(r.code == 0){
		// 					alert('操作成功', function(index){
		// 						vm.reload();
		// 					});
		// 				}else{
		// 					alert(r.msg);
		// 				}
		// 			}
		// 		});
		// 	});
		// },
		getInfo: function(goodsId){
			$.get(baseURL + "o2o/goods/info/"+goodsId, function(r){
                vm.goods = r.goods;
                ue.setContent(r.goods.describe);
            });
		},
		getCategoryList: function(){
			$.get(baseURL + "o2o/category/getAll", function(r){
				vm.categoryList = r.categoryList;
			});
		},
		reload: function (event) {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {pageNumber: 1});
		},
		validator: function () {
            if(vm.goods.picUrls.length == 0){
                alert("请选择任务图片");
                return true;
            }

            if(isBlank(vm.goods.goodsName)){
                alert("请填写帮忙主题");
                return true;
            }
            
            if(isBlank(vm.goods.price)){
                alert("请填写价格");
                return true;
            }
        }
	}
});