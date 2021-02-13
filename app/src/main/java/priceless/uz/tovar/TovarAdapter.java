package priceless.uz.tovar;

import android.app.Activity;
import android.app.Dialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import java.util.List;

import priceless.uz.R;

public class TovarAdapter extends RecyclerView.Adapter<TovarAdapter.ViewHolder> {

    private List<Tovar> tovarList;
    private Activity context;
    private TovarDatabase tovarDatabase;

    public TovarAdapter(Activity context, List<Tovar> tovarList){

        this.context = context;
        this.tovarList = tovarList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Tovar tovar = tovarList.get(position);

        tovarDatabase = TovarDatabase.getInstance(context);

        holder.textView.setText(tovar.getNomi());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mine", "bosildi");

                Tovar t = tovarList.get(holder.getAdapterPosition());

                Tovar_global.setTovar_glob(t);

                Navigation.findNavController(v).navigate(R.id.action_nav_gallery_to_add_new_tovar_fragment);

            }
        });

//        holder.btEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tovar t = tovarList.get(holder.getAdapterPosition());
//
//                Tovar_global.setTovar_glob(t);
//
//                Navigation.findNavController(v).navigate(R.id.action_nav_gallery_to_add_new_tovar_fragment);
//
////                int sid = t.getId();
////
////                String sNomi = t.getNomi();
////
////                Dialog dialog = new Dialog(context);
////
////                dialog.setContentView(R.layout.dialog_update);
////
////                //initialize width
////                int width = WindowManager.LayoutParams.MATCH_PARENT;
////
////                int height = WindowManager.LayoutParams.WRAP_CONTENT;
////
////                dialog.getWindow().setLayout(width,height);
////
////                dialog.show();
////
////                EditText editText = dialog.findViewById(R.id.edit_text);
////
////                Button btUpdate = dialog.findViewById(R.id.bt_update);
////
////                editText.setText(sNomi);
//
////                btUpdate.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog.dismiss();
////                        String uNomi = editText.getText().toString().trim();
////                        tovarDatabase.tovarDao().update(sid,uNomi);
////
////                        //notify when data is updated
////                        tovarList.clear();
////                        tovarList.addAll(tovarDatabase.tovarDao().getAll());
////                        notifyDataSetChanged();
////                    }
////                });
//            }
//        });

//        holder.btDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tovar t = tovarList.get(holder.getAdapterPosition());
//                tovarDatabase.tovarDao().delete(t);
//
//                int position = holder.getAdapterPosition();
//
//                tovarList.remove(position);
//
//                notifyItemRemoved(position);
//
//                notifyItemRangeChanged(position, tovarList.size());
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return tovarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //initialize variable
        TextView textView;
        ImageView btEdit, btDelete;

        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //assign variable
            textView = itemView.findViewById(R.id.text_view);
//            btEdit = itemView.findViewById(R.id.bt_edit);
//            btDelete = itemView.findViewById(R.id.bt_delete);

            linearLayout = itemView.findViewById(R.id.qator);

        }
    }
}
