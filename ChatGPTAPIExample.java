import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTAPIExample {

   public static String chatGPT(String prompt) {
       String url = "https://api.openai.com/v1/chat/completions";

       String apiKey = "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"; 
       
       //String apiKey = "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";

       String model = "gpt-3.5-turbo";

       try {
           URL obj = new URL(url);
           HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
           connection.setRequestMethod("POST");
           connection.setRequestProperty("Authorization", "Bearer " + apiKey);
           connection.setRequestProperty("Content-Type", "application/json");

           // The request body
           String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
           connection.setDoOutput(true);
           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
           writer.write(body);
           writer.flush();
           writer.close();

           // Response from ChatGPT
           BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           String line;

           StringBuffer response = new StringBuffer();

           while ((line = br.readLine()) != null) {
               response.append(line);
           }
           br.close();

           // calls the method to extract the message.
           return extractMessageFromJSONResponse(response.toString());

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

   public static String extractMessageFromJSONResponse(String response) {
       int start = response.indexOf("content")+ 11;

       int end = response.indexOf("\"", start);

       return response.substring(start, end);

   }

   public static void main(String[] args) {
        String filePath = "sum.txt";
        String sample = readFile.read(filePath);

        System.out.println(chatGPT(sample));

        //System.out.println(chatGPT("Can you tell me what's a Fibonacci Number?"));

   }
}

/*   curl https://api.openai.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer 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" \
  -d '{
     "model": "gpt-3.5-turbo",
     "messages": [{"role": "user", "content": "Say this is a test!"}],
     "temperature": 0.7
   }'
*/

// Github token: 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