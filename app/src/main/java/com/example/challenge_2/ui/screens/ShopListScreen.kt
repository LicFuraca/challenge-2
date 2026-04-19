package com.example.challenge_2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.challenge_2.ui.components.card.Product
import com.example.challenge_2.ui.components.card.ProductCard
import com.example.challenge_2.ui.theme.SurfacePeach

private val SampleProducts = listOf(
    Product(
        id = "leather_boots",
        name = "Leather boots",
        priceLabel = "27,5 $",
        description = "Great warm shoes from the artificial leather. You can buy this model only in our shop.",
    ),
    Product(
        id = "running_shoes",
        name = "Running shoes",
        priceLabel = "34,0 $",
        description = "Lightweight running shoes designed for everyday training. Breathable mesh and cushioned sole.",
    ),
    Product(
        id = "classic_sneakers",
        name = "Classic sneakers",
        priceLabel = "22,9 $",
        description = "Timeless canvas sneakers. A clean look that matches anything in your wardrobe.",
    ),
    Product(
        id = "hiking_boots",
        name = "Hiking boots",
        priceLabel = "49,5 $",
        description = "Waterproof hiking boots with a grippy rubber outsole. Perfect for long trails.",
    ),
)

@Composable
fun ShopListScreen(
    modifier: Modifier = Modifier,
    products: List<Product> = SampleProducts,
    onAddToFavourite: (Product) -> Unit = {},
    onBuy: (Product) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SurfacePeach),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
    ) {
        items(items = products, key = { it.id }) { product ->
            ProductCard(
                product = product,
                onAddToFavourite = { onAddToFavourite(product) },
                onBuy = { onBuy(product) },
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun ShopListScreenPreview() {
    ShopListScreen()
}
