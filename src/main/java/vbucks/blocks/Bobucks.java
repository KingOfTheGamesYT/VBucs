package vbucks.blocks;

import vbucks.*;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

import java.util.List;

public class Bobucks extends SnowBlock implements Content {
	@Environment(EnvType.CLIENT)
	public final Text tooltip = new TranslatableText("block.vbucks.bobucks.tooltip");
	public final Identifier ident = new Identifier(VBucks.MOD_ID, "bobucks");

	public Bobucks() {
		super(FabricBlockSettings.of(Material.WOOD)
			.breakByHand(true)
			.sounds(BlockSoundGroup.BAMBOO)
			.strength(0f, 1f)
			.nonOpaque());

		add();
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void appendTooltip(ItemStack stack, BlockView world,
			List<Text> tooltips, TooltipContext context) {
		tooltips.add(tooltip);
	}

	@Override
	public void register() {
		VBlock.register(this, ident);
	}

	// bobucks won't melt as ticksRandomly() isn't in the material
}
