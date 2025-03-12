export interface DownloadRequest {
    url: string;
    format?: string;
    quality?: string;
}

export interface DownloadResponse {
    fileName?: string;
    fileUrl?: string;
    fileType?: string;
    fileSize?: number;
    status: string;
    message: string;
    progressPercentage?: number;
}