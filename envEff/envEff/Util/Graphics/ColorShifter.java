package envEff.Util.Graphics;

import java.util.HashMap;

public class ColorShifter {

	public static ColorShifter instance = new ColorShifter(1337);
	public HashMap<int[], EnumColors> ColorMap = new HashMap<int[], EnumColors>();
	private ColorShifter(int i){
		this();
	}
	
	public ColorShifter(){
		//System.out.println("Hue Huea");
	for(EnumColors color : EnumColors.values()){
		ColorMap.put(color.rgbValues(), color);
	}
//		ColorMap.put(new int[]{255,0,0}, EnumColors.RED);
//		ColorMap.put(new int[]{255,127,0}, EnumColors.ORANGE);
//		ColorMap.put(new int[]{255,255,0}, EnumColors.YELLOW);
//		ColorMap.put(new int[]{127,255,0}, EnumColors.LGREEN);
//		ColorMap.put(new int[]{0,255,0}, EnumColors.GREEN);
//		ColorMap.put(new int[]{0,255,127}, EnumColors.TURQOISE);
//		ColorMap.put(new int[]{0,255,255}, EnumColors.CYAN);
//		ColorMap.put(new int[]{0, 127,255}, EnumColors.AUQUAMARINE);
//		ColorMap.put(new int[]{0, 0,255}, EnumColors.BLUE);
//		ColorMap.put(new int[]{127, 0,255}, EnumColors.VIOLET);
//		ColorMap.put(new int[]{255,)
	//System.out.println(ColorMap.keySet());
	};
	
	public static boolean shiftColor(Color c, EnumColors end){
		int[] color = c.getColors();
		
		if(color[0] != end.red){
			if(color[0] < end.red){
				c.incRed(1);
			}
			else{
				c.incRed(-1);
			}
		}
		if(color[1] != end.green){
			if(color[1]< end.green){
				c.incGreen(1);
			}
			else{
				c.incGreen(-1);
			}
		}
		if(color[2] != end.blue){
			if(color[2] < end.blue){
				c.incBlue(1);
			}
			else{
				c.incBlue(-1);
			}
		}
		if(c.equals(end)){			
			return true;
		}
		return false;
	}
	
	public static boolean shiftColorsRange(Color c, EnumColors end, boolean direction){
		if(c.lastColor == end){
			return true;
		}
		EnumColors targetColor = findNextColor(c, direction);
		if(targetColor != end){
			return shiftColor(c, targetColor);
		}
		else{
			return shiftColor(c, end);
		}
	}
	
	public static EnumColors findNextColor(Color c, boolean direction){			
		if(direction){
			if(c.lastColor == EnumColors.RASPBERRY){
				return EnumColors.RED;
			}
			return EnumColors.get(c.lastColor.ordinal()  + 1);
		}
		else{
			if(c.lastColor == EnumColors.RED){
				return EnumColors.RASPBERRY;
			}
			return EnumColors.get(c.lastColor.ordinal() -1);
		}
	}
	

}
