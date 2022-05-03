package Interface;

import java.util.List;

import Beans.Photos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderApi {

    @GET("photos")
    Call<List<Photos>> getPhotos();




}
