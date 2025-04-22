HapticEmotionApp
HapticEmotionApp est une application Android développée pour explorer la perception des retours haptiques dans divers contextes d'interaction utilisateur. Elle permet de déclencher des vibrations spécifiques et de recueillir les évaluations des utilisateurs sur ces sensations.

🎯 Objectif
L'objectif principal de cette application est d'étudier comment les utilisateurs perçoivent différentes vibrations haptiques en fonction de scénarios simulés tels que :

Sélection

Alerte

Échec/Réussite

Chaque vibration est suivie d'une évaluation immédiate par l'utilisateur, permettant de collecter des données sur la perception haptique dans différents contextes.

🛠️ Fonctionnalités
Déclenchement de vibrations haptiques : Utilisation de la bibliothèque haptique Android pour simuler des retours tactiles.

Scénarios interactifs : Présentation de contextes variés pour chaque vibration.

Évaluation utilisateur : Recueil des impressions des utilisateurs via des curseurs d'évaluation.

Mode hors-ligne : Possibilité de tester l'application sans connexion réseau grâce à un service API factice.

📱 Installation
Cloner le dépôt GitHub :

bash
Copier
Modifier
git clone https://github.com/just-Gabriel/HapticEmotionApp.git
Ouvrir le projet dans Android Studio :

Lancer Android Studio.

Sélectionner "Open an existing project".

Naviguer jusqu'au dossier cloné et l'ouvrir.

Configurer le fichier ServiceLocator.kt :

Pour activer le mode hors-ligne (sans backend), modifier la variable useFakeApi :

kotlin
Copier
Modifier
private val useFakeApi = true
Cela permettra d'utiliser des données simulées sans nécessiter de connexion réseau.

Exécuter l'application :

Connecter un appareil Android ou utiliser un émulateur.

Cliquer sur "Run" pour lancer l'application.

📊 Structure du projet
ui/ : Composants d'interface utilisateur (écrans, curseurs, etc.).

network/ : Gestion des services API, incluant le service factice pour le mode hors-ligne.

model/ : Modèles de données utilisés dans l'application.

utils/ : Classes utilitaires pour la gestion des scénarios et des vibrations.

📄 Documentation supplémentaire
Pour une compréhension approfondie des scénarios et des objectifs de l'application, veuillez consulter le document suivant :

👉 Documentation des scénarios haptiques

🤝 Contribuer
Les contributions sont les bienvenues ! Si vous souhaitez améliorer l'application ou ajouter de nouvelles fonctionnalités, n'hésitez pas à forker le dépôt et à soumettre une pull request.

📜 Licence
Ce projet est sous licence MIT. Consultez le fichier LICENSE pour plus d'informations.