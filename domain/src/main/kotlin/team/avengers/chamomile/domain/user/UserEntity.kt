package team.avengers.chamomile.domain.user

import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null
}