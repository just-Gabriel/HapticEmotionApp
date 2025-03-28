package fr.maloof.hapticemotionapp.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CustomButton(
    text: String,
    icon: Painter? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    backgroundColor: Color = Color(0xFF029AAF),
    successColor: Color = Color(0xFF4CAF50),
    isSuccess: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }


) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.05f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "scaleAnim"
    )

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(150)
            isPressed = false
        }
    }

    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSuccess) successColor else backgroundColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        ),
        border = BorderStroke(2.dp, Color.White),
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .height(56.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            if (icon != null) {
                Icon(painter = icon, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}
