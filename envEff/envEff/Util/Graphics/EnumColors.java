package envEff.Util.Graphics;

import java.util.EnumSet;
import java.util.HashMap;

public enum EnumColors {
	RED(255, 0, 0),
	ORANGE(255,127, 0),
	YELLOW(255, 255, 0),
	LGREEN(127,255,0),
	GREEN(0,255,0),
	TURQOISE(0,255,127),
	CYAN(0,255,255),
	AUQUAMARINE(0,127,255),
	BLUE(0,0,255),	
	VIOLET(127,0,255),
	MAGENTA(255,0,255),
	RASPBERRY(255,0,127);
	
	public final int red;
	public final int green;
	public final int blue;
	private static final HashMap<Integer, EnumColors> lookup = new HashMap<Integer, EnumColors>();
	static{
		for(EnumColors c: EnumSet.allOf(EnumColors.class)){
			lookup.put(c.ordinal(), c);
		}
	}
	EnumColors(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public int[] rgbValues(){
		return new int[]{red,green,blue};
	}
	public static EnumColors get(int i ){
		return lookup.get(i);
	}

}
