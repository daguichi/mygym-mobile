package ar.edu.itba.mygymapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import ar.edu.itba.mygymapp.R;
import ar.edu.itba.mygymapp.databinding.FragmentFavouritesBinding;
import ar.edu.itba.mygymapp.databinding.FragmentProfileBinding;
import ar.edu.itba.mygymapp.ui.favourites.FavouritesViewModel;
import ar.edu.itba.mygymapp.ui.routines.RoutinesAdapter;

public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.saveBtn.setOnClickListener(this::saveProfile);

        Spinner spinner = binding.gender;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return root;
    }

    public void saveProfile(View view){
        EditText fnView, lnView, phoneView;
        String firstName, lastName, phone;
        boolean error = false;

        fnView = binding.firstName;
        lnView = binding.lastName;
        phoneView = binding.editTextPhone;
        firstName = fnView.getText().toString();
        lastName = lnView.getText().toString();
        phone = phoneView.getText().toString();

        if (firstName.trim().length() == 0) {
            fnView.setError("Invalid first name");
            error = true;
        }
        if (lastName.trim().length() == 0) {
            lnView.setError("Invalid last name");
            error = true;
        }
        if(phone.trim().length() != 11){
            phoneView.setError("Invalid phone number");
            error = true;
        }
        if (error) {
            Toast.makeText(getActivity(),"Invalid data", Toast.LENGTH_SHORT).show();
            return ;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}