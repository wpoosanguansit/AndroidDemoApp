package com.betterment.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import static android.test.ViewAsserts.assertOnScreen;

// Import the activity to test and the app's R
import com.betterments.activities.RainyDayActivity;
import com.betterments.activities.SigninActivity.R;

public class SigninActivityTest extends ActivityInstrumentationTestCase2<SigninActivity> {

    private SigninActivity mMainActivity;
    private TextView mHelloWorldTextView;

    public SigninActivityTest() {
        super(SigninActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();
        mHelloWorldTextView = (TextView) mMainActivity.findViewById(R.id.for_customer_support);
    }

    // Methods whose names are prefixed with test will automatically be run
    public void testTextView() {
        assertOnScreen(mMainActivity.getWindow().getDecorView(), mHelloWorldTextView);
    }
}