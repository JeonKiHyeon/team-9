package kr.co.mju.mp2019f.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static kr.co.mju.mp2019f.tipcalculator.R.id.id_rand_tip;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enteramount;
    private SeekBar seekbar_tip_seekbar;
    private TextView seekbar_text_amount;
    private Button calculate_button;
    private TextView Tip;
    private TextView total_amount_final;
    private RadioButton rand_tip;
    private RadioButton max_tip;
    private RadioButton no_tip;

    private int seekbarpercentage;
    private float enterbillfloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteramount = (EditText) findViewById(R.id.id_enteramount);
        seekbar_tip_seekbar = (SeekBar) findViewById(R.id.id_seekbar);
        seekbar_text_amount = (TextView) findViewById(R.id.id_text_seekpercentage);
        calculate_button = (Button) findViewById(R.id.id_button);
        Tip = (TextView) findViewById(R.id.id_tip);
        total_amount_final = (TextView)findViewById(R.id.id_text_total_amount);
        rand_tip = (RadioButton)findViewById(R.id.id_no_tip);
        max_tip = (RadioButton)findViewById(R.id.id_max_tip);
        no_tip = (RadioButton)findViewById(R.id.id_no_tip);

        seekbar_text_amount.setText("Rate:" + seekbar_tip_seekbar.getProgress() + "%");

        calculate_button.setOnClickListener(this);

        RadioButton.OnClickListener optionOnClickListener = new RadioButton.OnClickListener(){


            @Override
            public void onClick(View v) {
                float result1 = 0.2f;
                if(rand_tip.isChecked()){
                    Random rnd = new Random();
                    int num = rnd.nextInt(seekbarpercentage);
                    Log.d(tag: "Random", String.valueOf(seekbarpercentage));
                }else if(max_tip.isChecked()){
                    enterbillfloat = Float.parseFloat(enteramount.getText().toString());
                    result1 = (enterbillfloat*30/100);
                    Tip.setText("Tip: " + String.valueOf(result1) + "$");

                }else{
                    total_amount_final.setText("Total: " + String.valueOf(enterbillfloat) + "$");
                }
            }



        };

        seekbar_tip_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b){
                seekbar_text_amount.setText("Rate: " + String.valueOf(seekbar_tip_seekbar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                seekbarpercentage = seekbar_tip_seekbar.getProgress();

            }
        });

    }

    @Override
    public void onClick(View view){
        calculate();
    }

    public void calculate(){
        float result = 0.2f;
        if(!enteramount.getText().toString().equals("")){
            enterbillfloat = Float.parseFloat(enteramount.getText().toString());
            result = (enterbillfloat*seekbarpercentage/100);
            Tip.setText("Tip: " + String.valueOf(result) + "$");

            total_amount_final.setText("Total: " + String.valueOf(enterbillfloat + result) + "$");
        } else{
            Toast.makeText(getApplicationContext(), "Enter amount", Toast.LENGTH_LONG).show();
        }
    }
}
