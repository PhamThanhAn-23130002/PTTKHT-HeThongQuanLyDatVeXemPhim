package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session {
    private String sessionId;
    private String userId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    private static List<Session> sessions = new ArrayList<>();
    private static final long SESSION_HOURS = 24;

    public Session(String sessionId, String userId, String token,
                   LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public static String createSession(String userId) {
        String sessionId = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expires = now.plusHours(SESSION_HOURS);
        sessions.add(new Session(sessionId, userId, token, now, expires));
        return token;
    }

    public static boolean validateSession(String sessionId) {
        for (Session s : sessions) {
            if (s.sessionId.equals(sessionId) || s.token.equals(sessionId)) {
                return LocalDateTime.now().isBefore(s.expiresAt);
            }
        }
        return false;
    }

    public static boolean deleteSession(String sessionId) {
        return sessions.removeIf(s -> s.sessionId.equals(sessionId) || s.token.equals(sessionId));
    }

    public String getSessionId() { return sessionId; }
    public String getUserId() { return userId; }
    public String getToken() { return token; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
}
