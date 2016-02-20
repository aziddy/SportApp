package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.alexander.sportapp.R;


public class ManageCurrentFragment extends Fragment implements View.OnClickListener {

    public ManageCurrentFragment() {
        // Required empty public constructor

    }

    LayoutInflater inflater;
    ViewGroup container;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "WOW", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        View view = inflater.inflate(R.layout.fragment_manage_current, container, false);


        Button clickBtn = (Button) view.findViewById(R.id.clickBtn1);

        clickBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.clickBtn1){

            Toast.makeText(getContext(), "wtf the FIRST worked", Toast.LENGTH_LONG).show();
           // ViewGroup vg = (ViewGroup) getView();
         //   vg.removeAllViews();
         //   vg.addView(view);


        }

    }

}
