package com.example.ssmbu.gankki.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MyUtils {
    public static void hideSoftKeyboard(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }
}
