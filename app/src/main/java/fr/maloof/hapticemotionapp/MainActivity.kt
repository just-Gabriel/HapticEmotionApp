package fr.maloof.hapticemotionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.maloof.hapticemotionapp.ui.theme.HapticEmotionAppTheme
import fr.maloof.hapticemotionapp.scenarios.ScenarioScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HapticEmotionAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "accueil") {

                    composable("accueil") {
                        AccueilScreen(
                            onStartClick = {
                                navController.navigate("formulaire")
                            }
                        )
                    }

                    composable("formulaire") {
                        FormulaireScreen { userId, phoneId ->
                            navController.navigate("sliders/$userId/$phoneId")
                        }
                    }

                    composable(
                        "sliders/{userId}/{telephoneId}",
                        arguments = listOf(
                            navArgument("userId") { type = NavType.IntType },
                            navArgument("telephoneId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                        val telephoneId = backStackEntry.arguments?.getInt("telephoneId") ?: 0
                        SliderScreen(
                            navController = navController,
                            userId = userId,
                            telephoneId = telephoneId
                        )
                    }

                    composable(
                        "scenario/{userId}/{telephoneId}/{vibrationId}/{slider1}/{slider2}/{slider3}/{scenarioName}/{mobile}/{vibrationClickCount}",
                        arguments = listOf(
                            navArgument("userId") { type = NavType.IntType },
                            navArgument("telephoneId") { type = NavType.IntType },
                            navArgument("vibrationId") { type = NavType.IntType },
                            navArgument("slider1") { type = NavType.FloatType },
                            navArgument("slider2") { type = NavType.FloatType },
                            navArgument("slider3") { type = NavType.FloatType },
                            navArgument("scenarioName") { type = NavType.StringType },
                            navArgument("mobile") { type = NavType.IntType },
                            navArgument("vibrationClickCount") { type = NavType.IntType }


                        )
                    ) { backStackEntry ->
                        val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                        val telephoneId = backStackEntry.arguments?.getInt("telephoneId") ?: 0
                        val vibrationId = backStackEntry.arguments?.getInt("vibrationId") ?: 0
                        val slider1 = backStackEntry.arguments?.getFloat("slider1") ?: 0f
                        val slider2 = backStackEntry.arguments?.getFloat("slider2") ?: 0f
                        val slider3 = backStackEntry.arguments?.getFloat("slider3") ?: 0f
                        val scenarioName = backStackEntry.arguments?.getString("scenarioName") ?: ""
                        val mobile = backStackEntry.arguments?.getInt("mobile") ?: 0
                        val vibrationClickCount = backStackEntry.arguments?.getInt("vibrationClickCount") ?: 0

                        ScenarioScreen(
                            userId = userId,
                            telephoneId = telephoneId,
                            vibrationId = vibrationId,
                            slider1 = slider1,
                            slider2 = slider2,
                            slider3 = slider3,
                            scenarioName = scenarioName,
                            mobile = mobile,
                            vibrationClickCount = vibrationClickCount,
                            navController = navController
                        )
                    }
                }


                    }
                }
            }
        }






@Composable
fun AccueilScreen(onStartClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenue dans l'app Haptique", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onStartClick) {
                Text("Commencer")
            }
        }
    }
}


