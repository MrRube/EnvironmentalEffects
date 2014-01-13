package envEff.asm;

//import Debug;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;


//import net.rubeAurora.*;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.launchwrapper.IClassTransformer;

public class EntityRendererTransformer implements IClassTransformer {

	// private final String CLASS_NAME =
	// "net.minecraft.client.renderer.EntityRenderer";
	// private final String OBF_CLASS_NAME = "bfe";
	// private final String RENRASN_NAME = "renderRainSnow";
	// private final String RENRASN_NAME_M = "func_78474_d";
	// private final String RENRASN_NOTCH = "d";
	// private final String RENRASN_DESC = "(F)V";
	// private final String OBF_RENRASN_DESC = "(F)V";

	private HashMap<String, String> obfStrings = new HashMap<String, String>();
	private HashMap<String, String> mcpStrings = new HashMap<String, String>();
	private HashMap<String, String> strings = new HashMap<String, String>();

	public EntityRendererTransformer() {
		System.out.println("Welcome to EntityRenderTransformer");
		putMappings("className", "bfe",
				"net.minecraft.client.renderer.EntityRenderer");
		putMappings("renderRainSnowName", "d", "renderRainSnow");
		putMappings("renderRainSnowDesc", "(F)V", "(F)V");
	}

	@Override
	public byte[] transform(String name, String trans, byte[] bytes) {
		if (FMLLaunchHandler.side() == Side.CLIENT) {
			String mcp = mcpStrings.get("className");

			if (name.equals(mcp)) {
				strings = mcpStrings;
				return transformEntityRenderer(bytes, mcpStrings);
			}
			String obf = obfStrings.get("className");
			if (name.equals(obf)) {
				strings = obfStrings;
				return transformEntityRenderer(bytes, obfStrings);
			}
		}
		return bytes;
	}

	private byte[] transformEntityRenderer(byte[] bytes,
			HashMap<String, String> map) {
		FMLLog.log(Level.FINEST, "Found class, starting aurora transformation");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		Iterator<MethodNode> methods = classNode.methods.iterator();
		while (methods.hasNext()) {
			MethodNode m = methods.next();
			if (m.name.equals(map.get("renderRainSnowName"))
					&& m.desc.equals(map.get("renderRainSnowDesc"))) {
				FMLLog.log(Level.FINEST, "renderRainSnow has been found WOOOOT");

				LabelNode lmm1Node = new LabelNode(new Label());

				// make new instruction list
				InsnList toInject = new InsnList();

				toInject.add(new VarInsnNode(Opcodes.FLOAD, 1));
				toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
						"envEff/EnvironmentalEffect", "render", "(F)V"));
				//toInject.add(new InsnNode(Opcodes.RETURN));
				toInject.add(lmm1Node);

				m.instructions.insert(m.instructions.get(0), toInject);
				System.out.println("Transformation Complete");
				break;
			}
		}
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(cw);

		return cw.toByteArray();
	}

	private void putMappings(String givenName, String obfName, String mcpName) {
		obfStrings.put(givenName, obfName);
		mcpStrings.put(givenName, mcpName);
	}
	// @Override
	// public String name() {
	//
	// return "Entity Renderer Transformer";
	// }
	// @Override
	// public byte[] transform(String name, String transformedName, byte[]
	// bytes) {
	// if(FMLLaunchHandler.side() == Side.CLIENT && name.equals(this.CLASS_NAME)
	// || name.equals(OBF_CLASS_NAME)) {
	// return this.transformRenderPlayer(name, bytes);
	// }
	// return bytes;
	// }
}
