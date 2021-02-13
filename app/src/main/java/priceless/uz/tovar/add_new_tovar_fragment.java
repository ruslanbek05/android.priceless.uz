package priceless.uz.tovar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import priceless.uz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_new_tovar_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_new_tovar_fragment extends Fragment {

    TovarDatabase tovarDatabase;

    Tovar tovar_temp;

    Button button_save, button_delete;

    EditText editText_nomi, editText_shtrix_kod;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_new_tovar_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_new_tovar_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static add_new_tovar_fragment newInstance(String param1, String param2) {
        add_new_tovar_fragment fragment = new add_new_tovar_fragment();
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


        View view = inflater.inflate(R.layout.fragment_add_new_tovar, container, false);

        tovarDatabase = TovarDatabase.getInstance(getActivity());


        editText_nomi = view.findViewById(R.id.nomi);
        editText_shtrix_kod = view.findViewById(R.id.shtrix_kod);

        button_save = view.findViewById(R.id.save_button);
        button_delete = view.findViewById(R.id.save_delete);

        tovar_temp = new Tovar();
        tovar_temp = Tovar_global.getTovar_glob();

//        editText_id.setText(tovar_temp.getNomi());

        if(tovar_temp.getId() == 0)
        {
//            Log.d("mine", "nolga teng ekan");
        }
        else
        {
            editText_nomi.setText(tovar_temp.getNomi());
            editText_shtrix_kod.setText(tovar_temp.getShtrix_kod());
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tovar_temp.setNomi(editText_nomi.getText().toString().trim());
                tovar_temp.setShtrix_kod(editText_shtrix_kod.getText().toString().trim());

                if (!tovar_temp.getNomi().toString().trim().equals("")){
                    tovarDatabase.tovarDao().insertTovar(tovar_temp);

                    Toast.makeText(getActivity(),"Tovar saved!",Toast.LENGTH_SHORT).show();
                }


            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tovarDatabase.tovarDao().delete(tovar_temp);

//                FragmentManager fm = getFragmentManager();
//                if (fm.getBackStackEntryCount() > 0) {
//                    Log.d("mine", "popping backstack");
//                    fm.popBackStack();
//                } else {
//                    Log.d("mine", "nothing on backstack, calling super");
//                    getActivity().onBackPressed();
//                }

                tovar_temp = new Tovar();
                Tovar_global.setTovar_glob(tovar_temp);
                editText_nomi.setText(tovar_temp.getNomi());
                editText_shtrix_kod.setText(tovar_temp.getShtrix_kod());

                Toast.makeText(getActivity(),"Tovar deleted!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}