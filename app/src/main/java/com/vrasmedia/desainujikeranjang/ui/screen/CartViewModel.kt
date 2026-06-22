package com.vrasmedia.desainujikeranjang.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartCount = mutableIntStateOf(0)

        val cartCount: State<Int> = _cartCount

    fun addToCart() {
        _cartCount.intValue += 1
    }

    fun resetCart() {
        _cartCount.intValue = 0
    }
}