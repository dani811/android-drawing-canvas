package co.martinbaciga.drawingtest.ui.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.martinbaciga.drawingtest.domain.model.Segment;
import co.martinbaciga.drawingtest.ui.component.DrawingView;
import co.martinbaciga.drawingtest.ui.component.ManipulableImageView;
import co.martinbaciga.drawingtest.ui.component.ManipulableTextView;
import co.martinbaciga.drawingtest.ui.component.ManipulableView;
import co.martinbaciga.drawingtest.ui.interfaces.ManipulableViewEventListener;

public class LayerManager
{
	private Context mContext;
	private FrameLayout mRoot;
	private DrawingView mBaseDrawingView;

	private ArrayList<ManipulableView> mManipulableViews = new ArrayList<>();
	private ArrayList<DrawingView> mDrawingViews = new ArrayList<>();
	private ArrayList<View> mLayers = new ArrayList<>();

	public LayerManager(Context context, FrameLayout root, DrawingView baseDrawingView)
	{
		mContext = context;
		mRoot = root;
		mBaseDrawingView = baseDrawingView;
		mBaseDrawingView.setEnabled(true);

		mDrawingViews.add(mBaseDrawingView);
		mLayers.add(mBaseDrawingView);
	}

	public ManipulableTextView addTextComponent(String text, float textSize, float x, float y, ManipulableViewEventListener listener, String segmentId)
	{
		if (mLayers.size() > 1 && getTopLayer().getClass() == DrawingView.class && ((DrawingView)getTopLayer()).isEmpty())
		{
			removeTopLayer();
		}

		ManipulableTextView tv = new ManipulableTextView(mContext, listener);
		tv.setText(text);
		tv.setTextSize(textSize);
		tv.setControlItemsHidden(true);
		tv.setX(x);
		tv.setY(y);
		tv.setSegmentId(segmentId);
		mRoot.addView(tv);

		mManipulableViews.add(tv);
		mLayers.add(tv);

		//addDrawingLayer();

		return tv;
	}

	public ManipulableTextView addTextComponent(String text, float textSize, int color, float x, float y, int width, int height, String alignment, ManipulableViewEventListener listener, String segmentId)
	{
		if (mLayers.size() > 1 && getTopLayer().getClass() == DrawingView.class && ((DrawingView)getTopLayer()).isEmpty())
		{
			removeTopLayer();
		}

		ManipulableTextView tv = new ManipulableTextView(mContext, listener);
		tv.setText(text);
		tv.setTextSize(textSize);
		tv.setTextColor(color);
		tv.setControlItemsHidden(true);
		tv.setX(x);
		tv.setY(y);
		tv.setSize(width, height);
		tv.setSegmentId(segmentId);

		if (alignment.matches(Segment.TEXT_ALIGN_LEFT))
		{
			tv.setTextGravity(Gravity.LEFT);
		} else if (alignment.matches(Segment.TEXT_ALIGN_CENTER))
		{
			tv.setTextGravity(Gravity.CENTER_HORIZONTAL);
		} else if (alignment.matches(Segment.TEXT_ALIGN_RIGHT))
		{
			tv.setTextGravity(Gravity.RIGHT);
		}

		mRoot.addView(tv);

		mManipulableViews.add(tv);
		mLayers.add(tv);

		//addDrawingLayer();

		return tv;
	}

	public void updateTextComponent(String segmentId, String text, float x, float y, float textSize, int color, int width, int height, String alignment)
	{
		for (ManipulableView mv : mManipulableViews)
		{
			if (mv.getSegmentId().matches(segmentId))
			{
				ManipulableTextView mtv = (ManipulableTextView) mv;
				mtv.setText(text);
				mtv.setX(x);
				mtv.setY(y);
				mtv.setTextSize(textSize);
				mtv.setTextColor(color);
				mtv.setSize(width, height);

				if (alignment.matches(Segment.TEXT_ALIGN_LEFT))
				{
					mtv.setTextGravity(Gravity.LEFT);
				} else if (alignment.matches(Segment.TEXT_ALIGN_CENTER))
				{
					mtv.setTextGravity(Gravity.CENTER_HORIZONTAL);
				} else if (alignment.matches(Segment.TEXT_ALIGN_RIGHT))
				{
					mtv.setTextGravity(Gravity.RIGHT);
				}
			}
		}
	}

	public ManipulableImageView addImageComponent(Bitmap bitmap, ManipulableViewEventListener listener, String segmentId)
	{
		if (mLayers.size() > 1 && getTopLayer().getClass() == DrawingView.class && ((DrawingView)getTopLayer()).isEmpty())
		{
			removeTopLayer();
		}

		ManipulableImageView iv = new ManipulableImageView(mContext, listener);
		iv.setImageBitmap(bitmap);
		iv.setControlItemsHidden(true);
		iv.setSegmentId(segmentId);
		mRoot.addView(iv);

		mManipulableViews.add(iv);
		mLayers.add(iv);

		//addDrawingLayer();

		return iv;
	}

	public ManipulableImageView addImageComponent(String url, ManipulableViewEventListener listener, String segmentId)
	{
		if (mLayers.size() > 1 && getTopLayer().getClass() == DrawingView.class && ((DrawingView)getTopLayer()).isEmpty())
		{
			removeTopLayer();
		}

		ManipulableImageView iv = new ManipulableImageView(mContext, listener);
		iv.setImageUrl(mContext, url);
		iv.setControlItemsHidden(true);
		iv.setSegmentId(segmentId);
		mRoot.addView(iv);

		mManipulableViews.add(iv);
		mLayers.add(iv);

		//addDrawingLayer();
		return iv;
	}

	public ManipulableImageView addImageComponent(String url, float x, float y, int width, int height, ManipulableViewEventListener listener, String segmentId)
	{
		if (mLayers.size() > 1 && getTopLayer().getClass() == DrawingView.class && ((DrawingView)getTopLayer()).isEmpty())
		{
			removeTopLayer();
		}

		ManipulableImageView iv = new ManipulableImageView(mContext, listener);
		iv.setImageUrl(mContext, url);
		iv.setX(x);
		iv.setY(y);
		iv.setSize(width, height);
		iv.setControlItemsHidden(true);
		iv.setSegmentId(segmentId);
		mRoot.addView(iv);

		mManipulableViews.add(iv);
		mLayers.add(iv);

		//addDrawingLayer();
		return iv;
	}

	public void updateImageComponent(String segmentId, float x, float y, int width, int height)
	{
		for (ManipulableView mv : mManipulableViews)
		{
			if (mv.getSegmentId().matches(segmentId))
			{
				ManipulableImageView miv = (ManipulableImageView) mv;
				miv.setX(x);
				miv.setY(y);
				miv.setSize(width, height);
			}
		}
	}

	public void removeManipulableView(String segmentId)
	{
		for (int i = 0; i < mManipulableViews.size(); i++)
		{
			if (mManipulableViews.get(i).getSegmentId().matches(segmentId))
			{
				mRoot.removeView(mManipulableViews.get(i));
				mLayers.remove(mManipulableViews.get(i));
				mManipulableViews.remove(i);
			}
		}
	}

	public void addDrawingLayer()
	{
		DrawingView drawingView = new DrawingView(mContext, true);
		mRoot.addView(drawingView);
		drawingView.setBackgroundColor(Color.TRANSPARENT);

		disableDrawingViews();

		mDrawingViews.add(drawingView);
		mLayers.add(drawingView);
	}

	public void disableDrawingViews()
	{
		for (DrawingView dv : mDrawingViews)
		{
			dv.setEnabled(false);
		}
	}

	public void disableTopDrawingView()
	{
		mDrawingViews.get(mDrawingViews.size()-1).setEnabled(false);
	}

	public View getRoot()
	{
		return mRoot;
	}

	public ManipulableView getManipulableView(String segmentId)
	{
		for (ManipulableView mv : mManipulableViews)
		{
			if (mv.getSegmentId().matches(segmentId))
			{
				return mv;
			}
		}

		return null;
	}

	public ManipulableView getTopManipulableView()
	{
		return mManipulableViews.get(mManipulableViews.size()-1);
	}

	public void enableTopDrawingView()
	{
		mDrawingViews.get(mDrawingViews.size()-1).setEnabled(true);
	}

	private View getTopLayer()
	{
		return mLayers.get(mLayers.size()-1);
	}

	private void removeTopLayer()
	{
		mRoot.removeView(getTopLayer());
		mLayers.remove(mLayers.size()-1);
		mDrawingViews.remove(mDrawingViews.size()-1);
	}

}
