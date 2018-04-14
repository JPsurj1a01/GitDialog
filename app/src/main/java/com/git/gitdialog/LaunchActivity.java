package com.git.gitdialog;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gitdialogs.gitdialoglib.GitDialog;

public class LaunchActivity extends AppCompatActivity {

    private Button btn_show_success_dialog;
    private Button btn_error_dialog;
    private Button btn_warning_dialog;
    private Button btn_information_dialog;
    private Button btn_progress_dialog;
    private Button btn_simple_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_show_success_dialog = (Button) findViewById(R.id.btn_show_success_dialog);
        btn_error_dialog = (Button) findViewById(R.id.btn_error_dialog);
        btn_warning_dialog = (Button) findViewById(R.id.btn_warning_dialog);
        btn_information_dialog = (Button) findViewById(R.id.btn_information_dialog);
        btn_progress_dialog = (Button) findViewById(R.id.btn_progress_dialog);
        btn_simple_dialog = (Button) findViewById(R.id.btn_simple_dialog);

        callListener();
    }

    private void callListener() {

        btn_show_success_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.SUCCESS_TYPE)
                        .setTitle("Success!")
                        .setContentText("This is the demo content to view under the dialog.User can change the dialog content accordingly and manage the several properties like text color,text font style.")
                        .setAnimationTrue(true)
                        .setDialogAnimationTrue(false)
                        .setBackgroundColor(R.color.md_light_green_300);
                sampleAlertDialog.show();
            }
        });

        btn_error_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.ERROR_TYPE)
                        .setTitle("Error!")
                        .setContentText("This is the demo content to view under the dialog.User can change the dialog content accordingly and manage the several properties like text color,text font style.")
                        .setAnimationTrue(true)
                        .setBackgroundColor(R.color.md_deep_orange_600)
                        .setTitleColor(R.color.colorWhite)
                        .showCancelButton(true)
                        .setOnDialogCancelClickListener(new GitDialog.OnShowDialogClickListener() {
                            @Override
                            public void onClick(GitDialog showDialog) {
                                showDialog.dismiss();
                                new GitDialog(LaunchActivity.this, GitDialog.INFORMATION_TYPE)
                                        .setTitle("Information!")
                                        .setContentText("Deleting the Item is cancelled now !")
                                        .setAnimationTrue(true).show();
                            }
                        })
                        .setOnDilaogConfirmClickListener(new GitDialog.OnShowDialogClickListener() {
                            @Override
                            public void onClick(GitDialog showDialog) {
                                showDialog.dismiss();
                                new GitDialog(LaunchActivity.this, GitDialog.SUCCESS_TYPE)
                                        .setTitle("Success!")
                                        .setContentText("The Item Deleted Successfully !")
                                        .setAnimationTrue(true).show();
                            }
                        });
                sampleAlertDialog.show();
            }
        });

        btn_warning_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.WARNING_TYPE)
                        .setTitle("Warning!")
                        .setContentText("This is the demo content to view under the dialog.User can change the dialog content accordingly and manage the several properties like text color,text font style.")
                        .setAnimationTrue(true)
                        .setBackgroundColor(R.color.md_amber_300)
                        .setTitleColor(R.color.colorWhite);
                sampleAlertDialog.show();
            }
        });

        btn_information_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.INFORMATION_TYPE)
                        .setTitle("Information!")
                        .setContentText("This is the demo content to view under the dialog.User can change the dialog content accordingly and manage the several properties like text color,text font style.")
                        .setAnimationTrue(true)
                        .setBackgroundColor(R.color.md_light_blue_A200)
                        .setContentFontFamily("serif-monospace")
                        .setTitleColor(R.color.colorWhite);
                sampleAlertDialog.setCancelable(false);
                sampleAlertDialog.show();
            }
        });

        btn_progress_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.LOADER_TYPE)
                        .setBackgroundColor(R.color.white)
                        .setTitle("Loading please wait...")
                        .setTitleColor(R.color.colorBlack)
                        .setProgressBarColor(R.color.colorPrimary);
                sampleAlertDialog.setCancelable(true);
                sampleAlertDialog.show();

                //this timer is used for demo pupose of running an service and showing the progress dialog
                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        sampleAlertDialog.dismiss();
                    }
                }.start();
            }
        });

        btn_simple_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitDialog sampleAlertDialog = new GitDialog(LaunchActivity.this, GitDialog.SIMPLE_DIALOG)
                        .setBackgroundColor(R.color.colorAccent)
                        .setTitle("Simple Dialog")
                        .setContentText("This is the demo content to view under the dialog.User can change the dialog content accordingly and manage the several properties like text color,text font style.This is simple dialog to view the content with title only.");
                sampleAlertDialog.show();
            }
        });


    }


}