package com.covid_tracker;


import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class OwnInformation extends DefaultClusterRenderer<MyItem> {
    Context context;
    public OwnInformation(Context context, GoogleMap map,
                          ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item,
                                               MarkerOptions markerOptions) {
        markerOptions.icon(item.getMarker().getIcon());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
        int confirmed = 0;
        for (MyItem myItem : cluster.getItems()) {
            confirmed += myItem.getConfirmed();
        }
        IconGenerator iconFactory = new IconGenerator(context);
        iconFactory.setBackground(context.getResources().getDrawable (R.drawable.shape));
        iconFactory.setTextAppearance (R.style.clusterText);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(String.valueOf(confirmed))));
    }
}
