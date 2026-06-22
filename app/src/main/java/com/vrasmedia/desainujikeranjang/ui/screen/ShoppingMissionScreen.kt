package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrasmedia.desainujikeranjang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingMissionScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Mission", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White,
                        modifier = Modifier.clickable { /* Back action */ })
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // 1. Tab Selector (Special Mission & Multi Level Mission)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD11F1F))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    TabItemCustom(text = "Special Mission", isSelected = selectedTab == 0, modifier = Modifier.weight(1f)) { selectedTab = 0 }
                    TabItemCustom(text = "Multi Level Mission", isSelected = selectedTab == 1, modifier = Modifier.weight(1f)) { selectedTab = 1 }
                }
            }

            // 2. Banner Utama (Gambar Utuh)
            item {
                Image(
                    painter = painterResource(id = R.drawable.img_banner_shopping_mission),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            // 3. Progres Misi (Gambar Utuh) + Syarat Ketentuan
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.img_progress),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable {}.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painterResource(R.drawable.ic_tnc), contentDescription = null, tint = Color(0xFF00529C))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Special Mission Terms & Conditions", color = Color(0xFF00529C), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color(0xFF00529C)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 4. List Item Misi yang Tidak Tersedia
            items(2) {
                MissionExpiredCard()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun TabItemCustom(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(4.dp)
            // Perbaikan pada Color(0---------) menjadi warna merah yang lebih gelap
            .background(if (isSelected) Color(0xFFA51818) else Color.Transparent, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal, fontSize = 14.sp)
    }
}

@Composable
fun MissionExpiredCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Special Mission Boost & Win Product Online Septemb...", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Belanja Produk Online Dengan Total Akumulasi Sebesar Rp. 500.000,- S/D 15 Oktober 2025 Untuk Mendapatkan Reward A-Voucher Rp. ....", color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painterResource(R.drawable.img_dummy_reward), contentDescription = null, modifier = Modifier.size(100.dp, 40.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Mission no longer available", color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}