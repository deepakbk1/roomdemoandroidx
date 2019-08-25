package com.deepak.roomexample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewNoteActivity extends AppCompatActivity {
    public static final String NOTE_ADDED = "new_note";
    @BindView(R.id.etNewNote)
    AppCompatEditText etNewNote;
    @BindView(R.id.bAdd)
    AppCompatButton bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bAdd)
    public void onClick() {
        Intent resultIntent = new Intent();

        if (TextUtils.isEmpty(etNewNote.getText())) {
            setResult(RESULT_CANCELED, resultIntent);
        } else {
            String note = etNewNote.getText().toString();
            resultIntent.putExtra(NOTE_ADDED, note);
            setResult(RESULT_OK, resultIntent);
        }

        finish();
    }

}
