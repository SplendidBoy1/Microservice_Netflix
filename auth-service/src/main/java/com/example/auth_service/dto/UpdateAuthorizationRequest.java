package com.example.auth_service.dto;

import java.util.List;

import com.example.auth_service.entity.Role;

public class UpdateAuthorizationRequest {
    
    private boolean enabled;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UpdateAuthorizationRequest(){

    }

    private List<Integer> roleIds;

    public List<Integer> getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }


    public UpdateAuthorizationRequest(boolean enabled, List<Integer> roleIds){
        this.enabled = enabled;
        this.roleIds = roleIds;
    }

}
