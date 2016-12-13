//任务ID
var jobId = T.p("jobId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增定时任务",
		schedule:{}
	},
	created: function() {
		if(jobId != null){
			this.title = "修改定时任务";
			this.getScheduleJob(jobId)
		}
    },
	methods: {
		getScheduleJob: function(jobId){
			$.get("../sys/schedule/info/"+jobId, function(r){
				vm.schedule = r.schedule;
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.schedule.jobId == null ? "../sys/schedule/save" : "../sys/schedule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.schedule),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});