<p align="center">  
MarvelWikia is a demo application Utilizing the MVVM + Kotlin + Flows + Room + Hilt.<br>
It  fetches data using the MarvelAPI and displays the list of marvel characters. With the functionality to tap on a character and
get a list of all the comics, events, series and stories he/she/it was in. Also the user can search for their favorite characters with a simple search function.
The application supports pagination as well, so the user may scroll and get new characters for every new page.</p>
</br>

## Application MAD Score cards

![Summary](https://github.com/Karim-92/MarvelDemo/blob/master/madscores/summary.png)
![Kotlin](https://github.com/Karim-92/MarvelDemo/blob/master/madscores/kotlin.png)
![Jetpack](https://github.com/Karim-92/MarvelDemo/blob/master/madscores/jetpack.png)

## Architecture
  - MarvelWikia is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
  
## Usage
  - To use the application and build it correctly you need to signup for the marvel developer API program, get a developer Key and create a file called "apikey.properties" in the root directory of the project. You'll then need to add two key value pairs on two separate lines. 
  
  ```xml
  PUB_API_KEY = "Your public developer key here"
  PRIV_API_KEY = "Your private developer key here" 
```
  And that's it! you're ready to build the project.
  
## Libraries used

  - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
  - [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses.
  - [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
  - [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - loading images.
  - [TransformationLayout](https://github.com/skydoves/transformationlayout) - implementing transformation motion animations.
  - [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently.
  - [Bundler](https://github.com/skydoves/bundler) - Android Intent & Bundle extensions that insert and retrieve values elegantly.
  - [Timber](https://github.com/JakeWharton/timber) - logging.
  - [MaterialSearchView](https://github.com/Mauker1/MaterialSearchView) - Material design searchview with query change state listeners.
  - [Hilt](https://dagger.dev/hilt/) - Dependency injection library built on top of Dagger2.
  - [DiscreteScrollView](https://github.com/yarolegovich/DiscreteScrollView) - A scrollable list of items that centers the current element and provides easy-to-use APIs for cool item animations.

## Influences
  - [MarvelHeroes](https://github.com/skydoves/marvelheroes): A similar application that utilizes coroutines, livedata and custom views and most of the libraries mentioned above. However it doesn't utilize the marvel API nor list the comics, events, stories and series.

<p align="center">  
"Data provided by Marvel. © 2014 Marvel"



