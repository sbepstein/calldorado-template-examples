package com.badharry.heartattack.views;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calldorado.android.ui.views.custom.CalldoradoCustomView;

public class AftercallCustomView extends CalldoradoCustomView {

    public AftercallCustomView(Context context) {
        super(context);
    }

    @Override
    public View getRootView() {

        int dpUnits = convertDpToPixel(5);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final LinearLayout ll = new LinearLayout(getContext());
        ll.setPadding(3*dpUnits, 2*dpUnits, 3*dpUnits, 2*dpUnits);
        ll.setBackgroundColor(Color.parseColor("#F44336"));
        ll.setLayoutParams(llp);

        final EditText textEdit = new EditText(getContext());
        textEdit.setHintTextColor(Color.WHITE);
        textEdit.setTextColor(Color.WHITE);
        textEdit.setHint("Add a note...  ");

        final TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(Color.WHITE);

        String note = (String) findItem(getPhoneNumber(),"");

        if (!TextUtils.isEmpty(note)) {
            note = "Notes about " + getContactName() + ":\n" + note;
            textView.setText(note);
            ll.addView(textView);
            return ll;
        }

        final Button button = new Button(getContext());
        final Button button2 = new Button(getContext());

        button2.setText("Save");
        button2.setBackgroundColor(Color.WHITE);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String typedText = textEdit.getText().toString();
                    if (!TextUtils.isEmpty(typedText)) {
                        saveItem(getPhoneNumber(), typedText);

                        typedText = "Notes about " + getContactName() + ":\n" + typedText;
                        textView.setText(typedText);
                        textEdit.setVisibility(View.GONE);
                        button2.setVisibility(View.GONE);
                        ll.addView(textView);

                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(textEdit.getWindowToken(), 0);
                    }
                    else showFeedbackMessage("Note is empty!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button.setText("Add Note");
        button.setBackgroundColor(Color.WHITE);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    button.setVisibility(View.GONE);
                    ll.addView(textEdit);
                    ll.addView(button2);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ll.addView(button);
        return ll;
    }

    @Override
    public void executeOnStart() {
        super.executeOnStart();
    }

    @Override
    public void executeOnPause() {
        super.executeOnPause();
    }

    @Override
    public void executeOnResume() {
        super.executeOnResume();
    }

    @Override
    public void executeOnStop() {
        super.executeOnStop();
    }

    @Override
    public void executeOnDestroy() {
        super.executeOnDestroy();
    }
}