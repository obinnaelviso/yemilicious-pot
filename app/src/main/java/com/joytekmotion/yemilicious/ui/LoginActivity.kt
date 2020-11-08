package com.joytekmotion.yemilicious.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.joytekmotion.yemilicious.R
import com.joytekmotion.yemilicious.data.LoginViewModel
import com.joytekmotion.yemilicious.helpers.alertBox
import com.joytekmotion.yemilicious.ui.buyer.BuyersDashboardActivity
import com.joytekmotion.yemilicious.ui.seller.SellerDashboardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginVm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO: login button animation

        // Observe current user to check if user has setup profile
        loginVm.currentUser.observe(this, {
            if (it == null) {
                alertBox(
                    loginContainer,
                    getString(R.string.login_to_continue),
                    Snackbar.LENGTH_LONG
                )
            } else {
                loginVm.checkSetup(it)
            }
        })

        // Observe if user has setup profile to change ui
        loginVm.userRole.observe(this, {
            if (it != null) {
                if (it == SELLER_ROLE)
                    launchSellerDashboardActivity()
                else
                    launchBuyerDashboardActivity()
            } else launchSetupActivity()
        })

        // Observe loginError to display error message
        loginVm.loginError.observe(this, {
            alertBox(loginContainer, it, Snackbar.LENGTH_LONG)
        })

        login.setOnClickListener {
            loginVm.login(email.text.toString().trim(), password.text.toString().trim())
        }

        // Open Registration activity
        btnRegister.setOnClickListener {
            registrationActivity()
        }
    }

    private fun registrationActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun launchSetupActivity() {
        startActivity(Intent(this, SetupActivity::class.java))
    }

    private fun launchSellerDashboardActivity() {
        startActivity(Intent(this, SellerDashboardActivity::class.java))
    }

    private fun launchBuyerDashboardActivity() {
        startActivity(Intent(this, BuyersDashboardActivity::class.java))
    }
}