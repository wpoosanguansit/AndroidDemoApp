package com.betterment.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.betterment.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;

public class FragmentsTransferTab extends SherlockFragment implements
        ActionBar.TabListener {
    private Fragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from fragment1.xml
        getActivity().setContentView(R.layout.fragments_transfer_tab);
    }

    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTransferTab();
        // Attach fragment1.xml layout
        fragmentTransaction.add(android.R.id.content, mFragment);
        fragmentTransaction.attach(mFragment);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        // Remove fragment1.xml layout
        fragmentTransaction.remove(mFragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub

    }

}