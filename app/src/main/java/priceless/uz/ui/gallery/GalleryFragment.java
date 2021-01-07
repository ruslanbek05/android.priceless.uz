package priceless.uz.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import priceless.uz.R;
import priceless.uz.tovar.Tovar;
import priceless.uz.tovar.TovarAdapter;
import priceless.uz.tovar.TovarDao;
import priceless.uz.tovar.TovarDatabase;

public class GalleryFragment extends Fragment {

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

List<Tovar> tovarList = new ArrayList<>();
LinearLayoutManager linearLayoutManager;
TovarDatabase tovarDatabase;
TovarAdapter adapter;


    Tovar tovar;
    TovarDao tovarDao;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        editText = root.findViewById(R.id.edit_text);
        btAdd = root.findViewById(R.id.bt_add);
        btReset = root.findViewById(R.id.bt_reset);

        recyclerView = root.findViewById(R.id.recycler_view);

        tovarDatabase = TovarDatabase.getInstance(getActivity());

        tovarList = tovarDatabase.tovarDao().getAll();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new TovarAdapter(getActivity(),tovarList);

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNomi = editText.getText().toString().trim();
                if (!sNomi.equals("")){
                    Tovar tovar = new Tovar();
                    tovar.setNomi(sNomi);
                    tovarDatabase.tovarDao().insertTovar(tovar);
                    editText.setText("");

                    tovarList.clear();
                    //notify when data is inserted
                    tovarList.addAll(tovarDatabase.tovarDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });

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








        return root;
    }
}