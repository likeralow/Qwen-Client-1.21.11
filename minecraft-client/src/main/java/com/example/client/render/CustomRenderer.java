package com.example.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

/**
 * Кастомный рендерер для клиента
 */
public class CustomRenderer {

    /**
     * Рендеринг линии между двумя точками в мире
     */
    public static void renderLine(Vec3 start, Vec3 end, float red, float green, float blue, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);

        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition();

        double x1 = start.x - cameraPos.x();
        double y1 = start.y - cameraPos.y();
        double z1 = start.z - cameraPos.z();

        double x2 = end.x - cameraPos.x();
        double y2 = end.y - cameraPos.y();
        double z2 = end.z - cameraPos.z();

        buffer.addVertex((float) x1, (float) y1, (float) z1).setColor(red, green, blue, alpha);
        buffer.addVertex((float) x2, (float) y2, (float) z2).setColor(red, green, blue, alpha);

        MeshData meshData = buffer.buildOrThrow();
        BufferUploader.drawWithShader(meshData);

        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    /**
     * Рендеринг коробки (ESP box)
     */
    public static void renderBox(Vec3 center, double width, double height, double depth, 
                                  float red, float green, float blue, float alpha) {
        double halfWidth = width / 2;
        double halfDepth = depth / 2;

        Vec3 min = new Vec3(center.x - halfWidth, center.y, center.z - halfDepth);
        Vec3 max = new Vec3(center.x + halfWidth, center.y + height, center.z + halfDepth);

        // Нижняя грань
        renderLine(new Vec3(min.x, min.y, min.z), new Vec3(max.x, min.y, min.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, min.y, min.z), new Vec3(max.x, min.y, max.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, min.y, max.z), new Vec3(min.x, min.y, max.z), red, green, blue, alpha);
        renderLine(new Vec3(min.x, min.y, max.z), new Vec3(min.x, min.y, min.z), red, green, blue, alpha);

        // Верхняя грань
        renderLine(new Vec3(min.x, max.y, min.z), new Vec3(max.x, max.y, min.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, max.y, min.z), new Vec3(max.x, max.y, max.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, max.y, max.z), new Vec3(min.x, max.y, max.z), red, green, blue, alpha);
        renderLine(new Vec3(min.x, max.y, max.z), new Vec3(min.x, max.y, min.z), red, green, blue, alpha);

        // Вертикальные линии
        renderLine(new Vec3(min.x, min.y, min.z), new Vec3(min.x, max.y, min.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, min.y, min.z), new Vec3(max.x, max.y, min.z), red, green, blue, alpha);
        renderLine(new Vec3(max.x, min.y, max.z), new Vec3(max.x, max.y, max.z), red, green, blue, alpha);
        renderLine(new Vec3(min.x, min.y, max.z), new Vec3(min.x, max.y, max.z), red, green, blue, alpha);
    }

    /**
     * Рендеринг текста на экране (упрощённо)
     */
    public static void renderText(String text, int x, int y, int color) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.font != null) {
            mc.font.drawShadow(mc.guiGraphics.pose(), text, x, y, color);
        }
    }
}
