package com.thompson.tony.unit.convertion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity {


    // UI references.
    private EditText poundEditText;
    private EditText ounceEditText;
    boolean isPoundFocusable = true;
    private TextWatcher poundWatcher;
    private TextWatcher ounceWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the login form.
        poundEditText = (EditText) findViewById(R.id.pound);
        ounceEditText = (EditText) findViewById(R.id.ounce);
        initWatcher();


        poundEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isPoundFocusable = hasFocus;
                if (isPoundFocusable) {
                    poundEditText.addTextChangedListener(poundWatcher);
                    ounceEditText.removeTextChangedListener(ounceWatcher);
                    poundEditText.setText("");
                } else {
                    poundEditText.removeTextChangedListener(poundWatcher);
                    ounceEditText.addTextChangedListener(ounceWatcher);
                    ounceEditText.setText("");
                }
            }
        });


//        poundEditText.addTextChangedListener(poundWatcher);
//        ounceEditText.addTextChangedListener(ounceWatcher);

        poundEditText.requestFocus();


    }

    private void initWatcher() {

        poundWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isPoundFocusable) {
                    String textInput = s.toString();
                    if (!TextUtils.isEmpty(textInput)) {
                        if (textInput.equals(".")) {
                            return;
                        }
                        if (textInput.startsWith(".")) {
                            textInput = "0" + textInput;
                        }
                        Double ounceValue = Double.valueOf(textInput) * 16;
                        ounceEditText.setText(String.format("%.2f   %.2f", ounceValue, ounceValue / 2));
                    } else {
                        ounceEditText.setText("");
                    }
                }
            }
        };

        ounceWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isPoundFocusable) {
                    String textInput = s.toString();
                    if (!TextUtils.isEmpty(textInput)) {
                        if (textInput.equals(".")) {
                            return;
                        }
                        if (textInput.startsWith(".")) {
                            textInput = "0" + textInput;
                        }
                        poundEditText.setText(String.format("%.2f", Double.valueOf(textInput) / 16));
                    } else {
                        poundEditText.setText("");
                    }
                }
            }
        };
    }


}

