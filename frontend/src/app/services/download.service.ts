import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DownloadRequest, DownloadResponse } from '../models/download.model';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {
  private apiUrl = 'http://localhost:8080/api/download';

  constructor(private http: HttpClient) { }

  /**
   * Send a download request to the backend
   * @param request The download request containing URL and options
   * @returns Observable of DownloadResponse
   */
  downloadMedia(request: DownloadRequest): Observable<DownloadResponse> {
    return this.http.post<DownloadResponse>(this.apiUrl, request);
  }

  /**
   * Get the download URL for a file
   * @param fileName The name of the file to download
   * @returns The full URL to download the file
   */
  getDownloadUrl(fileName: string): string {
    return `${this.apiUrl}/file/${fileName}`;
  }
}