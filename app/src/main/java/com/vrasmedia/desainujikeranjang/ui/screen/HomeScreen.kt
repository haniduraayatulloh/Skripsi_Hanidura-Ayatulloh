package com.vrasmedia.desainujikeranjang.ui.screenimport

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vrasmedia.desainujikeranjang.R
import com.vrasmedia.desainujikeranjang.ui.BottomNavigationBar
import com.vrasmedia.desainujikeranjang.ui.Routes

@Composable
fun HomeScreen(
    navController: NavHostController,
    onBeverageClick: () -> Unit,
    onTehBotolClick: () -> Unit,
    onCartClick: () -> Unit // Parameter untuk aksi klik keranjang
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Routes.Home)
        }
    ) { innerPadding ->

        // GUNAKAN LAZYCOLUMN MURNI SEBAGAI UTAMA
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5))
        ) {
            // Mengirim onCartClick ke HeaderSection
            item { HeaderSection(onCartClick = onCartClick) }
            item { BannerCarouselSection() }
            item {
                MenuGridSection()
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// ==========================================
// 1. HEADER SECTION (Top Bar, Search, Member Card)
// ==========================================
@Composable
fun HeaderSection(onCartClick: () -> Unit) { // Tambahkan parameter di sini
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE52323), Color(0xFFFA8B8B))
                )
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            // --- BARIS 1: Metode Order (Kiri) & Icons (Kanan) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Order method not selected yet", color = Color.White, fontSize = 12.sp)

                // Ikon di pojok kanan atas
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.ic_chat), contentDescription = null, tint = Color.White, modifier = Modifier
                        .size(22.dp)
                        .clickable { /* Chat */ })
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(painterResource(id = R.drawable.ic_notif), contentDescription = null, tint = Color.White, modifier = Modifier
                        .size(22.dp)
                        .clickable { /* Notif */ })
                    Spacer(modifier = Modifier.width(12.dp))

                    // ICON KERANJANG - Sekarang bisa diklik
                    IconButton(
                        onClick = { onCartClick() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = "Cart",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            // --- BARIS 2: Nama Kota ---
            Text(
                text = "Surabaya",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            // --- BARIS 3: Please choose (Kiri) & Try Self-service (Kanan) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Please choose order method first", color = Color.White, fontSize = 12.sp)

                Surface(
                    onClick = { /* Interaksi */ },
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Try Self-service →",
                        color = Color.White,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Baris Tengah: Search Bar dan Tombol Favorit
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Find your favorite products", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        Icon(painterResource(id = R.drawable.ic_scan), contentDescription = "Scan")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .background(Color.White, RoundedCornerShape(25.dp)),
                    shape = RoundedCornerShape(25.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White, CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Favorite",
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Member Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Hi, Sandro", fontWeight = FontWeight.Bold, color = Color(0xFF1E5128))
                        Text("Newbie Member >", color = Color.Gray, fontSize = 12.sp)
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        MemberInfoItem("0", "Exchange A-Points")
                        MemberInfoItem("0", "Voucher")
                        MemberInfoItem("0", "Stamp")
                        MemberInfoItem("0", "Star")
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_barcode), contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Barcode Member", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun MemberInfoItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, fontSize = 10.sp, color = Color.Gray)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarouselSection() {
    val pagerState = rememberPagerState(pageCount = { 8 })

    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { _ ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_banner_homecare),
                    contentDescription = "Promo Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                repeat(8) { index ->
                    val color = if (pagerState.currentPage == index) Color(0xFF00529C) else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }

            Text(
                text = "See All Promos",
                color = Color(0xFF00529C),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { }
            )
        }
    }
}

data class MenuItem(val title: String, val iconRes: Int)

@Composable
fun MenuGridSection() {
    val menus = listOf(
        MenuItem("Produk Online", R.drawable.ic_menu_online),
        MenuItem("Kalkulator Zat Besi", R.drawable.ic_menu_iron),
        MenuItem("Isi Ulang & Tagihan", R.drawable.ic_menu_bills),
        MenuItem("Gift Card", R.drawable.ic_menu_gift),
        MenuItem("Tukar A-Poin", R.drawable.ic_menu_apoin),
        MenuItem("Brand Deals", R.drawable.ic_menu_brand),
        MenuItem("Personal Color Test", R.drawable.ic_menu_color),
        MenuItem("Bean Spot", R.drawable.ic_menu_coffee),
        MenuItem("Kartonan & Kemasan Be...", R.drawable.ic_menu_box),
        MenuItem("Healthy Corner", R.drawable.ic_menu_healthy)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val chunks = menus.chunked(5)
        for (rowItems in chunks) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (item in rowItems) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(70.dp)
                            .clickable { }
                    ) {
                        Image(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                            color = Color.Black,
                            lineHeight = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}