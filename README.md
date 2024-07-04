## Sitoo Android Assignment

## Overview
This is my implementation of the test assignment for Sitoo

<br/>
<br/>

## Tech Stack & Libraries

- [Material 3 design]
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

<br/>
<br/>

##  Architecture

Developed using [Google's official architecture guidance](https://developer.android.com/topic/architecture)

1. UI Layer
2. Domain layer
3. Data layer

<br/>
<br/>

## Run down

The app fetches products from API displayed in a LazyColumn loaded from a paginating source 
that requests 10 products starting from index 0. When the user scrolls down the list the 
paginating source prefetches the next 10 products when the list reaches 3 products from the end

Clicking on a product in the list navigates to the ProductDetailsScreen presenting all available
values and a product image from the API request.

The network requests are cached, the cache also works when the device is offline.

When no internet is detected a sticky header appears in the products list informing the user,
automatically disappears when connection is reestablished.

When the pagination source don't receive any data from API request a error view with a retry button 
is displayed informing the user that no content is available.

<br/>
<br/>

# Info

Local properties are included in the git repo because I stored the API auth in there, 
they are accessible within the BuildConfigField.

The AppState was intended to be used across screen states and I kept it for scalability.

<br/>
<br/>

## Testing

There are two android tests for the screens using Hilt and io.mockk, very simple.
One unit test for certifying that the network data is correctly mapped 

Test network modules are injected and replace the standard when android tests are runned.
Mock Interceptor: returns local json data for Products

<br/>
<br/>

## Things I encountered

The TopAppBar in compose is difficult reusing over many screens, the scroll state does not reset nor saves using navigation 
only persists and when restoring saved states it behaves very jerky.

Navigation using NavHost is not type safe when passing args, the newest navigation version is but it was spamming the log
with error logs so I kept to the older version.

<br/>
<br/>

Created by Henrik Ekelin 2024-07-01
email: gekn76@gmail.com
