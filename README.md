# ![Logo](./img/tripmatelogo.png)
> TripMate is a web app that helps users discover and explore exciting experiences such as cultural tours, outdoor adventures, and culinary activities. The app features an intuitive interface and curated categories that make it easy for users to find experiences that match their interests and preferences. Users can read reviews and ratings from other travelers who have experienced these activities before, and filter experiences based on location. TripMate is a powerful tool for travelers who want to discover new experiences while on the go.

> Live demo [_here_](https://tripmate-s7-09.netlify.app/).
> Backend Demo [_here_](https://deploy-tripmate-production.up.railway.app/swagger-ui/index.html#).

## Table of Contents
* [General Info](#general-information)
* [Our Team](#our-team)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
  * [Backend (Swagger)](#backend-swagger)
  * [Frontend](#frontend-1)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)

## General Information
This project was carried out over a period of one month for [_No Country_](https://www.nocountry.tech/perfilesit). Our goal was to develop a web application that helps travelers discover exciting experiences while on the go, allowing users to explore a wide range of cultural tours, outdoor adventures, culinary activities, and more, all in one place. With its intuitive interface and selected categories, TripMate makes it easy for users to find experiences that match their interests and preferences. Travelers can also read reviews and ratings from other users who have experienced these activities before, and filter experiences by location, making it easy to search and plan their trips. While users cannot book experiences directly through the application, they can easily contact experience providers to make reservations and plan their trips, ensuring a stress-free and hassle-free experience. Overall, TripMate is a powerful tool for travelers who want to discover new experiences and make the most of their trips."

## Our Team
**Frontend**
- Adrián Centurión: [**GitHub**](https://github.com/adrianmcenturion) [**LinkedIn**](https://www.linkedin.com/in/adrian-centurion/)
- Darío Elguero: [**GitHub**](https://github.com/Dario-Elguero) [**LinkedIn**](https://www.linkedin.com/in/dario-elguero/)
- Jordi Mantilla: [**GitHub**](https://github.com/JordiM21) [**LinkedIn**](https://www.linkedin.com/in/jordi-mantilla21/)

**Backend**
- Alex Lihuel Mujica: [**GitHub**](https://github.com/LihuelMujica) [**LinkedIn**](https://www.linkedin.com/in/lihuelmujica)
- Analia Casali [**GitHub**](https://github.com/AnaliaCasali) [**LinkedIn**](https://www.linkedin.com/in/analiacasali/)
- Christian Herrera [**GitHub**]() [**LinkedIn**](https://www.linkedin.com/in/chris-herrera-dev/)
- Gabriel Mayantz Remes [**GitHub**](https://github.com/Gabusy07) [**LinkedIn**](https://www.linkedin.com/in/gabriel-mayantz-remes/)

**UX/UI**
- Andrés Cajas: [**LinkedIn**](https://www.linkedin.com/in/afcv10/)
- Gerardo Vargas: [**LinkedIn**](https://www.linkedin.com/in/geravargas/)

## Technologies Used
- **Backend:** Java, Spring Boot, Spring Security, Hibernate, REST, API, JWT, JSON, Maven, Apache, JUnit, Mockito Swagger, MapStruct
- **Database:** MySQL
- **Frontend:** HTML, CSS, Typescript, React, Redux, React-Query, Tailwind
- **Infrastructure:** EC2, RDS, Netlify, Railway
- **Tools:** Git, GitHub, VSCode, IntelliJ IDEA, ESLint Postman, Trello, Discord, Slack, Figma

## Features
Developed features:

- Login
- Experience list
- Inspect experience details, characteristics and geolocalization
- Find close experiences
- Review experiences

## Setup
### Prerequistes
For running this project you need:
- SDK Java 18
- Maven 3.8 or above
- NodeJs
- MySQL
- Git
- You have to clone this repository using: 
```console
git clone https://github.com/No-Country/s7-09-T-JavaReact
```
### Database
You have to create a new database schema. When you start the application hibernate will autogenerate all tables.

### Backend
1. Install any required dependencies by running the following command in the backend directory of the project:
```console
mvn install
```
2. You have to set the following environment variables
```env
DATABASE_HOST=
DATABASE_NAME=
DATABASE_PASSWORD=
DATABASE_PORT=
DATABASE_USERNAME=
SERVER_PORT=
```
3. Build the project using the following command:
```console
mvn package
```
4. Run the project using the following command:
```console
java -jar target/your-project.jar
```

This will start the Spring Boot application and make it available at the port you set in SERVER_PORT varaible

### Frontend
1. Install any required dependencies by running the following command in the frontend directory of the project:
```console
npm i
```
2. Configure the backend url in an .env file
3. Run the frontend in dev mod using
```console
npm run dev
```
or you can create a build using:
```console
npm run build
```

## Usage

### Backend (Swagger)
You can acces api documentation in swagger in http://localhost:port/swagger-ui/index.html#.

### Frontend

Acá van fotos del flujo normal de un usuario


## Project Status

Project is _no longer being worked on_ since it was a one month project

## Room for Improvement
Room for improvement:
- Making automatized integration tests
- Getting unit test lines coverage higher than 80% in the whole application

To do: 
- Implement administrator flux: full CRUD of experiences, categories, cities, contacts and providers.
- Implement provider flux: provider registration and expirience creation flux for providers
- Add more options for geolocalizations
- Implement creditcard payment
- Allow users to make bookings
- Add an option to the admin for making offers
