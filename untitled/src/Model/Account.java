package Model;

public class Account {
    private Role role;

    public Account(Role  role){
        this.role =role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
