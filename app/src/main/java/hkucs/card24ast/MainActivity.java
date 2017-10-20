package hkucs.card24ast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button butt;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        butt = (Button) findViewById(R.id.button);

        input = (EditText) findViewById(R.id.editText);


        butt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int k = 1;
                int num=0;
                try{
                     num = Integer.parseInt(input.getText().toString());
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                    k = 0;
                }

                if (k==1) {
                    Intent myIntent = new Intent(view.getContext(), MainActivity2.class);
                    myIntent.putExtra("num", num);
                    try {
                        startActivityForResult(myIntent, 0);
                    } catch (android.content.ActivityNotFoundException e) {
                        System.out.print(e);
                    }

                }
            }
        });

    }
}
