 package com.example.copython.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.copython.Classes.MainViewModel
import com.example.copython.ui.theme.ui.theme.DarkBlue10
import com.example.copython.ui.theme.ui.theme.LightBlue10
import com.example.copython.ui.theme.ui.theme.OrangeYellow


 @Composable
fun AIChatLayout(
     navController: NavController,
     innerPadding: PaddingValues,
     viewModel: MainViewModel = viewModel()
     ){
     var (inputText, setInputText) = remember { mutableStateOf("") }
     val textOutput: String by viewModel.output.collectAsState()
     var lastProcessedTextOutput by remember { mutableStateOf("") }
     var messages by remember { mutableStateOf(emptyList<MessageItem>()) }

     
     Column(
         modifier = Modifier
             .padding(innerPadding),
         verticalArrangement = Arrangement.SpaceBetween,
         horizontalAlignment = Alignment.CenterHorizontally,

     ){
         OutlinedTextField(
             modifier = Modifier.fillMaxWidth(),
             value = inputText,
             onValueChange = setInputText,
             colors= TextFieldDefaults.outlinedTextFieldColors(
                 focusedBorderColor = DarkBlue10,
                 unfocusedBorderColor = OrangeYellow
             ),
             label = { Text(
                 text="Escribe tu pregunta en ingles:",
                 color= DarkBlue10)
             }
         )
         Button(
             onClick = {
                 if(inputText.isNotBlank()){
                     messages = messages + MessageItem(inputText, OrangeYellow, true)
                     viewModel.sendPrompt(inputText, messages)
                     inputText = ""

                 }

             },
             modifier = Modifier.padding(8.dp),
             colors = ButtonDefaults.buttonColors(
                 backgroundColor = OrangeYellow,
                 contentColor= Color.White
             )

         ) {
             Text("Enviar")
         }

         // Check if there is a new output from the AI
         LaunchedEffect(textOutput) {
             if (textOutput.isNotBlank() && textOutput != lastProcessedTextOutput) {
                 // Add AI's response to the list
                 messages = messages + MessageItem(textOutput, LightBlue10, false)
                 lastProcessedTextOutput = textOutput
             }
         }

         MessageList(messages = messages)
     }

}

 @Composable
 fun MessageBubble(
     message: String,
     color: Color,
     isUserMessage: Boolean
 ) {
     Box(
         modifier = Modifier
             .fillMaxWidth()
             .padding(8.dp)
             .background(color)
             .border(1.dp, Color.Black)
             .padding(8.dp),
         contentAlignment = if (isUserMessage) Alignment.CenterEnd else Alignment.CenterStart
     ) {
         Text(
             text = message,
             color = if (isUserMessage) Color.White else Color.Black
         )
     }
 }

 @Composable
 fun MessageList(messages: List<MessageItem>) {
     LazyColumn {
         items(messages.size) { index ->
             MessageBubble(
                 message = messages[index].message,
                 color = messages[index].color,
                 isUserMessage = messages[index].isUserMessage
             )
         }
     }
 }

 data class MessageItem(val message: String, val color: Color, val isUserMessage: Boolean)

