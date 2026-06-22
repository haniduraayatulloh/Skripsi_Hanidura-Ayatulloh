package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun OrderSummaryScreen(onBackClick: () -> Boolean) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Summary", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White,
                        modifier = Modifier.clickable { })
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Produk & Kurir Section
            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("1 Product", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                            Icon(painterResource(R.drawable.ic_flash), contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Instant Delivery", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                        Image(painterResource(R.drawable.img_courier_bike), contentDescription = null, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(12.dp))
                        Image(painterResource(R.drawable.img_summary_item), contentDescription = null, modifier = Modifier.fillMaxWidth())
                    }
                }
            }

            // 2. Rincian Harga Pembayaran
            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Order Summary", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        PriceRow(label = "Subtotal", value = "Rp 56.500")
                        PriceRow(label = "Discount", value = "Rp 6.600")
                        PriceRow(label = "Voucher", value = "Rp 0")
                        PriceRow(label = "Total Delivery Fee", value = "Rp 0")
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Shipping", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Rp 49.900", color = Color(0xFFD11F1F), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }

            // 3. Tombol Pilih Voucher (Gambar Utuh)
            item {
                Image(
                    painter = painterResource(id = R.drawable.img_summary_voucher_click),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().clickable { },
                    contentScale = ContentScale.FillWidth
                )
            }

            // 4. Alamat Pengiriman
            item {
                Image(
                    painter = painterResource(id = R.drawable.img_summary_address),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Composable
fun PriceRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, color = Color.Black, fontSize = 14.sp)
    }
}