
$(function () {
    //选择到分钟
    $('#date1').datetimepicker({
        format: 'yyyy-mm-dd', //格式设置
        language: 'zh-CN',  //日期
    }).on('changeDate', function (ev) {
        console.info($('#date1').val()); //获取选择结果，2018-05-05 20:10
        console.info(ev.date);//返货js Date 类型GMT格式 ，Thu May 17 2018 10:30:38 GMT+0800 (中国标准时间)
        console.info(ev.date.valueOf());//返回 数字格式时间，1526524238000
    });
}); 