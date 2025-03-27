package fr.maloof.hapticemotionapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.maloof.hapticemotionapp.components.CustomButton



@Composable
fun SliderScreen(
    navController: NavController,
    userId: Int,
    telephoneId: Int,
    vibrationManager: VibrationManager
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    val testCounter by remember {
        derivedStateOf { savedStateHandle?.get<Int>("testCounter") ?: 1 }
    }

    val shouldPlayNext by remember {
        derivedStateOf { savedStateHandle?.get<Boolean>("shouldPlayNext") ?: true }
    }

    var slider1 by remember { mutableStateOf(0.5f) }
    var slider2 by remember { mutableStateOf(0.5f) }
    var slider3 by remember { mutableStateOf(0.5f) }
    val mobile = 0
    var vibrationClickCount by remember { mutableStateOf(0) }
    val maxClicks = 10
    var selectedVibrationType by remember { mutableStateOf<Int?>(null) }

    // ✅ Lancer la vibration uniquement si shouldPlayNext est true
    LaunchedEffect(shouldPlayNext) {
        if (shouldPlayNext) {
            vibrationManager.playNextVibration()
            selectedVibrationType = vibrationManager.currentVibrationId
            savedStateHandle?.set("shouldPlayNext", false)
        }
    }

    // ✅ Pour le log si besoin
    Log.d("SliderScreen", "Test n°$testCounter / 60")


    LaunchedEffect(Unit) {
        val fullList = vibrationManager.getCurrentVibrationList()
        fullList.forEachIndexed { index, id ->
            Log.d("VIB_LIST", "[$index] vibrationId = $id")
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = "Vibration (${vibrationClickCount}/$maxClicks)",
            onClick = {
                if (vibrationClickCount < maxClicks) {
                    vibrationManager.replayCurrentVibration()
                    vibrationClickCount++
                    Log.d("SliderScreen", "📢 Vibration rejouée : type=$selectedVibrationType, nb=$vibrationClickCount")
                }
            },
            isEnabled = vibrationClickCount < maxClicks, // ✅ bien écrit ici
            modifier = Modifier.fillMaxWidth()
        )


        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Slider 1: Failure ↔ Success", fontSize = 16.sp)
            Slider(
                value = slider1,
                onValueChange = { slider1 = it },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF029AAF),
                    activeTrackColor = Color(0xFF029AAF)
                )
            )


            Text("Slider 2: Warning ↔ Confirmation", fontSize = 16.sp)
            Slider(
                value = slider2,
                onValueChange = { slider2 = it },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF029AAF),
                    activeTrackColor = Color(0xFF029AAF)
                )
            )


            Text("Slider 3: Selection ↔ Navigation", fontSize = 16.sp)
            Slider(
                value = slider3,
                onValueChange = { slider3 = it },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF029AAF),
                    activeTrackColor = Color(0xFF029AAF)
                )
            )
        }

        CustomButton(
            text = "Valider",
            onClick = {
                val scenario = determineScenario(slider1, slider2, slider3)
                val route = "scenario/$userId/$telephoneId/$selectedVibrationType/$slider1/$slider2/$slider3/$scenario/$mobile/$vibrationClickCount"
                navController.navigate(route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
    }
}

// ✅ En dehors du composable
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


