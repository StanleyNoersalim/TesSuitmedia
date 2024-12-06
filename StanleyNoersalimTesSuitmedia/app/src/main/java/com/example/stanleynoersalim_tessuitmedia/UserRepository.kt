package com.example.stanleynoersalim_tessuitmedia

class UserRepository {
    private val api = RetrofitInstance.api

    suspend fun getUsers(page: Int, perPage: Int): List<User> {
        return api.getUsers(page, perPage).data
    }
}