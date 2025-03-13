package fr.maloof.hapticemotionapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext





@Composable
fun SliderScreen(
    onValidate: () -> Unit = {},
    onValidateTest: () -> Unit = {},
    onVibrationTest: () -> Unit = {}
) {
    val context = LocalContext.current
    val vibrationManager = remember { VibrationManager(context) }



    var slider1 by remember { mutableStateOf(0.5f) } // Failure–Success
    var slider2 by remember { mutableStateOf(0.5f) } // Warning–Confirmation
    var slider3 by remember { mutableStateOf(0.5f) } // Selection–Navigation

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                vibrationManager.playNextVibration()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vibration")
        }





        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Slider 1: Failure ↔ Success", fontSize = 16.sp)
            Slider(
                value = slider1,
                onValueChange = { slider1 = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Slider 2: Warning ↔ Confirmation", fontSize = 16.sp)
            Slider(
                value = slider2,
                onValueChange = { slider2 = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Slider 3: Selection ↔ Navigation", fontSize = 16.sp)
            Slider(
                value = slider3,
                onValueChange = { slider3 = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                val scenario = determineScenario(slider1, slider2, slider3)
                Log.d("SliderScreen", "Slider1: $slider1, Slider2: $slider2, Slider3: $slider3")
                Log.d("SliderScreen", "🎯 Scénario déterminé : $scenario")
                onValidate() // Tu pourras plus tard transmettre ce scénario en paramètre si besoin
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Valider")
        }
    }
}

// Fonction pour déterminer le scénario à partir des 3 sliders
fun determineScenario(slider1: Float, slider2: Float, slider3: Float): String {
    val isSuccess = slider1 >= 0.5f
    val isConfirmation = slider2 >= 0.5f
    val isNavigation = slider3 >= 0.5f

    return when {
        !isSuccess && !isConfirmation && !isNavigation -> "Scenario_1_Failure_Warning_Selection"
        !isSuccess && !isConfirmation && isNavigation -> "Scenario_2_Failure_Warning_Navigation"
        !isSuccess && isConfirmation && !isNavigation -> "Scenario_3_Failure_Confirmation_Selection"
        !isSuccess && isConfirmation && isNavigation -> "Scenario_4_Failure_Confirmation_Navigation"
        isSuccess && !isConfirmation && !isNavigation -> "Scenario_5_Success_Warning_Selection"
        isSuccess && !isConfirmation && isNavigation -> "Scenario_6_Success_Warning_Navigation"
        isSuccess && isConfirmation && !isNavigation -> "Scenario_7_Success_Confirmation_Selection"
        isSuccess && isConfirmation && isNavigation -> "Scenario_8_Success_Confirmation_Navigation"
        else -> "Scenario_Inconnu"
    }
}
