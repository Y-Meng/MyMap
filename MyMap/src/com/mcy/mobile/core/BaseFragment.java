package com.mcy.mobile.core;

import java.lang.reflect.Field;

import com.mcy.mobile.injection.EventListener;
import com.mcy.mobile.injection.InjectVandM;
import com.mcy.mobile.injection.InjectView;
import com.mcy.mobile.injection.Select;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

/**
 * Base class for fragments
 * 
 */
public abstract class BaseFragment extends Fragment {

	protected int viewLayoutID = 0;

	protected static String TAG = "BaseFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestory");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.d(TAG, "onDestoryView");
		super.onDestroyView();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG, "onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		Log.d(TAG, "onDetach");
		super.onDetach();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		if (viewLayoutID == 0)
			return super.onCreateView(inflater, container, savedInstanceState);
		else
			return inflateView(inflater, container, viewLayoutID);
	}

	/**
	 * Inflate view for fragment.
	 * 
	 * @param inflater
	 * @param container
	 * @param layoutID
	 * @return
	 */
	protected View inflateView(LayoutInflater inflater, ViewGroup container,
			int layoutID) {

		View rootView = inflater.inflate(layoutID, container, false);
		Log.d(TAG, "inject view");
		injectView(this, rootView);
		Log.d(TAG, "init view");
		initView();
		return rootView;

	}

	/**
	 * Abstract method which will called after inflating view.
	 */
	protected abstract void initView();

	/**
	 * Use it like avtivity.setContentView() in or before onCreateView().
	 */
	protected void setContentView(int layoutId) {
		viewLayoutID = layoutId;
	}

	/**
	 * Inject views in viewroot.
	 * 
	 * @param target
	 * @param root
	 */
	private void injectView(Object target, View root) {
		Field[] fields = target.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					if (field.get(target) != null)
						continue;
					InjectVandM injectVandM = field
							.getAnnotation(InjectVandM.class);
					if (injectVandM != null) {
						field.set(target, root.findViewById(injectVandM.id()));

						setListener(target, field, injectVandM.click(),
								Method.Click);
						setListener(target, field, injectVandM.longClick(),
								Method.LongClick);
						setListener(target, field, injectVandM.itemClick(),
								Method.ItemClick);
						setListener(target, field, injectVandM.itemLongClick(),
								Method.itemLongClick);

						Select select = injectVandM.select();
						if (!TextUtils.isEmpty(select.selected())) {
							setViewSelectListener(target, field,
									select.selected(), select.noSelected());
						}
					} else {
						InjectView injectView = field
								.getAnnotation(InjectView.class);
						if (injectView != null) {
							int viewId = injectView.value();
							field.set(target, root.findViewById(viewId));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void setViewSelectListener(Object injectedSource,
			Field field, String select, String noSelect) throws Exception {
		Object obj = field.get(injectedSource);
		if (obj instanceof View) {
			((AbsListView) obj).setOnItemSelectedListener(new EventListener(
					injectedSource).select(select).noSelect(noSelect));
		}
	}

	private static void setListener(Object injectedSource, Field field,
			String methodName, Method method) throws Exception {
		if (methodName == null || methodName.trim().length() == 0)
			return;

		Object obj = field.get(injectedSource);

		switch (method) {
		case Click:
			if (obj instanceof View) {
				((View) obj).setOnClickListener(new EventListener(
						injectedSource).click(methodName));
			}
			break;
		case ItemClick:
			if (obj instanceof AbsListView) {
				((AbsListView) obj).setOnItemClickListener(new EventListener(
						injectedSource).itemClick(methodName));
			}
			break;
		case LongClick:
			if (obj instanceof View) {
				((View) obj).setOnLongClickListener(new EventListener(
						injectedSource).longClick(methodName));
			}
			break;
		case itemLongClick:
			if (obj instanceof AbsListView) {
				((AbsListView) obj)
						.setOnItemLongClickListener(new EventListener(
								injectedSource).itemLongClick(methodName));
			}
			break;
		default:
			break;
		}
	}

	public enum Method {
		Click, LongClick, ItemClick, itemLongClick
	}

	protected void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T $(View v, int id) {
		return (T) v.findViewById(id);
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T $(int id) {
		return (T) getView().findViewById(id);
	}
}
