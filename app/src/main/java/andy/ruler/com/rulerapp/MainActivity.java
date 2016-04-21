package andy.ruler.com.rulerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

	private TextView distanceTextView;
	private double ydpi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DisplayMetrics dm  = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		DrawLineView drawView = new DrawLineView(this, dm.heightPixels, ydpi = dm.ydpi);
		drawView.setBackgroundColor(Color.WHITE);
		FrameLayout  layout = (FrameLayout) findViewById(R.id.mainView);
		layout.addView(drawView);
		layout.addView(distanceTextView = new TextView(this));
		distanceTextView.setX(dm.widthPixels / 2 );
		distanceTextView.setY(dm.heightPixels / 3);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		double x1, y1, x2, y2;
		switch(event.getActionMasked()){
			case MotionEvent.ACTION_POINTER_DOWN:{
				if(event.getPointerCount() == 2){
					x1 = event.getX(0);
					y1 = event.getY(0);
					x2 = event.getX(1);
					y2 = event.getY(1);
					distanceTextView.setText(distance(x1,x2,y1,y2));
				}
				break;
			}
			case MotionEvent.ACTION_POINTER_UP:{
				distanceTextView.setText("");
				break;
			}
		}
		return super.onTouchEvent(event);
	}

	private String distance(double x1, double x2, double y1, double y2){
		double distance =  Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / ydpi;
		return "Distance = " + round(distance, 2) + " inches";
	}

	/**
	 * Util method for rounding, got it from
	 * http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 * @param value
	 * @param places
	 * @return
	 */
	private double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
