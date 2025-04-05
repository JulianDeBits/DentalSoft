package com.example.dentalsoft

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import androidx.core.net.toUri

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BienvenidaFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_bienvenida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aquí puedes modificar tus TextView
        val logoDoctor = view.findViewById<ImageView>(R.id.logoDoctor)
        val bienvenidaText = view.findViewById<TextView>(R.id.bienvenidaText)
        val descripcionText = view.findViewById<TextView>(R.id.descripcionText)
        val ingresarButton = view.findViewById<Button>(R.id.ingresarButton)
        val registrarseButton = view.findViewById<Button>(R.id.registrarseButton)
        val politicaButton = view.findViewById<Button>(R.id.politicaButton)
        val versionText = view.findViewById<TextView>(R.id.versionText)

        ingresarButton.setOnClickListener {

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

        registrarseButton.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.entrar_desde_derecha,
                    R.anim.salir_hacia_izquierda,
                    R.anim.entrar_desde_izquierda,
                    R.anim.salir_hacia_derecha
                )
                .replace(R.id.fragmentContainerView, RegistroFragment())
                .addToBackStack(null)
                .commit()

        }

        politicaButton.setOnClickListener {
            val url = "https://portafoliojuliandebits.netlify.app/"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BienvenidaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BienvenidaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}