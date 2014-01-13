package envEff.envEffBackend;

import java.util.ArrayList;
import java.util.Hashtable;

import envEff.EnvironmentalEffect;
import envEff.Util.PlugInCreation.EnvEntityClient;
import envEff.Util.PlugInCreation.EnvEntityServer;
import envEff.Util.PlugInCreation.IEffectHandlers;

public class EffectRegistry {

	public static Hashtable<IEffectHandlers, ArrayList<EnvEntityClient>> activeClientRegistry = new Hashtable<IEffectHandlers, ArrayList<EnvEntityClient>>();
	public static Hashtable<IEffectHandlers, ArrayList<EnvEntityServer>> activeServerRegistry = new Hashtable<IEffectHandlers, ArrayList<EnvEntityServer>>();
	public static Hashtable<Integer, IEffectHandlers> effectIDMap = new Hashtable<Integer, IEffectHandlers>();

	public static ArrayList<IEffectHandlers> effectRegistry = new ArrayList<IEffectHandlers>();

	public EffectRegistry() {
	}

	public void registerEffect(IEffectHandlers effect) {
		effectRegistry.add(effect);
		effectIDMap.put(new Integer(effect.getID()), effect);
		activeClientRegistry.put(effect, new ArrayList<EnvEntityClient>());
		activeServerRegistry.put(effect, new ArrayList<EnvEntityServer>());
	}

	public ArrayList<EnvEntityServer> getActiveServerEntites(
			IEffectHandlers effect) {
		return activeServerRegistry.get(effect);

	}

	public ArrayList<EnvEntityClient> getActiveClientEntites(
			IEffectHandlers effect) {

		return activeClientRegistry.get(effect);

	}

	public void addToActiveClientEntites(IEffectHandlers effect,
			EnvEntityClient ent) {
		activeClientRegistry.get(effect).add(ent);

	}

	public void addToActiveServerEntites(IEffectHandlers effect,
			EnvEntityServer ent) {

		activeServerRegistry.get(effect).add(ent);

	}

	public void clearActiveClientEntites(IEffectHandlers effect) {
		activeClientRegistry.get(effect).clear();
	}

	public void clearActiveServerEntites(IEffectHandlers effect) {
		activeServerRegistry.get(effect).clear();
	}
}
