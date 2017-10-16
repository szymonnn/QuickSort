package pl.nejos.quicksort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final int NUMBERS_TO_GENERATE = 38;
    private int [] numbers;

    @BindView(R.id.randomNumbersTextView)
    TextView randomNumbersTextView;
    @BindView(R.id.sortedNumbersTextView)
    TextView sortedNumbersTextView;
    @BindView(R.id.generateButton)
    Button generateButton;
    @BindView(R.id.sortButton)
    Button sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        generate();
    }

    @OnClick({R.id.generateButton, R.id.sortButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.generateButton:
                generate();
                break;
            case R.id.sortButton:
                quicksort(0, numbers.length - 1);
                break;
        }
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
        updateTextView();
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private void updateTextView() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : numbers) {
            stringBuilder.append(num);
            stringBuilder.append(" ");
        }
        sortedNumbersTextView.setText(stringBuilder.toString());
    }

    private void generate() {
        sortedNumbersTextView.setText("");
        numbers = new int[NUMBERS_TO_GENERATE];
        Random randomGenerator = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++){
            int rand = randomGenerator.nextInt(100);
            numbers[i] = rand;
            stringBuilder.append(rand);
            stringBuilder.append(" ");
        }
        randomNumbersTextView.setText(stringBuilder.toString());
    }
}
