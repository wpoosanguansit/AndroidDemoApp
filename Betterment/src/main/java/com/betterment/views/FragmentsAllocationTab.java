package com.betterment.views;

/**
 * Created by watt on 7/14/13.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.betterment.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.betterment.widgets.AllocationMeter;

public class FragmentsAllocationTab extends SherlockFragment implements ActionBar.TabListener {
    private Fragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from fragment1.xml
        getActivity().setContentView(R.layout.fragments_allocation_tab);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        final View view             = (View) inflater.inflate(R.id.pie_chart, container, false);
//        //Speedometer speedometer     = (Speedometer)view.findViewById(R.id.Speedometer);
//        return view;
//    }


    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsAllocationTab();
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