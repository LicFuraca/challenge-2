package com.example.challenge_2.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val TopBarBackground = Color(0xFFFFFFFF)
private val TopBarTitle = Color(0xFF2B2B2B)
private val TopBarIconTint = Color(0xFF1F1F1F)
private val TopBarDivider = Color(0xFFEAEAEA)

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(TopBarBackground),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(64.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TopBarIconTint,
                        modifier = Modifier.size(24.dp),
                    )
                }
            } else {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Open menu",
                        tint = TopBarIconTint,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }

            Text(
                text = title,
                color = TopBarTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )

            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    tint = TopBarIconTint,
                    modifier = Modifier.size(34.dp),
                )
            }
        }

        HorizontalDivider(color = TopBarDivider, thickness = 1.dp)
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun AppTopBarMenuPreview() {
    AppTopBar(title = "Shop list")
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun AppTopBarBackPreview() {
    AppTopBar(title = "Profile", onBackClick = {})
}
