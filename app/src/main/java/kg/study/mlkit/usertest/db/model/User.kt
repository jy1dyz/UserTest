package kg.study.mlkit.usertest.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long,
    val firstName: String,
    val lastName: String
) {
//    @PrimaryKey(autoGenerate = true)
//    var id: Long = 0
}
