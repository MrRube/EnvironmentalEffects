package envEff.asm;


	import java.util.Map;



	import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
	//import envEff.IDebug;
	/*
	* Don't let any access transformer stuff accidentally modify our classes A list
	* of package prefixes for FML to ignore
	*/
	@TransformerExclusions({ "envEff.asm"})
	public class EnvEffCorePlugIn implements IFMLLoadingPlugin
	{
		public EnvEffCorePlugIn(){
			System.out.println("Starting to load Environmental Effect's CoreMod!");
		}
	    @Override
	    public String[] getLibraryRequestClass()
	    {
	        return null;
	    }
	    
	    @Override
	    public String[] getASMTransformerClass()
	    {
	    	//Debug.println(this, "Hue");
	        return new String[] { "envEff.asm.EntityRendererTransformer"};//"envEff.asm.FenceTransformer" };
	    }
	    
	    @Override
	    public String getModContainerClass()
	    {
	        return null;
	    }
	    
	    @Override
	    public String getSetupClass()
	    {
	        return null;
	    }
	    
	    @Override
	    public void injectData(Map<String, Object> data)
	    {
	        
	    }

//		@Override
//		public String name() {
//			
//			return "Aurora Core Plugin";
//		}
	    
	}

