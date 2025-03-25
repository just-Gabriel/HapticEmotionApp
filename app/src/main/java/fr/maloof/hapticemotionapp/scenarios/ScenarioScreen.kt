package fr.maloof.hapticemotionapp.scenarios

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import fr.maloof.hapticemotionapp.DataModel.EmotionalExperience
import fr.maloof.hapticemotionapp.RetrofitInstance
import fr.maloof.hapticemotionapp.VibrationManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ScenarioScreen(
    userId: Int,
    telephoneId: Int,
    vibrationId: Int,
    slider1: Float,
    slider2: Float,
    slider3: Float,
    scenarioName: String,
    mobile: Int,
    vibrationClickCount: Int,
    navController: NavController
) {
    val apiService = RetrofitInstance.api



    // ✅ Fonction POST
    fun sendExperienceToApi(evaluation: String) {
        val experience = EmotionalExperience(
            user = "/api/users/$userId",
            telephone = "/api/telephones/$telephoneId",
            vibrationId = vibrationId,
            slider1FS = slider1,
            slider2WC = slider2,
            slider3SN = slider3,
            scenario = scenarioName,
            evaluation = evaluation,
            nbDeFois = vibrationClickCount,
            mobile = mobile
        )

        Log.d("POST_JSON", Gson().toJson(experience))

        apiService.postEmotionalExperience(experience).enqueue(object : Callback<EmotionalExperience> {
            override fun onResponse(call: Call<EmotionalExperience>, response: Response<EmotionalExperience>) {
                if (response.isSuccessful) {
                    Log.d("ScenarioScreen", "✅ POST réussi : ${response.body()}")
                } else {
                    Log.e("ScenarioScreen", "❌ Erreur POST : ${response.code()} - ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<EmotionalExperience>, t: Throwable) {
                Log.e("ScenarioScreen", "🚨 Échec réseau : ${t.message}")
            }
        })
    }

    // ✅ UI complète avec footer fixé en bas
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔸 Partie centrale dynamique (haut de l’écran)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            when (scenarioName) {
                "Scenario_1_Failure_Warning_Selection" -> Scenario1UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_2_Failure_Warning_Navigation" -> Scenario2UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_3_Failure_Confirmation_Selection" -> Scenario3UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_4_Failure_Confirmation_Navigation" -> Scenario4UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_5_Success_Warning_Selection" -> Scenario5UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_6_Success_Warning_Navigation" -> Scenario6UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_7_Success_Confirmation_Selection" -> Scenario7UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                "Scenario_8_Success_Confirmation_Navigation" -> Scenario8UI(
                    vibrationManager = VibrationManager(context),
                    vibrationType = vibrationId,
                )
                else -> Text("Scénario inconnu", color = MaterialTheme.colorScheme.error)
            }
        }

        // 🔸 Footer toujours visible
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vibration adaptée au scénario ?", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = {
                    sendExperienceToApi("yes")

                    // 🔁 Dire à SliderScreen de jouer la prochaine vibration au retour
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("shouldPlayNext", true)

                    navController.popBackStack()
                    Log.d("Navigation", "Retour vers SliderScreen via popBackStack")
                    Log.d("ScenarioScreen", "POST vers API : YES")
                    Log.d("POST DEBUG", "POST : user=$userId, tel=$telephoneId, vibration=$vibrationId, scenario=$scenarioName")
                }) {
                    Text("Oui")
                }

                Button(onClick = {
                    sendExperienceToApi("no")

                    // 🔁 Dire à SliderScreen de jouer la prochaine vibration au retour
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("shouldPlayNext", true)

                    navController.popBackStack()
                    Log.d("Navigation", "Retour vers SliderScreen via popBackStack")
                    Log.d("ScenarioScreen", "POST vers API : NO")
                    Log.d("POST DEBUG", "POST : user=$userId, tel=$telephoneId, vibration=$vibrationId, scenario=$scenarioName")
                }) {
                    Text("Non")
                }

            }
        }
    }
}

