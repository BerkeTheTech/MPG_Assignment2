package Berke.MobilProgGiris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Users> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_users_screen);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        PersonAdapter productAdapter = new PersonAdapter(this, create_persons());
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public ArrayList<Users> create_persons(){
        Users user1 = new Users("admin","1234");
        Users user2 = new Users("Berke","Kaan");
        Users user3 = new Users("Kerim","Ozsoz");
        Users user4 = new Users("Amaç","Güvensan");
        Users user5 = new Users("Batuhan","5678");
        Users user6 = new Users("Tolga","1999");
        Users user7 = new Users("Alperen","4321");
        Users user8 = new Users("Murat","8520");
        Users user9 = new Users("Alp","1793");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        return users;
    }
}
