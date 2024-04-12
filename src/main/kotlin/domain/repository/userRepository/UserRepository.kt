package domain.repository.userRepository

import domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun addUser(user: User)
    fun getAllUsers(): Flow<List<User>>
    fun getUserById(id: String): Flow<User>

    fun  setCurrentUser(user: User):User
    fun getCurrentUser(): User
}