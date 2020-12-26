package kg.study.mlkit.usertest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.study.mlkit.usertest.db.model.MainRepository
import kg.study.mlkit.usertest.db.model.User
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"
    private val _users = MutableLiveData<List<User>>()

    var users: LiveData<List<User>> = _users

    init {
        _users.postValue(
            listOf(
                User("Jane", "Doe"),
                User("Jane", "Smith"),
                User("Anna", "Frank"),
                User("Anna", "Windzor"),
                User("Will", "Gun"),
                User("John", "Lizbet"),
                User("Артур", "Король"),
                User("Мария", "Тюдор"),
                User("Анна", "Болейн"),
                User("Дженни", "Сеймур"),
                User("Филипп", "Великолепный")
            )
        )
//        users = repository.getAllUsers()
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user = user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            repository.update(user = user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            repository.delete(user = user)
        }
    }

    fun deleteAllUsers(users: User) {
        viewModelScope.launch {
            repository.deleteAllUsers(users = users)
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return users
    }

    fun findUserWithName(search: String): LiveData<List<User>> {
        return repository.findUserWithName(search = search)
    }

}