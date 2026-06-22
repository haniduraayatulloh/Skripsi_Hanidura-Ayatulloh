package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vrasmedia.desainujikeranjang.R
import com.vrasmedia.desainujikeranjang.ui.BottomNavigationBar
import com.vrasmedia.desainujikeranjang.ui.Routes

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, currentRoute = Routes.Profile) }// Menggunakan BottomNav yang sama dengan halaman sebelumnya
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8F9FA)) // Background abu-abu sangat tipis bersih
        ) {

            // 1. Header Merah Gradient + Card Informasi Akun Utuh
            item {
                ProfileHeaderSection()
            }

            // 2. Banner Shopping Mission (Gambar Utuh)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_shopping_mission1), // Taruh potongan banner oranye di drawable
                        contentDescription = "Shopping Mission Banner",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 3. Kelompok Menu Pengaturan Akun (White Background)
            item {
                Column(modifier = Modifier.background(Color.White)) {
                    ProfileMenuItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null, tint = Color(0xFF00529C)) },
                        title = "Account Settings",
                        subtitle = "Change password, change PIN & address list"
                    )
                    ProfileMenuItem(
                        icon = { Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF00529C)) },
                        title = "Rating & Reviews",
                        subtitle = null
                    )
                    ProfileMenuItem(
                        icon = { Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFF00529C)) },
                        title = "Notification Settings",
                        subtitle = "Notification setting is off. Turn on now"
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // 4. Kelompok Menu Fitur Tambahan & Affiliate
            item {
                Column(modifier = Modifier.background(Color.White)) {
                    ProfileMenuItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF00529C)) },
                        title = "Invite your friend to use Alfagift",
                        subtitle = null
                    )
                    ProfileMenuItem(
                        icon = { Icon(Icons.Default.Share, contentDescription = null, tint = Color(0xFF00529C)) },
                        title = "Alfagift Affiliate",
                        subtitle = null,
                        isNew = true // Flag khusus untuk memunculkan badge merah "NEW !"
                    )
                }
            }
        }
    }
}

// ==========================================
// COMPONENT 1: PROFILE HEADER SECTION
// ==========================================
@Composable
fun ProfileHeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFD11F1F), Color(0xFFE54A4A))
                )
            )
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Mengatur kiri dan kanan
            .padding(top = 16.dp)        // Mengatur atas saja
        )
        {
            Text(
                text = "Account",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // PEMBENAHAN GAMBAR PROFILE AGAR SESUAI DESAIN KECIL
            Image(
                painter = painterResource(id = R.drawable.img_profile_card),
                contentDescription = "User Profile Card Info",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // KUNCI UTAMA: Kunci tinggi maksimal kartu agar tidak melar raksasa ke bawah
                    .clickable { /* Edit profile */ },
                contentScale = ContentScale.Fit // Memastikan rasio gambar tetap proporsional dan pas di tengah
            )
        }
    }
}

// ==========================================
// COMPONENT 2: GENERIC PROFILE MENU ITEM (LIST ITEM)
// ==========================================
@Composable
fun ProfileMenuItem(
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String?,
    isNew: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Menu Sisi Kiri
            icon()

            Spacer(modifier = Modifier.width(16.dp))

            // Judul & Subtitle Menu
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            // Kondisional Badge "NEW !" Merah jika diatur true
            if (isNew) {
                Surface(
                    color = Color(0xFFD11F1F),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "NEW !",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            // Panah Klik Sisi Kanan (Chevron)
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Navigate",
                tint = Color.LightGray,
                modifier = Modifier.size(20.dp)
            )
        }

        // Garis pemisah tipis antar menu
        HorizontalDivider(
            thickness = 0.5.dp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
