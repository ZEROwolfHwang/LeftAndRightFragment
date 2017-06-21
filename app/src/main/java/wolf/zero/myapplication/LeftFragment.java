package wolf.zero.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LeftFragment extends Fragment {

    private static final String TAG = "LeftFragment";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private List<String> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private onDataChangedListener mListener;

    public LeftFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        mAdapter = new MyAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        RightFragment rightFragment = new RightFragment();
        rightFragment.update(mList.get(0));
        transaction.replace(R.id.container_right, rightFragment);
        transaction.commit();

        return view;

    }

    private void initData() {
        String s = "TITLE";
        for (int i = 0; i < 100; i++) {
            String title = s + i;
            mList.add(title);
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        private final List<String> mListData;

        public MyAdapter(List<String> list) {
            mListData = list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.title_item, parent, false);

            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            String s = mListData.get(position);
            holder.bindUI(s);
        }

        @Override
        public int getItemCount() {
            return mListData.size();
        }

    }


    private class MyHolder extends RecyclerView.ViewHolder{

        private final TextView mText_Title;

        public MyHolder(View itemView) {
            super(itemView);
            mText_Title = (TextView) itemView.findViewById(R.id.text_title);

        }

        public void bindUI(final String s) {
            mText_Title.setText(s);
            mText_Title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {
                        mListener.onDataChanged(s);
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                    RightFragment rightFragment = new RightFragment();
                    rightFragment.update(s);
                    transaction.replace(R.id.container_right, rightFragment);
                    transaction.commit();


                }


            });
        }
    }
    public interface onDataChangedListener  {
        void onDataChanged(String data);
    }

    public void setOnDataChangedListener(onDataChangedListener listener) {
        mListener = listener;
    }

}
