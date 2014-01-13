package envEff.Util.PlugInCreation;

import java.util.ArrayList;

import envEff.Util.Graphics.Node;
import net.minecraft.world.World;

/**
 * Class used to define the Environmental entity on the client. This includes
 * tick based logic and any Node[] the entity contains.
 * 
 * @author MrRube
 * 
 */

public abstract class EnvEntityClient extends EnvEntityServer{

	/**
	 * Contains any Node[]s that the entity uses
	 */
	protected ArrayList<Node[]> nodeList = new ArrayList<Node[]>();

	/**
	 * Used to determine the age in in game ticks
	 */
	protected long ticksExisted = 0;

	/**
	 * Create a Clientside Environmental Entity.
	 * 
	 * @param x
	 *            X spawn location
	 * @param y
	 *            Y spawn location
	 * @param z
	 *            Z spawn location
	 * @param time
	 *            local time
	 */

	public EnvEntityClient(float x, float y, float z, long time) {
		super(x,y,z,time);
		generate();
	}

	/**
	 * Create a Clientside Environmental Entity using a Serverside Environmental
	 * Entity
	 * 
	 * @param s
	 *            Received Server entity
	 */
	public EnvEntityClient(EnvEntityServer s) {
		this(s.posX, s.posY, s.posZ, s.birthTime);
	}

	/**
	 * Called when the Entity is spawned. Used to populate any Node[]s or
	 * general spawn stuff
	 */
	protected abstract void generate();

	/**
	 * Called every in game Tick. Put any recurring logic you want in this
	 * method
	 */
	@Override
	public void onTickInGame() {
		ticksExisted += 1;
	}

	public long getTicksExisted(){
		return ticksExisted;
	}
	/**
	 * 
	 * @return Returns Entity's nodeList
	 */
	public ArrayList<Node[]> getNodeList() {
		return this.nodeList;
	}

	/**
	 * 
	 * @param a
	 *            Adds a Node[] to nodeList
	 */
	protected void addNodeList(Node[] a) {
		this.nodeList.add(a);
	}
}
