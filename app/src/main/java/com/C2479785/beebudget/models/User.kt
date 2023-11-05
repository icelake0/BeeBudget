package com.C2479785.beebudget.models

data class User(val id: String?,
                 val userId: String,
                 val firstName: String,
                 val lastName: String
    ){
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "first_name" to this.firstName,
            "last_name" to this.lastName
        )
    }

}