package data.repository.userRepository

import utils.singleton.IHMSDatabase
import domain.model.User
import domain.repository.userRepository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Users

class UserRepositoryImpl constructor(private val db: IHMSDatabase): UserRepository {
    override fun addUser(user: Users) {
        db.usersMain.add(user)
    }

    override fun getAllUsers(): Flow<List<Users>> = flow {
        val users = db.usersMain.toList().also {
            emit(it)
        }
    }



    override fun setCurrentUser(user: Users):Users {
        db.currentUser=user
        return db.currentUser
    }

    override fun getCurrentUser():Users{
        return db.currentUser
    }

}