# Media Downloader Backend

A Spring Boot application that provides APIs for downloading media content from various websites.

## Overview

This backend service allows users to download videos, audio, and images from different sources by providing a URL. It handles the download process, file management, and provides appropriate responses to the frontend application.

## Features

-   Download media from various websites
-   Support for different file formats (mp4, mp3, jpg, png, etc.)
-   Quality selection options
-   File management and storage
-   RESTful API for frontend integration

## Prerequisites

-   Java 11 or higher
-   Maven 3.6 or higher
-   Spring Boot 2.7.x

## Getting Started

### Installation

1. Clone the repository
2. Navigate to the backend directory
3. Build the project using Maven:

```bash
mvn clean install
```

### Running the Application

You can run the application using the following command:

```bash
mvn spring-boot:run
```

The server will start on port 8080 by default.

## Configuration

The application can be configured through the `application.properties` file located in `src/main/resources/`.

Key configuration properties:

```properties
# Server configuration
server.port=8080

# Download directory configuration
download.directory=downloads
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

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── downloader/
│   │           └── mediadownloader/
│   │               ├── controller/  # REST controllers
│   │               ├── model/       # Data models
│   │               ├── service/     # Business logic
│   │               └── MediaDownloaderApplication.java
│   └── resources/
│       └── application.properties   # Application configuration
```

## Dependencies

The project uses the following main dependencies:

-   Spring Boot Web: For creating RESTful APIs
-   Apache HttpClient: For handling HTTP connections
-   JSoup: For parsing HTML content
-   Jackson: For JSON processing
-   Spring Boot Validation: For request validation

All dependencies are managed through Maven in the `pom.xml` file.

## Development

### Adding New Features

To add new features or modify existing ones:

1. Create or modify the appropriate model classes in the `model` package
2. Implement or update the business logic in the `service` package
3. Expose the functionality through controllers in the `controller` package
