package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

public final class JsonUtils {


    private final static String SIMPLE_NAME = JsonUtils.class.getSimpleName();
    private final static String DETAIL_NAME = "name";
    private final static String DETAIL_MAIN_NAME = "mainName";
    private final static String DETAIL_ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String DETAIL_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String DETAIL_DESCRIPTION = "description";
    private final static String DETAIL_IMAGE = "image";
    private final static String DETAIL_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject mainJsonObject = new JSONObject(json);

            JSONObject name = mainJsonObject.getJSONObject(DETAIL_NAME);
            String mainName = name.getString(DETAIL_MAIN_NAME);

            JSONArray JSONArrayAlsoKnownAs = name.getJSONArray(DETAIL_ALSO_KNOWN_AS);
            List<String> alsoKnownAs = convertToListFromJsonArray(JSONArrayAlsoKnownAs);

            String placeOfOrigin = mainJsonObject.getString(DETAIL_PLACE_OF_ORIGIN);

            String description = mainJsonObject.getString(DETAIL_DESCRIPTION);

            String image = mainJsonObject.getString(DETAIL_IMAGE);

            JSONArray JSONArrayIngredients = mainJsonObject.getJSONArray(DETAIL_INGREDIENTS);
            List<String> ingredients = convertToListFromJsonArray(JSONArrayIngredients);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;

    }

}
    private static List<String> convertToListFromJsonArray(JSONArray jsonArray) throws JSONException{;
    List<String> list = new ArrayList<>(jsonArray.length());

    for (int i = 0; i< jsonArray.length(); i++) {
        list.add(jsonArray.getString(i));
    }
        return list ;


    }
}


