package vbucks.items;

import vbucks.blocks.*;
import vbucks.*;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.*;

import java.util.List;

public class CreditCard extends VItem {
	public static final float DROP_CHANCE = 0.01f;
	public static final Text DECLINED = new TranslatableText("item.vbucks.credit_card.declined")
		.setStyle(Style.EMPTY.withColor(Formatting.RED));
	public static final Text ACCEPTED = new TranslatableText("item.vbucks.credit_card.accepted")
		.setStyle(Style.EMPTY.withColor(Formatting.GREEN));

	@Environment(EnvType.CLIENT)
	public final Text tooltip = new TranslatableText("item.vbucks.credit_card.tooltip");

	public CreditCard() {
		super("credit_card");
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void appendTooltip(ItemStack stack, World world,
			List<Text> tooltips, TooltipContext context) {
		tooltips.add(tooltip);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (world.isClient()) {
			user.sendMessage(DECLINED, false);
		}
		return TypedActionResult.fail(stack);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext ctx) {
		PlayerEntity user = ctx.getPlayer();
		// require a player to transmute bobucks
		if (user == null) {
			return ActionResult.FAIL;
		}

		World world = ctx.getWorld();
		BlockState state = world.getBlockState(ctx.getBlockPos());
		// neds a full bobuck pile (8 bobucks)
		if (state == null || !state.isOf(Content.BOBUCKS) || state.get(Bobucks.LAYERS) < 8) {
			if (world.isClient()) {
				user.sendMessage(DECLINED, false);
			}
			return ActionResult.FAIL;
		}

		world.setBlockState(ctx.getBlockPos(), Content.BOBUCK_BLOCK.getDefaultState());
		if (world.isClient()) {
			user.sendMessage(ACCEPTED, false);
		}
		// TODO: consume credit card? consume credit card durability?
		return ActionResult.CONSUME;
	}
}
