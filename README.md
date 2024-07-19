# Маркетплейс

## Описание

Этот проект представляет собой маркетплейс, позволяющий продавцам размещать свои товары для продажи, а покупателям — находить и приобретать эти товары.

## Установка и запуск

### Клонирование репозитория

1. Клонируйте репозиторий на ваш локальный компьютер:

   ```bash
   git clone https://github.com/TimurTimurych/Marketplace.git
   cd Marketplace

### Настройка базы данных

1. Установите PostgreSQL, если он ещё не установлен.
2. Создайте базу данных и таблицы, используя файл в корне проекта или с помощью SQL-скрипта:
```sql
        -- Создание таблицы продавцов
        CREATE TABLE sellers (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        contact_info TEXT
        );
        
        -- Создание таблицы товаров
        CREATE TABLE products (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        description TEXT,
        price DECIMAL(10, 2) NOT NULL,
        stock_quantity INTEGER NOT NULL,
        seller_id INTEGER REFERENCES sellers(id) ON DELETE SET NULL
        );
```
3. Настройте параметры подключения к базе данных в вашем проекте. Измените настройки в файле src/main/resources/application.properties или application.yml, например:

        spring.datasource.url=jdbc:postgresql://localhost:5432/ваша_база_данных
        spring.datasource.username=ваш_пользователь
        spring.datasource.password=ваш_пароль

### Сборка и запуск
1. Убедитесь, что у вас установлены Maven и JDK. Затем выполните следующие команды для сборки и запуска проекта:

    `mvn clean install
    mvn spring-boot:run`

Приложение будет доступно по адресу http://localhost:8080.

## API

**Коллекция Postman находится в корне проекта.**

## Продавцы

### Добавление продавца

- **Метод:** POST
- **URL:** /api/sellers/add
- **Описание:** Добавляет нового продавца.
- **Тело запроса:**
```json
    [
    {
        "name": "Seller1",
        "contactInfo": "seller1@example.com",
        "address": "123 Main St"
    },
    {
        "name": "Seller2",
        "contactInfo": "seller2@example.com",
        "address": "123 Main St"
    },
    {
        "name": "Seller3",
        "contactInfo": "seller3@example.com",
        "address": "123 Main St"
    }
   ]
 ```

### Поиск продавца по ID

- **Метод:** GET
- **URL:** /api/sellers/{id}
- **Описание:** Возвращает информацию о продавце по указанному ID.

### Просмотр всех продавцов

- **Метод:** GET
- **URL:** /api/sellers
- **Описание:** Возвращает список всех продавцов.

### Изменение информации о продавце

- **Метод:** PUT
- **URL:** /api/sellers/{id}
- **Описание:** Обновляет информацию о продавце по указанному ID.
- **Тело запроса:**
    ```json
    {
    "name": "Updated Seller",
    "contactInfo": "updated_seller@example.com",
    "address": "456 Main St"
   }
    ```

### Удаление продавца

- **Метод:** DELETE
- **URL:** /api/sellers/{id}
- **Описание:** Удаляет продавца по указанному ID.

## Товары

### Добавление товара

- **Метод:** POST
- **URL:** /api/products/add
- **Описание:** Добавляет новый товар.
- **Тело запроса:**
    ```json
    [
    {
        "name": "Product1",
        "description": "Desc1",
        "price": 1.99,
        "stockQuantity": 200,
        "sellerId": 3
    },
    {
        "name": "Product2",
        "description": "Desc2",
        "price": 5.99,
        "stockQuantity": 100,
        "sellerId": 3
    },
    {
        "name": "Product3",
        "description": "Desc3",
        "price": 4.99,
        "stockQuantity": 50,
        "sellerId": 3
    }
   ]
    ```

### Редактирование товара

- **Метод:** PUT
- **URL:** /api/products/{id}
- **Описание:** Обновляет информацию о товаре по указанному ID.
- **Тело запроса:**
    ```json
    {
        "name": "Updated",
        "description": "Updated",
        "price": 30.99,
        "stockQuantity": 50,
        "sellerId": 3
    }
    ```

### Удаление товара

- **Метод:** DELETE
- **URL:** /api/products/{id}
- **Описание:** Удаляет товар по указанному ID.

### Просмотр всех товаров

- **Метод:** GET
- **URL:** /api/products
- **Описание:** Возвращает список всех доступных товаров.

### Просмотр товара по ID

- **Метод:** GET
- **URL:** /api/products/{id}
- **Описание:** Возвращает детальную информацию о товаре по указанному ID.

### Покупка товара

- **Метод:** PUT
- **URL:** /api/products/:id/buy?quantity=:quantity
- **Описание:** Позволяет добавить товар в корзину и оформить заказ. Обновляет количество на складе.
