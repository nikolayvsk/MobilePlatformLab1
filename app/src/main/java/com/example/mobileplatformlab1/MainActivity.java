package com.example.mobileplatformlab1;

import static java.lang.Math.abs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private LinearLayout triangleLayout;
    private LinearLayout rectangleLayout;
    private LinearLayout circleLayout;
    private LinearLayout ellipseLayout;

    // Добавим блок для четырехугольника
    private EditText x1EditText;
    private EditText y1EditText;
    private EditText x2EditText;
    private EditText y2EditText;
    private EditText x3EditText;
    private EditText y3EditText;

    private EditText x1RectEditText; // Добавим EditText-ы для четырехугольника
    private EditText y1RectEditText;
    private EditText x2RectEditText; // Добавим EditText-ы для четырехугольника
    private EditText y2RectEditText;
    private EditText x3RectEditText;
    private EditText y3RectEditText;
    private EditText x4RectEditText;
    private EditText y4RectEditText;

    private EditText xCenterEditText;
    private EditText yCenterEditText;
    private EditText radiusEditText;

    private EditText aAxisEditText;
    private EditText bAxisEditText;
    private Button calculateButton;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        shapeSpinner = findViewById(R.id.shapeSpinner);
        triangleLayout = findViewById(R.id.triangleLayout);
        rectangleLayout = findViewById(R.id.rectangleLayout);
        circleLayout = findViewById(R.id.circleLayout);
        ellipseLayout = findViewById(R.id.ellipseLayout);

        x1EditText = findViewById(R.id.x1EditText);
        y1EditText = findViewById(R.id.y1EditText);
        x2EditText = findViewById(R.id.x2EditText);
        y2EditText = findViewById(R.id.y2EditText);
        x3EditText = findViewById(R.id.x3EditText);
        y3EditText = findViewById(R.id.y3EditText);

        x1RectEditText = findViewById(R.id.x1RectEditText);
        y1RectEditText = findViewById(R.id.y1RectEditText);
        x2RectEditText = findViewById(R.id.x2RectEditText);
        y2RectEditText = findViewById(R.id.y2RectEditText);
        x3RectEditText = findViewById(R.id.x3RectEditText);
        y3RectEditText = findViewById(R.id.y3RectEditText);
        x4RectEditText = findViewById(R.id.x4RectEditText);
        y4RectEditText = findViewById(R.id.y4RectEditText);

        xCenterEditText = findViewById(R.id.xCenterEditText);
        yCenterEditText = findViewById(R.id.yCenterEditText);
        radiusEditText = findViewById(R.id.radiusEditText);

        aAxisEditText = findViewById(R.id.aAxisEditText);
        bAxisEditText = findViewById(R.id.bAxisEditText);

        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setBackgroundColor(Color.rgb(0, 128, 0));
        resultTextView = findViewById(R.id.resultTextView);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setBackgroundColor(Color.rgb(128, 0, 0));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shape_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);


        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedShape = shapeSpinner.getSelectedItem().toString();

                if (selectedShape.equals("Triangle")) {
                    triangleLayout.setVisibility(View.VISIBLE);
                    rectangleLayout.setVisibility(View.GONE);
                    circleLayout.setVisibility(View.GONE);
                    ellipseLayout.setVisibility(View.GONE);
                } else if (selectedShape.equals("Rectangle")) {
                    triangleLayout.setVisibility(View.GONE);
                    rectangleLayout.setVisibility(View.VISIBLE);
                    circleLayout.setVisibility(View.GONE);
                    ellipseLayout.setVisibility(View.GONE);
                } else if (selectedShape.equals("Circle")) {
                    triangleLayout.setVisibility(View.GONE);
                    rectangleLayout.setVisibility(View.GONE);
                    circleLayout.setVisibility(View.VISIBLE);
                    ellipseLayout.setVisibility(View.GONE);
                }
                else if (selectedShape.equals("Ellipse")) {
                    triangleLayout.setVisibility(View.GONE);
                    rectangleLayout.setVisibility(View.GONE);
                    circleLayout.setVisibility(View.GONE);
                    ellipseLayout.setVisibility(View.VISIBLE);
                }
                else {
                    triangleLayout.setVisibility(View.GONE);
                    rectangleLayout.setVisibility(View.GONE);
                    circleLayout.setVisibility(View.GONE);
                    ellipseLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAreaAndPerimeter();
            }
        });
    }

    private void calculateAreaAndPerimeter() {
        String selectedShape = shapeSpinner.getSelectedItem().toString();
        double area = 0.0;
        double perimeter = 0.0;
        double length = 0.0;

        if (selectedShape.equals("Triangle")) {
            int x1 = Integer.parseInt(x1EditText.getText().toString());
            int y1 = Integer.parseInt(y1EditText.getText().toString());
            int x2 = Integer.parseInt(x2EditText.getText().toString());
            int y2 = Integer.parseInt(y2EditText.getText().toString());
            int x3 = Integer.parseInt(x3EditText.getText().toString());
            int y3 = Integer.parseInt(y3EditText.getText().toString());

            boolean isTriangleValid = isTriangleValid(x1, y1, x2, y2, x3, y3);

            if (isTriangleValid) {
                area = calculateTriangleArea(x1, y1, x2, y2, x3, y3);
                perimeter = calculateTrianglePerimeter(x1, y1, x2, y2, x3, y3);
                resultTextView.setText("Area: " + area + "\n:Perimetr: " + perimeter);
            } else {
                showValidationErrorDialog("Incorrect coordinates");
                return;
            }
        } else if (selectedShape.equals("Rectangle")) {
            int x1 = Integer.parseInt(x1RectEditText.getText().toString());
            int y1 = Integer.parseInt(y1RectEditText.getText().toString());
            int x2 = Integer.parseInt(x2RectEditText.getText().toString());
            int y2 = Integer.parseInt(y2RectEditText.getText().toString());
            int x3 = Integer.parseInt(x3RectEditText.getText().toString());
            int y3 = Integer.parseInt(y3RectEditText.getText().toString());
            int x4 = Integer.parseInt(x4RectEditText.getText().toString());
            int y4 = Integer.parseInt(y4RectEditText.getText().toString());

            boolean isRectangleValid = isRectangleValid(x1, y1, x2, y2, x3, y3, x4, y4);

            if (isRectangleValid) {
                area = calculateRectangleArea(x1, y1, x2, y2, x3, y3, x4, y4);
                perimeter = calculateRectanglePerimeter(x1, y1, x2, y2, x3, y3, x4, y4);
                resultTextView.setText("Area: " + area + "\nPerimetr: " + perimeter);
            } else {
                showValidationErrorDialog("Incorrect coordinates");
                return;
            }
        } else if (selectedShape.equals("Circle")) {
            double radius = Double.parseDouble(radiusEditText.getText().toString());
            double xCenter = Double.parseDouble(xCenterEditText.getText().toString());
            double yCenter = Double.parseDouble(yCenterEditText.getText().toString());

            if (xCenter != yCenter) {
                showValidationErrorDialog("Incorrect coordinates");
                return;
            }
            area = calculateCircleArea(radius);
            length = calculateCircleLength(radius);
            resultTextView.setText("Area: " + area + "\nLength: " + length);
        } else if (selectedShape.equals("Ellipse")) {
            double a = Double.parseDouble(aAxisEditText.getText().toString());
            double b = Double.parseDouble(bAxisEditText.getText().toString());

            area = calculateEllipseArea(a, b);
            length = calculateEllipseLength(a, b);
            resultTextView.setText("Area: " + area + "\nLength: " + length);
        }

    }

    private double calculateTriangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        double side1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double side3 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        double semiPerimeter = (side1 + side2 + side3) / 2;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3));

        return area;
    }

    private double calculateTrianglePerimeter(int x1, int y1, int x2, int y2, int x3, int y3) {
        double side1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double side3 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        return side1 + side2 + side3;
    }

    private boolean isTriangleValid(int x1, int y1, int x2, int y2, int x3, int y3) {
        double side1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double side3 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

        return (side1 + side2 > side3) && (side2 + side3 > side1) && (side3 + side1 > side2) && ((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) != 0);
    }

    private boolean isRectangleValid(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        if ((x1 == x2 && y1 == y2) || (x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4) ||
                (x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4) || (x3 == x4 && y3 == y4)) {
            return false;
        }
        if ((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) == 0 || (x1 * (y2 - y4) + x2 * (y4 - y1) + x4 * (y1 - y2)) == 0 || (x1 * (y3 - y4) + x3 * (y4 - y1) + x4 * (y1 - y3)) == 0){
            return false;
        };
        return true;
    }

    private double calculateRectangleArea(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        return Math.abs((x1 - x2) * (y1 + y2) + (x2 - x3) * (y2 + y3) + (x3 - x4) * (y3 + y4) + (x4 - x1) * (y4 + y1)) / 2;
    }

    private double calculateRectanglePerimeter(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double side1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double side2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double side3 = Math.sqrt(Math.pow(x4 - x3, 2) + Math.pow(y4 - y3, 2));
        double side4 = Math.sqrt(Math.pow(x1 - x4, 2) + Math.pow(y1 - y4, 2));

        return side1 + side2 + side3 + side4;
    }

    private double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }

    private double calculateCircleLength(double radius) {
        return 2 * Math.PI * radius;
    }

    private double calculateEllipseArea(double a, double b) {
        return Math.PI * a * b;
    }

    private double calculateEllipseLength(double a, double b) {
        double term1 = (3 * (a + b));
        double term2 = Math.sqrt((3 * a + b) * (a + 3 * b));
        return Math.PI * (term1 - term2);
    }

    private void showValidationErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void saveDataToFile(String data) {
        try {
            File file = new File(getExternalFilesDir(null), "user_data.txt");
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSaveButtonClicked(View view) {

        EditText x1EditText = findViewById(R.id.x1EditText);
        EditText y1EditText = findViewById(R.id.y1EditText);
        EditText x2EditText = findViewById(R.id.x2EditText);
        EditText y2EditText = findViewById(R.id.y2EditText);
        EditText x3EditText = findViewById(R.id.x3EditText);
        EditText y3EditText = findViewById(R.id.y3EditText);

        EditText x1RectEditText = findViewById(R.id.x1RectEditText);
        EditText y1RectEditText = findViewById(R.id.y1RectEditText);
        EditText x2RectEditText = findViewById(R.id.x2RectEditText);
        EditText y2RectEditText = findViewById(R.id.y2RectEditText);
        EditText x3RectEditText = findViewById(R.id.x3RectEditText);
        EditText y3RectEditText = findViewById(R.id.y3RectEditText);
        EditText x4RectEditText = findViewById(R.id.x4RectEditText);
        EditText y4RectEditText = findViewById(R.id.y4RectEditText);

        EditText xCenterEditText = findViewById(R.id.xCenterEditText);
        EditText yCenterEditText = findViewById(R.id.yCenterEditText);
        EditText radiusEditText = findViewById(R.id.radiusEditText);

        EditText xCenterEllipseEditText = findViewById(R.id.xCenterEllipseEditText);
        EditText yCenterEllipseEditText = findViewById(R.id.yCenterEllipseEditText);
        EditText aAxisEditText = findViewById(R.id.aAxisEditText);
        EditText bAxisEditText = findViewById(R.id.bAxisEditText);

        String dataToSave = "";

        dataToSave += "Triangle:\n";
        dataToSave += "x1: " + x1EditText.getText().toString() + "\n";
        dataToSave += "y1: " + y1EditText.getText().toString() + "\n";
        dataToSave += "x2: " + x2EditText.getText().toString() + "\n";
        dataToSave += "y2: " + y2EditText.getText().toString() + "\n";
        dataToSave += "x3: " + x3EditText.getText().toString() + "\n";
        dataToSave += "y3: " + y3EditText.getText().toString() + "\n";

        dataToSave += "\nRectangle:\n";
        dataToSave += "x1: " + x1RectEditText.getText().toString() + "\n";
        dataToSave += "y1: " + y1RectEditText.getText().toString() + "\n";
        dataToSave += "x2: " + x2RectEditText.getText().toString() + "\n";
        dataToSave += "y2: " + y2RectEditText.getText().toString() + "\n";
        dataToSave += "x3: " + x3RectEditText.getText().toString() + "\n";
        dataToSave += "y3: " + y3RectEditText.getText().toString() + "\n";
        dataToSave += "x4: " + x4RectEditText.getText().toString() + "\n";
        dataToSave += "y4: " + y4RectEditText.getText().toString() + "\n";

        dataToSave += "\nCircle:\n";
        dataToSave += "x: " + xCenterEditText.getText().toString() + "\n";
        dataToSave += "y: " + yCenterEditText.getText().toString() + "\n";
        dataToSave += "Radius: " + radiusEditText.getText().toString() + "\n";

        dataToSave += "\nEllipse:\n";
        dataToSave += "x: " + xCenterEllipseEditText.getText().toString() + "\n";
        dataToSave += "y: " + yCenterEllipseEditText.getText().toString() + "\n";
        dataToSave += "a: " + aAxisEditText.getText().toString() + "\n";
        dataToSave += "b: " + bAxisEditText.getText().toString() + "\n";

        saveDataToFile(dataToSave);
    }
}

