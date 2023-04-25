package org.example;

import okhttp3.*;
import java.io.IOException;

public class OpenAIClient {
    private final  OkHttpClient httpClient = new OkHttpClient();
    private final String apiKey;

    public OpenAIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCompletion(String prompt) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String url = "https://api.openai.com/v1/engines/davinci-codex/completions";

        String jsonBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }
}