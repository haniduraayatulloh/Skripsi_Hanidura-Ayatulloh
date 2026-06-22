package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vrasmedia.desainujikeranjang.R
import com.vrasmedia.desainujikeranjang.ui.BottomNavigationBar
import com.vrasmedia.desainujikeranjang.ui.Routes

// Model Data Dummy untuk Produk Promo
data class ProductPromo(
    val id: Int,
    val name: String,
    val category: String,
    val currentPrice: String,
    val originalPrice: String?,
    val discountPercent: String?,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }

    // Data Dummy Produk sesuai dengan Gambar yang Diupload
    val promoProducts = remember {
        listOf(
            ProductPromo(1, "Good Day Kopi Instan Cappucino 10 X 25g", "Kopi Bubuk", "Rp 22.900", "Rp 25.200", "9%", R.drawable.img_good_day),
            ProductPromo(2, "Frisian Flag Krimer Kental Manis Putih 535g", "Kental Manis", "Rp 14.500", "Rp 19.500", "26%", R.drawable.img_frissian_flag),
            ProductPromo(3, "MamaSuka Rumput Laut Panggang 2 X 4,5g", "Camilan Rumput Laut", "Rp 14.500", null, null, R.drawable.img_mamasuka),
            ProductPromo(4, "Sania Minyak Goreng Pouch 2 L", "Minyak Goreng", "Rp 38.400", null, null, R.drawable.img_sania)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Promo", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* Interaksi: Buka Fitur Search Promo */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController, currentRoute = Routes.Promo) } // Memanggil bottom navigation yang sudah ada
    ) { innerPadding ->

        // Menggunakan LazyVerticalGrid sebagai kontainer utama agar grid produk efisien saat di-scroll
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 Kolom ke samping sesuai desain
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // 1. Row Tab Menu Atas (Special Price, Free Product, Package)
            // Menggunakan GridItemSpan agar baris ini memakan 2 kolom penuh (tidak terbagi dua)
            item(span = { GridItemSpan(maxLineSpan) }) {
                PromoTabRow(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
            }

            // 2. Row Filter Dropdown (Promo, Product Online, Delivery, Delivery)
            item(span = { GridItemSpan(maxLineSpan) }) {
                FilterChipsRow()
            }

            // 3. Grid Items Produk Promo Dinamis
            items(promoProducts) { product ->
                ProductPromoCard(product = product, onBasketClick = {
                    /* Interaksi Front-End: Tambah jumlah item di keranjang belanja */
                })
            }
        }
    }
}

// ==========================================
// COMPONENT 1: PROMO TAB ROW
// ==========================================
@Composable
fun PromoTabRow(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.White,
        contentColor = Color(0xFF00529C),
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = Color(0xFF00529C),
                height = 2.dp
            )
        }
    ) {
        val tabs = listOf("Special Price", "Free Product", "Package")
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        color = if (selectedTab == index) Color(0xFF00529C) else Color.Gray
                    )
                }
            )
        }
    }
}

// ==========================================
// COMPONENT 2: FILTER CHIPS ROW (HORIZONTAL)
// ==========================================
@Composable
fun FilterChipsRow() {
    val filters = listOf("Promo ∨", "Product Online ∨", "Delivery ∨")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        filters.forEach { filterText ->
            Box(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(text = filterText, fontSize = 12.sp, color = Color.Black)
            }
        }
    }
}

// ==========================================
// COMPONENT 3: PRODUCT PROMO CARD (GRID ITEM)
// ==========================================
@Composable
fun ProductPromoCard(product: ProductPromo, onBasketClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // Bagian Atas: Gambar Produk & Badge Tag Diskon Samping
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )

                // Efek Banner Pita Merah Kategori Produk (Di bawah Gambar Persis)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD11F1F))
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = product.category,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Bagian Bawah: Informasi Detail Teks Produk
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    minLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Baris Harga Utama Aktif
                Text(
                    text = product.currentPrice,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                // Baris Coretan Diskon (Hanya muncul jika diskon tidak null)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (product.discountPercent != null && product.originalPrice != null) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFD11F1F), RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = product.discountPercent,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = product.originalPrice,
                            color = Color.Gray,
                            fontSize = 11.sp,
                            textDecoration = TextDecoration.LineThrough // Efek coret harga asli
                        )
                    } else {
                        // Spacer pengisi ruang agar tinggi kartu tetap seragam seimbang
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Label Instant Delivery Kecil
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_instan),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Instant Delivery", color = Color(0xFFD11F1F), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // TOMBOL INTERAKTIF: + Basket
                Button(
                    onClick = onBasketClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00529C)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "+ Basket", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}
