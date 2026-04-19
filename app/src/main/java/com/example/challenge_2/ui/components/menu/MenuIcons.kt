package com.example.challenge_2.ui.components.menu

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

enum class MenuShape { Circle, Triangle, Square, Pentagon }

@Composable
fun MenuShapeIcon(
    shape: MenuShape,
    color: Color = MenuIcon,
    modifier: Modifier = Modifier.size(18.dp),
) {
    Canvas(modifier = modifier) {
        when (shape) {
            MenuShape.Circle -> {
                drawCircle(color = color, radius = size.minDimension / 2f)
            }

            MenuShape.Triangle -> {
                val path = Path().apply {
                    moveTo(size.width / 2f, 0f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(path = path, color = color)
            }

            MenuShape.Square -> {
                drawRect(color = color, topLeft = Offset.Zero, size = size)
            }

            MenuShape.Pentagon -> {
                val cx = size.width / 2f
                val cy = size.height / 2f
                val radius = size.minDimension / 2f
                val path = Path().apply {
                    for (i in 0 until 5) {
                        val angle = (-PI / 2 + i * 2 * PI / 5).toFloat()
                        val x = cx + radius * cos(angle)
                        val y = cy + radius * sin(angle)
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                }
                drawPath(path = path, color = color)
            }
        }
    }
}
