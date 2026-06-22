# 🛒 Alfagift Clone - Skripsi Project

> A modern Android application that replicates the user experience and shopping flow of the Alfagift mobile application using **Jetpack Compose**, developed as part of an undergraduate thesis project.

![Android](https://img.shields.io/badge/Platform-Android-green)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple)
![Jetpack%20Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)
![Material%203](https://img.shields.io/badge/Design-Material%203-orange)

---

## 📖 About The Project

**Alfagift Clone** merupakan aplikasi Android yang dikembangkan untuk mensimulasikan pengalaman pengguna pada aplikasi Alfagift. Proyek ini dibuat sebagai bagian dari penelitian skripsi dengan fokus pada implementasi antarmuka modern menggunakan **Jetpack Compose** serta penerapan arsitektur aplikasi Android yang terstruktur dan scalable.

Aplikasi ini mengimplementasikan berbagai fitur utama e-commerce seperti katalog produk, navigasi multi-halaman, keranjang belanja, hingga ringkasan pesanan dengan pendekatan **Modern Android Development (MAD)**.

---

## ✨ Features

### 🏠 Home Page

* Tampilan beranda yang menyerupai aplikasi Alfagift
* Banner promosi dan kategori produk
* Layout responsif dan modern

### 🛍️ Shopping Experience

* Daftar produk berdasarkan kategori
* Detail produk yang informatif
* Navigasi antar halaman yang intuitif

### 🛒 Cart Management

* Menambahkan produk ke keranjang
* Melihat detail pesanan
* Ringkasan transaksi sebelum checkout

### 🧭 Dynamic Navigation

* Implementasi Navigation Compose
* Single Activity Architecture
* Navigasi yang smooth antar halaman

### 📱 Modern UI/UX

* Material Design 3
* Jetpack Compose Components
* Responsive Layout
* Scrollable Content

---

## 🏗️ Application Architecture

Proyek ini menggunakan pola arsitektur:

```text
Presentation Layer
│
├── Screens
├── Components
└── Navigation
│
ViewModel Layer
│
├── State Management
└── Business Logic
│
Data Layer
│
├── Models
└── Data Sources
```

### Architecture Pattern

* MVVM (Model-View-ViewModel)
* Single Activity Architecture
* State Hoisting
* Navigation Component

---

## 🛠️ Tech Stack

| Technology         | Description               |
| ------------------ | ------------------------- |
| Kotlin             | Main Programming Language |
| Jetpack Compose    | Declarative UI Framework  |
| Material 3         | UI Design System          |
| Navigation Compose | Navigation Management     |
| Android Studio     | Development Environment   |
| MVVM               | Architectural Pattern     |

---

## 📱 Application Flow

### Splash Screen

Initial loading screen displayed when the application starts.

### Main Navigation

* 🏠 Home
* 🛍️ Shopping
* 🎉 Promo
* 📦 Order History
* 👤 Profile

### Shopping Flow

```text
Home
 ↓
Product Category
 ↓
Product List
 ↓
Product Detail
 ↓
Cart
 ↓
Order Summary
```

---

## 📂 Project Structure

```text
app/
├── navigation/
├── screens/
│   ├── splash/
│   ├── home/
│   ├── shopping/
│   ├── promo/
│   ├── order/
│   └── profile/
├── components/
├── ui/
│   ├── theme/
│   └── widgets/
├── model/
└── viewmodel/
```

---

## 🚀 Getting Started

### Prerequisites

Before running this project, make sure you have installed:

* Android Studio (Latest Version Recommended)
* Android SDK
* Kotlin
* JDK 17+

---

### Installation

Clone this repository:

```bash
git clone https://github.com/haniduraayatulloh/Skripsi_Hanidura-Ayatulloh.git
```

Open the project in Android Studio.

Sync Gradle dependencies:

```bash
File → Sync Project with Gradle Files
```

Run the application using:

* Android Emulator
* Physical Android Device

Click:

```bash
Run ▶
```

---

## 🎓 Academic Purpose

Proyek ini dikembangkan sebagai bagian dari penelitian skripsi Program Studi Teknik Informatika dengan tujuan:

* Mempelajari penerapan Jetpack Compose pada aplikasi e-commerce.
* Mengimplementasikan arsitektur MVVM pada Android.
* Mengembangkan UI modern berbasis Material Design 3.
* Menganalisis pengalaman pengguna pada aplikasi mobile commerce.

---

## 👨‍💻 Author

**Hanidura Ayatulloh**

Undergraduate Student of Informatics Engineering
Faculty of Computer Science
Universitas Brawijaya

GitHub:
https://github.com/haniduraayatulloh

---

## 📄 License

This project is developed for educational and academic purposes only.

All trademarks, logos, and visual references related to Alfagift belong to their respective owners.

This repository is not affiliated with, endorsed by, or associated with Alfagift or PT Sumber Alfaria Trijaya Tbk.
