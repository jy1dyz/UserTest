package kg.study.mlkit.usertest.db.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun populateDb(database: AppDatabase?) {
    database?.let { db ->
        withContext(Dispatchers.IO) {
            val userDao: UserDao = db.userDao()

            userDao.deleteAll()
            val userOne = User(0L, firstName = "Jane", lastName = "Air")
            val userTwo = User(1L, firstName = "Anna", lastName = "Vintour")
            val userThree = User(2L, firstName = "Will", lastName = "Smith")
            userDao.insert(userOne, userTwo, userThree)
        }
    }
}