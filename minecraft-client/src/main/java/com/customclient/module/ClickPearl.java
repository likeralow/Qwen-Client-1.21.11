package com.customclient.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

import com.customclient.setting.BooleanSetting;
import com.customclient.setting.FloatSetting;

/**
 * ClickPearl - бросок эндер-жемчуга по клику ПКМ
 */
public class ClickPearl extends Module {
    private final BooleanSetting onlyInAir = new BooleanSetting("OnlyInAir", "Только в воздухе", false);
    private final FloatSetting minDistance = new FloatSetting("MinDistance", "Мин. дистанция", 5f, 1f, 50f);
    
    public ClickPearl() {
        super("ClickPearl", "Бросок эндер-жемчуга по клику", Category.COMBAT);
        addSetting(onlyInAir);
        addSetting(minDistance);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        // Проверка наличия эндер-жемчуга
        if (!mc.player.getMainHandStack().isOf(Items.ENDER_PEARL) &&
            !mc.player.getOffHandStack().isOf(Items.ENDER_PEARL)) {
            return;
        }
        
        // Проверка только в воздухе
        if (onlyInAir.getValue() && mc.player.isOnGround()) {
            return;
        }
        
        // Проверка нажатия ПКМ
        if (mc.options.useKey.isPressed()) {
            HitResult hitResult = mc.player.raycast(100, 1.0f, false);
            
            if (hitResult.getType() == HitResult.Type.BLOCK || 
                hitResult.getType() == HitResult.Type.MISS) {
                
                Vec3d lookVec = mc.player.getRotationVecClient();
                double distance = minDistance.getValueFloat();
                
                // Вычисляем позицию для телепортации
                Vec3d targetPos = mc.player.getPos().add(lookVec.multiply(distance));
                
                // TODO: Реализовать отправку пакета использования предмета
                // Это позволит бросить жемчуг в нужном направлении
                // mc.interactionManager.interactItem(mc.player, Hand.MAIN_HAND);
            }
        }
    }
}
