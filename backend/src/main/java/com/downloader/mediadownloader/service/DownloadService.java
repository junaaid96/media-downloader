package com.downloader.mediadownloader.service;

import com.downloader.mediadownloader.model.DownloadRequest;
import com.downloader.mediadownloader.model.DownloadResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Service for handling media downloads from various sources
 */
@Service
@Slf4j
public class DownloadService {

    @Value("${download.directory}")
    private String downloadDirectory;

    /**
     * Process a download request and return download information
     * 
     * @param request The download request containing URL and options
     * @return DownloadResponse with download details
     */
    public DownloadResponse downloadMedia(DownloadRequest request) {
        try {
            // Create download directory if it doesn't exist
            createDownloadDirectoryIfNotExists();
            
            // Extract information from URL
            URL url = new URL(request.getUrl());
            String host = url.getHost();
            String path = url.getPath();
            
            // Determine file type and name
            String fileName = determineFileName(path, host);
            String fileType = determineFileType(fileName, request.getFormat());
            
            // Download the file
            File downloadedFile = downloadFile(request.getUrl(), fileName);
            
            // Build and return response
            return DownloadResponse.builder()
                    .fileName(downloadedFile.getName())
                    .fileUrl("/downloads/" + downloadedFile.getName())
                    .fileType(fileType)
                    .fileSize(downloadedFile.length())
                    .status("SUCCESS")
                    .message("File downloaded successfully")
                    .progressPercentage(100)
                    .build();
            
        } catch (Exception e) {
            log.error("Error downloading media: {}", e.getMessage(), e);
            return DownloadResponse.builder()
                    .status("ERROR")
                    .message("Failed to download: " + e.getMessage())
                    .build();
        }
    }
    
    /**
     * Creates the download directory if it doesn't exist
     */
    private void createDownloadDirectoryIfNotExists() throws IOException {
        Path path = Paths.get(downloadDirectory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
    
    /**
     * Determines a suitable filename for the download
     */
    private String determineFileName(String path, String host) {
        // Extract filename from path if possible
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        
        // If no filename in path, generate one with UUID
        if (fileName.isEmpty() || !fileName.contains(".")) {
            fileName = host.replace(".", "_") + "_" + UUID.randomUUID().toString().substring(0, 8);
        }
        
        return fileName;
    }
    
    /**
     * Determines the file type based on filename and requested format
     */
    private String determineFileType(String fileName, String requestedFormat) {
        // If format is specified in request, use that
        if (requestedFormat != null && !requestedFormat.isEmpty()) {
            return requestedFormat;
        }
        
        // Otherwise extract from filename
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        
        // Default to binary if no extension found
        return "bin";
    }
    
    /**
     * Downloads a file from the given URL
     */
    private File downloadFile(String fileUrl, String fileName) throws IOException {
        File outputFile = new File(downloadDirectory, fileName);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(fileUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                try (InputStream inputStream = entity.getContent();
                     FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                    
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    long totalBytesRead = 0;
                    long contentLength = entity.getContentLength();
                    
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        
                        // Log progress for large files
                        if (contentLength > 0 && totalBytesRead % (contentLength / 10) < 8192) {
                            int progress = (int) (totalBytesRead * 100 / contentLength);
                            log.info("Download progress: {}%", progress);
                        }
                    }
                }
            }
        }
        
        return outputFile;
    }
}