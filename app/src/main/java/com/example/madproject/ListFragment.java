package com.example.madproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.madproject.Adapter.ToDoADapter;
import com.example.madproject.Model.ToDoModel;
import com.example.madproject.Utils.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    public RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private DatabaseHelper myDb;
    private List<ToDoModel> mList;
    private ToDoADapter aDapter , adapter2;
    Activity activity = getActivity();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       // recyclerView = (RecyclerView) getView().findViewById(R.id.recycleViewId);





    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);



        recyclerView =(RecyclerView) view.findViewById(R.id.recycleViewId);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        aDapter = new ToDoADapter(myDb,this);







        myDb=new DatabaseHelper(getActivity());
        mList = new ArrayList<>();
        aDapter = new ToDoADapter(myDb,this);



        mList = myDb.getAllTasks();

        System.out.println(mList);

        Collections.reverse(mList);
        aDapter.setTask(mList);

        aDapter.notifyDataSetChanged();
        recyclerView.setAdapter(aDapter);












        return view;







    }


}