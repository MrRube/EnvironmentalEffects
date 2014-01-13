package envEff.Util.Graphics;

import java.util.ArrayList;

public class NodeUtils {
	
	/**
	 * Creates a segmented line
	 * @param startX X position of the previous node
	 * @param startZ Z position of the previous node
	 * @param baseAngle Angle the segment will start from
	 * @param angle Angle of each segment
	 * @param nodeLength Distance between each segment
	 * @param seg Number of segments in the line
	 * @return A Node[] containing the resultant line
	 */
	public static Node[] populateSegment(float startX, float startZ, float baseAngle, float angle, float nodeLength, int seg){
		Node[] n = new Node[seg];
		for(int i = 0; i < seg; i ++){
			if (i == 0) {
				n[i] = new Node(startX + (float) Math.cos(baseAngle)
						* nodeLength, startZ + (float) Math.sin(baseAngle)
						* nodeLength, baseAngle);
			} else {
				float subAngle = n[i - 1].getAngle() + angle;
				float posX = n[i - 1].posX + (float) Math.cos(subAngle)
						* nodeLength;
				float posZ = n[i - 1].posZ + (float) Math.sin(subAngle)
						* nodeLength;
				n[i] = new Node(posX, posZ, subAngle);
			}
			
		}
		return n;
		
	}
	
	/***
	 * Merges a collection of Node[]s into one continuous array
	 * @param nArrayList The Array list of Node[]s to be merged
	 * @return The combined Node[]
	 */
	public static Node[] merge(ArrayList<Node[]> nArrayList) {
		int size = 0;
		for (Node[] n : nArrayList) {
			size += n.length;
		}
		Node[] nArray = new Node[size];
		int index = 0;
		for (Node[] n : nArrayList) {
			for (int i = 0; i < n.length; i++) {
				nArray[index] = n[i];
				index++;
			}
		}
		return nArray;
	}
	/**
	 * Sets a continuous width along a Node[]. Does not set offsets!
	 * @param Node[] to be processed
	 * @param Width to be set
	 * @return Altered Node[]
	 */
	public static Node[] setWidths(Node[] n, float width){
		Node[] nArray = n;
		for(Node node: n){
			node.width = width;
		}
		return nArray;
		
	}
	/**
	 * Tapers the end widths
	 * @param n The Node[] to be tapered
	 * @param dist the number of segments from each end that you want to be tapered
	 * @param minWidth The minimum width of the ends of the Node[]
	 * 	 * @return A modified Node[]
	 */
	public static Node[] taperEnds(Node[] n, int dist, float minWidth){
		Node[] nArray = n;
		int seg = dist;
		float slope = (n[0].width -1)/dist;
		if(seg*2 >= n.length){
			if(n.length % seg == 0){
				seg = n.length/2 - 1;
			}
			else{
			seg = n.length/2;
			}
		}
		for(int i = 0; i < seg; i ++){
			float width = i * slope + minWidth ;
			n[i].width = width;
		}
		for(int i = 0; i < seg; i ++){
			float width = i * slope + minWidth ;
			n[n.length-1 - i].width = width;
		}
		
		return n;
	}
	/**
	 * Calculates the offset points for the Node[]. Use after widths are set
	 * @param node Node[] to be transformed
	 * @return Transformed Node[]
	 */
	public static Node[] formOffsets(Node[] node){
		Node[] nArray = node;
		for (int i = 0; i < node.length; i++) {
			float angle = 0;
			float x = 0;
			float x2 = 0;
			float z = 0;
			float z2 = 0;
			if (i < node.length - 1 && i != 0) {
				angle = (float) Math.atan2(nArray[i].getModdedZ()
						- nArray[i + 1].getModdedZ(), nArray[i].posX
						- nArray[i + 1].posX);

				float deg90 = angle + (float) Math.PI / 2;
				float deg270 = deg90 + (float) Math.PI;
				x = node[i].posX + (float) Math.cos(deg90) * node[i].width;
				x2 = node[i].posX + (float) Math.cos(deg270) * node[i].width;
				z = node[i].getModdedZ() + (float) Math.sin(deg90)
						* node[i].width;
				z2 = node[i].getModdedZ() + (float) Math.sin(deg270)
						* node[i].width;
			} else {
				x = node[i].posX;
				x2 = x;
				z = node[i].getModdedZ();
				z2 = z;
			}
			nArray[i].setAngle(angle);
			nArray[i].setTet(x, x2, z, z2);

		}

		return nArray;
	}

}
