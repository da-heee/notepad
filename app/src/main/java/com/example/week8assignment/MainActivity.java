package com.example.week8assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button save;
    Button delete;
    EditText ET_title_input;
    EditText ET_contents_input;
    TextView TV_title_output;
    TextView TV_contents_output;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF = "SHARED_PREF";
    private static final String USER_INPUT = "STRING_USER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE); //다른 앱에서 여기에 접근 못함, name은 key 값, 전역으로 하자

        save = findViewById(R.id.BT_save);
        delete = findViewById(R.id.BT_delete);

        ET_title_input = findViewById(R.id.ET_titleInput);
        ET_contents_input = findViewById(R.id.ET_contentsInput);
        TV_title_output = findViewById(R.id.TV_titleOutput);
        TV_contents_output = findViewById(R.id.TV_contentsOutput);

        String getInput = null;

        save.setOnClickListener(this);
        delete.setOnClickListener(this);

        getInput = sharedPreferences.getString(USER_INPUT,"");

        TV_title_output.setText(getInput);
        TV_contents_output.setText(getInput);



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.BT_save:
                String titleInput = ET_title_input.getText().toString();
                String contentsInput = ET_contents_input.getText().toString();

                TV_title_output.setText(titleInput);
                TV_contents_output.setText(contentsInput);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_INPUT,titleInput);
                editor.putString(USER_INPUT,contentsInput);
                editor.apply();

                break;

            case R.id.BT_delete:
                TV_title_output.setText("");
                TV_contents_output.setText("");

                SharedPreferences.Editor deleteEditor = sharedPreferences.edit();
                deleteEditor.remove(USER_INPUT);
                deleteEditor.apply();

                break;
        }
    }
}