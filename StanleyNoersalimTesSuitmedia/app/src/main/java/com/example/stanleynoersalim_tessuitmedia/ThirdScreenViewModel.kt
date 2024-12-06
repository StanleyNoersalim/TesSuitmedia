package com.example.stanleynoersalim_tessuitmedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThirdScreenViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1
    private val usersPerPage = 10

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userList = userRepository.getUsers(page = currentPage, perPage = usersPerPage)
                _users.value = userList
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                e.printStackTrace()
            }
        }
    }

    fun loadMoreUsers() {
        if (_isLoading.value) return  // Avoid multiple loading triggers
        viewModelScope.launch {
            try {
                _isLoading.value = true
                currentPage++
                val additionalUsers = userRepository.getUsers(page = currentPage, perPage = usersPerPage)
                _users.value += additionalUsers
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                e.printStackTrace()
            }
        }
    }
}
