var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/notifications', function (message) {
        var notification = JSON.parse(message.body);
        var li = document.createElement('li');
        li.className = 'list-group-item';
        li.textContent = notification;
        document.getElementById('notifications').prepend(li);
    });
});