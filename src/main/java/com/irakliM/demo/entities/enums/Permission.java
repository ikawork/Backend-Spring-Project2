package com.irakliM.demo.entities.enums;

public enum Permission {
    STUDENT_READ("student:read"), STUDENT_ADD("student:add");

    Permission(String permission) {
        this.permission = permission;
    }

    private String permission;

    public String getPermission() {
        return permission;
    }
}
