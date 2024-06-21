package my.android.geminichatbot.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import my.android.geminichatbot.Models.MessageModel
import my.android.geminichatbot.Utilities.Constansts

class ChatViewModel : ViewModel(){



    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = Constansts.apiKey
    )

    fun sendMessage(question: String){
        viewModelScope.launch {

            try {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role){text(it.message)}
                    }.toList()
                )

                messageList.add(MessageModel(question, "user"))
                messageList.add(MessageModel("Typing....","model"))

                val response = chat.sendMessage(question)
                messageList.removeLast()
                messageList.add(MessageModel(response.text.toString(), "model"))

            }catch (e: Exception){
                messageList.removeLast()
                messageList.add(MessageModel("Error Occured:" +e.message.toString(), "model"))

            }            }


    }
}