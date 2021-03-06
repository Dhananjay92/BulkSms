package thedhakadigital.digibulk.retrofit;

import thedhakadigital.digibulk.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by y34h1a on 3/16/17.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("addnumber.php")
    Call<ServerResponse> sendnumber(
            @Field("numbers") String numbers
    );
}
