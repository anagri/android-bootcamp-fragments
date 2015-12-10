package co.creativev.gotapp;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GoTService {
    @GET("/got_characters/{id}.json")
    public GoTCharacter getCharacter(@Path("id") Integer id);
}
