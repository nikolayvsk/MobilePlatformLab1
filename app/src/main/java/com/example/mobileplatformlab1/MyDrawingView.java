package com.example.mobileplatformlab1;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.Shape;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyDrawingView extends View {

    public MyDrawingView(Context context) {
        super(context);
    }
}


/*
<com.example.mobileplatformlab1.MyDrawingView
        android:id="@+id/drawingView"
        android:layout_width="357dp"
        android:layout_height="202dp"
        app:layout_constraintBottom_toTopOf="@id/calculateButton"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.549"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.969" />
 */