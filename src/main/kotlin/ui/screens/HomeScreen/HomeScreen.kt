package ui.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.navigation.NavController

@Composable
fun HomeScreen(modifier: Modifier=Modifier,navController: NavController){
    Column(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colors.background), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Home Screen")
    }
}