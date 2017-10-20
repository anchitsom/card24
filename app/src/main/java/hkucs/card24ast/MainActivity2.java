package hkucs.card24ast;

/**
 * Created by anchitsom on 19/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.singularsys.jep.EvaluationException;
import com.singularsys.jep.Jep;
import com.singularsys.jep.ParseException;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.content.Intent;
import com.singularsys.jep.*;
import java.util.ArrayList;
import java.util.List;
public class MainActivity2 extends AppCompatActivity {


    Button rePick;
    Button checkInput;
    Button clear;
    Button left;
    Button right;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    TextView expression;
    ImageButton[] cards;

    int[] card;
    int[] imageCount=new int[4];
    int[] data;
    boolean[] enable_card;
    boolean[] enable_arithmetic;
    boolean[] enable_brackets;
    List<String> list = new ArrayList<String>();

    private void initCardImage() {
        for (int i = 0; i < 4; i++) {
            int resID = getResources().getIdentifier("back_0", "drawable", "hkucs.card24ast");
            cards[i].setImageResource(resID);
        }
    }

    private void setClear(){
        int resID;
        expression.setText("");
        for (int i = 0; i < 4; i++) {
            imageCount[i] = 0;
            resID = getResources().getIdentifier
                    ("card"+card[i],"drawable", "hkucs.card24ast");
// Please replace “XXX” by your package name
            cards[i].setImageResource(resID);
            cards[i].setClickable(true);
        }
    }

    private void pickCard(){
        data = new int[4];
        card = new int[4];
        int rand[];
        rand = new int[4];
        for (int i=0; i<4; i++)
        {
            rand[i]=0;
        }
        rand[0] = (int) (Math.random()*52 + 1);
        data[0] = card[0] = rand[0];

        System.out.println(rand[0]);
        for (int i=1; i<4; i++)
        {
            int random = (int) (Math.random()*52 + 1);
            System.out.println(random);
            if ((random == rand[0])||(random == rand[1])||(random == rand[2])||(random == rand[3])){
                i--;
            }
            else
            {
                data[i] = card[i] = rand[i] = random;
            }
        }

        setClear();
    }
    private void clickCard(int i) {
        int resId;
        String num;
        Integer value;
        if (imageCount[i] == 0) {
            resId = getResources().getIdentifier("back_0","drawable", "hkucs.card24ast");
// Please replace “XXX” by your package name
            cards[i].setImageResource(resId);
            cards[i].setClickable(false);
            value = data[i];
            num = value.toString();
            expression.append(num);
            imageCount[i] ++;
        }
    }

    private boolean checkInput(String input) {
        Jep jep = new Jep();
        Object res;
        try {
            jep.parse(input);
            res = jep.evaluate();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity2.this,
                    "Wrong Expression", Toast.LENGTH_SHORT).show();
            return false;

        } catch (EvaluationException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity2.this,
                    "Wrong Expression", Toast.LENGTH_SHORT).show();
            return false;
        }
        Double ca = (Double)res;
        if(Math.abs(ca - 24) < 1e-6)
            return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    cards = new ImageButton[4];
    cards[0] = (ImageButton) findViewById(R.id.card1);
    cards[1] = (ImageButton) findViewById(R.id.card2);
    cards[2] = (ImageButton) findViewById(R.id.card3);
    cards[3] = (ImageButton) findViewById(R.id.card4);
    rePick = (Button)findViewById(R.id.repick);
    checkInput = (Button)findViewById(R.id.checkinput);
    left = (Button)findViewById(R.id.left);
    right = (Button)findViewById(R.id.right);
    plus = (Button)findViewById(R.id.plus);
    minus = (Button)findViewById(R.id.minus);
    multiply = (Button)findViewById(R.id.multiply);
    divide = (Button)findViewById(R.id.divide);
    clear = (Button)findViewById(R.id.clear);
    expression = (TextView)findViewById(R.id.expression);


        Bundle extras = getIntent().getExtras();

        int number = extras.getInt("num");

        expression.setHint("Please form an expression such that the result is" + number);

        initCardImage();



    cards[0].setOnClickListener(new ImageButton.OnClickListener() {
        public void onClick(View view) {
            clickCard(0);
        }
    });

    cards[1].setOnClickListener(new ImageButton.OnClickListener() {
        public void onClick(View view) {
            clickCard(1);
        }
    });

    cards[2].setOnClickListener(new ImageButton.OnClickListener() {
        public void onClick(View view) {
            clickCard(2);
        }
    });

    cards[3].setOnClickListener(new ImageButton.OnClickListener() {
        public void onClick(View view) {
            clickCard(3);
        }
    });

    left.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {
                if (expression.getText().toString().equals("") || expression.getText().toString().charAt(expression.getText().toString().length()-1) == '(') {
                    expression.append("(");
                }
                else{
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }


    });

    right.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {

            if (expression.getText().toString().equals("")){
                Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
            }
            else{
                char ch = expression.getText().toString().charAt(expression.getText().toString().length() - 1);
                if (ch == ')' || (ch >= '0' && ch <= '9')) {
                    expression.append(")");
                } else {
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    plus.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {

            if (expression.getText().toString().equals("")){
                Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
            }
            else{
                char ch = expression.getText().toString().charAt(expression.getText().toString().length() - 1);
                if (ch == ')' || (ch >= '0' && ch <= '9')) {
                    expression.append("+");
                } else {
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    minus.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {

            if (expression.getText().toString().equals("")){
                Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
            }
            else{
                char ch = expression.getText().toString().charAt(expression.getText().toString().length() - 1);
                if (ch == ')' || (ch >= '0' && ch <= '9')) {
                    expression.append("-");
                } else {
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }

        }
    });
    multiply.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {

            if (expression.getText().toString().equals("")){
                Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
            }
            else{
                char ch = expression.getText().toString().charAt(expression.getText().toString().length() - 1);
                if (ch == ')' || (ch >= '0' && ch <= '9')) {
                    expression.append("*");
                } else {
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    divide.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view) {

            if (expression.getText().toString().equals("")){
                Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
            }
            else{
                char ch = expression.getText().toString().charAt(expression.getText().toString().length() - 1);
                if (ch == ')' || (ch >= '0' && ch <= '9')) {
                    expression.append("/");
                } else {
                    Toast.makeText(MainActivity2.this, "You cannot add this", Toast.LENGTH_SHORT).show();
                }
            }

        }
    });


    clear.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View view){
            setClear();
        }
    });

    checkInput.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View view) {

            int k = 1;
            for (int i = 0; i < 4; i++){
                if(cards[i].isClickable() == true){
                    Toast.makeText(MainActivity2.this, "You must select all four cards", Toast.LENGTH_SHORT).show();
                    k = 0;
                }
            }
            if (k == 1) {
                String inputStr = expression.getText().toString();
                if (checkInput(inputStr)) {
                    Toast.makeText(MainActivity2.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    pickCard();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    setClear();
                }
            }
        }


    });


    pickCard();
}
}

