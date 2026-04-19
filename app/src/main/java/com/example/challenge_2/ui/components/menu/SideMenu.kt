package com.example.challenge_2.ui.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MenuItem(
    val id: String,
    val label: String,
    val shape: MenuShape,
    val badge: Int? = null,
)

private val DefaultMenuItems = listOf(
    MenuItem(id = "shop_list", label = "Shop list", shape = MenuShape.Circle),
    MenuItem(id = "favourites", label = "Favourites", shape = MenuShape.Triangle, badge = 3),
    MenuItem(id = "profile", label = "Profile", shape = MenuShape.Square),
    MenuItem(id = "settings", label = "Settings", shape = MenuShape.Pentagon),
)

@Composable
fun SideMenu(
    modifier: Modifier = Modifier,
    title: String = "Title",
    sectionHeader: String = "Section Header",
    items: List<MenuItem> = DefaultMenuItems,
    selectedId: String = "shop_list",
    onItemClick: (MenuItem) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(260.dp)
            .background(MenuBackground)
            .padding(horizontal = 16.dp, vertical = 20.dp),
    ) {
        Text(
            text = title,
            color = MenuPrimaryText,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 16.dp),
        )

        HorizontalDivider(color = MenuDivider, thickness = 1.dp)

        Text(
            text = sectionHeader,
            color = MenuPrimaryText,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 12.dp, top = 14.dp, bottom = 14.dp),
        )

        HorizontalDivider(color = MenuDivider, thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        items.forEach { item ->
            MenuItemRow(
                item = item,
                selected = item.id == selectedId,
                onClick = { onItemClick(item) },
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = MenuDivider, thickness = 1.dp)
    }
}

@Composable
private fun MenuItemRow(
    item: MenuItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(24.dp)
    val background = if (selected) MenuSelectedBackground else MenuBackground

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(background)
            .clickable(onClick = onClick)
            .padding(PaddingValues(horizontal = 14.dp, vertical = 12.dp)),
    ) {
        Box(
            modifier = Modifier.size(18.dp),
            contentAlignment = Alignment.Center,
        ) {
            MenuShapeIcon(shape = item.shape)
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = item.label,
            color = MenuPrimaryText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
        )

        if (item.badge != null) {
            Text(
                text = item.badge.toString(),
                color = MenuBadgeText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
private fun SideMenuPreview() {
    SideMenu()
}
