package com.ninetyone.projects.atlas.nation_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.ninetyone.projects.atlas.nation_detail.NationDetailedActivity;
import com.ninetyone.projects.atlas.R;
import com.ninetyone.projects.atlas.models.NationData;
import com.ninetyone.projects.atlas.image_helper.SvgSoftwareLayerSetter;

import java.util.ArrayList;
import java.util.List;

import static com.ninetyone.projects.atlas.nation_detail.NationDetailedActivity.NATION_DATA;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NationListAdapter extends RecyclerView.Adapter<NationListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<NationData> nationList;

    public NationListAdapter(Context context, ArrayList<NationData> nationList) {
        mContext = context;
        this.nationList = nationList;
    }

    @Override
    public NationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NationListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_nation, parent, false));
    }

    @Override
    public void onBindViewHolder(NationListAdapter.ViewHolder holder, int position) {
        final NationData nationData = nationList.get(position);
        holder.countryName.setText(nationData.getName());
        holder.capital.setText(nationData.getCapital());

        RequestBuilder<PictureDrawable> requestBuilder = Glide.with(mContext)
                .as(PictureDrawable.class)
                .listener(new SvgSoftwareLayerSetter());

        requestBuilder.load(Uri.parse(nationData.getFlag())).into(holder.countryFlag);

        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NationDetailedActivity.class);
                intent.putExtra(NATION_DATA, nationData);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nationList.size();
    }

    void addNationDataInAdapter(List<NationData> nationDataList) {
        int sizeBeforeAddition = this.nationList.size();
        for (NationData nationData : nationDataList) {
            if (!this.nationList.contains(nationData)) {
                this.nationList.add(nationData);
            }
        }
        notifyItemRangeInserted(sizeBeforeAddition, nationList.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView countryFlag;
        TextView countryName;
        TextView capital;
        LinearLayout listLayout;

        ViewHolder(View itemView) {
            super(itemView);
            countryFlag = (AppCompatImageView) itemView.findViewById(R.id.flag_image);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            capital = (TextView) itemView.findViewById(R.id.capital);
            listLayout = (LinearLayout) itemView.findViewById(R.id.list_layout);
        }
    }

    void setFilter(List<NationData> nationDataList) {
        nationList = new ArrayList<>();
        nationList.addAll(nationDataList);
        notifyDataSetChanged();
    }
}
