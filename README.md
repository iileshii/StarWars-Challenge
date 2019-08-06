# StarWars-Challenge
An app to show StarWars Characters Info

# CHALLENGE

Implement a small but scalable (!) app which interacts with the API. Please use the API provided to you in the resources section.

The app should contain 2 main areas:
Character search (Home Screen)
Character details

In the resources section, you will find a txt file including specific attribute requirements for the character details screen.

Expected user flow
On app start, the user lands on the character search screen.
The user can search for characters from the Star Wars universe. The result of the search should display a character list.
When tapping on a character, details are displayed as defined in the attribute requirements text file.

Technical requirements
Please use Kotlin as the project language. The design of the UI, usage of 3rd party libraries and supported API level are simply your choice, with exceptions mentioned in the resources section. That said, don't forgot to approach this like a project that has to scale, so make the appropriate decisions regarding structure, architecture, testing, DI, ...

Documentation
As part of the deliverable, please add a read.me file documenting your decisions (e.g. design patterns, 3rd party library choices). This will helps us understand your thought process and can fuel further discussions during the interview process. Please be aware that your documentation and explanations will be a big part of our evaluation.

Attribute requrements
[Character search]
name
birth_year

[Character details]
name
birth_year
height (cm/feet/inches)
name (species)
language (species)
homeworld (species)
population (planets)
films (the movies the character appears in: title, release date, opening crawl)

# SOLUTION DESCRIPTION

The app contains 3 main layer:
- presentation
- repository
- domain

They are close to the Clean architecture layers: 
- domain layer here is just domain data classes
- repository layer handles everything about data sources and parsing them
- presentation layer makes UI make datas visible

Also every layer is ready to be separated to a module.

## Domain layer 
Domain layer contains only domain models, so it's better to use koltin data classes here. Data classes bring a possibility to change model structure faster.

## Repository layer
- OkHttp + Retrofit as "de facto" standard libraries to make server calls
- Kotlin serialization for serialization/deserealization, and Jake Wharton json convertor factory to connect it to retrofit
- Kotlin coroutines for making asynchronous calls
- In-memory caching is implemented for economy network traffic and shrink accumulator usage. In current version it helps to load data faster.
- RepositoryFabric class is a custom Simple Dependecy Injector to hide repository realizations under interfaces. We don't have to change anything in presentation or domain layer, if we want to change caching method, combine models on server or something else.
- LiveData for asynchronous data source for presentation layer

## Presentation layer
- Splash is implemented to make "cold launch" more responsible by using splash theme for activity. Also it's a good place to make initial server calls, initialize database and so on (for future)
- MainActivity has starter design pattern to simplify its launching from Splash screen
- CharacterListFragment is a main fragment with content. It handles searchview, character list (search results), and character info fragment as a child fragment
- ViewModel architecture component is implemented to handle activity and fragment lifecycle stable
- Constraint layout is implemented to have a posibility to design effective complex layout
- Recycler view is implemented to show lists as a fast solution to use viewholder design pattern

## Things to improve
If I could invest more time in the project, I'd improve:
- Implement paging, now it works only with 10 first character on the list response
- Separate CharacterRepository class to different repositories for each type of data to have more flexible repository design
- Use cache for mapped data (domain models) not for Response models, another way to improve caching is adding OkHttp local cache to work when network is offline
- Handle Loading states - EMPTY, LOADING, LOADED, ERROR for each layout
- Make CharacterFragment as BottomSheetDialog cause it should be better to work with
- Add JUnit (test different logic parts), and Instrumentation test (e.g. mocking server request and test mapping)
- Separate mappers to Mapper classes
- Alternative way to work with network source is to implement local database (e.g. Room) and make init database while compiling APK, then updates it when app is launching
