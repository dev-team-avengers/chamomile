package team.avengers.chamomile.domain.task

import team.avengers.chamomile.domain.common.IntValueEnum
import team.avengers.chamomile.domain.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "task")
class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var taskId: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    var userEntity: UserEntity? = null

    @Column(length = 40, nullable = false)
    var title: String = ""

    @Column(length = 1_000, nullable = false)
    var content: String = ""

    @Column(nullable = false)
    var publicState: PublicState = PublicState.ALL

    @Column(nullable = false)
    var priority: Priority = Priority.MEDIUM

    @Column(nullable = false)
    var pin: Boolean = false

    var parentTaskId: Long? = null

    @Column(nullable = false)
    var status: Status = Status.NEW

    @Column(nullable = false)
    var deleted: Boolean? = false

    @Column(nullable = false)
    var createdAt: LocalDateTime? = null

    @Column(nullable = false)
    var modifiedAt: LocalDateTime? = null

    var deletedAt: LocalDateTime? = null
}

enum class PublicState(private val value: Int) : IntValueEnum {
    ALL(1), FOLLOWER(2), PRIVATE(9)
    ;

    override fun getValue(): Int = value

    companion object {
        fun findByValue(value: Int): PublicState? {
            return PublicState.values().find {
                it.value == value
            }
        }
    }
}

@Converter(autoApply = true)
class PublicStateConverter : AttributeConverter<PublicState, Int> {
    override fun convertToDatabaseColumn(publicState: PublicState): Int = publicState.getValue()

    override fun convertToEntityAttribute(value: Int): PublicState = PublicState.findByValue(value) ?: throw RuntimeException()
}

enum class Priority(private val value: Int) : IntValueEnum {
    LOW(25), MEDIUM(50), HIGH(75)
    ;

    override fun getValue(): Int = value

    companion object {
        fun findByValue(value: Int): Priority? {
            return values().find {
                it.value == value
            }
        }
    }
}

@Converter(autoApply = true)
class PriorityConverter : AttributeConverter<Priority, Int> {
    override fun convertToDatabaseColumn(priority: Priority): Int = priority.getValue()

    override fun convertToEntityAttribute(value: Int): Priority = Priority.findByValue(value) ?: throw RuntimeException()
}

enum class Status(private val value: Int, private val description: String) : IntValueEnum {
    NEW(1, "신규"),
    IN_PROGRESS(20, "진행중"),
    DONE(90, "완료")
    ;

    override fun getValue(): Int = value

    fun getDescription(): String = description

    companion object {
        fun findByValue(value: Int): Status? {
            return values().find {
                it.value == value
            }
        }
    }
}

@Converter(autoApply = true)
class StatusConverter : AttributeConverter<Status, Int> {
    override fun convertToDatabaseColumn(status: Status): Int = status.getValue()

    override fun convertToEntityAttribute(value: Int): Status = Status.findByValue(value) ?: throw RuntimeException()
}