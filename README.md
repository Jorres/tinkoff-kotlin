#### Немного о задании

Я никогда еще не писал столько бойлерплейта в своей жизни! Это несколько новое ощущение. На самом деле, 
я понимаю, что доля бойлерплейта уменьшится, как только приложение станет обзаводиться мало-мальски значимой
логикой, так что это задание, получается, небольшой скелет, на который можно накручивать очень много чего. 


Я осознанно добавил слой сервисов, хоть все, что они делают, это прокидывают запрос к базе данных, потому что
в будущем как раз здесь место для новой логики (хоть это простое приложение пока ей не обладает).


Еще я добавил обработку ошибок только в GET запросы на получение сущностей, потому что я не уверен, правильный
ли я подход выбрал для обработки ошибок. Я написал об этом в чатик телеграма длиннопост, но никто не ответил, 
поэтому пока оставил только один запрос с обработкой ошибок, а все остальные при, например, отсутствии данных 
в базе данных я оставил как undefined behaviour. Посоветуйте, пожалуйста, как норм хендлить ошибки! 


А еще я запилил для валидации параметров запроса специальный костыль `validateId`, и я очень очень не уверен, что это хорошо. 
Я потратил много времени, чтобы попробовать найти валидацию, которую должен бы делать ktor(мне кажется, что 
валидация параметров строки запроса - очень нужная вещь). 
