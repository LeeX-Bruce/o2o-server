$(function () {
	var columns = [
        { checkbox: true, align: 'center' },
		{ title: '管理员', field: 'username'},
		{ title: '邮箱', field: 'email'},
		{ title: '手机号', field: 'mobile'},
		{ title: '状态', field: 'status',
			formatter: function (value, row, index) {
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
		}}, 
		{ title: '创建时间', field: 'createTime'}
	];
	
	$("#table").bootstrapTable({
        url: baseURL + 'sys/user/list',
        cache: false,
        striped: true,
        pagination: true, //显示分页
        pageSize: 10,  //每页记录数
        pageNumber: 1, //初始化加载第一页，默认第一页
        sidePagination: 'server',
        columns: columns,
        queryParams: function (params) {
        	return {
				 //params.offset： SQL语句起始索引
	        	page: params.offset / params.limit + 1,
				// params.limit：每页显示的记录条数
	        	limit: params.limit,
				username: vm.q.username

        	}
        }
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: null
		},
		showList: true,
		title:null,
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		}
	},
	methods: {
		query: function () {

			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			//status:1 新增管理员的状态默认为正常
			//roleIdList:[] 管理员角色列表
			vm.user = {status:1, roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
		},
		update: function () {
			var userId = getSelectedVal("userId");
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
		},
		del: function () {
			var userIds = getSelectedVals("userId");
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function () {
			//userid为空就映射到用户添加，userid不为空则映射路径为修改
			var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
				// stringify()用于从一个对象解析出字符串
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getUser: function(userId){
			$.get(baseURL + "sys/user/info/"+userId, function(r){
				vm.user = r.user;
				vm.user.password = null;
			});
		},
		getRoleList: function(){
			$.get(baseURL + "sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		reload: function () {
			vm.showList = true;
			$('#table').bootstrapTable('refresh',  {
				// postData:{'username': vm.q.username},
				pageNumber: 1
			});
		}
	}
});