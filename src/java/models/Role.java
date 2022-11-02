package models;


/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */

public class Role {

    private int role_id;
    private String role_name;


    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.role_id = roleId;
        this.role_name = roleName;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int roleId) {
        this.role_id = roleId;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
