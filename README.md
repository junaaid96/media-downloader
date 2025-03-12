# Media Downloader

A full-stack application for downloading media content from various websites, featuring a Spring Boot backend and Angular frontend.

## Overview

Media Downloader is a comprehensive solution for downloading videos, audio, and images from different online sources. The application consists of two main components:

- **Backend**: A Spring Boot application that handles the actual downloading, processing, and storage of media files
- **Frontend**: An Angular application that provides a user-friendly interface for submitting download requests and viewing results

## Features

- Download media from various websites
- Support for multiple file formats (MP4, MP3, JPG, PNG, etc.)
- Quality selection options (High, Medium, Low)
- Format selection options
- Clean, responsive user interface
- Download history tracking
- File management and storage
- RESTful API integration

## System Architecture

The application follows a client-server architecture:

```
┌─────────────┐      HTTP/REST      ┌─────────────┐
│   Angular   │<─────Requests─────>│  Spring Boot │
│  Frontend   │                    │   Backend    │
└─────────────┘                    └─────────────┘
      ↑                                   ↓
      │                              ┌─────────────┐
      └──────User Interaction────>│  Downloaded  │
                                    │    Files     │
                                    └─────────────┘
```

## Prerequisites

### Backend
- Java 11 or higher
- Maven 3.6 or higher
- Spring Boot 2.7.x

### Frontend
- Node.js 14.x or higher
- npm 6.x or higher
- Angular CLI 15.x

## Getting Started

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   The server will start on port 8080 by default.

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the development server:
   ```bash
   npm start
   ```
   Or use Angular CLI:
   ```bash
   ng serve
   ```
   Navigate to `http://localhost:4200/` in your browser.

## Project Structure

### Backend Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── downloader/
│   │   │           └── mediadownloader/
│   │   │               ├── controller/  # REST controllers
│   │   │               ├── model/       # Data models
│   │   │               ├── service/     # Business logic
│   │   │               └── MediaDownloaderApplication.java
│   │   └── resources/
│   │       └── application.properties   # Application configuration
│   └── test/                            # Unit and integration tests
└── pom.xml                              # Maven dependencies
```

### Frontend Structure

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/       # UI components
│   │   │   └── download/     # Download component
│   │   ├── models/           # Data models
│   │   ├── services/         # API services
│   │   └── app.module.ts     # Main module
│   ├── assets/              # Static assets
│   └── environments/        # Environment configurations
├── angular.json            # Angular configuration
└── package.json            # npm dependencies
```

## API Endpoints

### Download Media

```
POST /api/download
```

**Request Body:**

```json
{
    "url": "https://example.com/video.mp4",
    "format": "mp4", // Optional
    "quality": "high" // Optional
}
```

**Response:**

```json
{
    "fileName": "video.mp4",
    "fileUrl": "/downloads/video.mp4",
    "fileType": "mp4",
    "fileSize": 1048576,
    "status": "SUCCESS",
    "message": "File downloaded successfully",
    "progressPercentage": 100
}
```

## Configuration

### Backend Configuration

The backend application can be configured through the `application.properties` file located in `backend/src/main/resources/`.

Key configuration properties:

```properties
# Server configuration
server.port=8080

# Download directory configuration
download.directory=downloads
```

### Frontend Configuration

The frontend communicates with the backend API at `http://localhost:8080/api/download` by default. This can be configured in the environment files located in `frontend/src/environments/`.

## Development

### Backend Development

To add new features or modify existing ones:

1. Create or modify the appropriate model classes in the `model` package
2. Implement or update the business logic in the `service` package
3. Expose the functionality through controllers in the `controller` package

### Frontend Development

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).
