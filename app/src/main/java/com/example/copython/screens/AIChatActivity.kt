 package com.example.copython.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.copython.navigation.AppScreens

 @Composable
fun AIChatLayout(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TopTitle("AI Assistant")

        ChatStructure()
        // Bottom Navigation Bar
        BottomBar(navController)
    }

}

@Composable()
fun ChatStructure(){
    Box( modifier = Modifier
        .height(600.dp)
        .padding(30.dp)
        .fillMaxWidth()
        .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
    ){
        Column ( modifier = Modifier
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                ChatBox(red = 232, green = 175, blue = 48, message = "Me podrias explicar que es una lista en progra?", _textAlign = TextAlign.Right)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                ChatBox(red = 135, green = 206, blue = 235, message = "En programación, una lista es una colección ordenada de elementos (números, palabras u otros datos). Funciona como una lista de compras: agregas elementos y se mantienen en el mismo orden. Puedes añadir, eliminar y acceder a los elementos por su posición.", _textAlign = TextAlign.Left)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                ChatBox(red = 232, green = 175, blue = 48, message = "Muchas gracias!", _textAlign = TextAlign.Right)
            }

            Spacer(modifier = Modifier.height(180.dp))

            UserRequestInput()

        }

    }
}

@Composable
fun ChatBox(red: Int, green: Int, blue: Int, message: String, _textAlign: TextAlign){

    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(red, green, blue), shape = RoundedCornerShape(16.dp))
        ) {
        Text(
            fontSize = 12.sp,
            text = message,
            textAlign = _textAlign,
            modifier = Modifier.padding(16.dp))
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRequestInput() {
    var request = ""
    TextField(value = request,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {request = it},
        label = {Text("Type your request here") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color.LightGray)
    )
}

