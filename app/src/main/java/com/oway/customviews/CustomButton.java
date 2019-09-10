package com.oway.customviews;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.oway.utillis.FontCache;

public class CustomButton extends AppCompatButton {

    Context context;

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            init();
    }

    private void init() {
        Typeface font = FontCache.getTypeface(FontCache.REGULAR_FONT, context);
        setTypeface(font);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }
}
