package kg.study.mlkit.usertest.db.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation

/** One-to-One*/
data class UserAndDog(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val dog: Dog

)
