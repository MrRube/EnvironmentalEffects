package envEff.Util.Graphics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import envEff.Util.PlugInCreation.EnvEntityClient;

public class GLUtil {

	public static void sexyGlowStart() {
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glShadeModel(7425 /* GL_SMOOTH */);
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glBlendFunc(770, 1);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(3008 /* GL_ALPHA_TEST */);
		GL11.glDisable(2884 /* GL_CULL_FACE */);
		GL11.glDepthMask(false);
	}

	public static void sexyGlowStop() {
		GL11.glDepthMask(true);
		GL11.glEnable(2884 /* GL_CULL_FACE */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glShadeModel(7424 /* GL_FLAT */);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(3008 /* GL_ALPHA_TEST */);
		GL11.glEnable(GL11.GL_FOG);
	}

	/**
	 * Used to render the entity at the proper location. Always put
	 * 
	 * @param partialTick
	 * @param ent
	 */
	public static void translateEntPosition(float partialTick,
			EnvEntityClient ent) {

		Minecraft minecraft = FMLClientHandler.instance().getClient();

		double transX = ent.posX
				- (minecraft.renderViewEntity.prevPosX + (minecraft.renderViewEntity.posX - minecraft.renderViewEntity.lastTickPosX)
						* partialTick);

		double transY = ent.posY
				- (float) (minecraft.renderViewEntity.lastTickPosY + (minecraft.renderViewEntity.posY - minecraft.renderViewEntity.lastTickPosY)
						* (double) partialTick);

		double transZ = ent.posZ
				- (minecraft.renderViewEntity.prevPosZ + (minecraft.renderViewEntity.posZ - minecraft.renderViewEntity.lastTickPosZ)
						* partialTick);

		GL11.glTranslatef((float) transX, (float) transY, (float) transZ);
	}

	public static void translateEntPosistionFixedAxis(float partialTick,
			EnvEntityClient ent, float xOffset, float yOffset, float zOffset) {
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		double transX;
		double transY;
		double transZ;
		{
			if (xOffset == 0) {
				transX = ent.posX
						- (minecraft.renderViewEntity.prevPosX + (minecraft.renderViewEntity.posX - minecraft.renderViewEntity.lastTickPosX)
								* partialTick);
			} else {
				transX = xOffset;
			}
		}
		{
			if (zOffset == 0) {
				transY = ent.posY
						- (float) (minecraft.renderViewEntity.lastTickPosY + (minecraft.renderViewEntity.posY - minecraft.renderViewEntity.lastTickPosY)
								* (double) partialTick);
			} else {
				transY = yOffset;
			}
		}
		{
			if (yOffset == 0) {
				transZ = ent.posZ
						- (minecraft.renderViewEntity.prevPosZ + (minecraft.renderViewEntity.posZ - minecraft.renderViewEntity.lastTickPosZ)
								* partialTick);
			} else {
				transZ = zOffset;
			}
		}
		GL11.glTranslatef((float) transX, (float) transY, (float) transZ);
	}
	
	

}
