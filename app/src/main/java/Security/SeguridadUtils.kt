package Security

import java.security.MessageDigest

object SeguridadUtils {
    fun hashearPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}