# Custom Minecraft Client

Кастомный Minecraft клиент с поддержкой:
- **Mixin системы** - для внедрения кода в Minecraft
- **Системы событий (Events)** - для обработки различных игровых событий
- **Кастомного рендерера** - ESP, линии, коробки и другие визуальные эффекты
- **Полноценной системы модулей** - с настройками Boolean, Float, Integer, Mode, Color
- **ClickGUI** - удобное управление модулями через графический интерфейс

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
│   ├── gui/
│   │   ├── CustomGuiScreen.java   # Кастомный GUI экран
│   │   └── clickgui/
│   │       ├── ClickGui.java      # Основной экран ClickGUI
│   │       ├── CategoryPanel.java # Панель категории модулей
│   │       ├── ModuleButton.java  # Кнопка модуля
│   │       ├── SettingComponent.java # Базовый компонент настройки
│   │       ├── BooleanComponent.java # Компонент Boolean настройки
│   │       ├── FloatComponent.java   # Компонент Float настройки (слайдер)
│   │       ├── IntegerComponent.java # Компонент Integer настройки (слайдер)
│   │       ├── ModeComponent.java    # Компонент Mode настройки (выбор)
│   │       └── ColorComponent.java   # Компонент Color настройки
│   └── module/
│       ├── Module.java            # Базовый класс модуля
│       ├── ModuleManager.java     # Менеджер всех модулей
│       ├── settings/
│       │   ├── Setting.java       # Базовый класс настройки
│       │   ├── BooleanSetting.java # Булева настройка
│       │   ├── FloatSetting.java   # Настройка float (слайдер)
│       │   ├── IntegerSetting.java # Настройка int (слайдер)
│       │   ├── ModeSetting.java    # Настройка mode (выбор из списка)
│       │   └── ColorSetting.java   # Настройка цвета
│       └── modules/
│           ├── FullBright.java    # Максимальная яркость
│           ├── BlockOverlay.java  # Подсветка блока на который смотришь
│           ├── StorageESP.java    # Подсветка сундуков и хранилищ
│           ├── AutoFish.java      # Автоматическая рыбалка
│           └── ClickPearl.java    # Бросок эндер-жемчуга по клику
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

## Управление

- **Правый Shift** - открыть ClickGUI
- **ЛКМ по модулю** - включить/выключить модуль
- **ПКМ по модулю** - раскрыть/скрыть настройки модуля
- **ЛКМ по заголовку категории** - перетаскивание панели
- **ПКМ по заголовку категории** - свернуть/развернуть категорию
- **ESC** - закрыть ClickGUI

## Особенности

### Mixin Система
Используется SpongePowered Mixin для внедрения кода в оригинальные классы Minecraft.
Пример в `MinecraftMixin.java` показывает как внедриться в методы рендеринга и обработки ввода.

### Система Событий
Простая и эффективная система событий позволяет подписываться на различные события:
- `RENDER_WORLD` - вызывается при рендеринге мира (для ESP, визуальных эффектов)
- `RENDER_GUI` - вызывается при рендеринга GUI (для ArrayList, overlay)

### Система Модулей
Каждый модуль имеет:
- Название и описание
- Категорию (Combat, Movement, Render, Player, Misc, World)
- Состояние (включен/выключен)
- Бинд клавиши для быстрого включения
- Список настроек различных типов

#### Типы настроек:
- **Boolean** - вкл/выкл (checkbox)
- **Float** - плавающее значение (слайдер с min/max)
- **Integer** - целочисленное значение (слайдер с min/max)
- **Mode** - выбор из списка (переключение кликом)
- **Color** - выбор цвета (с поддержкой rainbow режима)

### ClickGUI
Полноценный графический интерфейс для управления модулями:
- Перетаскиваемые панели категорий
- Раскрывающиеся списки настроек
- Визуальная индикация состояния модулей
- Поддержка мыши и клавиатуры

### Кастомный Рендерер
`CustomRenderer` предоставляет методы для:
- Рендеринга линий между точками
- Рендеринга коробок (ESP boxes)
- Рендеринга текста на экране

## Встроенные модули

### Combat
- **ClickPearl** - бросок эндер-жемчуга по правому клику
  - AutoSwap - автоматический поиск жемчуга в инвентаре
  - OnlyInAir - работа только в воздухе
  - Rotate - автоматический поворот при броске
  - Delay - задержка между бросками
  - PitchThreshold - порог угла для броска

### Render
- **FullBright** - максимальная яркость без изменения настроек игры
  - Smooth - плавный переход яркости
  - Brightness - уровень яркости (0.5-1.0)

- **BlockOverlay** - подсветка блока на который смотришь
  - Outline/Filled - режимы отображения
  - OutlineColor/FillColor - настройка цветов
  - Alpha - прозрачность заполнения

- **StorageESP** - подсветка сундуков и хранилищ
  - Chests/EnderChests/Barrels/ShulkerBoxes - типы хранилищ
  - RenderMode - режим рендеринга (Outline/Filled/Both)
  - Range - дальность прорисовки
  - Color - цвет подсветки

### Player
- **AutoFish** - автоматическая рыбалка
  - AutoRecast - автоматический перезаброс
  - DetectBite - детектирование поклёвки по частицам
  - CastDelay - задержка перед забросом
  - BobberWaitTime - время ожидания поплавка
  - SoundAlert - звуковое оповещение

## Расширение

### Добавление нового модуля:
1. Создайте класс в `module/modules`, унаследовавшись от `Module`
2. Добавьте настройки через `addSetting()` в конструкторе
3. Переопределите методы `onEnable()`, `onDisable()`, `onTick()`, `onRender()`
4. Зарегистрируйте модуль в `CustomClient.registerModules()`

### Добавление новой настройки:
1. Создайте класс в `module/settings`, унаследовавшись от `Setting<T>`
2. Реализуйте метод `getType()`
3. Добавьте компонент в `gui/clickgui`, унаследовавшись от `SettingComponent`
4. Обновите `ModuleButton.createSettingComponent()`

### Добавление новых миксинов:
1. Создайте класс в пакете `mixin` с аннотацией `@Mixin`
2. Зарегистрируйте его в `mixins.client.json`
3. Используйте `@Inject`, `@Redirect` и другие аннотации для внедрения кода

## Лицензия

Этот проект создан в образовательных целях.
