package envEff.envEffBackend;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import envEff.Util.*;
import envEff.Util.PlugInCreation.EnvEntityServer;
import envEff.Util.PlugInCreation.IEffectHandlers;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import envEff.EnvironmentalEffect;

public class EnvEffCommonTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		if (type.equals(EnumSet.of(TickType.SERVER))) {
			if (EnvironmentalEffect.registry.effectRegistry.size() > 0) {
				//System.out.println("Effect in Registry");
				for (IEffectHandlers effect : EnvironmentalEffect.registry.effectRegistry) {
					//System.out.println(effect.getName());
					for (int i : effect.dimensions()) {
						WorldServer world;
						try {
							world = FMLCommonHandler.instance()
									.getMinecraftServerInstance()
									.worldServerForDimension(i);
							
							effect.commonTickHandler(world);
							
						} catch (Exception e) {
							System.out.println(effect.getName()
									+ "Can't find Active dimension " + i);
						}
					}
				}
			}
		}

	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
