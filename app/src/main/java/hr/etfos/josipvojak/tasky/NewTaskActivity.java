package hr.etfos.josipvojak.tasky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity {
    private Spinner s1;
    private EditText etTitle, etDescription;
    TaskDBHelper dbHelper = new TaskDBHelper(this);
    ArrayList<Task> myTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
    }

    private void init() {
        this.etTitle = (EditText) findViewById(R.id.etTitle);
        this.etDescription = (EditText) findViewById(R.id.etDescription);

        String[] priorityArray = getResources().getStringArray(R.array.priorityArray);
        s1 = (Spinner) findViewById(R.id.spinnerPriority);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, priorityArray);

        s1.setAdapter(adapter);
    }

    public void addNewItem(View view) {
        String priority = s1.getSelectedItem().toString();
        String title = "Unknown title";
        String description = "No description provided";
        if(!isEmpty(etTitle)) {
            title = etTitle.getText().toString();
        }
        if(!isEmpty(etDescription)){
            description = etDescription.getText().toString();
        }

        Task t = new Task(title,description,priority);
        dbHelper.addTask(t);
        setResult(RESULT_OK);
        finish();
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
