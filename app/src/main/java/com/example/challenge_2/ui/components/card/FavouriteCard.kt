package com.example.challenge_2.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.ImagePlaceholder
import com.example.challenge_2.ui.theme.OnSurface
import com.example.challenge_2.ui.theme.SurfaceWhite

data class FavouriteItem(
    val id: String,
    val position: Int,
    val name: String,
    val priceLabel: String,
    @DrawableRes val imageRes: Int? = null,
)

@Composable
fun FavouriteCard(
    item: FavouriteItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PositionBadge(position = item.position)

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = item.name,
                    color = OnSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.priceLabel,
                    color = OnSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            FavouriteThumbnail(imageRes = item.imageRes)
        }
    }
}

@Composable
private fun PositionBadge(position: Int) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(BrandRust),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = position.toString(),
            color = SurfaceWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun FavouriteThumbnail(
    @DrawableRes imageRes: Int?,
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(width = 88.dp, height = 64.dp)
            .clip(shape)
            .background(ImagePlaceholder),
        contentAlignment = Alignment.Center,
    ) {
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 88.dp, height = 64.dp)
                    .clip(shape),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun FavouriteCardPreview() {
    FavouriteCard(
        item = FavouriteItem(
            id = "1",
            position = 1,
            name = "Leather boots",
            priceLabel = "27,5 $",
        ),
        modifier = Modifier.padding(16.dp),
    )
}
