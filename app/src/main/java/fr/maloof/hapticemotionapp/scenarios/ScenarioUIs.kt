package fr.maloof.hapticemotionapp.scenarios


import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.ui.zIndex
import fr.maloof.hapticemotionapp.components.CustomButton
import androidx.compose.ui.layout.ContentScale






@Composable
fun Scenario1UI(
    vibrationManager: VibrationManager,
    vibrationType: Int,

) {

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


//________________________________________________________________________________________________________________________________________________________________________________

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

//_____________________________________________________________________________________________________________________________________________________________________________


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
            tint = Color(0xFF029AAF),
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
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val buttonColor by animateColorAsState(
            targetValue = if (isPressed) Color(0xFF029AAF) else Color(0xFF029AAF),
            label = "dynamicRed"
        )

        CustomButton(
            text = "Connexion",
            onClick = {
                showAlert = true
                if (!vibrationPlayed) {
                    vibrationManager.vibrateByType(vibrationType)
                    vibrationPlayed = true
                    Log.d("Scenario3UI", "📣 Vibration déclenchée (échec connexion)")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = buttonColor, // couleur animée
            interactionSource = interactionSource // pour détecter le clic ici uniquement
        )



        // ✅ Alerte sous forme de popup
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                confirmButton = {
                    CustomButton(
                        text = "Réessayer",
                        onClick = { showAlert = false },
                        backgroundColor = Color(0xFF029AAF),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(36.dp)
                    )
                },
                title = {
                    Text(
                        "Connexion échouée",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Text("Oups... vos identifiants sont incorrects.\nVeuillez réessayer.")
                },
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            )
        }

    }
}

//_____________________________________________________________________________________________________________________________________________________________________________

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
        Box(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                text = selectedLevel.ifEmpty { "Sélectionner un niveau" },
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .wrapContentSize(Alignment.TopStart)
                    .fillMaxWidth()
                    .zIndex(1f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                niveaux.forEach { niveau ->
                    val isSelected = niveau == selectedLevel

                    DropdownMenuItem(
                        onClick = {
                            selectedLevel = niveau
                            expanded = false
                            showAlert = true
                            if (!vibrationPlayed) {
                                vibrationManager.vibrateByType(vibrationType)
                                vibrationPlayed = true
                                Log.d("Scenario4UI", "📣 Vibration déclenchée sur sélection : $niveau")
                            }
                        },
                        text = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = if (isSelected) Color(0xFF029AAF).copy(alpha = 0.1f) else Color.Transparent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = null,
                                    tint = if (isSelected) Color(0xFF029AAF) else Color.Gray,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = niveau,
                                    fontSize = 16.sp,
                                    color = if (isSelected) Color(0xFF029AAF) else Color.Black,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
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
//_____________________________________________________________________________________________________________________________________________________________________________
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

        // 💎 Image dans une Card stylée
        Card(
            modifier = Modifier
                .size(240.dp)
                .clickable {
                    articleSelected = true
                    if (!vibrationPlayed) {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                        Log.d("Scenario5UI", "✅ Vibration déclenchée : SUCCESS")
                    }
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.table),
                contentDescription = "Table",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Alerte positive stylée
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
                        color = Color(0xFF1B5E20),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


//_____________________________________________________________________________________________________________________________________________________________________________
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

//_____________________________________________________________________________________________________________________________________________________________________________
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
            Text(
                text = "Vous êtes ravis de votre choix,\nl’application vous demande de confirmer votre super achat",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 🖼️ Image dans une Card
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .size(250.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.table),
                    contentDescription = "Achat à confirmer",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ✅ Custom Button Vert
            CustomButton(
                text = "CONFIRMER",
                backgroundColor = Color(0xFF66BB6A),
                onClick = {
                    confirmed = true
                    if (!vibrationPlayed) {
                        vibrationManager.vibrateByType(vibrationType)
                        vibrationPlayed = true
                        Log.d("Scenario7UI", "✅ Vibration de confirmation envoyée")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Bravo l’achat est confirmé 🎉",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "Achat validé",
                tint = Color(0xFF2E7D32),
                modifier = Modifier
                    .size(96.dp)
                    .padding(bottom = 16.dp)
            )

            // 🌿 Bonus : visuel type laurier ou trophée ?
            // Image(painter = painterResource(id = R.drawable.lauriers), contentDescription = "Success", modifier = Modifier.size(120.dp))
        }
    }
}


enum class ActivationState {
    Idle, Loading, Success
}



//_____________________________________________________________________________________________________________________________________________________________________________



@Composable
fun Scenario8UI(
    vibrationManager: VibrationManager,
    vibrationType: Int
) {
    var activationState by remember { mutableStateOf(ActivationState.Idle) }
    var isPressed by remember { mutableStateOf(false) }

    // ⚡ Animation de zoom rebond
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.05f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "scaleAnim"
    )

    // 💥 Rebond après clic
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(150)
            isPressed = false
        }
    }

    // ✅ Effet après état Loading
    LaunchedEffect(activationState) {
        if (activationState == ActivationState.Loading) {
            delay(2000L)
            activationState = ActivationState.Success
            Log.d("Scenario8UI", "✅ Vibration envoyée (Success)")
            vibrationManager.vibrateByType(vibrationType)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Appuie sur le bouton ci-dessous pour continuer.",
                fontSize = 18.sp,
                color = Color(0xFF444444), // gris foncé sympa
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Ton bouton stylé ici
            Button(
                onClick = {
                    if (activationState == ActivationState.Idle) {
                        isPressed = true
                        activationState = ActivationState.Loading
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (activationState) {
                        ActivationState.Idle, ActivationState.Loading -> Color(0xFF029AAF)
                        ActivationState.Success -> Color(0xFF4CAF50)
                    },
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 12.dp
                ),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier
                    .graphicsLayer(scaleX = scale, scaleY = scale)
                    .height(56.dp)
                    .width(220.dp)
                    .padding(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    when (activationState) {
                        ActivationState.Idle -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_up),
                                contentDescription = "Activate",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("let's go ! 🕊️", color = Color.White)
                        }

                        ActivationState.Loading -> {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Waiting...", color = Color.White)
                        }

                        ActivationState.Success -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "on continue",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("on continue ✅", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}






