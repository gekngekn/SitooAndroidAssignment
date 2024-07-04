## Sitoo Android Assignment

## Overview
This is my implementation of the test assignment for Sitoo

## Tech Stack & Libraries

- [Material 3 design] used across the whole app
- [Jetpack Compose]
- [Androidx]
	- Paging3
	- Navigation

- [Hilt] Dependency Injection
- [Coil] For displaying Images in Compose
- [Retrofit2 & OkHttp3] For networking

- Testing
	- jUnit4
	- Hilt
	- io.mockk
	- Kotlinx coroutines
  <br>
  <br>
  <br>
  
##  Architecture

Developed using [Google's official architecture guidance](https://developer.android.com/topic/architecture)

1. UI Layer
2. Domain layer
3. Data layer

## Testing

There are two android tests for the screens using Hilt and io.mockk, very simple.
One unit test for certifying that the network data is correctly mapped 

Test network modules are injected and replace the standard when android tests are runned.
Mock Interceptor: returns local json data for Products

I would have added more comprehensive testing but too much time went in to building tests for the pagination module,
it refused to play ball with mocked instances soo I chose to pass it over.  


## Things I encountered

I choose not to build too many custom views to keep the app clean and readable.

The AppState was intended to be used across screen states and I kept it for scalability.

The product pagination starts with fetching 10 products and prefetches new products when it is three from the end. 
The caching is not persistent over configuration changes.

The TopAppBar in compose is difficult reusing over many screens, the scroll state does not reset nor saves using navigation 
only persists and when restoring saved states it behaves very jerky.

Navigation using NavHost is not type safe when passing args, the newest navigation version is but it was spamming the log
with error logs so I kept to the older version.

Animation, I started using android foundation for shared element animations during screen transitions but I realized it would 
become too time consuming.

Local properties are included in the git repo because I stored the API auth in there, they are stored in the BuildConfigField.


Created by Henrik Ekelin 2024-07-01
email: gekn76@gmail.com
