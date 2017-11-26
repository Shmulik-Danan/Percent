package com.example.elixi.percent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.elixi.percent.MainActivity.animatefab;
import static com.example.elixi.percent.MainActivity.arr;
import static com.example.elixi.percent.MainActivity.isFirst;
import static com.example.elixi.percent.R.id.fab;
import static com.example.elixi.percent.R.id.fab1;
import static com.example.elixi.percent.R.id.fab2;
import static com.example.elixi.percent.R.id.fab3;
import static com.example.elixi.percent.R.id.fab4;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPercent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPercent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPercent extends Fragment{
    EditText etNumber,etPercent;
    TextView tv;
    ImageButton im;
    SQL  myDb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPercent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPercent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPercent newInstance(String param1, String param2) {
        FragmentPercent fragment = new FragmentPercent();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_fragment_percent, container, false);

        myDb = new SQL(getActivity());

        etNumber=(EditText)view.findViewById(R.id.etNumber);
        etPercent=(EditText)view.findViewById(R.id.etPercent);
        tv=(TextView)view.findViewById(R.id.tv);



        etPercent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    push(false);

                    return true;
                }
                return false;
            }
        });
        im=(ImageButton)view.findViewById(R.id.imageButton);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push(true);
            }

        });
        return view;
    }

    private void push(boolean isPush) {
        View view = getActivity().getCurrentFocus();
        if (view != null) {//to take dwon the keyboord
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 500);
        }
        if(isPush) {
            Animation a = new AlphaAnimation(1.0f, 0.0f);
            a.setDuration(300);
            im.startAnimation(a);
            isPush=false;
        }
        if(!etPercent.getText().toString().isEmpty()&&!etNumber.getText().toString().isEmpty()){
            double Percent=Double.parseDouble(etPercent.getText().toString());
            double Number=Double.parseDouble(etNumber.getText().toString());

            double num=(1-(Percent/100))  *Number;


            String ans=String.valueOf(num);
            String final_ans=doMath(ans);

            tv.setText(final_ans);
            arr.add(new DB(etNumber.getText().toString(),etPercent.getText().toString(),final_ans));

            boolean isInserted = myDb.insertData(etNumber.getText().toString(),
                    etPercent.getText().toString(),"",
                    final_ans );
            if(isInserted == true){

            }

            // Toast.makeText(getActivity(),"Data Inserted",Toast.LENGTH_LONG).show();
            else{

            }
            //Toast.makeText(getActivity(),"Data not Inserted",Toast.LENGTH_LONG).show();

        } else{
            if(MainActivity.isOpen) {
                animatefab();
            }
            Snackbar.make(etPercent, "one of the required field(s) is empty", 1300)
                    .setAction("Action", null).show();
            MainActivity.fab.setEnabled(false);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            MainActivity.fab.setEnabled(true);

                        }
                    });
                }
            }).start();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    String doMath(String ans){
        String final_ans="";
        boolean flag=false;
        String s=".";
        String s_0="0";

        for(int i=0;i<ans.length();i++){
            if(ans.charAt(i)==s.charAt(0)||flag){
                flag=true;
                if(i+2<ans.length()){
                    if(ans.charAt(i+1)!=s_0.charAt(0)&&ans.charAt(i+2)!=s_0.charAt(0)){
                        final_ans+=ans.charAt(i);
                        final_ans+=ans.charAt(i+1);
                        final_ans+=ans.charAt(i+2);
                        break;
                    }
                    else if(ans.charAt(i+1)!=s_0.charAt(0)&&ans.charAt(i+2)==s_0.charAt(0)){
                        final_ans+=ans.charAt(i);
                        final_ans+=ans.charAt(i+1);
                        break;

                    }
                    else{
                        break;

                    }

                }
                else if(i+1<ans.length()){
                    if(ans.charAt(i+1)!=s_0.charAt(0)){
                        final_ans+=ans.charAt(i);
                        final_ans+=ans.charAt(i+1);
                        break;
                    }
                    else{
                        break;

                    }

                }
                else{
                    break;
                }
            }

            if(!flag){

                final_ans+=ans.charAt(i);



            }
        }
        return final_ans;
    }

}
