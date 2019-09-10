package com.oway.customviews;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.oway.utillis.FontCache;

public class CustomTextView extends AppCompatTextView {

    Context context;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            init();
    }

    private void init() {
        Typeface font = FontCache.getTypeface(FontCache.REGULAR_FONT, context);
        setTypeface(font);
    }
}
