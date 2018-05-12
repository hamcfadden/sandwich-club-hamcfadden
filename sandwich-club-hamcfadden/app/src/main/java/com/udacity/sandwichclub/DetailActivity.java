package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {



    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mAlsoKnownTv;
    private TextView mAlsoKnownLabel;
    private TextView mOriginTv;
    private TextView mOriginLabel;
    private TextView mDescriptionTv;
    private TextView mDescriptionLabel;
    private TextView mIngredientsTv;
    private TextView mIngredientsLabel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image_iv = findViewById(R.id.image_iv);
        mAlsoKnownLabel = findViewById(R.id.also_known_label);
        mAlsoKnownTv = findViewById(R.id.also_known_tv);
        mOriginLabel = findViewById(R.id.origin_label);
        mOriginTv = findViewById(R.id.origin_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mDescriptionLabel = findViewById(R.id.description_label);
        mIngredientsTv = findViewById(R.id.ingredients_tv);
        mIngredientsLabel = findViewById(R.id.ingredients_label);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        assert intent != null;
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);


        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            mOriginTv.setVisibility(View.GONE);
            mOriginLabel.setVisibility(View.GONE);
        } else {
            mOriginTv.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            mAlsoKnownTv.setVisibility(View.GONE);
            mAlsoKnownLabel.setVisibility(View.GONE);
        } else {
            mAlsoKnownTv.setText(listModel(sandwich.getAlsoKnownAs()));

        }

        mDescriptionTv.setText(sandwich.getDescription());
        mIngredientsTv.setText(listModel(sandwich.getIngredients()));
    }
        //idea from jordiguzman github

        public StringBuilder listModel(List<String> list) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i)).append("\n");
            }
            return stringBuilder;
        }
}
