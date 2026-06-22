package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(navController: NavHostController) {
    // State untuk mengontrol tab yang aktif (0 untuk App Purchases, 1 untuk Store Purchases)
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            // TopAppBar Berwarna Merah Solid sesuai Desain
            TopAppBar(
                title = {
                    Text(
                        text = "Order History",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD11F1F) // Merah Alfagift
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Routes.OrderHistory)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // 1. BAGIAN TAB LAYOUT (App Purchases & Store Purchases)
            // ... (bagian atas tetap sama)

            // 1. BAGIAN TAB LAYOUT (App Purchases & Store Purchases)
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = Color(0xFF00529C),
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = Color(0xFF00529C),
                        height = 2.dp // Garis indikator lebih tipis sesuai desain
                    )
                },
                divider = { HorizontalDivider(color = Color(0xFFE0E0E0)) } // Garis pemisah abu-abu tipis
            ) {
                // Tab Pertama: App Purchases
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    // Kita gunakan satu blok konten agar icon dan text bisa berjajar HORIZONTAL
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_phone_android),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp), // UKURAN IKON DISESUAIKAN
                            tint = if (selectedTab == 0) Color(0xFF00529C) else Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // JARAK ANTARA IKON DAN TEKS
                        Text(
                            text = "App Purchases",
                            fontSize = 14.sp,
                            color = if (selectedTab == 0) Color(0xFF00529C) else Color.Gray,
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }

                // Tab Kedua: Store Purchases
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_store),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp), // UKURAN IKON DISESUAIKAN
                            tint = if (selectedTab == 1) Color(0xFF00529C) else Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Store Purchases",
                            fontSize = 14.sp,
                            color = if (selectedTab == 1) Color(0xFF00529C) else Color.Gray,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            // 2. BAGIAN KONTEN (Tergantung Tab yang Dipilih)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (selectedTab == 0) {
                    // Tampilan jika memilih App Purchases (Sesuai Gambar Empty State)
                    EmptyStateSection()
                } else {
                    // Tampilan alternatif jika memilih Store Purchases (Bisa berupa dummy data atau empty state lain)
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No Store Transaction Found", color = Color.Gray)
                    }
                }
            }
        }
    }
}



// ==========================================
// COMPOSABLE COMPONENT: EMPTY STATE SECTION
// ==========================================
@Composable
fun EmptyStateSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // ASET GAMBAR UTUH KARTUN: Diekspor utuh dari desain
        Image(
            painter = painterResource(id = R.drawable.img_empty_transaction), // Taruh potongan gambar kartun beserta background abu-abunya di drawable
            contentDescription = "No transaction illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Teks Judul Empty State
        Text(
            text = "No transaction found",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Teks Deskripsi Subtitle
        Text(
            text = "Let's Shop",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TOMBOL INTERAKTIF: Reload Button
        Button(
            onClick = { /* Interaksi Front-End: Jalankan efek loading tiruan atau refresh halaman */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00529C)), // Warna biru tombol
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(140.dp)
                .height(45.dp)
        ) {
            Text(
                text = "Reload",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}