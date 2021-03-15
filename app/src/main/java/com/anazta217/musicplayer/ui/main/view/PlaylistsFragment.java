package com.anazta217.musicplayer.ui.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.anazta217.musicplayer.R;
import com.anazta217.musicplayer.data.model.Playlist;
import com.anazta217.musicplayer.ui.main.adapter.PlaylistAdapter;
import com.anazta217.musicplayer.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Playlist> playlists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_playlists, container, false);
        playlists = new ArrayList<>();
        recyclerView = inflate.findViewById(R.id.playlists_list_view);
        inflate.findViewById(R.id.create_playlist_btn).setOnClickListener(this);

        loadPlaylists();

        return inflate;
    }

    private void loadPlaylists(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.PLAYLIST_COLLECTION_NAME).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                fillPlaylists(task);
            }
        });
    }

    private void fillPlaylists(Task<QuerySnapshot> task) {
        if (task.isSuccessful() && task.getResult().getDocuments().size() > 0){
            List<Playlist> playlists = new ArrayList<>();
            List<DocumentSnapshot> documents = task.getResult().getDocuments();
            for (DocumentSnapshot doc: documents) {
                playlists.add(doc.toObject(Playlist.class));
            }
            this.playlists = playlists;
            updateListItem();
        }
    }

    private void updateListItem(){
        recyclerView.setAdapter(new PlaylistAdapter(getContext(), playlists, (MainActivity)getActivity()));
    }

    public void updateUI(){
        ((MainActivity) getActivity()).openCreatePlaylistFragment();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_playlist_btn:
                updateUI();
                break;
        }
    }
}