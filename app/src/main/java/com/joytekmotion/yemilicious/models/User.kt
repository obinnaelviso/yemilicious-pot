package com.joytekmotion.yemilicious.models

import com.google.firebase.firestore.Exclude

data class User(
        var phone: String? = null,
        var address: String? = null
) {
    var role: String? = null
    var shop: Shop? = null
    @get:Exclude
    var email: String = ""
    @get:Exclude
    var password: String = ""
    @get:Exclude
    var name: String = ""
}