package com.example.challenge_2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.components.card.FavouriteCard
import com.example.challenge_2.ui.components.card.FavouriteItem
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.SurfacePeach
import com.example.challenge_2.ui.theme.SurfaceWhite

private val SampleFavourites = listOf(
    FavouriteItem(id = "fav_1", position = 1, name = "Leather boots", priceLabel = "27,5 $"),
    FavouriteItem(id = "fav_2", position = 2, name = "Leather boots", priceLabel = "27,5 $"),
    FavouriteItem(id = "fav_3", position = 3, name = "Leather boots", priceLabel = "27,5 $"),
)

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    items: List<FavouriteItem> = SampleFavourites,
    onItemClick: (FavouriteItem) -> Unit = {},
    onBuyClick: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(SurfacePeach),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        items(items = items, key = { it.id }) { item ->
            FavouriteCard(
                item = item,
                onClick = { onItemClick(item) },
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                BuyButton(onClick = onBuyClick)
            }
        }
    }
}

@Composable
private fun BuyButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandRust,
            contentColor = SurfaceWhite,
        ),
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = 12.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            modifier = Modifier.padding(end = 6.dp),
        )
        Text(
            text = "Buy",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun FavouritesScreenPreview() {
    FavouritesScreen()
}
