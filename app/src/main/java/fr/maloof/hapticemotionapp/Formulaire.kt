package fr.maloof.hapticemotionapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.maloof.hapticemotionapp.DataModel.User
import fr.maloof.hapticemotionapp.DataModel.Telephone

@Composable
fun FormulaireScreen(onFormSubmit: (User, Telephone) -> Unit) {

    // Champs utilisateur
    var age by remember { mutableStateOf("") }
    var sexe by remember { mutableStateOf("") }
    var mainDominante by remember { mutableStateOf("Droite") }
    var superviseur by remember { mutableStateOf("") }
    var paysResidence by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    var isVibrationTelActive by remember { mutableStateOf(false) }
    var isVibrationClavierActive by remember { mutableStateOf(false) }
    var isCoqueTelActive by remember { mutableStateOf(false) }
    var niveauInformatique by remember { mutableStateOf(0f) }

    // Champs téléphone
    var phoneBrand by remember { mutableStateOf("") }
    var phoneModel by remember { mutableStateOf("") }
    var phoneVersion by remember { mutableStateOf("") }
    var phoneModelNumber by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Formulaire utilisateur",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Âge") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = sexe, onValueChange = { sexe = it }, label = { Text("Genre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = superviseur, onValueChange = { superviseur = it }, label = { Text("Nom du superviseur") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = paysResidence, onValueChange = { paysResidence = it }, label = { Text("Pays de résidence") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = profession, onValueChange = { profession = it }, label = { Text("Profession") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Main dominante :", fontWeight = FontWeight.Medium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = mainDominante == "Droite", onClick = { mainDominante = "Droite" })
            Text("Droite", modifier = Modifier.padding(end = 16.dp))
            RadioButton(selected = mainDominante == "Gauche", onClick = { mainDominante = "Gauche" })
            Text("Gauche")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isVibrationTelActive, onCheckedChange = { isVibrationTelActive = it })
            Text("Vibration téléphone activée")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isVibrationClavierActive, onCheckedChange = { isVibrationClavierActive = it })
            Text("Vibration clavier activée")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isCoqueTelActive, onCheckedChange = { isCoqueTelActive = it })
            Text("Coque de téléphone")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Niveau informatique : ${niveauInformatique.toInt()}")
        Slider(
            value = niveauInformatique,
            onValueChange = { niveauInformatique = it },
            valueRange = 0f..5f,
            steps = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Informations sur le téléphone personnel",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(value = phoneBrand, onValueChange = { phoneBrand = it }, label = { Text("Système d’exploitation du téléphone") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phoneModel, onValueChange = { phoneModel = it }, label = { Text("Modèle téléphone") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phoneVersion, onValueChange = { phoneVersion = it }, label = { Text("Version logicielle") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = phoneModelNumber, onValueChange = { phoneModelNumber = it }, label = { Text("Numéro de modèle") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val user = DataModel.User(
                    age = age.toIntOrNull() ?: 0,
                    sexe = sexe,
                    mainDominante = mainDominante,
                    superviseur = superviseur,
                    paysResidence = paysResidence,
                    profession = profession,
                    vibrationTelActive = isVibrationTelActive,
                    vibrationClavierActive = isVibrationClavierActive,
                    coqueTel = isCoqueTelActive,
                    niveauInformatique = niveauInformatique.toInt()
                )

                val telephone = DataModel.Telephone(
                    marque = phoneBrand,
                    modele = phoneModel,
                    versionLogiciel = phoneVersion,
                    numeroModele = phoneModelNumber
                )

                // Envoi API
                RetrofitInstance.api.createUser(user).enqueue(object : retrofit2.Callback<DataModel.User> {
                    override fun onResponse(call: retrofit2.Call<DataModel.User>, response: retrofit2.Response<DataModel.User>) {
                        if (response.isSuccessful) {
                            Log.d("RETROFIT", "✅ Utilisateur envoyé avec succès")
                            RetrofitInstance.api.createTelephone(telephone).enqueue(object : retrofit2.Callback<DataModel.Telephone> {
                                override fun onResponse(call: retrofit2.Call<DataModel.Telephone>, response: retrofit2.Response<DataModel.Telephone>) {
                                    if (response.isSuccessful) {
                                        Log.d("RETROFIT", "✅ Téléphone envoyé avec succès")
                                        onFormSubmit(user, telephone)
                                    } else {
                                        Log.e("RETROFIT", "❌ Erreur Téléphone : ${response.code()}")
                                    }
                                }

                                override fun onFailure(call: retrofit2.Call<DataModel.Telephone>, t: Throwable) {
                                    Log.e("RETROFIT", "❌ Envoi téléphone échoué : ${t.message}")
                                }
                            })
                        } else {
                            Log.e("RETROFIT", "❌ Erreur Utilisateur : ${response.code()}")
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<DataModel.User>, t: Throwable) {
                        Log.e("RETROFIT", "❌ Envoi utilisateur échoué : ${t.message}")
                    }
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuer")
        }
    }
}
