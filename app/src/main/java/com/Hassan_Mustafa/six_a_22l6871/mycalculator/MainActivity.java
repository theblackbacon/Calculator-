package com.Hassan_Mustafa.six_a_22l6871.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder input = new StringBuilder();
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean isNewInput = true;
    private double memory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        setNumberButtonListeners();
        setOperatorButtonListeners();
        setFunctionButtonListeners();
        setMemoryButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDecimal};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                if (isNewInput) {
                    input.setLength(0);
                    isNewInput = false;
                }
                input.append(button.getText().toString());
                display.setText(input.toString());
            });
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorIds = {R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};
        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                if (input.length() > 0) {
                    firstOperand = Double.parseDouble(input.toString());
                    operator = button.getText().toString();
                    input.setLength(0);
                    isNewInput = true;
                }
            });
        }

        findViewById(R.id.btnEquals).setOnClickListener(view -> {
            if (!operator.isEmpty() && input.length() > 0) {
                secondOperand = Double.parseDouble(input.toString());
                double result;
                if (operator.equals("+")) {
                    result = firstOperand + secondOperand;
                } else if (operator.equals("-")) {
                    result = firstOperand - secondOperand;
                } else if (operator.equals("*")) {
                    result = firstOperand * secondOperand;
                } else if (operator.equals("/")) {
                    result = secondOperand != 0 ? firstOperand / secondOperand : 0;
                } else {
                    result = secondOperand;
                }
                display.setText(String.valueOf(result));
                input.setLength(0);
                input.append(result);
                operator = "";
                isNewInput = true;
            }
        });
    }

    private void setFunctionButtonListeners() {
        findViewById(R.id.btnC).setOnClickListener(view -> {
            input.setLength(0);
            display.setText("0");
            isNewInput = true;
            operator = "";
            firstOperand = 0;
            secondOperand = 0;
        });
        findViewById(R.id.btnBackspace).setOnClickListener(view -> {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
                display.setText(input.length() > 0 ? input.toString() : "0");
            }
        });

        findViewById(R.id.btnPercent).setOnClickListener(view -> {
            if (input.length() > 0) {
                double value = Double.parseDouble(input.toString()) / 100;
                display.setText(String.valueOf(value));
                input.setLength(0);
                input.append(value);
            }
        });

        findViewById(R.id.btnXSquared).setOnClickListener(view -> {
            if (input.length() > 0) {
                double value = Double.parseDouble(input.toString()) * Double.parseDouble(input.toString());
                display.setText(String.valueOf(value));
                input.setLength(0);
                input.append(value);
            }
        });

        findViewById(R.id.btnPlusMinus).setOnClickListener(view -> {
            if (input.length() > 0) {
                double value = Double.parseDouble(input.toString()) * -1;
                display.setText(String.valueOf(value));
                input.setLength(0);
                input.append(value);
            }
        });

    }
    private void setMemoryButtonListeners() {
        findViewById(R.id.mc).setOnClickListener(view -> memory = 0);
        findViewById(R.id.mr).setOnClickListener(view -> display.setText(String.valueOf(memory)));
        findViewById(R.id.btnMPlus).setOnClickListener(view -> memory += Double.parseDouble(display.getText().toString()));
        findViewById(R.id.btnMMinus).setOnClickListener(view -> memory -= Double.parseDouble(display.getText().toString()));
        findViewById(R.id.btnM).setOnClickListener(view -> memory = Double.parseDouble(display.getText().toString()));
    }
}