package ui.screens.UsersScreen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.User
import domain.model.UserRole
import ui.IHMSTheme
import ui.navigation.NavController

@Composable
fun UsersScreen(
    navController: NavController,
    users: List<User>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier=Modifier,
    addUser:(String)->Unit,
    addAdmin:(String)->Unit
) {
    var userName by remember { mutableStateOf("") }
    Column(modifier=Modifier.fillMaxSize()){
        Row {
            Column(modifier = modifier.fillMaxWidth(0.5f)){
                Text(text = "Users", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                LazyColumn {
                    itemsIndexed(items = users){
                        index,user -> UserRow(index = index, user = user)
                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(modifier=Modifier.fillMaxWidth()){
                Row(modifier = Modifier.fillMaxWidth().clickable{}){
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Add Patient")
                    Text(text = "Add User", fontSize = 18.sp, fontWeight= FontWeight.Bold)
                }
                Row (modifier = Modifier.fillMaxWidth()){
                    TextField(value = userName, onValueChange = {userName=it})
                }
                Row (modifier = Modifier.fillMaxWidth()){
                    Button(onClick ={addAdmin(userName)},modifier = Modifier.width(150.dp),shape = RoundedCornerShape(50)){
                        Text(text = "Admin")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick ={addUser(userName)},modifier = Modifier.width(150.dp), shape = RoundedCornerShape(50)){
                        Text(text = "Normal User")
                    }
                }
            }
        }
    }
}

@Composable
fun UserRow(modifier: Modifier=Modifier,index:Int,user: User){
    Row(modifier = Modifier.fillMaxWidth().background(color = if(isEven(index)) Color.Gray else Color.DarkGray), horizontalArrangement = Arrangement.SpaceBetween) {
        Column() {
            Icon(Icons.Outlined.AccountCircle,user.username, tint = Color.White)
        }
        Column {
            Text(text= user.username.uppercase(), fontWeight = FontWeight.Light, color = Color.White)
        }
        Column { Text(text = user.role.name, fontWeight = FontWeight.Light, color = Color.White) }
    }
}

fun isEven(number: Int): Boolean {
    return number % 2 == 0
}

@Preview
@Composable
fun UsersScreenPreview() {
    IHMSTheme {
        val users = listOf(
            User(id = "1", username = "John", role = UserRole.ADMIN),
            User(id = "2", username = "Mary",role = UserRole.ADMIN),
            User(id = "3", username = "Peter",role = UserRole.ADMIN),
            User(id = "4", username = "Jane",role = UserRole.ADMIN)
        )
        UsersScreen(users = users, onUserClicked = {}, addUser = {},addAdmin={}, navController = NavController(""))
    }
}