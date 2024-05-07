package vbucks.blocks;

import vbucks.*;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.*;
import net.minecraft.world.BlockView;

public class VBuck extends FacingBlock implements Content, Waterloggable {
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private static final VoxelShape[] BOUNDING_BOX = new VoxelShape[6];

	static {
		BOUNDING_BOX[0] = VoxelShapes.cuboid(0f, 0.75f, 0f, 1.0f, 1.0f, 1.0f); // Down
		BOUNDING_BOX[1] = VoxelShapes.cuboid(0f, 0f, 0f, 1.0f, 0.25f, 1.0f); // Up
		BOUNDING_BOX[2] = VoxelShapes.cuboid(1.0f, 0f, 0.75f, 0f, 1.0f, 1.0f); // North
		BOUNDING_BOX[3] = VoxelShapes.cuboid(0f, 0f, 0f, 1.0f, 1.0f, 0.25f); // South
		BOUNDING_BOX[4] = VoxelShapes.cuboid(0.75f, 0f, 0f, 1.0f, 1.0f, 1.0f); // West
		BOUNDING_BOX[5] = VoxelShapes.cuboid(0f, 0f, 0f, 0.25f, 1.0f, 1.0f); // East
	}

	public final Identifier ident;

	public VBuck(String name, int light) {
		super(FabricBlockSettings.of(Material.METAL)
			.luminance(state -> light)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.sounds(BlockSoundGroup.METAL)
			.strength(4f, 15f)
			.nonOpaque());

		ident = new Identifier(VBucks.MOD_ID, name);

		setDefaultState(getStateManager().getDefaultState()
			.with(FACING, Direction.UP)
			.with(WATERLOGGED, false));

		add();
	}

	@Override
	public void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(FACING, WATERLOGGED);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state,
			BlockView view, BlockPos pos, ShapeContext ctx) {
		return BOUNDING_BOX[state.get(FACING).ordinal()];
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return (BlockState) super.getPlacementState(ctx).with(FACING, ctx.getPlayerLookDirection().getOpposite());
	}

	@Override
	public void register() {
		VBlock.register(this, ident);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		if ((Boolean) state.get(WATERLOGGED)) {
			return Fluids.WATER.getStill(false);
		}
		return super.getFluidState(state);
	}
}
