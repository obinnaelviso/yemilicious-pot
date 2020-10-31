package com.joytekmotion.yemilicious.models

object UsersContract {
    internal const val COLLECTION_NAME = "users"

    // Users fields
    object Fields {
        const val NAME = "name"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val PASSWORD_CONFIRM = "password_confirm"
    }
}