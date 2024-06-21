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

| Home(Now playing/Popular) | Home(Top rated/Coming soon) |
| ------------- | ------------ |
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/dbd4720f-72b3-43cb-9b14-1058d5294e62" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/78940362-05e0-47f9-8d7e-aa5e3b53aa81" width="290" height="600"> |

| Trending | Search |
| ------------ | ------------- |
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/8b988e82-82b8-4a35-b84c-7738f509507f" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/ab534c4e-4980-491a-a1dc-a0fc6d90ebee" width="290" height="600"> |

| Searched | Details(Top) |
| ------------ | ------------ |
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/7610fc21-c123-4e15-a5c3-1783193235fe" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/7d37411d-7eac-4ff7-97f7-04de516cc88f" width="290" height="600"> |


| Details(Mid) | Details(Bottom) | 
| ------------- | ------------ | 
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/17a36614-08e2-4540-8be1-2134747ef988" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/6a1ff0e4-dc95-4c31-b4cc-b6620c882534" width="290" height="600"> | 

| Home(Dark mode) | Trending(Dark mode) |
| ------------ | ------------- |
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/ac5412ad-eb27-461c-9480-6e6a3f6176c6" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/5053b8e7-11ab-4617-973e-35c75d4c3d31" width="290" height="600"> |


| Search(Dark mode) | Details(Dark mode) |
| ------------ | ------------ |
| <img src="https://github.com/ifMaxi/Movips/assets/112733459/cd67b65f-1b28-42d3-b723-95533526ddad" width="290" height="600"> | <img src="https://github.com/ifMaxi/Movips/assets/112733459/9599f838-eedf-43f2-ad9c-43b83b54a68b" width="290" height="600"> |
