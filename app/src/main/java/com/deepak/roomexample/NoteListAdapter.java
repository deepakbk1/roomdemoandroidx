package com.deepak.roomexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deepakpurohit on 25,August,2019
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Note myNote);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Note> mNotes;
    private OnDeleteClickListener onDeleteClickListener;

    public NoteListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        if (mNotes != null) {
            Note note = mNotes.get(position);
            holder.setData(note.getNote(), position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.noteItemView.setText(R.string.no_note);
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    // public class NoteViewHolder extends RecyclerView.ViewHolder {
//
//        private AppCompatTextView noteItemView;
//
//        private AppCompatImageView imgDelete, imgEdit;
//
//        public NoteViewHolder(View itemView) {
//            super(itemView);
//            noteItemView = itemView.findViewById(R.id.txvNote);
//            imgDelete = itemView.findViewById(R.id.ivRowDelete);
//            imgEdit = itemView.findViewById(R.id.ivRowEdit);
//        }
//
//
//    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private int mPosition;
        @BindView(R.id.txvNote)
        AppCompatTextView noteItemView;
        @BindView(R.id.ivRowDelete)
        AppCompatImageView imgDelete;
        @BindView(R.id.ivRowEdit)
        AppCompatImageView imgEdit;

        public NoteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(String note, int position) {
            noteItemView.setText(note);
            mPosition = position;
        }

        public void setListeners() {
            imgEdit.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, EditNoteActivity.class);
                intent.putExtra("note_id", mNotes.get(mPosition).getId());
                ((Activity) mContext).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
            });

            imgDelete.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));
                }
            });
        }
    }
}
