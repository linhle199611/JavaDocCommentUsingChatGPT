import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class ChatGPTClient {

    private static final String OPENAI_API_KEY = "your_api_key_here";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/your-endpoint-here";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        // Define the request body as a JSON object
        JSONObject requestBody = new JSONObject()
                .put("prompt", "Translate the following English text to French: 'Hello, how are you?'")
                .put("max_tokens", 50); // Adjust max tokens as needed

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());

        Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .post(body)
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + response);
                }

                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);

                String generatedText = jsonResponse.getString("choices");
                System.out.println("Generated Text: " + generatedText);
            }
        });
    }
}
