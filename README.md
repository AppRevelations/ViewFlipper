ViewFlipper
===========
With ViewFlipper you can animate between two or more views that have been added to it,here we added 
three RelativeLayout as you will see in activity_main.xml.Only one child is shown at a time.ViewFlipper
can automatically flip between each child at a regular interval. You can change views by swiping also.

        //ViewFlipper starts Automatically with time interval of 2s.
        
        viewFlipper.setAutoStart(true);   
        viewFlipper.setFlipInterval(2000);
       
        //Flipping with animation
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,R.anim.left_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,R.anim.left_out));
       
        //Start Flipping
        viewFlipper.startFlipping();
        
For changing views by swiping you have to add SwipeGesture.
   
   //On Touch Listener for SwipeGesture
        viewFlipper.setOnTouchListener(new OnTouchListener() {
           
            @SuppressLint("NewApi")
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
               
                    detector.onTouchEvent(event);
                    return true;
            }
        });
        
        
onFling Function to detect your swipe directions.
Swipe_Minimum_Distance:-Minimum distance you set for swiping.
Swipe_Threshold_Velocity:-Minimum velocity with which user have to swipe.
   public boolean onFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) 
   {
      try {
   
      //right to left swipe
          if (me1.getX() - me2.getX() > Swipe_Minimum_Distance && Math.abs(velocityX) > Swipe_Threshold_Velocity) 
          {
              
                    return true;
          }
               
      //left to right swip
         else if (me2.getX() - me1.getX() > Swipe_Minimum_Distance && Math.abs(velocityX) >Swipe_Threshold_Velocity) 
         {
                   
                  
                    return true;
          }
          
          } 
    }    
