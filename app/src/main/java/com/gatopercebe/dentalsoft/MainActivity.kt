package com.gatopercebe.dentalsoft

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.gatopercebe.dentalsoft.Fragmentos.BienvenidaFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val contenedorFragmentos = findViewById<FragmentContainerView>(R.id.contenedorFragmentos)

        val fragment = BienvenidaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragmentos, fragment)
            .addToBackStack(null)
            .commit()
    }
}