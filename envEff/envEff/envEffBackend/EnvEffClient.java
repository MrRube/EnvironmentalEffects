package envEff.envEffBackend;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


public class EnvEffClient extends EnvEffCommon{

	public EnvEffClient(){
		super();
	}
	
	@Override
	public void registerTickHandlers(){
		TickRegistry.registerTickHandler(new EnvEffClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new EnvEffCommonTickHandler(), Side.SERVER);
	}
}
