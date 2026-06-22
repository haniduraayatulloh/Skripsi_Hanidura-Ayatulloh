package com.vrasmedia.desainujikeranjang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrasmedia.desainujikeranjang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(onNextClick: () -> Unit, onBackClick: () -> Unit) {
    var isPilihSemua by remember { mutableStateOf(true) }
    var isPengirimanInstan by remember { mutableStateOf(true) }
    var isProdukChecked by remember { mutableStateOf(true) }
    var itemQuantity by remember { mutableStateOf(1) }

    val hargaSatuan = 4200
    val totalHarga = if (isProdukChecked) hargaSatuan * itemQuantity else 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.clickable { /* Interaksi: Kembali */ }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD11F1F))
            )
        },
        bottomBar = {
            // Bottom Bar untuk Kalkulasi Harga dan Tombol Selanjutnya
            CartBottomBar(totalHarga = totalHarga)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5)) // Background abu-abu tipis antar section
        ) {

            // 1. Section Alamat Pengiriman
            item {
                AddressSection()
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 2. Section Banner Tebus Murah
            item {
                TebusMurahBanner()
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 3. Section Pilih Semua
            item {
                PilihSemuaSection(
                    isChecked = isPilihSemua,
                    onCheckedChange = {
                        isPilihSemua = it
                        isProdukChecked = it
                        isPengirimanInstan = it
                    }
                )
            }

            // 4. Section Grup Pengiriman Instan & List Produk
            item {
                Column(modifier = Modifier.background(Color.White)) {
                    // Header Pengiriman Instan
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isPengirimanInstan,
                            onCheckedChange = { isPengirimanInstan = it }
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_instan), // Icon motor/jam instan
                            contentDescription = null,
                            tint = Color(0xFFE52323),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Pengiriman Instan", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }

                    Divider(thickness = 1.dp, color = Color(0xFFE0E0E0))

                    // Item Produk (Tehbotol Sosro)
                    ProductItemRow(
                        isChecked = isProdukChecked,
                        quantity = itemQuantity,
                        onCheckedChange = { isProdukChecked = it },
                        onQuantityDecrease = { if (itemQuantity > 1) itemQuantity-- },
                        onQuantityIncrease = { itemQuantity++ }
                    )
                }
            }
        }
    }
}

// ==========================================
// COMPONENT 1: ADDRESS SECTION
// ==========================================
@Composable
fun AddressSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_courier_bike), // Ekspor kurir merah kecil
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Kost - Hanidura Ayatulloh",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFD11F1F), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("Utama", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(
                text = "Jalan Simpang Semanggi Timur No. 1....",
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { /* Interaksi: Ganti Alamat */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.border(1.dp, Color(0xFF00529C), RoundedCornerShape(8.dp)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text("Ganti", color = Color(0xFF00529C), fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// ==========================================
// COMPONENT 2: TEBUS MURAH BANNER (Wadah Gambar Kosong + Tombol Manual)
// ==========================================
@Composable
fun TebusMurahBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Ambil background kuning beserta list produk kecil di kiri bawah sebagai 1 gambar utuh
        Image(
            painter = painterResource(id = R.drawable.bg_tebus_murah_empty),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        // Tumpuk Tombol "Lihat" secara manual di atas gambar agar presisi dan interaktif
        Button(
            onClick = { /* Interaksi: Buka Bottom Sheet Tebus Murah */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .height(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
        ) {
            Text("Lihat", color = Color(0xFF00529C), fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

// ==========================================
// COMPONENT 3: PILIH SEMUA SECTION
// ==========================================
@Composable
fun PilihSemuaSection(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Pilih Semua", fontSize = 14.sp, color = Color.Black)
    }
}

// ==========================================
// COMPONENT 4: PRODUCT ITEM ROW (Dinamis + Counter)
// ==========================================
@Composable
fun ProductItemRow(
    isChecked: Boolean,
    quantity: Int,
    onCheckedChange: (Boolean) -> Unit,
    onQuantityDecrease: () -> Unit,
    onQuantityIncrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)

        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = R.drawable.img_tehbotol), // Gambar produk Tehbotol Sosro
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                "Tehbotol Sosro Minuman Teh Original 350 ml",
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Rp 4.200", color = Color(0xFFD11F1F), fontWeight = FontWeight.Bold, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // Komponen Counter Plus Minus (Sisi Kanan Bawah Produk)
            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onQuantityDecrease, modifier = Modifier.size(28.dp)) {
                    Text("—", color = Color(0xFF00529C), fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(text = quantity.toString(), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = onQuantityIncrease, modifier = Modifier.size(28.dp)) {
                    Text("+", color = Color(0xFF00529C), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ==========================================
// COMPONENT 5: BOTTOM BAR (Total & Selanjutnya)
// ==========================================
@Composable
fun CartBottomBar(totalHarga: Int) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Rp $totalHarga",
                    color = Color(0xFF00529C),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = { /* Interaksi Front-end: Menuju halaman Checkout */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00529C)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(160.dp)
                    .height(45.dp)
            ) {
                Text("Selanjutnya", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}