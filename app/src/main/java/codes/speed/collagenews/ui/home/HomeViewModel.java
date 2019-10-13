package codes.speed.collagenews.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import codes.speed.collagenews.R;

public class HomeViewModel extends RecyclerView.Adapter<HomeViewModel.Viewholder> {
    private String[] jack = {"jakc", "asd", "adkj", "daksdj"};

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.listitem, parent, false);
        Viewholder vv = new Viewholder(listItem);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.text.setText(String.valueOf(position));


    }

    @Override
    public int getItemCount() {

        return 100;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        public TextView text;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.textView8);


        }
    }
}