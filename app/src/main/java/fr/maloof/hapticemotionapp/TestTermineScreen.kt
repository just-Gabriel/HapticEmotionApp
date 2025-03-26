package fr.maloof.hapticemotionapp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.emitter.*
import nl.dionsegijn.konfetti.core.models.Size
import fr.maloof.hapticemotionapp.components.CustomButton








@Composable
fun TestTermineScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // 🎊 Confettis en arrière-plan
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    angle = Angle.TOP,
                    speed = 10f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    emitter = Emitter(duration = 60_000L).perSecond(100),
                    position = Position.Relative(0.5, 0.0),
                    size = listOf(Size(12), Size(18))
                )
            )
        )

        // 🧩 UI par-dessus les confettis
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.85f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // 🎉 Message central
                Column(
                    modifier = Modifier.weight(1f), // ✅ Cela empêche de prendre trop de place
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🎉 Test terminé !",
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "🙏 Un immense merci pour votre participation précieuse.\n" +
                                "Grâce à vous, l’expérience haptique progresse vers un futur plus intuitif 💡",
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                // 🔁 Bouton retour en bas visible
                CustomButton(
                    text = "🔁 Revenir à l'accueil",
                    onClick = {
                        navController.navigate("accueil") {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                )
            }
        }
    }
}


