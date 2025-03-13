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
        val superviseur: String?,
        val paysResidence: String,
        val profession: String,
        val vibrationTelActive: Boolean,
        val vibrationClavierActive: Boolean,
        val coqueTel: Boolean,
        val niveauInformatique: Int,
        //val experiences: List<Experience> = emptyList()
    ) : Parcelable

    @Parcelize
    data class Telephone(
        val id: Int? = null,
        val marque: String,
        val modele: String,
        val versionLogiciel: String,
        val numeroModele: String,
        //val experiences: List<Experience> = emptyList(),
        //val vibrations: List<Vibration> = emptyList()
    ) : Parcelable

}



