package dev.muon.betterendshipsfix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.betterx.betterend.world.features.CrashedShipFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrashedShipFeature.class)
public abstract class CrashedShipFeatureMixin {

	@Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
			at = @At(value = "RETURN"),
			locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private void injectLootTables(FeaturePlaceContext<?> featureConfig, CallbackInfoReturnable<Boolean> cir,
								  RandomSource random, BlockPos center, WorldGenLevel world, BoundingBox bounds) {
		if (cir.getReturnValue()) {
			for (BlockPos pos : BlockPos.betweenClosed(bounds.minX(), bounds.minY(), bounds.minZ(), bounds.maxX(), bounds.maxY(), bounds.maxZ())) {
				if (world.getBlockState(pos).getBlock() instanceof ChestBlock) {
					BlockEntity te = world.getBlockEntity(pos);
					if (te instanceof ChestBlockEntity) {
						((ChestBlockEntity) te).setLootTable(END_CITY_TREASURE, random.nextLong());
					}
				}
			}
		}
	}
	private static final ResourceLocation END_CITY_TREASURE = new ResourceLocation("minecraft", "chests/end_city_treasure");
}