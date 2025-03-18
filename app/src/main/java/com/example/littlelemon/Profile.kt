package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profile(context: Context, navController: NavController) {
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val firstName = remember { mutableStateOf(sharedPreferences.getString("firstName", "")) }
    val lastName = remember { mutableStateOf(sharedPreferences.getString("lastName", "")) }
    val emailAddress = remember { mutableStateOf(sharedPreferences.getString("emailAddress", "")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
            ) {
                Text(
                    text = "Personal information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "First name",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 24.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = firstName.value!!,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Text(
                text = "Last name",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = lastName.value!!,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Text(
                text = "Email",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = emailAddress.value!!,
                onValueChange = {},
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedButton(
                onClick = { navController.navigate(Onboarding.route) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}