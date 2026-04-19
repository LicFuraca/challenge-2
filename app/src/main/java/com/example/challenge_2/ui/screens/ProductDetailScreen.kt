package com.example.challenge_2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge_2.ui.components.card.Product
import com.example.challenge_2.ui.theme.BrandRust
import com.example.challenge_2.ui.theme.OnSurface
import com.example.challenge_2.ui.theme.OnSurfaceMuted
import com.example.challenge_2.ui.theme.SurfacePeach
import com.example.challenge_2.ui.theme.SurfaceWhite

private val SizeOptions = listOf("38", "39", "40", "41", "42", "43", "44")

@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onBuy: (size: String, count: String) -> Unit = { _, _ -> },
) {
    var selectedSize by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfacePeach)
            .verticalScroll(rememberScrollState())
            .padding(PaddingValues(horizontal = 28.dp, vertical = 24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle(text = "Select size")
        Spacer(modifier = Modifier.height(8.dp))
        SizeDropdown(
            value = selectedSize,
            onValueChange = { selectedSize = it },
            options = SizeOptions,
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle(text = "Count of product")
        Spacer(modifier = Modifier.height(8.dp))
        CountField(
            value = count,
            onValueChange = { input -> count = input.filter(Char::isDigit) },
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = onBack,
                shape = RoundedCornerShape(24.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, BrandRust),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = BrandRust,
                    containerColor = SurfaceWhite,
                ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "Back",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            Button(
                onClick = { onBuy(selectedSize, count) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandRust,
                    contentColor = SurfaceWhite,
                ),
                contentPadding = PaddingValues(horizontal = 36.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "Buy",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        color = OnSurface,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SizeDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Size") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = fieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun CountField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Count") },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = fieldColors(),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = SurfaceWhite.copy(alpha = 0f),
    unfocusedContainerColor = SurfaceWhite.copy(alpha = 0f),
    focusedBorderColor = OnSurfaceMuted,
    unfocusedBorderColor = OnSurfaceMuted.copy(alpha = 0.5f),
    focusedLabelColor = OnSurface,
    unfocusedLabelColor = OnSurfaceMuted,
    cursorColor = BrandRust,
)

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        product = Product(
            id = "leather_boots",
            name = "Leather boots",
            priceLabel = "27,5 $",
            description = "",
        ),
    )
}
