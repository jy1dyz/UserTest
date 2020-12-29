package kg.study.mlkit.usertest.db.model

import androidx.lifecycle.LiveData

class MainRepository(private val userDao: UserDao) {

    private val TAG = "UserRepository"

    suspend fun insert(user: User) {
        userDao.insert(user)
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
}