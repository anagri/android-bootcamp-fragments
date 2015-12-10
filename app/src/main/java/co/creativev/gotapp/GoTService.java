package co.creativev.gotapp;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GoTService {
    @GET("/got_characters/{id}.json")
    public GoTCharacter getCharacter(@Path("id") Integer id);

    @GET("/got_characters.json")
    public List<GoTCharacter> getCharacters(@Query("page") Integer page);

    @POST("/got_characters.json")
    public GoTCharacter newCharacter(@Body GoTCharacter goTCharacter);
}
