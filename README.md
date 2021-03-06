# Travel-agency

## TZ
Турагенство має каталог Турів. Для каталогу реалізувати можливість вибірки турів:
- за типом (відпочинок, екскурсія, шопінг);
- за ціною;
- за кількістю осіб;
- за типом готелю.

Користувач реєструється в системі, обирає Тур і робить Замовлення. Після замовлення тур має статус 'зареєстрований'.
Незареєстрований користувач не має можливості замовляти тур.
Користувач має особистий кабінет, в якому міститься коротка інформація про нього, а також список обраних турів і їх поточний статус (зареєстрований, сплачений, скасований).
Менеджер визначає тур як 'палаючий'. 'Палаючі' тури завжди відображаються нагорі переліку. Менеджер переводить статус туру з 'зареєстрований' у 'оплачений' або 'скасований'. На кожен замовлений тур визначається знижка з кроком, який встановлюється менеджером, але не більше відсотка, який так само визначає менеджер.
Адміністратор системи володіє такими ж правами, як і менеджер, а додатково може:
- додати/видалити тур, змінити інформацію про тур;
- заблокувати/розблокувати користувача.

## Use Case diagram
![use_case_diagram](diagrams/use_case_diagram.png)

## ER diagram
![er_diagram](diagrams/er_diagram.png)

## DB diagram
![db_diagram](diagrams/db_diagram.png)

## Running
In order to run this app I propose you to use Docker.
1. Clone this repo:
```
git clone https://github.com/OlegChuy/Travel-agency.git
```
2. Build custom postgreSQL image and run docker-compose file.
   * Building custom postgreSQL image:
   ```
   $path\Travel-agency: docker build -t travel_agency/custom_db -f .\docker_files\postgres_custom\Dockerfile .
   ```
   * Running docker-compose file (it will automatically pull app image from docker hub):
   ```
   $path\Travel-agency: docker-compose up -d
   ```
   
