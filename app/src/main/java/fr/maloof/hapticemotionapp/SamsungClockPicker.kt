package fr.maloof.hapticemotionapp.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.maloof.hapticemotionapp.R
import fr.maloof.hapticemotionapp.VibrationManager

@Composable
fun SamsungClockPicker(
    selectedHour: Int,
    selectedMinute: Int,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var showAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ⏰ Horloge stylisée
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.montre), // mets ton image dans drawable
                contentDescription = "Cadran",
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "${selectedHour.toString().padStart(2, '0')} : ${selectedMinute.toString().padStart(2, '0')}",
                fontSize = 42.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Slider Heure
        Text("Heure", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Slider(
            value = selectedHour.toFloat(),
            onValueChange = { onHourChange(it.toInt()) },
            valueRange = 0f..23f,
            steps = 22,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Slider Minute
        Text("Minute", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
        Slider(
            value = selectedMinute.toFloat(),
            onValueChange = { onMinuteChange(it.toInt()) },
            valueRange = 0f..59f,
            steps = 58,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Bouton enregistrer
        Button(onClick = {
            showAlert = true
            if (!vibrationPlayed) {
                vibrationManager.vibrateByType(vibrationType)
                vibrationPlayed = true
                Log.d("SamsungClockPicker", "✅ Vibration lancée : type=$vibrationType")
            }
        }) {
            Text("Enregistrer")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🚨 Alerte affichée après clic
        if (showAlert) {
            Card(
                modifier = Modifier.wrapContentSize(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alerte",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "⚠ Heure déjà occupée par le mode \"Ne pas déranger\"",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
