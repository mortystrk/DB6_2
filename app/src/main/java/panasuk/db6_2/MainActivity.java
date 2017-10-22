package panasuk.db6_2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    File file;
    TextView name, surname, telefon;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "Contacts.json");
        name = (TextView) findViewById(R.id.nameView);
        surname = (TextView) findViewById(R.id.surnameView);
        telefon = (TextView) findViewById(R.id.telefonVew);
        date = (EditText) findViewById(R.id.dateText);
    }

    public void onSearch(View view) throws IOException, ParseException {
        String birthday = date.getText().toString();
        boolean flag = false;
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(
                new FileReader(file));
        JSONArray array = (JSONArray) object.get("Contacts");
        for(Object tempObject : array){
            if(((JSONObject) tempObject).get("birthday").equals(birthday)){
                name.setText((((JSONObject) tempObject).get("name").toString()));
                surname.setText((((JSONObject) tempObject).get("surname").toString()));
                telefon.setText((((JSONObject) tempObject).get("telefon").toString()));
                flag = true;
            }
        }
        if(!flag){
            name.setText("Значений не найдено");
        }
    }
}
