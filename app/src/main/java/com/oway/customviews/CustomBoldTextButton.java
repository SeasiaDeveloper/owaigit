package com.oway.customviews;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.oway.utillis.FontCache;

public class CustomBoldTextButton extends AppCompatButton {

    Context context;

    public CustomBoldTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            init();
    }

    private void init() {
        Typeface font = FontCache.getTypeface(FontCache.BOLD_FONT, context);
        setTypeface(font, Typeface.BOLD);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }
}