"""Audio downloader for user-owned or otherwise authorized audio URLs.

Downloads the best available audio-only stream supported by yt-dlp. It does not
convert formats: conversion would require bundling FFmpeg separately.
"""
import json
import os
from urllib.parse import urlparse

from yt_dlp import YoutubeDL


def download_audio(url, output_dir):
    parsed = urlparse((url or "").strip())
    if parsed.scheme not in ("http", "https") or not parsed.hostname:
        raise ValueError("Enter a valid HTTPS audio URL.")

    host = parsed.hostname.lower()
    allowed = (
        host == "youtube.com"
        or host.endswith(".youtube.com")
        or host == "youtu.be"
        or host == "soundcloud.com"
        or host.endswith(".soundcloud.com")
    )
    if not allowed:
        raise ValueError("This downloader currently accepts YouTube and SoundCloud links only.")

    os.makedirs(output_dir, exist_ok=True)
    options = {
        "format": "bestaudio[ext=m4a]/bestaudio/best",
        "outtmpl": os.path.join(output_dir, "%(title).80B-%(id)s.%(ext)s"),
        "noplaylist": True,
        "quiet": True,
        "no_warnings": True,
        "ignoreerrors": False,
        "overwrites": False,
        "retries": 2,
        "fragment_retries": 2,
    }

    with YoutubeDL(options) as ydl:
        info = ydl.extract_info(url, download=True)
        if not info or info.get("_type") == "playlist":
            raise RuntimeError("The link did not resolve to one audio track.")

        candidates = info.get("requested_downloads") or []
        file_path = candidates[0].get("filepath") if candidates else None
        if not file_path:
            file_path = ydl.prepare_filename(info)

    if not file_path or not os.path.isfile(file_path) or os.path.getsize(file_path) == 0:
        raise RuntimeError("The source did not provide a downloadable audio file.")

    return json.dumps({
        "ok": True,
        "title": info.get("title") or "Downloaded audio",
        "fileName": os.path.basename(file_path),
        "size": os.path.getsize(file_path),
    })
