package domain.repository.patientReopsitory

import domain.model.User
import domain.model.UserRole
import java.util.*

class UserFactory {
        fun createAdmin(username: String): User {
            return User(id = generateId(), username = username, role = UserRole.ADMIN)
        }

        fun createMember(username: String): User {
            return User(id = generateId(), username = username, role = UserRole.MEMBER)
        }

        private fun generateId(): String {
            // Implementation to generate a unique ID
            return UUID.randomUUID().toString()
        }
}