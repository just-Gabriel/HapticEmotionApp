package fr.maloof.hapticemotionapp.components

import android.app.TimePickerDialog
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
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    var showAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ⏰ Affichage du cadran
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.montre),
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

        // ✅ Bouton pour choisir l'heure via TimePicker
        CustomButton(
            text ="Choisir une heure",
            onClick = {
            TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    onHourChange(hour)
                    onMinuteChange(minute)
                },
                selectedHour,
                selectedMinute,
                true // is24HourView = true
            ).show()
        })

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Bouton Enregistrer
        CustomButton(
                text="Enregistrer",
                onClick = {
            showAlert = true
            if (!vibrationPlayed) {
                vibrationManager.vibrateByType(vibrationType)
                vibrationPlayed = true
                Log.d("SamsungClockPicker", "✅ Vibration lancée : type=$vibrationType")
            }
        })


        Spacer(modifier = Modifier.height(16.dp))



        // 🚨 Alerte sous forme de popup
        if (showAlert) {
            AlertDialog(
                onDismissRequest = {
                    showAlert = false
                },
                confirmButton = {
                    TextButton(onClick = { showAlert = false }) {
                        Text("OK")
                    }
                },
                icon = {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                },
                title = {
                    Text("Attention", color = MaterialTheme.colorScheme.error)
                },
                text = {
                    Text("Heure déjà occupée par un mode \"Ne pas déranger\"")
                }
            )
        }

    }
}
