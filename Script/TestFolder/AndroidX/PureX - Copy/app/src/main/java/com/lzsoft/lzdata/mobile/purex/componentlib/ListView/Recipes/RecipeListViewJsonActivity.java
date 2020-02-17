package com.lzsoft.lzdata.mobile.purex.componentlib.ListView.Recipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;

import java.util.ArrayList;

/**
 * Created by wanglai on 11/27/2016.
 */

public class RecipeListViewJsonActivity extends AppCompatActivity {
    private final String LOG_TAG = RecipeListViewJsonActivity.class.getSimpleName();

    private final boolean isRemoteData;

    public RecipeListViewJsonActivity() {
        isRemoteData = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_listview_json);

        ListView mListView = findViewById(R.id.recipe_list_view);

// 1
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        if (!isRemoteData) {

// 2
            String[] listItems = new String[recipeList.size()];
// 3
            for (int i = 0; i < recipeList.size(); i++) {
                Recipe recipe = recipeList.get(i);
                listItems[i] = recipe.title;
            }
// 4
            ArrayAdapter adapter_;
            adapter_ = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        } else {
            RecipeAdapter adapter = new RecipeAdapter(this, recipeList);

            mListView.setAdapter(adapter);
        }

        final Context context = this;
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            // 1
            Recipe selectedRecipe = recipeList.get(position);

            // 2
            Intent detailIntent = new Intent(context, RecipeDetailActivity.class);

            // 3
            detailIntent.putExtra("title", selectedRecipe.title);
            detailIntent.putExtra("url", selectedRecipe.instructionUrl);

            // 4
            startActivity(detailIntent);
        });
    }
}
