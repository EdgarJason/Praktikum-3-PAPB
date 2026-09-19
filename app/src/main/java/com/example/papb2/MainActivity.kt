package com.example.papb2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papb2.R

val LightBluePrimary = Color(0xFF81D4FA)
val LightBlueDarkText = Color(0xFF01579B)
val LightBlueBackground = Color(0xFFE1F5FE)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = LightBlueBackground
            ) {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("menu") }

    when (currentScreen) {
        "menu" -> MainMenuScreen(onNavigate = { screen -> currentScreen = screen })
        "counter" -> CounterScreen(onBack = { currentScreen = "menu" })
        "toggle" -> ColorToggleScreen(onBack = { currentScreen = "menu" })
        "profile" -> ProfileScreen(onBack = { currentScreen = "menu" })
    }
}

@Composable
fun MainMenuScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Menu Utama",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavigate("counter") },
            colors = ButtonDefaults.buttonColors(containerColor = LightBluePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = "Counter Plus Minus", fontSize = 16.sp, color = LightBlueDarkText, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigate("toggle") },
            colors = ButtonDefaults.buttonColors(containerColor = LightBluePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = "Toggle Box", fontSize = 16.sp, color = LightBlueDarkText, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigate("profile") },
            colors = ButtonDefaults.buttonColors(containerColor = LightBluePrimary),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = "Profile", fontSize = 16.sp, color = LightBlueDarkText, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CounterScreen(onBack: () -> Unit) {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Counter Plus-Minus",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$count",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    if (count > 0) count--
                },
                colors = ButtonDefaults.buttonColors(containerColor = LightBluePrimary)
            ) {
                Text(text = "Kurang (-)", color = LightBlueDarkText, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = {
                    count++
                },
                colors = ButtonDefaults.buttonColors(containerColor = LightBluePrimary)
            ) {
                Text(text = "Tambah (+)", color = LightBlueDarkText, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(onClick = onBack) {
            Text(text = "Kembali ke Menu", color = LightBlueDarkText)
        }
    }
}

@Composable
fun ColorToggleScreen(onBack: () -> Unit) {
    var isRed by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Toggle Box Warna",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(if (isRed) Color.Red else Color.Green)
                .clickable { isRed = !isRed },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Klik untuk Ganti",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(onClick = onBack) {
            Text(text = "Kembali ke Menu", color = LightBlueDarkText)
        }
    }
}

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    var isFollowing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My Profile",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "EDGAR JASON HUSIN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Mahasiswa Teknik Informatika FILKOM UB",
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { isFollowing = !isFollowing },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFollowing) Color.Gray else LightBluePrimary
            )
        ) {
            Text(
                text = if (isFollowing) "Unfollow" else "Follow",
                color = if (isFollowing) Color.White else LightBlueDarkText,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = if (isFollowing) "Akun sudah diikuti" else "Akun belum diikuti",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = LightBlueDarkText
        )
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedButton(onClick = onBack) {
            Text(text = "Kembali ke Menu", color = LightBlueDarkText)
        }
    }
}