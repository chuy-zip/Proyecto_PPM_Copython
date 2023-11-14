 package com.example.copython.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.copython.Classes.MainViewModel
import com.example.copython.ui.theme.ui.theme.DarkBlue10
import com.example.copython.ui.theme.ui.theme.OrangeYellow


 @Composable
fun AIChatLayout(
     navController: NavController,
     innerPadding: PaddingValues,
     viewModel: MainViewModel = viewModel()
     ){
     val (inputText, setInputText) = remember { mutableStateOf("") }
     val textOutput: String by viewModel.output.collectAsState()
     Column(
         modifier = Modifier
             .padding(innerPadding)
             .verticalScroll(rememberScrollState()),
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
             label = { Text("Escribe tu pregunta:")
             }
         )
         Button(
             onClick = {
                 viewModel.sendPrompt(inputText)
             },
             modifier = Modifier.padding(8.dp),
             colors = ButtonDefaults.buttonColors(
                 backgroundColor = OrangeYellow,
                 contentColor= Color.White
             )

         ) {
             Text("Enviar")
         }
         Card(
             modifier = Modifier
                 .padding(vertical = 2.dp)
                 .fillMaxWidth()
         ) {
             Column(
                 modifier = Modifier.padding(8.dp)
             ) {
                 Text(
                     modifier = Modifier.fillMaxWidth(),
                     text = textOutput
                 )
             }
         }
     }
}

