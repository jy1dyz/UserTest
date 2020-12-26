package kg.study.mlkit.usertest.db.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Delete
    suspend fun deleteAllUsers(vararg users: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE firstName LIKE :search OR lastName LIKE :search")
    fun findUserWithName(search: String): LiveData<List<User>>
}