package com.appointments.service;

import com.appointments.model.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SessionService {
    private Map<String, Session> cacheSession = new HashMap<>();

    public void addSessionToCache(Session session){
        cacheSession.put(session.getToken(), session);
    }

    public void removeSessionFromCache(String token){
        cacheSession.remove(token);
    }

    public Session getSession(String token){
        return cacheSession.get(token);
    }
}
