# Custom Minecraft Client

Кастомный Minecraft клиент с поддержкой:
- **Mixin системы** - для внедрения кода в Minecraft
- **Системы событий (Events)** - для обработки различных игровых событий
- **Кастомного рендерера** - ESP, линии, коробки и другие визуальные эффекты
- **Кастомного GUI** - собственные экраны интерфейса

## Структура проекта

```
minecraft-client/
├── src/main/java/com/example/client/
│   ├── CustomClient.java          # Основной класс клиента
│   ├── event/
│   │   ├── Event.java             # Базовый класс событий
│   │   ├── EventManager.java      # Менеджер событий
│   │   ├── RenderWorldEvent.java  # Событие рендеринга мира
│   │   └── RenderGuiEvent.java    # Событие рендеринга GUI
│   ├── render/
│   │   └── CustomRenderer.java    # Кастомный рендерер
│   ├── mixin/
│   │   └── MinecraftMixin.java    # Mixin для Minecraft класса
│   └── gui/
│       └── CustomGuiScreen.java   # Кастомный GUI экран
└── src/main/resources/
    ├── mixins.client.json         # Конфигурация Mixin
    └── client.accesswidener       # Access Widener для доступа к protected/private полям
```

## Требования

- Java 17 или выше
- Gradle 8.x

## Сборка

```bash
cd minecraft-client
./gradlew build
```

## Особенности

### Mixin Система
Используется SpongePowered Mixin для внедрения кода в оригинальные классы Minecraft.
Пример в `MinecraftMixin.java` показывает как внедриться в методы рендеринга.

### Система Событий
Простая и эффективная система событий позволяет подписываться на различные события:
- `RENDER_WORLD` - вызывается при рендеринге мира
- `RENDER_GUI` - вызывается при рендеринга GUI

### Кастомный Рендерер
`CustomRenderer` предоставляет методы для:
- Рендеринга линий между точками
- Рендеринга коробок (ESP boxes)
- Рендеринга текста на экране

### GUI
`CustomGuiScreen` демонстрирует создание собственного экрана с кнопками и информацией.

## Расширение

Вы можете легко расширить функциональность:
1. Добавьте новые миксины в пакет `mixin`
2. Зарегистрируйте их в `mixins.client.json`
3. Добавьте новые события в `EventManager`
4. Используйте `CustomRenderer` для визуальных эффектов

## Лицензия

Этот проект создан в образовательных целях.
