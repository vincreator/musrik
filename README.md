# Musrik

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)

Musrik is an open-source music player application for Android, built using Kotlin and Jetpack Compose. Designed to provide a simple and intuitive music playback experience, with support for various audio formats and modern UI components.

## Features

- **Music Playback**: Play music from your device's storage.
- **Modern UI**: Built with Jetpack Compose for a responsive and sleek interface.
- **Audio Format Support**: Supports multiple audio formats via ExoPlayer.
- **Seamless Navigation**: Smooth navigation between screens using Navigation Compose.
- **Album Art Display**: Displays album artwork using Coil for image loading.
- **Permissions Handling**: Requests necessary permissions for accessing media files.
- **Customizable Themes**: Supports light and dark themes (if implemented).

## Screenshots

![Screenshot 1](screenshots/screenshot1.png)
![Screenshot 2](screenshots/screenshot2.png)

## Requirements

- Android API 26 (Android 8.0) or higher
- Kotlin 1.9+
- Gradle 8.0+
- Android Studio Arctic Fox or later

## Installation

### From Source Code

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/vincreator/musrik.git
   cd musrik
   ```

2. **Open in Android Studio**:
   - Launch Android Studio.
   - Select "Open an existing Android Studio project".
   - Navigate to the `musrik` folder and select it.

3. **Build and Run**:
   - Sync the project with Gradle files.
   - Run the app on an emulator or physical device.

### From APK

- Download the latest APK from [Releases](https://github.com/vincreator/musrik/releases).
- Install the APK on your Android device.

## Usage

1. Grant storage permissions when first launching the app.
2. Browse and select music from the available track list.
3. Use playback controls to play, pause, skip to next, or previous tracks.
4. Enjoy your music with a clean and intuitive interface.

## Development

### Building the Project

To build the project locally:

```bash
./gradlew assembleDebug
```

For release build:

```bash
./gradlew assembleRelease
```

### Running Tests

Run unit tests:

```bash
./gradlew test
```

Run instrumentation tests:

```bash
./gradlew connectedAndroidTest
```

### Code Style

This project follows Kotlin coding conventions. Use ktlint for code formatting:

```bash
./gradlew ktlintCheck
./gradlew ktlintFormat
```

## Contributing

We welcome contributions from the community! To contribute:

1. **Fork the Repository**: Fork this repository to your GitHub account.

2. **Create a Feature Branch**: Create a new branch for your feature or fix.
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make Changes**: Implement your changes.

4. **Commit Changes**: Commit with a clear message.
   ```bash
   git commit -m "Add feature: brief description"
   ```

5. **Push to Branch**: Push the branch to your repository.
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**: Open a Pull Request to the `main` branch of the main repository.

### Contribution Guidelines

- Follow standard Kotlin code style.
- Add comments to complex code sections.
- Ensure the app builds without errors.
- Add unit tests where applicable.
- Update documentation if necessary.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- [ExoPlayer](https://github.com/google/ExoPlayer) for audio playback.
- [Coil](https://github.com/coil-kt/coil) for image loading.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI framework.

## Contact

- **Developer**: Vincreator
- **Email**: ovinc@pm.me
- **GitHub**: [https://github.com/vincreator/musrik](https://github.com/vincreator/musrik)
- **Issues**: [https://github.com/vincreator/musrik/issues](https://github.com/vincreator/musrik/issues)

---

Thank you for using Musrik! If you have questions or suggestions, feel free to open an issue on GitHub.