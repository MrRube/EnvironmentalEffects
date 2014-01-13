package envEff.Util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import envEff.Util.PlugInCreation.EnvEntityServer;
import envEff.Util.PlugInCreation.IEffectHandlers;

public class NetUtils {

	
	public static void sendPacket(EnvEntityServer ent, Player player){
		System.out.println("Sending packet to player");
		ByteArrayOutputStream bos = new ByteArrayOutputStream(24);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try{
			System.out.println(ent.getID());
			outputStream.writeInt( ent.getID());
			outputStream.writeInt((int)ent.posX);
			outputStream.writeInt((int)ent.posY);
			outputStream.writeInt((int)ent.posZ);
			outputStream.writeLong(ent.birthTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "envEffSending";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		PacketDispatcher.sendPacketToPlayer(packet, player);
		
	}
	
	public static void sendRequest(IEffectHandlers effect){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(4);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try{
			outputStream.writeInt( effect.getID());			
		}catch(Exception e){
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "envEffRequest";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		PacketDispatcher.sendPacketToServer(packet);
	}
}
