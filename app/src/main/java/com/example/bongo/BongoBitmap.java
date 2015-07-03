package com.example.bongo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.widget.Toast;

public class BongoBitmap extends ImageView implements OnTouchListener {
	public Context mycontext;
	private Canvas myCanvas;
	public float x = 0, y = 0;// 854x480
	public float rx;
	public float ry;
	private Handler myHandler;
	private final int FRAME_RATE = 30;
	private int touched = 0;
	BitmapDrawable b1,b2;
	
	AudioManager myAudioManager=(AudioManager)getContext().getSystemService(Context.AUDIO_SERVICE);
	float actualVolume=(float)myAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	float maxVolume=(float)myAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	float volume=actualVolume/maxVolume;
	public SoundPool mySoundPool;
	private int[] soundId;
	boolean loaded=false;

	public BongoBitmap(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mycontext = context;
		mySoundPool=new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		soundId=new int[11];
		soundId[0] = mySoundPool.load(getContext(),R.raw.bleftcenter,1);
		soundId[1] = mySoundPool.load(getContext(),R.raw.bleftupper,1);
		soundId[2] = mySoundPool.load(getContext(),R.raw.bleftlower,1);
		soundId[3] = mySoundPool.load(getContext(),R.raw.brightinner,1);
		soundId[4] = mySoundPool.load(getContext(),R.raw.brightouter,1);
		soundId[5] = mySoundPool.load(getContext(),R.raw.chalangai,1);
		soundId[6] = mySoundPool.load(getContext(),R.raw.chime,1);
		soundId[7] = mySoundPool.load(getContext(),R.raw.tamourine,1);
		soundId[8] = mySoundPool.load(getContext(),R.raw.shaker,1);
		soundId[9] = mySoundPool.load(getContext(),R.raw.cow,1);
		soundId[10] = mySoundPool.load(getContext(),R.raw.gong,1);
		
		myHandler = new Handler();
		b1 = (BitmapDrawable)getContext().getResources().getDrawable(R.drawable.doubledrum_left_hl);
		b2 = (BitmapDrawable)getContext().getResources().getDrawable(R.drawable.doubledrums_right_hl);
	}
	
    private Runnable r = new Runnable() {
		
		public void run() {
			invalidate();
		}
	};
	
	public Bitmap resizeImage(Bitmap image) {
		Bitmap resized;
		float imageHeight, imageWidth;
		imageHeight=image.getHeight()*ry;
		imageWidth=image.getWidth()*rx;
		resized=Bitmap.createScaledBitmap(image, (int) imageWidth, (int)imageHeight, true);
		return resized;
	}

	@Override
	protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
		super.onSizeChanged(xNew, yNew, xOld, yOld);
		rx = (float) xNew / 854;
		ry = (float) yNew / 480;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		myHandler.post(new Runnable() {


			public void run() {
				// TODO Auto-generated method stub
				BongoBitmap.this.setVisibility(View.VISIBLE);
			}
		});
		x = event.getX();
		y = event.getY();
		touched = 1;
		return false;
	}
	
	protected void onDraw(Canvas c) {
		BongoBitmap.this.myCanvas = c;

        if (touched == 1) {
//            Toast.makeText(getContext(),"x: "+x/rx+" y: "+y/ry,Toast.LENGTH_SHORT).show();
            //left part of the left drum that includes both top and bottom
            if ( (x>81*rx && x<216*rx && y>393*ry && y<436*ry) || (x>53*rx && x<216*rx && y>324*ry && y<394*ry) ||
                    (x>53*rx && x<216*rx && y>259*ry && y<323*ry) || (x>91*rx && x<213*rx && y>200*ry && y<258*ry))
            {
                x = 241 * rx;
                y = 319 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.doubledrum_left_hl);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[0], volume, volume, 1, 0, 1f);
            }
            //right part of the left drum that includes bottom alone
            if ( (x>214*rx && x<374*rx && y>401*ry && y<436*ry) || (x>214*rx && x<430*rx && y>335*ry && y<402*ry))
            {
                x = 241 * rx;
                y = 319 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.doubledrum_left_hl);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[2], volume, volume, 1, 0, 1f);
            }
            //right part of the left drum that includes top alone
            if ( (x>216*rx && x<422*rx && y>227*ry && y<311*ry) || (x>216*rx && x<353*rx && y>205*ry && y<228*ry))
            {
                x = 241 * rx;
                y = 319 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.doubledrum_left_hl);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[1], volume, volume, 1, 0, 1f);
            }
            //left portion of the right drum
            if ((x>506*rx && x<636*rx && y>371*ry && y<425*ry)||(x>506*rx && x<636*rx && y>313*ry && y<370*ry)||
                    (x>468*rx && x<636*rx && y>247*ry && y<314*ry) || (x>530*rx && x<636*rx && y>232*ry && y<247*ry))
            {
                x = 637 * rx;
                y = 320 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.doubledrums_right_hl);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[3], volume, volume, 1, 0, 1f);
            }
            //right portion of the right drum
            if ((x>637*rx && x<733*rx && y>396*ry && y<425*ry)||(x>637*rx && x<784*rx && y>333*ry && y<395*ry)||
                    (x>637*rx && x<784*rx && y>255*ry && y<332*ry) || (x>637*rx && x<727*rx && y>232*ry && y<254*ry))
            {
                x = 637 * rx;
                y = 320 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.doubledrums_right_hl);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[4], volume, volume, 1, 0, 1f);
            }
			//jinjak
			if(x>45*rx && x<153*rx && y>85*ry && y<175*ry){
				x = 80 * rx;
				y = 138 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.jinjak);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[5], volume, volume, 1, 0, 1f);
			}
			//chimes
			if(x>170*rx && x<339*rx && y>105*ry && y<183*ry){
				x = 256 * rx;
				y = 136 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.chimes);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[6], volume, volume, 1, 0, 1f);
			}
            //trium
			if(x>364*rx && x<471*rx && y>85*ry && y<186*ry){
				x = 417 * rx;
				y = 134 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.trium);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[7], volume, volume, 1, 0, 1f);
			}
            //shakers
			if(x>494*rx && x<562*rx && y>81*ry && y<183*ry){
				x = 526 * rx;
				y = 132 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.shakers1);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[8], volume, volume, 1, 0, 1f);
			}
            //cowbell
			if(x>585*rx && x<663*rx && y>80*ry && y<180*ry){
				x = 623 * rx;
				y = 130 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.cowbell1);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[9], volume, volume, 1, 0, 1f);
			}
            //gong
			if(x>694*rx  && x<770*rx && y>90*ry && y<165*ry){
				x = 732 * rx;
				y = 136 * ry;
                this.setVisibility(View.VISIBLE);
                final Bitmap currentBitmap;
                currentBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.gong);
                Bitmap scaledBitmap=resizeImage(currentBitmap);
                c.drawBitmap(scaledBitmap, x - scaledBitmap.getWidth() / 2, y - scaledBitmap.getHeight() / 2, null);
                mySoundPool.play(soundId[10], volume, volume, 1, 0, 1f);
			}
			myHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					BongoBitmap.this.setVisibility(View.VISIBLE);
				}
			}, 150);
			touched=0;
			}
		this.setOnTouchListener(this);
		myHandler.postDelayed(r, FRAME_RATE);
}
}
