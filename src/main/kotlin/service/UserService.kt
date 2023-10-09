package service

import model.User
import repository.UserRepository
import service.base.EntityServiceImpl

class UserService(
    userRepository: UserRepository
): EntityServiceImpl<User, String>(userRepository) {}