package com.github.polurival.stc.login.ui

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.login.R
import com.github.polurival.stc.login.databinding.LoginFragmentLoginBinding
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.StorageCoreLibApi
import com.github.polurival.stc.storageapi.USER_NAME
import com.google.firebase.auth.FirebaseAuth

/**
 * https://firebase.google.com/docs/auth/android/firebaseui
 *
 * @author Юрий Польщиков on 11.07.2021
 */
class LoginFragment : BaseFragment(R.layout.login_fragment_login) {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        onSignInResult(result)
    }
    private var binding: LoginFragmentLoginBinding? = null
    private var prefs: PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = api.getCore(StorageCoreLibApi::class.java).preferencesManager

        val loggedUserName = prefs?.getString(USER_NAME)
        if (loggedUserName != null) {
            locateToMainScreen()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentLoginBinding.bind(view).apply {
            loginButton.setOnClickListener { createSignInIntent() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs = null
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Toast.makeText(requireContext(), "Hello $user!", Toast.LENGTH_LONG).show()

            prefs?.putString(USER_NAME, user?.displayName)

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
}
