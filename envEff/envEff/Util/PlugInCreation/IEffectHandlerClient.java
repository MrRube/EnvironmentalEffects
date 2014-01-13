package envEff.Util.PlugInCreation;

import java.util.ArrayList;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldServer;

public interface IEffectHandlerClient {
	
	/**
	 * Used by the client to track active client entities
	 */
	public ArrayList<EnvEntityClient> getActiveClientEntities();// = new ArrayList<EnvEntityClient>();

	

	/**
	 * Hooks into EnvEffClientTickhandler. Use for any client tick logic
	 * @param world The active world which the player is in
	 */
	public  void clientTickHandler(WorldClient world);

	/**
	 * Hooks into EntityRender.renderRainSnow(float f) 
	 * @param f Incremental ticks
	 */
	public  void render(float f);

	/**
	 * Gives the class that the effect uses as it's client entity
	 * @return The effect's client entity class
	 */
	public  Class<? extends EnvEntityClient> getEntityClientClass();

	

	

	/**
	 * Gives the name of the Effect
	 * @return
	 */
	public String getName();

	/**
	 * An Integer id that is used to identify the mod. Please use care when picking a number as it will cause conflicts with other effects
	 * @return Your effect id wrapped in a Integer
	 */
	public Integer getID();

	/**
	 * Called on receiving a packet bearing your effect's ID. Use this method add to your active client effects
	 * @param server A EnvEntityServer object is created based on incoming packet data. This is used to generate a client entity
	 */
	public void addEntity(EnvEntityServer server);

	/**
	 * Used to clear the active effects when the player is in the main menu
	 */
	public void clearActiveClientEffects();
	
}
