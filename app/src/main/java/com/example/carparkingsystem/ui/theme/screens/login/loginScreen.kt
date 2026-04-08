package com.example.carparkingsystem.ui.theme.screens.login


import android.graphics.Color
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.carparkingsystem.R

@Composable
fun LogInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // 🌈 Animated gradient
    val infiniteTransition = rememberInfiniteTransition()
    val colorShift by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF0F2027),
                        androidx.compose.ui.graphics.Color(0xFF203A43),
                        androidx.compose.ui.graphics.Color(0xFF2C5364)
                    ),
                    start = androidx.compose.ui.geometry.Offset(0f, colorShift),
                    end = androidx.compose.ui.geometry.Offset(1000f, 0f)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .shadow(14.dp, CircleShape)
                .border(
                    2.dp,
                    androidx.compose.ui.graphics.Color(0xFFFFD700),
                    CircleShape
                )
        )



        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = androidx.compose.ui.graphics.Color.LightGray) },
            leadingIcon = {
                Icon(Icons.Default.Email, null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(12.dp)),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(

                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Gray,
                focusedContainerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.07f),
                unfocusedContainerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.03f)
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = androidx.compose.ui.graphics.Color.LightGray) },
            leadingIcon = {
                Icon(Icons.Default.Lock, null)
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(12.dp)),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(

                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Gray,
                focusedContainerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.07f),
                unfocusedContainerColor = androidx.compose.ui.graphics.Color.White.copy(alpha = 0.03f)
            )
        )


        Button(
            onClick = {},
            modifier = Modifier.shadow(16.dp, RoundedCornerShape(30.dp)),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(

            )
        ) {
            Text(
                text = "Register",
                color = androidx.compose.ui.graphics.Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Are you registered?",
                fontSize = 13.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )

            Text(
                text = " Log in here",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,

            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
        LogInScreen(rememberNavController())

}
