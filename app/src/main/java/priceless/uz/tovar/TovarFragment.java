package priceless.uz.tovar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import priceless.uz.MainActivity;
import priceless.uz.R;

public class TovarFragment extends Fragment {

//    ConfirmationAction action;

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

List<Tovar> tovarList = new ArrayList<>();
LinearLayoutManager linearLayoutManager;
TovarDatabase tovarDatabase;
TovarAdapter adapter;


    Tovar tovar, tovar_temp;
    TovarDao tovarDao;

    Button add_tovar_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tovar, container, false);

        tovar_temp = new Tovar();


        editText = root.findViewById(R.id.edit_text);

        btReset = root.findViewById(R.id.bt_reset);

        recyclerView = root.findViewById(R.id.recycler_view);

        tovarDatabase = TovarDatabase.getInstance(getActivity());

        tovarList = tovarDatabase.tovarDao().getAll();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new TovarAdapter(getActivity(),tovarList);

        recyclerView.setAdapter(adapter);



//        btAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String sNomi = editText.getText().toString().trim();
//                if (!sNomi.equals("")){
//                    Tovar tovar = new Tovar();
//                    tovar.setNomi(sNomi);
//                    tovarDatabase.tovarDao().insertTovar(tovar);
//                    editText.setText("");
//
//                    tovarList.clear();
//                    //notify when data is inserted
//                    tovarList.addAll(tovarDatabase.tovarDao().getAll());
//                    adapter.notifyDataSetChanged();
//
//                }
//            }
//        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all data from database
                tovarDatabase.tovarDao().reset(tovarList);

                tovarList.clear();
                tovarList.addAll(tovarDatabase.tovarDao().getAll());
                adapter.notifyDataSetChanged();

            }
        });



        add_tovar_button = root.findViewById(R.id.bt_add_fragment);
        add_tovar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                tovar_temp.setId(0);
                tovar_temp.setNomi("0");
                tovar_temp.setShtrix_kod("0");

                Tovar_global.setTovar_glob(tovar_temp);

                Navigation.findNavController(root).navigate(R.id.action_nav_gallery_to_add_new_tovar_fragment);
            }
        });





        return root;
    }
}