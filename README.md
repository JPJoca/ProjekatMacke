# Cat Breeds App

This Android application allows users to explore various cat breeds, learn detailed information about them, and view their photos, temperament,
and other characteristics. It is developed as a **school project** using **Kotlin**, **Jetpack Compose**, and modern Android development tools.

## Features

### Breeds List Screen
- Lists all available cat breeds using TheCatApi.
- Each list item shows:
  - Breed name
  - Alternative names (if available)
  - A shortened description (up to 250 characters)
  - Up to 3 temperament traits (randomly selected)

### Breed Details Screen
- Displays detailed information including:
  - At least one image of the breed
  - Full description
  - Origin countries
  - All temperament traits
  - Life span
  - Weight and/or height
  - Behavior and needs visualized via at least 5 Material UI widgets (e.g., adaptability, affection level, dog friendliness, etc.)
  - Rare breed indicator
  - Button to open Wikipedia page in browser

### Search Functionality
- Search breeds by name
- Reusable list item UI as in the main screen
- Clicking a result opens the breed details screen
- Optionally integrated in the list screen with the ability to reset results

---

##  Technologies Used

- **Kotlin** – Programming language
- **Jetpack Compose** – Modern declarative UI
- **Jetpack Navigation** – For single-activity navigation
- **Model-View-Intent (MVI)** – Architecture pattern
- **Coroutines** – For asynchronous operations
- **TheCatApi** – External API for breed and image data

---

## Backend API (TheCatApi)

The application uses the [TheCatApi](https://thecatapi.com) to fetch breed data and images.

For full API documentation:  
📄 https://documenter.getpostman.com/view/5578104/VUjSHPqo

---

