package deltanedas.vbucks_mod.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

import static deltanedas.vbucks_mod.Constants.*;

@Config(modid = MOD_ID)
public class InitConfig {
	@Comment("V-Buck Pack integration.\nThis requires: Actually Additions, EnderIO, Mekanism, GalacticraftCore, RFTools, Wizardry and PSI.")
	@RequiresMcRestart
	public static boolean vbuckPackIntegration = false;
}

