# FoodiePal Culinary Companion

## Description
FoodiePal Culinary Companion is an Android application developed in Kotlin and Java. It's a comprehensive guide for food lovers, providing features like Recipes, Meal Planner, Blog, Contact, and About Me sections.

## Prerequisites
Before you begin, ensure you have met the following requirements:
* You have installed the latest version of [Android Studio](https://developer.android.com/studio)

# FoodiePal Culinary Companion

## Description
FoodiePal Culinary Companion is an Android application developed in Kotlin and Java. It's a comprehensive guide for food lovers, providing features like Recipes, Meal Planner, Blog, Contact, and About Me sections.

## Prerequisites
Before you begin, ensure you have met the following requirements:
* You have installed the latest version of [Android Studio](https://developer.android.com/studio)

## Framework and Tools
The application is developed using the following frameworks and tools:
- **Kotlin**: The primary programming language used for developing the application.
- **Java**: Used in some parts of the application for compatibility and legacy code.
- **Android Studio**: The IDE used for developing the application.
- **Gradle**: The build automation tool used for managing dependencies and building the application.

## Database Details
The application currently does not use a database for data persistence. Instead, it uses local storage for storing data. Please note that since a database is not used, the local session clears every time the app is redeployed. This means that any data saved during a session will be lost once the app is uninstalled and installed again.

## Guidelines

### How to Run the App
1. Clone the repository to your local machine (branch - main).
2. Open the project in Android Studio.
3. Clean build the project by running the following command:
    ```bash
    ./gradlew clean build
    ```
4. Run the app using the built-in Android emulator or on your connected Android device.

## Requirements

### Recipes Feature
1. **Add Recipe**: This feature allows users to add a new recipe to the application. The user can input various details about the recipe, such as the recipe name, cook time, ingredients, food description, and rating. The user interface for this feature is defined in `activity_add_recipe.xml`.
2. **View Recipe**: This feature allows users to view the details of a recipe. The user interface for this feature would likely be a separate activity or fragment that displays the recipe details in a readable format.

### Meal Planner Feature
1. **Add Meal Plan**: This feature allows users to add a new meal plan to the application. The user can select a recipe from the existing list of recipes and assign it to a specific meal (breakfast, lunch, dinner) and a specific day of the week.
2. **View Meal Plan**: This feature allo

## Guidelines

### How to Run the App
1. Clone the repository to your local machine (branch - main).
2. Open the project in Android Studio.
3. Clean build the project by running the following command:
    ```bash
    ./gradlew clean build
    ```
4. Run the app using the built-in Android emulator or on your connected Android device.

## Requirements

### Recipes Feature
1. **Add Recipe**: This feature allows users to add a new recipe to the application. The user can input various details about the recipe, such as the recipe name, cook time, ingredients, food description, and rating. The user interface for this feature is defined in `activity_add_recipe.xml`.
2. **View Recipe**: This feature allows users to view the details of a recipe. The user interface for this feature would likely be a separate activity or fragment that displays the recipe details in a readable format.

### Meal Planner Feature
1. **Add Meal Plan**: This feature allows users to add a new meal plan to the application. The user can select a recipe from the existing list of recipes and assign it to a specific meal (breakfast, lunch, dinner) and a specific day of the week.
2. **View Meal Plan**: This feature allows users to view their planned meals for each day. The user interface for this feature would likely include a calendar or schedule view where the user can see their planned meals for each day.

### Blog Feature
1. **View Blog Post**: This feature allows users to view the details of a blog post. Each blog post would likely include a title, author, date of publication, and the blog content.
2. **Browse Blog Posts**: This feature allows users to browse the list of blog posts. The user interface for this feature would likely include a list or grid view of blog posts.

### Contact Feature
1. **Submit Contact Form**: This feature allows users to contact the developers or support team. Users can enter their name, email, and message in a form within the app. Once the form is filled out and submitted, the message would be sent to the developers or support team.

### About Me Section
1. **View About Me**: This feature allows users to view the "About Me" section. As suggested by the `AboutAdapter.kt` file, this section likely includes details such as the user's culinary journey, favorite recipes, and food philosophy.

### Android Compatibility
The application should be compatible with Android devices, as it is developed using Kotlin and Java in Android Studio.

### Build and Run
The application should be able to be built and run using Android Studio and Gradle, as described in the README's "How to Run the App" section. This involves cloning the repository, opening the project in Android Studio, building the project using Gradle, and running the app on an emulator or connected Android device.

## Use Case Diagram
![use-case-diagram.drawio.png](assets%2Fuse-case-diagram.drawio.png)
