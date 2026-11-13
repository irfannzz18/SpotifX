# SpotifX

SpotifX is an Android music-player project with a Kotlin host app, an embedded HTML interface, local audio import, and a Python-powered audio download module.

## Android stack

- Kotlin Android activity and JavaScript bridge
- Chaquopy 17.2.0 embedding Python 3.11
- yt-dlp 2025.10.22 for supported audio-source extraction
- Local audio storage under the app's private files directory
- Native MediaPlayer playback for downloaded files
- Existing WebView-based interface and offline file import
- Custom SpotifX music-wave app symbol (not Electron branding)

## Audio downloads and rights

The downloader accepts YouTube and SoundCloud URLs and requests an audio-only stream. Use it only for material you own or are explicitly authorized to download, and follow the source service's terms and applicable copyright law. Site changes, authentication, region restrictions, or anti-bot checks can prevent a download. yt-dlp does not guarantee that every URL will work.

The downloader keeps the source audio format. It does not convert to MP3 because that would require a separately packaged FFmpeg binary. Downloaded audio is stored privately inside the app and can be played through the Kotlin native player.

## Android compatibility

- Minimum Android API: 24 (required by current Chaquopy)
- Target/compile SDK: 35
- ABIs: arm64-v8a, armeabi-v7a, x86_64
- Java/Kotlin target: 17
- Python: 3.11

## Build

Open Actions → Build Android APK. A successful run uploads the SpotifX-debug-apk artifact.

Open Actions → Build Windows EXE for the Windows installer artifact SpotifX-Windows-EXE. The desktop wrapper reuses the HTML interface and the custom SpotifX icon.

## Recovery status

This repository was recreated after the previous source repository contents were removed. It now contains the remembered Android/Kotlin/Chaquopy/yt-dlp architecture and the current starter interface. It is not an exact restoration of every file from the old project: the original full source ZIP and all historical files were not available in this workspace. The original APK's compiled code cannot be converted back into the exact original source project.

## License

Review yt-dlp, Chaquopy, and all bundled asset licenses before distributing builds. No third-party music is bundled.
