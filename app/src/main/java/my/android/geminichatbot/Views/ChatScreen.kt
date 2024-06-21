package my.android.geminichatbot.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.android.geminichatbot.Models.MessageModel
import my.android.geminichatbot.R
import my.android.geminichatbot.ViewModels.ChatViewModel
import my.android.geminichatbot.ui.theme.AppBackground
import my.android.geminichatbot.ui.theme.ColorModelMessage
import my.android.geminichatbot.ui.theme.ColorUserMessage
import my.android.geminichatbot.ui.theme.DarkGreen
import my.android.geminichatbot.ui.theme.HunterGreen

@Composable
fun ChatScreen(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppBackground)) {
        AppHeader()
        MessageList(
            modifier = Modifier.weight(1f),
            messageList = viewModel.messageList
        )
        MessageInput(onMessageSend = {
            viewModel.sendMessage(it)
        })
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    if (messageList.isEmpty()) {
        EmptyMessageList(modifier)
    } else {
        LazyColumn(
            modifier = modifier,
            reverseLayout = true
        ) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
fun EmptyMessageList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "Chat Icon",
            tint = DarkGreen
        )
        Text(text = "Ask me anything", fontSize = 22.sp)
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"
    val alignment = if (isModel) Alignment.BottomStart else Alignment.BottomEnd
    val startPadding = if (isModel) 8.dp else 70.dp
    val endPadding = if (isModel) 70.dp else 8.dp
    val icon = if (isModel) Icons.Default.AccountBox else Icons.Default.AccountCircle

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isModel) {
            Icon(
                imageVector = icon,
                contentDescription = "Model Icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 8.dp, end = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(start = startPadding, end = endPadding, top = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (isModel) ColorModelMessage else ColorUserMessage)
                .padding(16.dp)
        ) {
            SelectionContainer {
                Text(
                    text = messageModel.message,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                )
            }
        }
        if (!isModel) {
            Icon(
                imageVector = icon,
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Type a message...") },
            keyboardActions = KeyboardActions(
                onSend = {
                    if (message.isNotEmpty()) {
                        onMessageSend(message)
                        message = ""
                        keyboardController?.hide()
                    }
                }
            )
        )
        IconButton(
            onClick = {
                if (message.isNotEmpty()) {
                    onMessageSend(message)
                    message = ""
                    keyboardController?.hide()
                }
            },
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HunterGreen)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Gemini Bot",
            color = Color.White,
            fontSize = 22.sp
        )
    }
}
