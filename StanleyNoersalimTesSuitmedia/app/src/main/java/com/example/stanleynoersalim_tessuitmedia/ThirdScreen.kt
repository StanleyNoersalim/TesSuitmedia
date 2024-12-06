package com.example.stanleynoersalim_tessuitmedia

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ThirdScreen(
    viewModel: ThirdScreenViewModel = viewModel(),
    onUserSelected: (String, String) -> Unit,
    userName: String
) {
    val users = viewModel.users.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val isRefreshing = remember { mutableStateOf(false) }

    // Load users when this screen is displayed
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    // Implement the pull-to-refresh logic
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = {
            isRefreshing.value = true
            viewModel.loadUsers() // Trigger refresh
            isRefreshing.value = false
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            // Pass both userName and selectedUser back to SecondScreen
                            onUserSelected("${user.first_name} ${user.last_name}", userName)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(user.avatar),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("${user.first_name} ${user.last_name}", fontWeight = FontWeight.Bold)
                            Text(user.email)
                        }
                    }
                }
            }

            item {
                if (isLoading) {
                    Text("Loading more users...")
                } else {
                    Button(onClick = { viewModel.loadMoreUsers() }) {
                        Text("Load More")
                    }
                }
            }
        }
    }
}