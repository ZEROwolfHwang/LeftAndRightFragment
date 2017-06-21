package wolf.zero.myapplication;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment implements LeftFragment.onDataChangedListener{


    private static final String TAG = "RightFragment";
    private TextView mText_big_title;
    private TextView mText_content;
    private String mData;
    private String mS1;

    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);

        mText_big_title = (TextView) view.findViewById(R.id.text_big_title);
        mText_content = (TextView) view.findViewById(R.id.text_content);

        mText_big_title.setText(mS1);

        Random random = new Random();
        int length = random.nextInt(10);
        StringBuilder builder = new StringBuilder();
        for (int i =0;i<length;i++) {
            builder.append(mS1 + i+"//");
        }
        mText_content.setText(builder);

        return view;

    }


    @Override
    public void onDataChanged(String data) {
        Log.i(TAG, "onDataChanged: "+data);
        mData = data;
    }

    public void update(String s) {
        mS1 = s;
    }
}
