package data.repository.userRepository

import utils.singleton.IHMSDatabase
import domain.model.User
import domain.repository.userRepository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl constructor(private val db: IHMSDatabase): UserRepository {
    override fun addUser(user: User) {
        db.users.add(user)
    }

    override fun getAllUsers(): Flow<List<User>> = flow {
        val users = db.users.toList().also {
            emit(it)
        }
    }

    override fun getUserById(id: String): Flow<User> =flow{
        val users = db.users.filter { user -> user.id == id }
        val user = users[0]
        emit(user)
    }

    override fun setCurrentUser(user: User):User {
        db.currentUser=user
        return db.currentUser
    }

    override fun getCurrentUser():User{
        return db.currentUser
    }

}