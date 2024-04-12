package ui.screens.MessageScreen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Message
import domain.model.User
import domain.model.UserRole
import ui.IHMSTheme
import ui.navigation.NavController
import java.util.UUID

@Composable
fun MessagingScreen(
    modifier: Modifier=Modifier,
    users:List<User>,
    messages:List<Message>,
    currentUser: User,
    currentMessageReceiver:User,
    hasUser:Boolean,
    setHasUser:(Boolean)->Unit,
    message:String,
    onMessageChange:(String)->Unit,
    onSendClick:() -> Unit,
    setCurrentReceiver:(User)->Unit,
    setCurrentUser:(User)->Unit,
    navController: NavController,
    messageScreenUiState: MessagingScreenUiState,
    setViewMessage:() -> Unit
){
    Box(modifier = modifier.fillMaxSize()){
        if (!hasUser){
            SelectSenderScreen(users = users,currentUser=currentUser, updateCurrentUser = setCurrentUser, updateHasUser = setHasUser)
        }else{
            SendMessageScreen(users = users,currentUser=currentUser,currentMessageReceiver=currentMessageReceiver,messages=messages,setCurrentReceiver=setCurrentReceiver,message=message,onMessageChange=onMessageChange,onSendClick=onSendClick, messageScreenUiState = messageScreenUiState,setViewMessage)
        }
    }
}

@Composable
fun SelectSenderScreen(users: List<User>, currentUser: User,updateCurrentUser:(User) -> Unit,updateHasUser:(Boolean)->Unit){

    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background), horizontalAlignment = Alignment.CenterHorizontally){
        Row(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier.fillMaxWidth(0.45f).fillMaxHeight()){
                Text(text = "Select Sender", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    items(items = users){
                            user -> UserItem(user = user, onUserClick = updateCurrentUser)
                    }
                }
            }
            Column(modifier = Modifier){
                Text("Current Message Sender", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = currentUser.username, fontSize = 14.sp, fontWeight = FontWeight.ExtraLight)
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {updateHasUser(true)}){
                    Text(text = "Continue", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
@Composable
fun SendMessageScreen(users: List<User>, currentUser: User, currentMessageReceiver: User, messages: List<Message>,setCurrentReceiver: (User) -> Unit,message:String,onMessageChange: (String) -> Unit,onSendClick: () -> Unit,messageScreenUiState: MessagingScreenUiState,setViewMessage: () -> Unit){
    var messageList by remember { mutableStateOf<List<Message>>(emptyList()) }
    messageList=messageScreenUiState.messages
    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)){
        Row(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier.fillMaxSize(0.33f)){
                Text(text = "Users", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    items(items = users){
                            user -> UserItem(user = user, onUserClick = setCurrentReceiver)
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight()){
                Text(text = "Chats", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Current User", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(currentUser.username,fontWeight = FontWeight.ExtraLight, fontSize = 14.sp)
                Text(text = "Receiver", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(currentMessageReceiver.username,fontWeight = FontWeight.ExtraLight, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(text = "Messages", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                }
                LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
                    items(items = messageList){
                        msg -> MessageItem(currentUser = currentUser, message = msg)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
                Text(text = "Send Message", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(5.dp))
                TextField(value = message, onValueChange = {onMessageChange(it)})
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {onSendClick()}) {
                    Text(text = "Send", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Button(onClick = setViewMessage){
                    Text("View Messages")
                }
            }
        }
    }

}
@Composable
fun UserItem(modifier: Modifier=Modifier,user: User,onUserClick: (User) -> Unit){
    Card(modifier = modifier.fillMaxWidth(0.96f).clickable { onUserClick(user) })
    {
        Row(modifier = Modifier.fillMaxWidth(0.96f).background(color = MaterialTheme.colors.surface).clip(RoundedCornerShape(12.dp),
        ), verticalAlignment = Alignment.CenterVertically){
            Icon(Icons.Outlined.AccountCircle, contentDescription = user.username,)
            Text(modifier = Modifier.padding(16.dp),text = user.username.uppercase(), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(5.dp))
            Text(modifier = Modifier.padding(16.dp),text = "${user.role}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}
@Composable
fun MessageItem(modifier: Modifier=Modifier,message: Message,currentUser: User){
    val isFromCurrentUser = (message.senderId==currentUser.id)
    Spacer(modifier = Modifier.height(2.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = if (isFromCurrentUser)Arrangement.End else Arrangement.Start){
        Card(modifier = modifier.fillMaxWidth(0.4f).clip(RoundedCornerShape(0.5f)), shape = RoundedCornerShape(12.dp)){
            Row(modifier=modifier.fillMaxWidth().clip(RoundedCornerShape(0.5f))){
                Text(message.message, modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp),fontSize = 14.sp, fontWeight = FontWeight.ExtraLight)
            }
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Preview
@Composable
fun MessagingScreenPreview() {
    IHMSTheme{
        var currentUser by remember { mutableStateOf(User("Wassanyi kevin","",UserRole.MEMBER)) }
        val users =listOf(
            User(id = UUID.randomUUID().toString(),username = "Wassanyi",role = UserRole.MEMBER),
            User(id = UUID.randomUUID().toString(),username = "Kevin",role = UserRole.ADMIN),
            User(id = UUID.randomUUID().toString(),username = "Stephen",role = UserRole.MEMBER))
        val messages =listOf(
            Message(messageId = UUID.randomUUID().toString(),senderId = users[0].id,receiverId = users[1].id,message = "Hello"),
            Message(messageId = UUID.randomUUID().toString(),senderId = users[1].id,receiverId = users[0].id,message = "hello"),
            Message(messageId = UUID.randomUUID().toString(),senderId = users[1].id,receiverId = users[0].id,message = "hello"),
            Message(messageId = UUID.randomUUID().toString(),senderId = users[1].id,receiverId = users[0].id,message = "hello"),
            Message(messageId = UUID.randomUUID().toString(),senderId = users[0].id,receiverId = users[1].id,message = "Hello"))
//        SelectSenderScreen(users = users, currentUser = currentUser, updateCurrentUser = {currentUser=it}, updateHasUser = {})
//        SendMessageScreen(users = users, currentUser = users[0], messages = messages, currentMessageReceiver = users[1], setCurrentReceiver = {}, message = "", onMessageChange = {}, onSendClick = {})
        MessageItem(message = messages[0], currentUser = users[0])
    }
}