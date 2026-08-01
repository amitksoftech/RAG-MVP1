package com.ai.applications.rag.ragmvp1.models;

public record IngestionStatistics(
        long totalDocuments,
        long uploadedDocuments,
        long processingDocuments,
        long readyDocuments,
        long failedDocuments,
        long documentsUploadedToday,
        double averageChunksPerDocument,
        long totalChunksIndexed,
        long totalStorageBytes,
        long activeUsers
) {
}
