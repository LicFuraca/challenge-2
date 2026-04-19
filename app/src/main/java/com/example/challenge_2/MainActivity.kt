package com.example.challenge_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.challenge_2.ui.components.bottomnav.AppBottomNav
import com.example.challenge_2.ui.components.bottomnav.BottomNavTab
import com.example.challenge_2.ui.components.menu.MenuItem
import com.example.challenge_2.ui.components.menu.MenuShape
import com.example.challenge_2.ui.components.menu.SideMenu
import com.example.challenge_2.ui.components.topbar.AppTopBar
import com.example.challenge_2.ui.screens.ShopListScreen
import com.example.challenge_2.ui.theme.Challenge2Theme
import com.example.challenge_2.ui.theme.SurfacePeach
import kotlinx.coroutines.launch

private val Items = listOf(
    MenuItem(id = "shop_list", label = "Shop list", shape = MenuShape.Circle),
    MenuItem(id = "favourites", label = "Favourites", shape = MenuShape.Triangle, badge = 3),
    MenuItem(id = "profile", label = "Profile", shape = MenuShape.Square),
    MenuItem(id = "settings", label = "Settings", shape = MenuShape.Pentagon),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Challenge2Theme {
                AppShell()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppShell() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedMenuId by remember { mutableStateOf("shop_list") }
    var selectedTab by remember { mutableStateOf(BottomNavTab.Product) }

    val selectedMenuItem = Items.firstOrNull { it.id == selectedMenuId } ?: Items.first()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                items = Items,
                selectedId = selectedMenuId,
                onItemClick = { item ->
                    selectedMenuId = item.id
                    scope.launch { drawerState.close() }
                },
            )
        },
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = selectedMenuItem.label,
                    onMenuClick = { scope.launch { drawerState.open() } },
                )
            },
            bottomBar = {
                AppBottomNav(
                    selected = selectedTab,
                    onTabSelect = { selectedTab = it },
                )
            },
            containerColor = SurfacePeach,
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(SurfacePeach),
            ) {
                ShopListScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppShellPreview() {
    Challenge2Theme {
        AppShell()
    }
}
