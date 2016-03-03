package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexander.sportapp.R;


public class ManageCompletedFragment extends Fragment implements View.OnClickListener {

    LayoutInflater inflater;
    ViewGroup container;

    String activityName = "";
    Boolean pickup = false;
    Boolean league = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Toast.makeText(getActivity(), getActivity().toString(), Toast.LENGTH_LONG).show();


/**------------------------ get which activity the fragment is in ----------------------------**/

        for (int x = (getActivity().toString().length())-1; x > -1; x--){

            if (getActivity().toString().charAt(x) == '.') {

                for (int y = x+1; y < (getActivity().toString().length())-1; y++){
                    if (getActivity().toString().charAt(y) == '@') {

                        y = getActivity().toString().length()+40;

                    } else {
                        activityName += getActivity().toString().charAt(y);

                    }
                }


                if (activityName.equals("ManageMyPickups")) {
                    pickup = true;
                }

                if (activityName.equals("ManageMyLeagues")) {
                    league = true;
                }

                x = -1;
            }
        }
/** ----------------------------------------------------------------------------------------- **/

              //  Lenovo U530
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_completed, container, false);

        this.inflater = inflater;
        this.container = container;


        String [] dataPickup = {"pickup", "pickup2", "pickup3", "pickup4", "pickup5", "pickup2", "pickup2", "pickup2", "pickup2", "pickup2"};
        String [] dataLeague = {"League", "League2", "League3", "League4", "League5", "League2", "League2", "League2", "League2", "League2"};

        if(pickup){

            ListView listView = (ListView) view.findViewById(R.id.listView);
            ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dataPickup);
            listView.setAdapter(adp);
        }


        if (league){
            ListView listView = (ListView) view.findViewById(R.id.listView);
            ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dataLeague);
            listView.setAdapter(adp);
        }

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


/*

        if(v.getId() == R.id.clickBtn1){

            Toast.makeText(getContext(), "wtf the FIRST worked", Toast.LENGTH_LONG).show();

            // ViewGroup vg = (ViewGroup) getView();
            //   vg.removeAllViews();
            //   vg.addView(view);


        }
*/
    }
}
