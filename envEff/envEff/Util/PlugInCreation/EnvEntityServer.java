package envEff.Util.PlugInCreation;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public abstract class EnvEntityServer {
	/**
	 * X spawn location
	 */
	public float posX;
	/**
	 * Y spawn location
	 */
	public float posY;
	/**
	 * Z spawn location
	 */
	public float posZ;
	
	/**
	 * The spawn time based on real world time. Is used by the client as a seed in random
	 * generation
	 */
	public long birthTime;
	
	/**
	 * Used for self culling
	 */
	boolean markForRemoval = false;
	
	/**
	 * The distance allowed between two Entities
	 */
	protected int cullingDistance;
	
	/**
	 * Creates a ServerSide entity
	 * @param x X spawn location
	 * @param y Y spawn location
	 * @param z Z spawn location
	 * @param t The server's local time, or a specific seed. Used in random generation.
	 */
	public EnvEntityServer(float x, float y, float z, long time){
		posX = x;
		posY = y;
		posZ = z;
		birthTime = time;
	}
	
	/**
	 * Creates a ServerSide entity with self culling.
	 * @param x X spawn location
	 * @param y Y spawn location
	 * @param z Z spawn location
	 * @param time The server's local time, or a specific seed. Used by the client in random generation
	 * @param list A list of existing Server Entities.
	 */
	public EnvEntityServer(float x, float y, float z, long time, ArrayList list){
		this(x, y, z, time);
		selfCull(list);
	}
	
	/**
	 * Marks Entity for removal if it is too close to another entity of the same type
	 * @param list A list of existing Server Entities.
	 * @return True if this entity should be removed, false otherwise
	 */
	private boolean selfCull(ArrayList list){
		if (list.size() > 1) {
			for (Object o : list) {
				if (getDistance((EnvEntityServer) o) < cullingDistance) {
					this.markForRemoval = true;
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Called every in game Tick. Put any recurring logic you want in this
	 * method
	 */
	public abstract void onTickInGame();
	
	/**
	 * Gives the distance between this entity and another
	 * @param ent Entity that this is being tested against
	 * @return The distance
	 */
	protected int getDistance(EnvEntityServer ent){
		int deltaXs = (int) Math.pow((posX - ent.posX), 2);
		int deltaZs = (int) Math.pow((posZ - ent.posZ), 2);
		return (int) Math.pow((deltaXs + deltaZs), .5F);
	}
	
	/**
	 * Used to see if the entity has the same properties as another entity
	 * @param ent Entity that this is being tested against
	 * @return True if this is the other entity, false otherwise
	 */
	public boolean equals(EnvEntityServer ent){
		return this.birthTime == ent.birthTime && this.posX == ent.posX
				&& this.posZ == ent.posZ && this.posY == ent.posY;
	}

	
	public int getID() {		
		return 0;
	}
	
	public boolean shouldRemove(){
		return this.markForRemoval;
	}

}
