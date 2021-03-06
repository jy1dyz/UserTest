package kg.study.mlkit.usertest.db.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg users: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Delete
    suspend fun deleteAllUsers(vararg users: User)

    @Query("DELETE FROM User")
    suspend fun deleteAll()

    @Query("SELECT * FROM User ORDER BY firstName ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE firstName LIKE :search OR lastName LIKE :search")
    fun findUserWithName(search: String): LiveData<List<User>>

    /** One-to-One*/
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersAndDogs(): LiveData<List<UserAndDog>>

    /** One-to-Many*/
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithDogs():LiveData<List<UserWithDogs>>
}