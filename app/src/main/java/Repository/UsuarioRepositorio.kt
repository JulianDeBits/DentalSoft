package Repository

import Model.Usuario
import Security.SeguridadUtils

object UsuarioRepositorio {

    private val listaUsuarios = mutableListOf<Usuario>()

    fun registrar(nombre: String, password: String): Boolean {
        if (listaUsuarios.any { it.nombre == nombre }) return false

        val passwordHash = SeguridadUtils.hashearPassword(password)
        listaUsuarios.add(Usuario(nombre, passwordHash))
        return true
    }

    fun autenticar(nombre: String, password: String): Boolean {
        val passwordHash = SeguridadUtils.hashearPassword(password)
        return listaUsuarios.any { it.nombre == nombre && it.passwordHash == passwordHash }
    }

    fun obtenerUsuarios(): List<Usuario> = listaUsuarios
}