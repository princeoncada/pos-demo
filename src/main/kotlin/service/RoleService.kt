package service

import model.Role
import repository.RoleRepository
import service.base.EntityServiceImpl

class RoleService(
    roleRepository: RoleRepository
): EntityServiceImpl<Role, String>(roleRepository) {}