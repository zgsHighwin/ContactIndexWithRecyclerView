package highwin.zgs.contactindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import highwin.zgs.contactindex.utils.ToastUtils;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description:
 * Create-Time: 2016/8/24 9:14
 */
public class ConTactIndexView extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private Paint mTextPaint;
    private Paint mCirclePaint;
    private int mHeight;
    private int mWidth;
    private float mCellHeight;
    private int mPhoneWidth;
    private float mLetterY;
    private int mLetterPosition = -1;

    public interface OnLetterSelectListener {
        void onLetterChange(String lastLetter, String currentLetter);
    }

    private OnLetterSelectListener mOnLetterSelectListener;

    public void setOnLetterSelectListener(OnLetterSelectListener onLetterSelectListener) {
        this.mOnLetterSelectListener = onLetterSelectListener;
    }

    public ConTactIndexView(Context context) {
        this(context, null);
    }

    public ConTactIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConTactIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.WHITE);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCirclePaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ConTactIndexView", "onDraw");
        for (int i = 0; i < LETTERS.length; i++) {
            float intX = mWidth * 1.0f / 2 - mTextPaint.measureText(LETTERS[i]) / 2;
            Rect rect = new Rect();
            mTextPaint.getTextBounds(LETTERS[i], 0, LETTERS[i].length(), rect);
            float intY = mCellHeight * 1.0f / 2 + rect.height() / 2;
            canvas.drawText(LETTERS[i], intX, intY + i * mCellHeight, mTextPaint);
        }
        Log.d("ConTactIndexView", "mPhoneWidth / 2:" + (mPhoneWidth / 2));
        Log.d("ConTactIndexView", "mLetterY:" + mLetterY);
        if (mLetterPosition > -1 && mLetterPosition < LETTERS.length) {
            Log.d("ConTactIndexView", " LETTERS[mLetterPosition]\t" + LETTERS[mLetterPosition]);
            ToastUtils.showCustomToast(getContext(), LETTERS[mLetterPosition], Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 300, ToastUtils.LENGTH_SHORT);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mPhoneWidth = getResources().getDisplayMetrics().widthPixels;
        mWidth = getMeasuredWidth();
        mCellHeight = mHeight * 1.0f / 26;
    }

    private int mLastPosition = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = MotionEventCompat.getActionMasked(event);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mLetterY = event.getY();
                mLetterPosition = (int) (mLetterY / mCellHeight);
                if (mLetterPosition >= 0 && mLetterPosition < LETTERS.length) {
                    if (mLastPosition != mLetterPosition) {
                        if (mOnLetterSelectListener != null) {
                            mOnLetterSelectListener.onLetterChange(LETTERS[mLastPosition == -1 ? 0 : mLastPosition], LETTERS[mLetterPosition]);
                            mLastPosition = mLetterPosition;
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mLastPosition = -1;
                mLetterPosition = -1;
                break;
        }
        return true;

    }
}
