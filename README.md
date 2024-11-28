# SnapFetch

## Overview

SnapFetch is a simple Android application that displays a list of photos sourced from the public API at <a href="https://picsum.photos/">picsum.photos</a>. Users can view a list of photos with a loading animation, refresh the list to load new photos, and tap on any photo to see detailed information about it, including the author, dimensions, and download link.

## Features

- **Fetch Photos**: : Loads a list of 20 photos from the Unsplash- public API.
- **Pull-to-Refresh**: Allows users to refresh the list of photos with a button, fetching a new set of 20 photos each time.
- **Loading Animation:**: Displays a loading animation while photos are being fetched.
- **Photo List**: Each item in the list shows a photo thumbnail and its ID.
- **Photo Counter**: The list of photos has counter which dispaly number of loaded photos.
- **Photo Details**: Tapping on a photo displays detailed information, including the ID, author name, width, height, and download URL.
- **Error Handling**: Shows error messages if there are issues fetching data or no network connection.

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/artwil94/snap-fetch.git
    cd snap-fetch
    ```

2. **Open the project**: Open the project in Android Studio.

3. **Sync the project**: Ensure that all Gradle dependencies are downloaded and the project is synced successfully.

4. **Run the app**: Connect an Android device or start an emulator and run the app from Android Studio. The application requires network connection.

## Usage

1. **View Photos**: Open the app to see the list of photos. Each item displays the photo and its ID.
2. **Automatic Refreshing List**:  As you scroll to the bottom of the list, a new set of 20 photos loads automatically, ensuring a seamless browsing experience.
3. **View Details:**: Tap any photo to navigate to a details screen with more information about the photo, including ID, author, dimensions, and a download URL.

## Technology Stack

- **Kotlin**: Programming language used for the application, including Kotlin Coroutines and Kotlin Flow for asynchronous programming
- **Android Architecture**: MVVM pattern
- **Jetpack Compose**: Used for build UI
- **Coroutines and Flow**: For asynchronous operations.
- **Retrofit**: For making API calls
- **Coil**: For image loading from the network
- **Hilt Dagger**: Used for dependency injection
- **JUnit and MockK**: For unit testing.
- **Timber**: For logging

## UX/UI

<img width="308" alt="Screenshot1" src="https://github.com/user-attachments/assets/d3a3c506-7f69-4c82-bbc1-813dce936bfc">
<img width="307" alt="Screenshot2" src="https://github.com/user-attachments/assets/bd8d4c25-4a36-4a7f-8295-4fcab6caf680">
<img width="307" alt="Screenshot2" src="https://github.com/user-attachments/assets/01e5efc4-1a59-4384-8417-19707d71efdf">
