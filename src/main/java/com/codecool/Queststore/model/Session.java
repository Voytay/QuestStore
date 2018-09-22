package com.codecool.Queststore.model;

import java.time.LocalDateTime;

public class Session {
    String session_id;
    LocalDateTime expirationdate;
    String user_id;

    public Session(String session_id, String user_id){
        this.session_id = session_id;
        expirationdate = LocalDateTime.now().plusMinutes(5);
        this.user_id = user_id;
    }

    public String getSessionID() {
        return session_id;
    }

    public LocalDateTime getExpirationDate() {
        return expirationdate;
    }

    public String getUserID() {
        return user_id;
    }
}
