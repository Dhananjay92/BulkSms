package thedhakadigital.digibulk.retrofit;

import android.util.Log;

import com.squareup.otto.Produce;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thedhakadigital.digibulk.model.ServerResponse;
import thedhakadigital.digibulk.model.ServerResponseEvent;
import thedhakadigital.digibulk.receiver.ErrorEvent;
import thedhakadigital.digibulk.utils.Constants;

/**
 * Created by y34h1a on 5/13/17.
 */

public class Communicator {
    public void sendNumbers(String numbers){
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        ApiService service = retrofit.create(ApiService.class);

        Call<ServerResponse> call = service.sendnumber(numbers);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                BusProvider.getInstance().post(new ServerResponseEvent(response.body()));
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
                Log.e("arif","FAiled");
            }
        });
    }

    @Produce
    public ServerResponseEvent produceServerResponseEvent(ServerResponse serverResponse){
        return new ServerResponseEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
