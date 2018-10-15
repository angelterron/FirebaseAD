package e.valka.firebasead;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SongsFragment extends Fragment {
    ArrayList<Song> songs;
    void setArray(ArrayList<Song> songs){
        this.songs = songs;
    }
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity ();
        if(activity==null)return;
        RecyclerView recycler = activity.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(activity));
        recycler.setAdapter(new SongsAdapter(getContext(),songs));


    }
    class SongsAdapter extends RecyclerView.Adapter<ListadoViewHolder>{
        private Context context;
        private ArrayList<Song> songs;
        SongsAdapter(Context context, ArrayList<Song> songs){
            this.context = context;
            this.songs = songs;
        }

        @NonNull
        @Override
        public ListadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View rowView = LayoutInflater.from (context).inflate (R.layout.list_element, viewGroup, false);
            return new ListadoViewHolder (rowView);
        }

        @Override
        public void onBindViewHolder(@NonNull ListadoViewHolder listadoViewHolder, int i) {
            listadoViewHolder.bind(songs.get(i).title,songs.get(i).author,songs.get(i).album);
        }

        @Override
        public int getItemCount() {
            return songs.size();
        }
    }
    class ListadoViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;

        ListadoViewHolder (@NonNull View itemView) {
            super (itemView);
            textView1 = itemView.findViewById(R.id.titulo);
            textView2 = itemView.findViewById(R.id.autor);
            textView3 = itemView.findViewById(R.id.album);

        }

        public void bind (String titulo, String autor,String album) {
            textView1.setText(titulo);
            textView2.setText(autor);
            textView3.setText(album);
        }
    }

}
