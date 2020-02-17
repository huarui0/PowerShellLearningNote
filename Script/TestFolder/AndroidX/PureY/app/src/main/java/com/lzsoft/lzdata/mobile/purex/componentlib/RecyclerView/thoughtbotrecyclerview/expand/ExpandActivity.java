package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.expand;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lzsoft.lzdata.mobile.purex.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeBluegrassGenre;
import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeClassicGenre;
import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeGenres;
import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeJazzGenre;
import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeRockGenre;
import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.thoughtbotrecyclerview.GenreDataFactory.makeSalsaGenre;

public class ExpandActivity extends AppCompatActivity {

  public GenreAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thoughtbot_recyclerview_expand);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
    // Specifically when you call notifyItemChanged() it does a fade animation for the changing
    // of the data in the ViewHolder. If you would like to disable this you can use the following:
/*
    RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
    if (animator instanceof DefaultItemAnimator) {
      ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
    }
*/
    adapter = new GenreAdapter(makeGenres());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    adapter.toggleGroup(makeRockGenre());
    adapter.toggleGroup(makeJazzGenre());
    adapter.toggleGroup(makeClassicGenre());

    Button clearAll = (Button) findViewById(R.id.toggle_all_button);
    clearAll.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
          toggleAllGroup();
      }
    });

    Button clear = (Button) findViewById(R.id.toggle_button);
    clear.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.toggleGroup(makeClassicGenre());
      }
    });

  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    adapter.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    adapter.onRestoreInstanceState(savedInstanceState);
  }

  protected void toggleAllGroup() {
      // 这里要一个 for 循环 取数据。 //如何都是收拢，折展开，
      // 是不是要判断 多和少 ? 往多的靠？
      adapter.toggleGroup(makeRockGenre());
      adapter.toggleGroup(makeJazzGenre());
      adapter.toggleGroup(makeClassicGenre());
      adapter.toggleGroup(makeSalsaGenre());
      adapter.toggleGroup(makeBluegrassGenre());
  }
}
