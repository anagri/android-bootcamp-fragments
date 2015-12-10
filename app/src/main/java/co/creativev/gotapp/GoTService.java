package co.creativev.gotapp;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GoTService {
    @GET("/got_characters/{id}.json")
    public GoTCharacter getCharacter(@Path("id") Integer id);

    @GET("/got_characters.json")
    public List<GoTCharacter> getCharacters(@Query("page") Integer page);
}
