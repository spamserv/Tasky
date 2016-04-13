package hr.etfos.josipvojak.tasky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    ListView lvTasks;
    TaskDBHelper dbHelper = new TaskDBHelper(this);
    ArrayList<Task> myTasks;
    TaskAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = (ListView) findViewById(R.id.lvTasks);
        myTasks = dbHelper.getTasks();
        myArrayAdapter = new TaskAdapter(this, myTasks);
        lvTasks.setAdapter(myArrayAdapter);
        lvTasks.setOnItemLongClickListener(this);
    }

    public void onClickAdd(View view) {
        Intent StartNewTaskActivity = new Intent(
                getApplicationContext(), NewTaskActivity.class
        );
       // startActivity(StartNewTaskActivity);

        startActivityForResult(StartNewTaskActivity, Constants.REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, Constants.NEW_ITEM,
                        Toast.LENGTH_SHORT).show();
            }
        }
        myTasks = dbHelper.getTasks();
        myArrayAdapter = new TaskAdapter(this, myTasks);
        lvTasks.setAdapter(myArrayAdapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        dbHelper.deleteGame((Task) myArrayAdapter.getItem(position));
        myTasks.remove(position);
        myArrayAdapter.notifyDataSetChanged();
        return false;
    }
}
