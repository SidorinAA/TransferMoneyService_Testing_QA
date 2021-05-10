# TransferMoneyService
[![Build status](https://ci.appveyor.com/api/projects/status/dyxm53r1327wm4jc/branch/master?svg=true)](https://ci.appveyor.com/project/AlexeiSidorin/pageobject/branch/master)

## Описание тестируемого проекта Smoke test прототипа

Данный тест посвящен проверки новой функции перевода денег с одной карты на другую.

Мне был предоставлен SUT в виде черного ящика. Программа состояла из формы 
логина, страницы верификации (поддтвержение пользователя через sms) и страницы 
личного кабинета. 

Было заявлено два тестового пользователя, баланс которых 
проходил проверку. 


В ходе тестирования было проведено три теста. 

Два позитивных: перевод с одной карты на другую и наоборот.

Один негативный: перевод большей суммы чем было в наличии у клиекта. 

#### Баг отчет

[Возможная сумма перевода денег превышает реальную](https://github.com/AlexeiSidorin/PageObject/issues/1)

#### Запуск проекта

```
java -jar ./artifacts/app-ibank-build-for-testers.jar
```

#### Окружение
java 11, Gradle, JUnit5, Selenide, Appveyor
