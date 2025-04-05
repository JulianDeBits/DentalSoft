package com.example.dentalsoft

import Repository.UsuarioRepositorio
import android.media.Image
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val passwordTitulo2 = view.findViewById<TextView>(R.id.passwordTitulo2)
        val passwordInput2 = view.findViewById<EditText>(R.id.passwordInput2)
        val logoDoctor = view.findViewById<ImageView>(R.id.logoDoctor)
        val iniciarTitulo = view.findViewById<TextView>(R.id.iniciarTitulo)
        val ingresarButton = view.findViewById<Button>(R.id.ingresarButton)
        val correoInput = view.findViewById<EditText>(R.id.correoInput)
        val correoTitulo = view.findViewById<TextView>(R.id.correoTitulo)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val passwordTitulo = view.findViewById<TextView>(R.id.passwordTitulo)
        val loguearseButton = view.findViewById<Button>(R.id.loguearseButton)

        loguearseButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.entrar_desde_derecha,
                    R.anim.salir_hacia_izquierda,
                    R.anim.entrar_desde_izquierda,
                    R.anim.salir_hacia_derecha
                )
                .replace(R.id.fragmentContainerView, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        ingresarButton.setOnClickListener {
            val correo = correoInput.text.toString().trim()
            val password = passwordInput.text.toString()
            val repetir = passwordInput2.text.toString()

            when {
                correo.isEmpty() || password.isEmpty() || repetir.isEmpty() -> {
                    Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }

                password != repetir -> {
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val registrado = UsuarioRepositorio.registrar(correo, password)
                    if (registrado) {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        correoInput.text.clear()
                        passwordInput.text.clear()
                        passwordInput2.text.clear()
                    } else {
                        Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment RegistroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}