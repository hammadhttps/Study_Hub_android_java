# 📚 StudyHub – Smart Notes Organizer

## 🚀 Overview

StudyHub is an Android application designed to help students organize their study materials efficiently. The app follows a **Single Activity + Multiple Fragments architecture** and allows users to manage subjects, folders, and note images in a structured way.

---

## 🎯 Features

### 🔹 Splash Screen

* Displays app logo and name
* Simple animation (fade/scale)
* Automatically navigates to Main Screen after 3 seconds

---

### 🔹 Main Screen

* Built using **TabLayout + ViewPager2**
* Contains three main sections:

  * Subjects
  * Folders
  * Settings

---

### 🔹 Subjects Screen

* Displays subjects using **RecyclerView (Grid Layout)**
* Each subject includes:

  * Icon
  * Name
  * Number of folders
  * Open Folder button
* Uses:

  * Custom Adapter
  * Subject Model Class
* Clicking a subject navigates to Folders screen

---

### 🔹 Folders Screen

* Displays folders using **Custom ListView**
* Each folder includes:

  * Icon
  * Name
  * Number of images
  * Open button
  * Delete button
* Uses:

  * Custom Adapter
  * Folder Model Class
* Allows navigation to Notes Images screen

---

### 🔹 Notes Images Screen

* Displays images using **RecyclerView (Grid – 2 columns)**
* Features:

  * Add Image (opens gallery via implicit intent)
  * Delete Image
  * Share Image (using ACTION_SEND intent)
* Ensures validation before sharing

---

### 🔹 Settings Screen

* Uses **SharedPreferences** for persistent storage
* Options include:

  * Dark Mode toggle
  * Show Image Preview toggle
  * Clear All Notes
  * Reset App Data

---

## 💾 Persistent Data (SharedPreferences)

The app stores and retrieves data even after restart:

* Subjects list
* Folder structure
* Image paths
* Last opened subject
* App settings (toggles)

Example stored values:

```
studyhub.darkmode = true
studyhub.showPreview = true
studyhub.lastSubject = DSA
studyhub.folderCount = 3
```

---

## 🧠 Architecture & Concepts Used

* Single Activity Architecture
* Fragments Navigation
* RecyclerView & Custom Adapters
* Custom ListView
* Model Classes (Subject, Folder, NoteImage)
* SharedPreferences (Persistent Storage)
* Implicit Intents (Image Sharing & Gallery Access)

---

## 🏗️ Tech Stack

* Java / Android SDK
* XML (UI Design)
* Android Jetpack Components

---

## 📌 Minimum Data Requirements

* At least 4 Subjects
* At least 3 Folders per Subject
* Multiple Images per Folder

---

## ✅ Expected Behavior

* Data persists after app restart
* Users can add, delete, and share images
* Smooth navigation between fragments
* Settings are saved and restored correctly

---

## 📈 Future Improvements

* Cloud storage integration
* User authentication
* Search and filter functionality
* Drag & drop folder management

---

## 👨‍💻 Author

Developed as part of an academic assignment to demonstrate Android development concepts and best practices.

---
