package com.gatopercebe.dentalsoft.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.gatopercebe.dentalsoft.R
import org.w3c.dom.Text

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BienvenidaFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_bienvenida, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            BienvenidaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iniciarSesionButton = view.findViewById<Button>(R.id.iniciarSesionButton)
        val registrarseButton = view.findViewById<Button>(R.id.registrarseButton)
        val politicaPrivacidadText = view.findViewById<TextView>(R.id.politicaPrivacidadText)

        iniciarSesionButton.setOnClickListener {
            val nuevoFragmento = LoginFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedorFragmentos, nuevoFragmento)
                .addToBackStack(null)
                .commit()
        }
    }

}