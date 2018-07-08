package com.example.ssmbu.gankki.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.ui.fragment.AboutFragment;
import com.example.ssmbu.gankki.ui.fragment.BrowseGankFragment;
import com.example.ssmbu.gankki.ui.fragment.CollectionsOfGanksFragment;
import com.example.ssmbu.gankki.ui.fragment.RandomGankFragment;
import com.example.ssmbu.gankki.ui.fragment.SearchGankFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String SELECT_POSITION = "selectPosition";
    private static final String[] toobarTitles={"干货","随便看看","搜索","收藏夹","关于"};
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;


    private FragmentManager fragmentManager;
    private List<Fragment> fragments=new ArrayList<>() ;
    private Fragment currentFragment;
    //private int mCurrentFragmentPosition;

    private int mCurrentNavPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCurrentNavPosition=0;
        fragmentManager=getSupportFragmentManager();

        if(savedInstanceState!=null){
            //restore fragment
            Log.d(TAG, "onCreate: restore");
            fragments.removeAll(fragments);

            fragments.add(fragmentManager.findFragmentByTag(0+""));
            fragments.add(fragmentManager.findFragmentByTag(1+""));
            fragments.add(fragmentManager.findFragmentByTag(2+""));
            fragments.add(fragmentManager.findFragmentByTag(3+""));
            fragments.add(fragmentManager.findFragmentByTag(4+""));
            Log.d(TAG, "onCreate: restore fragment");
            restoreFragment();
        }else {
            Log.d(TAG, "onCreate: add new");
            fragments.add(new BrowseGankFragment());
            fragments.add(new RandomGankFragment());
            fragments.add(new SearchGankFragment());
            fragments.add(new CollectionsOfGanksFragment());
            fragments.add(new AboutFragment());
            //testFragment();
            showFragment();
        }

        setSupportActionBar(mainToolbar);


        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
        outState.putInt(SELECT_POSITION, mCurrentNavPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
        //restore nav
        mCurrentNavPosition = savedInstanceState.getInt(SELECT_POSITION, 0);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(mCurrentNavPosition);
        menuItem.setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_BrowseGank:
                mCurrentNavPosition = 0;
                break;
            case R.id.nav_randomGank:
                mCurrentNavPosition = 1;
                break;
            case R.id.nav_searchGank:
                mCurrentNavPosition = 2;
                break;
            case R.id.nav_collections:
                mCurrentNavPosition = 3;
                break;
            case R.id.nav_about:
                mCurrentNavPosition=4;
                break;
        }
        item.setChecked(true);
        showFragment();
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }

    private void restoreFragment(){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        for(int i=0;i<fragments.size();i++){
            if(i==mCurrentNavPosition){
                transaction.show(fragments.get(i));
            }
            else {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.addToBackStack(null);
        transaction.commit();
        currentFragment=fragments.get(mCurrentNavPosition);
    }

    private void showFragment() {
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(!fragments.get(mCurrentNavPosition).isAdded()){
            if(currentFragment!=null) {
                transaction.hide(currentFragment);
            }
            //Log.d(TAG, "showFragment: add a fragment");
            transaction.add(R.id.frameLayout,fragments.get(mCurrentNavPosition),mCurrentNavPosition+"");
        }else {
            if(currentFragment!=null) {
                transaction.hide(currentFragment);
            }
            transaction.show(fragments.get(mCurrentNavPosition));
        }
        currentFragment=fragments.get(mCurrentNavPosition);
        transaction.commit();

        mainToolbar.setTitle(toobarTitles[mCurrentNavPosition]);
    }


}
