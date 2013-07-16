package com.betterment.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.betterment.R;

/**
 * Created by watt on 7/14/13.
 */
public class FragmentsActivityTab extends SherlockFragment implements ActionBar.TabListener {
        private Fragment mFragment;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Get the view from fragment1.xml
            getActivity().setContentView(R.layout.fragments_activity_tab);
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            // TODO Auto-generated method stub
            mFragment = new FragmentsActivityTab();
            // Attach fragment1.xml layout
            fragmentTransaction.add(android.R.id.content, mFragment);
            fragmentTransaction.attach(mFragment);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            // TODO Auto-generated method stub
            // Remove fragment1.xml layout
            fragmentTransaction.remove(mFragment);
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            // TODO Auto-generated method stub

        }


    }
