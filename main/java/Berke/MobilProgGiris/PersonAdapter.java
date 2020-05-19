package Berke.MobilProgGiris;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    ArrayList<Users> mProductList;
    LayoutInflater inflater;

    public PersonAdapter(Context context, ArrayList<Users> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Users selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userName, password;
        ImageView personImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.username);
            password = (TextView) itemView.findViewById(R.id.password);
            personImage = (ImageView) itemView.findViewById(R.id.personImage);
        }

        public void setData(Users selectedProduct, int position) {
            this.userName.setText(selectedProduct.getUsername());
            this.password.setText(selectedProduct.getPassword());
            //this.personImage.setImageResource(selectedProduct.());
        }

        @Override
        public void onClick(View v) {
        }
    }
}