package envEff.envEffBackend;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.TickType;
import envEff.EnvironmentalEffect;
import envEff.Util.PlugInCreation.IEffectHandlers;

public class EnvEffClientTickHandler extends EnvEffCommonTickHandler {
	private boolean outOfGameCuller = false;
	@Override
	public EnumSet ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
		WorldClient world = FMLClientHandler.instance().getClient().theWorld;

		if (type.equals(EnumSet.of(TickType.RENDER))) {
			onRenderTick();
		} else if (type.equals(EnumSet.of(TickType.CLIENT))) {
			
			if (EnvironmentalEffect.registry.effectRegistry.size() > 0) {
				for (IEffectHandlers effect : EnvironmentalEffect.registry.effectRegistry) {
					effect.clientTickHandler(world);
				}
			}
			// Used to check if the user is not in game
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;		
			if(guiscreen == null && outOfGameCuller == false){
				outOfGameCuller = true;
			}
			if (guiscreen != null && guiscreen instanceof GuiMainMenu && outOfGameCuller) {
				onTickInGUI(guiscreen);
				outOfGameCuller = false;
			}
		}
	}

	public void onRenderTick() {

	}

	/**
	 * Used for culling active Effects when the player is not in game
	 * 
	 * @param g
	 */
	public void onTickInGUI(GuiScreen g) {
		if(EnvironmentalEffect.registry.effectRegistry.size() > 0){
			for(IEffectHandlers eff : EnvironmentalEffect.registry.effectRegistry){				
				EnvironmentalEffect.registry.clearActiveClientEntites(eff);
			}			
		}

	}
	
}
