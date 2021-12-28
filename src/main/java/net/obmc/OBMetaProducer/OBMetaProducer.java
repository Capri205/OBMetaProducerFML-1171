package net.obmc.OBMetaProducer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(OBMetaProducer.MOD_ID)

public class OBMetaProducer
{
	public static OBMetaProducer instance = new OBMetaProducer();
	public static final String MOD_ID = "obmetaproducer";

    private static final Logger LOGGER = LogManager.getLogger();

	public static final String OBMFILE = "MetaFile";
	public static final String OBMOPTS = "Options";

    private String metafile;
    private Boolean obsfucate;
    private Integer range;
    
    public OBMetaProducer() {

        // Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
	}
	
    public void setup (final FMLCommonSetupEvent event) {

   		metafile = "/overviewer/ob-twilight/markers.json";
   		obsfucate = false;
   		range = 1000;

   		LOGGER.info("[OBMetaProducer] Metadata output in "+metafile);
   		LOGGER.info("[OBMetaProducer] Player positon masking is set to "+obsfucate);
   		if (obsfucate) {
   			LOGGER.info("[OBMetaProducer] Masking range is "+range+" blocks");
   		}

   		MinecraftForge.EVENT_BUS.register(new OBTicker(metafile, obsfucate, range));
    }
}