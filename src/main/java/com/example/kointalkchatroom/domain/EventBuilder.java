package com.example.kointalkchatroom.domain;

import java.util.HashMap;
import java.util.Map;

public class EventBuilder {
    private Event.Type type;
    private PayloadBuilder payloadBuilder = new PayloadBuilder();

    public EventBuilder type(Event.Type type) {
        this.type = type;
        return this;
    }

    public PayloadBuilder withPayload() {
        return payloadBuilder;
    }

    private Event buildEvent(Payload payload) {
        return new Event(type, payload);
    }

    public class PayloadBuilder {

        private String roomId;

        private long userId;
        private String username;
        private String avatar;
        private Map<String, Object> properties = new HashMap<>();

        public PayloadBuilder roomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public PayloadBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public PayloadBuilder userUsername(String username) {
            this.username = username;
            return this;
        }

        public PayloadBuilder userAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public PayloadBuilder user(User user) {
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.avatar = user.getAvatar();
            return this;
        }

        public PayloadBuilder property(String property, Object value) {
            properties.put(property, value);
            return this;
        }


        public Event build() {
            return buildEvent(new Payload(payloadBuilder.roomId, new User(payloadBuilder.username, payloadBuilder.userId, payloadBuilder.avatar), properties));
        }
    }
}
