package kg.study.mlkit.usertest.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog(
    @PrimaryKey(autoGenerate = true)
    var dogId: Long = 0,
    val userOwnerId: Long,
    val name: String
) {

}
