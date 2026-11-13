# SpotifX

Android music-player project with a Kotlin host app, embedded HTML interface, local audio import, and Python-powered audio download support.

## Android stack
- Kotlin Android activity and JavaScript bridge
- Chaquopy 17.2.0 embedding Python 3.11
- yt-dlp 2025.10.22 for supported audio-source extraction
- Local private storage and native MediaPlayer playback for downloaded files
- Existing WebView interface and custom SpotifX music-wave icon

## Downloads and rights
The downloader accepts YouTube and SoundCloud URLs and requests an audio-only stream. Use it only for material you own or are explicitly authorized to download, and follow each service's terms and applicable copyright law. Authentication, region restrictions, anti-bot checks, and upstream site changes may prevent downloads. yt-dlp cannot guarantee that every URL works.

The source audio format is retained. MP3 conversion is not included because it requires a separately packaged FFmpeg binary. Downloaded audio is saved in the app's private files directory.

## Android build
Minimum Android API 24 (Chaquopy requirement); compile/target SDK 35; Java/Kotlin 17; Python 3.11; ABIs arm64-v8a, armeabi-v7a, x86_64.

GitHub Actions installs Python 3.11 and Java 17 before running the Gradle build. Open Actions → Build Android APK. A successful run uploads the SpotifX-debug-apk artifact.

Open Actions → Build Windows EXE for the Windows installer artifact SpotifX-Windows-EXE.

## Recovery status
This repository was recreated after the previous source contents were removed. It now includes the remembered Android/Kotlin/Chaquopy/yt-dlp architecture, offline download UI wiring, custom icon resource, and the current starter interface. It is not an exact restoration of every historical source file: the complete old source ZIP was not available in this workspace, and compiled APK code cannot be converted back into the exact original source project.

Review all dependency and asset licenses before distribution. No third-party music is bundled.
