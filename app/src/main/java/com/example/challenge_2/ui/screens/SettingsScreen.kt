package com.example.challenge_2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.NavBarInactive
import com.example.challenge_2.ui.theme.OnSurface
import com.example.challenge_2.ui.theme.OnSurfaceMuted
import com.example.challenge_2.ui.theme.SurfacePeach
import com.example.challenge_2.ui.theme.SurfaceWhite

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onEditProfile: () -> Unit = {},
    onChangePassword: () -> Unit = {},
    onAboutUs: () -> Unit = {},
    onPrivacyPolicy: () -> Unit = {},
    onTermsAndConditions: () -> Unit = {},
) {
    var pushNotifications by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfacePeach)
            .verticalScroll(rememberScrollState()),
    ) {
        SectionHeader(title = "Account Settings")

        SettingsClickRow(label = "Edit profile", onClick = onEditProfile)
        RowDivider()
        SettingsClickRow(label = "Change password", onClick = onChangePassword)
        RowDivider()
        SettingsToggleRow(
            label = "Push notifications",
            checked = pushNotifications,
            onCheckedChange = { pushNotifications = it },
        )
        RowDivider()
        SettingsToggleRow(
            label = "Dark mode",
            checked = darkMode,
            onCheckedChange = { darkMode = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader(title = "More")

        SettingsClickRow(label = "About us", onClick = onAboutUs)
        RowDivider()
        SettingsClickRow(label = "Privacy policy", onClick = onPrivacyPolicy)
        RowDivider()
        SettingsClickRow(label = "Terms and conditions", onClick = onTermsAndConditions)

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        color = OnSurfaceMuted,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(
            start = 24.dp,
            end = 24.dp,
            top = 20.dp,
            bottom = 8.dp,
        ),
    )
}

@Composable
private fun SettingsClickRow(
    label: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .heightIn(min = 56.dp)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            color = OnSurface,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = OnSurfaceMuted,
            modifier = Modifier.size(22.dp),
        )
    }
}

@Composable
private fun SettingsToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            color = OnSurface,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = BrandRust,
                checkedThumbColor = SurfaceWhite,
                checkedBorderColor = BrandRust,
                uncheckedTrackColor = NavBarInactive,
                uncheckedThumbColor = SurfaceWhite,
                uncheckedBorderColor = NavBarInactive,
            ),
        )
    }
}

@Composable
private fun RowDivider() {
    HorizontalDivider(
        color = OnSurfaceMuted.copy(alpha = 0.18f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 24.dp),
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
