package fr.maloof.hapticemotionapp.scenarios


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import fr.maloof.hapticemotionapp.R
import fr.maloof.hapticemotionapp.components.SamsungClockPicker


@Composable
fun Scenario1UI(
    vibrationManager: VibrationManager,
    vibrationType: Int,
    userId: Int,
    telephoneId: Int,
    slider1: Float,
    slider2: Float,
    slider3: Float,
    mobile: Int,
    vibrationClickCount: Int
) {
    var vibrationPlayed by remember { mutableStateOf(false) }
    var showUnavailableMessage by remember { mutableStateOf(false) }


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

            Text(
                text = "🎩 Sélectionnez un article",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )


            // ✅ Chapeau bleu
            Box(contentAlignment = Alignment.TopStart) {
                var showUnavailableMessageBlue by remember { mutableStateOf(false) }
                var vibrationPlayedBlue by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(id = R.drawable.cap_blue),
                    contentDescription = "Chapeau bleu",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            showUnavailableMessageBlue = true
                        }
                )

                if (showUnavailableMessageBlue) {
                    if (!vibrationPlayedBlue) {
                        LaunchedEffect(showUnavailableMessageBlue) {
                            Log.d(
                                "Scenario1UI",
                                "📣 Vibration déclenchée : BLEUE → type=$vibrationType"
                            )
                            vibrationManager.vibrateByType(vibrationType)
                            vibrationPlayedBlue = true
                        }
                    }

                    Card(
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp)
                            .wrapContentSize(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Alerte",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "⚠ Article indisponible",
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // ✅ Chapeau rouge + message + vibration au moment de l'affichage
            Box(contentAlignment = Alignment.TopStart) {
                var showUnavailableMessage by remember { mutableStateOf(false) }
                var vibrationPlayed by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(id = R.drawable.cap_red),
                    contentDescription = "Chapeau rouge",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            showUnavailableMessage = true
                        }
                )

                // ✅ Vibration dès que showUnavailableMessage devient true
                if (showUnavailableMessage) {
                    if (!vibrationPlayed) {
                        LaunchedEffect(showUnavailableMessage) {
                            Log.d(
                                "Scenario1UI",
                                "📣 Vibration déclenchée dans Text() : type=$vibrationType"
                            )
                            vibrationManager.vibrateByType(vibrationType)
                            vibrationPlayed = true
                        }
                    }

                    // ✅ Nouvelle alerte stylée à la place du Text classique
                    Card(
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp)
                            .wrapContentSize(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Alerte",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "⚠ Article indisponible",
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Chapeau vert avec vibration + alerte stylée
            Box(contentAlignment = Alignment.TopStart) {
                var showUnavailableMessageGreen by remember { mutableStateOf(false) }
                var vibrationPlayedGreen by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(id = R.drawable.cap_green),
                    contentDescription = "Chapeau vert",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            showUnavailableMessageGreen = true
                        }
                )

                if (showUnavailableMessageGreen) {
                    if (!vibrationPlayedGreen) {
                        LaunchedEffect(showUnavailableMessageGreen) {
                            Log.d(
                                "Scenario1UI",
                                "📣 Vibration déclenchée : VERTE → type=$vibrationType"
                            )
                            vibrationManager.vibrateByType(vibrationType)
                            vibrationPlayedGreen = true
                        }
                    }

                    Card(
                        modifier = Modifier
                            .padding(start = 4.dp, top = 4.dp)
                            .wrapContentSize(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Alerte",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "⚠ Article indisponible",
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }}}




@Composable
fun Scenario2UI(
    vibrationManager: VibrationManager,
    vibrationType: Int,
    userId: Int,
    telephoneId: Int,
    slider1: Float,
    slider2: Float,
    slider3: Float,
    mobile: Int,
    vibrationClickCount: Int
) {
    var showAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf(7) }
    var selectedMinute by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔸 Titre
        Text(
            text = "⏰ Réglage de votre alarme matinale",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 🔸 Samsung style clock picker (notre composant stylé)
        SamsungClockPicker(
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            onHourChange = { selectedHour = it },
            onMinuteChange = { selectedMinute = it },
            vibrationManager = vibrationManager,
            vibrationType = vibrationType
        )


        Spacer(modifier = Modifier.height(24.dp))

        // 🔘 Bouton "Enregistrer"
        Button(
            onClick = {
                showAlert = true
                if (!vibrationPlayed) {
                    Log.d("Scenario2UI", "💢 Vibration déclenchée : type=$vibrationType")
                    vibrationManager.vibrateByType(vibrationType)
                    vibrationPlayed = true
                }
            }
        ) {
            Text("Enregistrer")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔔 Alerte stylée en cas de conflit horaire
        if (showAlert) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Alerte",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Heure déjà occupée par un mode \"Ne pas déranger\"",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 14.sp
                    )
                }
            }
        }
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


