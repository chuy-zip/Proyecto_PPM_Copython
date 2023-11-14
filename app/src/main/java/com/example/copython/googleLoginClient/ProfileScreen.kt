package com.example.copython.googleLoginClient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    userName: String?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       if(userData?.profilePictureUrl != null) {
           AsyncImage(
               model = userData.profilePictureUrl,
               contentDescription = "PFP",
               modifier = Modifier
                   .size(150.dp)
                   .clip(CircleShape),
               contentScale = ContentScale.Crop
               )
           Spacer(modifier = Modifier.height(16.dp))
       }

        if(userData?.userName != null) {
            Text(text = userData.userName,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold)
            
            Spacer(modifier = Modifier.height(16.dp))
        } else if (userName != null) {
            Text(text = userName,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(onClick = onSignOut) {
            Text(text = "Sign Out")
        }
    }

}