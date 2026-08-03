package com.customclient.module;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import com.customclient.setting.BooleanSetting;
import com.customclient.setting.ColorSetting;
import com.customclient.setting.FloatSetting;

/**
 * BlockOverlay - подсветка блока на который смотришь
 */
public class BlockOverlay extends Module {
    private final BooleanSetting outline = new BooleanSetting("Outline", "Рисовать контур", true);
    private final BooleanSetting fill = new BooleanSetting("Fill", "Заполнение", false);
    private final ColorSetting color = new ColorSetting("Color", "Цвет оверлея", 0x40FF0000);
    private final FloatSetting alpha = new FloatSetting("Alpha", "Прозрачность", 0.5f, 0f, 1f);
    
    public BlockOverlay() {
        super("BlockOverlay", "Подсветка блока на который смотришь", Category.RENDER);
        addSetting(outline);
        addSetting(fill);
        addSetting(color);
        addSetting(alpha);
    }
    
    @Override
    public void onRenderWorld(float tickDelta) {
        if (mc.player == null || mc.world == null) return;
        
        HitResult hitResult = mc.player.raycast(5.0, tickDelta, false);
        if (!(hitResult instanceof BlockHitResult blockHit)) return;
        
        BlockPos pos = blockHit.getBlockPos();
        Block block = mc.world.getBlockState(pos).getBlock();
        
        // Не подсвечивать воздух и коренную породу
        if (block == Blocks.AIR || block == Blocks.BEDROCK) return;
        
        // TODO: Реализовать рендеринг бокса через BufferBuilder
        // Это требует доступа к VertexConsumer и матрицам
        // Примерная логика:
        // if (outline.getValue()) renderOutline(pos, color.getColor(), alpha.getValueFloat());
        // if (fill.getValue()) renderFill(pos, color.getColor(), alpha.getValueFloat());
    }
}
