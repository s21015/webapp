document.addEventListener('DOMContentLoaded', function() {
	var id = document.getElementById("titleId").value;
	setTimeout(function() {
		fetch('/100-chan/partial/' + id) // ajax.html を取得するエンドポイントを指定
			.then(response => response.text())
			.then(html => {
				const container = document.getElementById('threads-container');
				// 既存の内容をクリアし、取得した内容を挿入
				container.innerHTML = html;
			})
			.catch(error => console.error('Error:', error));
	}, 0);

	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/100-chan/partial/' + id, true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			var threadsContainer = document.getElementById('threads-container');
			threadsContainer.innerHTML = xhr.responseText;

			// AJAXリクエスト後にスクロール位置を一番下に設定
			threadsContainer.scrollTop = threadsContainer.scrollHeight;
		}
	};
	xhr.send();
});

var socket = new WebSocket("ws://192.168.140.114:8080/websocket");
socket.onmessage = function(event) {
	console.log("Message received: " + event.data);
	// ページのリロード
	updateContent();
};

function updateContent() {
	var id = document.getElementById("titleId").value;

	fetch('/100-chan/partial/' + id)
		.then(response => response.text())
		.then(html => {
			const container = document.getElementById('threads-container');
			// 新しい内容を追加
			container.innerHTML = html;
		})
		.catch(error => console.error('Error:', error));

	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/100-chan/partial/' + id, true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			var threadsContainer = document.getElementById('threads-container');
			threadsContainer.innerHTML = xhr.responseText;

			// AJAXリクエスト後にスクロール位置を一番下に設定
			threadsContainer.scrollTop = threadsContainer.scrollHeight;
		}
	};
	xhr.send();
}

function jumpToId() {
	// 検索ボックスの値を取得
	const searchValue = document.getElementById('search').value;

	// 対応するID名を生成
	const idName = searchValue;

	// 対応する要素を取得
	const element = document.getElementById(idName);

	// 要素が存在する場合、ジャンプする
	if (element) {
		// すべてのハイライトを解除
		document.querySelectorAll('.highlight').forEach(el => el.classList.remove('highlight'));

		// ハイライトを追加
		element.classList.add('highlight');

		// 要素にスクロール
		element.scrollIntoView({ behavior: 'smooth', block: 'start' });
	} else {
		alert('指定された番号の項目が見つかりません');
	}
}