package ui.screens.UsersScreen

import data.repository.userRepository.UserRepositoryImpl
import domain.model.User
import domain.model.UserRole
import domain.repository.patientReopsitory.UserFactory
import domain.repository.userRepository.UserRepository
import factory.UserMainFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Users

@OptIn(DelicateCoroutinesApi::class)
class UserController constructor(private val userRepositoryImpl: UserRepository, private val userFactory: UserMainFactory) {
    private  val _userScreenState =  MutableStateFlow<UserScreenUiState>(UserScreenUiState(currentUser = Users("","",UserRole.MEMBER), users = emptyList()))
    val userScreenState:StateFlow<UserScreenUiState>
        get() = _userScreenState.asStateFlow()

    init {
        val scope =GlobalScope
        scope.launch {
            userRepositoryImpl.getAllUsers().collect{
                _userScreenState.update { state -> state.copy(currentUser = userRepositoryImpl.getCurrentUser(),users = it) }
            }
        }

    }

    fun addAdmin(name:String){
        val user = userFactory.createUser(UserRole.ADMIN,name,"")
        userRepositoryImpl.addUser(user)
        val scope =GlobalScope
        scope.launch {
            userRepositoryImpl.getAllUsers().collect{
                _userScreenState.update { state -> state.copy(users = it) }
            }
        }

    }
    fun addMember(name:String){
        val user = userFactory.createUser(UserRole.MEMBER,name,"")

        userRepositoryImpl.addUser(user)
        val scope =GlobalScope
        scope.launch {
            userRepositoryImpl.getAllUsers().collect{
                _userScreenState.update { state -> state.copy(users = it) }
            }
        }
    }

}