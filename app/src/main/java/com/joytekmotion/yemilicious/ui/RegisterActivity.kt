package com.joytekmotion.yemilicious.ui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.joytekmotion.yemilicious.R
import com.joytekmotion.yemilicious.data.RegisterUsersViewModel
import com.joytekmotion.yemilicious.models.User
import com.joytekmotion.yemilicious.models.UsersContract
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val registerVm: RegisterUsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerVm.validationErrors.observe(this, { t -> checkErrors(t) })
        registerVm.currentUser.observe(this, { t -> isRegistered(t) })
        registerVm.loginError.observe(this, { t -> Snackbar.make(registerLayout, "Error: $t", Snackbar.LENGTH_LONG).show() })

        // TODO: Button animation
//        bindProgressButton(regRegister)
//
//        regRegister.attachTextChangeAnimator {  }

        // Click register button to register user
        regRegister.setOnClickListener {
            registerUser()
        }

        // Click sign in button to load login activity
        regLogin.setOnClickListener {
            loginActivity()
        }
    }

    private fun registerUser() {
        val user = User(
                regName.text.toString().trim(),
                regEmail.text.toString().trim(),
        )
        user.password = regPassword.text.toString().trim()
        registerVm.register(user, regPasswordConfirm.text.toString().trim())
    }

    private fun isRegistered(currentUser: FirebaseUser) {
        Snackbar.make(registerLayout, "Registration successful! User is ${currentUser.displayName}", Snackbar.LENGTH_LONG).show()
    }

    private fun checkErrors(errors: ContentValues) {
        regName.error = errors.getAsString(UsersContract.Fields.NAME)
        regEmail.error = errors.getAsString(UsersContract.Fields.EMAIL)
        regPassword.error = errors.getAsString(UsersContract.Fields.PASSWORD)
        regPasswordConfirm.error = errors.getAsString(UsersContract.Fields.PASSWORD_CONFIRM)
    }

    private fun loginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}