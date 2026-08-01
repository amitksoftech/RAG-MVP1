package com.ai.applications.rag.ragmvp1.config;

import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmbeddingConfiguration {

    @Bean
    EmbeddingModel embeddingModel() {
        return new EmbeddingModel() {
            @Override
            public EmbeddingResponse call(EmbeddingRequest request) {
                List<float[]> vectors = request.getInstructions().stream()
                        .map(this::simpleHashEmbedding)
                        .toList();
                List<Embedding> embeddings = vectors.stream()
                        .map(vector -> new Embedding(vector, 0))
                        .toList();
                return new EmbeddingResponse(embeddings);
            }

            @Override
            public float[] embed(String text) {
                return simpleHashEmbedding(text);
            }

            @Override
            public float[] embed(org.springframework.ai.document.Document document) {
                return simpleHashEmbedding(document.getText());
            }

            @Override
            public int dimensions() {
                return 8;
            }

            private float[] simpleHashEmbedding(String text) {
                float[] vector = new float[8];
                for (int i = 0; i < text.length(); i++) {
                    int value = text.charAt(i);
                    int index = i % vector.length;
                    vector[index] += (float) value / 31.0f;
                }
                return vector;
            }
        };
    }
}
