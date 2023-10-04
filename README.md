# PharmacyApp
Мой проект Аптеки

Реализовал микросервисную архитектуру,так:

AuthService отвечает за авторизацию и аутентификацию

InventoryService отвечает за Инвентаризацию

MedicineService отвечает за лекарства и все,что связано с ними

OrderManagementSystem отвечает за заказы

SupplierService отвечает за поставщиков



Связь между сервисами устанавливается через WebClient(POST,GET запросы)

Пароли пользователей шифруются(BCRypt)

По ходу проекта были использованы следующие технологии:

  Spring(Web,MVC,Data JPA,Security,Boot)
  
  Hibernate
  
  ModelMapper
  
Предучсотрены методы отправляющие ответы в виде JSON,поэтому некоторые методы используют produces = json
