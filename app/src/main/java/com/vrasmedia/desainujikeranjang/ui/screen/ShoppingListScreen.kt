package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vrasmedia.desainujikeranjang.R
import com.vrasmedia.desainujikeranjang.ui.BottomNavigationBar
import com.vrasmedia.desainujikeranjang.ui.Routes

// Data model sederhana untuk produk rekomendasi
data class RecommendedProduct(
    val name: String,
    val category: String,
    val price: String,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavHostController,
    currentRoute: String = Routes.ShoppingList,
    onAddToCart: () -> Unit
) {
    var activeTab by remember { mutableIntStateOf(0) }

    // Data sesuai gambar: Teh Botol, Aqua, Ultra Milk
    val recommendations = remember {
        listOf(
            RecommendedProduct("Tehbotol Sosro Minuman Teh Original", "Teh Botol", "Rp 4.000", R.drawable.img_tehbotol), // Ganti ke drawable teh botol
            RecommendedProduct("Aqua Air Mineral Botol 600 Ml", "Air Mineral", "Rp 3.600", R.drawable.img_aqua), // Ganti ke drawable aqua
            RecommendedProduct("Ultra Milk Susu UHT Cokelat Kotak 250 Ml", "Susu Cair", "Rp 7.500", R.drawable.img_milku) // Ganti ke drawable ultra milk
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping List", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = { IconButton(onClick = {}) { Icon(Icons.Default.Search, contentDescription = null, tint = Color.White) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = Routes.ShoppingList)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // 1. Tab Khusus (Routine & Favorite) dengan garis bawah biru/gelap
            item {
                Row(modifier = Modifier.fillMaxWidth().height(50.dp)) {
                    CustomTabItem(
                        icon = R.drawable.ic_nav_routineshop, // Ganti ke ikon kalender
                        label = "Routine Shopping",
                        isSelected = activeTab == 0,
                        modifier = Modifier.weight(1f),
                        onClick = { activeTab = 0 }
                    )
                    CustomTabItem(
                        icon = R.drawable.ic_heartshop, // Ganti ke ikon love
                        label = "Favorite Shopping",
                        isSelected = activeTab == 1,
                        modifier = Modifier.weight(1f),
                        onClick = { activeTab = 1 }
                    )
                }
                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
            }

            // 2. Empty State (Ilustrasi Kartun sesuai Gambar)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_empty_transaction1), // Ini gambar kartun wanita
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "Your regular shopping list is empty",
                        fontWeight = FontWeight.ExtraBold, // Tambah ketebalan jika dirasa kurang
                        color = Color.Black,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "We'll recommend the product that you buy regularly & show at this page.",
                        fontWeight = FontWeight.Bold, // Tambah ketebalan jika dirasa kurang
                        color = Color.Gray,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00529C)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.width(150.dp)
                    ) {
                        Text("Let's Shop", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // 3. Section Recommendation For You
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Recommendation For You", fontWeight = FontWeight.ExtraBold,
                        color = Color.Black, fontSize = 16.sp)
                    Text(
                        "See All",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00529C), // Tambah ketebalan jika dirasa kurang
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { }
                    )
                }

                // List Produk Horizontal (Teh Botol, Aqua, Ultra Milk)
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    items(recommendations) { product ->
                        RecommendationProductCard(product)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTabItem(icon: Int, label: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                // Jika ingin warna asli dari file muncul, gunakan Color.Unspecified
                // Jika ingin berubah warna biru/abu, pastikan PNG kamu transparan (bolong)
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) Color(0xFF00529C) else Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        // Garis Indikator di bawah tab
        if (isSelected) {
            Box(modifier = Modifier.fillMaxWidth(0.8f).height(3.dp).background(Color(0xFF00529C)))
        }
    }
}

@Composable
fun RecommendationProductCard(product: RecommendedProduct) {
    Card(
        modifier = Modifier.width(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFF0F0F0))
    ) {
        Column {
            // Gambar Produk
            Box(modifier = Modifier.fillMaxWidth().height(130.dp)) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            // Label Kategori (Merah)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD11F1F))
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(product.category, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.name,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    minLines = 2,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.ExtraBold, // Tambah ketebalan jika dirasa kurang
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(product.price, fontWeight = FontWeight.ExtraBold, // Tambah ketebalan jika dirasa kurang
                    color = Color.Black, fontSize = 15.sp)

                // Info Instant Delivery
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_instan),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Unspecified // Agar detail ikon asli muncul
                    )
                    Text(" Instant Delivery", color = Color(0xFFD11F1F), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }

                // Tombol + Basket (Biru)
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(34.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00529C)),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("+ Basket", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}