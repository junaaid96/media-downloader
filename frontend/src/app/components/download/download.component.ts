import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DownloadService } from '../../services/download.service';
import { DownloadRequest, DownloadResponse } from '../../models/download.model';

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrls: ['./download.component.scss']
})
export class DownloadComponent implements OnInit {
  downloadForm: FormGroup;
  isLoading = false;
  downloadResponse: DownloadResponse | null = null;
  errorMessage = '';
  downloadHistory: DownloadResponse[] = [];
  
  // Format options
  formatOptions = [
    { value: '', label: 'Auto detect' },
    { value: 'mp4', label: 'MP4 Video' },
    { value: 'mp3', label: 'MP3 Audio' },
    { value: 'jpg', label: 'JPEG Image' },
    { value: 'png', label: 'PNG Image' }
  ];
  
  // Quality options
  qualityOptions = [
    { value: '', label: 'Auto' },
    { value: 'high', label: 'High' },
    { value: 'medium', label: 'Medium' },
    { value: 'low', label: 'Low' }
  ];

  constructor(
    private fb: FormBuilder,
    private downloadService: DownloadService
  ) {
    this.downloadForm = this.fb.group({
      url: ['', [Validators.required, Validators.pattern('https?://.+')]],
      format: [''],
      quality: ['']
    });
  }

  ngOnInit(): void {
    // Load download history from local storage if available
    const savedHistory = localStorage.getItem('downloadHistory');
    if (savedHistory) {
      this.downloadHistory = JSON.parse(savedHistory);
    }
  }

  onSubmit(): void {
    if (this.downloadForm.invalid) {
      return;
    }
    
    this.isLoading = true;
    this.errorMessage = '';
    this.downloadResponse = null;
    
    const request: DownloadRequest = this.downloadForm.value;
    
    this.downloadService.downloadMedia(request).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.downloadResponse = response;
        
        if (response.status === 'SUCCESS') {
          // Add to download history
          this.downloadHistory.unshift(response);
          // Keep only the last 10 downloads in history
          if (this.downloadHistory.length > 10) {
            this.downloadHistory = this.downloadHistory.slice(0, 10);
          }
          // Save to local storage
          localStorage.setItem('downloadHistory', JSON.stringify(this.downloadHistory));
        } else {
          this.errorMessage = response.message;
        }
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = 'Failed to connect to the server. Please try again later.';
        console.error('Download error:', error);
      }
    });
  }

  getDownloadUrl(fileName: string): string {
    return this.downloadService.getDownloadUrl(fileName);
  }

  formatFileSize(bytes: number | undefined): string {
    if (!bytes) return '0 Bytes';
    
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
  }

  clearForm(): void {
    this.downloadForm.reset({
      url: '',
      format: '',
      quality: ''
    });
    this.downloadResponse = null;
    this.errorMessage = '';
  }
}