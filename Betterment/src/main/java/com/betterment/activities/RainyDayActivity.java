package com.betterment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.betterment.R;
import com.betterment.views.*;

/**
 * The activity for the Rainy Day screen.
 */
public class RainyDayActivity extends SherlockFragmentActivity {
    // Declare Tab Variable
    ActionBar.Tab mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainy_day);

        // Setup ActionBar
        ActionBar mActionBar = getSupportActionBar();
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setCustomView(R.layout.rainy_day_title);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Create Actionbar Tabs
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create Summary Tab
        mTab = mActionBar.newTab().setTabListener(new FragmentsSummaryTab());
        // Set custom icon
        //mTab.setIcon(R.drawable.ic_launcher);
        mTab.setText("Summary");
        mActionBar.addTab(mTab, 0, false);

        // Create Second Tab
        mTab = mActionBar.newTab().setTabListener(new FragmentsTransferTab());
        // Set custom icon
        //mTab.setIcon(R.drawable.ic_launcher);
        // Set Tab Title
        mTab.setText("Transfer");
        mActionBar.addTab(mTab, 1, false);

        // Create Third Tab
        mTab = mActionBar.newTab().setTabListener(new FragmentsAllocationTab());
        // Set custom icon
        //mTab.setIcon(R.drawable.ic_launcher);
        // Set Tab Title
        mTab.setText("Allocation");
        mActionBar.addTab(mTab, 2, true);

        mTab = mActionBar.newTab().setTabListener(new FragmentsActivityTab());
        // Set custom icon
        //mTab.setIcon(R.drawable.ic_launcher);
        // Set Tab Title
        mTab.setText("Activity");
        mActionBar.addTab(mTab, 3, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.rainy_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, SigninActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
