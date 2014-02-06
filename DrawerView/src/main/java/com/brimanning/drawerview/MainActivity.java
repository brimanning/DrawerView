package com.brimanning.drawerview;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import com.brimanning.drawerview.ui.DrawerItem;
import com.brimanning.drawerview.ui.DrawerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private List<DrawerItem> pages;
    private Fragment currentFragment;
    private int currentSection = 0;

    public static String SECTION_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            public void onDrawerClosed(View view) { }

            public void onDrawerOpened(View drawerView) { }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setHomeButtonEnabled(true);

        populateDrawer();
        drawerList.setAdapter(new DrawerListAdapter(this, R.layout.drawer_item, pages));

        //use this to pass a specific section into the activity to be the initial fragment loaded
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            currentSection = extras.getInt(SECTION_NAME, 0);

        selectSection(currentSection);

        drawerList.post(new Runnable() {
            @Override
            public void run() {
                updateHeader(currentSection);
            }
        });
    }

    private void populateDrawer() {
        pages = new ArrayList<DrawerItem>();
        pages.add(new DrawerItem(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher),
                getResources().getString(R.string.title_section1)));
        pages.add(new DrawerItem(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher),
                getResources().getString(R.string.title_section2)));
        pages.add(new DrawerItem(
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_launcher),
                getResources().getString(R.string.title_section3)));
    }

    public void selectSection(int position) {
        currentFragment = new MainFragment();
        currentSection = position;

        //here you can handle different fragment displays
        if (position == 1) {
            currentFragment = new MainFragment();
        } else if (position == 2) {
            currentFragment = new MainFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();

        // update selected item and title, then close the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);

        updateHeader(position);
    }

    private void updateHeader(int position) {
        String title = "";

        //if each section has a different title, handle it here
        if (position == 0) {
            title = getResources().getString(R.string.app_name);
        } else if (position == 1) {
            title = getResources().getString(R.string.title_section2);
        } else if (position == 2) {
            title = getResources().getString(R.string.title_section3);
        }

        getActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
