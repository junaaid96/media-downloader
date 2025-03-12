# Media Downloader Frontend

An Angular-based frontend application for downloading media content from various websites.

## Overview

This frontend application provides a user-friendly interface for downloading videos, audio, and images from different sources. It communicates with the backend API to process download requests and display results to users.

## Features

- Clean, responsive user interface
- Support for downloading various media types
- Format selection options (MP4, MP3, JPG, PNG)
- Quality selection options (High, Medium, Low)
- Download history tracking
- File size formatting

## Prerequisites

- Node.js 14.x or higher
- npm 6.x or higher
- Angular CLI 15.x

## Getting Started

### Installation

1. Clone the repository
2. Navigate to the frontend directory
3. Install dependencies:

```bash
npm install
```

### Running the Application

Run the development server:

```bash
npm start
```

Or use Angular CLI:

```bash
ng serve
```

Navigate to `http://localhost:4200/` in your browser. The application will automatically reload if you change any of the source files.

## Project Structure

```
src/
├── app/
│   ├── components/       # UI components
│   │   └── download/     # Download component
│   ├── models/           # Data models
│   ├── services/         # API services
│   └── app.module.ts     # Main module
├── assets/              # Static assets
└── environments/        # Environment configurations
```

## Key Components

### Download Component

The main component that handles the download form, processes user input, and displays download results.

### Download Service

Service that communicates with the backend API to process download requests.

### Download Models

Data models for download requests and responses.

## Building for Production

Run `ng build` to build the project for production. The build artifacts will be stored in the `dist/` directory.

```bash
ng build --prod
```

## Development

### Code Scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Running Unit Tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

```bash
ng test
```

## Integration with Backend

The frontend communicates with the backend API at `http://localhost:8080/api/download` by default. This can be configured in the environment files.
