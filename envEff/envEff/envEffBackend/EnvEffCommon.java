package envEff.envEffBackend;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import envEff.Util.PlugInCreation.EnvEntityClient;
import envEff.Util.PlugInCreation.EnvEntityServer;



public class EnvEffCommon {

//	public static ArrayList activeServerEffects = new ArrayList();
//	public static ArrayList activeClientEffects = new ArrayList();
	public EnvEffCommon(){
		//registerTickHandlers();
	}
	public void registerTickHandlers(){
		TickRegistry.registerTickHandler(new EnvEffCommonTickHandler(), Side.SERVER);
	}
	
	
	
//	public static void addClientEffect(EnvEntityClient eff){
//		activeClientEffects.add(eff);
//	}
//		
//	public static void addServerEffect(EnvEntityServer eff){
//		activeServerEffects.add(eff);
//	}
//	 
//	public static void clearLists(){
//		activeServerEffects.clear();
//		activeClientEffects.clear();
//	}
	
	
//	public void addAurora(long time, int posX, int posZ){		
//		AuroraCommon.activeServerAuroras.add(new ServerAurora( posX, posZ, time));
//	}
}
