# SpotifX

SpotifX is an Android music-player project with a lightweight local interface and an offline music library.

> **Recovery note:** This repository was recreated after its previous contents were deleted. This is a clean starter baseline, not a recovered copy of the earlier Snuffy/SpotifX source. The original UI and complete Musify-level feature set have not yet been restored.

## Current scope

- Android application shell
- Local HTML/CSS/JavaScript interface packaged inside the app
- Offline audio import from files selected by the user
- Local playback controls
- No public website deployment or GitHub Pages workflow

## Build

The Android APK workflow uses GitHub Actions and builds with Gradle. Open **Actions → Build Android APK** to see the build result and download the APK artifact when a run succeeds.

## Project layout

- `app/` — Android application
- `.github/workflows/` — Android APK build workflow

## Offline audio

Import audio files that you own or are permitted to save. The app stores imported files in app-private storage. Streaming search results are not automatically downloadable; availability and download rights depend on the source.

## Roadmap

- Restore the original Snuffy interface from a supplied backup or screenshots
- Improve offline library management and shuffle playback
- Add playback notification and background audio service
- Add Windows desktop packaging
- Add automated tests and device verification

## License

License has not yet been selected. Do not redistribute this project until its dependencies and licensing have been reviewed.
