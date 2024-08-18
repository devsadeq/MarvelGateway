# Marvel App

## Overview

An Android app for exploring Marvel content with offline access and clean architecture using MVVM.

## Architecture

The app is built using clean architecture:

### 1. Data Layer
- **Remote API Service**: Utilizes Retrofit to fetch data from the Marvel API.
- **Local DAOs**: Manages Room database for caching purposes, allowing offline access to previously fetched data.
- **Data Sources**: Acts as an intermediary between the data layer and the repository, isolating external dependencies.

### 2. Adapting Layer (Repository)
- **Marvel Repository**: The single repository in the app, responsible for handling data operations by interacting with the data sources (both remote and local).

### 3. Domain Layer
- **Use Cases**: Contains the business logic of the app, which is executed through the use cases that interact with the repository.

### 4. UI Layer
- **MVVM with Jetpack Compose**: Implements the Model-View-ViewModel pattern to manage the UI. The UI components are built using Jetpack Compose, providing a modern and reactive approach to UI development.

## Screens

[Figma Desgin](#) _(https://www.figma.com/design/P5pxqm451RvxaJsLaetWGf/Marvel?node-id=1-10&t=A0LBpsuMM3NlcqBS-0)_

<p align="center">
  <img src="https://github.com/user-attachments/assets/1d327b80-de33-47ae-98ca-937f5ccc068a" alt="Home Screen" width="200"/>
  <img src="https://github.com/user-attachments/assets/6b879721-33e3-4c2b-a2bd-cd88b8485dd3" alt="View All Screen" width="200"/>
  <img src="https://github.com/user-attachments/assets/d1198f63-d1c0-45b9-9167-fc23b6d63790" alt="Search Screen" width="200"/>
  <img src="https://github.com/user-attachments/assets/aed93acb-6f13-43c9-9475-963f62f5cbe3" alt="Another Screen" width="200"/>
</p>

- **Home Screen**:
  - Displays banners and allows users to search for Marvel characters.
  - Shows a list of sections like Characters, Events, Series, Stories, Cartoons, and Comics.
  - Selecting a section navigates to the View All screen.

- **View All Screen**:
  - Displays a list of Marvel items for the selected section.
  - Data is cached locally, enabling offline access.

- **Search Screen**:
  - Allows users to search for Marvel characters by name.
  - Displays a list of suggestions based on the search query.
  - Selecting a suggestion and hitting the search button loads the character's details along with related comics, events, stories, and series.

## Libraries Used

- **Dagger-Hilt**: Dependency injection library to manage the app's dependencies efficiently.
- **KSP (Kotlin Symbol Processing)**: Used for annotation processing, mainly with Room for database handling.
- **Room**: Provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- **Lifecycle Runtime Compose**: Manages UI-related data in a lifecycle-conscious way, especially when using Jetpack Compose.
- **Compose Navigation**: Handles in-app navigation between screens using Jetpack Compose.
- **Serialization**: Kotlin library for JSON serialization and deserialization.
- **Coil**: Image loading library that supports asynchronous image loading and caching.

## Video Demo

[Watch the video demo](#) _(https://youtu.be/zo3poPNMvOo)_
