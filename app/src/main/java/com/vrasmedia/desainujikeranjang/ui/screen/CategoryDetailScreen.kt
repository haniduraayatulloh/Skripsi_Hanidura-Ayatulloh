package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun CategoryDetailScreen(
    navController: NavHostController, // Pastikan baris ini ada
    onTehBotolClick: () -> Unit       // Pastikan baris ini ada
) {
    val categoryProducts = listOf(
        ProductPromo(1, "Good Day Kopi Instan Cappucino 10 X 25g", "Kopi Bubuk", "Rp 22.900", "Rp 25.200", "9%", R.drawable.img_good_day),
        ProductPromo(2, "Frisian Flag Krimer Kental Manis Putih 535g", "Kental Manis", "Rp 14.500", "Rp 19.500", "26%", R.drawable.img_frissian_flag),
        ProductPromo(3, "Tehbotol Sosro Minuman Teh Original ...", "Teh Sosro", "Rp 4.200", null, null, R.drawable.img_tehbotol),
        ProductPromo(4, "Diabetasol Vita Digest Susu Bubuk Dewasa C...", "Susu Bubuk", "Rp 38.400", null, null, R.drawable.img_diabetasol)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        placeholder = { Text("Find your favorite products", fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth().height(46.dp).background(Color.White, RoundedCornerShape(23.dp)),
                        shape = RoundedCornerShape(23.dp)
                    )
                },
                navigationIcon = { Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White, modifier = Modifier.padding(horizontal = 8.dp)) },
                actions = { Icon(painterResource(R.drawable.ic_nav_shopping), contentDescription = null, tint = Color.White, modifier = Modifier.padding(end = 8.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController, currentRoute = Routes.CategoryDetail) }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(innerPadding).background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Filter Baris Atas Nama Kategori & Sub-Kategori Slider Horizontal
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Minuman", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Kategori Lain ∨", color = Color(0xFF00529C), fontSize = 14.sp)
                    }
                    Image(painterResource(R.drawable.img_sub_categories_slider), contentDescription = null, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    FilterChipsRow() // Memanggil filter chips row yang sudah kita buat sebelumnya
                }
            }

            // Daftar Item Produk Berdasarkan Grid
            items(categoryProducts) { product ->
                ProductPromoCard(product = product, onBasketClick = {})
            }
        }
    }
}