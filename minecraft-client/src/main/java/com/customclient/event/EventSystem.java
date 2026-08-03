package com.customclient.event;

/**
 * Менеджер событий для регистрации и вызова событий
 */
public class EventSystem {
    
    // Событие рендера мира (ESP, BoxESP и т.д.)
    public static final Event<RenderWorldEvent> RENDER_WORLD = new Event<>();
    
    // Событие рендера GUI (Overlay, Watermark и т.д.)
    public static final Event<RenderGuiEvent> RENDER_GUI = new Event<>();
    
    // Событие получения урона
    public static final Event<DamageEvent> DAMAGE = new Event<>();
    
    // Событие движения
    public static final Event<MotionEvent> MOTION = new Event<>();
    
    public static void init() {
        System.out.println("[EventSystem] Система событий инициализирована");
    }
    
    /**
     * Событие рендера мира
     */
    public static class RenderWorldEvent {
        private final float tickDelta;
        
        public RenderWorldEvent(float tickDelta) {
            this.tickDelta = tickDelta;
        }
        
        public float getTickDelta() {
            return tickDelta;
        }
    }
    
    /**
     * Событие рендера GUI
     */
    public static class RenderGuiEvent {
        private final float tickDelta;
        
        public RenderGuiEvent(float tickDelta) {
            this.tickDelta = tickDelta;
        }
        
        public float getTickDelta() {
            return tickDelta;
        }
    }
    
    /**
     * Событие получения урона
     */
    public static class DamageEvent {
        private final float damage;
        
        public DamageEvent(float damage) {
            this.damage = damage;
        }
        
        public float getDamage() {
            return damage;
        }
    }
    
    /**
     * Событие движения
     */
    public static class MotionEvent {
        private double x, y, z;
        private boolean onGround;
        
        public MotionEvent(double x, double y, double z, boolean onGround) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.onGround = onGround;
        }
        
        public double getX() { return x; }
        public double getY() { return y; }
        public double getZ() { return z; }
        public boolean isOnGround() { return onGround; }
        
        public void setX(double x) { this.x = x; }
        public void setY(double y) { this.y = y; }
        public void setZ(double z) { this.z = z; }
        public void setOnGround(boolean onGround) { this.onGround = onGround; }
    }
}
