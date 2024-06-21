# Movips
A catalogue of films. 🍿

## Description

An application written in Kotlin that consumes several APIs from [The Movie DataBase](https://developer.themoviedb.org/docs/getting-started) to display movies and get information about them.

The app has a simple interface. The navigation bar contains 3 destinations: "Home", "Trending" and "Search" and an extra destination that is accessed by selecting an item, which is the "Details" screen.
It also uses several Jetpack libraries such as compose for the UI, room and pager with remote mediator. Thanks to remote mediator it has a small support for an "Offline first" type app.

> [!NOTE]
> The app supports light and dark mode, but does not support dynamic color. I decided to disable dynamic color for better viewing of images and text.

> [!IMPORTANT]
> In order to have access to the use of the application you need an API key or Token that you can obtain by registering on the website [The Movie DataBase](https://developer.themoviedb.org/docs/getting-started).
>
> Once you have obtained the key you will need to place it in your ***"local.properties"*** file in the root folder of the project and name the variable as ***"API_KEY"***, the rest of the configurations in the build gradle
> file have already been done. Then rebuild the project and you can start the app from the emulator.

## Architecture
The type of architecture used for this project was MVVM(Model-View-ViewModel).

This is divided into the:

- Model: Which represents the data and business logic
- View: Which represents the UI
- ViewModel: Which represents the bridge between the View and the Model

![1 oCv7nJ66-uq2d1f7eipV9w](https://github.com/ifMaxi/Movips/assets/112733459/8d028065-0608-4b2d-99b5-297baa5815c6)

## Api

The app uses several APIs from [The Movie DataBase](https://developer.themoviedb.org/docs/getting-started) including details, trending, upcoming, popular, now playing, top movies, and search movie.

## Language and libraries

- Kotlin
  - Serialization
  - Coroutines
  - Kps
- Jetpack Libraries
  - Compose
  - Hilt
  - ViewModel
  - Navigation
  - Room database
  - Paging 3
    - Remote mediator
- Other libraries
  - Retrofit
  - OkHttp
  - Coil
  - Lottie

## Captures

TODO.
