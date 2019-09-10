package com.oway.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.oway.utillis.FontCache;


/**
 * Created by PatelSanket on 01-Apr-18.
 */

public class CustomTextViewSemiBold extends AppCompatTextView {
    Context context;

    public CustomTextViewSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (!isInEditMode())
            init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null && !isInEditMode()) {
           /* TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextViewSemiBold, 0, 0);
            try {
                String fontName = array.getString(R.styleable.CustomTextView_fontName);
                if (fontName != null) {
                    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
                    setTypeface(typeface);
                }
            } finally {
                array.recycle();
            }*/
            Typeface font = FontCache.getTypeface(FontCache.SEMI_BOLD_FONT, context);
            setTypeface(font, Typeface.NORMAL);
        }
    }

/*
    private void init() {

        String font_path = "fonts/my_font.otf";
        Typeface myTypeface = Typeface.createFromAsset(App.getInstance().assets, font_path)
        setTypeface(myTypeface);

        Typeface font = FontCache.getTypeface(FontCache.REGULAR_FONT, context);
        setTypeface(font, Typeface.BOLD);
    }
*/

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }
}
