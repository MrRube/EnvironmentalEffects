package envEff.Util.PlugInCreation;

import java.util.ArrayList;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldServer;

public interface IEffectHandlerServer {
	/**
	 * Used by the server to track active entities
	 */
	public ArrayList<EnvEntityServer> getActiveServerEntities(); // new ArrayList<EnvEntityServer>();
	

	/**
	 * Hooks into EnvEffCommonTickhandler. Use for any server tick logic
	 * @param world Active world which the Effect can exist
	 */
	public  void commonTickHandler(WorldServer world);

	

	


	/**
	 * Gives the class that the effect uses as it's server entity
	 * @return The effect's server entity class
	 */
	public  Class<? extends EnvEntityServer> getServerEntityClass();

	/**
	 * Use this to specify the dimensions you want the effect to spawn in
	 * @return an int[] containing dimension ids
	 */
	public int[] dimensions();

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

	
	}

