package com.mcy.mobile.exags;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry.Type;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.STYLE;
import com.esri.core.symbol.Symbol;
/**
 * 扩展可聚类显示的GraphicsLayer
 * 依据显示比例尺将点数据聚类显�?
 * @author mengchaoyue
 *
 */
public class ClusterGraphicsLayer extends GraphicsLayer {

	//是否进行空间聚合
	private boolean isCluster = false;

	//聚合字段
	private String clusterField;
	
	//聚合要素
	private ArrayList<Graphic> mGraphics;
	
	//聚合线程
	private Cluster mCluster;
	
	//--------------构�?�函�?--------------------------
	public ClusterGraphicsLayer(Graphic[] graphics) {
		super();
		mGraphics = new ArrayList<Graphic>();
		if(isCluster){
			mGraphics.addAll(mGraphics);
		}else
			addGraphics(graphics);
	}
	public ClusterGraphicsLayer(boolean initLayer) {
		super(initLayer);
	}
	public ClusterGraphicsLayer(long handle) {
		super(handle);
	}
	public ClusterGraphicsLayer(MarkerRotationMode rotationMode) {
		super(rotationMode);
	}
	public ClusterGraphicsLayer(RenderingMode mode) {
		super(mode);
	}
	public ClusterGraphicsLayer(SpatialReference sr, Envelope fullextent,
			RenderingMode mode) {
		super(sr, fullextent, mode);
	}
	public ClusterGraphicsLayer(SpatialReference sr, Envelope fullextent) {
		super(sr, fullextent);
	}
	
	@Override
	public int[] addGraphics(Graphic[] graphics) {
		if(isCluster){
			mGraphics.addAll(mGraphics);
			return new int[]{-1};
		}else
		    return super.addGraphics(graphics);
	}

	private void isCluster(boolean isCluster) {
		this.isCluster = isCluster;
		
		//图层状�?�监�?
		OnStatusChangedListener listener = new OnStatusChangedListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onStatusChanged(Object source, STATUS status) {
				switch (status) {
				case INITIALIZED:
					mCluster.start();
					//mCluster.run();
					break;
				default:
					break;
				}
			}
		};
		setOnStatusChangedListener(listener);
	}
	
	public void setCluster(Cluster cluster){
		mCluster = cluster;
		isCluster(true);
	}
	
	public String getClusterField() {
		return clusterField;
	}
	public void setClusterField(String clusterField) {
		this.clusterField = clusterField;
	}
	

	public void zoomIn(){
		if(mCluster.isAlive()){
			
		}else{
			mCluster.zoomIn = true;
			mCluster.start();
		}
	}
	
	public void zoomOut(){
		if(mCluster.isAlive()){
			
		}else{
			mCluster.zoomIn = false;
			mCluster.start();
		}
	}
	
	/**
	 * 对要素进行聚�?
	 */
	protected class Cluster extends Thread {

		private int screenW;
		private int screenH;
		private Symbol clusterSymbol;//聚类后渲染符�?
		private int markW,markH = 10;//符号尺寸
		private int tolerance = 2;
		private int m,n;//屏幕划分网格�?
		private int[][] screen;//聚类数组
		//未显示的要素
		@SuppressWarnings("unused")
		private List<Graphic> mTempGraphics;
		public boolean zoomIn = false;
		
		public Cluster(int w,int h){
			super();
			screenW = w;
			screenH = h;
			clusterSymbol = new SimpleMarkerSymbol(Color.RED,10,STYLE.CIRCLE);
			m = screenW/(markW+tolerance);
			n = screenH/(markH+tolerance);
            screen = new int[m][n];//java整形数组默认初始化全�?0
            mTempGraphics = new ArrayList<Graphic>();
		}
		
		public void setSymble(SimpleMarkerSymbol s){
			clusterSymbol = s;
			markW = markH = (int)s.getSize();
		}
		
		@Override
		public void run(){
			//状�?�数组重�?
			for(int i = 0;i<m;i++){
				for(int j = 0;j<n;j++){
					screen[i][j] = 0;
				}
			}
			
			//聚合
			for(Graphic g:mGraphics){
				Type geoType = g.getGeometry().getType();
				
				if(geoType==Type.POINT){
					double gx = ((Point)g.getGeometry()).getX();
					double gy = ((Point)g.getGeometry()).getY();
					Polygon curExtent = getExtent();
					//判断是否在当前屏幕显示范�?
					if(isInExtent(gx, gy, curExtent)){
					    android.graphics.Point screenPoint = mapToScreen(gx, gy, curExtent);
						int i = screenPoint.x/(markW+tolerance);
						int j = screenPoint.y/(markH+tolerance);
						if(screen[i][j]<1){
							Graphic cg = new Graphic(g.getGeometry(),clusterSymbol);
							addGraphic(cg);
						}else{
							//加入缓存列表
							//mTempGraphics.add(g);
							//从当前列表删�?
							//mGraphics.remove(g);
						}
                        screen[i][j] += 1;
					}
				}else{
					//如果不是点类型，直接加入图层
					addGraphic(g);
				}
			}
			//TODO 优化缓存策略
//			if(zoomIn){
//				for(Graphic g:mTempGraphics){
//					Type geoType = g.getGeometry().getType();
//					
//					if(geoType==Type.POINT){
//						double gx = ((Point)g.getGeometry()).getX();
//						double gy = ((Point)g.getGeometry()).getY();
//						Polygon curExtent = getExtent();
//						//判断是否在当前屏幕显示范�?
//						if(isInExtent(gx, gy, curExtent)){
//						    android.graphics.Point screenPoint = mapToScreen(gx, gy, curExtent);
//							int i = screenPoint.x/(markW+tolerance);
//							int j = screenPoint.y/(markH+tolerance);
//							if(screen[i][j]<1){
//								Graphic cg = new Graphic(g.getGeometry(),clusterSymbol);
//								addGraphic(cg);
//							}else{
//								//加入缓存列表
//								mTempGraphics.add(g);
//								//从当前列表删�?
//								mGraphics.remove(g);
//							}
//	                        screen[i][j] += 1;
//						}
//					}else{
//						//如果不是点类型，直接加入图层
//						addGraphic(g);
//					}
//				}
//			}else{
//				for(Graphic g:mGraphics){
//					Type geoType = g.getGeometry().getType();
//					
//					if(geoType==Type.POINT){
//						double gx = ((Point)g.getGeometry()).getX();
//						double gy = ((Point)g.getGeometry()).getY();
//						Polygon curExtent = getExtent();
//						//判断是否在当前屏幕显示范�?
//						if(isInExtent(gx, gy, curExtent)){
//						    android.graphics.Point screenPoint = mapToScreen(gx, gy, curExtent);
//							int i = screenPoint.x/(markW+tolerance);
//							int j = screenPoint.y/(markH+tolerance);
//							if(screen[i][j]<1){
//								Graphic cg = new Graphic(g.getGeometry(),clusterSymbol);
//								addGraphic(cg);
//							}else{
//								//加入缓存列表
//								mTempGraphics.add(g);
//								//从当前列表删�?
//								mGraphics.remove(g);
//							}
//	                        screen[i][j] += 1;
//						}
//					}else{
//						//如果不是点类型，直接加入图层
//						addGraphic(g);
//					}
//				}
//			}
		}

		/**
		 * 地理坐标向屏幕坐标转�?
		 * @param x
		 * @param y
		 * @param curExtent
		 * @return
		 */
		private android.graphics.Point mapToScreen(double x,double y,Polygon curExtent){
			Envelope env = new Envelope();
			curExtent.queryEnvelope(env);
			double factorx =  (env.getXMax() - env.getXMin())/screenW;
			double factory =  (env.getYMax() - env.getYMin())/screenH;
			int screenX = (int) ((x-env.getXMin())/factorx);
			int screenY = (int) ((y-env.getYMin())/factory);
			return new android.graphics.Point(screenX,screenY);
		}
		
		/**
		 * 判断点是否在当前显示范围
		 * @param gx
		 * @param gy
		 * @param curExtent
		 * @return
		 */
		private boolean isInExtent(double gx, double gy, Polygon extent) {
			
			Envelope env = new Envelope();
			extent.queryEnvelope(env);
			
			if(gx>env.getXMin()&&gx<env.getXMax()&&gy<env.getYMax()&&gy>env.getXMin())
				return true;
			return false;
		}
	}
}
