package vbucks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static vbucks.Content.*;

@Environment(EnvType.CLIENT)
public class VBucksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(VBUCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(GLOWING_VBUCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BOBUCKS, RenderLayer.getCutout());
	}
}
