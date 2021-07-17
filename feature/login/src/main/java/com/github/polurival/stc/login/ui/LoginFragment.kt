package com.github.polurival.stc.login.ui

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.github.polurival.stc.login.databinding.LoginFragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * https://firebase.google.com/docs/auth/android/firebaseui
 *
 * @author Юрий Польщиков on 11.07.2021
 */
class LoginFragment : Fragment() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        onSignInResult(result)
    }
    private lateinit var binding: LoginFragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loggedUserId = requireContext().getSharedPreferences("user", MODE_PRIVATE)
            .getString(USER_UID, null)
        if (loggedUserId != null) {
            locateToMainScreen()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentLoginBinding.inflate(layoutInflater)
        binding.loginButton.setOnClickListener { createSignInIntent() }
        return binding.root
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Toast.makeText(requireContext(), "Hello $user!", Toast.LENGTH_LONG).show()

            // todo move to storage module
            requireContext().getSharedPreferences("user", MODE_PRIVATE)
                .edit()
                .putString(USER_UID, user?.uid)
                .apply()

            locateToMainScreen()
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    /**
     * todo move to Router class
     */
    private fun locateToMainScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.github.polurival.stc/app_nav_fragment_main".toUri())
            .build()
        findNavController().apply {
            popBackStack()
            navigate(request)
        }
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    companion object {
        private const val USER_UID = "USER_TOKEN"
    }
}
