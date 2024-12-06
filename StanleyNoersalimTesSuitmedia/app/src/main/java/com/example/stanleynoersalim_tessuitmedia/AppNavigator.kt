package com.example.stanleynoersalim_tessuitmedia

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stanleynoersalim_tessuitmedia.FirstScreen


@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("second_screen/{userName}/{selectedUser}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val selectedUser = backStackEntry.arguments?.getString("selectedUser") ?: ""
            SecondScreen(navController, userName, selectedUser)
        }
        composable("third_screen/{userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val thirdScreenViewModel: ThirdScreenViewModel = viewModel()

            ThirdScreen(
                thirdScreenViewModel,
                onUserSelected = { selectedUser, _ ->
                    // Pass both userName and selectedUser to SecondScreen
                    navController.navigate("second_screen/$userName/$selectedUser")
                },
                userName = userName // Pass userName to ThirdScreen
            )
        }
    }
}