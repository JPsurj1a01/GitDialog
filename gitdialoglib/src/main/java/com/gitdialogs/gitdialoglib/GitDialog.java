package com.gitdialogs.gitdialoglib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Puja Kumari on 4/4/2018.
 */

public class GitDialog extends Dialog implements View.OnClickListener {

    public static final int SUCCESS_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int WARNING_TYPE = 2;
    public static final int INFORMATION_TYPE = 3;
    public static final int LOADER_TYPE = 4;
    public static final int SIMPLE_DIALOG = 5;

    int progressStatus = 0;
    private RelativeLayout rl_main_layout;
    private RelativeLayout rl_dialog_layout;
    private TextView tv_dialog_title;
    private TextView tv_content;
    private TextView tv_loading_content;
    private ImageView iv_success_check;
    private TextView btn_cancel;
    private TextView btn_ok;
    private View view_divider;
    private RelativeLayout rl_loading;
    private ProgressBar progressbar_dialog;

    private Context mContext;
    private int mDialogType;
    private String mTitle;
    private String mContent;
    private Boolean mAnimationTrue = true;
    private int mBackgroundColor = 0;
    private int mTitleColor = R.color.colorWhite;
    private int mProgressBarColor = R.color.colorPrimary;
    private String mPositiveText;
    private Boolean mShowCancelButton = false;
    private String mCancelText;
    private OnShowDialogClickListener mCancelClickListener;
    private OnShowDialogClickListener mConfirmClickListener;
    private String mFontFamily;
    private Handler handler = new Handler();

    public GitDialog(@NonNull Context context, int dialogType) {
        super(context);
        mContext = context;
        mDialogType = dialogType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_design);

        initLayout();
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    private void initLayout() {
        rl_main_layout = (RelativeLayout) findViewById(R.id.rl_main_layout);
        rl_dialog_layout = (RelativeLayout) findViewById(R.id.rl_dialog_layout);
        rl_loading = (RelativeLayout) findViewById(R.id.rl_loading);
        tv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_loading_content = (TextView) findViewById(R.id.tv_loading_content);
        iv_success_check = (ImageView) findViewById(R.id.iv_success_check);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        view_divider = (View) findViewById(R.id.view_divider);
        progressbar_dialog = (ProgressBar) findViewById(R.id.progressbar_dialog);

        onClickListener();
        setTitle(mTitle);
        setContentText(mContent);
        setAnimationTrue(mAnimationTrue);
        setBackgroundColor(mBackgroundColor);
        changeDialog(mDialogType);
        setTitleColor(mTitleColor);
        setPositiveButtonText(mPositiveText);
        setProgressBarColor(mProgressBarColor);
        showCancelButton(mShowCancelButton);
        setCancelText(mCancelText);
        setContentFontFamily(mFontFamily);
    }

    private void onClickListener() {
        btn_cancel.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    public GitDialog setTitle(String title) {
        mTitle = title;
        if (mDialogType != LOADER_TYPE) {
            if (mTitle != null && tv_dialog_title != null) {
                tv_dialog_title.setText(mTitle);
            }
        } else {
            if (mTitle != null && tv_loading_content != null) {
                tv_loading_content.setText(mTitle);
            }
        }
        return this;
    }

    public GitDialog setContentText(String content) {
        mContent = content;
        if (mContent != null && tv_content != null) {
            tv_content.setText(mContent);
        }

        return this;
    }

    public GitDialog setAnimationTrue(Boolean animationTrue) {
        mAnimationTrue = animationTrue;
        if (iv_success_check != null && mAnimationTrue != false) {
            Animation animBounce = AnimationUtils.loadAnimation(getContext(),
                    R.anim.bounce);
            // Use bounce interpolator with amplitude 0.2 and frequency 20
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 26);
            animBounce.setInterpolator(interpolator);
            iv_success_check.startAnimation(animBounce);

            getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }
        return this;
    }

    public GitDialog setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        if (mDialogType == LOADER_TYPE) {
            if (mBackgroundColor != 0 && rl_loading != null) {
                ((GradientDrawable) rl_loading.getBackground()).setColor(getContext().getResources().getColor(mBackgroundColor));
            }
        } else {
            if (mBackgroundColor != 0 && tv_dialog_title != null) {
                ((GradientDrawable) tv_dialog_title.getBackground()).setColor(getContext().getResources().getColor(mBackgroundColor));
                view_divider.setBackgroundColor(getContext().getResources().getColor(backgroundColor));
            }
        }
        return this;
    }

    public GitDialog setTitleColor(int titleColor) {
        mTitleColor = titleColor;
        if (mDialogType != LOADER_TYPE) {
            if (tv_dialog_title != null && mTitleColor != 0) {
                tv_dialog_title.setTextColor(getContext().getResources().getColor(mTitleColor));
            }
        } else {
            if (mTitleColor != 0 && tv_loading_content != null) {
                tv_loading_content.setTextColor(getContext().getResources().getColor(mTitleColor));
            }
        }
        return this;
    }

    public GitDialog setContentFontFamily(String fontFamily) {
        mFontFamily = fontFamily;
        if (mDialogType != LOADER_TYPE) {
            if (mFontFamily != null && tv_content != null) {
                tv_content.setTypeface(Typeface.create(mFontFamily, Typeface.NORMAL));
            }
        } else {
            if (mFontFamily != null && tv_loading_content != null) {
                tv_loading_content.setTypeface(Typeface.create(mFontFamily, Typeface.NORMAL));
            }
        }
        return this;
    }

    public GitDialog setCancelText(String cancelText) {
        mCancelText = cancelText;
        if (mCancelText != null) {
            showCancelButton(true);
            btn_cancel.setText(mCancelText);
        }
        return this;
    }

    public GitDialog showCancelButton(Boolean showCancel) {
        mShowCancelButton = showCancel;
        if (mShowCancelButton != null && btn_cancel != null) {
            btn_cancel.setVisibility(mShowCancelButton ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public GitDialog changeDialog(int dialogType) {
        mDialogType = dialogType;
        if (iv_success_check != null) {
            switch (mDialogType) {
                case SUCCESS_TYPE:
                    setDialogLayout();
                    iv_success_check.setBackground(getContext().getResources().getDrawable(R.drawable.success_check));
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.md_green_200);
                    }
                    break;
                case ERROR_TYPE:
                    setDialogLayout();
                    iv_success_check.setBackground(getContext().getResources().getDrawable(R.drawable.error));
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.md_red_A100);
                    }
                    break;
                case WARNING_TYPE:
                    setDialogLayout();
                    iv_success_check.setBackground(getContext().getResources().getDrawable(R.drawable.warning));
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.md_amber_400);
                    }
                    break;
                case INFORMATION_TYPE:
                    setDialogLayout();
                    iv_success_check.setBackground(getContext().getResources().getDrawable(R.drawable.info));
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.md_light_blue_200);
                    }
                    break;
                case LOADER_TYPE:
                    setProgressLayout();
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.white);
                    }
                    break;
                case SIMPLE_DIALOG:
                    setDialogLayout();
                    iv_success_check.setBackground(null);
                    if (mBackgroundColor == 0) {
                        setBackgroundColor(R.color.md_light_blue_100);
                    }
                    break;
            }
        }
        return this;
    }

    public GitDialog setPositiveButtonText(String positiveText) {
        mPositiveText = positiveText;
        if (mPositiveText != null && btn_ok != null) {
            btn_ok.setText(mPositiveText);
        }
        return this;
    }

    public GitDialog setProgressBarColor(int progressBarColor) {
        mProgressBarColor = progressBarColor;
        if (progressbar_dialog != null) {
            progressbar_dialog.getIndeterminateDrawable()
                    .setColorFilter(ContextCompat.getColor(getContext(), mProgressBarColor), PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

  /*  public GitDialog dismissDialog() {
        if (progressbar_dialog != null && progressbar_dialog.isShown()) {
            progressbar_dialog.setVisibility(View.GONE);
            dismiss();
        }
        return this;
    }*/

    private void setProgressLayout() {
        rl_loading.setVisibility(View.VISIBLE);
        rl_dialog_layout.setVisibility(View.GONE);
    }

    private void setDialogLayout() {
        rl_loading.setVisibility(View.GONE);
        rl_dialog_layout.setVisibility(View.VISIBLE);
    }

    public GitDialog setOnDilaogConfirmClickListener(OnShowDialogClickListener clickListener) {
        mConfirmClickListener = clickListener;
        return this;
    }

    public GitDialog setOnDialogCancelClickListener(OnShowDialogClickListener clickListener) {
        mCancelClickListener = clickListener;
        return this;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_ok) {
            if (mConfirmClickListener != null) {
                mConfirmClickListener.onClick(this);
            } else {
                dismiss();
            }

        } else if (i == R.id.btn_cancel) {
            if (mCancelClickListener != null) {
                mCancelClickListener.onClick(this);
            } else {
                dismiss();
            }

        }
    }

    public void setHorizontalProgressDialog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    // Update the progress status
                    progressStatus += 1;

                    // Try to sleep the thread for 20 milliseconds
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressbar_dialog.setProgress(progressStatus);
                            // Show the progress on TextView
//                            tv.setText(progressStatus + "");
                        }
                    });
                }
            }
        }).start(); // Start the operation

    }

    public interface OnShowDialogClickListener {
        void onClick(GitDialog gitDialog);
    }

}
