package domain.repository.userRepository

import domain.model.User
import kotlinx.coroutines.flow.Flow
import model.Users

interface UserRepository {
    fun addUser(user: Users)
    fun getAllUsers(): Flow<List<Users>>

    fun  setCurrentUser(user: Users):Users
    fun getCurrentUser(): Users
}