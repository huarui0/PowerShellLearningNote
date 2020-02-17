package com.example.inazaruk.navigationsample;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.inazaruk.shared.ui.actionscontentview.ActionsContentView;
import com.lzsoft.lzdata.mobile.purex.R;

public class NavigationSampleActivity extends FragmentActivity {

  private ActionsContentView viewActionsContentView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inazaruk_simplenavigation);

    viewActionsContentView = (ActionsContentView) findViewById(R.id.actionsContentView);

    final ListView viewActionsList = (ListView) findViewById(R.id.actions);

    final String[] values = new String[] { "Home", "About", "Settings" };
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, android.R.id.text1, values);

    viewActionsList.setAdapter(adapter);
    viewActionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapter, View v, int position,
          long flags) {
        showFragment(position);
      }
    });

    showFragment(0);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_inazaruk_simplenavigation, menu);
    return true;
  }

  private void showFragment(int position) {
    final Fragment f;
    switch (position) {
    case 0:
      f = new HomeFragment();
      break;
    case 1:
      f = new AboutFragment();
      break;
    case 2:
      f = new SettingsFragment();
      break;

    default:
      return;
    }
    getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();

    viewActionsContentView.showContent();
  }
}
