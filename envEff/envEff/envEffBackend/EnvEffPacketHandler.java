package envEff.envEffBackend;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import envEff.EnvironmentalEffect;
import envEff.Util.NetUtils;
import envEff.Util.PlugInCreation.EnvEntityServer;

public class EnvEffPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

		if (packet.channel.equals("envEffSending")) {

			handleEnvEffClient(packet);
		}
		if (packet.channel.equals("envEffRequest")) {

			handleEnvEffRequest(packet, player);
		}
		// TODO Auto-generated method stub

	}

	private void handleEnvEffRequest(Packet250CustomPayload packet,
			Player player) {
		DataInputStream input = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		int id = 0;
		try {
			id = input.readInt();
			for (Integer k : EnvironmentalEffect.registry.effectIDMap.keySet()) {
				if (k.intValue() == id) {
					System.out.println("Received request from "
							+ EnvironmentalEffect.registry.effectIDMap.get(
									new Integer(id)).getName() + " Client");
					
					ArrayList<EnvEntityServer> a = EnvironmentalEffect.registry.getActiveServerEntites(EnvironmentalEffect.registry.effectIDMap
							.get(k));
					if (a.size() > 0) {
						for (EnvEntityServer ent : a) {
							NetUtils.sendPacket(ent, player);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void handleEnvEffClient(Packet250CustomPayload packet) {
		System.out.println("Received EnvEnt packet, proceeding to process");
		DataInputStream input = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		// int id = 0;
		try {
			int id = input.readInt();
			System.out.println("Packet id = " + id);

			ServerInter server = new ServerInter(input.readInt(),
					input.readInt(), input.readInt(), input.readLong());
			for (Integer k : EnvironmentalEffect.registry.effectIDMap.keySet()) {
				if (k.intValue() == id) {
					System.out.println("Received packet from "
							+ EnvironmentalEffect.registry.effectIDMap.get(k).getName()
							+ " server");
					EnvironmentalEffect.registry.effectIDMap.get(k).addEntity(
							(EnvEntityServer) server);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class ServerInter extends EnvEntityServer {

	public ServerInter(float x, float y, float z, long time) {
		super(x, y, z, time);
	}

	@Override
	public void onTickInGame() {

	}

}
