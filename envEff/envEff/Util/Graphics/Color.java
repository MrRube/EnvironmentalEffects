package envEff.Util.Graphics;

public class Color {
	
	private int red;
	private int green;
	private int blue;
	private int alpha = 128;
	public EnumColors lastColor = EnumColors.RED;
	
	public Color ( int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
		lastColor = this.closestColor();
	}
	
	public Color(int red, int green, int blue, int alpha){
		this(red, green, blue);
		this.alpha = alpha;
	}
	
	public Color(EnumColors s){
		this.red = s.red;
		this.green = s.green;
		this.blue = s.blue;
		lastColor = s;
	}
	
	public Color(EnumColors s, int alpha){
		this(s);
		this.alpha = alpha;
	}
	
	public void setRed(int i){
		this.red = i;
	}
	
	public void setGreen(int i){
	this.green = i;
	}
	
	public void setBlue(int i){
		this.blue = i;
	}
	public void setAlpha(int i){
		this.alpha = i;
	}
	
	public void setColor(EnumColors c){
		lastColor = c;
		this.red = c.red;
		this.green = c.green;
		this.blue = c.blue;
	}
	public int[] getColors(){
		return new int[]{red, green,blue, alpha};
	}
	
	public void incRed(int i){
		this.red += i;
	}
	public void incGreen(int i){
		this.green += i;
	}
	public void incBlue(int i){
		this.blue +=i;
	}
	public void incAlpha(int i){
		this.alpha +=i;
	}
	
	public boolean equals(EnumColors c){
		if(c.red == this.red && c.green == this.green && this.blue == c.blue){
			this.lastColor = c;
			return true;
		}
		return false;
	}
	
	private int closestTo(int i){
		if(i >= 0 && i <= 63){
			return 0;
		}
		else if(i > 63 && i<= 127){
			return 127;
		}
		else{
			return 255;
		}
		
	}
	
	public EnumColors closestColor(){
		int red = closestTo(this.red);
		int green = closestTo(this.green);
		int blue = closestTo(this.blue);
		
		return ColorShifter.instance.ColorMap.get(new int[]{red,green,blue});
	}

}
