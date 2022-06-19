$(function () {
	
	var columns = [
        { checkbox: true, align: 'center' },
		{ title: '订单编号', field: 'orderNumber', formatter: function(value, options, row){
			return "<a class='a-detail' onClick='vm.detail("+row.orderId+")'>"+value+"</a>";
		}},
		{ title: '接单人', field: 'user.nickname'},
		{ title: '地区', field: 'orderGoods.location'},
		{ title: '帮忙主题', field: 'orderGoods.goodsName'},

		{ title: '赏金', field: 'orderGoods.price'},
		{ title: '任务期限/小时', field: 'orderGoods.qtime'},

		{ title: '订单状态', field: 'orderStatus', formatter: function(value, options, row){
			if(value == 0){
				return '<span class="label label-default">已取消</span>';
			}else if(value == 1){
				return '<span class="label label-primary">待完成</span>';
			}else if(value == 2){
				return '<span class="label label-success">已完成</span>';
			}
		}},
		{ title: '创建时间', field: 'createTime'}
		];
	
	$("#table").bootstrapTable({
        url: baseURL + 'o2o/order/list',
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

				orderNumber: vm.q.orderNumber

        	}
        }
	});
	
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		order: {
			orderShipment: {}
		},
		q: {

			orderNumber:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.order = {};
		},
		update: function (event) {
			var orderId = getSelectedVal("orderId");
			if(orderId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderId)
		},
		saveOrUpdate: function (event) {
			var url = vm.order.orderId == null ? "o2o/order/save" : "o2o/order/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.order),
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
			var orderIds = getSelectedVals("orderId");
			if(orderIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "o2o/order/delete",
                    contentType: "application/json",
				    data: JSON.stringify(orderIds),
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
		getInfo: function(orderId){
			$.get(baseURL + "o2o/order/info/"+orderId, function(r){
                vm.order = r.order;
            });
		},
		reload: function (event) {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {pageNumber: 1});
		},
		detail: function(id){
			vm.showList = false;
			vm.title = "详情";
			vm.getInfo(id);
		},
		sendGoods: function(){
			var orderId = getSelectedVal();
			if(orderId == null){
				return ;
			}
			
			if(orderId == null){
				return ;
			}

			var html = '';

			html += '<div style="padding:10px">快递单号：<input name="courierNum" /></div>';

			layer.open({
			  type: 1,
			  shade: false,
			  content: html,
			  btn: ['确定'],
			  yes: function(index){
				  
				  var courierNum = $("input[name='courierNum']").val();
				  if(courierNum == ""){
					  alert("请填写快递单号");
					  return;
				  }
				  layer.close(index);
				  $.ajax({
						type: "POST",
					    url: baseURL + "o2o/order/sendGoods/" + orderId,
					    data: 'courierNum=' + courierNum,
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
			  }
			});
		}
	}
});