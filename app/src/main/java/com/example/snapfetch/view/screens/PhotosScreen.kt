package com.example.snapfetch.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapfetch.navigation.LocalNavController
import com.example.snapfetch.navigation.Screen

@Composable
fun PhotosScreen() {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Photos"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.navigate(Screen.PhotoDetails) }) {
                Text(text = "Navigate to Photo Details")
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PhotosScreenPreview() {
    PhotosScreen()
}