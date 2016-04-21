package andy.ruler.com.rulerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Andy on 4/17/2016.
 */
public class DrawLineView extends View {

	//Constans
	private static final int INCHES_LENGTH = 400;
	private static final int ONE_EIGHTH_INCH = 100;
	private static final int QUARTER_INCH= 200;
	private static final int HALF_INCH = 300;
	private static final int xOffSet = 30;
	private static final int TEXT_Y_OFFSET = 20;

	private Paint paint;
	private double heightPixels, ydpi;

	public DrawLineView(Context context, double heightPixels, double ydpi){
		super(context);
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(10);
		this.heightPixels = heightPixels;
		this.ydpi = ydpi;
	}

	/**
	 * Draw method, draws the line
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		double oneEighth = ydpi / 8;
		float y = 0;
		int inches = 0;
		while( y <= heightPixels){
			switch(inches % 1000){
				case 375:
				case 625:
				case 875:
				case 125:{
					paint.setTextSize(50);
					canvas.drawLine(0, y, ONE_EIGHTH_INCH, y, paint);
					break;
				}
				case 750:
				case 250:{
					paint.setTextSize(55);
					canvas.drawLine(0, y, QUARTER_INCH, y, paint);
					canvas.drawText(Double.toString(inchConvertUtil(inches)), QUARTER_INCH + TEXT_Y_OFFSET, y + xOffSet, paint);
					break;
				}
				case 500:{
					paint.setTextSize(60);
					canvas.drawLine(0, y, HALF_INCH, y, paint);
					canvas.drawText(Double.toString(inchConvertUtil(inches)), HALF_INCH + TEXT_Y_OFFSET, y + xOffSet, paint);
					break;
				}
				case 0:{
					int extraOffSet = 0;
					if(inches == 0){
						extraOffSet = 30;
					}
					paint.setTextSize(80);
					canvas.drawLine(0, y, INCHES_LENGTH, y, paint);
					canvas.drawText(Double.toString(inchConvertUtil(inches)), INCHES_LENGTH + TEXT_Y_OFFSET,y + xOffSet + extraOffSet, paint);
					break;
				}
				default: new IllegalArgumentException();
			}
			y += oneEighth;
			inches += 125;

		}
	}

	/**
	 * Util converter
	 * @param num
	 * @return
	 */
	private double inchConvertUtil(int num){
		return num / 1000.0;
	}
}
