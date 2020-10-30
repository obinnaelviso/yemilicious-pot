package com.joytekmotion.yemilicious.models

import com.google.firebase.firestore.Exclude

data class User(
        val name: String,
        val email: String,
        val phone: String? = null,
        var role: String? = null
) {
    @get:Exclude
    var password: String = ""
    var address: String = ""
}