
package edu.chl.leep.ctrl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.linneabark.test.R;
import com.example.linneabark.test.SaveActivityRowList;

import edu.chl.leep.model.Leep;
import edu.chl.leep.model.MainActivityModel;

import edu.chl.leep.service.SaveActivity;

import edu.chl.leep.model.Time;


public class MainActivity extends AppCompatActivity {
//TODO name to xCtrl, maybe


    //private AccountController account = new AccountController();

    public static Leep leep;
    private Context mContext;
    private SettingsController settings;

    MainActivityModel mainActivityModel;
    SaveActivityRowList saveActivityRowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        System.out.println("Context: " + mContext);
        mainActivityModel = new MainActivityModel();
        saveActivityRowList = new SaveActivityRowList();





        System.out.println("this.mContext: " + mContext);

        leep = new Leep();

        //if the value is 0 start login in again

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        changeFragment(R.id.timelog_id);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    private boolean changeFragment(int id){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment nextFrag = new Fragment();
        settings = new SettingsController();
        switch (item.getItemId()) {

        switch (id) {
            case R.id.settings_id:
                nextFrag = settings;
                break;
            case R.id.statistics_id:
                nextFrag = new Statistics();
                break;
            case R.id.timelog_id:
                nextFrag = new TimeLog();
                break;
            case R.id.account_id:

                System.out.println("Håller på att logga ut.");
                for(int i = 0; i < SaveActivity.activityRowList.size(); i++){
                    System.out.println("activityRowList ('the main list for activiys') innehåller: " + SaveActivity.activityRowList.get(i).getUserName() + " " + SaveActivity.activityRowList.get(i).getStartTime());
                }
                saveActivityRowList.saveActivityRowListSharedPref(mContext, SaveActivity.activityRowList);
                SaveActivity.activityRowList.clear();

                mainActivityModel.logOutUser(mContext);
                Intent MainToLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(MainToLogin);

        }
        transaction.add(R.id.fragment_container, nextFrag);
        transaction.commit();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return changeFragment(item.getItemId());
    }


    public void showPopUp(View v){
        settings.choosePopUp(v);
    }

    public Context getContext(){
        return mContext;
    }


}
