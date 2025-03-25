package fr.maloof.hapticemotionapp


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class DataModel {

    @Parcelize
    data class User(
        val id: Int? = null,
        val age: Int,
        val sexe: String,
        val mainDominante: String,
        val password: String?,
        val paysResidence: String,
        val profession: String,
        val vibrationTelActive: Boolean,
        val vibrationClavierActive: Boolean,
        val coqueTel: Boolean,
        val niveauInformatique: Int,
    ) : Parcelable

    @Parcelize
    data class Telephone(
        val id: Int? = null,
        val marque: String,
        val modele: String,
        val versionLogiciel: String,
        val numeroModele: String,
    ) : Parcelable


    data class EmotionalExperience(
        val user: String,
        val telephone: String,
        val vibrationId: Int?,
        val slider1FS: Float?,
        val slider2WC: Float?,
        val slider3SN: Float?,
        val nbDeFois: Int?,
        val scenario: String?,
        val evaluation: String?,
        val mobile: Int?
    )

    @Parcelize
    data class VibrationEntry(
        val id: Int,
        val action: () -> Unit)
        : Parcelable
}



