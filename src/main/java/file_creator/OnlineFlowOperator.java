package file_creator;

import com.google.gson.Gson;
import file_creator.user.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OnlineFlowOperator {

    public static String getUserDataJsonString() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://randomuser.me/api/?inc=gender,name,location,dob,nat")
                .get()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static User getUserDataFromWebApi(String jsonUser) {
        Gson gson = new Gson();
        try
        {
            User user = gson.fromJson(jsonUser
                    .substring(jsonUser.indexOf("[") + 1, jsonUser.indexOf("]")), User.class);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
