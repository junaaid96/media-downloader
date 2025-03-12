package com.downloader.mediadownloader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Model class representing a download request from the client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequest {
    
    @NotBlank(message = "URL cannot be empty")
    private String url;
    
    private String format; // Optional format for conversion (e.g., mp3, mp4)
    
    private String quality; // Optional quality setting (e.g., high, medium, low)
}