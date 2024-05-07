package deltanedas.vbucks_mod;

import java.util.Random;

import com.google.common.base.Predicate;

import deltanedas.vbucks_mod.init.InitBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGen implements IWorldGenerator {
	public void generate(Random random,
		int chunkX,
		int chunkY,
		World worldIn,
		IChunkGenerator chunkGenerator,
		IChunkProvider chunkProvider) {
		if (worldIn.provider.getDimension() == 0) {
			runGenerator(InitBlocks.vinderiumOre.getDefaultState(), 11, 1, 13, 13, BlockMatcher.forBlock(Blocks.STONE), worldIn, random, chunkX, chunkY);
		}
	}

	void runGenerator(IBlockState block,
		int amount,
		int spawnTimes,
		int minHeight,
		int maxHeight,
		Predicate<IBlockState> replacee, // Block to replace
		World worldIn,
		Random random,
		int chunkX,
		int chunkY) {
		WorldGenMinable generator = new WorldGenMinable(block, amount, replacee);
		int heightdiff = maxHeight - minHeight + 1;
		for (int i = 0; i < spawnTimes; i++){
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightdiff);
			int z = chunkY * 16 + random.nextInt(16);
			generator.generate(worldIn, random, new BlockPos(x, y, z));
		}
	}
}
