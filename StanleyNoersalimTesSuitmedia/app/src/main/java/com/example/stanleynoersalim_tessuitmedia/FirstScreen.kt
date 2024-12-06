package com.example.stanleynoersalim_tessuitmedia

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var sentence by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background3x), // Replace with your image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ensure the image covers the entire screen
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Icon
            Image(
                painter = painterResource(id = R.drawable.ic_photo), // Replace with your resource
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Name TextField
            TextField(
                value = name,
                onValueChange = { name = it },

                // Use placeholder instead of label
                placeholder = {
                    Text(
                        "Name",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF686777) // Set placeholder color to #686777
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White, shape = CircleShape)
                    .clip(CircleShape), // Make the TextField circular
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // Remove border when focused
                    unfocusedBorderColor = Color.Transparent // Remove border when unfocused
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.Black) // Set text color for input
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Palindrome TextField
            TextField(
                value = sentence,
                onValueChange = { sentence = it },
                placeholder = {
                    Text(
                        "Palindrome",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF686777) // Set placeholder color to #686777
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White, shape = CircleShape) // Set background to white
                    .clip(CircleShape), // Make the TextField circular
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // Remove border when focused
                    unfocusedBorderColor = Color.Transparent // Remove border when unfocused
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.Black) // Set the text color for input
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Check Button
            Button(
                onClick = {
                    val isPalindrome = sentence.text.replace(" ", "").equals(
                        sentence.text.replace(" ", "").reversed(),
                        ignoreCase = true
                    )
                    Toast.makeText(
                        context,
                        if (isPalindrome) "isPalindrome" else "not palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B) // Set color for the Check button
                )
            ) {
                Text("CHECK", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Next Button
            Button(
                onClick = {
                    navController.navigate("second_screen/${name.text}/")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B)
                )
            ) {
                Text("NEXT", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

    }
}
