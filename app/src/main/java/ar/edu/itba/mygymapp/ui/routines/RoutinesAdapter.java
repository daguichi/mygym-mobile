package ar.edu.itba.mygymapp.ui.routines;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import ar.edu.itba.mygymapp.R;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> implements Filterable {

    private ArrayList<Routine> routines = new ArrayList<>();
    private ArrayList<Routine> allRoutines;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routine_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.routineName.setText(routines.get(position).getName());
        holder.routineScore.setText(routines.get(position).getScore());
        holder.routineUser.setText(routines.get(position).getUser());

//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, destacadas.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void setRoutines(ArrayList<Routine> destacadas) {
        this.routines = destacadas;
        this.allRoutines = new ArrayList<>(destacadas);
        // notifyDataSetChanged();  para notificarle al view adaptor que la data proveniente de la api ya cambio
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Routine> filteredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()) {
                filteredList.addAll(allRoutines);
            } else {
                for(Routine routine : allRoutines) {
                    if(routine.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        filteredList.add(routine);
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            routines.clear();
            routines.addAll((Collection<? extends Routine>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @SuppressLint("NotifyDataSetChanged")
    public void sort(Comparator<Routine> comparator) {
        routines.sort(comparator);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView routineName, routineScore, routineUser;
        private MaterialCardView parent;
        private ImageView routineImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            routineName = itemView.findViewById(R.id.routineName);
            routineScore = itemView.findViewById(R.id.routineScore);
            routineUser = itemView.findViewById(R.id.routineUser);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}