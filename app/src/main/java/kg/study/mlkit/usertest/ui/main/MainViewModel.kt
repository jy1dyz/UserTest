package kg.study.mlkit.usertest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.study.mlkit.usertest.db.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val TAG = "MainViewModel"
    private val _users = MutableLiveData<List<User>>()
    private val _usersAndDogs = MutableLiveData<List<UserAndDog>>()
    private val _usersWithDogs = MutableLiveData<List<UserWithDogs>>()

    val users: LiveData<List<User>>
    val usersAndDogs: LiveData<List<UserAndDog>> = _usersAndDogs
    val usersWithDogs: LiveData<List<UserWithDogs>> = _usersWithDogs

    init {
        users = repository.getAllUsers()
//        _users.postValue(
//            listOf(
//                User("Jane", "Doe"),
//                User("Jane", "Smith"),
//                User("Anna", "Frank"),
//                User("Anna", "Windzor"),
//                User("Will", "Gun"),
//                User("John", "Lizbet"),
//                User("Артур", "Король"),
//                User("Мария", "Тюдор"),
//                User("Анна", "Болейн"),
//                User("Дженни", "Сеймур"),
//                User("Филипп", "Великолепный")
//            )
//        )
//        users = repository.getAllUsers()
//        _usersAndDogs.postValue(
//            listOf(
//                UserAndDog(User(1,"Jane", "Foster"), Dog(1,1,"Puppy")),
//                UserAndDog(User(2,"Anna", "Vintour"), Dog(2,2,"Poppy")),
//                UserAndDog(User(3, "Hanna", "Montana"), Dog(3,3,"Ruffy")),
//                UserAndDog(User(4, "Will", "Smith"), Dog(4,4,"Rocky")),
//                UserAndDog(User(5, "Vin", "Diesel"), Dog(5,5,"Rock")),
//                UserAndDog(User(6, "Sam", "Nickon"), Dog(6,6,"Bobby"))
//            )
//        )

//        _usersWithDogs.postValue(
//            listOf(
//                UserWithDogs(User(1, "Jane", "Doe"), listOf(Dog(1, 1, "Bob"), Dog(1,1,"Poppy"))),
//                UserWithDogs(User(2, "Anna", "Dilan"), listOf(Dog(2, 2, "Bobby"), Dog(2,2,"Puppy"),
//                Dog(2,2,"Fox"))),
//                UserWithDogs(User(3, "Hanna", "Montana"), listOf(Dog(3, 3, "Billy"))),
//                UserWithDogs(User(4, "Will", "Smith"), listOf(Dog(4, 4, "Puppy"))),
//            )
//        )
    }

    fun insert(user: User) {
        viewModelScope.launch {
            _users.postValue(listOf(User(1, "Jane", "Doe"),
                    User(2, "Kate", "Moss"),
                    User(3, "Katie", "Holms"),
                    User(4, "Anjelina", "Jolie"),
                    User(5, "Kate", "Hudson")
            ))
            _users.value?.forEach { user ->
                repository.insert(user)
            }
        }
    }
    suspend fun insertUser(user: User) = withContext(Dispatchers.IO) {

    }
    fun populateDb() {
        viewModelScope.launch {
            repository.populateDb()
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
    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return users
    }

    fun findUserWithName(search: String): LiveData<List<User>> {
        return repository.findUserWithName(search = search)
    }

    /** One-to-One*/
    fun gUsersAndDogs(): LiveData<List<UserAndDog>> {
        return repository.getUsersAndDogs()
    }

    /** One-to-Many*/
    fun gUsersWithDogs(): LiveData<List<UserWithDogs>> {
        return usersWithDogs
    }

}