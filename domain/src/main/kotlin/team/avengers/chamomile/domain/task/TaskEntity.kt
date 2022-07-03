package team.avengers.chamomile.domain.task

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val taskId: Long,
    val name: String
) {
}
