# VocalNote — Speech to Text Studio

A full-stack starter project for the Procucev transcription assignment.

## Stack
- Frontend: React + Vite
- Backend: Java 17, Spring Boot 3, Spring Web, Spring Data JPA, Bean Validation
- Database: MySQL 8+
- Transcription: browser Web Speech API (Chrome-based browsers)

## Important note about speech recognition
This version provides live/interim browser speech recognition when the browser supports `SpeechRecognition` / `webkitSpeechRecognition`. It is useful for a working MVP and demo, but it is **not a self-deployed open-source speech engine**, and browser support/language behavior varies. The assignment prefers a self-deployed open-source service. For a stricter submission, replace the browser recognition adapter with a streaming service such as [WhisperLive](https://github.com/collabora/WhisperLive), or integrate the [Gemini Live transcription API](https://ai.google.dev/gemini-api/docs/live-transcribe). Keep API credentials on the server and never commit secrets. This repo's CRUD backend and dashboard are independent of the speech provider.

## Features
- Start/stop microphone capture with interim transcript display
- English (India), English (US), and Hindi recognition language selection
- Manual transcript editing and save
- Create, read, update, and delete transcription history
- Search by title, text, or language
- MySQL persistence through Spring Data JPA
- Validation and API error responses

## Prerequisites
- JDK 17+
- Maven 3.8+
- Node.js 20+ (or a recent supported LTS)
- MySQL 8+

## 1. Create the database
Run this in MySQL:
```sql
CREATE DATABASE speech_to_text;
```
Default local configuration uses username `root` and password `root`. Update `backend/src/main/resources/application.properties` or set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables.

## 2. Run backend
From the `backend` directory:
```bash
mvn spring-boot:run
```
Backend URL: `http://localhost:8080`

## 3. Run frontend
In a second terminal:
```bash
cd frontend
npm install
npm run dev
```
Open the Vite URL, normally `http://localhost:5173`.

## REST API
| Method | Path | Description |
|---|---|---|
| GET | `/api/transcriptions` | List all records, newest first |
| GET | `/api/transcriptions/{id}` | Get one record |
| POST | `/api/transcriptions` | Create record |
| PUT | `/api/transcriptions/{id}` | Update record |
| DELETE | `/api/transcriptions/{id}` | Delete record |

Example create request:
```json
{
  "title": "Daily notes",
  "transcribedText": "Hello, नमस्ते. Today I am learning Spring Boot.",
  "language": "Hindi + English"
}
```

## Deployment outline
1. Provision a managed MySQL database and create `speech_to_text`.
2. Deploy the Spring Boot backend to a Java-compatible host. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and `PORT` environment variables.
3. Update CORS in `TranscriptionController` to allow your deployed frontend origin. Do not use wildcard origins in a production app.
4. Deploy `frontend` to Vercel/Netlify. Set `VITE_API_URL` to the deployed backend's base API URL ending in `/api`.
5. Ensure microphone access is served over HTTPS in production.
6. For production-grade real-time transcription and the assignment's self-hosted/open-source preference, integrate a streaming engine such as WhisperLive.

## Security and production checklist
- Do not commit passwords, API keys, or tokens.
- Restrict CORS to the deployed frontend origin.
- Add authentication if the app will hold private user transcripts.
- Use managed database credentials and backups.
- Check the speech provider's privacy and retention terms before sending real audio.

## Suggested GitHub description
"VocalNote is a full-stack speech-to-text application built with React, Spring Boot, and MySQL. It provides live browser speech recognition, editable transcripts, and a searchable CRUD history dashboard."
