package com.example.challenge_2.ui.components.bottomnav

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.NavBarBackground
import com.example.challenge_2.ui.theme.NavBarInactive
import com.example.challenge_2.ui.theme.SurfaceWhite

enum class BottomNavTab(val label: String) {
    Product("Product"),
    Search("Search"),
    Cart("Cart"),
    Profile("Profile"),
}

@Composable
fun AppBottomNav(
    selected: BottomNavTab,
    onTabSelect: (BottomNavTab) -> Unit,
    modifier: Modifier = Modifier,
    onCenterClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(NavBarBackground)
                .navigationBarsPadding()
                .height(72.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NavItem(
                tab = BottomNavTab.Product,
                icon = Icons.Filled.Home,
                selected = selected == BottomNavTab.Product,
                onClick = { onTabSelect(BottomNavTab.Product) },
                modifier = Modifier.weight(1f),
            )
            NavItem(
                tab = BottomNavTab.Search,
                icon = Icons.Filled.Search,
                selected = selected == BottomNavTab.Search,
                onClick = { onTabSelect(BottomNavTab.Search) },
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.weight(1f))

            NavItem(
                tab = BottomNavTab.Cart,
                icon = Icons.Filled.ShoppingCart,
                selected = selected == BottomNavTab.Cart,
                onClick = { onTabSelect(BottomNavTab.Cart) },
                modifier = Modifier.weight(1f),
            )
            NavItem(
                tab = BottomNavTab.Profile,
                icon = Icons.Filled.Person,
                selected = selected == BottomNavTab.Profile,
                onClick = { onTabSelect(BottomNavTab.Profile) },
                modifier = Modifier.weight(1f),
            )
        }

        CenterButton(
            onClick = onCenterClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 8.dp)
                .navigationBarsPadding(),
        )
    }
}

@Composable
private fun NavItem(
    tab: BottomNavTab,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tint = if (selected) BrandRust else NavBarInactive
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = tab.label,
            tint = tint,
            modifier = Modifier.size(22.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tab.label,
            color = tint,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
        )
    }
}

@Composable
private fun CenterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .shadow(elevation = 8.dp, shape = CircleShape)
            .clip(CircleShape)
            .background(BrandRust)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        GridIcon(color = SurfaceWhite)
    }
}

@Composable
private fun GridIcon(color: Color) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val cell = size.minDimension / 2.6f
        val gap = (size.minDimension - 2 * cell) / 2f
        val positions = listOf(
            Offset(0f, 0f),
            Offset(cell + gap, 0f),
            Offset(0f, cell + gap),
            Offset(cell + gap, cell + gap),
        )
        positions.forEach { origin ->
            drawRect(
                color = color,
                topLeft = origin,
                size = androidx.compose.ui.geometry.Size(cell, cell),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun AppBottomNavPreview() {
    AppBottomNav(
        selected = BottomNavTab.Product,
        onTabSelect = {},
    )
}
