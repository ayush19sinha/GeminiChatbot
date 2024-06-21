# Gemini ChatBot

Gemini ChatBot is an Android application built with Jetpack Compose for creating a chat interface with a chatbot. Users can interact with the chatbot by sending messages and receiving responses in real-time.

## Features

- Real-time chat interface using Jetpack Compose
- Sends messages to a Gemini API to fetch responses
- Displays incoming and outgoing messages with different UI styles
- Allows users to input text and send messages to the chatbot

## Screenshots

<img src = "https://github.com/ayush19sinha/GeminiChatbot/assets/143383811/f9441891-ec79-4c54-964b-defc9ce97513" width = "180">
<img src = "https://github.com/ayush19sinha/GeminiChatbot/assets/143383811/67e8a00f-b253-430c-8b42-4f0aa09443f3" width = "180">
<img src = "https://github.com/ayush19sinha/GeminiChatbot/assets/143383811/f5d76cef-fcab-47fa-a9fd-eebb528ca372" width = "180">

## Getting Started

To get started with Gemini ChatBot, follow these steps:

1. **Clone the repository:**

    ```
    git clone https://github.com/your_username/geminichatbot.git
    ```

2. **Open the project in Android Studio:**

    Open Android Studio and select `Open an existing Android Studio project`, then navigate to the cloned repository and select it.

3. **Add your Gemini API key:**

    Replace `YOUR_GEMINI_API_KEY` with your actual Gemini API key in the `Constants.kt` file located at `app/src/main/java/my/android/geminichatbot/Utils/Constants.kt`.

    ```kotlin
    const val GEMINI_API_KEY = "YOUR_GEMINI_API_KEY"
    ```

4. **Build and Run:**

    Build and run the application on an Android device or emulator.

## Dependencies

- Jetpack Compose: Modern toolkit for building native UIs for Android.
- Material Design Components: For UI elements and design.
- Retrofit: Type-safe HTTP client for Android to make API calls.
- Gson: Library for converting Java objects to JSON and vice-versa.

## API Reference

- [Gemini API](https://example.com/gemini-api) - Provides responses based on user input.

## Contributing

Contributions to Gemini ChatBot are welcome! Feel free to open issues or pull requests for any improvements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
