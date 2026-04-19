package com.example.challenge_2.ui.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.ImagePlaceholder
import com.example.challenge_2.ui.theme.OnSurface
import com.example.challenge_2.ui.theme.OnSurfaceMuted
import com.example.challenge_2.ui.theme.SurfaceWhite

data class Product(
    val id: String,
    val name: String,
    val priceLabel: String,
    val description: String,
    @DrawableRes val imageRes: Int? = null,
)

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onAddToFavourite: () -> Unit = {},
    onBuy: () -> Unit = {},
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ProductImage(
                imageRes = product.imageRes,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
            ) {
                Text(
                    text = product.name,
                    color = OnSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.priceLabel,
                    color = OnSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = product.description,
                    color = OnSurfaceMuted,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedButton(
                        onClick = onAddToFavourite,
                        shape = RoundedCornerShape(24.dp),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, BrandRust),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandRust),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp),
                    ) {
                        Text(
                            text = "Add to favourite",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = onBuy,
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandRust,
                            contentColor = SurfaceWhite,
                        ),
                        contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp),
                    ) {
                        Text(
                            text = "Buy",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductImage(
    @DrawableRes imageRes: Int?,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .background(ImagePlaceholder)
            .border(width = 0.dp, color = Color.Transparent, shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun ProductCardPreview() {
    ProductCard(
        product = Product(
            id = "boots",
            name = "Leather boots",
            priceLabel = "27,5 $",
            description = "Great warm shoes from the artificial leather. You can buy this model only in our shop.",
        ),
        modifier = Modifier.padding(16.dp),
    )
}
