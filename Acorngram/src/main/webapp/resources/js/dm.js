"use strict";

const msgList = document.getElementById('msg-list');

const addMsg = (msg, owner)=>{
	const li = document.createElement('li');
	li.textContent = msg;
	if(owner) li.classList.add(owner);
	return li;
}

const socketFunction = {
	onconnect : function(mine){
		this.socket.emit("userConnected", {userId: mine.id});
	},
	onsendmsg : function(mine, msg){
		this.socket.emit('/privateMsg/', {
			msg: msg, 
			sender: mine.id, 
			senderUsercode: mine.code, 
			targetUserId: mine.target.id, 
			num: mine.target.code, 
		});
	},
	onreceivemsg:{
		private: (mine, data)=>{
			let li;
			switch(data.sender){
				case mine.id:
					li= addMsg(data.msg, 'mine');
					break;
				case mine.target.id:
					li = addMsg(data.msg, 'target');
					break;
			}
			msgList.append(li);
		},
		offline: msg=>{
			msgList.append(addMsg("SYSTEM SENT MSG: "+msg));
		}
	}
};

/*
//console.log('userid : ' + ${id});
		
		
		//socket.join("private/${targetUser.id}")
		
		//client
		//socket.emit("joinAction", "room 1");
		
		//server
		
		//socket.on("joinAction", function(roomName){
			//socket.join(roomName);
		//});
		
		const socket = io('http://172.30.1.56:3000');
		
		socket.on("connect", event=>{
			console.log("socket 연결되었습니다.");
			socket.emit("userConnected", {userId: '${id}'});
			//상대 유저아이디로 만들어진 방에 접속하기
			//socket.emit("/privateMsg/", {userId: '${id}', targetUserId: '${targetUser.id}'});
			//console.log("targetUserId" + '${targetUser.id}');
		});
		
		
		function sendMsg() {
			console.log("targetUserId: " + '${targetUser.id}')
			socket.emit('/privateMsg/', {msg: $('#dm-msg').val(), sender: '${id}', targetUserId: '${targetUser.id}', num: '${targetUserCode}', usercode: '${usercode}'});
	        //socket.emit('chat message', {msg: $('#dm-msg').val(), targetUser: '${targetUser.id}', user: '${id}'});
	        $('#dm-msg').val('');
	    }
		
		socket.on('/privateMsg/', function(data){
        	$('#msg-list').append($('<li>').text(data.sender + " : " + data.msg));
        });
		
		socket.on('User Offline', function(msg){
			$('#msg-list').append($('<li>').text("SYSTEM SENT MSG: " + msg));
		});
*/
