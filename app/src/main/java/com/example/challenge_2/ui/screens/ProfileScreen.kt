package com.example.challenge_2.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.ImagePlaceholder
import com.example.challenge_2.ui.theme.OnSurface
import com.example.challenge_2.ui.theme.OnSurfaceMuted
import com.example.challenge_2.ui.theme.SurfacePeach
import com.example.challenge_2.ui.theme.SurfaceWhite

data class ProfileData(
    val name: String = "Martin",
    val role: String = "UI UX DESIGN",
    @DrawableRes val avatarRes: Int? = null,
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profile: ProfileData = ProfileData(),
    onEditAvatar: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfacePeach)
            .verticalScroll(rememberScrollState())
            .padding(PaddingValues(horizontal = 24.dp, vertical = 24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Avatar(
            avatarRes = profile.avatarRes,
            onEditClick = onEditAvatar,
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = profile.name,
            color = OnSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = profile.role,
            color = OnSurfaceMuted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(28.dp))

        ProfileField(
            value = email,
            onValueChange = { email = it },
            label = "E-Mail Address",
            placeholder = "xxx@gmail.com",
            trailingIcon = Icons.Filled.Email,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone Number",
            placeholder = "+5493123135",
            trailingIcon = Icons.Filled.Phone,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileField(
            value = website,
            onValueChange = { website = it },
            label = "Web Site",
            placeholder = "www.google.com",
            trailingIcon = Icons.Filled.Settings,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "xxxxxxxxxxxxxx",
            trailingIcon = Icons.Filled.Lock,
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun Avatar(
    @DrawableRes avatarRes: Int?,
    onEditClick: () -> Unit,
) {
    Box(
        modifier = Modifier.size(140.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(ImagePlaceholder),
            contentAlignment = Alignment.Center,
        ) {
            if (avatarRes != null) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(36.dp)
                .clip(CircleShape)
                .background(BrandRust),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit avatar",
                tint = SurfaceWhite,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}

@Composable
private fun ProfileField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    trailingIcon: ImageVector,
    isPassword: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder, color = OnSurfaceMuted) },
        trailingIcon = {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = OnSurfaceMuted,
            )
        },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = SurfaceWhite.copy(alpha = 0.0f),
            unfocusedContainerColor = SurfaceWhite.copy(alpha = 0.0f),
            focusedBorderColor = OnSurfaceMuted,
            unfocusedBorderColor = OnSurfaceMuted.copy(alpha = 0.5f),
            focusedLabelColor = OnSurface,
            unfocusedLabelColor = OnSurfaceMuted,
            cursorColor = BrandRust,
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
