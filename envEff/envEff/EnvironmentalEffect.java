package envEff;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import envEff.Util.PlugInCreation.IEffectHandlers;
import envEff.envEffBackend.EffectRegistry;
import envEff.envEffBackend.EnvEffCommon;
import envEff.envEffBackend.EnvEffPacketHandler;

@Mod(modid = "EnvironmentalEffect", name = "Mr. Rube's Environmental Effect API", version = "v1.0.0 for Mc 1.6.4")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, channels = {"envEffSending", "envEffRequest"}, versionBounds = "1.6.4", packetHandler = EnvEffPacketHandler.class)

public final class EnvironmentalEffect {
	
@SidedProxy(clientSide = "envEff.envEffBackend.EnvEffClient", serverSide = "envEff.envEffBackend.EnvEffCommon")
 public static EnvEffCommon proxy;
 public static EffectRegistry registry;
 
 
public EnvironmentalEffect(){
	registry = new EffectRegistry();
}

@EventHandler
public void preInit(FMLPreInitializationEvent event){
	
}

@EventHandler
public void load(FMLInitializationEvent event){
	this.proxy.registerTickHandlers();
}

@EventHandler
public void modsLoaded(FMLPostInitializationEvent evt){
	
}

public static void render(float f){
	
	if(registry.effectRegistry.size() > 0){
			for (IEffectHandlers effect : registry.effectRegistry) {
				effect.render(f);
			}
	}
	
}

public static void registerEffect(IEffectHandlers effect){
	registry.registerEffect(effect);
	
}

}
