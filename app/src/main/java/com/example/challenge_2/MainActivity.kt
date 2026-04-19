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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.components.bottomnav.AppBottomNav
import com.example.challenge_2.ui.components.bottomnav.BottomNavTab
import com.example.challenge_2.ui.components.menu.MenuItem
import com.example.challenge_2.ui.components.menu.MenuShape
import com.example.challenge_2.ui.components.menu.SideMenu
import com.example.challenge_2.ui.components.topbar.AppTopBar
import com.example.challenge_2.ui.screens.ProfileScreen
import com.example.challenge_2.ui.screens.ShopListScreen
import com.example.challenge_2.ui.theme.Challenge2Theme
import com.example.challenge_2.ui.theme.OnSurfaceMuted
import com.example.challenge_2.ui.theme.SurfacePeach
import kotlinx.coroutines.launch

enum class AppScreen(
    val title: String,
    val menuId: String?,
    val bottomTab: BottomNavTab?,
) {
    ShopList("Shop list", "shop_list", BottomNavTab.Product),
    Search("Search", null, BottomNavTab.Search),
    Favourites("Favourites", "favourites", null),
    Cart("Cart", null, BottomNavTab.Cart),
    Profile("Profile", "profile", BottomNavTab.Profile),
    Settings("Settings", "settings", null),
}

private val MenuItems = listOf(
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
    var currentScreen by remember { mutableStateOf(AppScreen.ShopList) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                items = MenuItems,
                selectedId = currentScreen.menuId ?: "",
                onItemClick = { item ->
                    AppScreen.entries.firstOrNull { it.menuId == item.id }?.let {
                        currentScreen = it
                    }
                    scope.launch { drawerState.close() }
                },
            )
        },
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = currentScreen.title,
                    onBackClick = if (currentScreen != AppScreen.ShopList) {
                        { currentScreen = AppScreen.ShopList }
                    } else null,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onProfileClick = { currentScreen = AppScreen.Profile },
                )
            },
            bottomBar = {
                AppBottomNav(
                    selected = currentScreen.bottomTab ?: BottomNavTab.Product,
                    onTabSelect = { tab ->
                        AppScreen.entries.firstOrNull { it.bottomTab == tab }?.let {
                            currentScreen = it
                        }
                    },
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
                when (currentScreen) {
                    AppScreen.ShopList -> ShopListScreen()
                    AppScreen.Profile -> ProfileScreen()
                    else -> ComingSoon(currentScreen.title)
                }
            }
        }
    }
}

@Composable
private fun ComingSoon(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$name — coming soon",
            color = OnSurfaceMuted,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppShellPreview() {
    Challenge2Theme {
        AppShell()
    }
}
