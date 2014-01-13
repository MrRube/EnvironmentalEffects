package envEff.Util.Graphics;



public class Node{
	private static int subNodes;
	private static int nodeLength;
	private float angle;
	public float posX = 1;	
	public float posZ = 1;
	public float posY = 7;
	private float modZ = 0;
	private float modX = 0;
	private float modY = 0;
	private float tetX = 0;
	private float tetX2 = 0;
	private float tetZ = 0;
	private float tetZ2 = 0;
	public float width = 30;
	public int alpha = 1;
	private int modA = 0;
	public Color color = new Color(EnumColors.TURQOISE);
	
	private boolean shouldRender = true;
	
	
	public Node(float x, float z, float theta){
		posX = x;	
		posZ = z;
		angle = theta;
	}
	
	public Node(float x,float y,  float z, float theta){
		this(x, z, theta);
		posY = y;
	}
	
	public Node(float x, float y, float z, float theta, int alph){
		this(x,y,z, theta);
		alpha = alph;
	}
	public Node(float x,float  y, float z, float theta, EnumColors c){
		this(x,y,z,theta);
		color.setColor(c);
	}
	public Node(float x, float y, float z, float theta, EnumColors c, int alph){
		this(x,y,z,theta,c);
		alpha = alph;
	}
	public void setTet(float x, float x2, float z, float z2){
		tetX = x;
		tetX2 = x2;
		tetZ = z;
		tetZ2 = z2;
	}
	public float[] getTet(){
		float[] f = {tetX, tetX2, tetZ, tetZ2};
		return f;
	}
	public void setModAlph(int f){
		modA = f;
	}
	public void setModZ(float f){
		modZ = f;
	}
	public void setModX (float f){
		modX = f;
	}
	public void setModY(float f){
		modY = f;
	}
	
	public float getModdedZ(){
		return posZ + modZ;
	}
	
	public float getModdedX(){
		
		return posX + modX;
	}
	
	public float getModdedY(){
		float y = posY + modY;
		if(y < 0){
			y = 0;
		}
		return y;
	}
	
	public int getModdedAlph(){
		return alpha + modA;
	}
	
	public void setAngle(float f){
		angle = f;
	}
	
	public void setPosition(float x,  float y, float z){
		this.setPosition(x, z);
		posY = y;
	}
	public void setPosition(float x, float z){
		posX = x;
		posZ = z;
	}
	public void setPosistionAndAngle(float x, float z, float angle){
		this.setPosition(x, z);
		this.setAngle(angle);
	}
	public float getAngle(){
		return angle;
	}
	
	public float[] getPosition(){
		float[] f = {posX, posZ};
		
		return f;
	}
	
	public static int nodDistance(CNode node1, CNode node2){
		float dX = node1.posX - node2.posX;
		float dZ = node1.posZ - node2.posZ;
		double sum = Math.pow(Math.pow(dX, 2) + Math.pow(dZ, 2), .5);
		//System.out.println(((Double)sum).toString());
		return (int)sum;
	}
	public void translateNextNode(CNode node){
		float tranX = posX;
		float tranZ = posZ;
		for(int i = 0; i < subNodes; i ++){
			tranZ += Math.sin(angle/57.295779513082320876798154814105)*nodeLength;
			tranX += Math.cos(angle/57.295779513082320876798154814105)*nodeLength;
		}
		node.setPosition(tranZ, tranX);
	}
	public void printNode(){
		System.out.println( "X = " + posX + " Z = " + posZ);
	}
	public void addX(float f){
		this.posX += f;
	}
	
	public void addZ(float f){
		this.posZ += f;
	}
	public void addA(int a){
		if(this.alpha >= 1){
			this.alpha += a;
		}
	}
	public boolean shouldRender(){
		
		return shouldRender;
	}
	public void stopRendering(){
		shouldRender = false;
	}
//	@Override
//	public String name() {
//		// TODO Auto-generated method stub
//		return "Node";
//	}
	


}
