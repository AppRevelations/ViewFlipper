package com.apprevelations.viewflipper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	private static final int Swipe_Minimum_Distance = 10;
	private static final int Swipe_Threshold_Velocity = 18;
	private ViewFlipper viewFlipper;	
	private AnimationListener animationListener;
	private Context context;
	
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGesture());

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
		
		viewFlipper.setAutoStart(true);
		//Flip Interval- time after which image changes.
		viewFlipper.setFlipInterval(2000);
		
		//Flipping with animation
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,R.anim.left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.left_out));
		
		// controlling animation
	
		viewFlipper.getInAnimation().setAnimationListener(animationListener);
		//Start Flipping 
		viewFlipper.startFlipping();
		
		//On Touch Listener for SwipeGesture
		viewFlipper.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				
					detector.onTouchEvent(event);
					return true;
			}
		});
		
		//Animation listener
		animationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				//When Animation Starts
		      
			}

			public void onAnimationRepeat(Animation animation) {
				//On Repeatation of animation
			}

			@SuppressLint("NewApi")
			public void onAnimationEnd(Animation animation) {
				//After Animation end
				
			}
		};
	}

   //Class for Swipe Gesture 
	class SwipeGesture extends SimpleOnGestureListener {
		@SuppressLint("NewApi")
		@Override
		public boolean onFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
			try {
	
			//right to left swipe
				if (me1.getX() - me2.getX() > Swipe_Minimum_Distance && Math.abs(velocityX) > Swipe_Threshold_Velocity) {
				
					//stop Flipping
					viewFlipper.stopFlipping();
					viewFlipper.getInAnimation().setAnimationListener(animationListener);
					viewFlipper.showNext();
                    
                    viewFlipper.setAutoStart(true);
                    viewFlipper.setFlipInterval(2000);
                    viewFlipper.startFlipping();
					
					return true;
				}
				
			//left to right swipe
				
				else if (me2.getX() - me1.getX() > Swipe_Minimum_Distance && Math.abs(velocityX) > Swipe_Threshold_Velocity) {
					
					viewFlipper.stopFlipping();
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.right_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.right_out));
                    
                    // controlling animation
                    viewFlipper.getInAnimation().setAnimationListener(animationListener);
                    viewFlipper.showPrevious();

                    viewFlipper.setAutoStart(true);
                    viewFlipper.setFlipInterval(2000);
                    viewFlipper.startFlipping();

                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.left_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.left_out));

					return true;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}	
	}
}


