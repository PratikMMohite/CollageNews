package codes.speed.collagenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mContext;
    public List<UserCon> mpost;

    public PostAdapter(Context mContext, List<UserCon> mpost) {
        this.mContext = mContext;
        this.mpost = mpost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.postitem, parent, false);

        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();
        UserCon p = mpost.get(position);
        holder.ptitle.setText(p.getTitle());
        holder.pdesc.setText(p.getContent());
        holder.pcname.setText(p.getCollage());

        holder.pauthor.setText("contact:" + p.getAuther());

    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pimg;
        TextView ptitle, pcname, pdesc, pauthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ptitle = itemView.findViewById(R.id.newstitle);
            pcname = itemView.findViewById(R.id.collagenews);
            pdesc = itemView.findViewById(R.id.postdesc);
            pauthor = itemView.findViewById(R.id.postuser);
            pimg = itemView.findViewById(R.id.eventImage);


        }
    }
}
