package fr.maloof.hapticemotionapp.scenarios


import android.app.TimePickerDialog
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.ThumbUp
import java.util.*
import java.util.concurrent.TimeUnit
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext



@Composable
fun Scenario1UI(
    vibrationManager: VibrationManager,
    vibrationType: Int,

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
fun Scenario3UI(
    vibrationManager: VibrationManager,
    vibrationType: Int

) {
    var pseudo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ Titre
        Text(
            text = "Vous vous connectez à votre application préférée",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // ✅ Avatar / Illustration
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Utilisateur",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(96.dp)
                .padding(bottom = 16.dp)
        )

        // ✅ Champ pseudo
        OutlinedTextField(
            value = pseudo,
            onValueChange = { pseudo = it },
            label = { Text("Pseudo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Champ mot de passe
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Bouton de connexion (qui échoue volontairement ici)
        Button(
            onClick = {
                showAlert = true
                if (!vibrationPlayed) {
                    vibrationManager.vibrateByType(vibrationType)
                    vibrationPlayed = true
                    Log.d("Scenario3UI", "📣 Vibration déclenchée (échec connexion)")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Connexion", color = MaterialTheme.colorScheme.onError)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Alerte sous forme de popup
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                confirmButton = {
                    TextButton(onClick = { showAlert = false }) {
                        Text("OK")
                    }
                },
                icon = {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                },
                title = {
                    Text("Échec de la connexion")
                },
                text = {
                    Text("Vos identifiants sont incorrects. Veuillez réessayer.")
                }
            )
        }
    }
}



@Composable
fun Scenario4UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var expanded by remember { mutableStateOf(false) }
    val niveaux = listOf("débutant", "faible", "moyen", "hard")
    var selectedLevel by remember { mutableStateOf("selection") }
    var showAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🔸 Titre
        Text(
            text = "Sélectionnez un niveau dans la liste déroulante",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 🔸 Dropdown Menu
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedLevel)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                niveaux.forEach { niveau ->
                    DropdownMenuItem(
                        text = { Text(niveau) },
                        onClick = {
                            selectedLevel = niveau
                            expanded = false
                            showAlert = true
                            if (!vibrationPlayed) {
                                vibrationManager.vibrateByType(vibrationType)
                                vibrationPlayed = true
                                Log.d("Scenario4UI", "📣 Vibration déclenchée sur sélection : $niveau")
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔸 Alerte stylisée après sélection
        if (showAlert) {
            Card(
                modifier = Modifier.wrapContentSize(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "❗ Vous devez créer un compte avant de jouer",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun Scenario5UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var articleSelected by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🔸 Titre
        Text(
            text = "Vous êtes ravis de votre choix,\nl’application vous demande de sélectionner votre super article",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 🔸 Image de l’article plus grande
        Image(
            painter = painterResource(id = R.drawable.table), // ajoute bien table.png dans drawable
            contentDescription = "Table",
            modifier = Modifier
                .size(220.dp)
                .clickable {
                    articleSelected = true
                    if (!vibrationPlayed) {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                        Log.d("Scenario5UI", "✅ Vibration déclenchée : SUCCESS")
                    }
                }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Alerte positive stylée en vert
        if (articleSelected) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD0F0C0)) // vert clair
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Succès",
                        tint = Color(0xFF1B5E20) // vert foncé
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Super ! Vous avez bénéficié de -50%",
                        color = Color(0xFF1B5E20), // vert foncé
                        fontSize = 14.sp
                    )
                }
            }

        }
}}


@Composable
fun Scenario6UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var expanded by remember { mutableStateOf(false) }
    val jeux = listOf("Puissance 4", "Min", "Echec", "Super Mario")
    var selectedGame by remember { mutableStateOf("selection") }
    var showPositiveAlert by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 🔸 Titre
        Text(
            text = "Sélectionnez un jeu dans la liste déroulante",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 🔸 Dropdown menu
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedGame)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                jeux.forEach { jeu ->
                    DropdownMenuItem(
                        text = { Text(jeu) },
                        onClick = {
                            selectedGame = jeu
                            expanded = false
                            showPositiveAlert = true
                            if (!vibrationPlayed) {
                                vibrationManager.vibrateByType(vibrationType)
                                vibrationPlayed = true
                                Log.d("Scenario6UI", "✅ Vibration déclenchée sur sélection : $jeu")
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔸 Alerte positive après sélection
        if (showPositiveAlert) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFCCFFCC)) // vert clair
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Succès",
                        tint = Color(0xFF2E7D32) // vert foncé
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "C’est parti !",
                        color = Color(0xFF2E7D32),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Composable
fun Scenario7UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var confirmed by remember { mutableStateOf(false) }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (!confirmed) {
            // 🛍 Titre avant confirmation
            Text(
                text = "Vous êtes ravis de votre choix,\nl’application vous demande de confirmer votre super achat",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 🖼 Article affiché (table)
            Image(
                painter = painterResource(id = R.drawable.table), // image "table" à ajouter dans drawable
                contentDescription = "Achat",
                modifier = Modifier
                    .size(220.dp)
                    .padding(bottom = 24.dp)
            )

            // ✅ Bouton confirmer
            Button(
                onClick = {
                    confirmed = true
                    if (!vibrationPlayed) {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                        Log.d("Scenario8UI", "✅ Vibration de confirmation envoyée")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A)) // Vert succès
            ) {
                Text("CONFIRMER", color = Color.White)
            }

        } else {
            // ✅ Confirmation affichée après clic
            Text(
                text = "Bravo l’achat est confirmé 🎉",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // ✅ Icône confirmation / Visuel succès
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "Achat validé",
                tint = Color(0xFF2E7D32),
                modifier = Modifier
                    .size(96.dp)
                    .padding(bottom = 16.dp)
            )

            // (optionnel) Tu peux aussi afficher une image de lauriers ici si tu veux un effet visuel plus proche de ta maquette
            // Image(painter = painterResource(id = R.drawable.lauriers), contentDescription = "Lauriers", modifier = Modifier.size(120.dp))
        }
    }
}


@Composable
fun Scenario8UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    val context = LocalContext.current
    var selectedHour by remember { mutableStateOf(7) }
    var selectedMinute by remember { mutableStateOf(30) }
    var messageAlarme by remember { mutableStateOf("") }
    var vibrationPlayed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔸 Titre
        Text(
            text = "⏰ Réglage de votre alarme matinale",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 🔸 Cadran avec message superposé
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

            // Heure affichée au centre
            Text(
                text = "${selectedHour.toString().padStart(2, '0')} : ${selectedMinute.toString().padStart(2, '0')}",
                fontSize = 42.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            // 🟩 Bulle superposée au-dessus du cadran
            if (messageAlarme.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = "Alarme",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = messageAlarme,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔘 TimePicker
        Button(onClick = {
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    selectedHour = hour
                    selectedMinute = minute
                },
                selectedHour,
                selectedMinute,
                true
            ).show()
        }) {
            Text("Choisir une heure")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔘 Enregistrer
        Button(onClick = {
            if (!vibrationPlayed) {
                vibrationManager.vibrateByType(vibrationType)
                vibrationPlayed = true
            }

            val now = Calendar.getInstance()
            val alarm = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
                set(Calendar.SECOND, 0)
                if (before(now)) add(Calendar.DAY_OF_YEAR, 1)
            }

            val diffMillis = alarm.timeInMillis - now.timeInMillis
            val diffHours = TimeUnit.MILLISECONDS.toHours(diffMillis)
            val diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis) % 60

            messageAlarme = "L’alarme sonnera dans ${diffHours}h ${diffMinutes}min"
        }) {
            Text("Enregistrer")
        }
    }
}


