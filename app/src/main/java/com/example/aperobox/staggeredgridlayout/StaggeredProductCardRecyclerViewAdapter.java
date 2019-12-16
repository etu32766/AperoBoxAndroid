package com.example.aperobox.staggeredgridlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aperobox.Activity.BoxFragment;
import com.example.aperobox.Activity.NavigationHost;
import com.example.aperobox.Model.Box;
import com.example.aperobox.R;
import com.example.aperobox.Dao.network.ImageRequester;
import com.example.aperobox.Dao.network.BoxEntry;
import com.example.aperobox.Utility.Constantes;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class StaggeredProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredProductCardViewHolder> {

    private Fragment fragment;
    private List<Box> productList;
    //private ImageRequester imageRequester;

    public StaggeredProductCardRecyclerViewAdapter(List<Box> productList, Fragment fragment) {
        this.fragment = fragment;
        this.productList = productList;
        //imageRequester = ImageRequester.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.staggered_boxs_card_first;
        if (viewType == 1) {
            layoutId = R.layout.staggered_boxs_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.staggered_boxs_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, final int position) {
        if (productList != null && position < productList.size()) {
            Locale locale = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            Box product = productList.get(position);
            holder.productTitle.setText(product.getNom());
            holder.productPrice.setText(format.format(product.getPrixUnitaireHtva()));
            Glide.with(fragment).load(Constantes.URL_IMAGE_API + product.getPhoto()).into(holder.productImage);

            //imageRequester.setImageFromUrl(holder.productImage, product.url);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((NavigationHost) fragment.getActivity()).navigateTo(new BoxFragment(productList.get(position).getId(), fragment.getContext()), true);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
