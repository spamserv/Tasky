package hr.etfos.josipvojak.tasky;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jvojak on 12.4.2016..
 */
public class TaskAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Task> tasklist;

    public TaskAdapter(Context ctx, ArrayList<Task> tasklist) {
        super();
        this.ctx = ctx;
        this.tasklist = tasklist;
    }

    public int getCount() {
        return this.tasklist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tasklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(ctx, R.layout.list_item_task, null);
        }
        Task current = tasklist.get(position);
        ImageView ivPriority   = (ImageView) convertView.findViewById(R.id.ivPriority);
        TextView tvTitle  = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDescription  = (TextView) convertView.findViewById(R.id.tvDescription);
        switch(current.getPriorityPicture()){
            case "Low":
                ivPriority.setImageResource(R.drawable.low_priority);
                break;
            case "Normal":
                ivPriority.setImageResource(R.drawable.normal_priority);
                break;
            case "Urgent":
                ivPriority.setImageResource(R.drawable.high_priority);
                break;
            default:
                break;
        }
        tvTitle.setText(current.getTitle());
        tvDescription.setText(current.getDescription());
        return convertView;
    }

    public void clear()
    {
        tasklist.clear();
        notifyDataSetChanged();
    }

    public void add(Task object)
    {
        tasklist.add(object);
        notifyDataSetChanged();
    }
}

