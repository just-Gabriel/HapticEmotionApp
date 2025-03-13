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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.maloof.hapticemotionapp.ui.theme.HapticEmotionAppTheme

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
                        FormulaireScreen { user, phone ->
                            // Une fois le formulaire validé et les données envoyées,
                            // tu rediriges vers l'écran avec les sliders
                            navController.navigate("sliders")
                        }
                    }

                    composable("sliders") {
                        SliderScreen()
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
            Text(
                text = "Bienvenue dans l'app Haptique",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onStartClick) {
                Text(text = "Commencer")
            }
        }
    }
}
