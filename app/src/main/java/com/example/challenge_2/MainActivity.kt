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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.challenge_2.ui.components.menu.MenuItem
import com.example.challenge_2.ui.components.menu.MenuShape
import com.example.challenge_2.ui.components.menu.SideMenu
import com.example.challenge_2.ui.components.topbar.AppTopBar
import com.example.challenge_2.ui.theme.Challenge2Theme
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
    var selectedId by remember { mutableStateOf("shop_list") }

    val selected = Items.firstOrNull { it.id == selectedId } ?: Items.first()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                items = Items,
                selectedId = selectedId,
                onItemClick = { item ->
                    selectedId = item.id
                    scope.launch { drawerState.close() }
                },
            )
        },
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = selected.label,
                    onMenuClick = { scope.launch { drawerState.open() } },
                )
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFFBEFE9)),
            )
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
