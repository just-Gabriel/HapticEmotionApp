package fr.maloof.hapticemotionapp.scenarios

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.maloof.hapticemotionapp.VibrationManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.clickable
import fr.maloof.hapticemotionapp.R





@Composable
fun Scenario1UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.cap_blue),
                contentDescription = "Chapeau bleu",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box {
                Image(
                    painter = painterResource(id = R.drawable.cap_red),
                    contentDescription = "Chapeau rouge",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            vibrationManager.vibrateByType(vibrationType)
                            vibrationPlayed = true
                        }
                )
                // 💬 Message d’alerte superposé
                /*Text(
                    text = "Article indisponible",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )*/
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.cap_green),
                contentDescription = "Chapeau vert",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                    }
            )
        }
    }
}


@Composable
fun Scenario2UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("⚠ Scénario 2 : Failure - Warning - Navigation", fontSize = 18.sp)
        AlertCard("Une erreur s'est produite.")
        AlertCard("Navigation interrompue.")
    }
}

@Composable
fun Scenario3UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("⚠ Scénario 3 : Failure - Confirmation - Selection", fontSize = 18.sp)
        AlertCard("Action échouée mais confirmée.")
        AlertCard("Choisissez une nouvelle option.")
    }
}

@Composable
fun Scenario4UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("⚠ Scénario 4 : Failure - Confirmation - Navigation", fontSize = 18.sp)
        AlertCard("Échec confirmé.")
        AlertCard("Navigation vers une solution proposée.")
    }
}

@Composable
fun Scenario5UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("✔ Scénario 5 : Success - Warning - Selection", fontSize = 18.sp)
        AlertCard("Succès avec avertissement.")
        AlertCard("Attention à votre sélection.")
    }
}

@Composable
fun Scenario6UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("✔ Scénario 6 : Success - Warning - Navigation", fontSize = 18.sp)
        AlertCard("Succès partiel dans la navigation.")
    }
}

@Composable
fun Scenario7UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("✔ Scénario 7 : Success - Confirmation - Selection", fontSize = 18.sp)
        AlertCard("Succès confirmé.")
        AlertCard("Sélection validée.")
    }
}

@Composable
fun Scenario8UI() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("✔ Scénario 8 : Success - Confirmation - Navigation", fontSize = 18.sp)
        AlertCard("Navigation fluide et validée.")
    }
}

@Composable
fun AlertCard(message: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = message, fontSize = 16.sp)
        }
    }
}


