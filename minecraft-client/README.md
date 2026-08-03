# Custom Client - Minecraft 1.21.3 Fabric Mod

Кастомный Minecraft клиент с ClickGUI, модулями, миксинами и системой событий.

## 🚀 Возможности

### Система модулей
- **FullBright** - максимальная яркость (ночное зрение)
- **BlockOverlay** - подсветка блока на который смотришь
- **StorageESP** - подсветка сундуков, эндер-сундуков, шалкер-боксов
- **AutoFish** - автоматическая рыбалка
- **ClickPearl** - бросок эндер-жемчуга по клику

### Система настроек
- `BooleanSetting` - булева настройка (вкл/выкл)
- `FloatSetting` - плавающее значение с min/max
- `IntegerSetting` - целочисленное значение с min/max
- `ModeSetting` - выбор из списка режимов
- `ColorSetting` - настройка цвета с rainbow

### ClickGUI
- Перетаскиваемые панели категорий
- Включение/выключение модулей ЛКМ
- Раскрытие настроек ПКМ
- Компоненты для каждого типа настроек

### Система событий
- `RenderWorldEvent` - событие рендера мира
- `RenderGuiEvent` - событие рендера GUI
- `DamageEvent` - событие получения урона
- `MotionEvent` - событие движения

### Миксины
- `MinecraftClientMixin` - внедрение в рендер
- `WorldRendererMixin` - рендеринг мира
- `KeyboardMixin` - обработка клавиш

## 📦 Установка

1. Установите [Fabric Loader](https://fabricmc.net/use/) для Minecraft 1.21.3
2. Установите [Fabric API](https://modrinth.com/mod/fabric-api)
3. Соберите мод: `./gradlew build`
4. Скопируйте `.jar` из `build/libs/` в папку `mods`

## 🎮 Управление

- **Правый Shift** - открыть ClickGUI
- **ЛКМ по модулю** - включить/выключить
- **ПКМ по модулю** - раскрыть настройки
- **ЛКМ по заголовку панели** - перетаскивание
- **ПКМ по заголовку панели** - свернуть/развернуть

## 🏗️ Архитектура

```
com.customclient/
├── client/          - Основной класс клиента
├── module/          - Система модулей
│   ├── Module       - Базовый класс модуля
│   ├── ModuleManager - Менеджер модулей
│   └── ...          - Конкретные модули
├── setting/         - Система настроек
│   ├── Setting<T>   - Базовый класс
│   └── ...          - Типы настроек
├── event/           - Система событий
│   ├── Event<T>     - Базовый класс события
│   └── EventSystem  - Менеджер событий
├── gui/clickgui/    - ClickGUI
│   ├── ClickGui     - Основной экран
│   ├── CategoryPanel - Панель категории
│   ├── ModuleButton - Кнопка модуля
│   └── ...          - Компоненты настроек
└── mixin/           - Миксины
    └── client/      - Клиентские миксины
```

## ➕ Добавление нового модуля

```java
public class MyModule extends Module {
    private final BooleanSetting mySetting = new BooleanSetting("Setting", "Description", true);
    
    public MyModule() {
        super("MyModule", "Description", Category.MISC);
        addSetting(mySetting);
    }
    
    @Override
    public void onEnable() {
        // При включении
    }
    
    @Override
    public void onTick() {
        // Каждый тик
    }
}
```

Затем зарегистрируйте в `ModuleManager.init()`:
```java
register(new MyModule());
```

## ⚙️ Сборка

```bash
./gradlew build
```

Артефакт будет в `build/libs/`

## 📄 Лицензия

MIT License
