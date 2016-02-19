package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.alexander.sportapp.R;


public class ManageCompletedFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    LayoutInflater inflater;
    ViewGroup container;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


              //  Lenovo U530
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_completed, container, false);

        this.inflater = inflater;
        this.container = container;

        Button clickBtn = (Button) view.findViewById(R.id.clickBtn);

        clickBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.clickBtn){

            Toast.makeText(getContext(), "wtf it worked the listener", Toast.LENGTH_LONG).show();
            View view = (this.inflater).inflate(R.layout.fragment_manage_current, (this.container), false);
            ViewGroup vg = (ViewGroup) getView();
            vg.removeAllViews();
            vg.addView(view);


        }




        if(v.getId() == R.id.clickBtn1){

            Toast.makeText(getContext(), "wtf the FIRST worked", Toast.LENGTH_LONG).show();

            // ViewGroup vg = (ViewGroup) getView();
            //   vg.removeAllViews();
            //   vg.addView(view);


        }

    }
}
