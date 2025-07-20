# Developer Tutorial

## Table of Contents
- [Introduction](#introduction)
- [Requirements](#requirements)
- [Setup](#setup)
    - [Backend](#backend)
    - [Frontend](#frontend)
- [Workflow](#workflow)
     - [Backend](#backend)
    - [Frontend](#frontend)
- [Troubleshooting](#troubleshooting)

## Introduction
Maniano is a web application to share music files with other pianists.

## Requirements
- [Git](https://git-scm.com/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop)
1. Backend devs:
    - [IntelliJ IDEA (free)](https://www.jetbrains.com/idea/download/)
2. Frontend devs:
    - [Visual Studio Code](https://code.visualstudio.com/download)
    - [Node.js and npm](https://nodejs.org/)

## Setup

### Backend
There is no required setup to configure the backend (other than IntelliJ installation).

For the backend server, we'll be using Java (JDK 21), the Spring Boot framework (v3.3.4), and Maven build tools. It is configured to use the Spring Data JPA framework paired with the Hibernate ORM.

We'll be using Docker compose to start the backend server and a PostgreSQL database simultaneously.


### Frontend
For the frontend server, we'll be developing in TypeScript with the React library and the Vite build tool. We are utilizing components from the [MUI library](https://mui.com/material-ui/all-components/), using the [Minimal UI](https://github.com/minimal-ui-kit/material-kit-react) template as a starting point.
1. Double-check that you have node (v20.19.0 LTS recommended) and npm installed:
     ```sh
     node -v
     ```
     ```
     npm -v
     ```
2. On a fresh clone, ask Hooman for the MAPS .env file and copy it into your folder.
3. Navigate to the frontend directory:
     ```sh
     cd frontend
     ```
4. Install the React dependencies:
     ```sh
     npm install
     ```

## Workflow

### Backend
1. Open Docker Desktop.
2. Navigate to the backend directory:
     ```sh
     cd backend
     ```
3. Build and run the Docker container with the following command:
     ```sh
     docker compose up
     ```

This automatically starts a local PostgreSQL server, builds the backend executable with a snapshot of the current code, and runs both on the same network. 

4. Write some code (IntelliJ recommended)
5. Run the following command to stop all running containers then clean ALL Docker containers, images, volumes, and networks:
     ```sh
     docker rm -f $(docker ps -aq) && docker system prune -a --volumes -f
     ```

6. Rebuild and rerun the Docker container to apply your changes (repeat 3-5)

### Frontend
1. Make sure the backend is running (see above)
2. Navigate to the frontend directory:
     ```sh
     cd frontend
     ```
3. Run the React app:
     ```sh
     npm run dev
     ```
4. Navigate to [http://localhost:8081/](http://localhost:8081/) or the network URL in your browser.
5. Write some code. VSCode is recommended, as we use the "Prettier" extension for automatic formatting.
6. If you encounter eslint errors, run the following command to automatically fix them:
     ```sh
     npx eslint --fix .
     ```

Note: Vite automatically watches for code changes and will reflect them on the app when developing locally. So, you don't need to rerun ```npm run dev``` after you make changes.

## Troubleshooting
![image](https://github.com/user-attachments/assets/b745b775-8e94-4b3f-8537-0d95af4352b5)
Run the following command in your terminal to clean ALL Docker containers, images, volumes, and networks:
     ```sh
     docker rm -f $(docker ps -aq) && docker system prune -a --volumes -f
     ```

Then, recompose Docker.
