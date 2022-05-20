# CoinTalk - ChatRoom
Webflux를 이용한 채팅 서버

<br><br>
## 배경(Background)
코인 차트를 보며 같이 토론을 하고, 차트 캔버스에 그림을 그려서 저장 후 채팅방에서 공유하기 위해 필요한 채팅 기능입니다.

<br><br>
## 목표(Goals)

 - 코인별 단체 채팅방 구현
 - 채팅방 입장 시 이전 대화 내용 불러오기

<br><br>
## 목표가 아닌 것 (Non-goals)
 - 일대일 채팅
<br><br>
## 계획 (Plan)

 - Webflux를 이용한 Socket 통신 구현
 - 코인방 별로 분리

<br><br>
## Websocket Document


### Socket 연결 
URI : 
<code><b>/chatting/rs</b></code> 

<br><br>
 ### Chatting방 채팅 전송

> Request
<pre>
{
  "type" : "CHAT_MESSAGE",
  "payload" : {
      "roomId":"비트코인",
      "user" : {
          "username":"222"
          "userId": 4,
          "avatar":"//ssl.gstatic.com/accounts/ui/avatar_2x.png"
      },
    "message":"zxcv"
  }
}

</pre>
<br><br>
> Responses
<pre>
{
    "type": "CHAT_MESSAGE",
    "payload": {
        "roomId": "비트코인",
        "user": {
            "username": "222",
            "userId": 4,
            "avatar": "//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        },
        "message": "zxcv"
    },
    "id": 5,
    "timestamp": 1652746642008
}</code></pre>
<br><br>

<br><br>

