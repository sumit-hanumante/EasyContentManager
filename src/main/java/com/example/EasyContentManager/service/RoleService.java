package com.example.EasyContentManager.service;

import com.example.EasyContentManager.exception.ResourceNotFoundException;
import com.example.EasyContentManager.model.Role;
import com.example.EasyContentManager.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id :" + roleId));
    }

    public Role updateRole(Long roleId, Role roleRequest) {
        return roleRepository.findById(roleId).map(role -> {
            role.setName(roleRequest.getName());
            return roleRepository.save(role);
        }).orElseThrow(() -> new ResourceNotFoundException("Role not found with id :" + roleId));
    }

    public void deleteRole(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new ResourceNotFoundException("Role not found with id :" + roleId);
        }
        roleRepository.deleteById(roleId);
    }
}
