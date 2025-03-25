package fr.maloof.hapticemotionapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SliderScreen(
    navController: NavController,
    //onValidateTest: () -> Unit = {},
    //onVibrationTest: () -> Unit = {},
    userId: Int,
    telephoneId: Int,
    vibrationManager: VibrationManager
) {
    val context = LocalContext.current
    //val vibrationManager = remember { VibrationManager(context) }

    var slider1 by remember { mutableStateOf(0.5f) }
    var slider2 by remember { mutableStateOf(0.5f) }
    var slider3 by remember { mutableStateOf(0.5f) }
    val mobile = 0
    var vibrationClickCount by remember { mutableStateOf(0) }
    val maxClicks = 10


    var selectedVibrationType by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        val fullList = vibrationManager.getCurrentVibrationList()
        fullList.forEachIndexed { index, id ->
            Log.d("VIB_LIST", "[$index] vibrationId = $id")
        }
    }



    //GOOD
    LaunchedEffect(Unit) {
        vibrationManager.playNextVibration()
        selectedVibrationType = vibrationManager.currentVibrationId
    }


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
                if (vibrationClickCount < maxClicks) {
                    vibrationManager.replayCurrentVibration()
                    vibrationClickCount++
                    Log.d("SliderScreen", "📢 Vibration rejouée : type=$selectedVibrationType, nb=$vibrationClickCount")
                }
            },
            enabled = vibrationClickCount < maxClicks,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vibration (${vibrationClickCount}/$maxClicks)")
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

                Log.d("SliderScreen", "Sliders ➜ 1:$slider1 | 2:$slider2 | 3:$slider3")
                Log.d("SliderScreen", "🎯 Scenario déterminé : $scenario")
                Log.d("SliderScreen", "📦 VibrationId à transmettre : $selectedVibrationType")
                val route = "scenario/$userId/$telephoneId/$selectedVibrationType/$slider1/$slider2/$slider3/$scenario/$mobile/$vibrationClickCount"
                Log.d("SliderScreen", "➡ Route de navigation : $route")
                navController.navigate(route)


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Valider")
        }

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

/*fun getVibrationNameFromType(vibrationType: Int): String {
    return when (vibrationType) {
        HapticFeedbackConstantsCompat.KEYBOARD_RELEASE -> "Keyboard Release"
        HapticFeedbackConstantsCompat.VIRTUAL_KEY_RELEASE -> "Virtual Key Release"
        HapticFeedbackConstantsCompat.CLOCK_TICK -> "Clock Tick"
        HapticFeedbackConstantsCompat.TEXT_HANDLE_MOVE -> "Text Handle Move"
        HapticFeedbackConstantsCompat.GESTURE_END -> "Gesture End"
        HapticFeedbackConstantsCompat.VIRTUAL_KEY -> "Virtual Key"
        HapticFeedbackConstantsCompat.KEYBOARD_PRESS -> "Keyboard Press"
        HapticFeedbackConstantsCompat.DRAG_START -> "Drag Start"
        HapticFeedbackConstantsCompat.CONTEXT_CLICK -> "Context Click"
        HapticFeedbackConstantsCompat.GESTURE_START -> "Gesture Start"
        HapticFeedbackConstantsCompat.CONFIRM -> "Confirm"
        HapticFeedbackConstantsCompat.LONG_PRESS -> "Long Press"
        HapticFeedbackConstantsCompat.REJECT -> "Reject"
        HapticFeedbackConstantsCompat.TOGGLE_ON -> "Toggle On"
        HapticFeedbackConstantsCompat.TOGGLE_OFF -> "Toggle Off"
        HapticFeedbackConstantsCompat.GESTURE_THRESHOLD_ACTIVATE -> "Gesture Threshold Activate"
        HapticFeedbackConstantsCompat.GESTURE_THRESHOLD_DEACTIVATE -> "Gesture Threshold Deactivate"
        HapticFeedbackConstantsCompat.KEYBOARD_TAP -> "Keyboard Tap"
        HapticFeedbackConstantsCompat.SEGMENT_TICK -> "Segment Tick"
        HapticFeedbackConstantsCompat.SEGMENT_FREQUENT_TICK -> "Segment Frequent Tick"
        else -> "Vibration inconnue"
    }
}*/
