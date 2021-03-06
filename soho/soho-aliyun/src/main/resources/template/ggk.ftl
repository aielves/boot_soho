<html>
<head>
    <title>系统安全认证</title>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
    <script charset="utf-8" src="//g.alicdn.com/sd/nch5/index.js"></script>
</head>
<body>
<div id="your-dom-id" style="margin-left:auto;margin-right:auto;"></div>
<input type="hidden" id="callurl" value="${callurl!''}"/>
<script>
    var nc_token = ["FFFF000000000179AE04", (new Date()).getTime(), Math.random()].join(':');
    var nc_scene = "sc_login_h5";
    var NC_Opt = {
        renderTo: "#your-dom-id",
        appkey: "FFFF000000000179AE04",
        scene: nc_scene,
        token: nc_token,
        trans: {'key1': "code0"},
        is_Opt: 0,
        type: "scrape",
        width: 300,
        height: 100,
        isEnabled: true,
        language: 'cn',
        times: 5,
        objects: ["//img.alicdn.com/tps/TB1BT9jPFXXXXbyXFXXXXXXXXXX-80-80.png"],
        apimap: {
            // 'uab_Url': '//aeu.alicdn.com/js/uac/909.js',
        },
        elements: [
            '//img.alicdn.com/tfs/TB17cwllsLJ8KJjy0FnXXcFDpXa-50-74.png',
            '//img.alicdn.com/tfs/TB17cwllsLJ8KJjy0FnXXcFDpXa-50-74.png'
        ],
        bg_back_prepared: '//img.alicdn.com/tps/TB1skE5SFXXXXb3XXXXXXXXXXXX-100-80.png',
        bg_front: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABQCAMAAADY1yDdAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAADUExURefk5w+ruswAAAAfSURBVFjD7cExAQAAAMKg9U9tCU+gAAAAAAAAAIC3AR+QAAFPlUGoAAAAAElFTkSuQmCC',
        obj_ok: '//img.alicdn.com/tfs/TB1rmyTltfJ8KJjy0FeXXXKEXXa-50-74.png',
        bg_back_pass: '//img.alicdn.com/tfs/TB1KDxCSVXXXXasXFXXXXXXXXXX-100-80.png',
        obj_error: '//img.alicdn.com/tfs/TB1q9yTltfJ8KJjy0FeXXXKEXXa-50-74.png',
        bg_back_fail: '//img.alicdn.com/tfs/TB1w2oOSFXXXXb4XpXXXXXXXXXX-100-80.png',
        upLang: {
            "cn": {
                _ggk_guide: "为确保您的操作安全，请刮出两面盾牌",
                _ggk_success: "恭喜您成功刮出盾牌<br/>继续下一步操作吧",
                _ggk_loading: "正在加载中",
                _ggk_fail: ['呀，盾牌不见了<br/>请', "javascript:NoCaptcha.reset()", '再来一次', '或', "http://survey.taobao.com/survey/QgzQDdDd?token=%TOKEN", '反馈问题'],
                _ggk_action_timeout: ['我等得太久啦<br/>请', "javascript:NoCaptcha.reset()", '再来一次', '或', "http://survey.taobao.com/survey/QgzQDdDd?token=%TOKEN", '反馈问题'],
                _ggk_net_err: ['网络实在不给力<br/>请', "javascript:NoCaptcha.reset()", '再来一次', '或', "http://survey.taobao.com/survey/QgzQDdDd?token=%TOKEN", '反馈问题'],
                _ggk_too_fast: ['您刮得太快啦<br/>请', "javascript:NoCaptcha.reset()", '再来一次', '或', "http://survey.taobao.com/survey/QgzQDdDd?token=%TOKEN", '反馈问题']
            }
        },
        callback: function (data) { //成功回调
            var callurl = document.getElementById('callurl').value;
            location.href = '/security/ggk/valid?sessionid=' + data.sessionId + '&sig=' + data.sig + '&token=' + nc_token + '&scene=' + nc_scene + '&callurl=' + callurl;
        },
        failCallback: function (data) { //拦截or失败回调
        },
        error: function (data) { //异常回调
        }
    };
    var nc = NoCaptcha.init(NC_Opt);
    nc.setEnabled(true); // 启动
</script>
</body>
</html>