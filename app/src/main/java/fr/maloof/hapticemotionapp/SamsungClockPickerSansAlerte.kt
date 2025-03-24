package fr.maloof.hapticemotionapp.components

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.maloof.hapticemotionapp.R
import fr.maloof.hapticemotionapp.VibrationManager

@Composable
fun SamsungClockPickerSansAlerte(
    selectedHour: Int,
    selectedMinute: Int,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    val context = LocalContext.current
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

        // ✅ Bouton pour ouvrir le TimePicker
        Button(onClick = {
            TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    onHourChange(hour)
                    onMinuteChange(minute)
                },
                selectedHour,
                selectedMinute,
                true
            ).show()
        }) {
            Text("Choisir une heure")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Bouton Enregistrer sans alerte
        Button(onClick = {
            if (!vibrationPlayed) {
                vibrationManager.vibrateByType(vibrationType)
                vibrationPlayed = true
                Log.d("SamsungClockPickerSansAlerte", "✅ Vibration lancée : type=$vibrationType")
            }
        }) {
            Text("Enregistrer")
        }
    }
}
