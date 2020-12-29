package kg.study.mlkit.usertest.db.model

import androidx.room.Embedded
import androidx.room.Relation

/** One-To-Many*/
data class UserWithDogs(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val dogs: List<Dog>
)
