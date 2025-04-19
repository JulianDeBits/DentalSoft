package com.gatopercebe.dentalsoft.Fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.Activity
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import com.gatopercebe.dentalsoft.AppActivity
import com.gatopercebe.dentalsoft.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import androidx.activity.result.contract.ActivityResultContracts



class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val GOOGLE_SIGN_IN_CODE = 1001
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Toast.makeText(requireContext(), "Error al iniciar con Google", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val emailEditText = view.findViewById<EditText>(R.id.inputUsuario)
        val passwordEditText = view.findViewById<EditText>(R.id.inputPassword)
        val loginButton = view.findViewById<Button>(R.id.iniciarSesionButton)
        val googleSignInButton = view.findViewById<Button>(R.id.googleButton)
        val registarseText = view.findViewById<TextView>(R.id.noTieneCuentaText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Sesión iniciada", Toast.LENGTH_SHORT).show()
                        goToMain()
                    } else {
                        Toast.makeText(requireContext(), "Error: Usuario y/o Contraseña Incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }

        registarseText.setOnClickListener {
            val nuevoFragmento = RegisterFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.contenedorFragmentos, nuevoFragmento)
                .addToBackStack(null)
                .commit()
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Sesión iniciada con Google", Toast.LENGTH_SHORT).show()
                    goToMain()
                } else {
                    Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun goToMain() {
        val intent = Intent(requireContext(), AppActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }



}