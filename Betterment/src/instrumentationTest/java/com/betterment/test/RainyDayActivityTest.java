package com.betterment.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import static android.test.ViewAsserts.assertOnScreen;

// Import the activity to test and the app's R
import com.betterments.activities.RainyDayActivity;
import com.betterments.activities.RainyDayActivity.R;

public class RainyDayActivityTest extends ActivityInstrumentationTestCase2<RainyDayActivity> {

    private RainyDayActivity mMainActivity;
    private TextView mHelloWorldTextView;

    public RainyDayActivityTest() {
        super(RainyDayActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();
    }

}