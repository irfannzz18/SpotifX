# Snuffy / SpotifX

A reconstructed music-player app project. The current build restores the previously shared 309-line HTML music-player interface from the conversation archive and hosts it in an Android WebView. It also includes the remembered native Android/Python audio-download architecture.

## Project components

- Android app: Kotlin activity, WebView host and JavaScript bridge.
- Python runtime: Chaquopy with Python 3.11.
- Audio extraction/search: yt-dlp, configured for audio-only streams.
- Offline playback: downloaded tracks are stored in app-private storage and played through Android MediaPlayer.
- Web player: recovered HTML interface with search, track cards, queue controls, shuffle, repeat, progress and volume controls, plus Jamendo catalog search.
- App icon: custom music-wave symbol reconstructed from the launcher icon visible in the supplied APK.
- Windows wrapper: Electron desktop shell reusing the HTML player and custom app icon.

## Build outputs

GitHub Actions:
- **Build Android APK** creates the Android debug APK artifact.
- **Build Windows EXE** creates the Windows installer artifact.

Open the repository's Actions tab and download the artifact from a successful run.

## Audio and copyright

The web catalog currently queries Jamendo. The native yt-dlp downloader accepts supported YouTube and SoundCloud URLs for audio-only extraction. Only download material you own or are authorized to save, and comply with each service's terms and applicable law. Site changes, authentication, regional restrictions or anti-bot measures can stop downloads.

No commercial music library is bundled. MP3 conversion is not included; the source audio format is retained, so playback compatibility depends on the downloaded format and the Android device.

## Recovery and accuracy note

This repository now contains the recovered prior HTML player file, reconstructed Kotlin/Chaquopy/yt-dlp integration, build workflows and custom logo resource. It is a substantial reconstruction, **not a byte-for-byte restoration of the original APK or every deleted source file**. A compiled APK does not preserve the original editable project structure. Some screens and integrations may still differ from the historical app and need comparison against screenshots or the original APK running on a device.

## Toolchain

- Android Gradle Plugin 8.7.3
- Kotlin 2.0.21
- Chaquopy 17.2.0
- Python 3.11
- yt-dlp 2025.10.22
- Java 17
