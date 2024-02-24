package com.example.beanshop;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    public Activity startActivity() {
        Intent intent = new Intent(getActivity(), HomePage.class);
        startActivity(intent);
        return null;
    }
}