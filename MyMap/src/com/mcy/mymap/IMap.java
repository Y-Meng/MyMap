package com.mcy.mymap;

import java.util.List;

import com.mcy.mybean.RwQxRecord;
import com.mcy.mybean.RwPointRecord;

import android.location.Location;

public interface IMap {

	public Location getLocation();
	public void addExceptionPoints(List<RwQxRecord> data);
	public void addPatrolPoints(List<RwPointRecord> data);
	public void addException(RwQxRecord record);
	public void addPatrol(RwPointRecord record);
}
