package com.downloader.mediadownloader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a download response to the client
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadResponse {
    
    private String fileName;
    private String fileUrl;
    private String fileType;
    private long fileSize;
    private String status;
    private String message;
    
    // For tracking download progress
    private int progressPercentage;
}