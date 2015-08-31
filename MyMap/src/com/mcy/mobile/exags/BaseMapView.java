package com.mcy.mobile.exags;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.MapOptions;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer.SELECTION_METHOD;
import com.esri.android.map.ags.ArcGISLayerInfo;
import com.esri.android.map.event.OnMapEventListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.io.UserCredentials;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.FeatureSet;
import com.esri.core.map.Legend;
import com.esri.core.portal.BaseMap;
import com.esri.core.portal.WebMap;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.tasks.ags.query.Query;
import com.esri.core.tasks.ags.query.QueryTask;
import com.mcy.mobile.view.ImgTextGroup;
import com.mcy.mymap.R;

public class BaseMapView extends MapView {

	private LinearLayout layersInfoHolder;
	
	public BaseMapView(Context context) {
		super(context);
		initView(context);
	}

	public BaseMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public BaseMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public BaseMapView(Context context, MapOptions options) {
		super(context, options);
		initView(context);
	}

	public BaseMapView(Context context, SpatialReference spatialreference,
			Envelope extent) {
		super(context, spatialreference, extent);
		initView(context);
	}

	public BaseMapView(Context context, String url, String user, String passwd,
			String bingMapsAppId, OnMapEventListener listener) {
		super(context, url, user, passwd, bingMapsAppId, listener);
		initView(context);
	}

	public BaseMapView(Context context, String url, String user, String passwd,
			String bingMapsAppId) {
		super(context, url, user, passwd, bingMapsAppId);
		initView(context);
	}

	public BaseMapView(Context context, String url, String user, String passwd) {
		super(context, url, user, passwd);
		initView(context);
	}

	public BaseMapView(Context context, String url,
			UserCredentials credentials, String bingMapsAppId,
			OnMapEventListener listener) {
		super(context, url, credentials, bingMapsAppId, listener);
		initView(context);
	}

	public BaseMapView(Context context, WebMap webmap, BaseMap basemap,
			String bingMapsAppId, OnMapEventListener listener) {
		super(context, webmap, basemap, bingMapsAppId, listener);
		initView(context);
	}

	public BaseMapView(Context context, WebMap webmap, String bingMapsAppId,
			OnMapEventListener listener) {
		super(context, webmap, bingMapsAppId, listener);
		initView(context);
	}
	
	protected void  initView(Context context) {
		ArcGISRuntime.setClientId("uK0DxqYT0om1UXa9");
		setMapBackground(Color.WHITE,Color.BLACK, 100, 0);
		setEsriLogoVisible(false);
		
		layersInfoHolder = new LinearLayout(context);
		layersInfoHolder.setOrientation(LinearLayout.VERTICAL);
		layersInfoHolder.setBackgroundResource(R.drawable.bg_white_coner_5);
		layersInfoHolder.setPadding(6, 6, 6, 6);
		addView(layersInfoHolder);
	}
	
	public void regTextInfo(String text){
		TextView txt = new TextView(getContext());
		txt.setText(text);
		layersInfoHolder.addView(txt);
	}
	
	public void regLegend(Legend legend){
		ImgTextGroup it = new ImgTextGroup(getContext(), legend.getImage(), legend.getLabel());
		layersInfoHolder.addView(it);
	}
	
	public void regLegend(int DrawableResId,String lable){
		TextView txt = new TextView(getContext());
		txt.setText(lable);
		Drawable legend = getResources().getDrawable(DrawableResId);
		legend.setBounds(new Rect(0,0,20,20));
		txt.setCompoundDrawables(legend, null, null, null);
		txt.setGravity(Gravity.CENTER);
		txt.setPadding(12, 0, 0, 0);
		layersInfoHolder.addView(txt);
	}
	
	public void showLayerInfoHolder(boolean visable){
		if(visable)
		    layersInfoHolder.setVisibility(View.VISIBLE);
		else
			layersInfoHolder.setVisibility(View.GONE);
	}
	
	public void registerLayer(Layer layer,String name){
		registerLayer(layer, -1, name,true);
	}

	public void registerLayer(Layer layer,String name,boolean visiable){
		registerLayer(layer, -1, name,visiable);
	}

	public void registerLayer(Layer rlayer, int index, String layerName,boolean visiable) {
		final Layer layer = rlayer;
		CheckBox layerCheck = new CheckBox(getContext());
		layerCheck.setChecked(true);
		layerCheck.setText(layerName);
		layerCheck.setTextColor(Color.BLACK);
		layerCheck.setTag(index);
		layerCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					layer.setVisible(true);
				} else {
					layer.setVisible(false);
				}
			}
		});
		layerCheck.setChecked(visiable);
		if(index==-1)
			addLayer(layer);
		else
		    addLayer(layer, index);
		layersInfoHolder.addView(layerCheck);
	}

	public void registerSubLayers(ArcGISDynamicMapServiceLayer msl,boolean visiable) {

		addLayer(msl);
		
		ArcGISLayerInfo[] layers = msl.getLayers();
		
		if(layers!=null&&layers.length>0){
			Log.d("map sublayer", String.valueOf(layers.length));
			registerLayerInfos(layers, layersInfoHolder,visiable);
		}
	}
	
	private void registerLayerInfos(ArcGISLayerInfo[] layers,LinearLayout holder,boolean visiable){
		
		for(final ArcGISLayerInfo layer:layers){
			
			CheckBox layerCheck = new CheckBox(getContext());

			final LinearLayout subHolder = new LinearLayout(getContext());
			subHolder.setOrientation(LinearLayout.VERTICAL);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(12, 0, 0, 0);
			subHolder.setLayoutParams(lp);
			
			List<Legend> legends = layer.getLegend();
			if(legends!=null&&legends.size()>0){
				
				for(Legend legend:legends){
					ImgTextGroup it = new ImgTextGroup(getContext(), legend.getImage(), legend.getLabel());
					subHolder.addView(it);
				}
			}	
			
			layerCheck.setText(layer.getName());
			layerCheck.setTextColor(Color.BLACK);
			layerCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								subHolder.setVisibility(View.VISIBLE);
							} else {
								subHolder.setVisibility(View.GONE);
							}
							layer.setVisible(isChecked);
							invalidate();
						}
					});
			layerCheck.setChecked(visiable);
			if(visiable){
				subHolder.setVisibility(View.VISIBLE);
			}else{
				subHolder.setVisibility(View.GONE);
			}
			
            holder.addView(layerCheck);
			holder.addView(subHolder);	
			
			ArcGISLayerInfo[] sublayers = layer.getLayers();
			if(sublayers!=null&&sublayers.length>0){
				registerLayerInfos(sublayers, subHolder,visiable);
			}
		}
	}

	public void registerFreatureLayer(ArcGISFeatureLayer featureLayer,String name,
			Query query,SELECTION_METHOD selectmethod,CallbackListener<FeatureSet> callback){
		registerLayer(featureLayer, name);
		if(query!=null)
		    featureLayer.selectFeatures(query, selectmethod, callback);
	}
	
	public void registerGraphicsLayerByQuery(String url,final String name,final int colorID,String where){
		Query query = new Query();
		//query.setWhere("WIID='TDGY201407153'");
		query.setWhere(where);
        //String[] fields = {"*"};
        //query.setOutFields(fields);
		query.setReturnGeometry(true);
		query.setMaxFeatures(1000);
		query.setGeometry(getExtent());	
        
		QueryFeatures queryFeatures = new QueryFeatures(url, name, colorID, query);
		queryFeatures.execute();
	}
	
	class QueryFeatures extends AsyncTask<Void, Void, FeatureSet>{

		private String mUrl;
		private String mName;
		private int mColor;
		private Query mQuery ;
		
		public QueryFeatures(String url,String name,int color,Query query){
			mUrl = url;
			mName = name;
			mColor = color;
			mQuery = query;
		}
		
		@Override
		protected FeatureSet doInBackground(Void... params) {
			FeatureSet fSet = null;
			QueryTask queryTask = new QueryTask(mUrl);
			try {
				fSet = queryTask.execute(mQuery);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return fSet;
		}

		@Override
		protected void onPostExecute(FeatureSet result) {
			if(result!=null){
				GraphicsLayer layer = new GraphicsLayer();
				layer.addGraphics(result.getGraphics());
				layer.setRenderer(new SimpleRenderer(new SimpleFillSymbol(getResources().getColor(mColor))));
				registerLayer(layer,mName);
			}
		}
	}
}
