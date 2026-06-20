package Model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String status;
    private String googleId;
    private List<Order> history;
    private double totalPoints;
    private String rank;

    private static List<Account> accounts = new ArrayList<>();

    static {
        accounts.add(new Account("U001", "admin", "admin@cinema.com", "Admin@123",
                new Role("Manager"), "ACTIVE", null, "Gold", 2000));
        accounts.add(new Account("U002", "user1", "user1@gmail.com", "User1@123",
                new Role("Customer"), "ACTIVE", null, "Bronze", 100));
        accounts.add(new Account("U003", "locked", "locked@gmail.com", "Lock@123",
                new Role("Customer"), "LOCKED", null, "Bronze", 0));
        accounts.add(new Account("U004", "staff1", "staff1@cinema.com", "Staff@123",
                new Role("Staff"), "ACTIVE", null, "Bronze", 0));
    }

    public Account(Role role) {
        this.role = role;
    }

    public Account(String id, String username, String email, String password, Role role,
                   String status, String googleId, String rank, double totalPoints) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.googleId = googleId;
        this.rank = rank;
        this.totalPoints = totalPoints;
        this.history = new ArrayList<>();
    }

    public static boolean validateCredentials(String email, String password) {
        Account acc = findByEmail(email);
        if (acc == null) {
            throw new RuntimeException("INVALID_CREDENTIALS");
        }
        if (acc.isLocked()) {
            throw new RuntimeException("ACCOUNT_LOCKED");
        }
        if (!acc.password.equals(password)) {
            throw new RuntimeException("INVALID_CREDENTIALS");
        }
        return true;
    }

    public static boolean registerUser(String email, String password, String confirmPassword) {
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            throw new RuntimeException("INVALID_INPUT");
        }
        if (findByEmail(email) != null) {
            throw new RuntimeException("EMAIL_ALREADY_EXISTS");
        }
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            throw new RuntimeException("PASSWORD_MISMATCH");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("INVALID_INPUT");
        }

        String newId = String.format("U%03d", accounts.size() + 1);
        String username = email.split("@")[0];
        Account newAcc = new Account(newId, username, email, password,
                new Role("Customer"), "ACTIVE", null, "Bronze", 0);
        accounts.add(newAcc);
        return true;
    }

    public static Account findByEmail(String email) {
        if (email == null) return null;
        for (Account acc : accounts) {
            if (email.equalsIgnoreCase(acc.email)) {
                return acc;
            }
        }
        return null;
    }

    public static Account findById(String userId) {
        if (userId == null) return null;
        for (Account acc : accounts) {
            if (userId.equals(acc.id)) {
                return acc;
            }
        }
        return null;
    }

    // UC-2.2 (Sequence): Controller -> Account.updateProfile(userId, data)
    // Validate dữ liệu mới rồi cập nhật. Throw INVALID_PROFILE_DATA / SYSTEM_ERROR.
    // profileData là Map<String,String> với các key: "username", "email".
    public static boolean updateProfile(String userId, java.util.Map<String, String> profileData) {
        Account acc = findById(userId);
        if (acc == null) {
            throw new RuntimeException("SYSTEM_ERROR");
        }
        if (profileData == null || profileData.isEmpty()) {
            throw new RuntimeException("INVALID_PROFILE_DATA");
        }

        String newUsername = profileData.get("username");
        String newEmail = profileData.get("email");

        if (newUsername != null) {
            String u = newUsername.trim();
            if (u.length() < 2 || u.length() > 50) {
                throw new RuntimeException("INVALID_PROFILE_DATA");
            }
            acc.username = u;
        }
        if (newEmail != null) {
            String e = newEmail.trim();
            if (!isValidEmail(e)) {
                throw new RuntimeException("INVALID_PROFILE_DATA");
            }
            Account dup = findByEmail(e);
            if (dup != null && !dup.id.equals(acc.id)) {
                throw new RuntimeException("INVALID_PROFILE_DATA");
            }
            acc.email = e;
        }
        return true;
    }

    public static boolean updatePassword(String email, String password, String confirmPassword) {
        Account acc = findByEmail(email);
        if (acc == null) {
            throw new RuntimeException("INVALID_CREDENTIALS");
        }
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("PASSWORD_MISMATCH");
        }
        if (!isValidPassword(password)) {
            throw new RuntimeException("INVALID_PASSWORD_FORMAT");
        }
        acc.password = password;
        return true;
    }

    public boolean isLocked() {
        return "LOCKED".equalsIgnoreCase(this.status);
    }

    private static boolean isValidPassword(String pw) {
        if (pw == null || pw.length() < 8) return false;
        return pw.matches(".*[!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/\\\\|`~\\-].*");
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.+\\-]+@[\\w\\-]+(\\.[\\w\\-]+)+$");
    }

    public static List<Account> getAllAccounts() {
        return accounts;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getGoogleId() { return googleId; }
    public void setGoogleId(String googleId) { this.googleId = googleId; }

    public List<Order> getHistory() { return history; }
    public void setHistory(List<Order> history) { this.history = history; }

    public double getTotalPoints() { return totalPoints; }
    public void setTotalPoints(double totalPoints) { this.totalPoints = totalPoints; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
}
