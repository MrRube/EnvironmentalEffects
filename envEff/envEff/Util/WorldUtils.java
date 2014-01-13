package envEff.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import envEff.Util.PlugInCreation.EnvEntityClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WorldUtils {

	public static long getWrappedTime(World world) {
		long time;
		for (time = world.getWorldTime(); time > 24000; time -= 24000)
			;
		return time;
	}

	public static boolean isTimeEqual(World world, int t) {
		long time;
		for (time = world.getWorldTime(); time > 24000; time -= 24000)
			;
		return time == t;
	}

	public static boolean isTimeBetween(World world, int upper, int lower) {
		long time;
		for (time = world.getWorldTime(); time > 24000; time -= 24000)
			;
		return (time > lower && time < upper);
	}

	public static boolean isTimeOutside(World world, int upper, int lower) {
		long time;
		for (time = world.getWorldTime(); time > 24000; time -= 24000)
			;
		return (time < lower || time > upper);
	}
//	public static boolean isTimeOutside(WorldServer world, int upper, int lower){
//		long time;
//		for (time = world.getWorldTime(); time > 24000; time -= 24000)
//			;
//		return (time < lower || time > upper);
//	}

	public static EnvEntityClient getClosestEntity(
			ArrayList<EnvEntityClient> arraylist) {
		EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
		HashMap map = new HashMap();
		EnvEntityClient a = arraylist.get(0);
		System.out.println(a);

		if (arraylist.size() > 1) {
			a = arraylist.get(0);
			for (EnvEntityClient ent : arraylist) {
				float dX = (float) Math.pow(ent.posX - player.posX, 2);
				float dY = (float) Math.pow(ent.posY - player.posZ, 2);

				float dZ = (float) Math.pow(ent.posZ - player.posZ, 2);

				float distXYZ = (float) Math.pow(dX + dZ + dY, .5);
				map.put(distXYZ, ent);
			}
			List sortedKeys = new ArrayList(map.keySet());
			Collections.sort(sortedKeys);
			a = (EnvEntityClient) map.get(sortedKeys.get(0));
		}
		return a;
	}

	

	public static float getSunAngle(World world) {
		long time = getWrappedTime(world);
		float f =  ((float)time / 24000F * 360F);
		//System.out.println((float) (time / 24000));
		return f;
	}
	
	@SideOnly(Side.CLIENT)
	public static float[] getPlayerViewAngle() {
		Minecraft mc = FMLClientHandler.instance().getClient();
		float pitch = mc.renderViewEntity.rotationPitch;
		float yaw = MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw);

		return new float[] { yaw, pitch };
	}
	@SideOnly(Side.CLIENT)
	public static boolean isPlayerNotLookingAtSun() {
		Minecraft mc = FMLClientHandler.instance().getClient();
		float yaw = MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw);

		if (yaw < 1) {
			return getWrappedTime(mc.theWorld) < 12000
					&& getWrappedTime(mc.theWorld) >= 6000;
		}
		if (yaw >= 1) {
			return getWrappedTime(mc.theWorld) < 6000
					&& getWrappedTime(mc.theWorld) >= 0;
		}
		return true;
	}
}
