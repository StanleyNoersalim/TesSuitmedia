package com.example.stanleynoersalim_tessuitmedia

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, userName: String, selectedUser: String) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Second Screen",
                            fontSize = 20.sp, // Adjust text size if needed
                            fontWeight = FontWeight.Bold, // Optional: make the text bold
                            color = Color.Black, // Set text color to black
                            modifier = Modifier
                                .fillMaxWidth() // Make sure it fills the width
                                .wrapContentWidth(Alignment.CenterHorizontally) // Center horizontally
                                .align(Alignment.Center) // Center vertically // Center the title
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White), // Set background color to white
                modifier = Modifier
                    .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()) // Handle the safe area
            )

        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(top = 16.dp)    // Use the padding from the contentPadding
                    .verticalScroll(rememberScrollState()), // Enable scrolling
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF04021D),
                    fontSize = 17.sp,
                    modifier = Modifier.padding(start = 16.dp) // Apply padding here
                )

                Spacer(modifier = Modifier.height(2.dp))


                Text(
                    text = "Name: $userName",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    fontSize =25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.height(100.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ){

                    Text(
                        text =  if (selectedUser.isNotEmpty()) "$selectedUser" else "Selected User Name ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth() // Make sure it fills the width
                            .wrapContentWidth(Alignment.CenterHorizontally) // Center horizontally
                            .align(Alignment.Center) // Center vertically
                    )


                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        navController.navigate("third_screen/${userName}") // Navigate to the third screen
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2B637B) // Adjust button color to match the design
                    )
                ) {
                    Text("Choose a User", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}
