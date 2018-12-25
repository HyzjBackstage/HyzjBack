var phoneNum = "";
var title = "";
var toastControl = null;
var deviceId = "";
var benginDownload = false;
var lastSec = 60;
var ydFrom = "TIM";

//提示框
function showToastInfo(title, content) {
	if (!title) {
		title = "";
	}
	if (!content) {
		content = "";
	}
	setTipsControlTitle(title);
	setTipsControlContent(content);
	showControl(toastControl);
	setTimeout(function() {
		hideControl(toastControl);
	}, 1800);
}
//加载数据
//0 领取成功
//2 被别人领了
//3 自己已领取 
function loadData(flag) {
	alreadyData = "";

	var dataContainerClass = 'receive-success-container';
	var dataTipsContainerClass = 'receive-success-tips';
	var dataTips = "正在领取...";
	var dataBtnContainer = "<div class='download-btn' id='download_btn' onclick='backToShop()'>回到商城</div>";

	
	if (flag == 2) {
		// 		dataContainerClass = 'old-container';
		// 		dataTipsContainerClass = 'remind-tips-container';
		// 		dataTips = "<p class='remind-tips'>你来晚了~ </br>优惠券已经被人领走了，</br>快去找找别的优惠券吧！</br></p>";
		dataTips = "<p>领取失败！</br>已经被人抢先一步了~</p>";
	}
	if (flag == 3) {
		dataTips = "<p>领取成功！</br>您已经领取了该券哦~</p>";
	}

	alreadyData += "<div class='" + dataContainerClass + "'>";
	alreadyData += 	"<div class='" + dataTipsContainerClass + "'>";
	alreadyData += dataTips;
	alreadyData += "</div>";
	alreadyData += dataBtnContainer;
	alreadyData += "</div>";
	
	if (flag == 2 || flag == 3 || flag == 0) {
		$(".receive-time").hide();
		$(".input-container").hide();
		$(".xl-container").append(alreadyData);
	}

	if (flag == 0) {
		$(".receive-red-pack-bg").show();
		var inter = setInterval(downloadTime, 1000);

		function downloadTime() {
			var downloadText = $(".download-time").text();
			downloadText = downloadText - 1;
			$(".download-time").text(downloadText);
			if (downloadText == 0) {
				$(".receive-success-download-btn").text("跳转中...");
				clearInterval(inter);
				if (!benginDownload) {
					backToShop();
				}

			}
		}
	}

}

//验证手机格式
function getVerifyCode() {

	phoneNum = $("#phone_num").val();
	if (phoneNum == "" || phoneNum == undefined) {
		showToastInfo("", "请输入手机号!");
		return false;
	} else if (!phoneNum.match(/^1[3|4|5|8][0-9]\d{4,8}$/)) {
		showToastInfo("", "手机号码格式不正确!");
		return false;
	}
	//领取该优惠券的所有操作，通过ajax获得反馈数据，再调用如下方法
	collectCoupn(phoneNum)
}

// 领取优惠券
function collectCoupn(phoneNum) {
    $.ajax({
        type: "get",
        url: "collect/bind",
        data: "phoneNum="+phoneNum,
        dataType: "text",
        success: function (data) {
            loadData(data);
        },
        error: function () {
            alert("领取失败");
        }
    });
}



//回到商城
function backToShop() {
	window.location.href = "http://haoduodian.trunch.cn/mall/index"
}

//网页初始化
var interval = null;
$(document).ready(function() {

	if (toastControl == null) {
		toastControl = addTipsControl();
		hideControl(toastControl);
	}

	var nowTime = parseInt(new Date().getTime() / 1000);

	var interval = setInterval(countTime, 1000);

});

//计时
function countTime() {
	var timeText = $(".time-text").text();
	timeText = timeText - 1;
	$(".time-text").text(timeText);
	if (timeText == 0) {
		$(".receive-time-ft").text("快要被别人领走了哦～");
		clearInterval(interval);
	}
}

//初始化提示框
function addTipsControl() {
	var tipsNode = document.createElement('div');
	tipsNode.className = "toast-wrap";
	tipsNode.id = "self_alert";
	tipsNode.style = "display: none;";

	document.body.appendChild(tipsNode);

	var alertBox = document.createElement("div");
	alertBox.className = "toast";
	tipsNode.appendChild(alertBox);

	var title = document.createElement('p');
	title.className = "toast-text";
	title.id = "alert_title";

	alertBox.appendChild(title);

	var content = document.createElement("p")
	content.className = "toast-sub";
	content.id = "alert_content";

	alertBox.appendChild(content);

	return tipsNode;
}

//提示框相关方法
function setTipsControlTitle(title) {
	document.getElementById('alert_title').innerHTML = title;
}

function setTipsControlContent(content) {
	document.getElementById("alert_content").innerHTML = content;
}

function showControl(node) {
	if (node != null) {
		node.style.display = "block";
	}
}

function hideControl(node) {
	if (node != null) {
		node.style.display = "none";
	}
}
