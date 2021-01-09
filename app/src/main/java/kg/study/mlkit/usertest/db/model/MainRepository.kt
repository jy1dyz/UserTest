package kg.study.mlkit.usertest.db.model

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository(private val userDao: UserDao) {

    private val TAG = "UserRepository"

    suspend fun insert(users: User) {
        userDao.insert(users)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun deleteAllUsers(users: User) {
        userDao.deleteAllUsers(users)
    }
    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    fun findUserWithName(search: String): LiveData<List<User>> {
        return userDao.findUserWithName(search = search)
    }

    /** One-to-One*/
    fun getUsersAndDogs(): LiveData<List<UserAndDog>> {
        return userDao.getUsersAndDogs()
    }

    /** One-to-Many*/
    fun getUsersWithDogs(): LiveData<List<UserWithDogs>> {
        return userDao.getUsersWithDogs()
    }
    suspend fun populateDb() {
        userDao.deleteAll()
        val userOne = User(4L, firstName = "Jane", lastName = "Air")
        val userTwo = User(1L, firstName = "Anna", lastName = "Vintour")
        val userThree = User(2L, firstName = "Will", lastName = "Smith")
        val userFour = User(3L, firstName = "Garry", lastName = "Potter")
        userDao.insert(userOne, userTwo, userThree, userFour)
    }
}