
# 📝 Application de gestion de notes JustDoIt (Jetpack Compose)

Cette application Android est un petit projet de **gestion de notes / todos**, développée avec **Jetpack Compose** pour l’UI et **Room** pour la persistance des données.  
Elle permet de créer, consulter et supprimer des notes localement.

---

## 📱 Fonctionnalités

- 🏠 **Écran d’accueil (Notes)** : liste de toutes les notes enregistrées  
- ➕ **Ajout de note** : possibilité de créer une nouvelle note avec un titre et un contenu  
- ⚙️ **Paramètres** : page de réglages (thème, compte, apparence, etc.)  
- 🗄 **Sauvegarde locale** grâce à **Room Database**  
- 🎨 Interface moderne avec **Jetpack Compose**

---

## 🛠️ Technologies utilisées

- **Kotlin**
- **Jetpack Compose** (UI déclarative moderne)
- **Navigation Compose** (gestion des écrans)
- **Room** (base de données locale)
- **MVVM** (architecture avec ViewModel et Repository)
- **Coroutines & Flow** (gestion asynchrone et réactive)

---

## 📂 Structure du projet

```
app/
 └─ src/main/java/com/example/todos/
     ├─ MainActivity.kt
     ├─ NavGraph.kt
     ├─ Destinations.kt
     ├─ model/Note.kt
     ├─ repository/NoteRepository.kt
     ├─ viewmodel/NotesViewModel.kt
     └─ ui/
         ├─ theme/Theme.kt
         ├─ components/TopBar.kt
         ├─ components/BottomBar.kt
         ├─ components/SearchBar.kt
         ├─ components/NoteCard.kt
         └─ screens/
             ├─ HomeScreen.kt
             ├─ DetailScreen.kt
             └─ SettingsScreen.kt
````

---

## 🚀 Installation

1. Clone le projet  
 ```bash
 git clone git@github.com:n3ominer/JustDoIt.git
 cd JustDoIt
 ````

2. Ouvre le projet dans **Android Studio**

3. Lance l’application sur un émulateur ou un appareil physique

---

## 📌 Features futures

* Synchronisation avec une base distante (Firebase, Supabase, etc.)
* Gestion des catégories de notes
* Ajout de rappels (notifications locales)
* Ajout de Room DB pour la persistence locale des données

---
