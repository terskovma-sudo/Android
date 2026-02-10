# Android VK авторизация

Минимальный пример Android-приложения с кнопкой **«Войти через VK»**.

## Что сделано
- Экран с кнопкой входа через VK.
- Запуск OAuth в Chrome Custom Tabs.
- Обработка deep link `vk<APP_ID>://oauth.vk.com/blank.html` и получение `access_token`.

## Настройка
1. Создайте VK-приложение и получите `APP_ID`.
2. Вставьте `APP_ID` в:
   - `app/build.gradle.kts` в `manifestPlaceholders["vkClientId"]`
   - `app/src/main/java/com/example/vkauth/VkAuthManager.kt` в `VK_CLIENT_ID`
3. Убедитесь, что redirect URI в настройках VK совпадает:
   - `vk<APP_ID>://oauth.vk.com/blank.html`

## Важно
- Для production не храните `client_secret` в мобильном приложении.
- Рекомендуется обмен code/token через ваш backend.
