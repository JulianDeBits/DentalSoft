package com.example.dentalsoft

import Repository.UsuarioRepositorio
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.jvm.java

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoDoctor = view.findViewById<ImageView>(R.id.logoDoctor)
        val iniciarTitulo = view.findViewById<TextView>(R.id.iniciarTitulo)
        val ingresarButton = view.findViewById<Button>(R.id.ingresarButton)
        val ingresarGoogle = view.findViewById<Button>(R.id.ingresarGoogle)
        val correoInput = view.findViewById<EditText>(R.id.correoInput)
        val correoTitulo = view.findViewById<TextView>(R.id.correoTitulo)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val passwordText = view.findViewById<TextView>(R.id.passwordInput)

        ingresarButton.setOnClickListener {
            val usuario = correoInput.text.toString().trim()
            val password = passwordInput.text.toString()

            when {
                usuario.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val loginExitoso = UsuarioRepositorio.autenticar(usuario, password)

                    if (loginExitoso) {
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireContext(), CargandoActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}