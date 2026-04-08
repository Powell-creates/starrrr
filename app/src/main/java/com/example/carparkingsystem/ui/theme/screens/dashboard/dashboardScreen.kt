package com.example.carparkingsystem.ui.theme.screens.dashboard


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController){
    val selectedItem = remember { mutableStateOf(0)}

    // 🌠 Background animation
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing)
        )
    )

    // 🧊 Floating animation
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    val floatShadow by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        modifier = Modifier.background(Color.Black),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Parking Dashboard",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White.copy(alpha = 0.9f + (floatOffset / 60f)),
                        style = TextStyle(
                            brush = Brush.horizontalGradient(
                                listOf(Color.Cyan, Color.Magenta)
                            )
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF020617)) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home,null, tint = Color.Cyan) },
                    label = { Text("home", color = Color.White)}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.AccountCircle,null, tint = Color.Magenta) },
                    label = { Text("profile", color = Color.White)}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Settings,null, tint = Color.Green) },
                    label = { Text("setting", color = Color.White)}
                )
            }
        }
    )
    {padding ->

        // 🌠 STARFIELD + COMETS
        Canvas(modifier = Modifier.fillMaxWidth()) {
            val w = size.width
            val h = size.height

            repeat(40) {
                val x = Random.nextFloat() * w
                val y = (Random.nextFloat() * h + animatedOffset) % h

                drawCircle(
                    color = Color.White.copy(alpha = 0.4f),
                    radius = Random.nextFloat() * 3f,
                    center = Offset(x, y)
                )
            }

            // comet streak
            repeat(5) {
                val startX = Random.nextFloat() * w
                val startY = (Random.nextFloat() * h + animatedOffset) % h

                drawLine(
                    brush = Brush.linearGradient(
                        listOf(Color.White, Color.Transparent)
                    ),
                    start = Offset(startX, startY),
                    end = Offset(startX + 80f, startY + 120f),
                    strokeWidth = 2f
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Smart Parking System",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .graphicsLayer {
                        translationY = floatOffset
                        rotationX = floatOffset / 2
                        rotationY = floatOffset / 3
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(onPress = { tryAwaitRelease() })
                    }
                    .shadow(floatShadow.dp, RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0A2540).copy(alpha = 0.9f)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Available", color = Color.Cyan)
                        Text("18 SLOTS", fontSize = 24.sp, color = Color.White)
                    }
                    Column {
                        Text("Occupied", color = Color.Magenta)
                        Text("32 slots", fontSize = 24.sp, color = Color.White)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            translationY = floatOffset
                            rotationZ = floatOffset / 5
                        },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(floatShadow.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Cyan)
                ) {
                    Text("Add Car",
                        modifier = Modifier.padding(20.dp),
                        color = Color.Black)
                }

                Card(
                    modifier = Modifier
                        .graphicsLayer {
                            translationY = floatOffset
                            rotationZ = floatOffset / 5
                        },
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(floatShadow.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Magenta)
                ) {
                    Text("View Cars",
                        modifier = Modifier.padding(20.dp),
                        color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    MaterialTheme { Dashboard(rememberNavController())}
}